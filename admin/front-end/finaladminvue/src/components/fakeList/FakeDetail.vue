<template>
  <main id="main" class="main">
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">허위매물 상세보기</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- End Page Title -->

    <section class="section">
      <div class="container">
        <div class="col-md-11" style="float: none; margin: 0 auto">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">허위매물 상세보기</h5>

              <!-- General Form Elements -->
              <form>
                <div class="row mb-3">
                  <label for="inputNumber" class="col-sm-2 col-form-label">제목</label>
                  <div class="col-sm-10">
                    <input
                      disabled
                      type="text"
                      class="form-control"
                      v-model="$store.state.fake.curFake.title"
                    />
                  </div>
                </div>

                <div class="row mb-3">
                  <label for="inputNumber" class="col-sm-2 col-form-label">매물 번호</label>
                  <div class="col-sm-10">
                    <input
                      disabled
                      type="text"
                      class="form-control"
                      v-model="$store.state.fake.curFake.dealId"
                    />
                  </div>
                </div>

                <div class="row mb-3">
                  <label for="inputNumber" class="col-sm-2 col-form-label">판매자 ID</label>
                  <div class="col-sm-10">
                    <input
                      disabled
                      type="text"
                      class="form-control"
                      v-model="$store.state.fake.curFake.sellerId"
                    />
                  </div>
                </div>

                <div class="row mb-3">
                  <label for="inputNumber" class="col-sm-2 col-form-label">작성자 ID</label>
                  <div class="col-sm-10">
                    <input
                      disabled
                      type="text"
                      class="form-control"
                      v-model="$store.state.fake.curFake.userId"
                    />
                  </div>
                </div>

                <div class="row mb-3">
                  <label for="inputPassword" class="col-sm-2 col-form-label">내용</label>
                  <div class="col-sm-10">
                    <textarea
                      disabled
                      class="form-control"
                      style="height: 100px"
                      v-model="$store.state.fake.curFake.content"
                    ></textarea>
                  </div>
                </div>

                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                  <button class="btn btn-secondary me-md-2" type="button" @click="accept">
                    승인
                  </button>
                </div>
              </form>
              <!-- End General Form Elements -->
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
import http from "@/common/axios.js";
export default {
  data() {
    return {
      isDone: "",
      fakeId: "",
    };
  },
  methods: {
    accept() {
      this.done();
    },
    async done() {
      try {
        let { data } = await http.post("/fake/" + this.fakeId);

        console.log("fake: data : ");
        console.log(data);
        if (data.result == "login") {
          this.doLogout();
        } else {
          this.$alertify.success("글이 수정되었습니다.");
          this.$router.push("/fakeList");
        }
      } catch (error) {
        console.log("fake: error ");
        console.log(error);
      }
    },
  },
  created() {
    this.isDone = this.$store.state.fake.curFake.isDone;
    this.fakeId = this.$store.state.fake.curFake.fakeId;
  },
};
</script>

<style></style>
