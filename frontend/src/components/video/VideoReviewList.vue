<template>
  <div class="review-container">
    <!-- Review Form -->
    <div class="review-form">
      <b-card>
        <div class="review">
          <input
            class="form-control"
            type="text"
            v-model="review_content"
            placeholder="댓글 쓰기 (300자 이내)"
          />
          <b-button
            style="margin-left: 10px"
            @click="registReivew"
            variant="primary"
            @keyup.enter="registReivew"
            >등록</b-button
          >
        </div>
      </b-card>
    </div>

    <b-card class="review-list">
      <div>
        <div class="list-title">
          <div class="id">작성자</div>
          <div class="content">내용</div>
          <div class="date" style="font-size: 15px">작성일</div>
        </div>
        <hr />
        <tbody style="display: flex; flex-direction: column-reverse">
          <VideoReviewListRow
            v-for="(review, index) in reviews"
            :key="index"
            :index="`${index + 1}`"
            :review_id="`${review.review_id}`"
            :review_content="review.review_content"
            :user_id="review.user_id"
            :review_reg_date="review.review_reg_date"
          >
            <!-- <hr /> -->
          </VideoReviewListRow>
        </tbody>
      </div>
    </b-card>
  </div>
</template>

<script>
import VideoReviewListRow from "@/components/video/include/VideoReviewListRow.vue";
import { mapState } from "vuex";
export default {
  name: "ReviewForm",
  components: {
    VideoReviewListRow,
  },
  data() {
    return {
      review_content: "",
    };
  },
  computed: {
    ...mapState(["loginUser", "video", "reviews"]),
  },
  methods: {
    registReivew() {
      if (this.review_content == "") {
        alert("내용을 입력해주세요");
      } else if (this.review_content.length >= 300) {
        alert("300자 이내로 입력해주세요.");
        this.review_content = "";
      } else {
        const review = {
          user_id: this.loginUser.user_id,
          review_content: this.review_content,
          video_board_id: this.video.video_board_id,
        };
        this.review_content = "";
        this.$store.dispatch("registReview", review);
      }
    },
  },
};
</script>

<style scoped>
.list-title {
  font-weight: bolder;
  display: flex;
  justify-content: space-around;
}
.review-list {
  display: flex;
  justify-content: center;
  margin-bottom: 50px;
}
.content {
  text-align: center;
}

.form-control {
  height: 50px;
  width: 690px;
}
.review {
  display: flex;
  justify-content: space-around;
  align-content: center;
}
.review-container {
  width: 800px;
  margin: auto;
}

.review-form {
  margin-bottom: 2em;
  font-family: "Noto Sans KR", sans-serif;
  font-size: 15px;
}

/*
.review-table {
  width: 100%;
  border-collapse: collapse;
}

.review-table th {
  background-color: #f8f9fa;
  color: #343a40;
  padding: 15px;
}

.review-table td {
  padding: 10px;
} */
</style>
