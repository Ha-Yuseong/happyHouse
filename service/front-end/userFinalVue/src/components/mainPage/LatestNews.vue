<template>
  <section class="section-news section-t8">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="title-wrap d-flex justify-content-between">
                <div class="title-box">
                  <h2 class="title-a">Latest News</h2>
                </div>
                <div class="title-link">
                  <a href="blog-grid.html"
                    >All News
                    <span class="bi bi-chevron-right"></span>
                  </a>
                </div>
              </div>
            </div>
          </div>
          
          <div>
            <swiper
            class="swiper"
            :style="{
              '--swiper-pagination-color': '#fff',
              '--swiper-navigation-color': '#fff',
            }"
            :slides-per-view="3"
            :space-between="30"
            :loop="true"
            :autoplay='{
              "delay": 3000,
              "disableOnInteraction": false
            }'
            :navigation="true"
            :breakpoints="{ 1024: {
              slidesPerView: 3,
              spaceBetween: 40
            },
            768: {
              slidesPerView: 2,
              spaceBetween: 30
            },
            640: {
              slidesPerView: 1,
              spaceBetween: 20
            },
            320: {
              slidesPerView: 1,
              spaceBetween: 10
            } }"
          >
            <swiper-slide
              v-for="n in 6"
              :key="n"
              class="test"
              :class="{test_2: true}"
            >
              <div class="carousel-item-c swiper-slide">
                  <div class="card-box-b card-shadow news-box">
                  <div class="img-box-b">
                      <img :src="'assets/img/post-'+ (n+1 % 6) +'.jpg'" alt="" class="img-b img-fluid" style="height:412px; width:100%" />
                  </div>
                  <div class="card-overlay">
                      <div class="card-header-b">
                      <div class="card-category-b">
                          <a href="#" class="category-b">Travel</a>
                      </div>
                      <div class="card-title-b">
                          <h2 class="title-2">
                          <a href="blog-single.html"
                              >Travel is comming <br />
                              new</a
                          >
                          </h2>
                      </div>
                      <div class="card-date">
                          <span class="date-b">18 Sep. 2017</span>
                      </div>
                      </div>
                  </div>
                  </div>
              </div>
            </swiper-slide>
          </swiper>
          </div>

        </div>
      </section>
</template>

<script>

import { Navigation, Pagination, Autoplay } from 'swiper'

import { SwiperCore, Swiper, SwiperSlide } from 'swiper-vue2'

import 'swiper/swiper-bundle.css'

SwiperCore.use([Navigation, Pagination, Autoplay])

export default {
  components: {
    Swiper,
    SwiperSlide
  },
}
</script>

<style scoped>
</style>