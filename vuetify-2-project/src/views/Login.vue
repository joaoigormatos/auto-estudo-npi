<template>
    <v-container fluid class="fill-height">
        <v-row align="center" justify="center">
            <v-col cols="12" sm="8" md="4" >
                <v-card class="elevation-12">
                    <v-toolbar  color="blue-grey darken-2" dark flat>
                        <v-toolbar-title class="text-center">Login</v-toolbar-title>
                    </v-toolbar>
                    <v-form @submit.prevent="onSubmit" ref="form" lazy-validation v-model="valid">
                        <v-card-text>
                            <p class="error--text text-center">{{error}}</p>
                            <v-text-field label="Login" name="email" prepend-icon="person" type="text" v-model="email" required :rules="requiredRule"/>
                            <v-text-field id="password" label="Password" name="password" prepend-icon="lock" type="password" v-model="password" required :rules="requiredRule"/>
                      
                        </v-card-text>
                        <v-card-actions>
                            <v-btn left  @click="registerPage" color="green accent-4 white--text">Singup</v-btn>
                            <v-spacer/>
                            <v-btn right color="indigo darken-3 white--text" type="submit" :loading="loading"  >Login</v-btn>
                        </v-card-actions>
                    </v-form>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import { mapGetters, mapState } from 'vuex'
    import Register from './Register'
    export default {
        props: ["to", "invalidToken"],
        components:{
            Register
        },
        data: () => ({
            valid: true,
            loading: false,
            email: '',
            password: '',
            error: '',
            requiredRule: [
                v => !!v || 'Campo obrigatório'
            ],
        }),
        created() {
            if (this.invalidToken) {
                this.error = 'Sua sessão expirou. Faça login novamente'
            }
        },
        methods: {
            onSubmit () {
                this.loading = true
                if (this.$refs.form.validate()) {
                    this.$store.dispatch('auth/login', {email: this.email, password: this.password})
                        .then(() => {
                            if (this.$store.getters['auth/isAuthenticated']) {
                                this.$router.push('/')
                            } else {
                                this.error = 'Usuário e/ou senha inválidos'
                            }
                        })
                }
                this.loading = false
            },
            registerPage(){
                this.$router.push('/register')
            }
        },
        computed: {
            ...mapState({
                token: state => state.auth.token
            })
        }
    };
</script>

<style scoped lang="css">

</style>
