<template>
  <main id="main" class="main">
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">매물 등록</h1>
              <span class="color-text-a">올바르지 않은 정보가 있다면 수정해주세요!</span>
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
              <h5 class="card-title">매물 등록</h5>

              <!-- General Form Elements -->
              <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">매물 No.</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" :value="$store.state.sellerDetail.no" disabled>
                  </div>
                </div>
                                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">구/군</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" v-model="$store.state.sellerDetail.gu" disabled>
                  </div>
                </div>
                   <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">동</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" v-model="$store.state.sellerDetail.dong" disabled>
                  </div>
                </div>

                   <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">아파트 이름</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" v-model="$store.state.sellerDetail.AptName" disabled>
                  </div>
                </div>

                <div class="row mb-3">
                  <label for="inputNumber" class="col-sm-2 col-form-label">면적</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" v-model="$store.state.sellerDetail.area" />
                  </div>
                </div>

                <div class="row mb-3">
                  <label for="inputNumber" class="col-sm-2 col-form-label">층</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" v-model="$store.state.sellerDetail.floor" />
                  </div>
                </div>

                <div class="row mb-3">
                  <label for="inputNumber" class="col-sm-2 col-form-label">매매 가격</label>
                  <div class="col-sm-10">
                    <input type="number" class="form-control" v-model="$store.state.sellerDetail.dealAmount" />
                  </div>
                </div>

                <div class="row mb-3">
                  <label for="inputNumber" class="col-sm-2 col-form-label">사진 파일</label>
                  <div class="col-sm-10">
                    <input @change="changeFile" type="file" id="inputFileUploadUpdate"/>
                    <div id="imgFileUploadUpdateThumbnail" class="thumbnail-wrapper">
                     <!-- vue way img 를 만들어서 append 하지 않고, v-for 로 처리 -->
                     <img v-for="(file, index) in fileList" v-bind:src="file" v-bind:key="index" />
                  </div>
                  </div>
                </div>

                <div class="row mb-3">
                  <label for="inputPassword" class="col-sm-2 col-form-label">상세 내용</label>
                  <div class="col-sm-10">
                    <textarea class="form-control" style="height: 30vh; font-size: 15px;" v-model="$store.state.sellerDetail.houseContent"></textarea>
                  </div>
                </div>

                <div class="row mb-3" style="margin-left: 85%">
                  <div class="col-sm-12">
                    <button @click="houseRegist" class="propertyRegistBtn float-end" style="width:15vh; font-size:19px" >등록</button>
                  </div> 
                </div>
              <!-- End General Form Elements -->
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
  <!-- End #main -->
</template>

<script>

import http from "@/common/axios.js";

export default {
   name: "UpdateModal",

   data() {
      return {
         attachFile: false,
         fileList: [], // store 의 fileList 와 구분됨. 새로 첨부되는 파일을 위한.
      };
   },
   methods: {
      // modal 초기화
      initUI() {
         this.attachFile = false;
         this.fileList = [];
         document.querySelector("#inputFileUploadUpdate").value = "";
      },
      changeFile(fileEvent) {
        this.fileList = []; // thumbnail 초기화

        const fileArray = Array.from(fileEvent.target.files);
        fileArray.forEach((file) => {
          this.fileList.push(URL.createObjectURL(file)); // push : array 에 항목 추가
        });
      },
      // 굳이 actions 에 있을 필요 없다. backend async 작업이지만, 그 결과로 store 를 변경하는 내용이 없다.
      async houseRegist() {

          console.log(this.$store.state.sellerDetail.houseContent);
         // post form data
         let formData = new FormData();
         formData.append("houseId", this.$store.state.sellerDetail.no); // update 에 추가
         formData.append("area", this.$store.state.sellerDetail.area);
         formData.append("floor", this.$store.state.sellerDetail.floor);
         formData.append("dealAmount", this.$store.state.sellerDetail.dealAmount);
         formData.append("houseContent", this.$store.state.sellerDetail.houseContent);
         formData.append("userSeq", this.$store.state.userInfo.userSeq);

         let attachFiles = document.querySelector("#inputFileUploadUpdate").files;

         if (attachFiles.length > 0) {
            const fileArray = Array.from(attachFiles);
            fileArray.forEach((file) => formData.append("file", file));
         }

         let options = {
            headers: { "Content-Type": "multipart/form-data" },
         };

         // not put, REST but FileUpload
         try {

            let { data } = await http.post("/maps/123", formData, options);

            console.log("UpdateModalVue: data : ");
            console.log(data);
            if (data.result == "login") {
              console.log('dfsfs');
               this.$store.commit('SET_LOGOUT');
            } else {
               this.$router.push("/propertyManage");
            }
         } catch (error) {
            console.log("UpdateModalVue: error ");
            console.log(error);
         }
      },
   },
};
</script>

<style>
.propertyRegistBtn {
  background: #2eca6a;
  border-color: #2eca6a;
  color: #fff;
  text-align: center;
  font-size: 10px;
  border: 1px solid #2eca6a;
  border-radius: 8px;
  width: 100%;
  height: 35px;
  cursor: pointer;
}
</style>
