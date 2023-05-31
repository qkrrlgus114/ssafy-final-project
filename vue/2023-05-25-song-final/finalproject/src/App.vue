<template>
  <div id="app">
    <header-nav />
    <router-view />
  </div>
</template>
<script>
import HeaderNav from "@/components/common/HeaderNav.vue";

export default {
  name: "App",
  components: {
    HeaderNav,
  },
  methods: {},
};
</script>
<style scoped>
#app {
  padding-top: 70px; /* 헤더의 높이만큼 패딩을 주어 헤더에 가려지지 않게 합니다. */
  /* font-family: "Noto Sans KR", sans-serif; */
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  list-style: none;
}

.text-center {
  text-align: center;
}
.container {
  margin: 0px 30px;
}
</style>
<style>
body {
  background: rgb(248, 247, 245);
}
</style>
