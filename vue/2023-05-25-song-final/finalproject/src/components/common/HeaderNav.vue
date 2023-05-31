<template>
  <nav class="navbar">
    <div class="navbar__logo">
      <div>
        <img
          src="@/assets/logo.png"
          height="70px"
          @click="moveHome"
          style="cursor: pointer; margin-bottom: 15px"
        />
      </div>
    </div>

    <ul class="navbar__menu">
      <li><a href="" @click="moveTeam">프로젝트 소개</a></li>
      <li><a href="">팀원</a></li>
      <b-button
        @click="videoList"
        class="video-button"
        variant="outline-light"
        size="sm"
        style="border: none"
      >
        <i class="fa-solid fa-video" style="--fa-secondary-color: #ffae00"></i>
        영상
      </b-button>
      <b-dropdown variant="outline-light">
        <template #button-content>
          <i
            class="fa-solid fa-people-group"
            style="--fa-secondary-color: #ffae00; font-size: 16px"
          ></i>
          <span style="font-size: 16px; margin-left: 5px">크루</span>
        </template>
        <div>
          <b-dropdown-item-button class="menu1" @click="my_crew">
            마이 크루
          </b-dropdown-item-button>
          <b-dropdown-item-button class="menu2" @click="crew_search">
            크루 찾기
          </b-dropdown-item-button>
          <b-dropdown-item-button class="menu3" @click="crew_create">
            크루 등록
          </b-dropdown-item-button>
        </div>
      </b-dropdown>
    </ul>

    <ul class="navbar__icons">
      <div v-if="getUser">
        <div class="user-menu">
          <b-button
            variant="outline-dark"
            @click="moveUserDetail"
            class="logout-button"
            ><i
              class="fa-solid fa-circle-user fa-lg"
              style="color: #f5da81"
            ></i>
          </b-button>

          <b-button
            variant="outline-dark"
            @click="logout"
            class="logout-button"
          >
            <i
              class="fa-solid fa-right-from-bracket fa-lg"
              style="color: #f5da81"
            ></i>
          </b-button>
        </div>
      </div>
      <div v-else class="user-menu">
        <b-button-group class="user-menu__button-group">
          <b-button
            @click="login"
            class="video-button"
            variant="outline-light"
            size="m"
            style="border: none"
          >
            로그인
          </b-button>
          <b-button
            @click="regist"
            class="video-button"
            variant="outline-light"
            size="m"
            style="border: none"
          >
            회원가입
          </b-button>
        </b-button-group>
      </div>
    </ul>

    <a href="#" class="navbar__toogleBtn">
      <i class="fas fa-bars"></i>
    </a>
  </nav>
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "HeaderNav",
  methods: {
    logout() {
      this.$store.commit("LOGOUT");
      if (this.$route.name !== "Home" || this.$route.path !== "/") {
        // 현재 경로와 대상 경로의 이름과 경로 비교
        // if (this.$route.path.startsWith("/crew")) {
        this.$router.push({ name: "Home" }); // 크루 관련 경로인 경우 홈으로 이동
        // } else {
        //   this.$router.push(this.$route); // 다른 경로인 경우 현재 경로로 이동
        // }
      }
    },
    moveTeam() {
      this.$router.push({ name: "team-view" });
    },
    moveHome() {
      if (this.$route.name !== "Home") {
        this.$router.push({ name: "Home" });
      }
    },

    videoList() {
      if (this.$route.name !== "video-list") {
        this.$router.push({ name: "video-list" });
      }
    },

    my_crew() {
      if (this.$route.name !== "user-login") {
        if (this.loginUser) {
          const user_id = this.loginUser.user_id;
          this.$router.push({ name: "my-crew", params: { user_id: user_id } });
        } else {
          alert("로그인이 필요한 서비스입니다.");
          this.$router.push({ name: "user-login" });
        }
      } else if (this.$route.name === "user-login") {
        alert("로그인이 필요한 서비스입니다.");
      }
    },
    crew_search() {
      if (this.$route.name !== "crew-search") {
        this.$router.push({ name: "crew-search" });
      }
    },

    crew_create() {
      if (this.$route.name !== "crew-create") {
        this.$router.push({ name: "crew-create" });
      }
    },

    login() {
      if (this.$route.name !== "user-login") {
        this.$router.push({ name: "user-login" });
      }
    },

    regist() {
      if (this.$route.name !== "user-regist") {
        this.$router.push({ name: "user-regist" });
      }
    },

    moveUserDetail() {
      if (this.$route.name !== "user-detail-view") {
        const user_id = this.loginUser.user_id;
        this.$router.push({
          name: "user-detail-view",
          params: { user_id: user_id },
        });
      }
    },
  },
  mounted() {
    const toggleBtn = document.querySelector(".navbar__toogleBtn");
    const menu = document.querySelector(".navbar__menu");
    const icons = document.querySelector(".navbar__icons");

    toggleBtn.addEventListener("click", () => {
      menu.classList.toggle("active");
      icons.classList.toggle("active");
    });
  },
  computed: {
    ...mapState(["loginUser"]),
    getUser() {
      if (this.loginUser) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>
<style scoped>
:root {
  --text-color: #f0f4f5;
  --background-color: #263343;
  --accent-color: #d49466;
}

body {
  margin: 0px;
  font-family: "Open sans";
}

a {
  text-decoration: none;
  color: white;
}

.navbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 999;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #263343;
  padding: 0px 12px;
  height: 70px;
}

.navbar__logo {
  font-size: 24px;
  color: white;
}

.navbar__logo i {
  color: #d49466;
}

.navbar__menu {
  display: flex;
  list-style: none;
  padding-left: 0px;
}

.navbar__menu li {
  padding: 8px 12px;
}

/* .navbar__menu :hover {
  background: #d49466;
  border-radius: 4px;
} */

.menu1 :hover {
  background: #d49466;
  border-radius: 4px;
}
.menu2 :hover {
  background: #d49466;
  border-radius: 4px;
}
.menu3 :hover {
  background: #d49466;
  border-radius: 4px;
}

.navbar__icons {
  list-style: none;
  color: white;
  display: flex;
  padding-left: 0px;
}

.navbar__icons li {
  padding: 8px 12px;
}

.navbar__toogleBtn {
  display: none;
  position: absolute;
  right: 32px;
  font-size: 24px;
  color: #d49466;
}

@media screen and (max-width: 768px) {
  .navbar {
    flex-direction: column;
    align-items: flex-start;
    padding: 8px 24px;
  }

  .navbar__menu {
    margin-top: 50px; /* 새로운 속성 추가 */
    display: none;
    flex-direction: column;
    align-items: center;
    width: 100%;
  }

  .navbar__menu li {
    width: 100%;
    text-align: center;
  }

  .navbar__icons {
    display: none;
    justify-content: center;
    width: 100%;
    margin-top: 50px; /* 새로운 속성 추가 */
  }

  .navbar__toogleBtn {
    display: block;
  }

  .navbar__menu.active,
  .navbar__icons.active {
    display: flex;
  }
}
</style>
