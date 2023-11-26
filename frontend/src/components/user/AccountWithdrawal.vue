<template>
  <div>
    <div class="container">
      <h1>저희 사이트를 정말로 탈퇴하실 건가요?</h1>
      <input
        class="form-control"
        type="password"
        id="user_pass"
        placeholder="비밀번호"
        v-model="user_pass"
      />
      <button @click="userCheck" class="btn btn-primary">비밀번호 확인</button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "accountWithDrawal",
  data() {
    return {
      user_pass: "",
    };
  },
  methods: {
    async userCheck() {
      if (this.user_pass === "") {
        alert("비밀번호를 입력해주세요");
      } else {
        const user = {
          user_pass: this.user_pass,
        };
        try {
          await this.$store.dispatch("userPassCheck2", user);
          // 비밀번호 일치 시 탈퇴 처리 액션 호출
          if (confirm("정말로 탈퇴하시겠습니까?")) {
            await this.$store.dispatch("withdrawUser", user);
            this.$router.push({ name: "Home" });
          }
        } catch (error) {
          alert(error.message);
        }
      }
    },
  },
  computed: {
    ...mapState(["loginUser"]),
  },
};
</script>

<style scoped>
.form-control {
  width: 710px; /* 원하는 길이로 조정 */
}
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 50vh;
}

h2 {
  margin-top: 2rem;
}

.btn {
  font-size: 15px;
  white-space: nowrap;
  margin-top: 1rem;
}
</style>
