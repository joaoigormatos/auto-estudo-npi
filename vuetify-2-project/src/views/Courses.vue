<template>
  <div>
    <v-card-widget enableActions :title="'Cursos'">
      <div slot="widget-header-action">
        <v-btn @click="toggle('addUserDialog')" color="green white--text"  text>
          <v-icon>mdi-plus</v-icon>Add user
        </v-btn>
      </div>
      <div slot="widget-content">
        <v-row>
          <v-col cols="12">
            <v-simple-table  :fixed-header="true">
              <template v-slot:default>
                <thead>
                  <tr >
                        <th v-for="header in headers" :key="header.text">{{header.text}}</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="usuario in usuarios" :key="usuario.name" class="text-left">
                    <th >
                      {{usuario.nome}}
                    </th>
                     <th >
                      {{usuario.email}}
                    </th>
                    <th>
                      <v-btn text>
                        <v-icon @click="removeUserDialogToggle(usuario)" color="red darken-2" >
                          mdi-close-circle
                        </v-icon>
                      </v-btn>
                    </th>
                    <th>
                      <v-btn text>
                        <v-icon @click="editUserDialogToggle(usuario)" color="blue darken-2" >
                          mdi-pencil
                        </v-icon>
                      </v-btn>
                    </th>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
          </v-col>
          <v-col>
            <AddUser
              :dialog="addUserDialog"
              @dialogClose="toggle('addUserDialog')"
              @userAdded="getAllUsers"
            />

          <EditUser v-if={editUserDialog}
              :user="user"
              :dialog="editUserDialog"
              @dialogClose="toggle('editUserDialog')"
              @userUpdated="getAllUsers"
            />
            <RemoveUser
              :user="user"
              :dialog="removeUserDialog"
              @dialogClose="toggle('removeUserDialog')"
              @userDeleted="getAllUsers"
            />

            

          </v-col>
        </v-row>
      </div>
    </v-card-widget>
  </div>
</template>

<script>
// @ is an alias to /src
import VCardWidget from "@/components/VWidget";
import AddUser from "@/components/UserCrudDialogs/AddUser";
import EditUser from "@/components/UserCrudDialogs/EditUser"
import RemoveUser from "@/components/UserCrudDialogs/RemoveUser"

import { RepositoryFactory } from "@/repositories/RepositoryFactory";
const usuarioRepo = RepositoryFactory.get("usuario");

export default {
  name: "home",
  components: {
    VCardWidget,
    AddUser,
    EditUser,
    RemoveUser
  },

  data: () => ({
    headers: [
      {
        text: "Nome",
        value: "nome"
      },
      { text: "Email", value: "email" },
      { text: "Remove", value: "remove" },
      { text: "Edit", value: "edit" }
    ],
    usuarios: [],
    user:{},
    addUserDialog: false,
    editUserDialog: false,
    removeUserDialog: false
  }),

  created() {
    usuarioRepo
      .getAll()
      .then(res => {
        this.usuarios = res.data;
      })
      .catch(console.error);
  },

  computed: {},

  methods: {
    removeUser() {},
    editUser() {},
    addUser() {},
    toggle(value) {
      this[value] = !this[value];
    },
    editUserDialogToggle(user){
      this.user = user
      console.log(user)
      this.editUserDialog = !this.editUserDialog
    },removeUserDialogToggle(user){
      this.user = user
      this.removeUserDialog = !this.removeUserDialog
    },
    getAllUsers(){
      usuarioRepo
      .getAll()
      .then(res => {
        this.usuarios = res.data;
      })
      .catch(console.error);
    }

  }
};
</script>
