<template>
  <div class="container">
    <div class="menu">
      <h2>회원 정보</h2>
      <hr />
      <router-link
        :to="{
          name: 'user-info-check',
          params: { user_id: this.$route.params.user_id },
        }"
        >회원 정보 수정</router-link
      >
      <hr />
      <router-link :to="{ name: 'user-account-withdrawal' }"
        >회원 탈퇴</router-link
      >
    </div>
    <div class="content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: "user-detail",
};
</script>

<style scoped>
.container {
  width: 100%;
  display: flex;
  height: 100vh;
}

.menu {
  flex-basis: 20%;
  padding: 20px;
  background-color: #f0f0f0;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.menu h2 {
  margin-bottom: 20px;
  font-size: 24px;
  font-weight: bold;
}

.menu a {
  display: inline-block;
  padding: 10px 20px;
  margin-bottom: 10px;
  font-size: 16px;
  font-weight: bold;
  color: #080505;
  background-color: #e2e4eb;
  border-radius: 4px;
  text-decoration: none;
  transition: background-color 0.3s;
}

.menu a:hover {
  background-color: #ffae00;
}

.content {
  flex-basis: 80%;
  padding: 20px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.content h2 {
  margin-bottom: 20px;
  font-size: 24px;
  font-weight: bold;
}

.content p {
  margin-bottom: 10px;
  font-size: 16px;
  line-height: 1.5;
}
</style>
