<template>
  <div>
    <nav class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
      <div class="container">
        <button
          class="navbar-toggler collapsed"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarDefault"
          aria-controls="navbarDefault"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span></span>
          <span></span>
          <span></span>
        </button>
        <a class="navbar-brand text-brand" style="cursor: pointer" @click="toHome"
          >Estate<span class="color-b">Agency</span></a
        >

        <div class="navbar-collapse collapse justify-content-center" id="navbarDefault">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link to="/notice" class="nav-link">공지사항</router-link>
            </li>

            <li v-if="$store.state.userInfo.userCode == '001'" class="nav-item">
              <router-link to="/map" class="nav-link">지도</router-link>
            </li>

            <li v-if="$store.state.userInfo.userCode == '003'" class="nav-item">
              <router-link to="/sellerMap" class="nav-link">지도</router-link>
            </li>

            <li v-if="$store.state.userInfo.userCode == '001'" class="nav-item">
              <router-link to="/favorite" class="nav-link">관심 매물</router-link>
            </li>

            <li v-if="$store.state.userInfo.userCode == '003'" class="nav-item">
              <router-link to="/propertyManage" class="nav-link">매물 관리</router-link>
            </li>

            <li v-if="$store.state.userInfo.userCode === ''" class="nav-item">
              <router-link to="/login" class="nav-link">로그인</router-link>
            </li>

            <li v-else class="nav-item">
              <a style="cursor: pointer" class="nav-link" @click="logOut">로그아웃</a>
            </li>

            <li v-if="$store.state.userInfo.isLogin === false" class="nav-item">
              <router-link to="/register" class="nav-link">회원가입</router-link>
            </li>

            <li v-else class="nav-item">
              <router-link to="/mypage" class="nav-link">마이페이지</router-link>
            </li>

            <li class="nav-item">
              <router-link to="/ContactPage" class="nav-link">문의하기</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!-- End Header/Navbar -->
  </div>
</template>

<script>
import http from "@/common/axios.js";

import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

export default {
  methods: {
    toHome() {
      this.$router.push("/").catch(() => {});
    },
    async logOut() {
      try {
        await http.get("/logout");
        this.$store.commit("SET_LOGOUT");
        this.$router.push("/").catch(() => {});
      } catch (error) {
        console.log("LoginVue: error : ");
        console.log(error);
        this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
      }
    },
  },
};
</script>
