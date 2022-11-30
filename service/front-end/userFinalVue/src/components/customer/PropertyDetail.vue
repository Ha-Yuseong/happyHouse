<template>
  <div>
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">부동산 상세</h1>
              <span class="color-text-a">{{$store.state.houseDetail.list.AptName}}</span>
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
            </nav>
          </div>
        </div>
      </div>
    </section>
    <PropertyContent></PropertyContent>
     
  </div>
</template>

<script>

import PropertyContent from "./PropertyContent.vue";

export default {
  components: { PropertyContent },

}
</script>

<style>

</style>