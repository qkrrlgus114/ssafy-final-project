<template>
  <div class="post-list">
    <div class="post">
      <div class="id">{{ index }}</div>
      <!-- <div class="title">{{ crew_board_post_title }}</div> -->

      <div class="title">
        <router-link
          :to="{
            name: 'crew-board-detail',
            params: { crew_board_post_id: crew_board_post_id },
          }"
          class="video-link"
        >
          {{ crew_board_post_title }}
        </router-link>
      </div>
      <div class="writer">{{ crew_board_post_writer }}</div>
      <div class="cnt">{{ crew_board_post_view_cnt }}</div>
      <div class="date">{{ crew_board_post_reg_date }}</div>
      <div
        class="trashicon"
        v-if="isCurrentUser"
        @click="deleteReview"
        variant="dark"
      ></div>
    </div>
    <hr />
    <!-- <tr>
      <td class="th-num">{{ index }}</td>
      <td class="th-title">
        <router-link
          :to="{
            name: 'crew-board-detail',
            params: { crew_board_post_id: crew_board_post_id },
          }"
          class="video-link"
        >
          {{ crew_board_post_title }}
        </router-link>
      </td>
      <td class="th-view">{{ crew_board_post_writer }}</td>
      <td class="th-view">{{ crew_board_post_view_cnt }}</td>
      <td class="th-like">{{ crew_board_post_reg_date }}</td>
    </tr> -->
  </div>
</template>

<script>
export default {
  name: "ListRow",
  props: {
    index: String,
    crew_board_post_id: String,
    crew_id: String,
    crew_board_post_title: String,
    crew_board_post_writer: Number,
    crew_board_post_content: Number,
    crew_board_post_img: String,
    crew_board_post_reg_date: String,
    crew_board_post_view_cnt: Number,
  },
  methods: {},
};
</script>

<style scoped>
.post-list {
  text-align: center;
}
.post {
  font-size: 15px;
  font-family: "Noto Sans KR", sans-serif;
  display: flex;
  justify-content: space-around;
  align-content: center;
}
.post:hover {
  background-color: #e1f6fa;
}

.id {
  width: 80px;
  text-align: center;
}
.title {
  width: 320px;
}

.writer {
  width: 180px;
}
.cnt {
  /* font-size: 13px; */
  align-items: center;
  width: 80px;
}

.date {
  font-size: 13px;
  align-items: center;
  width: 130px;
}

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
  text-decoration: none;
}
</style>
