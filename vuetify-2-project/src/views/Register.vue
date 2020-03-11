<template>
  <v-container fluid class="fill-height">
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="4">
        <v-card class="elevation-12">
          <v-toolbar color="primary" dark flat>
            <v-toolbar-title>Register</v-toolbar-title>
          </v-toolbar>
          <v-form @submit.prevent="onSubmit" ref="form" lazy-validation v-model="valid">
            <v-card-text>
              <p class="error--text text-center">{{error}}</p>
              <v-text-field
                label="Email"
                name="email"
                prepend-icon="person"
                type="text"
                v-model="email"
                required
                :rules="requiredRule"
              />
              <v-text-field
                label="Name"
                name="name"
                prepend-icon="mdi-account-outline"
                type="text"
                v-model="name"
                required
                :rules="requiredRule"
              />
              <v-text-field
                id="password"
                label="Password"
                name="password"
                prepend-icon="lock"
                type="password"
                v-model="password"
                required
                :rules="requiredRule"
              />
              <v-select outlined :items="items" label="Enabled" small v-model="enabled"/>
            </v-card-text>
            <v-card-actions>  
             <v-btn left  @click="loginPage" color="green accent-4 white--text">
               <v-icon small left>mdi-arrow-left</v-icon>
               <span>Login</span>
               </v-btn>
              <v-spacer />
              <v-btn color="light-blue darken-4 white--text" type="submit" :loading="loading" >
                <span>Singup</span>
                <v-icon small right>mdi-arrow-right</v-icon>
                </v-btn>
            </v-card-actions>
          </v-form>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import { mapGetters, mapState } from "vuex";

export default {
  props: ["to", "invalidToken"],
  data: () => ({
    valid: true,
    loading: false,
    email: "",
    password: "",
    name: "",
    enabled:"",
    items: ["Online", "Offline"],
    error: "",
    requiredRule: [v => !!v || "Campo obrigatório"]
  }),
  created() {
    if (this.invalidToken) {
      this.error = "Sua sessão expirou. Faça login novamente";
    }
  },
  methods: {
    onSubmit() {
      this.loading = true;
      if (this.$refs.form.validate()) {
        this.$store
          .dispatch("auth/register", {
            email: this.email,
            password: this.password,
            name:this.name,
            enabled:this.enabled
          })
          .then((res) => {
            if (res) {
              this.$router.push("/login");
            } else {
              this.error = "Usuário e/ou senha inválidos";
            }
          });
      }

      this.loading = false;
    },
    loginPage(){
      this.$router.push('/login')
    }
  },
  computed: {
    ...mapState({
      token: state => state.auth.token
    })
  }
};
</script>