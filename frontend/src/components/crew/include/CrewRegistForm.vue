<template>
  <div class="container" role="main">
    <div class="crew-form">
      <h3 class="crew-form-title">크루 생성</h3>
      <div class="crew-form-group">
        <label for="crew_name" class="crew-form-label">크루 이름</label>
        <input
          type="text"
          id="crew_name"
          name="crew_name"
          v-model="crew_name"
          class="crew-form-input"
        />
      </div>
      <div class="crew-form-group">
        <label for="crew_leader" class="crew-form-label">크루 리더</label>
        <input
          readonly
          type="text"
          id="crew_leader"
          name="crew_leader"
          :value="crew_leader"
          class="crew-form-input"
        />
      </div>
      <div class="crew-form-group crew-form-group-row">
        <label for="crew_thema" class="crew-form-label">크루 테마</label>
        <select
          name="crew_thema"
          id="crew_thema"
          v-model="crew_thema"
          class="crew-form-input crew-form-input-half"
        >
          <option value="헬스">헬스</option>
          <option value="런닝">런닝</option>
          <option value="구기종목">구기종목</option>
          <option value="클라이밍">클라이밍</option>
          <option value="다이어트">다이어트</option>
          <option value="기타">기타</option>
        </select>
        <label for="crew_max_members" class="crew-form-label"
          >크루 최대 인원</label
        >
        <select
          name="crew_max_members"
          id="crew_max_members"
          v-model="crew_max_members"
          class="crew-form-input crew-form-input-half"
        >
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
          <option value="8">8</option>
          <option value="9">9</option>
          <option value="10">10</option>
        </select>
      </div>
      <div class="crew-form-group">
        <label for="crew_img" class="crew-form-label">크루 사진</label>
        <input
          type="file"
          ref="fileInput"
          id="crew_img"
          @change="uploadImage"
          class="crew-form-input"
        />
        <div v-if="crew_img" class="image-preview-container">
          <div class="image-preview-wrapper">
            <img
              :src="'http://localhost:9999/upload/' + crew_img"
              alt="크루 사진 미리보기"
              class="crew-image-preview"
            />
          </div>
        </div>
      </div>
      <div class="crew-form-group">
        <label for="crew_content" class="crew-form-label">크루 설명</label>
        <textarea
          rows="4"
          class="crew-form-input"
          type="text"
          id="crew_content"
          name="crew_content"
          v-model="crew_content"
        ></textarea>
      </div>
      <button
        v-if="type === 'create'"
        @click="registCrew"
        class="crew-form-button"
      >
        크루 생성
      </button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
export default {
  name: "crew-regist-form",
  props: {
    type: String,
  },
  data() {
    return {
      crew_leader: "",
      user_id: "",
      user_name: "",
      crew_name: "",
      crew_thema: "헬스",
      crew_create_date: "",
      crew_content: "",
      crew_max_members: "2",
      crew_current_members: "1",
      crew_img: "",
    };
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  created() {
    this.crew_leader =
      this.loginUser.user_name + " (" + this.loginUser.user_id + ")";
    this.user_name = this.loginUser.user_name;
    this.user_id = this.loginUser.user_id;
  },
  methods: {
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
          this.crew_img = res.data;
        });
      } catch (error) {
        console.error(error);
      }
    },
    registCrew() {
      if (
        this.crew_thema === "" ||
        this.crew_name === "" ||
        this.crew_content === "" ||
        this.crew_max_members === ""
      ) {
        alert("내용을 전부 입력해주세요");
      } else {
        const crew = {
          crew_leader: this.crew_leader,
          user_id: this.loginUser.user_id,
          crew_name: this.crew_name,
          crew_thema: this.crew_thema,
          crew_content: this.crew_content,
          crew_max_members: this.crew_max_members,
          crew_current_members: this.crew_current_members,
          crew_img: this.crew_img,
          crew_join_content: "leader",
        };

        this.$store.dispatch("createCrew", crew);
      }
    },
  },
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80vh;
}

.crew-form {
  font-family: "Noto Sans KR", sans-serif;
  width: 800px;
  padding: 30px;
  border-radius: 5px;
  background-color: #f5f5f5;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
}

.crew-form-title {
  font-family: "Noto Sans KR", sans-serif;
  margin-bottom: 20px;
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  color: #333;
}

.crew-form-group {
  margin-bottom: 15px;
}

.crew-form-group-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.crew-form-label {
  display: block;
  font-size: 14px;
  margin-bottom: 5px;
  color: #555;
}

.crew-form-input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
  transition: border-color 0.3s ease;
}

.crew-form-input-half {
  width: 45%;
  margin-right: 10px;
}

.crew-form-input:focus {
  outline: none;
  border-color: #007bff;
  box-shadow: 0px 0px 5px rgba(0, 123, 255, 0.3);
}

.crew-form-button {
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

.crew-form-button:hover {
  background-color: #0069d9;
}

.crew-form-input::placeholder {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.image-preview-container {
  max-width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.image-preview-wrapper {
  max-width: 200px; /* 원하는 미리보기 이미지 크기 설정 */
  max-height: 200px;
}

.crew-image-preview {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
}
</style>
