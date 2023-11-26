<template>
  <div>
    <div class="card align-middle" style="width: 25rem">
      <div class="card-body text-center">
        <h2 class="card-title" style="color: #f58b34">
          <img src="@/assets/logo-removebg-preview.png" alt="로  고" />
          <h3 class="main-title">비밀번호 찾기</h3>
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
            for="user_name"
            style="
              position: absolute;
              top: -25px;
              left: 0;
              font-size: 12px;
              color: #888888;
            "
            >이름</label
          >
          <input
            type="text"
            name="user_name"
            id="user_name"
            class="form-control"
            placeholder="이름"
            required
            autofocus
            v-model="user_name"
            @blur="validateName"
          />
        </div>
        <br />
        <div class="form-group" style="position: relative">
          <label
            for="user_phone_number"
            style="
              position: absolute;
              top: -25px;
              left: 0;
              font-size: 12px;
              color: #888888;
            "
            >휴대폰 번호</label
          >
          <input
            type="text"
            name="user_phone_number"
            id="user_phone_number"
            class="form-control"
            placeholder="휴대폰 번호(- 없이 입력)"
            required
            v-model="user_phone_number"
            @blur="validatePhoneNumber"
          />
        </div>
        <br />
        <div class="form-group" style="position: relative">
          <label
            for="user_email"
            style="
              position: absolute;
              top: -25px;
              left: 0;
              font-size: 12px;
              color: #888888;
            "
            >이메일</label
          >
          <input
            type="email"
            name="user_email"
            id="user_email"
            class="form-control"
            placeholder="이메일"
            required
            autofocus
            v-model="user_email"
            @blur="validateEmail"
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
          <b-button variant="dark" @click="sendEmail"
            >임시 비밀번호 재발급</b-button
          >
        </div>
      </div>
      <div class="card-footer text-center">
        <a href="#" @click.prevent="searchId">아이디 찾기</a> |
        <a href="#" @click.prevent="moveLogin">로그인</a> |
        <a href="#" @click.prevent="moveRegist">회원가입</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "setPass",
  data() {
    return {
      user_id: "",
      user_name: "",
      user_email: "",
      user_phone_number: "",
      showCheck: false,
      checkText: "",
      checkColor: "",
    };
  },
  methods: {
    searchId() {
      this.$router.push({ name: "search-id" });
    },
    moveRegist() {
      this.$router.push({ name: "user-regist" });
    },
    moveLogin() {
      this.$router.push({ name: "user-login" });
    },
    async sendEmail() {
      if (
        this.user_id === "" ||
        this.user_name === "" ||
        this.user_email === "" ||
        this.user_phone_number === ""
      ) {
        alert("모든 내용을 입력해주세요");
      } else {
        const user = {
          user_id: this.user_id,
          user_name: this.user_name,
          user_email: this.user_email,
          user_phone_number: this.user_phone_number,
        };

        await this.$store.dispatch("sendEmail", user);
      }
    },
    validateName() {
      if (this.user_name === "") {
        this.showCheck = true;
        this.checkText = "이름을 입력해주세요.";
        this.checkColor = "red";
      } else {
        this.showCheck = false;
      }
    },
    validatePhoneNumber() {
      if (this.user_phone_number === "") {
        this.showCheck = true;
        this.checkText = "휴대폰 번호를 입력해주세요.";
        this.checkColor = "red";
      } else {
        this.showCheck = false;
      }
    },
    validateEmail() {
      if (this.user_email === "") {
        this.showCheck = true;
        this.checkText = "이메일을 입력해주세요.";
        this.checkColor = "red";
      } else {
        this.showCheck = false;
      }
    },
    validateId() {
      if (this.user_id === "") {
        this.showCheck = true;
        this.checkText = "아이디를 입력해주세요.";
        this.checkColor = "red";
      } else {
        this.showCheck = false;
      }
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
  color: #000000;
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
