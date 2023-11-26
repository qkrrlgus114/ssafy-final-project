<template>
  <tr>
    <td class="th-num">{{ index }}</td>
    <td class="th-thum">
      <img :src="getThumbnailUrl(youtube_url)" alt="썸네일" />
    </td>
    <td class="th-title">
      <router-link
        :to="{
          name: 'video-detail',
          params: { video_board_id: video_board_id },
        }"
        class="video-link"
      >
        {{ video_board_title }}
      </router-link>
    </td>
    <td class="th-view">{{ video_board_view_cnt }}</td>
    <td class="th-like">{{ video_board_like_cnt }}</td>
    <td class="th-date">{{ video_board_reg_date }}</td>
  </tr>
</template>

<script>
export default {
  name: "ListRow",
  props: {
    index: String,
    video_board_id: String,
    video_board_title: String,
    video_board_content: String,
    video_board_view_cnt: Number,
    video_board_like_cnt: Number,
    video_board_reg_date: String,
    youtube_url: String,
  },
  methods: {
    getThumbnailUrl(youtubeUrl) {
      const videoId = youtubeUrl.split("v=")[1];
      return `https://img.youtube.com/vi/${videoId}/hqdefault.jpg`;
    },
  },
};
</script>

<style scoped>
.board-table .th-num,
.board-table .th-thum,
.board-table .th-title,
.board-table .th-view,
.board-table .th-like,
.board-table .th-date {
  text-align: center;
}

.video-link {
  color: #333;
  display: inline-block;
  line-height: 1.4;
  word-break: break-all;
  vertical-align: middle;
}
</style>
