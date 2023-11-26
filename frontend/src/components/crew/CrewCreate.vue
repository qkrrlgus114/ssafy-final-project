<template>
  <div>
    <div>
      <b-tabs class="tabs" align="center">
        <b-tab class="tab" title="마이 크루" @click="moveMyCrew"> </b-tab>
        <b-tab class="tab" title="크루 검색" @click="moveCrewSearch"> </b-tab>
        <b-tab class="tab" title="크루 등록" active> </b-tab>
      </b-tabs>
    </div>
    <CrewRegistForm type="create"></CrewRegistForm>
  </div>
</template>

<script>
import { mapState } from "vuex";

import CrewRegistForm from "@/components/crew/include/CrewRegistForm.vue";
export default {
  name: "create-crew",
  components: { CrewRegistForm },
  methods: {
    moveMyCrew() {
      const user_id = this.loginUser.user_id;
      this.$router.push({ name: "my-crew", params: { user_id: user_id } });
    },
    moveCrewSearch() {
      this.$router.push({ name: "crew-search" });
    },
  },
  computed: {
    ...mapState(["loginUser"]),
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
</style>
