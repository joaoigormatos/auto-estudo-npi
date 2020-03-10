<template>
  <v-app>
    <template v-if="!$route.meta.public">
      <v-app-bar color="primary" dark dense app>
        <div class="d-flex align-center">
          <v-img
            alt="Vuetify Logo"
            class="shrink mr-2"
            contain
            src="/static/images/logo01.png"
            transition="scale-transition"
            width="40"
          />
        </div>
        <v-toolbar-items class="ml-3">
          <v-btn to="/" text>Início</v-btn>
        </v-toolbar-items>

        <v-spacer></v-spacer>

        <span>Olá, {{username}}</span>
        <router-link :to="{path: '/logout',name:'Logout'}">
          <v-btn text>
            <v-icon>power_settings_new</v-icon>
          </v-btn>
        </router-link>
      </v-app-bar>

      <v-content class="content">
        <v-container fluid class="page-wrapper">
          <router-view></router-view>
        </v-container>
      </v-content>

      <!-- App Footer -->
      <v-footer height="auto" class="white pa-3 app--footer" app>
        <span
          class="caption"
        >Núcleo de Práticas em Informática &copy; {{ new Date().getFullYear() }}</span>
      </v-footer>
    </template>

    <template v-else>
      <transition>
        <keep-alive>
          <router-view :key="$route.fullpath"></router-view>
        </keep-alive>
      </transition>
    </template>
  </v-app>
</template>

<script>
import axios from "axios";
// Here we will add get a username
import { mapState } from "vuex";

export default {
  name: "App",

  data: () => ({}),
  computed: {
    ...mapState({
      username: state => state.auth.username
    })
  },

  created() {
    this.nome = this.$store.get;
  },

  methods: {
    logout() {
      //this.$router.push("/logout");
    }
  }
};
</script>

<style lang="stylus" scoped>
.page-wrapper {
  min-height: calc(100vh - 64px - 50px - 81px);
}

.content {
  background-color: #cbcedd;
}
</style>
