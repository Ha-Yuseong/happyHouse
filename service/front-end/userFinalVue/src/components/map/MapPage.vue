<template>
<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;">
      <ul id="category">
        <li v-for="(data, index) in DataToShow" :key="index" :id="data.id" @click="onClickCategory(data.id)" :data-order="index"> 
            <span :class="'category_bg '+ data.class"></span>
            {{data.name}}
        </li>
    </ul>
    </div>

    <div id="menu_wrap" class="bg_white">
        <div class="option">
          <div class="row mb-3">
              <div class="col">
                <select class="selectBox" name="sido" disabled>
                  <option value=11>서울특별시</option>
                </select>
                <select class="selectBox" name="gugun" @change="getDong($event)">
                  <option v-for="(gugun,index) in $store.getters.getGugunList" :key="index" :value="gugun.code">{{gugun.name}}</option>
                </select>
                <select class="selectBox" name="dong" @change="setSearch($event)">
                  <option v-for="(dong,index) in $store.getters.getDongList" :key="index" :value="dong.name">{{dong.name}}</option>
                </select>
              </div>
            </div>
            <div>
              <input class="inputForm" type="text" placeholder="아파트 이름 검색" id="keyword" > 
                    <button type="button" class="btn btn-b-n navbar-toggle-box navbar-toggle-box-collapse" style="height:30px; width:30px" @click="searchPlaces"><i class="bi bi-search"></i></button> 
            </div>
        </div>
        <hr>
        <ul id="placesList"></ul>
        <div id="pagination"></div>
    </div>
</div>
</template>

<script>

let map;
let infowindow;
let markers= [];
// let clusterer; // 클러스터 혹시 사용할거면 모든 클러스터 주석만 풀어주면 된다.

// 밑에는 카테고리 전용 변수들
let psC;
let placeOverlay;
let currCategory='';
let contentNode;
let CategoryMarkers;

export default {
  data() {
    return {

      DataToShow:[
      {id:"BK9", name:"은행", class:"bank"},
      {id:"MT1", name:"마트", class:"mart"},
      {id:"HP8", name:"병원", class:"pharmacy"},
      {id:"OL7", name:"주유소", class:"oil"},
      {id:"CE7", name:"카페", class:"cafe"},
      {id:"CS2", name:"편의점", class:"store"}],

      totalCnt: 0,

      imageSrc: "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4801afa0c3479ed50f35a937a19f2034&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      placeOverlay = new kakao.maps.CustomOverlay({zIndex:1}), 
      contentNode = document.createElement('div'), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다 
      CategoryMarkers = [], // 마커를 담을 배열입니다
      currCategory = ''; // 현재 선택된 카테고리를 가지고 있을 변수입니다
      
      var container = document.getElementById("map");
      infowindow = new kakao.maps.InfoWindow({zIndex:2, removable:true});

      var options = {
        center: new kakao.maps.LatLng(37.522231, 126.980380),
        level: 7,
      };

      map = new kakao.maps.Map(container, options);

      // clusterer = new kakao.maps.MarkerClusterer({
      //     map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
      //     averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
      //     minLevel: 7 // 클러스터 할 최소 지도 레벨 
      // });

      psC = new kakao.maps.services.Places(map); 

      kakao.maps.event.addListener(map, 'idle', this.searchCategory);

      contentNode.className = 'placeinfo_wrap';

      this.addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
      this.addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);

      placeOverlay.setContent(contentNode);  

    },

    async searchPlaces() {

      var keyword = document.getElementById('keyword').value;

      if (!keyword.replace(/^\s+|\s+$/g, '')) {
          await this.$store.dispatch('houseList','');
          this.makingMarker();
      }
      else{
        await this.$store.dispatch('houseList',keyword);
          this.makingMarker();
      }
      
    },

    makingMarker() {
      
      var tmp =  this.$store.state.houseInfo;
      console.log(tmp.totalListItemCount);

      if(this.$store.state.houseInfo.totalListItemCount<=0){
        alert('검색 결과가 없습니다.');
        console.log(this.$store.state.houseInfo);
        return;
      }
      else{
        // clusterer.clear();
        console.log(this.$store.state.houseInfo.list);
      
        this.displayPlaces(this.$store.state.houseInfo.list);
        this.displayPagination(this.$store.state.houseInfo.list);

      }
    },

    displayPlaces(places) {

    var listEl = document.getElementById('placesList'), 
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(), 
    bounds = new kakao.maps.LatLngBounds();

    // 검색 결과 목록에 추가된 항목들을 제거합니다
    this.removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    this.removeMarker();

    console.log('마커 만들기 숫자 : ' + places.length);

    for ( var i=0; i<places.length; i++ ) {

        

        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].lat, places[i].lng),
            marker = this.addMarker(placePosition, i), 
            itemEl = this.getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        var $this = this;

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function(marker, place, placePosition, index, detail) {
            kakao.maps.event.addListener(marker, 'click', function() {
            detail.$store.dispatch('SET_HOUSEDETAIL', place);
            detail.$store.state.houseDetail.img = place.img + ( (index % 9)+1) + '.jpg';
            var content = '<div class="markerBox"> <div class="headLine"> <span class="AptNumber">no : ' + place.no + '</span><br><span class="AptName"> ' + place.AptName + '</span></div>';
            content += '<div class="row"> <div class="col-md-4"> <div class="markerBoxImg" style="background:url(' + place.img + ( (index % 9)+1) + '.jpg) no-repeat; background-size: cover;"></div>'
            content += '</div> <div class="col-md-8"><p class="markerSpan">서울특별시 ' +  place.gu  + ' ' +place.dong + ' ' + place.jibun + ' ' + place.floor + '층</p>'
            content += '<span class="markerSpan">판매자 : '+ place.seller +'</span><br>'
            content += '<p class="markerSpan">가격 : ' + place.dealAmount +' 만원</p>'
            content += '<p class="markerSpan">평수 : ' + place.area +'m<sup>2</sup></p>'
            content += '<a id="favorite1" class="markerBrn2" style="color:#f4b1b1">찜</a><a id="detail1" class="markerBrn" style="color:#fff">상세 페이지</a>'
            content += '</div></div></div>'
            infowindow.setContent(content);
            infowindow.open(map, marker);

            document.getElementById("detail1").onclick = function(){
              detail.toDetail();
            }
            document.getElementById("favorite1").onclick = async function(){
              console.log('실행되라...');
              detail.$store.dispatch('addFavorite');
            }
            });

            itemEl.onclick =  function () {
            detail.$store.dispatch('SET_HOUSEDETAIL', place);
            detail.$store.state.houseDetail.img = place.img + ( (index % 9)+1) + '.jpg';
            map.setLevel(2);
            map.setCenter(placePosition);
            var content = '<div class="markerBox"> <div class="headLine"> <span class="AptNumber">no : ' + place.no + '</span><br><span class="AptName"> ' + place.AptName + '</span></div>';
            content += '<div class="row"> <div class="col-md-4"> <div class="markerBoxImg" style="background:url(' + place.img + ( (index % 9)+1) + '.jpg) no-repeat; background-size: cover;"></div>'
            content += '</div> <div class="col-md-8"><p class="markerSpan">서울특별시 ' +  place.gu  + ' ' +place.dong + ' ' + place.jibun + ' ' + place.floor + '층</p>'
            content += '<span class="markerSpan">판매자 : '+ place.seller +'</span><br>'
            content += '<p class="markerSpan">가격 : ' + place.dealAmount +' 만원</p>'
            content += '<p class="markerSpan">평수 : ' + place.area +'m<sup>2</sup></p>'
            content += '<a id="favorite" class="markerBrn2" style="color:#f4b1b1">찜</a><a id="detail" class="markerBrn" style="color:#fff">상세 페이지</a>'
            content += '</div></div></div>'
            infowindow.setContent(content);
            infowindow.open(map, marker);
            
            document.getElementById("detail").onclick = function(){
              detail.toDetail();
            }

            document.getElementById("favorite").onclick = async function(){
              console.log('실행되라...');
              detail.$store.dispatch('addFavorite');
            }

            };
        })(marker, places[i], placePosition, i, $this);

        fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
    },

    getListItem(index, places) {
    var el = document.createElement('li'),
    itemStr = '<span class="markerbg" style="background:url(' + places.img + ( (index % 9)+1) + '.jpg) no-repeat; background-size: cover;"></span>' +
                '<div class="info">' +
                '   <h5>' + places.AptName + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">부산광역시 동래구 사직3동' + /* places.address_name */ + '</span>';
    } else {
        itemStr += '    <span class="jibun gray" style="font-size: 11px">서울특별시 ' +  places.gu  + ' ' +places.dong + ' ' + places.jibun + '</span>'; 
    }
                
    itemStr += '  <span class="tel">010-5327-0972'/* + places.phone */ + '</span>' +
                '</div>';           

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
    },

    addMarker(position) {
    var imageSrc = require("@/assets/location.png"), // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(24, 35),  // 마커 이미지의 크기
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize),
            marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다
    // clusterer.addMarkers(markers);

    return marker;
    },

    removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }   
    markers = [];
    },

    displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i; 

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    var maxSize = pagination.last;

    for (i=1; i<=maxSize%10; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
    },

    removeAllChildNods(el) {   
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
    },
    
    
    /////////////////////////////////////// 카테고리 라인 ///////////////////////////////////////

    addEventHandle(target, type, callback) {
        if (target.addEventListener) {
            target.addEventListener(type, callback);
        } else {
            target.attachEvent('on' + type, callback);
        }
    },

    // 카테고리 검색을 요청하는 함수입니다
    searchCategory() {
        if (!currCategory) {
            return;
        }
        // 커스텀 오버레이를 숨깁니다 
        placeOverlay.setMap(null);

        // 지도에 표시되고 있는 마커를 제거합니다
        this.removeCategory();
        
        psC.categorySearch(currCategory, this.categorySearchCB, {useMapBounds:true}); 
    },

    categorySearchCB(data, status) {
        if (status === kakao.maps.services.Status.OK) {

            // 정상적으로 검색이 완료됐으면
            // 검색 목록과 마커를 표출합니다
            this.displayCategory(data);

        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

            alert('검색 결과가 존재하지 않습니다.');
            return;

        } else if (status === kakao.maps.services.Status.ERROR) {

            alert('검색 결과 중 오류가 발생했습니다.');
            return;

        }
    },

    displayCategory(places) {

        // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
        // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
        var order = document.getElementById(currCategory).getAttribute('data-order');

        for ( var i=0; i<places.length; i++ ) {

                // 마커를 생성하고 지도에 표시합니다
                var marker = this.addCategoryMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

                // 마커와 검색결과 항목을 클릭 했을 때
                // 장소정보를 표출하도록 클릭 이벤트를 등록합니다

                let place = places[i];

                kakao.maps.event.addListener(marker, 'click', function() {
                        var content = '<div class="placeinfo">' +
                      '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + '</a>';   

                      if (place.road_address_name) {
                          content += '    <span title="' + place.road_address_name + '">' + place.road_address_name + '</span>' +
                                      '  <span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span>';
                      }  else {
                          content += '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
                      }                
                    
                      content += '    <span class="tel">' + place.phone + '</span>' + 
                                  '</div>' + 
                                  '<div class="after"></div>';

                      contentNode.innerHTML = content;
                      placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
                      placeOverlay.setMap(map); 
                });
        }
    },
    
    addCategoryMarker(position, order) {
        var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
            imageSize = new kakao.maps.Size(27, 28),  // 마커 이미지의 크기
            imgOptions =  {
                spriteSize : new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
                spriteOrigin : new kakao.maps.Point(46, (order*36)), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                offset: new kakao.maps.Point(11, 28) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
            },
            markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
                marker = new kakao.maps.Marker({
                position: position, // 마커의 위치
                image: markerImage 
            });

        marker.setMap(map); // 지도 위에 마커를 표출합니다
        CategoryMarkers.push(marker);  // 배열에 생성된 마커를 추가합니다

        return marker;
    },

    onClickCategory(el) {
        var value = document.getElementById(el);

        placeOverlay.setMap(null);

        if (value.className === 'on') {
            currCategory = '';
            this.changeCategoryClass();
            this.removeCategory();
        } else {
            currCategory = el;
            this.changeCategoryClass(value);
            this.searchCategory();
        }
    },

    toDetail(){
      this.$router.push("/propertyDetail").catch(() => {});
    },

    changeCategoryClass(el) {
        var category = document.getElementById('category'),
            children = category.children,
            i;

        for ( i=0; i<children.length; i++ ) {
            children[i].className = '';
        }

        if (el) {
            console.log('sddsd'+el.id);
            el.className = 'on';
        } 
    },

    removeCategory() {
      for ( var i = 0; i < CategoryMarkers.length; i++ ) {
        CategoryMarkers[i].setMap(null);
        }   
        CategoryMarkers = [];
    },

    getGugun(event){
      this.$store.dispatch('gugunList',event.target.value);
    },

    async getDong(event){
     await this.$store.dispatch('dongList',event.target.value);
    },

    setSearch(event){
      this.$store.dispatch('setSearch',event.target.value);
    }

  },
  created() {
      this.$store.dispatch('gugunList',11);
      this.$store.dispatch('dongList',11680);
   },
  computed: {
    // gttters 이용
    getSidoList() {
        return this.$store.getters.getSidoList; // no getBoardList()
    },
  },
};
</script>
<style scoped>

</style>
