<template>
  <section style="background-color: white; margin-top: 70px; margin-bottom: 0px">
    <div class="container py-5">
      <div class="row">
        <div class="col">
          <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
            <ol class="breadcrumb mb-0">
              <li class="breadcrumb-item">HappyHouse</li>
              <li class="breadcrumb-item active" aria-current="page">My Account</li>
            </ol>
          </nav>
        </div>
        <UserPart></UserPart>
        <Favorite-Part></Favorite-Part>
        <QuestionPart></QuestionPart>
      </div>
    </div>
  </section>
</template>

<script>
import FavoritePart from "@/components/myPage/FavoritePart.vue";
import QuestionPart from "@/components/myPage/QuestionPart.vue";
import UserPart from "@/components/myPage/UserPart.vue";

export default {
  components: { FavoritePart, QuestionPart, UserPart },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
