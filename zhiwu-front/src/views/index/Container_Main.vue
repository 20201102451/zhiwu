<template>
  <el-container>
    <el-aside width="200px">
      <el-tabs :tab-position="tabPosition" v-model="curTab" @tab-click="getDataHandler">
        <el-tab-pane label="单身广场"></el-tab-pane>
        <el-tab-pane label="曝光板块"></el-tab-pane>
        <el-tab-pane label="角色管理" name="角色管理"></el-tab-pane>
        <el-tab-pane label="我的空间" name="我的空间"></el-tab-pane>
        <el-tab-pane label="公告板块"></el-tab-pane>
        <el-tab-pane v-if="isAdmin" label="公告管理"></el-tab-pane>
      </el-tabs>
    </el-aside>
    <router-view></router-view>
    <el-button class="sendPostButton" size="large" icon="el-icon-edit" @click="dialogVisible = true"
      type="primary">我要发帖</el-button>
    <!-- 编辑帖子内容 -->
    <el-dialog title="发布帖子" :visible.sync="dialogVisible" width="30%">
      <span>标题：</span>
      <el-input type="textarea" autosize placeholder="请输入标题" v-model="sendingPostTitle" maxlength="50"  show-word-limit>
      </el-input>
      <span>内容：</span>
      <div style="margin: 20px 0;"></div>
      <el-input type="textarea" :autosize="{ minRows: 10, maxRows: 20 }" placeholder="请输入内容" v-model="sendingPostContent">
      </el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">发 布</el-button>
      </span>
    </el-dialog>
  </el-container>
</template>

<script>
import Post from './Post.vue';

export default {
  components: {
    Post
  },
  data() {
    return {
      tabPosition: 'left',
      dialogVisible: false,
      curTab:"单身广场",
      sendingPostTitle: '',
      sendingPostContent: ''
    }
  },
  created() {
    this.isAdmin = this.checkAdmin()
    console.log(this.isAdmin)
  },
  methods: {
    getDataHandler(tab, event) {
      console.log(tab, event);
      console.log(tab.index)
      if (tab.index == 0) {
        this.$router.push("/post")
      } else if (tab.index == 1) {

      } else if (tab.index == 2) {

      } else if (tab.index == 3) {
        this.$router.push("/myInfo")
      } else if (tab.index == 4) {
        this.$router.push("/noticeBoard")
      } else if (tab.index == 5) {
        this.$router.push("/noticeManagement")
      }
    },
    checkAdmin() {
      return localStorage.getItem('admin') !== null;
    },
  }
}


</script>

<style scoped>

.sendPostButton {
  margin-top: 2%;
  width: 15em;
  height: 3em;
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