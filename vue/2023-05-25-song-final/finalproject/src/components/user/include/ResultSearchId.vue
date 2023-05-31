<template>
  <div>
    <div>
      <div class="card align-middle" style="width: 25rem">
        <div class="card-body text-center">
          <h2 class="card-title" style="color: #f58b34">
            <img src="@/assets/logo-removebg-preview.png" alt="로  고" />
          </h2>
          <h4>당신의 아이디는 {{ user.user_id }} 입니다.</h4>
          <br />
          <div class="d-flex justify-content-center">
            <b-button variant="dark" @click="moveLogin">로그인</b-button>
          </div>
        </div>
        <div class="card-footer text-center">
          <a href="#" @click.prevent="searchPass">비밀번호 찾기</a> |
          <a href="#" @click.prevent="moveLogin">로그인</a> |
          <a href="#" @click.prevent="moveRegist">회원가입</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "ResultSearchId",
  computed: {
    ...mapState(["user"]),
  },
  methods: {
    searchPass() {
      this.$router.push({ name: "search-pass" });
    },
    moveRegist() {
      this.$router.push({ name: "user-regist" });
    },
    moveLogin() {
      this.$router.push({ name: "user-login" });
    },
  },
};
</script>

<style scoped>
html {
  height: 100%;
}

body {
  width: 100%;
  height: 100%;
  margin: 0;
  padding-top: 200px;
  padding-bottom: 40px;
  font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  background-repeat: no-repeat;
}

.card {
  margin: 0 auto; /* Added */
  float: none; /* Added */
  margin-bottom: 10px; /* Added */
  margin-top: 200px;
}

#btn-Yes {
  background-color: #7274e9;
  border: none;
}

.form-signin .form-control {
  position: relative;
  height: auto;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  padding: 10px;
  font-size: 16px;
}
.card-title {
}
.links {
  text-align: center;
  margin-bottom: 10px;
}

a {
  color: #13773a;
  text-decoration: none;
}
.text2 {
  color: blue;
}
</style>
