import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import http from "@/common/axios.js";
import util from "@/common/util.js";

import router from "@/routers/router.js";

export default new Vuex.Store({
  state: {
    // login, NavBar
    login: {
      // NavBar
      isLogin: "",

      userName: "",
      userProfileImageUrl: "",

      // Login
      userEmail: "dskim@my.com",
      userPassword: "1234",
    },
    notice: {
      limit: 10,
      offset: 0,
      searchWord: "",

      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      totalListItemCount: 20,

      curNotice: {},
      noticeList: [{}],
    },
    fake: {
      limit: 10,
      offset: 0,
      searchWord: "",

      // pagination
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      totalListItemCount: 20,

      curFake: {},
      fakeList: [{}],
    },
    //
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

      totalListItemCount: 20,

      // detail, update, delete

      boardId: 0,
      title: "",
      content: "",
      userName: "",
      regDate: "",
    },
  },
  // state 상태를 변경하는 유일한 방법
  mutations: {
    SET_LOGIN(state, payload) {
      state.login.isLogin = payload.isLogin;
      state.login.userName = payload.userName;
      state.login.userProfileImageUrl = payload.userProfileImageUrl;
    },
    SET_LOGOUT(state) {
      state.login.isLogin = false;
      state.login.userSeq = "";
      state.login.userName = "";
      state.login.userPassword = "";
      state.login.userEmail = "";
      state.login.userCode = "";
    },

    SET_BOARD_LIST(state, list) {
      state.board.list = list;
    },

    SET_BOARD_TOTAL_LIST_ITEM_COUNT(state, count) {
      state.board.totalListItemCount = count;
    },

    SET_BOARD_MOVE_PAGE(state, pageIndex) {
      state.board.offset = (pageIndex - 1) * state.board.listRowCount;
      state.board.currentPageIndex = pageIndex;
    },

    SET_BOARD_DETAIL(state, payload) {
      state.board.boardId = payload.boardId;
      state.board.title = payload.title;
      state.board.content = payload.content;
      state.board.userName = payload.userName;
      state.board.regDate = util.makeDateStr(
        payload.regDt.date.year,
        payload.regDt.date.month,
        payload.regDt.date.day,
        "."
      );
      state.board.regTime = util.makeTimeStr(
        payload.regDt.time.hour,
        payload.regDt.time.minute,
        payload.regDt.time.second,
        ":"
      );
      state.board.readCount = payload.readCount;
      state.board.fileList = payload.fileList;
      state.board.sameUser = payload.sameUser;
    },
    // for UpdateModal title v-modal
    SET_BOARD_TITLE(state, title) {
      state.board.title = title;
    },
    //--------------------------------------------------------
    // 공지사항용 mutation 내가 만듬
    SET_CURNOTICE(state, payload) {
      state.notice.curNotice = payload;
    },
    SET_NOTICE_LIST(state, list) {
      state.notice.noticeList = list;
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
    // 허위매물
    SET_CURFAKE(state, payload) {
      state.fake.curFake = payload;
    },
    SET_FAKE_DETAIL(state, payload) {
      state.fake.fakeId = payload.fakeId;
      state.fake.title = payload.title;
      state.fake.content = payload.content;
      state.fake.userId = payload.userId;
      state.fake.sellerId = payload.sellerId;
      state.fake.isDone = payload.isDone;
      state.fake.regDate = util.makeDateStr(
        payload.regDt.date.year,
        payload.regDt.date.month,
        payload.regDt.date.day,
        "."
      );
      state.fake.regTime = util.makeTimeStr(
        payload.regDt.time.hour,
        payload.regDt.time.minute,
        payload.regDt.time.second,
        ":"
      );
      state.fake.readCount = payload.readCount;
      state.fake.fileList = payload.fileList;
      state.fake.sameUser = payload.sameUser;
    },

    SET_FAKE_LIST(state, list) {
      state.fake.fakeList = list;
    },

    SET_FAKE_TOTAL_LIST_ITEM_COUNT(state, count) {
      state.fake.totalListItemCount = count;
    },

    SET_FAKE_MOVE_PAGE(state, pageIndex) {
      state.fake.offset = (pageIndex - 1) * state.fake.listRowCount;
      state.fake.currentPageIndex = pageIndex;
    },
  },
  // for async method
  actions: {
    async noticeList(context) {
      let params = {
        limit: this.state.notice.limit,
        offset: this.state.notice.offset,
        searchWord: this.state.notice.searchWord,
      };

      try {
        console.log("list");
        let { data } = await http.get("/notice", { params }); // params: params shorthand property, let response 도 제거
        console.log("noticeList: data : ");
        console.log(data.list);
        if (data.result == "login") {
          router.push("/login");
        } else {
          context.commit("SET_NOTICE_LIST", data.list);
          //context.commit("SET_BOARD_TOTAL_LIST_ITEM_COUNT", data.count);
        }
      } catch (error) {
        console.error(error);
      }
    },
    async fakeList(context) {
      let params = {
        limit: this.state.fake.limit,
        offset: this.state.fake.offset,
        searchWord: this.state.fake.searchWord,
      };

      try {
        console.log("list");
        let { data } = await http.get("/fake", { params }); // params: params shorthand property, let response 도 제거
        console.log("fakeList: data : ");
        console.log(data.list);
        if (data.result == "login") {
          router.push("/login");
        } else {
          context.commit("SET_FAKE_LIST", data.list);
          //context.commit("SET_BOARD_TOTAL_LIST_ITEM_COUNT", data.count);
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

    getBoardList: function (state) {
      return state.board.list;
    },

    // pagination
    getPageCount: function (state) {
      return Math.ceil(state.board.totalListItemCount / state.board.listRowCount);
    },
    getStartPageIndex: function (state) {
      if (state.board.currentPageIndex % state.board.pageLinkCount == 0) {
        //10, 20...맨마지막
        return (
          (state.board.currentPageIndex / state.board.pageLinkCount - 1) *
            state.board.pageLinkCount +
          1
        );
      } else {
        return (
          Math.floor(state.board.currentPageIndex / state.board.pageLinkCount) *
            state.board.pageLinkCount +
          1
        );
      }
    },
    getEndPageIndex: function (state, getters) {
      let ret = 0;
      if (state.board.currentPageIndex % state.board.pageLinkCount == 0) {
        //10, 20...맨마지막
        ret =
          (state.board.currentPageIndex / state.board.pageLinkCount - 1) *
            state.board.pageLinkCount +
          state.board.pageLinkCount;
      } else {
        ret =
          Math.floor(state.board.currentPageIndex / state.board.pageLinkCount) *
            state.board.pageLinkCount +
          state.board.pageLinkCount;
      }
      // 위 오류나는 코드를 아래와 같이 비교해서 처리
      return ret > getters.getPageCount ? getters.getPageCount : ret;
    },
    getPrev: function (state) {
      if (state.board.currentPageIndex <= state.board.pageLinkCount) {
        return false;
      } else {
        return true;
      }
    },
    getNext: function (state, getters) {
      if (
        Math.floor(getters.getPageCount / state.board.pageLinkCount) * state.board.pageLinkCount <
        state.board.currentPageIndex
      ) {
        return false;
      } else {
        return true;
      }
    },
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

    // 허위 매물
    getFakeList: function (state) {
      return state.fake.list;
    },
    getFakePageCount: function (state) {
      return Math.ceil(state.fake.totalListItemCount / state.fake.listRowCount);
    },
    getFakeStartPageIndex: function (state) {
      if (state.fake.currentPageIndex % state.fake.pageLinkCount == 0) {
        //10, 20...맨마지막
        return (
          (state.fake.currentPageIndex / state.fake.pageLinkCount - 1) * state.fake.pageLinkCount +
          1
        );
      } else {
        return (
          Math.floor(state.fake.currentPageIndex / state.fake.pageLinkCount) *
            state.fake.pageLinkCount +
          1
        );
      }
    },
    getFakeEndPageIndex: function (state, getters) {
      let ret = 0;
      if (state.fake.currentPageIndex % state.fake.pageLinkCount == 0) {
        //10, 20...맨마지막
        ret =
          (state.fake.currentPageIndex / state.fake.pageLinkCount - 1) * state.fake.pageLinkCount +
          state.fake.pageLinkCount;
      } else {
        ret =
          Math.floor(state.fake.currentPageIndex / state.fake.pageLinkCount) *
            state.fake.pageLinkCount +
          state.fake.pageLinkCount;
      }
      // 위 오류나는 코드를 아래와 같이 비교해서 처리
      return ret > getters.getFakePageCount ? getters.getFakePageCount : ret;
    },
    getFakePrev: function (state) {
      if (state.fake.currentPageIndex <= state.fake.pageLinkCount) {
        return false;
      } else {
        return true;
      }
    },
    getFakeNext: function (state, getters) {
      if (
        Math.floor(getters.getFakePageCount / state.fake.pageLinkCount) * state.fake.pageLinkCount <
        state.fake.currentPageIndex
      ) {
        return false;
      } else {
        return true;
      }
    },
  },
});
