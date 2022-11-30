import Vue from "vue";
import VueRouter from "vue-router";
// Vue + router
Vue.use(VueRouter);

import NavBar from "@/components/NavBar.vue";
import MapPage from "@/components/MapPage.vue";
import SellerMapPage from "@/components/Seller/MapPage.vue";
import MainPage from "@/components/MainPage.vue";
import FooterPage from "@/components/FooterPage.vue";
import DummyPage from "@/components/DummyPage.vue";
import LoginPage from "@/components/LoginPage.vue";
import RegisterPage from "@/components/RegisterPage.vue";
import AccountPage from "@/components/AccountPage.vue";
import NoticePage from "@/components/NoticePage.vue";
import NoticeDetailPage from "@/components/notice/NoticeDetail.vue";
import FavoritePage from "@/components/customer/FavoritePage.vue";
import PropertyDetail from "@/components/customer/PropertyDetail.vue";
import ReportPage from "@/components/customer/ReportPage.vue";
import PropertyManage from "@/components/Seller/PropertyManage.vue";
import PropertyRegist from "@/components/Seller/PropertyRegist.vue";
import PropertyUpdate from "@/components/Seller/PropertyUpdate.vue";
import ContactPage from "@/components/ContactPage.vue";

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      components: {
        NavBar: NavBar,
        MainPage: MainPage,
        FooterPage: FooterPage,
      },
    },
    {
      path: "/map",
      components: {
        NavBar: NavBar,
        MainPage: MapPage,
        FooterPage: DummyPage,
      },
    },
    {
      path: "/sellerMap",
      components: {
        NavBar: NavBar,
        MainPage: SellerMapPage,
        FooterPage: DummyPage,
      },
    },
    {
      name: "RegisterPage",
      path: "/register",
      components: {
        NavBar: NavBar,
        MainPage: RegisterPage,
        FooterPage: FooterPage,
      },
    },
    {
      path: "/login",
      components: {
        NavBar: NavBar,
        MainPage: LoginPage,
        FooterPage: FooterPage,
      },
    },
    {
      path: "/favorite",
      components: {
        NavBar: NavBar,
        MainPage: FavoritePage,
        FooterPage: FooterPage,
      },
    },
    {
      path: "/mypage",
      components: {
        NavBar: NavBar,
        MainPage: AccountPage,
        FooterPage: DummyPage,
      },
    },
    {
      path: "/notice",
      components: {
        NavBar: NavBar,
        MainPage: NoticePage,
        FooterPage: FooterPage,
      },
    },
    {
      path: "/noticeDetail",
      components: {
        NavBar: NavBar,
        MainPage: NoticeDetailPage,
        FooterPage: FooterPage,
      },
    },
    {
      path: "/detail",
      components: {
        NavBar: NavBar,
        MainPage: PropertyDetail,
        FooterPage: FooterPage,
      },
    },
    {
      path: "/PropertyRegist",
      components: {
        NavBar: NavBar,
        MainPage: PropertyRegist,
        FooterPage: FooterPage,
      },
    },
    {
      path: "/PropertyManage",
      components: {
        NavBar: NavBar,
        MainPage: PropertyManage,
        FooterPage: FooterPage,
      },
    },
    {
      path: "/ReportPage",
      components: {
        NavBar: NavBar,
        MainPage: ReportPage,
        FooterPage: FooterPage,
      },
    },
    {
      path: "/propertyDetail",
      components: {
        NavBar: NavBar,
        MainPage: PropertyDetail,
        FooterPage: FooterPage,
      },
    },
    {
      path: "/PropertyUpdate",
      components: {
        NavBar: NavBar,
        MainPage: PropertyUpdate,
        FooterPage: FooterPage,
      },
    },
    {
      path: "/ContactPage",
      components: {
        NavBar: NavBar,
        MainPage: ContactPage,
        FooterPage: FooterPage,
      },
    },
  ],
});
