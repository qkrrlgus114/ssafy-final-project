<template>
  <div>
    <div>
      <b-tabs class="tabs" align="center">
        <b-tab class="tab" title="마이 크루" @click="moveMyCrew"> </b-tab>
        <b-tab class="tab" title="크루 검색" active> </b-tab>
        <b-tab class="tab" title="크루 등록" @click="moveCrewCreate"> </b-tab>
      </b-tabs>
    </div>

    <div class="searchbar">
      <div class="label">
        <!-- <label for="search">크루 검색</label> -->
      </div>
      <div>
        <input
          class="input"
          id="search"
          type="text"
          placeholder="키워드를 입력해주세요"
          v-model="word"
          @keyup.enter="searchCrew"
        />
      </div>
      <b-button class="search-button" @click="searchCrew">
        <i class="fa-solid fa-magnifying-glass" style="color: #ffffff"></i>
      </b-button>
    </div>
    <div v-if="crews">
      <div>
        <b-container fluid>
          <b-row>
            <b-col
              cols="4"
              sm="6"
              v-for="(crew, index) in reversedCrews"
              :key="index"
              class="card"
            >
              <!--     :img-src="`http://localhost:9999/upload/${crew.crew_img}`"    -->
              <div class="card-element">
                <b-card
                  style="height: 400px; max-width: 20rem"
                  :title="crew.crew_name"
                  :img-src="
                    crew.crew_img
                      ? `http://localhost:9999/upload/${crew.crew_img}`
                      : require('@/assets/crew_default.jpg')
                  "
                  img-height="155px"
                  img-alt="크루 사진"
                  img-top
                  title-class="card-title"
                  body-class="card-body"
                >
                  <b-card-text>
                    리더: {{ crew.crew_leader }} <br />테마: {{ crew.crew_thema
                    }}<br />소개: {{ crew.crew_content }}<br />크루 인원:
                    {{ crew.crew_current_members }} /
                    {{ crew.crew_max_members }}
                  </b-card-text>
                  <div class="card-button">
                    <b-button
                      class="button"
                      href="#"
                      variant="warning"
                      @click="detailCrew(index)"
                      >크루정보</b-button
                    >
                    <b-button
                      class="button"
                      href="#"
                      variant="warning"
                      @click="joinCrew(index)"
                      >크루신청</b-button
                    >
                  </div>
                </b-card>
              </div>
            </b-col>
          </b-row>
        </b-container>
      </div>
    </div>
    <div v-else>
      <h2>모집중인 크루가 없습니다.</h2>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

// import CrewListRow from "@/components/crew/include/CrewListRow.vue";
export default {
  name: "crewSearch",
  data() {
    return {
      word: "",
      searchCrews: null,
      crew_id: "",
    };
  },
  components: {},
  methods: {
    searchCrew() {
      const word = this.word;
      console.log(word);
      if (word == "") this.$store.dispatch("getCrews");
      else this.$store.dispatch("searchCrews", word);
    },
    joinCrew(index) {
      const reverse_index = this.crews.length - 1 - index;
      const crewId = this.crews[reverse_index].crew_id;
      const userId = this.loginUser.user_id;
      this.$store.dispatch("checkCrew", { crewId, userId });
    },
    detailCrew(index) {
      const reverse_index = this.crews.length - 1 - index;
      // console.log(index);
      // console.log(reverse_index);
      const crewId = this.crews[reverse_index].crew_id;
      this.$router.push({ name: "crew-detail", params: { crew_id: crewId } });
    },
    async moveMyCrew() {
      const user_id = this.loginUser.user_id;
      this.$router.push({ name: "my-crew", params: { user_id: user_id } });
    },

    moveCrewCreate() {
      this.$router.push({ name: "crew-create" });
    },
  },
  computed: {
    ...mapState(["crews"]),
    ...mapState(["loginUser"]),
    reversedCrews() {
      return [...this.crews].reverse();
    },
  },
  async created() {
    await this.$store.dispatch("getCrews");
  },
};
</script>

<style scoped>
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
  margin-top: 10px;
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
/* .card-group {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin: 5px;
}
.card-container {
  display: flex;
  flex-wrap: wrap;
  margin: 10px;
  justify-content: center;
} */
/* .tmp {
  display: flex;
  justify-content: center;
} */
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
  display: flex;
  font-family: "Noto Sans KR", sans-serif;
  font-size: 20px;
  font-weight: bold;
}
.button {
  margin-right: 15px;
}
.row {
  display: flex;
  justify-content: center;
}
</style>
