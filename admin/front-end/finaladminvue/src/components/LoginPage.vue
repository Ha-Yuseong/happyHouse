<template>
  <div>
    <main>
      <div class="container">
        <section
          class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4"
        >
          <div class="container">
            <div class="row justify-content-center">
              <div
                class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center"
              >
                <div class="d-flex justify-content-center py-4">
                  <a href="index.html" class="logo d-flex align-items-center w-auto">
                    <img src="assets/img/logo.png" alt="" />
                    <span class="d-none d-lg-block">Admin</span>
                  </a>
                </div>
                <!-- End Logo -->

                <div class="card mb-3">
                  <div class="card-body">
                    <div class="pt-4 pb-2">
                      <h5 class="card-title text-center pb-0 fs-4">Login</h5>
                    </div>

                    <div class="row g-3 needs-validation" novalidate>
                      <div class="col-12">
                        <label for="yourUsername" class="form-label">Username</label>
                        <div class="input-group has-validation">
                          <input
                            type="text"
                            name="username"
                            class="form-control"
                            v-model="this.$store.state.login.userEmail"
                            id="yourUsername"
                            required
                          />
                          <div class="invalid-feedback">Please enter your username.</div>
                        </div>
                      </div>

                      <div class="col-12">
                        <label for="yourPassword" class="form-label">Password</label>
                        <input
                          type="password"
                          autoComplete="off"
                          name="password"
                          class="form-control"
                          id="yourPassword"
                          v-model="this.$store.state.login.userPassword"
                          required
                        />
                        <div class="invalid-feedback">Please enter your password!</div>
                      </div>

                      <div class="col-12">
                        <button class="btn btn-primary w-100" @click="login">Login</button>
                      </div>
                      <div class="col-12">
                        <p class="small mb-0">
                          Don't have account? <a href="pages-register.html">Create an account</a>
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    </main>
  </div>
  <!-- End #main -->
</template>

<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

import http from "@/common/axios.js";
export default {
  methods: {
    async login() {
      let loginObj = {
        userEmail: this.$store.state.login.userEmail,
        userPassword: this.$store.state.login.userPassword,
      };
      try {
        // #1
        // let {data} = await  http.post('/login', params, options );
        // #2
        let { data } = await http.post("/login", loginObj);

        console.log("LoginVue: data : ");
        console.log(data);
        console.log(data.userName);

        // $emit 사용 X
        // login 성공 전달
        //this.$emit('call-parent-loginSuccess', {userName: data.userName, userProfileImageUrl: data.userProfileImageUrl});

        // isLogin 포함 mutator 호출
        this.$store.commit("SET_LOGIN", {
          isLogin: true,
          userName: data.userName,
          userProfileImageUrl: data.userProfileImageUrl,
        });
        // board 로 이동
        console.log("move");
        this.$router.push("/main");
      } catch (error) {
        console.log("LoginVue: error : ");
        console.log(error);
        if (error.response.status == "404") {
          this.$alertify.error("이메일 또는 비밀번호를 확인하세요.");
        } else {
          this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
        }
      }
    },
  },
};
</script>

<style></style>
