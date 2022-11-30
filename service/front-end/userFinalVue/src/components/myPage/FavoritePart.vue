<template>
  <div class="col-md-6">
      <div class="card mb-4 mb-md-0">
        <div class="card-body">
          <p class="mb-4"><span class="text-primary font-italic me-1">나의</span>관심 목록 <span>(12)</span></p>
          <div class="AccountList mt-3 mb-3" v-for="(list, index) in listGetters" v-bind:key="index">
            {{list.AptName}}
          </div>
          <PaginationUI v-on:call-parent="movePage"></PaginationUI>
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

      boardDetail(value){
      this.$store.state.noticeDetail = value;
      this.$router.push('/noticeDetail').catch(()=>{});
      },

   },
   created() {
      this.favoriteList();
   },
};
</script>

<style></style>
