import Vue from "vue";
import Vuex from "vuex";
// import http from "@/util/http-common";
import axios from "axios";
import createPersistedState from "vuex-persistedstate"; // 이게 있어야 로컬에 state정보를 전부 저장해놓는다.(새로고침해도 정보가 유지)
import router from "@/router";
axios.defaults.withCredentials = true; // 브라우저 쿠키 자동 전송

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    crews: [],
    crew: {},
    like_video: [],
    videos: [],
    video: {},
    user: {},
    reviews: [],
    loginUser: null,
    posts: [],
    post: {},
    infoMessage: "SSAFIT 사이트에 오신 걸 환영합니다.",
  },
  getters: {
    videos(state) {
      return state.videos;
    },
    video(state) {
      return state.video;
    },
  },
  mutations: {
    // 비디오 가져오기
    GET_VIDEO(state, video) {
      state.video = video;
    },
    // 비디오들 가져오기
    GET_VIDEOS(state, videos) {
      state.videos = videos;
    },
    // // 비디오 등록하기
    // REGIST_VIDEO(state, video) {
    //   state.videos.push(video);
    // },
    // 비디오 삭제
    DELETE_VIDEO(state) {
      state.video = null;
    },
    // 비디오 수정
    UPDATE_VIDEO(state, video) {
      state.video = video;
    },
    // 비디오 검색(제목)
    SEARCH_VIDEO(state, videos) {
      state.videos = videos;
    },
    //로그인
    SET_LOGIN_USER(state, user) {
      console.log(user);
      user.loginUser.user_pass = "";
      user.loginUser.user_phone_number = "";
      user.loginUser.user_email = "";
      state.loginUser = user.loginUser;
    },
    //로그아웃
    LOGOUT(state) {
      state.loginUser = null;
      localStorage.removeItem("access-token");
      alert("로그아웃이 완료되었습니다.");
    },
    // 좋아요 +1
    UPDATE_LIKE_CNT(state) {
      state.video.video_board_like_cnt += 1;
    },
    // 좋아요 -1
    CANCLE_UPDATE_LIKE_CNT(state) {
      state.video.video_board_like_cnt -= 1;
    },
    // 모든 비디오 좋아요 가져오기
    GET_VIDEO_LIKE(state, allLike) {
      state.like_video = allLike;
    },
    // 현재 로그인 유저 정보 가져오기
    GET_LOGIN_USER_INFO(state, loginUser) {
      state.loginUser = loginUser;
    },
    // 수정했으니 로그인 유저의 정보 갱신
    UPDATE_USER(state, loginUser) {
      loginUser.user_pass = "";
      loginUser.user_phone_number = "";
      state.loginUser = loginUser;
    },
    // 리뷰 등록
    REGIST_REVIEW(state, review) {
      state.reviews.push(review);
    },
    // 해당 게시글의 리뷰 가져오기
    GET_REVIEWS(state, reviews) {
      state.reviews = reviews;
    },
    // 아이디 찾기(유저 정보 저장)
    SEARCH_ID(state, user) {
      state.user = user;
    },
    // 회원탈퇴(유저 정보 null로 바꿈)
    WITHDRAW_USER(state) {
      state.loginUser = null;
      state.user = null;
      localStorage.removeItem("access-token");
    },
    // 크루 생성
    CREATE_CREW(state, crew) {
      state.crew = crew;
      state.crews.push(crew);
    },
    // 크루들 가져오기
    GET_CREWS(state, crews) {
      if (crews === "") {
        state.crews = [];
      } else {
        state.crews = crews;
      }
      state.crew = {};
    },
    // 크루 가져오기
    GET_CREW(state, crew) {
      state.crew = crew;
    },
    // 크루 검색
    SEARCH_CREWS(state, crews) {
      state.crews = crews;
    },
    // 토큰 만료
    TOKEN_FINISH(state) {
      state.loginUser = null;
      localStorage.removeItem("access-token");
    },
    // 크루 게시글들 없을 때
    GET_CREW_BOARD_POST_NO(state) {
      state.posts = [];
    },
    // 크루 게시글들 있으면
    GET_CREW_BOARD_POST(state, list) {
      state.posts = list;
    },
    // 특정 크루 게시글 가져오기
    GET_BOARD_POST(state, post) {
      state.post = post;
    },
  },
  actions: {
    // 비디오 가져오기
    getVideo({ commit }, video_board_id) {
      axios({
        url: `http://localhost:9999/videos/${video_board_id}`,
        method: "GET",
      })
        .then((res) => {
          commit("GET_VIDEO", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 비디오들 가져오기
    getVideos({ commit }) {
      axios({
        url: "http://localhost:9999/videos",
        method: "GET",
      }).then((res) => {
        commit("GET_VIDEOS", res.data);
      });
    },
    // 비디오 등록하기
    async registVideo({ commit }, video) {
      await axios({
        url: "http://localhost:9999/videos",
        method: "POST",
        params: video,
      })
        .then(() => {
          commit;
          alert("영상 등록에 성공하셨습니다.");
        })
        .catch((err) => {
          if (err.response) {
            if (err.response.data === "duplicationVideo") {
              alert("이미 등록된 주소입니다.");
            } else {
              alert("영상 등록에 실패하셨습니다.");
            }
          }
        });
    },
    // 비디오 수정하기
    updateVideo({ commit }, video) {
      axios({
        url: `http://localhost:9999/videos/update`,
        method: "PUT",
        params: video,
      })
        .then(() => {
          alert("수정이 완료되었습니다.");
          commit("UPDATE_VIDEO", video);
          // router.push({
          //   name: "video-detail",
          //   query: { video_board_id: video.video_board_id },
          // });
        })
        .catch((err) => {
          console.log(err);
          alert("수정을 실패했습니다.");
        });
    },
    // 비디오 삭제
    async deleteVideo({ commit }, video_board_id) {
      commit;
      await axios({
        url: `http://localhost:9999/videos/${video_board_id}`,
        method: "DELETE",
        params: video_board_id,
      })
        .then(() => {
          alert("삭제가 완료되었습니다.");
          commit("DELETE_VIDEO");
        })
        .catch((err) => {
          alert("삭제를 실패했습니다.");
          console.log(err);
        });
    },
    // 비디오 제목으로 검색
    searchVideo({ commit }, word) {
      axios({
        url: `http://localhost:9999/videos/search/${word}`,
        method: "GET",
        params: word,
      })
        .then((res) => {
          console.log(res);
          commit("SEARCH_VIDEO", res.data);
        })
        .catch((err) => {
          console.log(err);
          alert("일치하는 동영상이 없습니다.");
        });
    },
    // 회원가입
    async registUser({ commit }, user) {
      commit;
      await axios({
        url: "http://localhost:9999/user/signup",
        method: "POST",
        params: user,
      })
        .then(() => {
          alert(`가입에 성공하셨습니다. 
                로그인을 부탁드립니다.`);
        })
        .catch((err) => {
          console.log(err);
          alert("가입에 실패하셨습니다.");
        });
    },
    // 로그인
    setLoginUser({ commit }, user) {
      console.log(user);
      axios({
        url: "http://localhost:9999/user/login",
        method: "POST",
        params: user,
        headers: {
          "access-token": localStorage.getItem("access-token"), // 이게 있으며 넣어
        },
      })
        .then((res) => {
          console.log(res.data);
          if (res.data.user_is_admin === 1) {
            alert("관리자 계정으로 로그인 성공");
          } else {
            alert("로그인 성공");
          }
          localStorage.setItem("access-token", res.data["access-token"]);
          commit("SET_LOGIN_USER", res.data);
        })
        .catch((err) => {
          alert("로그인 실패");
          console.log(err);
        });
    },
    // 좋아요 업데이트
    async updateLikeCnt({ commit, dispatch }, like) {
      const video_board_id = like.video_board_id;
      console.log(video_board_id);
      await axios({
        url: `http://localhost:9999/like/plus/${video_board_id}`,
        method: "PUT",
        params: video_board_id,
      })
        .then(() => {
          commit("UPDATE_LIKE_CNT");
        })
        .catch((err) => {
          console.log(err);
        });
      await axios({
        url: `http://localhost:9999/like`,
        method: "POST",
        params: like,
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
      dispatch("getVideoLike");
    },
    // 좋아요 취소하기
    async cancleUpdateLikeCnt({ commit }, like) {
      commit;
      const video_board_id = like.video_board_id;
      await axios({
        url: `http://localhost:9999/like/minus/${video_board_id}`,
        method: "PUT",
        params: video_board_id,
      })
        .then(() => {
          commit("CANCLE_UPDATE_LIKE_CNT");
        })
        .catch((err) => {
          console.log(err);
        });
      await axios({
        url: `http://localhost:9999/like`,
        method: "DELETE",
        params: like,
      }).then((res) => {
        console.log(res);
      });
    },
    // 좋아요 데이터 가져오기
    getVideoLike({ commit }) {
      axios({
        url: `http://localhost:9999/like`,
        method: "GET",
      })
        .then((res) => {
          commit("GET_VIDEO_LIKE", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 유저 비밀번호 확인절차(유저 정보 수정 전에)
    userPassCheck1({ commit }, user) {
      console.log(user);
      axios({
        url: "http://localhost:9999/user/check",
        method: "POST",
        params: user,
        headers: {
          "access-token": localStorage.getItem("access-token"), // 이게 있으며 넣어
        },
      })
        .then((res) => {
          router.push({
            name: "user-info-update",
            params: { user_id: res.data.user_id },
          });
        })
        .catch((error) => {
          if (error.response) {
            if (error.response.data === "No token") {
              alert("로그인이 필요합니다.");
            } else if (error.response.data === "Invalid token") {
              alert("토큰이 만료되었습니다. 다시 로그인해주세요.");
              commit("TOKEN_FINISH");
              router.push({ name: "Home" });
            } else {
              alert("비밀번호가 틀렸습니다.");
            }
          }
        });
    },
    // 유저 비밀번호 확인절차(유저 탈퇴 전에)
    userPassCheck2({ commit }, user) {
      console.log(user);
      return new Promise((resolve, reject) => {
        axios({
          url: "http://localhost:9999/user/check",
          method: "POST",
          params: user,
          headers: {
            "access-token": localStorage.getItem("access-token"), // 이게 있으며 넣어
          },
        })
          .then(() => {
            // 비밀번호 일치 시 resolve 호출
            resolve(true);
          })
          .catch((error) => {
            if (error.response) {
              if (error.response.data === "No token") {
                alert("로그인이 필요합니다.");
              } else if (error.response.data === "Invalid token") {
                alert("토큰이 만료되었습니다. 다시 로그인해주세요.");
                commit("TOKEN_FINISH");
                router.push({ name: "Home" });
              } else {
                reject(new Error("비밀번호가 틀렸습니다."));
              }
            }
          });
      });
    },
    // 유저 정보 업데이트
    async updateUser({ commit }, user) {
      console.log(user);
      await axios({
        url: `http://localhost:9999/user`,
        method: "PUT",
        params: user,
        headers: {
          "access-token": localStorage.getItem("access-token"), // 이게 있으며 넣어
        },
      })
        .then((res) => {
          alert("유저 정보가 수정되었습니다.");
          commit("UPDATE_USER", res.data);
        })
        .catch((error) => {
          if (error.response) {
            if (error.response.data === "No token") {
              alert("로그인이 필요합니다.");
              commit("TOKEN_FINISH");
            } else if (error.response.data === "Invalid token") {
              alert("토큰이 만료되었습니다. 다시 로그인해주세요.");
              commit("TOKEN_FINISH");
              router.push({ name: "Home" });
            } else {
              alert("유저 수정을 실패했습니다.");
            }
          }
        });
    },
    // 회원탈퇴
    withdrawUser({ commit }, user) {
      commit;
      axios({
        url: `http://localhost:9999/user`,
        method: "DELETE",
        params: user,
        headers: {
          "access-token": localStorage.getItem("access-token"), // 이게 있으며 넣어
        },
      })
        .then(() => {
          alert("회원 탈퇴가 완료되었습니다.");
          commit("WITHDRAW_USER");
        })
        .catch((error) => {
          if (error.response) {
            if (error.response.data === "No token") {
              alert("로그인이 필요합니다.");
            } else if (error.response.data === "Invalid token") {
              console.log("123");
              alert("토큰이 만료되었습니다. 다시 로그인해주세요.");
              commit("TOKEN_FINISH");
              router.push({ name: "Home" });
            } else {
              alert("회원탈퇴를 실패했습니다.");
            }
          }
        });
    },
    // 비디오 게시판 리뷰 등록
    registReview({ commit }, review) {
      commit;
      axios({
        url: `http://localhost:9999/videos/reviews`,
        method: "POST",
        params: review,
      })
        .then((res) => {
          console.log(res);
          commit("REGIST_REVIEW", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 리뷰 목록들 가져오기
    getReviews({ commit }, video_board_id) {
      console.log(video_board_id);
      axios({
        url: `http://localhost:9999/videos/reviews/${video_board_id}`,
        method: "GET",
        params: video_board_id,
      })
        .then((res) => {
          console.log(res);
          commit("GET_REVIEWS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 해당 리뷰 삭제하기
    deleteReview({ commit }, review_id) {
      axios({
        url: `http://localhost:9999/videos/reviews/${review_id}`,
        method: "DELETE",
        params: review_id,
      })
        .then((res) => {
          commit;
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 아이디 찾기
    async searchId({ commit }, user) {
      await axios({
        url: `http://localhost:9999/user/search/id`,
        method: "POST",
        params: user,
      })
        .then((res) => {
          commit("SEARCH_ID", res.data);
          console.log(res);
          router.push({
            name: "result-seach-id",
          });
        })
        .catch((err) => {
          console.log(err);
          alert("일치하는 회원 정보가 없습니다.");
        });
    },
    // 비밀번호 찾기(이메일 전송)
    async sendEmail({ commit }, user) {
      await axios({
        url: "http://localhost:9999/user/sendEmail",
        method: "GET",
        params: user,
      })
        .then((res) => {
          alert("임시비밀번호가 발급되었습니다. 로그인을 해주세요.");
          commit;
          console.log(res);
          //////////////////////////////////////////////////////////확인하기
          router.push({
            name: "user-login",
          });
        })
        .catch((err) => {
          console.log(err);
          alert("비밀번호 전송을 실패했습니다.");
        });
    },

    // 크루 등록
    async createCrew({ commit }, crew) {
      await axios({
        url: `http://localhost:9999/crews`,
        method: "POST",
        params: crew,
      })
        .then((res) => {
          console.log(res.data);
          alert("등록이 완료되었습니다.");
          commit("CREATE_CREW", res.data);
          // crew_board_id를 가져와서 쿼리로 보내 해당 crew_board로 가게하기.
          const crew_id = res.data.crew_id;
          console.log(crew_id);
          axios({
            url: `http://localhost:9999/crews/board/${crew_id}`,
            method: "GET",
          })
            .then((res) => {
              console.log(res.data);
              router.push({
                name: "crew-search",
              });
            })
            .catch((err) => {
              console.log(err);
            });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 크루들 가져오기
    getCrews({ commit }) {
      axios({
        url: `http://localhost:9999/crews`,
        method: "GET",
      })
        .then((res) => {
          console.log(res);
          // if (res.data.length > 0) {
          commit("GET_CREWS", res.data);
          // } else {
          //   commit("GET_CREWS", []);
          // }
        })
        .catch((err) => {
          console.log(err);
          alert("오류가 발생했습니다.");
        });
    },
    // 크루 가져오기
    getCrew({ commit }, crew_id) {
      console.log(crew_id);
      axios({
        url: `http://localhost:9999/crews/detail/${crew_id}`,
        method: "GET",
        params: crew_id,
      })
        .then((res) => {
          commit("GET_CREW", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 크루 검색
    searchCrews({ commit }, word) {
      axios({
        url: `http://localhost:9999/crews/search/${word}`,
        method: "GET",
        params: "word",
      })
        .then((res) => {
          console.log(res);
          commit("SEARCH_CREWS", res.data);
        })
        .catch((err) => {
          console.log(err);
          alert("오류가 발생했습니다.");
        });
    },
    // 크루 가입
    async joinCrew({ commit }, { user_id, crew_join_content, route }) {
      commit;
      const crew_id = route.params.crew_id;
      await axios({
        url: `http://localhost:9999/crews/join/${crew_id}`,
        method: "POST",
        params: { crew_id, user_id, crew_join_content },
      })
        .then((res) => {
          console.log(res);
          alert("가입에 성공하셨습니다.");
          router.push({
            name: "crew-board",
            params: { crew_board_id: crew_id },
          });
        })
        .catch((err) => {
          console.log(err);
          alert("가입에 실패하셨습니다.");
        });
    },
    // 크루가입 중복 체크
    checkCrew({ commit }, { crewId, userId }) {
      commit;
      axios({
        url: `http://localhost:9999/crews/check/${crewId}`,
        method: "GET",
        params: { crewId, userId },
      })
        .then((res) => {
          if (res.data === "FAIL") {
            alert("이미 가입된 크루입니다.");
          } else if (res.data === "MAX") {
            alert("크루 멤버가 가득 찼습니다.");
          } else {
            router.push({ name: "crew-join", params: { crew_id: crewId } });
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 내 크루들 가져오기(마이 크루로 이동했을 경우)
    async getMyCrews({ commit }, user_id) {
      commit;
      await axios({
        url: `http://localhost:9999/crews/${user_id}`,
        method: "GET",
        params: user_id,
      })
        .then((res) => {
          if (res.data == "NO CREW") {
            // alert("가입한 크루가 없습니다. 크루 가입을 부탁드립니다.");
            // router.push({ name: "crew-search" });
            commit("GET_CREWS", null);
          } else {
            commit("GET_CREWS", res.data);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 크루 게시글 전부 가져오기
    async getCrewBoardPost({ commit }, crew_id) {
      commit;
      await axios({
        url: `http://localhost:9999/crews/board/post/${crew_id}`,
        method: "GET",
        params: crew_id,
      })
        .then((res) => {
          if (res.data === "NO") {
            commit("GET_CREW_BOARD_POST_NO");
          } else {
            commit("GET_CREW_BOARD_POST", res.data);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 크루 게시글 등록
    async registPost({ commit }, post) {
      console.log(post);
      commit;
      await axios({
        url: `http://localhost:9999/crews/board/post`,
        method: "POST",
        params: post,
      })
        .then((res) => {
          console.log(res);
          alert("게시글이 등록되었습니다.");
          router.push({
            name: "crew-board",
          });
        })
        .catch((err) => {
          console.log(err);
          alert("게시글 작성에 실패했습니다.");
        });
    },
    // 크루 특정 게시글 가져오기
    async getBoardPost({ commit }, crew_board_post_id) {
      console.log(crew_board_post_id);
      await axios({
        url: `http://localhost:9999/crews/board/post/get/${crew_board_post_id}`,
        method: "GET",
        params: crew_board_post_id,
      })
        .then((res) => {
          console.log(res.data);
          commit("GET_BOARD_POST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 회원가입 시 아이디 중복 체크
    checkId({ commit }, user_id) {
      axios({
        url: `http://localhost:9999/user/checkId/${user_id}`,
        method: "GET",
        params: user_id,
      })
        .then((res) => {
          console.log(res);
          if (res.data === "DUPLICATION") alert("해당 아이디가 존재합니다.");
          else alert("사용할 수 있는 아이디입니다.");
          commit;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 회원가입 시 이메일 중복 체크
    checkEmail({ commit }, user_email) {
      axios({
        url: `http://localhost:9999/user/checkEmail/${user_email}`,
        method: "GET",
        params: user_email,
      })
        .then((res) => {
          console.log(res);
          if (res.data === "DUPLICATION") alert("해당 이메일이 존재합니다.");
          else alert("사용할 수 있는 이메일입니다.");
          commit;
        })
        .catch((err) => {
          console.log(err);
        });
    }, // 회원가입 시 휴대폰 번호 중복 체크
    checkPNum({ commit }, user_phone_number) {
      axios({
        url: `http://localhost:9999/user/checkPNum/${user_phone_number}`,
        method: "GET",
        params: user_phone_number,
      })
        .then((res) => {
          console.log(res);
          if (res.data === "DUPLICATION")
            alert("해당 휴대폰 번호가 존재합니다.");
          else alert("사용할 수 있는 휴대폰 번호입니다..");
          commit;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  plugins: [
    createPersistedState({
      paths: [
        "loginUser",
        "infoMessage",
        "video",
        "videos",
        "crews",
        "crew",
        "posts",
        "post",
      ],
    }),
  ],
  modules: {},
});
