<template>
  <div id="app">
    <router-view name="NavBar"></router-view>
    <router-view name="MainPage"></router-view>
    <router-view name="FooterPage"></router-view>
  </div>
</template>

<script>
import Router from "@/routers/router.js";

export default {
  router: Router,
  mounted() {
    let recaptchaScript = document.createElement("script");
    recaptchaScript.setAttribute("src", "assets/vendor/bootstrap/js/bootstrap.bundle.min.js");
    recaptchaScript.setAttribute("src", "assets/vendor/swiper/swiper-bundle.min.js");
    recaptchaScript.setAttribute("src", "assets/vendor/php-email-form/validate.js");
    recaptchaScript.setAttribute("src", "assets/js/main.js");
    document.head.appendChild(recaptchaScript);
  },
};
</script>

<style></style>
