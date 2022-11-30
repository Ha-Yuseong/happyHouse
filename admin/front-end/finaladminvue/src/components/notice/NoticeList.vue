<template>
  <div>
    <main id="main" class="main">
      <div class="pagetitle">
        <h1>공지사항</h1>
      </div>
      <!-- End Page Title -->

      <section class="section">
        <div class="row">
          <div class="col-lg-11">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">공지사항</h5>

                <!-- Default Table -->
                <table class="table">
                  <thead>
                    <tr>
                      <th scope="col">#</th>
                      <th scope="col">제목</th>
                      <th scope="col">작성자</th>
                      <th scope="col">등록일</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr
                      @click="moveDetail(notice)"
                      v-for="(notice, index) in this.$store.state.notice.noticeList"
                      :key="index"
                    >
                      <th scope="row">{{ index + 1 }}</th>
                      <td>{{ notice.title }}</td>
                      <td>{{ notice.userName }}</td>
                      <td>
                        {{ notice.regDt.date.year }}.{{ notice.regDt.date.month }}.{{
                          notice.regDt.date.day
                        }}
                      </td>
                    </tr>
                  </tbody>
                </table>
                <!-- End Default Table Example -->
              </div>
            </div>
            <PaginationUI v-on:call-parent="movePage"></PaginationUI>
          </div>
        </div>
        <button class="btn btn-secondary" @click="moveRegist">등록하기</button>
      </section>
    </main>
    <!-- End #main -->
  </div>
</template>

<script>
// 삭제
import Vue from "vue";
import VueAlertify from "vue-alertify";
import PaginationUI from "./PaginationUI.vue";
Vue.use(VueAlertify);
export default {
  components: { PaginationUI },
  methods: {
    moveDetail(notice) {
      this.$store.commit("SET_CURNOTICE", notice);
      this.$router.push("/noticeDetail");
    },
    moveRegist() {
      this.$router.push("/noticeRegist");
    },
    noticeList() {
      this.$store.dispatch("noticeList");
    },
    movePage(pageIndex) {
      console.log("BoardMainVue : movePage : pageIndex : " + pageIndex);
      this.$store.commit("SET_NOTICE_MOVE_PAGE", pageIndex);

      this.noticeList();
    },
  },
  created() {
    this.noticeList();
  },
};
</script>

<style></style>
