<template>
  <div class="board-container">
    <div class="page-title">
      <div class="container">
        <h3>{{ crew_name }} 전용 게시판</h3>
      </div>
    </div>
    <!-- <button @click="moveRegist">동영상 등록</button> -->
    <div v-if="posts.length">
      <div>
        <div class="regist-button">
          <b-button variant="dark" @click="registPost">게시글 작성</b-button>
        </div>
        <b-card class="post-list">
          <div class="list-list">
            <div class="list-title">
              <div class="id">번호</div>
              <div class="title">제목</div>
              <div class="writer">작성자</div>
              <div class="cnt">조회수</div>
              <div class="date-title">작성일</div>
            </div>
            <hr />
            <b-card-body>
              <tbody style="display: flex; flex-direction: column-reverse">
                <CrewListRow
                  v-for="(post, index) in posts"
                  :key="index"
                  :index="`${index + 1}`"
                  :crew_board_post_id="`${post.crew_board_post_id}`"
                  :crew_id="post.crew_id"
                  :crew_board_post_title="post.crew_board_post_title"
                  :crew_board_post_writer="post.crew_board_post_writer"
                  :crew_board_post_content="post.crew_board_post_content"
                  :crew_board_post_img="post.crew_board_post_img"
                  :crew_board_post_reg_date="post.crew_board_post_reg_date"
                  :crew_crew_board_post_like_cnt="post.crew_board_post_like_cnt"
                  :crew_board_post_view_cnt="post.crew_board_post_view_cnt"
                >
                </CrewListRow>
              </tbody>
            </b-card-body>
          </div>
        </b-card>
      </div>
    </div>
    <div v-else class="no-content">
      <div class="main-notice">등록된 게시글이 없습니다.</div>
      <div class="regist-button">
        <b-button variant="dark" @click="registPost">게시글 작성</b-button>
      </div>
    </div>
  </div>
</template>

<script>
import CrewListRow from "@/components/crewBoard/include/CrewListRow.vue";
import { mapState } from "vuex";
export default {
  name: "CrewBoard",
  components: {
    CrewListRow,
  },
  computed: {
    ...mapState(["crew"]),
    ...mapState(["posts"]),
    crew_name() {
      return this.crew.crew_name;
    },
  },
  created() {
    this.$store.dispatch("getCrew", this.$route.params.crew_id);
    this.$store.dispatch("getCrewBoardPost", this.$route.params.crew_id);
  },
  methods: {
    registPost() {
      this.$router.push({ name: "post-create" });
    },
  },
};
</script>

<style scoped>
.regist-button {
  display: flex;
  justify-content: right;
  margin-bottom: 20px;
}
.no-content {
  display: flex;
  flex-direction: column;
}
.post:hover {
  background-color: #e1f6fa;
}
.id {
  width: 80px;
}
.title {
  width: 320px;
}

.writer {
  border: 1;
  width: 180px;
}
.cnt {
  /* font-size: 13px; */
  align-items: center;
  width: 80px;
}
.date-title {
  /* font-size: 13px; */
  /* align-items: center; */
  width: 140px;
}
/* Add custom styles here */
.board-container {
  width: 1000px;
  margin: auto;
  text-align: center;
}
.list-title {
  text-align: center;
  font-weight: bolder;
  display: flex;
  justify-content: space-around;
}
.main-notice {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 20px;
  text-align: center;
  margin-top: 50px;
}

/* .post-list{
  display: flex;
  justify-content: center;
  margin-bottom: 50px;
} */

#board-list {
  position: relative;
}

#board-list button {
  position: absolute;
  right: 20px;
  bottom: 20px;
}

.notice {
  background-color: #f9f7f9;
  padding: 80px 0;
}

.page-title {
  margin-bottom: 60px;
  text-align: center;
}

.page-title {
  font-size: 25px;
  font-weight: 400;
  margin-top: 50px;
}

.container {
  width: 1100px;
  /* margin: 0 auto; */
  font-family: "Noto Sans KR", sans-serif;
}

/* 
#board-search .search-window {
  padding: 15px 0;
}

#board-search .search-window .search-wrap {
  margin: 0 auto;
  max-width: 564px;
}

#board-search .search-window .search-wrap input {
  height: 40px;
  width: 100%;
  font-size: 14px;
  padding: 7px 14px;
  border: 1px solid #ccc;
}

#board-search .search-window .search-wrap input:focus {
  border-color: #333;
  outline: 0;
  border-width: 1px;
}

.board-table {
  font-size: 13px;
  width: 100%;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

.board-table a {
  color: #333;
  display: inline-block;
  line-height: 1.4;
  word-break: break-all;
  vertical-align: middle;
}

.board-table a:hover {
  text-decoration: underline;
}

.board-table th {
  text-align: center;
}

.board-table .th-num {
  width: 100px;
  text-align: center;
}

.board-table .th-date {
  width: 200px;
}

.board-table th,
.board-table td {
  padding: 14px 0;
}

.board-table tbody td {
  border-top: 1px solid #e7e7e7;
  text-align: center;
}

.board-table tbody th {
  padding-left: 28px;
  padding-right: 14px;
  border-top: 1px solid #e7e7e7;
  text-align: left;
}

.board-table tbody th p {
  display: none;
}

.btn {
  display: inline-block;
  padding: 0 30px;
  font-size: 15px;
  font-weight: 400;
  background: transparent;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
  touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  border: 1px solid transparent;
  text-transform: uppercase;
  -webkit-border-radius: 0;
  -moz-border-radius: 0;
  border-radius: 0;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  -ms-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-dark:hover,
.btn-dark:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}

.no-posts {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 80px;
  text-align: center;
}

.no-posts h2 {
  font-size: 24px;
  margin-bottom: 20px;
}

.no-posts button {
  display: inline-block;
  padding: 10px 30px;
  font-size: 16px;
  font-weight: 400;
  background: #555;
  color: #fff;
  text-align: center;
  text-transform: uppercase;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.no-posts button:hover {
  background-color: #373737;
}


* {
  list-style: none;
  text-decoration: none;
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

.clearfix:after {
  content: "";
  display: block;
  clear: both;
}

.container {
  width: 1100px;
  margin: 0 auto;
}

.blind {
  position: absolute;
  overflow: hidden;
  clip: rect(0 0 0 0);
  margin: -1px;
  width: 1px;
  height: 1px;
} */
</style>
