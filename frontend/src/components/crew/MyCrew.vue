<template>
  <div>
    <div>
      <b-tabs class="tabs" align="center">
        <b-tab class="tab" title="마이 크루" active> </b-tab>
        <b-tab class="tab" title="크루 검색" @click="moveCrewSearch"> </b-tab>
        <b-tab class="tab" title="크루 등록" @click="moveCrewCreate"> </b-tab>
      </b-tabs>
    </div>
    <div v-if="crews">
      <div class="notice">가입중인 크루</div>
      <hr />
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
              <!-- :img-src="`https://picsum.photos/1024/480/?image=100`" -->
              <div class="card-element">
                <div class="card-container">
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
                      리더: {{ crew.crew_leader }} <br />테마:
                      {{ crew.crew_thema }}<br />소개: {{ crew.crew_content
                      }}<br />원: {{ crew.crew_current_members }} /
                      {{ crew.crew_max_members }}
                    </b-card-text>
                    <div class="card-button">
                      <b-button
                        class="button"
                        href="#"
                        variant="primary"
                        @click="moveCrewBoard(index)"
                        >크루 게시판</b-button
                      >
                    </div>
                  </b-card>
                </div>
              </div>
            </b-col>
          </b-row>
        </b-container>
      </div>
    </div>
    <div v-else>
      <div class="notice">가입된 크루가 없습니다.</div>
      <hr />
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

    moveCrewBoard(index) {
      const reverse_index = this.crews.length - 1 - index;
      const crew_id = this.crews[reverse_index].crew_id;
      this.$router.push({
        name: "crew-board",
        params: { crew_id: crew_id },
      });
    },
    moveCrewSearch() {
      this.$router.push({ name: "crew-search" });
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
    await this.$store.dispatch("getMyCrews", this.$route.params.user_id);
  },
};
</script>

<style scoped>
.notice {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 20px;
  text-align: center;
  margin-top: 25px;
  margin-bottom: 20px;
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
  height: 40px;
  font-family: "Noto Sans KR", sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 15px;
  margin-bottom: 0px;
}
.label {
  height: 40px;
  font-size: 25px;
  display: flex;
  align-items: center;
}
.input {
  font-size: 18px;
  height: 40px;
  width: 700px;
  margin-left: 10px;
  margin-right: 10px;
  padding: 10px;
}
.search-button {
  background-color: black;
  color: #fafafa;
  height: 40px;
  width: 80px;
}
.card-group {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin: 5px;
}
.card-container {
  /* display: flex;
  flex-wrap: wrap; */
  /* margin: 10px; */
  /* justify-content: center; */
}
.tmp {
  display: flex;
  justify-content: center;
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
  margin: 5px;
}
</style>
