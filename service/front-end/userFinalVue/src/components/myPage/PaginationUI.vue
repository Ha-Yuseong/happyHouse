<template>
  <nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
      <li v-if="prev" class="page-item">
        <a class="page-link" href="#" aria-label="Previous" @click="paginationChanged(startPageIndex - 1)">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <li v-for="index in ( endPageIndex-startPageIndex + 1 )" :key="index"
          v-bind:class="{active: (startPageIndex + index - 1 == $store.state.favoriteList.currentPageIndex)}" class="page-item">
        <a @click="paginationChanged(startPageIndex + index - 1)" 
           class="page-link" href="#">{{ startPageIndex + index - 1 }}</a> <!-- href 는 그대로, 커서 모양 유지-->
      </li>
      <li v-if="next" class="page-item">
        <a class="page-link" href="#" aria-label="Next" @click="paginationChanged(endPageIndex + 1)">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  // props 사용 X
  data(){
    return {
    }
  },
  computed: {
    pageCount: function(){
      return this.$store.getters.getFavoritePageCount;
    },
    startPageIndex: function(){
      return this.$store.getters.getFavoriteStartPageIndex;
    },
    endPageIndex: function(){
      return this.$store.getters.getFavoriteEndPageIndex;
    },
    prev: function(){
      return this.$store.getters.getFavoritePrev;
    },
    next: function(){
      return this.$store.getters.getFavoriteNext;
    }
  },
  methods:{
    // 부모에게 event 전달    
    paginationChanged(pageIndex){
      console.log("paginationVue : paginationChanged : pageIndex : " + pageIndex );
      this.$emit('call-parent', pageIndex);
    }
  },

}
</script>