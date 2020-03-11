<template>
  <div>

    <v-card-widget enableActions :title="'Página Inicial'">
      <div slot="widget-header-action" >
        
          <!-- <template v-slot:activator="{ on }"  > -->
             <v-btn @click="toggleAddDialog" color="green white--text" flat text>
              <v-icon>mdi-plus</v-icon>
              Add user
             </v-btn>
         <!-- </template> -->
                <AddUser :dialog="addUserDialog" @dialogClose="toggleAddDialog" @addUserDialog="addUser"/>
              
      </div>
      <div slot="widget-content">
        <v-row>
          <v-col cols="12">
            <v-data-table
                    :headers="headers"
                    :items="usuarios"
                    :items-per-page="5"
                    class="elevation-1"
            ></v-data-table>
          </v-col>
        </v-row>
      </div>
    </v-card-widget>
  </div>
</template>

<script>
// @ is an alias to /src
import VCardWidget from "@/components/VWidget";
import AddUser  from "@/components/UserCrudDialogs/AddUser";

import {RepositoryFactory} from "@/repositories/RepositoryFactory";
const usuarioRepo = RepositoryFactory.get("usuario");

export default {
  name: 'home',
  components: {
    VCardWidget , AddUser},

  data: () => ({
    headers: [
      {
        text: 'Nome',
        align: 'left',
        value: 'nome',
      },
      { text: 'Email', value: 'email' },
      {text:'Remove', value: 'add user'}
    ],
    usuarios :[],
    addUserDialog:false,
  }),

  created() {
    usuarioRepo.getAll().then(res => {
      this.usuarios = res.data;
    }).catch(console.error);
  },

  computed: {

  },

  methods: {
      removeUser(){

      },
      editUser(){

      },
      addUser(){

      },
      toggleAddDialog(){
        this.addUserDialog = !this.addUserDialog;
      }

    }
}
</script>
