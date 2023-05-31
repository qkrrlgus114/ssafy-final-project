<template>
  <div class="board-post-container">
    <div class="container mt-4">
      <h2 class="mb-4 text-center">게시글 상세 정보</h2>
      <div class="regist-button">
        <b-button variant="dark" @click="registPost">게시글 작성</b-button>
      </div>
      <!-- <div class="d-flex justify-content-end mb-4">
        <router-link :to="{ name: 'crew-board' }" class="btn btn-secondary">
          게시글 목록
        </router-link>
      </div> -->
      <b-card no-body class="mb-2">
        <b-card-header header-tag="header" class="p-1">
          <b-container fluid>
            <b-row>
              <b-col>
                <div class="post-title">제목: {{ crew_board_post_title }}</div>
                <hr />
                <div class="post-details">
                  <div>작성자: {{ crew_board_post_writer }}</div>
                  <div>작성일: {{ crew_board_post_reg_date }}</div>
                  <div>조회수: {{ crew_board_post_view_cnt }}</div>
                </div>
              </b-col>
            </b-row>
          </b-container>
        </b-card-header>

        <b-card-body>
          <b-container fluid>
            <b-row>
              <b-col :md="crew_board_post_img ? '6' : '12'" class="mb-3">
                <p class="post-content" v-html="crew_board_post_content"></p>
              </b-col>
              <b-col md="6" v-if="crew_board_post_img">
                <b-img
                  :src="'http://localhost:9999/upload/' + crew_board_post_img"
                  alt="프로필 사진"
                  fluid
                  thumbnail
                ></b-img>
              </b-col>
            </b-row>
          </b-container>
        </b-card-body>
      </b-card>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";

export default {
  name: "ViewDetail",
  data() {
    return {
      crew_board_post_id: "",
      crew_id: "",
      crew_board_post_title: "",
      crew_board_post_writer: "",
      crew_board_post_content: "",
      crew_board_post_img: "",
      crew_board_post_reg_date: "",
      crew_board_post_view_cnt: "",
    };
  },
  computed: {
    ...mapState(["post", "loginUser"]),
    // isLiked() {
    //   const userId = this.loginUser.user_id;
    //   const videoId = this.video.video_board_id;
    //   return this.like_video.some(
    //     (like) => like.user_id === userId && like.video_board_id === videoId
    //   );
    // },
  },
  methods: {
    registPost() {
      this.$router.push({ name: "post-create" });
    },
    // // 좋아요
    // async likeUpdate() {
    //   let like = {
    //     user_id: this.loginUser.user_id,
    //     video_board_id: this.video.video_board_id,
    //   };
    //   await this.$store.dispatch("updateLikeCnt", like);
    //   await this.$store.dispatch("getVideoLike");
    // },
    // //좋아요 취소
    // async cancleLikeUpdate() {
    //   let like = {
    //     user_id: this.loginUser.user_id,
    //     video_board_id: this.video.video_board_id,
    //   };
    //   await this.$store.dispatch("cancleUpdateLikeCnt", like);
    //   await this.$store.dispatch("getVideoLike");
    // },
  },
  created() {
    axios({
      url: `http://localhost:9999/crews/board/post/get/${this.$route.params.crew_board_post_id}`,
      method: "GET",
      params: this.$route.params.crew_board_post_id,
    }).then((res) => {
      this.crew_board_post_id = res.data.crew_board_post_id;
      this.crew_id = res.data.crew_id;
      this.crew_board_post_title = res.data.crew_board_post_title;
      this.crew_board_post_writer = res.data.crew_board_post_writer;
      this.crew_board_post_content = res.data.crew_board_post_content;
      this.crew_board_post_img = res.data.crew_board_post_img;
      this.crew_board_post_reg_date = res.data.crew_board_post_reg_date;
      this.crew_board_post_view_cnt = res.data.crew_board_post_view_cnt;
    });
  },
};
</script>

<style scoped>
.board-post-container {
  margin: auto;
  width: 1000px;
  /* display: flex; */
  /* justify-content: center; */
}
.regist-button {
  display: flex;
  justify-content: right;
  margin-bottom: 20px;
}
.container {
  width: 1000px;
  padding: 20px;
  font-family: "Noto Sans KR", sans-serif;
}

.table-responsive {
  margin-bottom: 20px;
}
.post-title {
  font-size: 25px;
  margin-top: 10px;
}
.post-details {
  display: flex;
  justify-content: space-between;
  font-weight: lighter;
  font-size: 18px;
  color: grey;
  margin-bottom: 10px;
}
.post-content {
  font-size: 20px;
}
.area {
  position: relative;
  width: 100%;
  padding-bottom: 56.25%;
}

.video {
  position: absolute;
  width: 100%;
  height: 100%;
}
</style>
