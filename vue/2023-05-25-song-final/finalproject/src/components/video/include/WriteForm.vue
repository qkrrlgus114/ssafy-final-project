<template>
  <div>
    <h1 v-if="type == 'create'" class="underline">영상 등록</h1>
    <h2 v-if="type == 'update'" class="underline">영상 수정</h2>
    <div class="container" role="main">
      <div class="mb-3">
        <label for="video_board_title">제목</label>
        <input
          placeholder="50자 이내"
          class="form-control"
          type="text"
          id="video_board_title"
          name="video_board_title"
          v-model="video_board_title"
        />
      </div>
      <div class="mb-3">
        <label for="youtube_url">유튜브 주소</label>
        <input
          class="form-control"
          type="text"
          id="youtube_url"
          name="youtube_url"
          v-model="youtube_url"
        />
      </div>
      <div class="mb-3">
        <label for="video_board_content">내용</label>
        <textarea
          rows="10"
          cols="50"
          class="form-control"
          type="text"
          id="video_board_content"
          name="video_board_content"
          v-model="video_board_content"
        ></textarea>
      </div>

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
          @click="moveList"
        >
          삭제
        </b-button>
      </div>

      <!-- <b-button variant="dark" v-if="type == 'create'" @click="registVideo"
        >등록</b-button
      >
      <b-button variant="dark" v-if="type == 'update'" @click="updateVideo"
        >수정</b-button
      > -->
      <button v-if="type == 'create'" @click="registVideo">등록</button>
      <button v-if="type == 'update'" @click="updateVideo">수정</button>
      <b-button variant="dark" v-if="type == 'update'" @click="moveList"
        >삭제</b-button
      >
      <button @click="moveList">목록</button>
    </div>
  </div>
</template>

<script>
// import { mapGetters } from "vuex";
import { mapState } from "vuex";

export default {
  name: "WriteForm",
  props: {
    type: String,
  },
  data() {
    return {
      video_board_title: "",
      youtube_url: "",
      video_board_content: "",
    };
  },
  created() {
    if (this.type == "update") {
      this.video_board_title = this.video.video_board_title;
      this.youtube_url = this.video.youtube_url;
      this.video_board_content = this.video.video_board_content;
    }
  },
  methods: {
    async registVideo() {
      if (
        this.video_board_title == "" ||
        this.youtube_url == "" ||
        this.video_board_content == ""
      ) {
        alert("내용을 전부 입력해주세요.");
      } else {
        const video = {
          video_board_title: this.video_board_title,
          youtube_url: this.youtube_url,
          video_board_content: this.video_board_content,
        };
        await this.$store.dispatch("registVideo", video);
        await this.$store.dispatch("getVideos");
        this.$router.push({ name: "video-list" });
      }
    },
    async updateVideo() {
      const video = {
        video_board_id: this.$route.params.video_board_id,
        video_board_title: this.video_board_title,
        youtube_url: this.youtube_url,
        video_board_content: this.video_board_content,
      };
      await this.$store.dispatch("updateVideo", video);
      this.$router.push({
        name: "video-detail",
        params: { video_board_id: video.video_board_id },
      });
    },
    moveList() {
      this.$router.push({ name: "video-list" });
    },
  },
  computed: {
    ...mapState(["video"]),
    // ...mapGetters(["video"]),
  },
};
</script>

<style>
.container {
  /* display: flex; */

  margin-top: 50px;
  width: 800px;
  font-family: "Noto Sans KR", sans-serif;
}
</style>
