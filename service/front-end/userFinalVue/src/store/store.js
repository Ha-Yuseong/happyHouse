import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import http from "@/common/axios.js";
import util from "@/common/util.js";
import router from "@/routers/router.js";

// state가 저장된 파일에서 vuex-persistedstate를 import하여 사용한다.
import createPersistedState from 'vuex-persistedstate';

export default new Vuex.Store({
  // Vuex.Store 내부에 추가한다.
  // 세션에 저장하고 Content의 모듈에 대해서만 데이터 상태 유지한다.
  plugins: [
    createPersistedState({
      paths: ["userInfo"],
      storage: window.sessionStorage,
    })
  ],
  state: {
    // 로그인 때 받는 사용자 정보
    userInfo: {
      isLogin: false,
      userSeq: "",
      userName: "",
      userPassword: "1234",
      userEmail: "user@my.com",
      userCode: "",
      userProfileImageUrl: "",
      userAddress: "부산 강서구 송정동",
      userPhone: "051-555-5555",
      userMobile: "010-987654-321",
    },

    searchValue: {
      dong: '사직동',
      code: 11110,
    },

    favoriteProperty: {
      list : [],

    },

    //판매매물 관리 객체
    PropertyManage: {
      list: [],
      limit: 10,
      offset: 0,
      searchWord: "",
      
      // pagination
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      totalListItemCount: 20,
    },

    sellerDetail: [],

    // 공지사항 용 객체
    notice: {
      list: [],
      limit: 10,
      offset: 0,
      searchWord: "",

      // pagination
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      totalListItemCount: 20,

      // detail, update, delete
      boardId: 0,
      title: "",
      content: "",
      userName: "",
      regDate: "",
      regTime: "",
      readCount: 0,
      fileList: [],
      sameUser: false,
    },

    sidoList: {code: '11', name:'서울특별시'},

    gugunList: [],

    dongList: [],

    noticeDetail: {
      boardId: 0,
      title: "",
      content: "",
      userName: "",
      regDate: "",
      regTime: "",
      readCount: 0,
      fileList: [],
    },

    aptDetail: {
      aptName: "두산위브",
      sidoName: "포항",
      gugnName: "북구",
      dongName: "장성동",
      jibun: "법원로 2",
    },

    // 원본 store의 board 객체
    board: {
      // list
      list: [],
      limit: 10,
      offset: 0,
      searchWord: "",

      // pagination
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      totalListItemCount: 0,

      // detail, update, delete

      boardId: 0,
      title: "",
      content: "",
      userName: "",
      regDate: "",
      regTime: "",
      readCount: 0,
      fileList: [],
      sameUser: false,
    },

    currImage: '',

    houseDetail: {
      list:[],
      img:''
    },
    
    favoriteList:{
      list: [],
      limit: 6,
      offset: 0,
      searchWord: "",

      listRowCount: 6,
      pageLinkCount: 6,
      currentPageIndex: 1,
      totalListItemCount: 15,
    },

    houseInfo: {
      list: [],
      limit: 10,
      offset: 0,
      searchWord: "",

      listRowCount: 5,
      pageLinkCount: 5,
      currentPageIndex: 1,
      totalListItemCount: 0,

      fileList: [],
    },

  },
  // state 상태를 변경하는 유일한 방법
  mutations: {
    // 로그인 용 mutation
    SET_LOGIN(state, payload) {
      state.userInfo.isLogin = true;
      state.userInfo.userSeq = payload.userSeq;
      state.userInfo.userName = payload.userName;
      state.userInfo.userPassword = payload.userPassword;
      state.userInfo.userEmail = payload.userEmail;
      state.userInfo.userCode = payload.code;

      console.log('user 인포임');
      console.log(state.userInfo);
    },
    SET_LOGOUT(state) {
      state.userInfo.isLogin = false;
      state.userInfo.userSeq = "";
      state.userInfo.userName = "";
      state.userInfo.userPassword = "";
      state.userInfo.userEmail = "";
      state.userInfo.userCode = "";
    },

    // 공지사항용 mutation
    SET_NOTICE_LIST(state, list) {
      state.notice.list = list;
    },

    SET_NOTICE_TOTAL_LIST_ITEM_COUNT(state, count) {
      state.notice.totalListItemCount = count;
    },

    SET_NOTICE_MOVE_PAGE(state, pageIndex) {
      state.notice.offset = (pageIndex - 1) * state.notice.listRowCount;
      state.notice.currentPageIndex = pageIndex;
    },

    SET_NOTICE_DETAIL(state, payload) {
      state.notice.boardId = payload.boardId;
      state.notice.title = payload.title;
      state.notice.content = payload.content;
      state.notice.userName = payload.userName;
      state.notice.regDate = util.makeDateStr(
        payload.regDt.date.year,
        payload.regDt.date.month,
        payload.regDt.date.day,
        "."
      );
      state.notice.regTime = util.makeTimeStr(
        payload.regDt.time.hour,
        payload.regDt.time.minute,
        payload.regDt.time.second,
        ":"
      );
      state.notice.readCount = payload.readCount;
      state.notice.fileList = payload.fileList;
      state.notice.sameUser = payload.sameUser;
    },

    SET_HOUSE_LIST(state, data) {
      state.houseInfo.list = data.list;
      state.houseInfo.totalListItemCount = data.count;

      console.log(data.list[0].img);
    },

    SET_HOUSE_MOVE_PAGE(state, pageIndex) {
      state.houseInfo.offset = (pageIndex - 1) * state.houseInfo.listRowCount;
      state.houseInfo.currentPageIndex = pageIndex;
    },

    SET_SIDO_LIST(state, payload){
      state.sidoList = payload;
    },

    SET_GUGUN_LIST(state, payload){
      state.gugunList = payload;
    },

    SET_DONG_LIST(state, payload,){
      console.log('구 값을 바꾼 것을 확인했습니다. 다음 :');
      console.log(payload.code);
      console.log('첫 번째 값은 다음 : ');
      console.log(payload.data[0].name);
      state.searchValue.code = payload.code;
      state.searchValue.dong = payload.data[0].name;
      state.dongList = payload.data;
    },

    SET_SEARCH(state,payload){
      console.log('현재 code 값은 : ' + state.searchValue.code);
      console.log('name 값은  :  ' + payload);

      state.searchValue.dong = payload;
    },

    SET_FAVORITE_LIST(state, payload){
      state.favoriteList.list = payload;
    },

    SET_HOUSEDETAIL(state, payload) {
      state.houseDetail.list = payload;
    },

    ///////////////////// 관심 목록 pagination /////////////////////////

    SET_FAVORITE_TOTAL_LIST_ITEM_COUNT(state, count) {
      state.favoriteList.totalListItemCount = count;
    },

    SET_FAVORITE_MOVE_PAGE(state, pageIndex) {
      state.favoriteList.offset = (pageIndex - 1) * state.favoriteList.listRowCount;
      state.favoriteList.currentPageIndex = pageIndex;
    },

    SET_FAVORITE_DETAIL() {
    },

    /////////////////////  목록 pagination /////////////////////////

    SET_MYPROPERTY_LIST(state, list) {
      state.PropertyManage.list = list.list;
    },

    SET_MYPROPERTY_TOTAL_LIST_ITEM_COUNT(state, count) {
      state.PropertyManage.totalListItemCount = count;
    },

    SET_MYPROPERTY_MOVE_PAGE(state, pageIndex) {
      state.PropertyManage.offset = (pageIndex - 1) * state.PropertyManage.listRowCount;
      state.PropertyManage.currentPageIndex = pageIndex;
    },

    SET_MYPROPERTY_DETAIL(state, payload) {
      state.PropertyManage.boardId = payload.boardId;
      state.PropertyManage.title = payload.title;
      state.PropertyManage.content = payload.content;
      state.PropertyManage.userName = payload.userName;
      state.PropertyManage.regDate = util.makeDateStr(
        payload.regDt.date.year,
        payload.regDt.date.month,
        payload.regDt.date.day,
        "."
      );
      state.PropertyManage.regTime = util.makeTimeStr(
        payload.regDt.time.hour,
        payload.regDt.time.minute,
        payload.regDt.time.second,
        ":"
      );
      state.PropertyManage.readCount = payload.readCount;
      state.PropertyManage.fileList = payload.fileList;
      state.PropertyManage.sameUser = payload.sameUser;
    },
  },
  // for async method
  actions: {
    // 공지사항용 action

    SET_HOUSEDETAIL(context, data){
      context.commit("SET_HOUSEDETAIL", data);
    },

    setSearch(context, data){
      context.commit("SET_SEARCH",data);
    },

    async noticeList(context) {
      let params = {
        limit: this.state.notice.limit,
        offset: this.state.notice.offset,
        searchWord: this.state.notice.searchWord,
      };

      try {
        let { data } = await http.get("/boards", { params }); // params: params shorthand property, let response 도 제거
        // state.notice.list = data.list
        console.log(data);
        context.commit("SET_NOTICE_LIST", data.list);
        context.commit("SET_NOTICE_TOTAL_LIST_ITEM_COUNT", data.count);
      } catch (error) {
        console.error(error);
      }
    },


    // 판매 집 리스트 용 action
    async sellingHouseList(context, Word) {
      let params = {
        limit: this.state.PropertyManage.limit,
        offset: this.state.PropertyManage.offset,
        userSeq: this.state.userInfo.userSeq,
        searchWord: Word,
     };

      try {
        let { data } = await http.get("/maps/sellingList", {params}); // params: params shorthand property, let response 도 제거
        console.log("가져온 map 결과");
        console.log(data);
        if (data.result == "login") {
          router.push("/login");
        } else {
          context.commit("SET_MYPROPERTY_LIST", data);
          context.commit("SET_MYPROPERTY_TOTAL_LIST_ITEM_COUNT", data.count);
        }
      } catch (error) {
        console.error(error);
      }
    },

    // 판매 집 리스트 map 용 action
    async sellingHouseListMap(context, Word) {
      let params = {
        userSeq: this.state.userInfo.userSeq,
        dong: this.state.searchValue.dong,
        code: this.state.searchValue.code,
        searchWord: Word,
     };
      try {
        let { data } = await http.get("/maps/sellingListMap", {params}); // params: params shorthand property, let response 도 제거
        console.log("가져온 map 결과");
        console.log(data);
        if (data.result == "login") {
          router.push("/login");
        } else {
          if(data.count > 0)context.commit("SET_HOUSE_LIST", data);
          else this.state.houseInfo.totalListItemCount = 0;
        }
      } catch (error) {
        this.state.houseInfo.totalListItemCount = 0;
        console.error(error);
      }
    },
    
    async emptyHouseListMap(context, Word) {
      let params = {
        userSeq: -1,
        dong: this.state.searchValue.dong,
        code: this.state.searchValue.code,
        searchWord: Word,
     };
      try {
        let { data } = await http.get("/maps/sellingListMap", {params}); // params: params shorthand property, let response 도 제거
        console.log("가져온 map 결과");
        console.log(data);
        if (data.result == "login") {
          router.push("/login");
        } else {
          if(data.count > 0)context.commit("SET_HOUSE_LIST", data);
          else this.state.houseInfo.totalListItemCount = 0;
        }
      } catch (error) {
        this.state.houseInfo.totalListItemCount = 0;
        console.error(error);
      }
    },

    // 집 리스트 용 action
    async houseList(context, Word) {
      let params = {
        searchWord: Word,
        dong: this.state.searchValue.dong,
        code: this.state.searchValue.code,
     };
     console.log('마이클 콜');
     console.log(params);
      try {
        let { data } = await http.get("/maps/aptList", {params}); // params: params shorthand property, let response 도 제거
        console.log("가져온 map 결과");
        console.log(data);
        if (data.result == "login") {
          router.push("/login");
        } else {
          if(data.count > 0)context.commit("SET_HOUSE_LIST", data);
          else this.state.houseInfo.totalListItemCount = 0;
        }
      } catch (error) {
        this.state.houseInfo.totalListItemCount = 0;
        console.error(error);
      }
    },

    // 시도 리스트 용 action
    async sidoList(context) {
      try {
        let { data } = await http.get("/maps/sidoList");

        if (data.result == "login") {
          router.push("/login");
        } else {
          context.commit("SET_SIDO_LIST", data);
        }
      } catch (error) {
        console.error(error);
      }
    },

    // 구군 리스트 용 action
    async gugunList(context, params) {
      try {
        let { data } = await http.get("/maps/gugunList/"+ params); // params: params shorthand property, let response 도 제거
        console.log('gugunList');
        console.log(data);
        if (data.result == "login") {
          router.push("/login");
        } else {
          context.commit("SET_GUGUN_LIST", data);
        }
      } catch (error) {
        console.error(error);
      }
    },

    // 동 리스트 용 action
    async dongList(context, params) {
      try {
        let { data } = await http.get("/maps/dongList/"+ params); // params: params shorthand property, let response 도 제거
        if (data.result == "login") {
          router.push("/login");
        } else {

          let pay = {
            data : data,
            code : params,
          };

          context.commit("SET_DONG_LIST", pay);
        }
      } catch (error) {
        console.error(error);
      }
    },

    // 관심 목록 용 action
    async favoriteList(context) {
      let params = {
        limit: this.state.favoriteList.limit,
        offset: this.state.favoriteList.offset,
        userSeq: this.state.userInfo.userSeq
      }

      console.log(params);

      try {
        let { data } = await http.get("/maps/favoriteList", {params}); // params: params shorthand property, let response 도 제거
        console.log('워버러머')
        console.log(data)

        if (data.result == "login") {
          router.push("/login");
        } else {
          context.commit("SET_FAVORITE_LIST", data.list);
          context.commit("SET_FAVORITE_TOTAL_LIST_ITEM_COUNT", data.count);
        }
      } catch (error) {
        console.error(error);
      }
    },

    // favorite 리스트에 더하기
    async addFavorite() {

      let params = {
        no: this.state.houseDetail.list.no,
        userSeq: this.state.userInfo.userSeq
      }

      console.log(params);

      try {
        
        let { data } = await http.get("/maps/getFavoriteOne", {params});

        console.log(data.count);

        console.log('0밑에는 바로');
        console.log(params);

        if(data.count == 0){
          await http.post("/maps/favorite/"+ params.no +"/"+ params.userSeq);
          alert('찜 목록에 추가 되었습니다!');
        }
        else{
            await http.delete("/maps/favorite", {params});
            alert('찜 목록에서 삭제되었습니다!');
        }

        if (data.result == "login") {
          router.push("/login");
        } 
      } catch (error) {
        console.error(error);
      }
    },
    
  },
  getters: {
    isLogin: function (state) {
      return state.login.isLogin;
    },

    // !!!!!! 공지사항 용 getters 모음 !!!!!!!

    getNoticeList: function (state) {
      return state.notice.list;
    },
    getNoticePageCount: function (state) {
      return Math.ceil(state.notice.totalListItemCount / state.notice.listRowCount);
    },
    getNoticeStartPageIndex: function (state) {
      if (state.notice.currentPageIndex % state.notice.pageLinkCount == 0) {
        //10, 20...맨마지막
        return (
          (state.notice.currentPageIndex / state.notice.pageLinkCount - 1) *
            state.notice.pageLinkCount +
          1
        );
      } else {
        return (
          Math.floor(state.notice.currentPageIndex / state.notice.pageLinkCount) *
            state.notice.pageLinkCount +
          1
        );
      }
    },
    getNoticeEndPageIndex: function (state, getters) {
      let ret = 0;
      if (state.notice.currentPageIndex % state.notice.pageLinkCount == 0) {
        //10, 20...맨마지막
        ret =
          (state.notice.currentPageIndex / state.notice.pageLinkCount - 1) *
            state.notice.pageLinkCount +
          state.notice.pageLinkCount;
      } else {
        ret =
          Math.floor(state.notice.currentPageIndex / state.notice.pageLinkCount) *
            state.notice.pageLinkCount +
          state.notice.pageLinkCount;
      }
      // 위 오류나는 코드를 아래와 같이 비교해서 처리
      return ret > getters.getNoticePageCount ? getters.getNoticePageCount : ret;
    },
    getNoticePrev: function (state) {
      if (state.notice.currentPageIndex <= state.notice.pageLinkCount) {
        return false;
      } else {
        return true;
      }
    },
    getNoticeNext: function (state, getters) {
      if (
        Math.floor(getters.getNoticePageCount / state.notice.pageLinkCount) *
          state.notice.pageLinkCount <
        state.notice.currentPageIndex
      ) {
        return false;
      } else {
        return true;
      }
    },

    // !!!!!! 집 리스트 용 getters 모음 !!!!!!!

    getHouseList: function (state) {
      return state.houseInfo.list;
    },

    getSidoList: function (state) {
      return state.sidoList;
    },

    getGugunList: function (state) {
      return state.gugunList;
    },

    getDongList: function (state) {
      return state.dongList;
    },

    getFavoriteList: function (state) {
      return state.favoriteList.list;
    },

    //////////////////// 관심 목록 페이지네이션 ////////////////////

    getFavoritePageCount: function (state) {
      return Math.ceil(state.favoriteList.totalListItemCount / state.favoriteList.listRowCount);
    },
    getFavoriteStartPageIndex: function (state) {
      if (state.favoriteList.currentPageIndex % state.favoriteList.pageLinkCount == 0) {
        //10, 20...맨마지막
        return (
          (state.favoriteList.currentPageIndex / state.favoriteList.pageLinkCount - 1) *
            state.favoriteList.pageLinkCount +
          1
        );
      } else {
        return (
          Math.floor(state.favoriteList.currentPageIndex / state.favoriteList.pageLinkCount) *
            state.favoriteList.pageLinkCount +
          1
        );
      }
    },
    getFavoriteEndPageIndex: function (state, getters) {
      let ret = 0;
      if (state.favoriteList.currentPageIndex % state.favoriteList.pageLinkCount == 0) {
        //10, 20...맨마지막
        ret =
          (state.favoriteList.currentPageIndex / state.favoriteList.pageLinkCount - 1) *
            state.favoriteList.pageLinkCount +
          state.favoriteList.pageLinkCount;
      } else {
        ret =
          Math.floor(state.favoriteList.currentPageIndex / state.favoriteList.pageLinkCount) *
            state.favoriteList.pageLinkCount +
          state.favoriteList.pageLinkCount;
      }
      // 위 오류나는 코드를 아래와 같이 비교해서 처리
      return ret > getters.getFavoritePageCount ? getters.getFavoritePageCount : ret;
    },
    getFavoritePrev: function (state) {
      if (state.favoriteList.currentPageIndex <= state.favoriteList.pageLinkCount) {
        return false;
      } else {
        return true;
      }
    },
    getFavoriteNext: function (state, getters) {
      if (
        Math.floor(getters.getFavoritePageCount / state.favoriteList.pageLinkCount) *
          state.favoriteList.pageLinkCount <
        state.favoriteList.currentPageIndex
      ) {
        return false;
      } else {
        return true;
      }
    },

    /////////////////////////////////// 판매 매물 목록 //////////////////////////////////////

    getMyPropertyList: function (state) {
      return state.PropertyManage.list;
    },
    getMyPropertyPageCount: function (state) {
      return Math.ceil(state.PropertyManage.totalListItemCount / state.PropertyManage.listRowCount);
    },
    getMyPropertyStartPageIndex: function (state) {
      if (state.PropertyManage.currentPageIndex % state.PropertyManage.pageLinkCount == 0) {
        //10, 20...맨마지막
        return (
          (state.PropertyManage.currentPageIndex / state.PropertyManage.pageLinkCount - 1) *
            state.PropertyManage.pageLinkCount +
          1
        );
      } else {
        return (
          Math.floor(state.PropertyManage.currentPageIndex / state.PropertyManage.pageLinkCount) *
            state.PropertyManage.pageLinkCount +
          1
        );
      }
    },
    getMyPropertyEndPageIndex: function (state, getters) {
      let ret = 0;
      if (state.PropertyManage.currentPageIndex % state.PropertyManage.pageLinkCount == 0) {
        //10, 20...맨마지막
        ret =
          (state.PropertyManage.currentPageIndex / state.PropertyManage.pageLinkCount - 1) *
            state.PropertyManage.pageLinkCount +
          state.PropertyManage.pageLinkCount;
      } else {
        ret =
          Math.floor(state.PropertyManage.currentPageIndex / state.PropertyManage.pageLinkCount) *
            state.PropertyManage.pageLinkCount +
          state.PropertyManage.pageLinkCount;
      }
      // 위 오류나는 코드를 아래와 같이 비교해서 처리
      return ret > getters.getMyPropertyPageCount ? getters.getMyPropertyPageCount : ret;
    },
    getMyPropertyPrev: function (state) {
      if (state.PropertyManage.currentPageIndex <= state.PropertyManage.pageLinkCount) {
        return false;
      } else {
        return true;
      }
    },
    getMyPropertyNext: function (state, getters) {
      if (
        Math.floor(getters.getMyPropertyPageCount / state.PropertyManage.pageLinkCount) *
          state.PropertyManage.pageLinkCount <
        state.PropertyManage.currentPageIndex
      ) {
        return false;
      } else {
        return true;
      }
    },
    
  },  
});
