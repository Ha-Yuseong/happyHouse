<template>
  <div>
    <div class="intro position-relative">
      <div class="carousel-item-a intro-item bg-image" style="background-image: url(assets/img/slide-2.jpg)">
          <div class="overlay overlay-a"></div>
          <div class="intro-content display-table">
            <div class="table-cell">
              <div class="container">
                <div class="row">
                  <div class="col-lg-8">
                    <div class="intro-body">
                      <h1 class="intro-title mb-4">
                        <span class="color-b">나의 집을 어디에? </span> <br />
                        우리 같이 찾아볼까요?
                      </h1>
                    </div>
                  </div>
                </div>
                <div class="searchDiv">
                  <a type="text" class="searchBar" @click="toMap">찾으러 가봐요!</a>
                </div>
              </div>
            </div>
          </div>
        </div>
    </div>
    <!-- End Intro Section -->

    <main id="main">
      <SeviceIntro></SeviceIntro>

      <LatestProperty></LatestProperty>

      <LatestNews></LatestNews>
    </main>
    <!-- End #main -->
  </div>
</template>

<script>

import SeviceIntro from '@/components/mainPage/SeviceIntro.vue'
import LatestNews from '@/components/mainPage/LatestNews.vue'
import LatestProperty from '@/components/mainPage/LatestProperty.vue'

export default {
  components: {SeviceIntro, LatestNews, LatestProperty},
  methods:{
    toMap(){
      
      if(this.$store.state.userInfo.userCode==='003') this.$router.push("/sellerMap");
      else this.$router.push("/map");
    }
  }
}
</script>

<style scoped>

</style>

