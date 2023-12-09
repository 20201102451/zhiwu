<template>
  <div>
    <div class="header-user">

      <label>
        <div class="avatar">
          <el-avatar :size="40" :src="userInfo.avatar"></el-avatar>
        </div>

        <el-dropdown trigger="click">
          <span class="el-dropdown-link">{{ userInfo.userName }}<i class="el-icon-arrow-down el-icon--right"></i></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item><span @click="goToMyInfo" style="text-decoration: none"><i class="el-icon-user-solid"></i>个人中心</span>
            </el-dropdown-item>
            <el-dropdown-item><span @click="goToPassword" style="text-decoration: none"><i class="el-icon-lock"></i>修改密码</span>
            </el-dropdown-item>
            <el-dropdown-item icon="el-icon-remove">
              <span @click="logout" style="text-decoration: none"><i class="el-icon-out"></i>退出登录</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </label>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      userInfo: {}
    }
  },
  created() {
    this.userInfo = localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : {};
  },
  methods: {
    goToMyInfo() {
      console.log('to my info')
      this.$emit("toMySpace")
      this.$router.push('/myInfo')
    },
    goToPassword() {
      this.$router.push('/password')
    },
    logout() {
      localStorage.removeItem("user")
      localStorage.removeItem("admin")
      this.$router.push('/login')
      this.$message.success("退出成功")

    },
  }
}


</script>

<style scoped>
.header-user {
  /* display: inline-block; */
  position: absolute;
  right: 10%;

  .el-dropdown-link {
    cursor: pointer;
    color: black;
  }

  .avatar {
    position: absolute;
    right: 110%;
    top: 20%;
  }
}
</style>