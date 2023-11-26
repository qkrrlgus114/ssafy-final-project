<template>
  <div class="video-post">
    <ViewDetail></ViewDetail>
    <VideoReviewList></VideoReviewList>
  </div>
</template>

<script>
import ViewDetail from "@/components/video/include/ViewDetail.vue";
import VideoReviewList from "@/components/video/VideoReviewList.vue";

export default {
  name: "Video_detail_view",
  components: {
    ViewDetail,
    VideoReviewList,
  },
  async created() {
    await this.$store.dispatch("getVideo", this.$route.params.video_board_id);
    await this.$store.dispatch("getReviews", this.$route.params.video_board_id);
  },
};
</script>

<style>
.video-post {
  display: flex;
  /* width: 800px; */
  flex-direction: column;
  justify-content: center;
  align-content: center;
}
</style>
