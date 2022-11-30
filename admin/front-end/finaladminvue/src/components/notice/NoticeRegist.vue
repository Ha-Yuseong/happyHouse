<template>
  <main id="main" class="main">
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">공지사항 등록</h1>
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
              <h5 class="card-title">공지사항 등록</h5>

              <!-- General Form Elements -->
              <div>
                <div class="row mb-3">
                  <label for="inputNumber" class="col-sm-2 col-form-label">제목</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" v-model="title" />
                  </div>
                </div>

                <div class="row mb-3">
                  <label for="inputPassword" class="col-sm-2 col-form-label">내용</label>
                  <div class="col-sm-10">
                    <textarea class="form-control" style="height: 100px" v-model="content"></textarea>
                  </div>
                </div>

                <div class="row mb-3" style="margin-left: 90%">
                  <div class="col-sm-10">
                    <button class="btn btn-secondary" @click="noticeInsert">등록</button>
                  </div>
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
import Vue from "vue";
import VueAlertify from "vue-alertify";

Vue.use(VueAlertify);

import http from "@/common/axios.js";

export default {
  data() {
    return {
      title: "",
      content: "",
    };
  },
  methods: {
    async noticeInsert() {
      let formData = new FormData();
      formData.append("title", this.title);
      formData.append("content", this.content);

      try {
        let { data } = await http.post("/notice", formData);

        console.log("insert: data : ");
        console.log(data.result);
        if (data.result == "login") {
          this.doLogout();
        } else {
          this.$alertify.success("글이 등록되었습니다.");
          console.log("dd");
          this.$router.push("/notice");
        }
      } catch (error) {
        console.log("InsertModalVue: error ");
        console.log(error);
      }
    },
  },
};
</script>

<style></style>
