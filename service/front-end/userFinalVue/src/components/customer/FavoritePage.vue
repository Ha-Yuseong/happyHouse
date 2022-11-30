<template>
  <div>
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">관심 매물</h1>
              <span class="color-text-a">내가 찜한 매물</span>
            </div>
          </div>
        </div>
      </div>
    </section><!-- End Intro Single-->

    <!-- =======  Blog Grid ======= -->
    <section class="news-grid grid">
      <div class="container">
        <div class="row">
          <div class="col-md-4" v-for="(apart, index) in listGetters" :key="index">
            <div class="card-box-b card-shadow news-box">
              <div class="img-box-b">
                <img :src="'assets/img/post-'+ ((index%6) +1) +'.jpg'" alt="" class="img-b img-fluid" style="height: 416px; width: 550px">
              </div>
              <div class="card-overlay">
                <div class="card-header-b">
                  <div class="card-category-b">
                    <a class="category-b">서울</a>
                  </div>
                  <div class="card-title-b">
                    <h2 class="title-2">
                      <a @click="moveDetail(apart)" style="color:white; cursor:pointer;"><span style="font-size:22px; font-weight:600">{{apart.AptName}}</span>
                        <br><span style="font-size:15px">서울특별시 {{apart.gu}} {{apart.dong}} {{apart.jibun}} {{apart.floor}}층</span></a>
                    </h2>
                  </div>
                  <div class="card-date">
                    <span class="date-b">{{apart.dealAmount}}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        
        <PaginationUI v-on:call-parent="movePage"></PaginationUI>
      </div>
    </section><!-- End Blog Grid-->
  </div>
</template>

<script>
import PaginationUI from "./PaginationUI.vue";

export default {
  components: { PaginationUI },

  computed: {
      // gttters 이용
      listGetters() {
         return this.$store.getters.getFavoriteList; // no getBoardList()
      },
   },

  methods: {
      async favoriteList() {
         this.$store.dispatch("favoriteList");
      },

      // pagination
      movePage(pageIndex) {
         console.log("BoardMainVue : movePage : pageIndex : " + pageIndex);
         this.$store.commit("SET_FAVORITE_MOVE_PAGE", pageIndex);

         this.favoriteList();
      },

      moveDetail(aptDetail){
        this.$store.state.aptDetail = aptDetail;
        this.$router.push("/propertyDetail");
      }

   },
   created() {
      this.favoriteList();
   },
}
</script>

<style>

</style>
