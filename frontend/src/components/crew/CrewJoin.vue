<template>
  <div class="form-container">
    <div class="crew-join-form">
      <h3 class="form-title">크루 멤버 되기</h3>
      <div class="form-group">
        <label>프로필 사진</label>
        <br />
        <div class="profile_img">
          <img
            :src="'http://localhost:9999/upload/' + user_profile_img"
            width="200px"
          />
        </div>
      </div>
      <div class="form-group">
        <label>아이디</label>
        <input readonly type="text" name="user_id" v-model="user_id" />
      </div>
      <div class="form-group">
        <label>이름</label>
        <input readonly type="text" name="user_name" v-model="user_name" />
      </div>
      <div class="form-group">
        <label>휴대폰번호</label>
        <input
          readonly
          type="text"
          name="user_phone_number"
          v-model="user_phone_number"
        />
      </div>
      <div class="form-group">
        <label>운동 경력</label>
        <input
          readonly
          type="number"
          name="user_athletic_career"
          v-model="user_athletic_career"
        />
      </div>
      <div class="form-group">
        <label>가입 신청 이유</label>
        <br />
        <textarea
          name="crew_join_content"
          cols="30"
          rows="10"
          v-model="crew_join_content"
        ></textarea>
      </div>
      <button @click="joinCrew" class="join-button">크루 가입하기</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

export default {
  name: "crewJoin",
  data() {
    return {
      user_profile_img: "",
      user_id: "",
      user_name: "",
      user_phone_number: "",
      user_athletic_career: "",
      crew_join_content: "",
    };
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  created() {
    this.user_id = this.loginUser.user_id;
    axios({
      url: `http://localhost:9999/user/${this.user_id}`,
      method: "GET",
      params: this.loginUser,
    }).then((res) => {
      this.user_profile_img = res.data.user_profile_img;
      this.user_id = res.data.user_id;
      this.user_name = res.data.user_name;
      this.user_phone_number = res.data.user_phone_number;
      this.user_athletic_career = res.data.user_athletic_career;
    });
  },
  methods: {
    joinCrew() {
      if (this.crew_join_content == "") {
        alert("가입 신청 내용을 입력해주세요");
      } else {
        const user_id = this.user_id;
        const crew_join_content = this.crew_join_content;
        this.$store.dispatch("joinCrew", {
          user_id,
          crew_join_content,
          route: this.$route,
        });
      }
    },
  },
};
</script>

<style scoped>
.form-container {
  margin: 50px;
}

.profile_img {
  display: flex;
  justify-content: center;
}

.crew-join-form {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 5px;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
  font-family: "Noto Sans KR", sans-serif;
}

.form-title {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  font-size: 14px;
  margin-bottom: 5px;
  color: #555;
}

input[type="text"],
input[type="number"],
textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
  transition: border-color 0.3s ease;
}

textarea {
  resize: vertical;
}

input:read-only {
  background-color: #f5f5f5;
}

.join-button {
  display: block;
  width: 100%;
  padding: 12px;
  border: none;
  border-radius: 3px;
  background-color: #007bff;
  color: #fff;
  font-size: 16px;
  /* font-weight: bold; */
  text-align: center;
  transition: background-color 0.3s ease;
  cursor: pointer;
}

.join-button:hover {
  background-color: #0069d9;
}
</style>
