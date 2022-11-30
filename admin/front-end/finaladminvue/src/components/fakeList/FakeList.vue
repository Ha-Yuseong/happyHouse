<template>
  <div>
    <main id="main" class="main">
      <div class="pagetitle">
        <h1>허위매물 신고내역</h1>
      </div>
      <!-- End Page Title -->

      <section class="section">
        <div class="row">
          <div class="col-lg-11">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">허위매물 신고내역</h5>

                <!-- Default Table -->
                <table class="table">
                  <thead>
                    <tr>
                      <th scope="col">#</th>
                      <th scope="col">제목</th>
                      <th scope="col">작성자</th>
                      <th scope="col">작성자ID</th>
                      <th scope="col">등록일 및 등록시간</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr
                      @click="moveDetail(fake)"
                      v-for="(fake, index) in this.$store.state.fake.fakeList"
                      :key="index"
                    >
                      <th scope="row">{{ index }}</th>
                      <td>{{ fake.title }}</td>
                      <td>{{ fake.sellerId }}</td>
                      <td>{{ fake.userId }}</td>
                      <td>{{ fake.regDt }}</td>
                    </tr>
                  </tbody>
                </table>
                <!-- End Default Table Example -->
              </div>
            </div>
            <PaginationUI v-on:call-parent="movePage"></PaginationUI>
          </div>
        </div>
      </section>
    </main>
    <!-- End #main -->
  </div>
</template>

<script>
//import PaginationUI from "@/components/notice/PaginationUI.vue";
// 삭제
import Vue from "vue";
import VueAlertify from "vue-alertify";
import PaginationUI from "./PaginationUI.vue";
Vue.use(VueAlertify);
export default {
  components: { PaginationUI },
  methods: {
    moveDetail(fake) {
      this.$store.commit("SET_CURFAKE", fake);
      this.$router.push("/fakeDetail");
    },
    fakeList() {
      this.$store.dispatch("fakeList");
    },
    movePage(pageIndex) {
      console.log("BoardMainVue : movePage : pageIndex : " + pageIndex);
      this.$store.commit("SET_FAKE_MOVE_PAGE", pageIndex);

      this.fakeList();
    },
  },
  created() {
    this.fakeList();
  },
};
</script>

<style></style>
