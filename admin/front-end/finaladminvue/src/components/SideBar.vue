<template>
  <div>
    <header id="header" class="header fixed-top d-flex align-items-center">
      <div class="d-flex align-items-center justify-content-between">
        <div class="logo d-flex align-items-center">
          <img @click="moveIndex" src="assets/img/logo.png" alt="" />
          <span class="d-none d-lg-block" @click="moveIndex">Admin</span>
        </div>
      </div>
      <!-- End Logo -->

      <nav class="header-nav ms-auto">
        <ul class="d-flex align-items-center">
          <li class="nav-item d-block d-lg-none">
            <a class="nav-link nav-icon search-bar-toggle" href="#">
              <i class="bi bi-search"></i>
            </a>
          </li>
          <!-- End Search Icon-->

          <li class="nav-item dropdown pe-3">
            <a
              class="nav-link nav-profile d-flex align-items-center pe-0"
              href="#"
              data-bs-toggle="dropdown"
            >
              <img src="assets/img/profile-img.jpg" alt="Profile" class="rounded-circle" />
              <span class="d-none d-md-block dropdown-toggle ps-2">K. Anderson</span> </a
            ><!-- End Profile Iamge Icon -->

            <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
              <li class="dropdown-header">
                <h6>Kevin Anderson</h6>
                <span>Web Designer</span>
              </li>
              <li>
                <hr class="dropdown-divider" />
              </li>

              <li>
                <a class="dropdown-item d-flex align-items-center" href="users-profile.html">
                  <i class="bi bi-person"></i>
                  <span>My Profile</span>
                </a>
              </li>
              <li>
                <hr class="dropdown-divider" />
              </li>

              <li>
                <a class="dropdown-item d-flex align-items-center" href="users-profile.html">
                  <i class="bi bi-gear"></i>
                  <span>Account Settings</span>
                </a>
              </li>
              <li>
                <hr class="dropdown-divider" />
              </li>

              <li>
                <a class="dropdown-item d-flex align-items-center" href="pages-faq.html">
                  <i class="bi bi-question-circle"></i>
                  <span>Need Help?</span>
                </a>
              </li>
              <li>
                <hr class="dropdown-divider" />
              </li>

              <li>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <i class="bi bi-box-arrow-right"></i>
                  <span>Sign Out</span>
                </a>
              </li>
            </ul>
            <!-- End Profile Dropdown Items -->
          </li>
          <!-- End Profile Nav -->
        </ul>
      </nav>
      <!-- End Icons Navigation -->
    </header>
    <!-- End Header -->

    <!-- ======= Sidebar ======= -->
    <aside id="sidebar" class="sidebar">
      <ul class="sidebar-nav" id="sidebar-nav">
        <li class="nav-item">
          <div class="nav-link collapsed" @click="moveNoticeList">
            <i class="bi bi-card-list"></i>
            <span>공지사항 관리</span>
          </div>
        </li>
        <li class="nav-item">
          <div class="nav-link collapsed" @click="moveFakeList">
            <i class="bi bi-card-list"></i>
            <span>신고내역 관리</span>
          </div>
        </li>
        <!-- <li class="nav-item">
          <div class="nav-link collapsed" @click="moveLogin">
            <i class="bi bi-card-list"></i>
            <span>로그인 창</span>
          </div>
        </li> -->
        <li class="nav-item">
          <div class="nav-link collapsed" @click="logOut">
            <i class="bi bi-card-list"></i>
            <span>로그아웃</span>
          </div>
        </li>
      </ul>
    </aside>
    <!-- End Sidebar-->
  </div>
</template>

<script>
import http from "@/common/axios.js";

export default {
  methods: {
    moveNoticeList() {
      this.$router.push("/notice");
    },
    moveFakeList() {
      this.$router.push("/fakelist");
    },
    moveIndex() {
      this.$router.push("/main");
    },
    moveLogin() {
      this.$router.push("/login");
    },
    async logOut() {
      try {
        console.log("ss");
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

<style></style>
