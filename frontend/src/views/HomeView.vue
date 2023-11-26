<template>
  <div>
    <div>
      <!-- Carousel Start -->
      <div class="container-fluid p-0 wow fadeIn" data-wow-delay="0.1s">
        <div
          id="header-carousel"
          class="carousel slide"
          data-bs-ride="carousel"
        >
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img class="w-100" src="@/assets/main1.jpg" alt="Image" />
              <div class="introduce-container">
                <div class="carousel-caption">
                  <div class="cs-inner">
                    <div
                      class="main-introduce d-flex flex-column align-items-center justify-content-center"
                    >
                      <div class="introduce">
                        같이 운동할 크루원을 찾으시나요? 잘 찾아오셨습니다.
                      </div>
                      <div>
                        <b-button
                          variant="primary"
                          class="move-button"
                          @click="moveVideo"
                        >
                          크루원 찾기
                        </b-button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#header-carousel"
            data-bs-slide="prev"
          >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#header-carousel"
            data-bs-slide="next"
          >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
      </div>
      <!-- Carousel End -->
      <!-- Top Feature Start -->

      <!-- Top Feature End -->
    </div>
  </div>
  <!-- <div>
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    > -->

  <!-- 두 번째 슬라이드 -->
  <!-- <b-carousel-slide>
        <template #img>
          <img
            src="https://picsum.photos/1024/480/?image=54"
            alt="Slide Image"
          />
        </template>
        <template #default>
          <div class="carousel-content">
            <b-button variant="warning" class="carousel-button"
              >바로가기</b-button
            >
            <div class="carousel-view-detail">
              <ViewDetail></ViewDetail>
            </div>
          </div>
        </template>
      </b-carousel-slide> -->

  <!-- </b-carousel>

    <p class="mt-4">
      Slide #: {{ slide }}<br />
      Sliding: {{ sliding }}
    </p> -->
  <!-- </div> -->
</template>
<script>
// import ViewDetail from "@/components/video/include/ViewDetail.vue";

export default {
  // name: "home-view",
  components: {
    // ViewDetail,
  },
  data() {
    return {
      slide: 0,
      sliding: null,
    };
  },
  methods: {
    // onSlideStart(slide) {
    //   this.sliding = true;
    // },
    // onSlideEnd(slide) {
    //   this.sliding = false;
    // },
  },
};
</script>

<style>
/* .cs-inner {
  display: flex;
  justify-content: center;
  align-content: center;
} */
.introduce-container {
  display: flex;
  justify-content: end;
}

.carousel-caption {
  display: flex;
  justify-content: center;
}

.main-container {
  display: flex;
  align-content: center;
}

.move-button {
  height: 50px;
  font-family: "Noto Sans KR", sans-serif;
  font-weight: bol;
  margin-top: 10px;
  font-size: 20px;
}
.main-introduce {
  display: flex;
  /* flex-direction: column; */
}
.introduce {
  margin-top: 12px;
  display: flex;
  align-content: center;
  justify-content: center;
  height: 50px;
  font-family: "Do Hyeon";
  font-size: 33px;
}
.carousel-content {
  position: relative;
  width: 100%;
  height: 0;
  padding-top: 60.25%; /* 9:16 비율의 가로 너비에 대한 세로 높이 비율 */
  overflow: hidden;
}

.carousel-button {
  font-family: "Noto Sans KR", sans-serif;
  font-weight: bold;
  position: absolute;
  width: 100px;
  height: 50px;
  top: 0%;
  left: 0%;
  /* margin-bottom: 50px; */
  /* transform: translate(100%, 100%); */
  z-index: 1; /* 버튼이 ViewDetail 위에 표시되도록 z-index 설정 */
}

.carousel-view-detail {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}
</style>
