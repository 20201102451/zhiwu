<template>
  <el-card style="width: 500px; padding: 20px; border: 1px solid #CCC" title="修改密码">
    <el-form label-width="80px" size="small">
      <el-form-item label="当前密码">
        <el-input v-model="form.currentPW" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="新密码">
        <el-input v-model="form.newPW" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码">
        <el-input v-model="form.confirmPW" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密保问题">
        <el-input v-model="userInfo.passwordQuestion" autocomplete="off" readonly></el-input>
      </el-form-item>
      <el-form-item label="密保答案">
        <el-input v-model="form.pwA" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item style="text-align: right">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  name: "Person",
  data() {
    return {
      form: {
        currentPW: "",
        newPW: "",
        confirmPW: "",
        pwA: "",

      },
      userInfo: {},
      user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : {}
    }
  },
  created() {
    this.request.get('/user/' + this.user.userId)
        .then(res => {
          console.log(res)
          if (res.code === '200') {
            this.userInfo = res.data
          }
        })
  },
  methods: {
    save() {
      console.log(this.form)
      if (this.form.currentPW !== this.userInfo.userPassword) {
        this.$message.error('当前密码错误！');
        return
      }
      if (this.form.pwA !== this.userInfo.passwordAnswer) {
        this.$message.error('密保答案错误！');
        return;
      }
      if (this.form.newPW !== this.form.confirmPW) {
        this.$message.error('新密码不一致！')
        return
      }
      if (this.form.newPW === null || this.form.newPW === "") {
        this.$message.error('新密码不能为空！')
        return
      }
      this.userInfo.userPassword = this.form.newPW
      this.request.post("/user/save", this.userInfo)
          .then(res => {
            if (res) {
              this.$message.success('保存成功')
              this.$emit('refreshUser')
              this.getCurrentUser()
            } else {
              this.$message.error('保存失败')
            }
          });
    },
    getCurrentUser() {
      let uid = JSON.parse(localStorage.getItem('user')).userId
      this.request.get('/user/' + uid)
          .then(res => {
            localStorage.setItem('user', JSON.stringify(res.data))
            this.userInfo = res.data
          })
    },
    cancel() {
      this.$router.back()
    },
    handleAvatarSuccess(res) {
      this.form.avatarUrl = res
    },
  }
}
</script>

<style>
.avatar-uploader {
  text-align: center;
  padding-bottom: 10px;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>