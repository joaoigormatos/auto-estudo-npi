<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
         <v-toolbar color="primary" dark flat>
            <v-toolbar-title class="text--center">Add User</v-toolbar-title>
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
             <v-spacer></v-spacer>
            <v-btn color="red darken-3 white--text" text @click="closeDialog">Close</v-btn>
            <v-btn color="blue darken-1 white--text" text @click="onSubmit">Save</v-btn>
            </v-card-actions>
          </v-form>
      </v-card>
    </v-dialog>
  </v-row>
</template>
<script>
import { RepositoryFactory } from "@/repositories/RepositoryFactory";
const usuarioRepo = RepositoryFactory.get("usuario");

export default {
  props: {
    dialog: {
      type: Boolean,
      required: true
    }
  },
  data: () => ({
    valid: true,
    email: "",
    password: "",
    name: "",
    enabled: "",
    items: ["Online", "Offline"],
    error: "",
    requiredRule: [v => !!v || "Campo obrigatório"],
    dialog: false,
  }),
  methods: {
        onSubmit() {
      if (this.$refs.form.validate()) {

        const user = {}
        user.email = this.email;
        user.name = this.name;
        user.password =  this.password;
        user.enabled =  this.enabled;
        
  usuarioRepo
      .addUser(user)
      .then(res => {
        this.closeDialog();
        this.$emit('userAdded')
      })
      .catch(console.error);

      }
    },
    closeDialog() {
      this.$emit("dialogClose");
    }
  }
};
</script>
<style scoped>
</style>