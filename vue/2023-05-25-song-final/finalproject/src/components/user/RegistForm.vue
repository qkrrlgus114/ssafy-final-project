<template>
  <div>
    <div class="card align-middle" style="width: 35rem">
      <div class="card-body text-center">
        <h2 class="card-title" style="color: #f58b34">
          <img src="@/assets/logo-removebg-preview.png" alt="로고" />
          <h3 class="main-title">회원가입</h3>
          <br />
        </h2>

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
          <div class="input-group">
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
              style="flex: 1; white-space: nowrap"
            />
            <button
              variant="outline-dark"
              class="btn btn-secondary"
              @click="checkId"
            >
              중복확인
            </button>
          </div>
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
          <div class="input-group">
            <input
              type="password"
              name="user_pass"
              id="user_pass"
              class="form-control"
              placeholder="영/소문자+숫자+특수문자 포함 8글자 이상"
              required
              autofocus
              v-model="user_pass"
              @blur="validatePassword"
              style="flex: 1; white-space: nowrap"
            />
          </div>
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
            >비밀번호 확인</label
          >
          <div class="input-group">
            <input
              type="password"
              name="user_pass2"
              id="user_pass2"
              class="form-control"
              placeholder="비밀번호 확인"
              required
              v-model="user_pass2"
              @blur="validatePassword"
              style="flex: 1; white-space: nowrap"
            />
          </div>
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
          <div class="input-group">
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
              style="flex: 1; white-space: nowrap"
            />
          </div>
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
          <div class="input-group">
            <input
              type="text"
              name="user_phone_number"
              id="user_phone_number"
              class="form-control"
              placeholder="휴대폰 번호 (- 없이 입력)"
              required
              v-model="user_phone_number"
              @blur="validatePhoneNumber"
              maxlength="11"
              style="flex: 1; white-space: nowrap"
            />
            <button
              variant="outline-dark"
              class="btn btn-secondary"
              @click="checkPhoneNumber"
            >
              중복확인
            </button>
          </div>
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
          <div class="input-group">
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
              style="flex: 1; white-space: nowrap"
            />
            <button
              variant="outline-dark"
              class="btn btn-secondary"
              @click="checkEmail"
            >
              중복확인
            </button>
          </div>
        </div>
        <br />
        <div class="form-group" style="position: relative">
          <label
            for="user_athletic_career"
            style="
              position: absolute;
              top: -25px;
              left: 0;
              font-size: 12px;
              color: #888888;
            "
            >운동 경력</label
          >
          <div class="input-group">
            <input
              type="text"
              name="user_athletic_career"
              id="user_athletic_career"
              class="form-control"
              placeholder="운동 경력(연차, 숫자만 입력)"
              required
              autofocus
              v-model="user_athletic_career"
              @blur="validateAthleticCareer"
              style="flex: 1; white-space: nowrap"
            />
          </div>
        </div>
        <br />

        <div class="form-group" style="position: relative">
          <label
            for="user_profile_img"
            style="
              position: absolute;
              top: -25px;
              left: 0;
              font-size: 12px;
              color: #888888;
            "
            >프로필 사진</label
          >
          <div class="input-group">
            <input
              ref="fileInput"
              id="user_profile_img"
              @change="uploadImage"
              type="file"
              name="user_profile_img"
              class="form-control"
              placeholder="프로필 사진(선택)"
            />
          </div>
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
          <b-button variant="dark" @click="registUser">회원가입</b-button>
        </div>
      </div>
      <div class="card-footer text-center">
        <a href="" @click.prevent="moveLogin">로그인</a> |
        <a href="" @click.prevent="searchId">아이디 찾기</a> |
        <a href="" @click.prevent="searchPass">비밀번호 찾기</a>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "RegistForm",
  data() {
    return {
      user_id: "",
      user_pass: "",
      user_pass2: "",
      user_name: "",
      user_email: "",
      user_profile_img: "",
      user_phone_number: "",
      user_athletic_career: "",
      user_is_admin: 0,
      showCheck: false,
      checkText: "",
      checkColor: "",
    };
  },
  methods: {
    validateName() {
      if (this.user_name === "") {
        this.showCheck = true;
        this.checkText = "이름을 입력해주세요.";
        this.checkColor = "red";
      } else {
        this.showCheck = false;
      }
    },
    validateId() {
      if (this.user_id === "") {
        this.showCheck = true;
        this.checkText = "아이디 입력해주세요.";
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
    validatePassword() {
      if (this.user_pass === "") {
        this.showCheck = true;
        this.checkText = "비밀번호를 입력해주세요.";
        this.checkColor = "red";
      } else {
        this.showCheck = false;
      }
    },
    validateAthleticCareer() {
      if (this.user_athletic_career === "") {
        this.showCheck = true;
        this.checkText = "운동경력을 입력해주세요.";
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
    searchId() {
      this.$router.push({ name: "search-id" });
    },
    searchPass() {
      // this.$router.push({ name: "search-userinfo" });
      this.$router.push({ name: "search-pass" });
    },
    moveLogin() {
      this.$router.push({ name: "user-login" });
    },
    checkId() {
      // 영어 소문자, 숫자만 4~12자리까지 가능
      const regId = /^[a-z0-9]{4,12}$/;
      // 아이디 확인
      if (this.user_id.length < 4) {
        alert("아이디를 4글자 이상 입력하세요.");
        this.user_id.focus();
      } else if (!regId.test(this.user_id)) {
        alert("영문 소문자, 숫자만 입력하세요.");
        this.user_id.focus();
      }
      this.$store.dispatch("checkId", this.user_id);
    },

    checkEmail() {
      // 이메일 형식
      const regEmail =
        /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
      // 메일 주소 확인
      if (this.user_email === "") {
        alert("메일 주소를 입력하세요.");
        this.user_email.focus();
        return false;
      } else if (!regEmail.test(this.user_email)) {
        alert("잘못된 이메일 형식입니다.");
        this.user_email.focus();
        return false;
      }
      this.$store.dispatch("checkEmail", this.user_email);
    },
    checkPhoneNumber() {
      // 숫자만
      const checkNum = /^[0-9]+$/;
      // 휴대폰 번호 확인
      if (this.user_phone_number === "") {
        alert("휴대폰 번호를 입력하세요.");
        this.user_phone_number.focus();
        return false;
      } else if (!checkNum.test(this.user_phone_number)) {
        alert("휴대폰 번호는 숫자만 입력가능합니다.");
        this.user_phone_number.focus();
        return false;
      } else if (this.user_phone_number.length !== 11) {
        alert("휴대폰 번호를 010 부터 모두 입력하세요.");
        this.user_phone_number.focus();
        return false;
      }

      this.$store.dispatch("checkPNum", this.user_phone_number);
    },
    // 회원가입
    async registUser() {
      const user = {
        user_id: this.user_id,
        user_pass: this.user_pass,
        user_name: this.user_name,
        user_email: this.user_email,
        user_phone_number: this.user_phone_number,
        user_athletic_career: this.user_athletic_career,
        user_profile_img: this.user_profile_img,
        user_is_admin: 0,
      };
      if (this.checks()) {
        await this.$store.dispatch("registUser", user);
        this.$router.push({ name: "user-login" });
      }
    },
    checks() {
      // const regId = /^[a-z0-9]{4,12}$/;
      // 영어 소문자, 숫자, 특수문자 각각 1개 이상 비밀번호 8글자 이상
      const regPw =
        /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
      const regName = /^[가-힣a-zA-Z]{2,15}$/;
      // const regEmail =
      //   /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
      const checkNum = /^[0-9]+$/;

      // // 아이디 확인
      // if (this.user_id.length < 4) {
      //   alert("아이디를 4글자 이상 입력하세요.");
      //   this.user_id.focus();
      //   return false;
      // } else if (!regId.test(this.user_id)) {
      //   alert("영문 소문자, 숫자만 입력하세요.");
      //   this.user_id.focus();
      //   return false;
      // }

      // 비밀번호 확인
      if (this.user_pass.length < 8) {
        alert("비밀번호를 8글자 이상 입력하세요.");
        this.user_pass.focus();
        return false;
      } else if (!regPw.test(this.user_pass)) {
        alert(
          "비밀번호는 하나 이상의 문자, 숫자, 특수 문자를 모두 포함해야합니다."
        );
        this.user_pass.focus();
        return false;
      } else if (this.user_id === this.user_pass) {
        alert("아이디와 동일한 비밀번호를 사용할 수 없습니다.");
        this.user_pass.focus();
        return false;
      }

      // 비밀번호 재확인
      if (this.user_pass !== this.user_pass2) {
        alert("비밀번호가 일치하지 않습니다.");
        this.user_pass.focus();
        return false;
      }

      // 이름 확인
      if (this.user_name === "") {
        alert("이름을 입력해주세요.");
        this.user_name.focus();
        return false;
      } else if (!regName.test(this.name)) {
        alert("두 글자 이상의 한글과 영어만 입력하세요.");
        this.user_name.focus();
        return false;
      }

      // 휴대폰 번호 확인
      if (this.user_phone_number === "") {
        alert("휴대폰 번호를 입력하세요.");
        this.user_phone_number.focus();
        return false;
      } else if (!checkNum.test(this.user_phone_number)) {
        alert("휴대폰 번호는 숫자만 입력가능합니다.");
        this.user_phone_number.focus();
        return false;
      } else if (this.user_phone_number.length !== 11) {
        alert("휴대폰 번호를 010 부터 모두 입력하세요.");
        this.user_phone_number.focus();
        return false;
      }

      // // 메일 주소 확인
      // if (this.user_email === "") {
      //   alert("메일 주소를 입력하세요.");
      //   this.user_email.focus();
      //   return false;
      // } else if (!regEmail.test(this.user_email)) {
      //   alert("잘못된 이메일 형식입니다.");
      //   this.user_email.focus();
      //   return false;
      // }

      // 운동 경력 확인
      if (this.user_athletic_career === "") {
        alert("운동 경력을 입력하세요.");
        this.user_athletic_career.focus();
        return false;
      } else if (!checkNum.test(this.user_athletic_career)) {
        alert("운동 경력은 숫자만 입력하세요.");
        this.user_athletic_career.focus();
        return false;
      }

      return true;
    },
    async uploadImage(e) {
      const formData = new FormData();
      const imagefile = e.target.files[0];
      formData.append("file", imagefile);
      try {
        await axios({
          url: "http://localhost:9999/user/upload",
          method: "POST",
          data: formData,
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }).then((res) => {
          console.log(res);
          this.user_profile_img = res.data;
        });
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>
<style scoped>
.main-title {
  color: black;
  font-size: 25px;
  margin-top: 20px;
}
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
  margin-bottom: 100px; /* Added */
  margin-top: 100px;
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
  color: #040607;
  text-decoration: none;
}
.text2 {
  color: blue;
}
</style>
