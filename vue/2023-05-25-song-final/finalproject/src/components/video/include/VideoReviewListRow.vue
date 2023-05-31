<template>
  <div>
    <div class="review-content">
      <div class="id">{{ user_id }}</div>
      <div class="content">{{ review_content }}</div>
      <div class="date">{{ review_reg_date }}</div>
      <div
        class="trashicon"
        v-if="isCurrentUser"
        @click="deleteReview"
        variant="dark"
      >
        <i class="fa-solid fa-trash-can fa-lg"></i>
      </div>

      <!-- <b-button
        class="like-button"
        variant="outline-light"
        @click="cancleLikeUpdate"
      >
        <i class="fa-solid fa-heart" style="color: #ff0040"></i>
      </b-button> -->

      <!-- <b-button v-if="isCurrentUser" @click="deleteReview" variant="dark"
        >삭제</b-button
      > -->
    </div>
    <hr />
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "ReviewListRow",
  data() {
    return {
      reg_date: "",
    };
  },
  computed: {
    ...mapState(["reviews", "video", "loginUser"]),
    isCurrentUser() {
      return this.loginUser.user_id === this.user_id;
    },
  },
  props: {
    index: String,
    review_id: String,
    review_content: String,
    user_id: String,
    review_reg_date: String,
  },
  methods: {
    async deleteReview() {
      await this.$store.dispatch("deleteReview", this.review_id);
      setTimeout(async () => {
        await this.$store.dispatch(
          "getReviews",
          this.$route.params.video_board_id
        );
      }, 100); // 1초 후에 리뷰 목록을 다시 가져옴
    },
  },
};
</script>

<style>
.trashicon {
  cursor: pointer;
}
.review-content {
  font-size: 15px;
  font-family: "Noto Sans KR", sans-serif;
  display: flex;
  justify-content: space-around;
}
.review:hover {
  background-color: #e1f6fa;
}

.id {
  width: 80px;
}
.content {
  border: 1;
  width: 500px;
}
.date {
  font-size: 13px;
  align-items: center;
  width: 130px;
}
</style>
