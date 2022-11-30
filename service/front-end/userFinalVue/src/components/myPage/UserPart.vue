<template>
  <div class="row">
    <div class="col-lg-4">
      <div class="card mb-4">
        <div class="card-body text-center">
          <img
            src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp"
            alt="Image"
            class="rounded-circle img-fluid"
            style="width: 150px"
          />
          <h5 class="my-3">{{ $store.state.userInfo.userName }}</h5>
          <p v-if="$store.state.userInfo.userCode === '001'" class="text-muted mb-1">일반 사용자</p>
          <p v-else class="text-muted mb-1">공인중개사</p>
          <p class="text-muted mb-4">{{ $store.state.userInfo.userEmail }}</p>
          <div class="d-flex justify-content-center mb-2">
            <button type="button" class="btn btn-primary" @click="modifyMode">수정모드</button>
            <button type="button" class="btn btn-outline-primary ms-1" @click="userUpdate">
              수정하기
            </button>
          </div>
        </div>
      </div>
    </div>
    <div class="col-lg-8">
      <div class="card mb-4">
        <div class="card-body">
          <div class="row">
            <div class="col-sm-3">
              <p class="mb-0">Full Name</p>
            </div>
            <div v-if="modify == false" class="col-sm-9">
              <p class="text-muted mb-0">{{ $store.state.userInfo.userName }}</p>
            </div>
            <div v-else class="col-sm-4">
              <input type="text" class="modifyInput" v-model="userName" autocomplete="off" />
            </div>
            <!-- <div v-else class="col-sm-9">
                  <input v-bind:value="$store.state.userInfo.userName" class="text-muted mb-0" style="board:none"/>
                </div> -->
          </div>
          <hr />
          <div class="row">
            <div class="col-sm-3">
              <p class="mb-0">Email</p>
            </div>
            <div v-if="modify == false" class="col-sm-9">
              <p class="text-muted mb-0">{{ $store.state.userInfo.userEmail }}</p>
            </div>
            <div v-else class="col-sm-4">
              <input type="email" class="modifyInput" v-bind:="userEmail" autocomplete="off" />
            </div>
          </div>
          <hr />
          <div class="row">
            <div class="col-sm-3">
              <p class="mb-0">Phone</p>
            </div>
            <div v-if="modify == false" class="col-sm-9">
              <p class="text-muted mb-0">{{ $store.state.userInfo.userPhone }}</p>
            </div>
            <div v-else class="col-sm-9">
              <input
                type="text"
                class="modifyInput"
                :value="$store.state.userInfo.userPhone"
                autocomplete="off"
              />
            </div>
          </div>
          <hr />
          <div class="row">
            <div class="col-sm-3">
              <p class="mb-0">Mobile</p>
            </div>
            <div v-if="modify == false" class="col-sm-9">
              <p class="text-muted mb-0">{{ $store.state.userInfo.userMobile }}</p>
            </div>
            <div v-else class="col-sm-9">
              <input
                type="text"
                class="modifyInput"
                :value="$store.state.userInfo.userMobile"
                autocomplete="off"
              />
            </div>
          </div>
          <hr />
          <div class="row">
            <div class="col-sm-3">
              <p class="mb-0">Address</p>
            </div>
            <div v-if="modify == false" class="col-sm-9">
              <p class="text-muted mb-0">{{ $store.state.userInfo.userAddress }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/common/axios.js";
export default {
  data() {
    return {
      modify: false,
      userName: "",
      userEmail: "",
      userSeq: "",
      userPassword: "",
    };
  },
  computed: {
    requireImg: function () {
      if (this.$store.state.userInfo.userProfileImageUrl == "") {
        return require("../../assets/noProfile.png");
      } else {
        return require("../assets" + this.$store.state.login.userProfileImageUrl);
      }
    },
  },
  methods: {
    modifyMode() {
      this.modify = true;
    },
    async userUpdate() {
      let userName = this.userName;
      let userEmail = this.userEmail;
      let { data } = await http.put("/users", {
        userName: userName,
        userEmail: userEmail,
        userSeq: this.$store.state.userInfo.userSeq,
        userPassword: this.$store.state.userInfo.userPassword,
      });

      console.log(data);

      if (data.result == "login") {
        this.doLogout();
      } else {
        this.$alertify.success("회원정보가 수정되었습니다.");
        this.$router.push("/");
      }
    },
    catch(error) {
      console.log("update user");
      console.log(error);
    },
  },
  created() {
    this.userName = this.$store.state.userInfo.userName;
    this.userEmail = this.$store.state.userInfo.userEmail;
    this.userPassword = this.$store.state.userInfo.userPassword;
    this.userSeq = this.$store.state.userInfo.userSeq;
  },
};
</script>

<style>
.modifyInput {
  height: 22px;
  color: rgb(92, 92, 92);
  width: 26vh;
  border-bottom: 1px solid #ccc;
  border-top: none;
  border-left: none;
  border-right: none;
}

.modifyInput:focus {
  outline: none;
}
</style>
