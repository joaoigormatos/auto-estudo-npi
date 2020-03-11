<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="290">
     
      <v-card>
        <v-card-title class="headline">Are you sure that you do want to delete this?</v-card-title>
        <v-card-text>This user will be removed from the system.</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
            <v-btn color="blue darken-1 white--text" text @click="closeDialog">Close</v-btn>
            <v-btn color="red darken-3 white--text" text @click="deleteUser">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>
<script>
import { RepositoryFactory } from "@/repositories/RepositoryFactory";
const usuarioRepo = RepositoryFactory.get("usuario");


export default {
    props:{
        user:{
            type:Object,
            required:true
        },
        dialog:{
            type:Boolean,
            required:true
        }
    },
    
    data(){
        return {

        }
    },
    methods:{
        closeDialog() {
            this.$emit("dialogClose");
        },
        deleteUser(){
            console.log(this.user)
            usuarioRepo
      .removeUser(this.user.id)
      .then(res => {
        this.closeDialog();
        this.$emit('userDeleted')
      })
      .catch(console.error);

      }
        }
    
}
</script>

<style scoped>

</style>