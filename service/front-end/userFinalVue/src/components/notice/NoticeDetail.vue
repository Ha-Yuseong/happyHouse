<template>
  <div class="container">
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">제목 : {{$store.state.noticeDetail.title}}</h1>
              <span class="color-text-a">공지 세부 페이지</span>
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <strong>글 번호: &nbsp;</strong><span class="color-text-a">{{$store.state.noticeDetail.boardId}}</span>
                </li>
                <li class="breadcrumb-item active">
                  <strong>등록 날짜: &nbsp;</strong> <span class="color-text-a">{{$store.state.noticeDetail.regDt.date | makeDateStr(".")}}</span>
                </li>
                <li class="breadcrumb-item active" aria-current="page">
                  <strong>조회 수: &nbsp;</strong> <span class="color-text-a">{{$store.state.noticeDetail.readCount}}</span>
                </li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
    </section><!-- End Intro Single-->
    <NoticeContent></NoticeContent>
    
  </div>
</template>

<script>
import util from "@/common/util.js";
import NoticeContent from "./NoticeContent.vue";


export default {
  components: {NoticeContent},
  created(){
    if(this.$store.state.noticeDetail.boardId <= 0){
      this.$router.push("/notice");
    }
    console.log(this.$store.state.noticeDetail.boardId);
  },
  methods : {
    toList(){
      let blank = {
        boardId: 0, title: "", content: "", userName: "",
        regDate: "", regTime: "", readCount: 0, fileList: [],
      };
      this.$store.state.noticeDetail = blank;
      this.$router.push("/notice");
    },
    makeDateStr: util.makeDateStr,
  },
  filters: {
      makeDateStr: function (date, separator) {
         return date.year + separator + (date.month < 10 ? "0" + date.month : date.month) + separator + (date.day < 10 ? "0" + date.day : date.day);
      },
   },
}
</script>