<template>
  <div id="all" :class="{ showAll: this.isChangeAll, hideAll: !this.isChangeAll }">
    <!-- Sidebar.vue -->
    <div class="side">
      <div class="side-top mt-5">
        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
          <li class="nav-item" role="presentation" style="width: 1px">
            <button
              class="nav-link active"
              id="pills-default-tab"
              data-bs-toggle="pill"
              data-bs-target="#pills-default"
              type="button"
              role="tab"
              aria-controls="pills-default"
              aria-selected="true"
              style="display: none"
            ></button>
          </li>
          <li class="nav-item" role="presentation">
            <button
              class="nav-link"
              id="pills-aptname-tab"
              data-bs-toggle="pill"
              data-bs-target="#pills-aptname"
              type="button"
              role="tab"
              aria-controls="pills-aptname"
              aria-selected="false"
              style="background-color: rgba(29, 181, 135, 0.592); color: white; margin-right: 5px"
              @click="displayNone"
            >
              <strong>아파트별 조회</strong>
            </button>
          </li>
          <li class="nav-item" role="presentation">
            <button
              class="nav-link"
              id="pills-dong-tab"
              data-bs-toggle="pill"
              data-bs-target="#pills-dong"
              type="button"
              role="tab"
              aria-controls="pills-dong"
              aria-selected="false"
              style="background-color: rgba(29, 181, 135, 0.592); color: white"
              @click="displayNone"
            >
              <strong>동별 조회</strong>
            </button>
          </li>
        </ul>
        <div class="search-tabs">
          <div class="tab-content" id="pills-tabContent">
            <div
              class="tab-pane fade show active"
              v-show="show"
              role="tabpanel"
              aria-labelledby="pills-default-tab"
              style="padding: 10px"
            >
              <strong>아파트 이름, 동 이름으로 검색하세요.</strong>
            </div>
            <div
              class="tab-pane fade"
              id="pills-aptname"
              role="tabpanel"
              aria-labelledby="pills-aptname-tab"
              @click:="displayNone"
            >
              <div class="search-title">아파트별 조회</div>
              <form class="search-tab">
                <div class="mb-3">
                  <label for="exampleInputEmail1" class="form-label"
                    >아파트명을 입력해주세요.</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="inputSearchWord"
                    aria-describedby="emailHelp"
                    v-model="searchWord"
                  />
                  <div id="emailHelp" class="form-text"></div>
                </div>
              </form>
              <button id="aptNameSearchBtn" class="search" type="button">검색</button>
            </div>
            <div
              class="tab-pane fade"
              id="pills-dong"
              role="tabpanel"
              aria-labelledby="pills-dong-tab"
            >
              <div class="search-title">동별 조회</div>
              <form class="row g-3">
                <div class="col-md-4">
                  <select class="form-select" v-model="sido">
                    <!-- @change="getGugun" required> -->
                    <option value="" for="sidoSelect" class="form-label" style="font-size: 14px">
                      시/도
                    </option>
                    <option v-for="(sido, index) in sidoList" :key="index" :value="sido.sidoCode">
                      {{ sido.sidoName }}
                    </option>
                  </select>
                </div>
                <div class="col-md-4">
                  <select class="form-select" v-model="dong">
                    <!--@change="getSearchResult" required>-->
                    <option value="" for="sidoSelect" class="form-label" style="font-size: 14px">
                      구/군
                    </option>
                    <option
                      v-for="(gugun, index) in gugunList"
                      :key="index"
                      :value="gugun.gugunCode"
                    >
                      {{ gugun.gugunName }}
                    </option>
                  </select>
                </div>
                <div class="col-md-4">
                  <select class="form-select" id="dongSelect" required>
                    <option value="" for="sidoSelect" class="form-label" style="font-size: 14px">
                      동
                    </option>
                    <option v-for="(dong, index) in dongList" :key="index" :value="dong.dongCode">
                      {{ dong.dongName }}
                    </option>
                  </select>
                </div>
                <div id="emailHelp" class="form-text"></div>
              </form>
              <button id="dongSearchBtn" class="search" type="button">검색</button
              ><!--@click="getSearchResult">검색</button>-->
            </div>
          </div>
        </div>
      </div>

      <div class="side-bottom pd-2">
        <!--아파트별-->
        <div id="aptList">
          <div class="items">
            <div class="res">검색결과 <span id="aptTotalCnt">0</span>건</div>
            <div id="item_box">
              <div
                class="item"
                v-for="(apart, index) in apartList"
                :key="index"
                @click="moveDetail"
              >
                <div class="item-content">
                  <div class="apt-title">{{ apart.aptName }}</div>
                  <div>
                    <span class="add-icon">주소</span>
                    <span class="add">
                      {{ apart.sidoName }} {{ apart.gugunName }} {{ apart.dongName }}
                      {{ apart.jibun }}</span
                    >
                  </div>
                </div>
              </div>
            </div>
            <PaginationUI></PaginationUI>
          </div>
        </div>
        <!--아파트별 끝-->
        <!--
     
        <div id="dongList" v-show="dongLi">
          <div class="items" style="height: 800px">
            <div class="res">검색결과 <span id="dongTotalCnt">0</span>건</div>
            <div id="item_box">
              <div id="item_box">
                <div class="item" v-for="(apart, index) in apartList" :key="index">
                  <div class="item-content">
                    <div class="apt-title">{{ apart.aptName }}</div>
                    <div>
                      <span class="add-icon">주소</span>
                      <span class="add"> {{ apart.sidoName }} {{ apart.gugunName }} {{ apart.dongName }} {{ apart.jibun }}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div id="dongPaginationWrapper"></div>
          </div>
        </div>
        동별 끝
      --></div>
    </div>
    <img class="toggle" v-show="isOpen" src="@/assets/close.png" @click="open" />
    <img class="toggle" v-show="isClose" src="@/assets/open.png" @click="close" />
    <!--사이드바 리스트 끝-->
  </div>
</template>

<script>
import PaginationUI from "./PaginationUI.vue";

export default {
  components: { PaginationUI },
  data() {
    return {
      show: true,
      sidoList: [
        { sidoName: "포항", sidoCode: 1 },
        { sidoName: "부산", sidoCode: 2 },
      ],
      gugunList: [{ gugunName: "남구", gugunCode: 1 }],
      dongList: [{ dongName: "창포동", dongCode: 1 }],
      searchWord: "",

      LIST_ROW_COUNT: 5,
      OFFSET: 0,
      sido: "",
      gugun: "",
      dong: "",

      PAGE_LINK_COUNT: 10,
      TOTAL_LIST_ITEM_COUNT: 0,
      CURRENT_PAGE_INDEX: 1,

      apartList: [
        {
          aptName: "두산위브",
          sidoName: "포항",
          gugnName: "북구",
          dongName: "장성동",
          jibun: "법원로 2",
        },
        {
          aptName: "두산위브",
          sidoName: "포항",
          gugnName: "북구",
          dongName: "장성동",
          jibun: "법원로 2",
        },
        {
          aptName: "두산위브",
          sidoName: "포항",
          gugnName: "북구",
          dongName: "장성동",
          jibun: "법원로 2",
        },
        {
          aptName: "두산위브",
          sidoName: "포항",
          gugnName: "북구",
          dongName: "장성동",
          jibun: "법원로 2",
        },
        {
          aptName: "두산위브",
          sidoName: "포항",
          gugnName: "북구",
          dongName: "장성동",
          jibun: "법원로 2",
        },
      ],
      apart: {},

      dongLi: false,
      AptLi: false,

      isOpen: true,
      isClose: false,

      isChangeAll: false,
    };
  },
  methods: {
    displayNone() {
      console.log("ff");
      this.show = false;
    },
    open() {
      this.isChangeAll = true;
      this.isOpen = false;
      this.isClose = true;
    },
    close() {
      this.isChangeAll = false;
      this.isOpen = true;
      this.isClose = false;
    },
    moveDetail() {
      //식별값을 vuex에 넘겨주고 push
      this.$router.push("propertyDetail");
    },

    // async getSido() {
    //   let url = "/maps/sido";
    //   let response = await http.get(url);
    //   let { data } = response;
    //   //console.log(data);
    //   this.sidoList = data;
    //   //console.log(this.sidoList);
    // }, //end getSido
    // async getGugun() {
    //   this.gugunList = []; //[{ gugunCode: "0", gugunName: "선택" }];
    //   this.dongList = []; //[{ dongCode: "0", dongName: "선택" }];
    //   this.gugun = "";
    //   this.dong = "";
    //   //console.log(this.dongList);
    //   if (this.sido == "") {
    //     return;
    //   }
    //   let url = "/maps/gugun/" + this.sido;
    //   let response = await http.get(url);
    //   let { data } = response;
    //   this.gugunList = data;
    //   this.getSearchResultInit();
    // }, // end getGugun

    // async getDong() {
    //   this.dongList = []; //[{ dongCode: "0", dongName: "선택" }];
    //   this.dong = "";
    //   if (this.gugun == "") {
    //     return;
    //   }
    //   let url = "/maps/dong/" + this.gugun;
    //   let response = await http.get(url);
    //   let { data } = response;
    //   console.log(data);
    //   this.dongList = data;
    //   this.getSearchResultInit();
    // },

    // getURL() {
    //   let url = "/map/all?limit=" + this.LIST_ROW_COUNT + "&offset=" + this.OFFSET;
    //   if (this.dong != "") {
    //     url += "&dongCode=" + this.dong;
    //   } else if (this.gugun != "") {
    //     url += "&gugunCode=" + this.gugun;
    //   } else if (this.sido != "") {
    //     url += "&sidoCode=" + this.sido;
    //   }
    //   console.log("search query : " + url);
    //   return url;
    // },

    // async getSearchResult() {
    //   let url = this.getURL();
    //   let response = await http.get(url);
    //   let { data } = response;
    //   console.log("getSearchResult :" + data);
    //   this.apartList = data.list;
    //   this.TOTAL_LIST_ITEM_COUNT = data.count;
    //   //addPagination();
    //   //displayMarkers(data.list); // 맵
    // }, //end getSearchResult

    // async getSearchResultInit() {
    //   this.OFFSET = 0;
    //   this.CURRENT_PAGE_INDEX = 1;
    //   this.getSearchResult();
    // },

    // detailApart: async function (aptCode) {
    //   this.aptCode = aptCode;
    //   console.log(aptCode);
    //   this.detailModal.show();

    // displayMarkers(data);

    //맵에 버튼 달기
  },
};
</script>
<style scoped>
.hideAll {
  margin-left: -17.1%;
}
.showAll {
  margin-left: 0%;
}
.side {
  background-color: rgb(238, 238, 238);
  height: 93.8%;
  width: 17%;
  margin-top: 3.1%;
  z-index: 100;
  position: absolute;
}
.side-top {
  height: 30%;
  background-color: #2eca6a;
  margin-bottom: 10px;
  border-radius: 5px;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
}
.side-bottom {
  background-color: rgb(238, 238, 238);
}
.items {
  color: rgb(135, 134, 134);
}
.item {
  background-color: white;
  border-radius: 5px;
  padding: 14px;
  margin: 10px;
  height: 10%;
}
.search {
  border: 0;
  background-color: rgb(109, 118, 125);
  color: white;
  padding: 5px;
  border-radius: 5px;
  width: 100%;
  margin-top: 3%;
  margin-bottom: 2%;
  font-weight: bold;
}
.item a {
  text-decoration: none;
  color: rgb(56, 56, 56);
}
.item .title {
  font-size: 16px;
  margin-bottom: 5px;
}
.page {
  margin: 0px auto;
  text-align: center;
}
.page ul {
  list-style: none;
}
.nav-link {
  color: rgb(32, 201, 151);
}
body,
div,
ul,
li {
  margin: 0;
  padding: 0;
}
ul,
li {
  list-style: none;
}

#pills-home-tab {
  display: none;
}

.search-tabs {
  color: white;
  padding: 5%;
}
.search-title {
  font-size: 17px;
  font-weight: bold;
  margin-bottom: 5%;
}
.date {
  float: right;
  color: rgb(173, 173, 173);
  font-size: 14px;
  font-weight: bold;
}
.add-icon {
  border: 1px grey;
  background-color: rgb(32, 201, 151);
  color: white;
  font-size: 11px;
  padding: 1.2%;
  border-radius: 2px;
}
.add {
  font-size: 12px;
}
.apt-title {
  font-size: 15px;
  font-weight: bold;
  margin-bottom: 3%;
}
.res {
  margin: 5%;
  font-size: 14px;
  font-weight: bold;
  color: rgb(56, 56, 56);
}
.res span {
  color: rgb(32, 201, 151);
}
.info {
  border-radius: 5px;
  font-size: 13px;
}
.toggle {
  width: 25px;
  height: 50px;
  margin-top: 17%;
  margin-left: 17%;
  position: absolute;
  z-index: 100;
}
</style>
