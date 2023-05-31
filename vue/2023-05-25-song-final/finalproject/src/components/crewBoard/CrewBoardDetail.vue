<template>
  <div>
    <BoardPostViewDetail></BoardPostViewDetail>
  </div>
</template>

<script>
import BoardPostViewDetail from "@/components/crewBoard/include/BoardPostViewDetail.vue";

export default {
  name: "Board_Post_detail_view",
  components: {
    BoardPostViewDetail,
  },
  async created() {
    // await this.$store.dispatch(
    //   "getBoardPost",
    //   this.$route.params.crew_board_post_id
    // );
    // await this.$store.dispatch("getReviews", this.$route.params.video_board_id);s
  },
};
</script>

<style></style>
