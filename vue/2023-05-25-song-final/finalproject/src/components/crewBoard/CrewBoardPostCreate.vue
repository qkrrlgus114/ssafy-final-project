<template>
  <div class="container mt-4">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title mb-4">새 게시글 작성</h5>
        <div class="form-group">
          <label for="crew_board_post_title">제목</label>
          <input
            type="text"
            id="crew_board_post_title"
            v-model="crew_board_post_title"
            class="form-control"
            placeholder="제목을 입력하세요"
          />
        </div>
        <div class="form-group">
          <label for="crew_board_post_writer">작성자</label>
          <input
            readonly
            reado
            type="text"
            id="crew_board_post_writer"
            v-model="crew_board_post_writer"
            class="form-control"
            placeholder="제목을 입력하세요"
          />
        </div>
        <div class="form-group">
          <label for="crew_board_post_content">내용</label>
          <quill-editor
            v-model="crew_board_post_content"
            :options="editorOptions"
            @text-change="onEditorTextChange"
            class="form-control"
            placeholder="내용을 입력하세요"
          ></quill-editor>
          <div
            class="character-count"
            :class="{ 'exceeds-max-count': exceedsMaxCharacterCount }"
          >
            {{ characterCount }} / {{ maxCharacterCount }}
          </div>
        </div>
        <div class="form-group">
          <label for="crew_board_post_img">이미지 업로드</label>
          <input
            type="file"
            id="crew_board_post_img"
            @change="uploadImage"
            class="form-control-file"
          />
          <img
            v-if="crew_board_post_img"
            :src="'http://localhost:9999/upload/' + crew_board_post_img"
            alt="프로필 사진"
            class="mt-3"
            style="max-width: 100%"
          />
        </div>
        <button
          class="btn btn-primary mt-3"
          @click="registPost"
          :disabled="exceedsMaxCharacterCount"
        >
          등록
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
import { quillEditor } from "vue-quill-editor";
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";

export default {
  name: "WriteFormPost",
  components: {
    quillEditor,
  },
  props: {
    type: String,
  },
  data() {
    return {
      crew_board_post_title: "",
      crew_board_post_writer: "",
      crew_board_post_content: "",
      crew_board_post_img: "",
      crew_id: "",
      editorOptions: {
        modules: {
          toolbar: [
            [{ header: [1, 2, false] }],
            ["bold", "italic", "underline"],
            ["image", "code-block"],
          ],
        },
      },
      maxCharacterCount: 500, // 최대 글자 수
      exceedsMaxCharacterCount: false, // 최대 글자 수 초과 여부
    };
  },
  created() {
    this.crew_board_post_writer = this.loginUser.user_id;
    this.crew_id = this.crew.crew_id;
  },
  methods: {
    removeHtmlTags(text) {
      const div = document.createElement("div");
      div.innerHTML = text;
      return div.textContent || div.innerText || "";
    },
    onEditorTextChange() {
      let plainText = this.removeHtmlTags(this.crew_board_post_content);
      if (plainText.length > this.maxCharacterCount) {
        alert("최대 글자 수를 초과했습니다.");
        this.crew_board_post_content = this.crew_board_post_content.substring(
          0,
          this.maxCharacterCount
        );
      } else {
        this.checkCharacterCount(plainText);
      }
    },
    checkCharacterLimit(plainText) {
      this.exceedsMaxCharacterCount = plainText.length > this.maxCharacterCount;
    },
    async registPost() {
      if (
        this.crew_board_post_title === "" ||
        this.crew_board_post_writer === "" ||
        this.crew_board_post_content === ""
      ) {
        alert("내용을 전부 채워주세요");
      } else if (this.exceedsMaxCharacterCount) {
        alert("최대 글자 수를 초과했습니다.");
      } else {
        const post = {
          crew_board_post_title: this.crew_board_post_title,
          crew_board_post_writer: this.crew_board_post_writer,
          crew_board_post_content: this.crew_board_post_content,
          crew_board_post_img: this.crew_board_post_img,
          crew_id: this.crew_id,
        };
        await this.$store.dispatch("registPost", post);
      }
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
          this.crew_board_post_img = res.data;
        });
      } catch (error) {
        console.error(error);
      }
    },
  },
  computed: {
    ...mapState(["loginUser"]),
    ...mapState(["crew"]),
    characterCount() {
      return this.removeHtmlTags(this.crew_board_post_content).length;
    },
  },
};
</script>

<style scoped>
.card {
  font-family: "Noto Sans KR", sans-serif;
}
fieldset {
  border: 1px solid black;
  padding: 10px;
}
textarea.form-control {
  height: 200px; /* 원하는 높이로 조정 */
}
.conta {
  margin: 100px 0px;
}
.character-count {
  margin-top: 10px;
  font-size: 12px;
  color: gray;
}
.exceeds-max-count {
  color: red;
}
.btn-regist[disabled] {
  opacity: 0.5;
  cursor: not-allowed;
}
</style>
