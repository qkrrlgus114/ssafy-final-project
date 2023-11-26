<template>
  <div class="post-container">
    <div class="crew-detail-card">
      <b-card>
        <div class="post-header">
          <!-- 크루 이름 -->
          <h2 class="post-title">{{ crew.crew_name }}</h2>
          <hr />
          <div class="post-meta">
            <div class="post-meta-item">
              <i class="fa-solid fa-user"></i>
              <!-- 크루 리더 -->
              <span>크루 리더: {{ crew.crew_leader }}</span>
            </div>
            <div class="post-meta-item">
              <i class="fa-solid fa-calendar-day"></i>
              <!-- 크루 생성 날짜 -->
              <span>
                모집 시작일: {{ formatDate(crew.crew_create_date) }}
                <i class="fa-solid fa-tag"></i>
                {{ crew.crew_thema }}
              </span>
            </div>
          </div>
        </div>
        <div class="post-content">
          <div class="post-image">
            <img :src="getImageUrl(crew.crew_img)" alt="크루 이미지" />
          </div>
          <div class="post-description">
            <h3 class="post-subtitle">크루 설명</h3>
            <p class="post-text">{{ crew.crew_content }}</p>
          </div>
        </div>
        <!-- 크루 파트 추가 -->
        <div class="post-footer">
          <!-- 현재 참가인원 / 최대 모집인원 추가 -->
          <p class="post-participants">
            크루 인원: {{ crew.crew_current_members }} /
            {{ crew.crew_max_members }}
          </p>
        </div>

        <div class="button-container">
          <b-button
            variant="dark"
            style="margin: 5px; width: 70px"
            @click="joinCrew"
          >
            가입
          </b-button>
          <b-button
            variant="dark"
            style="margin: 5px; width: 70px"
            @click="moveCrewList"
          >
            목록
          </b-button>
        </div>
      </b-card>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "crew-detail",
  computed: {
    ...mapState(["crew", "loginUser"]),
  },
  created() {
    this.fetchCrewDetail();
  },
  methods: {
    joinCrew() {
      const crewId = this.crew.crew_id;
      const userId = this.loginUser.user_id;
      this.$store.dispatch("checkCrew", { crewId, userId });
    },
    moveCrewList() {
      this.$router.push({ name: "crew-search" });
    },
    fetchCrewDetail() {
      const crewId = this.$route.params.crew_id;
      this.$store.dispatch("getCrew", crewId);
    },
    formatDate(date) {
      const options = { year: "numeric", month: "long", day: "numeric" };
      return new Date(date).toLocaleDateString(undefined, options);
    },
    getImageUrl(image) {
      if (image) {
        return `http://localhost:9999/upload/${image}`;
      } else {
        return require("@/assets/crew_default.jpg");
      }
    },
  },
};
</script>

<style scoped>
.crew-detail-card {
  width: 800px;
}

.button-container {
  display: flex;
  justify-content: center;
  /* margin: 20px; */
}

.post-container {
  max-width: 800px;
  margin: 200px auto;
  padding: 20px;
  background-color: #fff;
  /* box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); */
  font-family: "Noto Sans KR", sans-serif;
}

.post-header {
  margin-bottom: 20px;
}

.post-title {
  font-size: 30px;
  font-weight: bold;
  margin-bottom: 10px;
}

.post-meta {
  display: flex;
  align-items: center;
  color: #777;
  font-size: 14px;
}

.post-meta-item {
  display: flex;
  align-items: center;
  margin-right: 10px;
}

.post-meta-item i {
  margin-right: 5px;
}

.post-content {
  display: flex;
}

.post-image img {
  max-width: 300px;
  height: auto;
  margin-right: 20px;
}

.post-description {
  flex-grow: 1;
}

.post-subtitle {
  font-size: 20px;
  margin-bottom: 10px;
}

.post-text {
  font-size: 16px;
  line-height: 1.6;
}

.post-footer {
  margin-top: 20px;
}

.post-part-list {
  list-style-type: disc;
  margin-left: 20px;
  padding-left: 10px;
}

.post-part-item {
  font-size: 16px;
  line-height: 1.6;
  margin-bottom: 5px;
}

.post-participants {
  font-size: 16px;
  line-height: 1.6;
  margin-top: 10px;
}

.fa-tag {
  margin-left: 5px;
  color: #777;
}

.post-buttons {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

.post-button {
  padding: 10px 20px;
  border-radius: 5px;
  background-color: #4c8bf5;
  color: rgb(0, 0, 0);
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
  margin-right: 10px;
}

.post-button:hover {
  background-color: #3a6fb2;
}

.join-button {
  background-color: #f0f0f0;
}

.join-button:hover {
  background-color: #7e839e;
}

.crew-list-button {
  background-color: #f0f0f0;
}

.crew-list-button:hover {
  background-color: #7e839e;
}
</style>
