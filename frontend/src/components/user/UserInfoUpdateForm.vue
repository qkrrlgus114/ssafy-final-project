<template>
  <div class="container">
    <h2>회원 정보</h2>
    <fieldset class="text-center">
      <label>프로필 사진</label>
      <div class="mb-3">
        <input
          type="file"
          ref="fileInput"
          id="user_profile_img"
          @change="uploadImage"
        />
      </div>
      <div class="mb-3">
        <img
          :src="'http://localhost:9999/upload/' + user_profile_img"
          alt="프로필 사진"
          width="100px"
        />
      </div>
      <div class="mb-3">
        <label for="id" class="form-label">아이디</label>
        <input
          readonly
          type="text"
          id="id"
          v-model="user_id"
          class="form-control"
        />
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">비밀번호</label>
        <input
          type="password"
          id="password"
          v-model="user_pass"
          class="form-control"
        />
      </div>
      <div class="mb-3">
        <label for="name" class="form-label">이름</label>
        <input type="text" id="name" v-model="user_name" class="form-control" />
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">이메일</label>
        <input
          type="email"
          id="email"
          v-model="user_email"
          class="form-control"
        />
      </div>
      <div class="mb-3">
        <label for="phone_number" class="form-label">휴대폰번호</label>
        <input
          type="text"
          id="phone_number"
          v-model="user_phone_number"
          class="form-control"
        />
      </div>
      <div class="mb-3">
        <label for="athletic_career" class="form-label">운동경력</label>
        <input
          type="text"
          id="athletic_career"
          v-model="user_athletic_career"
          class="form-control"
        />
      </div>

      <div class="mb-3">
        <button class="btn btn-primary me-2" @click="updateUser">수정</button>
        <button class="btn btn-danger" @click="moveDetail">취소</button>
      </div>
    </fieldset>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

export default {
  name: "UserDetail",
  data() {
    return {
      user_id: "",
      user_pass: "",
      user_name: "",
      user_email: "",
      user_phone_number: "",
      user_athletic_career: "",
      user_profile_img: "",
    };
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  created() {
    axios({
      url: `http://localhost:9999/user/${this.$route.params.user_id}`,
      method: "GET",
      params: this.$route.params.user_id,
    }).then((res) => {
      this.user_id = res.data.user_id;
      this.user_pass = res.data.user_pass;
      this.user_name = res.data.user_name;
      this.user_email = res.data.user_email;
      this.user_phone_number = res.data.user_phone_number;
      this.user_athletic_career = res.data.user_athletic_career;
      this.user_profile_img = res.data.user_profile_img;
    });
  },
  methods: {
    async updateUser() {
      if (
        this.user_id === "" ||
        this.user_pass === "" ||
        this.user_name === "" ||
        this.user_phone_number === "" ||
        this.user_email === "" ||
        this.user_athletic_career === ""
      ) {
        alert("모든 내용을 입력해주세요");
      } else {
        const user = {
          user_id: this.user_id,
          user_pass: this.user_pass,
          user_name: this.user_name,
          user_email: this.user_email,
          user_phone_number: this.user_phone_number,
          user_athletic_career: this.user_athletic_career,
          user_profile_img: this.user_profile_img,
        };
        await this.$store.dispatch("updateUser", user);
        this.$router.push({
          name: "user-detail-view",
          params: { user_id: this.user_id },
        });
      }
    },
    moveDetail() {
      this.$router.push({
        name: "user-detail-view",
        params: { user_id: this.user_id },
      });
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
    // getLoginUserInfo(user_id) {
    //   this.$store.getLoginUserInfo("getLoginUserInfo", id);
    // },
  },
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 30px;
}

fieldset {
  border: none;
  margin-top: 20px;
}

label {
  font-weight: bold;
}

input[type="file"] {
  margin-bottom: 10px;
}

img {
  margin-bottom: 10px;
}
</style>
