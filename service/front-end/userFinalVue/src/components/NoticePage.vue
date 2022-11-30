<template>
   <div class="container">
      
      <!-- ======= Intro Single ======= -->
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">공지사항</h1>
              <span class="color-text-a">새 공지를 확인해보세요!</span>
            </div>
          </div>
        </div>
      </div>
    </section>

      <div class="input-group mb-3">
          <input v-model="$store.state.notice.searchWord" @keydown.enter="boardList" type="text" class="form-control" />
          <button @click="boardList" type="button" class="btn btn-b-n navbar-toggle-box navbar-toggle-box-collapse">
          <i class="bi bi-search"></i>
        </button>
      </div>

      <table class="table table-hover">
         <thead>
            <tr>
               <th>글 번호</th>
               <th class="text-center">제목</th>
               <th class="text-center">작성일시</th>
               <th class="text-center">조회수</th>
            </tr>
         </thead>
         <tbody>
            <tr style="cursor: pointer" v-for="(board, index) in listGetters" @click="boardDetail(board)" v-bind:key="index">
               <td>{{ board.boardId }}</td>
               <td class="text-center">{{ board.title }}</td>
               <td class="text-center">{{ board.regDt.date | makeDateStr(".") }}</td>
               <td class="text-center">{{ board.readCount }}</td>
            </tr>
         </tbody>
      </table>

      <PaginationUI v-on:call-parent="movePage"></PaginationUI>

   </div>
</template>

<script>
import util from "@/common/util.js";

import PaginationUI from "./notice/PaginationUI.vue";

// 삭제
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

export default {
   name: "noticePage",
   components: { PaginationUI },
   computed: {
      // gttters 이용
      listGetters() {
         return this.$store.getters.getNoticeList; // no getBoardList()
      },
   },
   methods: {
      boardList() {
         this.$store.dispatch("noticeList");
      },

      // pagination
      movePage(pageIndex) {
         console.log("BoardMainVue : movePage : pageIndex : " + pageIndex);
         this.$store.commit("SET_NOTICE_MOVE_PAGE", pageIndex);

         this.boardList();
      },
      
      makeDateStr: util.makeDateStr,

      boardDetail(value){
      this.$store.state.noticeDetail = value;
      this.$router.push('/noticeDetail').catch(()=>{});
      },

   },
   created() {
      this.boardList();
   },
   filters: {
      makeDateStr: function (date, separator) {
         return date.year + separator + (date.month < 10 ? "0" + date.month : date.month) + separator + (date.day < 10 ? "0" + date.day : date.day);
      },
   },
};
</script>

<style scoped></style>
