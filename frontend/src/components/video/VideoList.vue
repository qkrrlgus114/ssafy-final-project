<template>
  <div>
    <div class="searchbar">
      <div class="label"></div>
      <div>
        <input
          class="input"
          id="search"
          type="text"
          placeholder="키워드를 입력해주세요"
          v-model="word"
          @keyup.enter="searchVideo"
        />
      </div>

      <div class="search-button" @click="searchVideo">
        <i class="fa-solid fa-magnifying-glass"></i>
      </div>

      <!-- <b-button class="search-button" @click="searchVideo">
        <i class="fa-solid fa-magnifying-glass" style="color: #ffffff"></i>
      </b-button> -->
    </div>
    <hr />
    <div class="list-container">
      <div class="admin-button">
        <b-button v-if="adminUser" @click="moveRegist">영상 등록</b-button>
      </div>
      <div v-if="videos">
        <div>
          <b-container fluid>
            <b-row>
              <b-col
                v-for="(video, index) in videos"
                :key="index"
                class="card"
                cols="4"
                sm="6"
              >
                <div class="card-element">
                  <b-card
                    style="max-width: 20rem"
                    :title="video.video_board_title"
                    :img-src="getThumbnailUrl(video.youtube_url)"
                    img-height="210px"
                    img-alt="썸네일"
                    img-top
                    title-class="card-title"
                    body-class="card-body"
                  >
                    <div class="content-container">
                      <div class="card-content">
                        {{ video.video_board_content }}
                      </div>
                      <div class="card-add">
                        <div class="card-option-container">
                          <div class="option-container">
                            <!-- 좋아요 -->
                            <div class="card-option">
                              <div class="button-element">
                                <i
                                  class="fa-solid fa-heart fa-lg"
                                  style="color: #ff0a16"
                                ></i>
                              </div>
                              <div>
                                <div class="button-element">
                                  {{ video.video_board_like_cnt }}
                                </div>
                              </div>
                            </div>

                            <!-- 조회수 -->
                            <div class="card-option2">
                              <div class="button-element2">
                                <b-icon
                                  icon="eye-fill"
                                  font-scale="1.5"
                                ></b-icon>
                              </div>
                              <!-- <div> -->
                              <div class="button-element">
                                {{ video.video_board_view_cnt }}
                              </div>
                              <!-- </div> -->
                            </div>
                          </div>
                        </div>

                        <div class="card-button">
                          <!-- <div
                        class="button1"
                        @click="movrd-addeVideoDetail(video.video_board_id)"
                      >
                        <i class="fa-solid fa-share" style="color: #0058f0"></i>
                      </div> -->

                          <b-button
                            class="go-button"
                            variant="primary"
                            @click="moveVideoDetail(video.video_board_id)"
                          >
                            <i
                              class="fa-solid fa-magnifying-glass fa-la"
                              style="color: #ffffff"
                            ></i>
                          </b-button>

                          <!-- <div class="card-button">
                          <b-button
                            class="go-button"
                            variant="primary"
                            @click="moveVideoDetail(video.video_board_id)"
                            >GO</b-button
                          >
                        </div> -->
                        </div>
                      </div>
                    </div>
                  </b-card>
                </div>
              </b-col>
            </b-row>
          </b-container>
        </div>
      </div>
      <div v-else>
        <div class="notice">등록된 영상이 없습니다.</div>
      </div>
    </div>
  </div>
</template>
<script>
// import ListRow from "@/components/video/include/ListRow.vue";
import { mapState } from "vuex";
export default {
  name: "videoList",
  data() {
    return {
      word: "",
      searchVideos: null,
    };
  },
  methods: {
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
    moveRegist() {
      this.$router.push({ name: "video-create" });
    },
    searchVideo() {
      const word = this.word;
      if (word == "") this.$store.dispatch("getVideos");
      else this.$store.dispatch("searchVideo", word);
    },
    getThumbnailUrl(youtubeUrl) {
      const videoId = youtubeUrl.split("v=")[1];
      return `https://img.youtube.com/vi/${videoId}/hqdefault.jpg`;
    },
    moveVideoDetail(video_board_id) {
      this.$router.push({
        name: "video-detail",
        params: { video_board_id: video_board_id },
      });
    },
  },
  components: {
    // ListRow,
  },
  computed: {
    ...mapState(["loginUser"]),
    ...mapState(["videos"]),
    // 유저가 어드민인지 판단
    adminUser() {
      return this.isAdmin();
    },
  },
  created() {
    this.$store.dispatch("getVideos");
  },
};
</script>
<style scoped>
.list-container {
  display: flex;
  flex-direction: column;
}

.card-add {
  display: flex;
  flex-direction: column;
  justify-content: right;
}
.card-option-container {
  display: flex;
  justify-content: right;
}

.option-container {
  display: flex;
  justify-content: space-around;
  align-content: center;
  /* margin: 5px; */
}

.button-element {
  /* margin-top: 5px; */
  margin-left: 10px;
  display: flex;
  justify-content: center;
  align-content: flex-end;
}
.button-element2 {
  /* margin-top: 5px; */
  margin-left: 10px;
  display: flex;
  justify-content: center;
  align-content: flex-end;
  /* margin-bottom: 7px; */
}

.card-option {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.card-option2 {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.content-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.card-content {
  border: 1px;
  margin: 2px;
  height: 100px;
  overflow: hidden;
  text-overflow: ellipsis;
}
/* .card-add {
  margin: 2px;
  margin-left: 10px;
} */

.admin-button {
  display: flex;
  justify-content: right;
  font-size: 18px;
  margin-top: 20px;
  margin-right: 120px;
  margin-bottom: 20px;
}
.notice {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 20px;
  text-align: center;
}
.tabs {
  font-family: "Jua", sans-serif;
  font-size: 20px;
}
.tab {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 20px;
}
.searchbar {
  height: 80px;
  font-family: "Noto Sans KR", sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 30px;
}
.label {
  height: 50px;
  font-size: 25px;
  display: flex;
  align-items: center;
}
.input {
  font-size: 15px;
  height: 35px;
  width: 500px;
  margin-left: 10px;
  margin-right: 10px;
}
.search-button {
  cursor: pointer;
}
.card {
  margin: 10px;
  padding: 0;
  max-width: 20rem;
  background-color: #fafafa;
}
.card-title {
  font-family: "Do Hyeon";
  font-size: 33px;
  white-space: nowrap; /* 텍스트를 한 줄로 표시합니다. */
  overflow: hidden; /* 요소 영역을 넘어간 내용을 숨깁니다. */
  text-overflow: ellipsis; /* 너무 긴 텍스트는 줄임표로 표시합니다. */
}
.card-text {
  white-space: nowrap; /* 텍스트를 한 줄로 표시합니다. */
  overflow: hidden; /* 요소 영역을 넘어간 내용을 숨깁니다. */
  text-overflow: ellipsis; /* 너무 긴 텍스트는 줄임표로 표시합니다. */
}
.card-body {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 15px;
}
.card-button {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 15px;
  font-weight: bold;
  display: flex;
  justify-content: right;
  margin-top: 5px;
}
.button1 {
  margin-right: 15px;
}
.button2 {
  width: 45px;
  margin-right: 15px;
}
.go-button {
  width: 50px;
}
.row {
  display: flex;
  justify-content: center;
}
</style>
