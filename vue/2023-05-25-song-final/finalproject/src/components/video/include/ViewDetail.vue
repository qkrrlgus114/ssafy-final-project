<template>
  <div class>
    <div class="video-detail">
      <div class="video-board">
        <b-card style="width: 50rem" no-body>
          <div class="youtube-video">
            <div v-html="getEmbeddedVideo(video.youtube_url)"></div>
          </div>

          <b-card-body>
            <b-card-title class="card-title">{{
              video.video_board_title
            }}</b-card-title>
            <b-card-sub-title class="mb-2"
              >조회수 {{ video.video_board_view_cnt }}회</b-card-sub-title
            >
            <b-card-text class="card-content">
              {{ video.video_board_content }}
            </b-card-text>
          </b-card-body>
          <b-card-body class="like-box-container">
            <div class="like-box">
              <b-button
                class="like-button"
                variant="outline-light"
                v-if="!isLiked"
                @click="likeUpdate"
              >
                <i class="fa-regular fa-heart" style="color: #051e48"></i>
              </b-button>

              <b-button
                class="like-button"
                variant="outline-light"
                v-else
                @click="cancleLikeUpdate"
              >
                <!-- <b-button
                class="like-button"
                variant="outline-light"
                v-if="!isLiked"
                @click="likeUpdate"
              >
                <i class="fa-regular fa-heart" style="color: #051e48" ></i>
              </b-button>

              <b-button
                class="like-button"
                variant="outline-light"
                v-else
                @click="cancleLikeUpdate"
              > -->
                <i class="fa-solid fa-heart" style="color: #ff0040"></i>
              </b-button>

              <div class="liketxt">
                좋아요 {{ video.video_board_like_cnt }}개
              </div>
            </div>
          </b-card-body>
          <template #footer>
            <small class="text-muted" style="text-align: right"
              >등록일 : {{ video.video_board_reg_date }}</small
            >
          </template>
        </b-card>
      </div>
      <div style="display: flex; justify-content: center">
        <div class="button-container">
          <b-button
            variant="dark"
            style="margin: 5px; width: 70px"
            @click="updateVideo"
          >
            수정
          </b-button>
          <b-button
            variant="dark"
            style="margin: 5px; width: 70px"
            @click="deleteVideo"
          >
            삭제
          </b-button>
        </div>

        <!-- <div class="button-container">
          <b-button class="admin-button" v-if="adminUser" @click="updateVideo">
            수정
          </b-button>
          <b-button class="admin-button" v-if="adminUser" @click="deleteVideo">
            삭제
          </b-button>
        </div> -->
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "ViewDetail",
  computed: {
    ...mapState(["video", "like_video", "loginUser"]),
    isLiked() {
      const userId = this.loginUser.user_id;
      const videoId = this.video.video_board_id;
      return this.like_video.some(
        (like) => like.user_id === userId && like.video_board_id === videoId
      );
    },
    adminUser() {
      return this.isAdmin();
    },
  },
  methods: {
    // 영상 가져오기
    getEmbeddedVideo(url) {
      // 유튜브 URL에서 videoId 추출
      const videoId = url.split("v=")[1];
      // 임베드 코드 생성
      return `<div class="area"><iframe width="780" height="440" class="embed-responsive-item video" src="https://www.youtube.com/embed/${videoId}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen ></iframe></div>`;
    },
    // 좋아요
    async likeUpdate() {
      let like = {
        user_id: this.loginUser.user_id,
        video_board_id: this.video.video_board_id,
      };
      await this.$store.dispatch("updateLikeCnt", like);
      await this.$store.dispatch("getVideoLike");
    },
    //좋아요 취소
    async cancleLikeUpdate() {
      let like = {
        user_id: this.loginUser.user_id,
        video_board_id: this.video.video_board_id,
      };
      await this.$store.dispatch("cancleUpdateLikeCnt", like);
      await this.$store.dispatch("getVideoLike");
    },
    // 영상 수정
    updateVideo() {
      const video_board_id = this.$route.params.video_board_id;
      this.$router.push({
        name: "video-update",
        params: { video_board_id: video_board_id },
      });
    },
    // 영상 삭제
    async deleteVideo() {
      if (confirm("정말로 영상을 삭제하시겠습니까?")) {
        const video_board_id = this.$route.params.video_board_id;
        await this.$store.dispatch("deleteVideo", video_board_id);
        this.$router.push({ name: "video-list" });
      }
    },
    // 어드민 여부 확인
    isAdmin() {
      const token = localStorage.getItem("access-token"); // 로컬 스토리지에서 토큰을 가져옵니다.
      if (token) {
        const base64Url = token.split(".")[1];
        const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
        const payload = JSON.parse(window.atob(base64));

        return payload.isAdmin === 1;
      } else {
        return false;
      }
    },
  },
  created() {
    this.$store.dispatch("getVideoLike");
  },
};
</script>

<style scoped>
.admin-button {
  margin: 10px;
}
.button-container {
  display: flex;
  justify-content: center;
  margin: 20px;
  /* margin-right: 144px; */
}
.video-board {
  display: flex;
  justify-content: center;
}
.youtube-video {
  margin-top: 10px;
  display: flex;
  justify-content: center;
}

.video-detail {
  margin-top: 50px;
  display: flex;
  flex-direction: column;
  font-family: "Noto Sans KR", sans-serif;
}

.card-title {
  font-family: "Do Hyeon";
  font-size: 30px;
}

.card-content {
  font-size: 15px;
  font-family: "Noto Sans KR", sans-serif;
}

.like-box {
  display: flex;
  flex-direction: column;
  margin-right: 10px;
  margin-bottom: 10px;
}
.like-box-container {
  display: flex;
  justify-content: flex-end;
}
/* 
@keyframes scale-up-center {
  0% {
    transform: scale(0.5);
  }
  100% {
    transform: scale(1);
  }
} */
.like-button {
  background-color: white;
  border: none;
  /* animation: scale-up-center 0.3s ease-in-out; */
  font-size: 30px;
}

.liketxt {
  font-size: 13px;
}
</style>
