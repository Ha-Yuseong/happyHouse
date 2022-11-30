<template>
  <div class="container">
    <!-- ======= Intro Single ======= -->
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">매물 관리</h1>
              <span class="color-text-a">내 매물을 관리해보세요!</span>
            </div>
          </div>
        </div>
      </div>
    </section>
    <table class="table table-hover">
      <thead>
        <tr>
          <th class="text-center">매물 번호</th>
          <th class="text-center">동</th>
          <th class="text-center">아파트 이름</th>
          <th class="text-center">가격</th>
        </tr>
      </thead>
      <tbody>
        <tr
          style="cursor: pointer"
          v-for="(deal, index) in $store.state.PropertyManage.list"
          @click="dealDetail(deal)"
          :key="index"
        >
          <td class="text-center">{{ deal.no }}</td>
          <td class="text-center">{{ deal.dong }}</td>
          <td class="text-center">{{ deal.AptName }}</td>
          <td class="text-center">{{ deal.dealAmount }}</td>
        </tr>
      </tbody>
    </table>
    <PaginationUI v-on:call-parent="movePage"></PaginationUI>
  </div>
</template>

<script>
import PaginationUI from "./PaginationUI.vue";

// 삭제
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

export default {
  components: { PaginationUI },
  methods: {
    boardList() {
         this.$store.dispatch("sellingHouseList",'');
      },

    dealDetail(deal) {
      this.$store.state.sellerDetail = deal;
      this.$router.push("/PropertyUpdate");
    },

    // pagination
    movePage(pageIndex) {
       console.log("BoardMainVue : movePage : pageIndex : " + pageIndex);
       this.$store.commit("SET_MYPROPERTY_MOVE_PAGE", pageIndex);

       this.boardList();
    },
  },
  created() {
      this.boardList();
   },
};
</script>

<style>
.propertyRegistBtn {
  background: #2eca6a;
  border-color: #2eca6a;
  color: #fff;
  text-align: center;
  font-size: 5px;
  border: 1px solid #2eca6a;
  border-radius: 8px;
  width: 100%;
  height: 35px;
  cursor: pointer;
}
</style>
