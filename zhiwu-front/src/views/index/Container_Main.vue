<template>
  <el-container>
    <el-aside width="200px">
      <el-tabs :tab-position="tabPosition" v-model="curTab" @tab-click="getDataHandler">
        <el-tab-pane label="单身广场"></el-tab-pane>
        <el-tab-pane label="曝光板块"></el-tab-pane>
        <el-tab-pane label="我的消息" name="我的消息"></el-tab-pane>
        <el-tab-pane label="我的空间" name="我的空间"></el-tab-pane>
        <el-tab-pane label="公告板块"></el-tab-pane>
        <el-tab-pane v-if="isAdmin" label="公告管理"></el-tab-pane>
      </el-tabs>
    </el-aside>
    <router-view></router-view>
    <el-button class="sendPostButton" ref="sendPostButton" size="large" icon="el-icon-edit" @click="dialogVisible = true"
      type="primary">我要发帖</el-button>
    <!-- 编辑帖子内容 -->
    <el-dialog title="发布帖子" :visible.sync="dialogVisible" width="30%">
      <span hidden>{{ tabIndex }}</span>
      <span hidden>{{ userId }}</span>

      <span>标题：</span>
      <el-input type="textarea" autosize placeholder="请输入标题" v-model="sendingPostTitle" maxlength="50" show-word-limit>
      </el-input>
      <span>内容：</span>
      <div style="margin: 20px 0;"></div>
      <el-input type="textarea" :autosize="{ minRows: 10, maxRows: 20 }" placeholder="请输入内容" v-model="sendingPostContent">
      </el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="sendPost">发 布</el-button>

      </span>
    </el-dialog>
  </el-container>
</template>

<script>
import Post from './Post.vue';
import Chat from './Chat.vue';
export default {
  components: {
    Post,
    Chat,
  },
  data() {
    return {
      tabPosition: 'left',
      dialogVisible: false,
      curTab: "单身广场",
      sendingPostTitle: '',
      sendingPostContent: '',
      tabIndex: '0',
      userId: ''
    }
  },
  created() {
    this.isAdmin = this.checkAdmin()
    console.log(this.isAdmin)
  },
  methods: {
    getDataHandler(tab, event) {
      this.tabIndex = tab.index;
      this.userId = JSON.parse(localStorage.getItem("user")).userId.toString();

      console.log(tab, event);
      console.log(tab.index)
      if (tab.index == 0) {
        this.$router.push("/post")
        this.displaySendPostButton();
      } else if (tab.index == 1) {
        this.displaySendPostButton();
      } else if (tab.index == 2) {

        this.hiddenSendPostButton();

        this.$router.push("/chat")
      } else if (tab.index == 3) {
        this.$router.push("/myInfo")
        this.hiddenSendPostButton();
      } else if (tab.index == 4) {
        this.$router.push("/noticeBoard")
        this.hiddenSendPostButton();

      } else if (tab.index == 5) {
        this.$router.push("/noticeManagement")
        this.hiddenSendPostButton();

      }
    },
    checkAdmin() {
      return localStorage.getItem('admin') !== null;
    },
    hiddenSendPostButton() {
      const sendPostButton = this.$refs.sendPostButton.$el;
      sendPostButton.style.display = 'none';
    },
    displaySendPostButton() {
      const sendPostButton = this.$refs.sendPostButton.$el;
      sendPostButton.style.display = '';
    },
    sendPost() {
      //关闭发帖页面
      this.dialogVisible = false
      //获取对象信息
      const createID = JSON.parse(localStorage.getItem("user")).userId.toString();
      const plateID = this.tabIndex.toString();

      console.log(createID);
      console.log(plateID);
      const postObject = {
        plateId: plateID,
        createId: createID,
        postTitle: this.sendingPostTitle,
        postContent: this.sendingPostContent,
      }
      //后台发送请求
      this.request.post('/post/add', JSON.stringify(postObject))
        .then(res => {
          //接收后台返回数据
          console.log(res.code);
          const code = res.code;
          if(code == '200'){
            this.sendPostSuccess(res.data);
          }else{
            this.sendPostFail(res.data);
          }
        })

    },
    sendPostSuccess(msg) {
      this.$notify({
        title: '成功',
        message: msg,
        type: 'success'
      });
    },
    sendPostFail(msg) {
      this.$notify.error({
        title: '错误',
        message: msg
      });
    }
  }
}


</script>

<style scoped>
.sendPostButton {
  margin-top: 2%;
  width: 15em;
  height: 3em;
  display: '';
}

.el-aside {
  margin-top: 1%;
  background-color: #E9EEF3;
  /*#E9EEF3*/
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  margin-top: 1%;
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body>.el-container {
  margin-bottom: 40px;
}
</style>
