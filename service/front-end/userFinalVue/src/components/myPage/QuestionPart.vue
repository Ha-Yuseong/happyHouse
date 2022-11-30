<template>
  <div class="col-md-6">
      <div class="card mb-4 mb-md-0">
        <div class="card-body">
          <p class="mb-4"><span class="text-primary font-italic me-1">최근</span> 문의 내용 <span>(6)</span></p>
          <div class="AccountList mt-3 mb-3" v-for="i in 6" :key="i">
            준비 중인 요소입니다.
          </div>
          <PaginationUI></PaginationUI>
        </div>
      </div>
    </div>
</template>

<script>
import PaginationUI from "./PaginationUI.vue";

export default {
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

      boardDetail(value){
      this.$store.state.noticeDetail = value;
      this.$router.push('/noticeDetail').catch(()=>{});
      },

   },
   created() {
      this.boardList();
   },
};
</script>

<style></style>
