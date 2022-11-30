<template>
  <main id="main" class="main">
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">공지사항 관리</h1>
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
              <br />
              <h5 class="card-title">공지사항 수정 및 삭제</h5>
              <br />
              <!-- General Form Elements -->
              <div class="col-md-8">
                <div class="row mb-3">
                  <label for="inputNumber" class="col-sm-2 col-form-label">제목</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" v-model="this.title" />
                  </div>
                </div>

                <div class="row mb-3">
                  <label for="inputPassword" class="col-sm-2 col-form-label">내용</label>
                  <div class="col-sm-10">
                    <textarea
                      class="form-control"
                      style="height: 100px"
                      v-model="content"
                    ></textarea>
                  </div>
                </div>

                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                  <button class="btn btn-secondary me-md-2" type="button" @click="updateNotice">
                    수정
                  </button>
                  <button class="btn btn-secondary" type="button" @click="askDelete(noticeId)">
                    삭제
                  </button>
                </div>
              </div>
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
      title: "",
      content: "",
      noticeId: "",
    };
  },
  methods: {
    //업데이트 비동기 통신
    async updateNotice() {
      let formData = new FormData();
      formData.append("title", this.title);
      formData.append("content", this.content);

      try {
        let { data } = await http.post("/notice/" + this.noticeId, {
          title: this.title,
          content: this.content,
          boardId: this.noticeId,
        });

        console.log("UpdateModalVue: data : ");
        console.log(data);
        if (data.result == "login") {
          this.doLogout();
        } else {
          this.$alertify.success("글이 수정되었습니다.");
          this.$router.push("/notice");
        }
      } catch (error) {
        console.log("UpdateModalVue: error ");
        console.log(error);
      }
    },

    askDelete() {
      var $this = this;
      this.$alertify.confirmWithTitle(
        "삭제 확인",
        "이 글을 삭제하시겠습니까?",
        function () {
          $this.noticeDelete(); // $this 사용
        },
        function () {
          console.log("cancel");
        }
      );
    },
    async noticeDelete() {
      console.log("w");
      try {
        let { data } = await http.delete("/notice/" + this.noticeId);
        console.log(data);

        if (data.result == "login") {
          this.doLogout();
        } else {
          this.$alertify.success("글이 삭제되었습니다.");
          this.$router.push("/notice");
        }
      } catch (error) {
        console.log("BoardMainVue: error : ");
        console.log(error);
      }
    },
  },
  created() {
    this.title = this.$store.state.notice.curNotice.title;
    this.content = this.$store.state.notice.curNotice.content;
    this.noticeId = this.$store.state.notice.curNotice.boardId;
  },
};
</script>

<style></style>
