<template>
  <div>
    <div class="login-form">
      <div class="card align-middle" style="width: 25rem">
        <div class="card-body text-center">
          <h2 class="card-title" style="color: #f58b34">
            <img src="@/assets/logo-removebg-preview.png" alt="로  고" />
            <h3 class="main-title">로그인</h3>
          </h2>
          <br />
          <div class="form-group" style="position: relative">
            <label
              for="user_id"
              style="
                position: absolute;
                top: -25px;
                left: 0;
                font-size: 12px;
                color: #888888;
              "
              >아이디</label
            >
            <input
              type="text"
              name="user_id"
              id="user_id"
              class="form-control"
              placeholder="아이디"
              required
              autofocus
              v-model="user_id"
              @blur="validateId"
            />
          </div>
          <br />
          <div class="form-group" style="position: relative">
            <label
              for="user_pass"
              style="
                position: absolute;
                top: -25px;
                left: 0;
                font-size: 12px;
                color: #888888;
              "
              >비밀번호</label
            >
            <input
              type="password"
              name="user_pass"
              id="user_pass"
              class="form-control"
              placeholder="비밀번호"
              required
              v-model="user_pass"
              @blur="validatePassword"
              @keyup.enter="login"
            />
          </div>
          <br />
          <p
            class="check"
            id="check"
            v-show="showCheck"
            :style="{ color: checkColor }"
          >
            {{ checkText }}
          </p>
          <br />
          <div class="d-grid gap-2">
            <b-button variant="dark" @click="login">로그인</b-button>
          </div>
        </div>
        <div class="card-footer text-center">
          <a href="" @click.prevent="searchId">아이디 찾기</a> |
          <a href="" @click.prevent="searchPass">비밀번호 찾기</a> |
          <a href="" @click.prevent="moveRegist">회원가입</a>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "LoginForm",
  data() {
    return {
      user_id: "",
      user_pass: "",
      showCheck: false,
      checkText: "",
      checkColor: "",
    };
  },
  methods: {
    validateId() {
      if (this.user_id === "") {
        this.showCheck = true;
        this.checkText = "아이디를 입력해주세요.";
        this.checkColor = "red";
      } else {
        this.showCheck = false;
      }
    },
    validatePassword() {
      if (this.user_pass === "") {
        this.showCheck = true;
        this.checkText = "비밀번호를 입력해주세요.";
        this.checkColor = "red";
      } else {
        this.showCheck = false;
      }
    },
    async login() {
      if (this.user_id === "" || this.user_pass === "") {
        alert("전부 입력해주세요");
        return;
      }
      let user = {
        user_id: this.user_id,
        user_pass: this.user_pass,
      };

      console.log(user);
      await this.$store.dispatch("setLoginUser", user);
      this.moveHome();
    },
    moveRegist() {
      this.$router.push({ name: "user-regist" });
    },
    searchId() {
      // this.$router.push({ name: "search-userinfo" });
      this.$router.push({ name: "search-id" });
    },
    searchPass() {
      // this.$router.push({ name: "search-userinfo" });
      this.$router.push({ name: "search-pass" });
    },
    moveHome() {
      this.$router.push({ name: "Home" });
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

.links {
  text-align: center;
  margin-bottom: 10px;
}

a {
  color: #060807;
  text-decoration: none;
}
.text2 {
  color: blue;
}
.main-title {
  color: black;
  font-size: 25px;
  margin-top: 20px;
}
</style>
