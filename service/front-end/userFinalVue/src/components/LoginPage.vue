<template>
  <div class="login-form">
    <h1>Login</h1>
    <div class="form-group">
      <input
        type="email"
        name="email"
        v-model="$store.state.userInfo.userEmail"
        value="hong@hong.com"
        placeholder="E-mail Address"
      />
      <span class="input-icon"><i class="fa fa-envelope"></i></span>
    </div>
    <div class="form-group">
      <input
        type="password"
        name="psw"
        v-model="$store.state.userInfo.userPassword"
        value="1234"
        placeholder="Password"
      />
      <span class="input-icon"><i class="fa fa-lock"></i></span>
    </div>
    <button class="login-btn" @click="login">Login</button>
    <a class="reset-psw" href="#">Forgot your password?</a>
    <div class="seperator"><b>or</b></div>
    <p>Sign in with your social media account</p>
    <!-- Social login buttons -->
    <div class="social-icon">
      <img
        src="@/assets/kakao.png"
        style="
          width: 50px;
          height: 50px;
          margin-top: 0px;
          margin-right: 10px;
          margin-bottom: 0px;
          margin-left: 10px;
          cursor: pointer;
        "
        @click="loginWithKakao"
      />
      <button type="button"><i class="fa fa-twitter"></i></button>
      <button type="button"><i class="fa fa-facebook"></i></button>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

import http from "@/common/axios.js";

export default {
  methods: {
    async login() {
      console.log(this.$store.state.userInfo.userEmail);
      //    // #2 JSON Way : application/json
      let loginObj = {
        userEmail: this.$store.state.userInfo.userEmail,
        userPassword: this.$store.state.userInfo.userPassword,
      };

      try {
        // #2
        let { data } = await http.post("/login", loginObj);

        console.log("LoginVue: data : ");
        console.log(data);

        // isLogin 포함 mutator 호출
        this.$store.commit("SET_LOGIN", data);
        // board 로 이동
        this.$router.push("/").catch(() => {});
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
    loginWithKakao() {
      var $this = this;
      // /* global Kakao / 는 한번만 선언해 줘야 한다. 아래 코드는 already defined 오류 발생
      Kakao.Auth.login({
        success: function (authObj) {
          console.log("----1");
          console.log(authObj);

          // 인증이 성공하면 개인 프로파일 정보를 얻어온다. 단, 백엔드에서 /v2/user/me 로 요청
          // 백엔드에 access_token 을 전달하고 user info 를 받는다.
          $this.getUserProfile(authObj.access_token);
        },
        fail: function (error) {
          console.error(error);
        },
      });
    },
    async getUserProfile(accessToken) {
      console.log(accessToken);

      // 주의! BackEnd 가 아래와 같은 경우, urlencoded 방식으로 처리해야 함. axios 기본은 json
      // @PostMapping(value="/kakaologin")
      // public HashMap<String, String> kakaoLogin(String accessToken) {

      // axios 기본 : application/json
      let options = {
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
      };

      // urlencoded 방식으로 param 보낼 때는 아래와 같이
      const params = new URLSearchParams();
      params.append("accessToken", accessToken);

      try {
        // 전체를 보려면
        // let {response} = await http.post("/kakaologin", params, options);
        // console.log(response);

        let { data } = await http.post("/kakaoLogin", params, options);
        console.log(data);

        if (data.result == "registered") {
          this.$store.commit(
            "SET_LOGIN",
            {
              isLogin: true,
              userName: data.nickname,
              userProfileImageUrl: data.thumbnail_image,
              userEmail: data.userEmail,
            },

            this.$router.push("/")
          );
        } else {
          // 가입하지 않은 회원
          alert("kakao 인증되었으나, 회원 가입이 필요합니다.");
          let params = {
            userName: data.nickname,
            userEmail: data.email,
          };
          this.$router.push({ name: "RegisterPage", params }); // params : params
        }
      } catch (error) {
        console.log(error);
      }
    },
  },
  mounted() {
    // 주의 if( Kakao == undefined ) 는 오류 발생 typeof 필요
    // typeof Kakao 는 string "undefined" 를 return
    // if (typeof Kakao !== undefined) return; 은 문법 오류는 아니나, Kakao 가 is not defuned 발생
    /* global Kakao */ if (typeof Kakao !== "undefined") return;

    const script = document.createElement("script");

    script.onload = () => Kakao.init(process.env.VUE_APP_KAKAO_JAVASCRIPT_KEY);
    script.src = "https://developers.kakao.com/sdk/js/kakao.js";
    document.head.appendChild(script);
  },
  computed: {},
  watch: {},
};
</script>

<style scoped></style>
