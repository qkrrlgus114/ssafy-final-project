<template>
  <div>
    <div class="container">
      <div class="mb-3">
        <h1 class="mb-4">비밀번호를 입력해주세요</h1>
        <label for="user_pass" class="visually-hidden">비밀번호</label>
        <input
          class="form-control"
          type="password"
          id="user_pass"
          placeholder="비밀번호"
          v-model="user_pass"
        />
      </div>
      <button @click="userCheck" class="btn btn-primary">비밀번호 확인</button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      user_pass: "",
    };
  },
  methods: {
    async userCheck() {
      if (this.user_pass == "") {
        alert("비밀번호를 입력해주세요");
      } else {
        const user = {
          user_pass: this.user_pass,
        };
        await this.$store.dispatch("userPassCheck1", user);
      }
    },
  },
  computed: {
    ...mapState(["loginUser"]),
  },
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 50vh;
}

h1 {
  margin-top: 2rem;
}

.btn {
  font-size: 15px;
  white-space: nowrap;
  margin-top: 1rem;
}
</style>
