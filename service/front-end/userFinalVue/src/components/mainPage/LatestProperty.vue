<template>
  <section class="section-property section-t8">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="title-wrap d-flex justify-content-between">
                <div class="title-box">
                  <h2 class="title-a">Latest Properties</h2>
                </div>
                <div class="title-link">
                  <a href="property-grid.html"
                    >All Property
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
            :loop="true"
            :navigation="true"
            :autoplay='{
              "delay": 2800,
              "disableOnInteraction": false
            }'
          >
            <swiper-slide
              v-for="n in 6"
              :key="n"
              class="test"
              :class="{test_2: true}"
            >


                <div class="card-box-a card-shadow">
                  <div class="img-box-a">
                    <img :src="'assets/img/property-'+ ( (n+1) % 8)+'.jpg'" alt="" class="img-a img-fluid" />
                  </div>
                  <div class="card-overlay">
                    <div class="card-overlay-a-content">
                      <div class="card-header-a">
                        <h2 class="card-title-a">
                          <a href="property-single.html"
                            >206 Mount <br />
                            Olive Road Two</a
                          >
                        </h2>
                      </div>
                      <div class="card-body-a">
                        <div class="price-box d-flex">
                          <span class="price-a">rent | $ 12.000</span>
                        </div>
                        <a href="#" class="link-a"
                          >Click here to view
                          <span class="bi bi-chevron-right"></span>
                        </a>
                      </div>
                      <div class="card-footer-a">
                        <ul class="card-info d-flex justify-content-around">
                          <li>
                            <h4 class="card-info-title">Area</h4>
                            <span
                              >340m
                              <sup>2</sup>
                            </span>
                          </li>
                          <li>
                            <h4 class="card-info-title">Beds</h4>
                            <span>2</span>
                          </li>
                          <li>
                            <h4 class="card-info-title">Baths</h4>
                            <span>4</span>
                          </li>
                          <li>
                            <h4 class="card-info-title">Garages</h4>
                            <span>1</span>
                          </li>
                        </ul>
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
import { Navigation, Pagination } from 'swiper'

import { SwiperCore, Swiper, SwiperSlide } from 'swiper-vue2'

import 'swiper/swiper-bundle.css'

SwiperCore.use([Navigation, Pagination])

export default {
  components: {
    Swiper,
    SwiperSlide
  },
}
</script>

<style>

</style>