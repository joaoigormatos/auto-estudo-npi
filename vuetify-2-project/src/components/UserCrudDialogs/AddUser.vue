<template>
    <v-container>
        <v-row align="center" justify="center" >
         <v-dialog v-model="dialog" persistent >
        <v-col cols="12" sm="8" md="4" flat>
        <v-card flat >
          <v-toolbar color="primary" dark flat>
            <v-toolbar-title>Add user</v-toolbar-title>
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
            <v-spacer />
          <v-btn color="red darken-1" text @click="$emit('dialogClose')">Close</v-btn>
          <v-btn color="green accent-4 white--text" text @click="$emit('addUserDialog')">Save</v-btn>


            </v-card-actions>
          </v-form>
        </v-card>
      </v-col>
        </v-dialog>
    </v-row>
    </v-container>
    
</template>
<script>
export default {
    props:{
        dialog:{
            type:Boolean,
            required:true
        }
    },
     data: () => ({
    valid: true,
    loading: false,
    email: "",
    password: "",
    name: "",
    enabled:"",
    items: ["Online", "Offline"],
    error: "",
    requiredRule: [v => !!v || "Campo obrigatório"],dialog:false
  }),
     methods:{
        addUser(){

        }
        ,closeDialog(){ 
            this.$emit('dialogClose')
        }
    }
}
</script>
<style scoped>

</style>