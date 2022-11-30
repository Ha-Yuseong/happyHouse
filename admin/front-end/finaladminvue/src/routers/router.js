import Vue from "vue";
import VueRouter from "vue-router";
// Vue + router
Vue.use(VueRouter);

import NavBar from "@/components/NavBar.vue";
import LoginPage from "@/components/LoginPage.vue";
import MainPage from "@/components/MainPage.vue";
import NoticePage from "@/components/notice/NoticeList.vue";
import NoticeDetail from "@/components/notice/NoticeDetail.vue";
import NoticeRegist from "@/components/notice/NoticeRegist.vue";
import FakeList from "@/components/fakeList/FakeList.vue";
import FakeDetail from "@/components/fakeList/FakeDetail.vue";
import DummyPage from "@/components/DummyPage.vue";
import SideBar from "@/components/SideBar.vue";

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      components: {
        NavBar: DummyPage,
        SideBar: DummyPage,
        MainPage: LoginPage,
      },
    },
    {
      path: "/login",
      components: {
        NavBar: DummyPage,
        SideBar: DummyPage,
        MainPage: LoginPage,
      },
    },
    {
      path: "/main",
      components: {
        NavBar: NavBar,
        SideBar: SideBar,
        MainPage: MainPage,
      },
    },
    {
      path: "/notice",
      components: {
        NavBar: NavBar,
        SideBar: SideBar,
        MainPage: NoticePage,
      },
    },
    {
      path: "/fakelist",
      components: {
        NavBar: NavBar,
        SideBar: SideBar,
        MainPage: FakeList,
      },
    },
    {
      path: "/noticeDetail",
      components: {
        NavBar: NavBar,
        SideBar: SideBar,
        MainPage: NoticeDetail,
      },
    },
    {
      path: "/fakeDetail",
      components: {
        NavBar: NavBar,
        SideBar: SideBar,
        MainPage: FakeDetail,
      },
    },
    {
      path: "/noticeRegist",
      components: {
        NavBar: NavBar,
        SideBar: SideBar,
        MainPage: NoticeRegist,
      },
    },
  ],
});
