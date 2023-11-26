import Vue from "vue";
import VueRouter from "vue-router";
import jwt_decode from "jwt-decode";
import store from "@/store";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("../views/HomeView.vue"),
  },
  {
    path: "/team",
    name: "team-view",
    component: () => import("@/views/teamView.vue"),
  },
  {
    path: "/video/",
    component: () => import("../views/VideoView.vue"),
    children: [
      {
        path: "",
        name: "video-list",
        component: () => import("@/components/video/VideoList.vue"),
      },
      {
        path: "create",
        name: "video-create",
        component: () => import("@/components/video/VideoCreate.vue"),
      },
      {
        path: "detail/:video_board_id",
        name: "video-detail",
        component: () => import("@/components/video/VideoDetail.vue"),
      },
      {
        path: "update/:video_board_id",
        name: "video-update",
        component: () => import("@/components/video/VideoUpdate.vue"),
      },
    ],
  },
  {
    path: "/users",
    component: () => import("../views/UserView.vue"),
    children: [
      {
        path: "regist",
        name: "user-regist",
        component: () => import("@/components/user/RegistForm.vue"),
      },
      {
        path: "login",
        name: "user-login",
        component: () => import("@/components/user/LoginForm.vue"),
      },
      {
        path: "detail",
        name: "user-detail",
        component: () => import("@/components/user/UserDetail.vue"),
        children: [
          {
            path: ":user_id",
            name: "user-detail-view",
            component: () =>
              import("@/components/user/include/UserDetailView.vue"),
          },
          {
            path: "update/:user_id",
            name: "user-info-update",
            component: () => import("@/components/user/UserInfoUpdateForm.vue"),
          },
          {
            path: "check",
            name: "user-info-check",
            component: () => import("@/components/user/UserInfoCheck.vue"),
          },
          {
            path: "withdrawal",
            name: "user-account-withdrawal",
            component: () => import("@/components/user/AccountWithdrawal.vue"),
          },
        ],
      },
      {
        path: "search",
        name: "search-userinfo",
        component: () => import("@/components/user/SearchUserInfo.vue"),
        children: [
          {
            path: "id",
            name: "search-id",
            component: () => import("@/components/user/include/SearchId.vue"),
          },
          {
            path: "result/id",
            name: "result-seach-id",
            component: () =>
              import("@/components/user/include/ResultSearchId.vue"),
          },
          {
            path: "pass",
            name: "search-pass",
            component: () => import("@/components/user/include/SearchPass.vue"),
          },
        ],
      },
    ],
  },
  {
    path: "/crew/",
    component: () => import("../views/CrewView.vue"),
    children: [
      {
        path: "search",
        name: "crew-search",
        component: () => import("@/components/crew/CrewSearch.vue"),
      },
      {
        path: "create",
        name: "crew-create",
        component: () => import("@/components/crew/CrewCreate.vue"),
      },
      {
        path: "join/:crew_id",
        name: "crew-join",
        component: () => import("@/components/crew/CrewJoin.vue"),
      },
      {
        path: "detail/:crew_id",
        name: "crew-detail",
        component: () => import("@/components/crew/CrewDetail.vue"),
      },
      {
        path: ":user_id",
        name: "my-crew",
        component: () => import("@/components/crew/MyCrew.vue"),
      },
    ],
  },
  {
    path: "/crewBoard/:crew_id",
    component: () => import("../views/CrewBoardView.vue"),
    children: [
      {
        path: "post",
        name: "post-create",
        component: () =>
          import("@/components/crewBoard/CrewBoardPostCreate.vue"),
      },
      {
        path: "",
        name: "crew-board",
        component: () => import("@/components/crewBoard/CrewBoard.vue"),
      },
      {
        path: "detail/:crew_board_post_id",
        name: "crew-board-detail",
        component: () => import("@/components/crewBoard/CrewBoardDetail.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  // 토큰을 가져옵니다.
  const accessToken = localStorage.getItem("access-token");

  // 로그인 검사를 건너뛰어야 하는 경로
  const publicPages = [
    "user-login",
    "user-regist",
    "Home",
    "search-id",
    "search-pass",
    "result-seach-id",
  ];

  // 토큰이 없고, 사용자가 로그인, 회원가입, 또는 홈 경로가 아니라면 로그인 페이지로 리다이렉션합니다.
  if (!accessToken && !publicPages.includes(to.name)) {
    alert("로그인이 필요한 서비스입니다.");
    store.commit("TOKEN_FINISH");
    console.log(to.name);
    return router.push({ name: "user-login" }).catch(() => {}); // 로그인 페이지로 리다이렉션
  }

  // 토큰이 있는 경우 토큰을 디코드합니다.
  if (accessToken) {
    const decodedToken = jwt_decode(accessToken);

    // 토큰에 'id' 또는 'isAdmin' 필드가 없다면 로그인 페이지로 리다이렉션합니다.
    // if (!decodedToken.id || !decodedToken.isAdmin) {
    //   alert("로그인부터 해주세요");
    //   return next({ name: "user-login" });
    // }

    const now = Date.now().valueOf() / 1000;
    if (typeof decodedToken.exp !== "undefined" && decodedToken.exp < now) {
      alert("로그인 유효 시간이 만료되었습니다. 다시 로그인해주세요.");
      store.commit("TOKEN_FINISH");
      return next({ name: "user-login" });
    }
  }

  // 나머지 경우에는 정상적으로 라우팅을 진행합니다.
  next();
});

export default router;
