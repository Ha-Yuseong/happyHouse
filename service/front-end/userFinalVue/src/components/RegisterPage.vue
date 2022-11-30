<template>
  <div class="login-form">
    <h2 class="text-uppercase text-center">Create an account</h2>
    <div class="form-group">
      <input type="email" name="email" placeholder="E-mail Address" v-model="userEmail" />
      <span class="input-icon"><i class="fa fa-envelope"></i></span>
    </div>
    <div class="form-group mb-3">
      <input type="text" name="userName" placeholder="Your Name" v-model="userName" />
      <span class="input-icon"><i class="fa fa-user" aria-hidden="true"></i></span>
    </div>
    <div class="form-group">
      <input type="password" name="psw" placeholder="Password" v-model="userPassword" />
      <span class="input-icon"><i class="fa fa-lock"></i></span>
    </div>
    <div class="form-group">
      <input type="password" name="psw" placeholder="Password Confirm" v-model="userPassword2" />
      <span class="input-icon"><i class="fa fa-lock"></i></span>
    </div>
    <button class="login-btn" @click="userRegist">Create an Account</button>
  </div>
</template>

<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);
import http from "@/common/axios.js";
export default {
  // data 사용 X
  data() {
    return {
      // v-model., from router
      userName: this.$route.params.userName,
      userEmail: this.$route.params.userEmail,
      userPassword: "",
      userPassword2: "",
    };
  },
  methods: {
    async userRegist() {
      try {
        let { data } = await http.post("/register", {
          userName: this.userName,
          userEmail: this.userEmail,
          userPassword: this.userPassword,
        });

        console.log("register: data : ");
        console.log(data.result);

        if (data.result == "login") {
          this.doLogout();
        } else {
          this.$alertify.success("회원가입이 완료되었습니다.");
          console.log("dd");
          this.$router.push("/");
        }
      } catch (error) {
        console.log("InsertModalVue: error ");
        console.log(error);
      }
    },
  },
  async created() {},
};
</script>

<style scoped></style>
