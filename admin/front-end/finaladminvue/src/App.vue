<template>
  <div id="app">
    <router-view name="NavBar"></router-view>
    <router-view name="SideBar"></router-view>
    <router-view name="MainPage"></router-view>
  </div>
</template>

<script>
import Router from "@/routers/router.js";

export default {
  data() {
    return {
      isLogin: "false",
    };
  },
  router: Router,
  mounted() {
    this.isLogin = this.$store.state.login.isLogin;
  },
};
</script>

<style></style>
