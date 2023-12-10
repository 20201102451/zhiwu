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
      curTab: "角色管理"
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
        this.$router.push("/chat")
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

body > .el-container {
  margin-bottom: 40px;
}
</style>
