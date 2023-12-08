<template>
  <div class="login">
    <div class="mylogin" align="center">
      <h4>WeLove 登录</h4>
      <el-radio-group v-model="userType" size="medium">
        <el-radio-button :label="0" value="0" >普通用户</el-radio-button>
        <el-radio-button :label="1" value="1" >管理员</el-radio-button>
      </el-radio-group>
      <el-form
          v-if="userType===0"
          :model="loginForm"
          :rules="loginRules"
          ref="loginForm"
          label-width="0px">
        <el-form-item label="" prop="account" style="margin-top: 10px">
          <el-row>
            <el-col :span="2">
              <span class="el-icon-s-custom"></span>
            </el-col>
            <el-col :span="22">
              <el-input
                  class="inps"
                  placeholder="学号"
                  v-model="loginForm.studentId"
                  oninput="value=value.replace(/[^\d.]/g,'')"
              >
              </el-input>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item label="" prop="passWord">
          <el-row>
            <el-col :span="2">
              <span class="el-icon-lock"></span>
            </el-col>
            <el-col :span="22">
              <el-input
                  class="inps"
                  type="password"
                  placeholder="密码"
                  v-model="loginForm.userPassword"
              ></el-input>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item style="margin-top: 55px">
          <el-button type="primary" round class="submitBtn" @click="submitForm">登录

          </el-button>
        </el-form-item>
        <div class="unlogin">
          <router-link :to="{ path: '/forgetpwd' }"> 忘记密码?</router-link>
          |
          <router-link :to="{ path: '/register' }">
            <a href="register.vue" target="_blank" align="right">注册新账号</a>
          </router-link>
        </div>
      </el-form>

<!--      管理员-->
      <el-form
          v-if="userType===1"
          :model="loginForm"
          ref="loginForm"
          label-width="0px">
        <el-form-item label="" prop="account" style="margin-top: 10px">
          <el-row>
            <el-col :span="2">
              <span class="el-icon-s-custom"></span>
            </el-col>
            <el-col :span="22">
              <el-input
                  class="inps"
                  placeholder="管理员ID"
                  v-model="loginForm.adminId"
                  oninput="value=value.replace(/[^\d.]/g,'')"
              >
              </el-input>
            </el-col>
          </el-row>
        </el-form-item >
        <el-form-item label="" prop="passWord">
          <el-row>
            <el-col :span="2">
              <span class="el-icon-lock"></span>
            </el-col>
            <el-col :span="22">
              <el-input
                  class="inps"
                  type="password"
                  placeholder="密码"
                  v-model="loginForm.adminPassword"
              ></el-input>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item style="margin-top: 55px">
          <el-button type="primary" round class="submitBtn" @click="submitForm">登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {mapMutations} from "vuex";

export default {
  name: "Login",
  data: function () {
    return {
      loginForm: {
        studentId: "",
        userPassword: "",
      },
      loginRules: {
        studentId: [{required: true, message: "请输入学号", trigger: "blur"}],
        userPassword: [{required: true, message: "请输入密码", trigger: "blur"}],
      },
      userType:0 // 1:admin   0:user
    };
  },

  methods: {
    test() {
      console.log(this.userType)
    },

    ...mapMutations(["changeLogin"]),
    submitForm() {
      console.log(this.userType)
      if (this.userType === 0) {
        const studentId = this.loginForm.studentId;
        const userPassword = this.loginForm.userPassword;
        if (!studentId) {
          return this.$message({
            type: "error",
            message: "学号不能为空！",
          });
        }
        if (!userPassword) {
          return this.$message({
            type: "error",
            message: "密码不能为空！",
          });
        }
        this.request.post("/user/login", this.loginForm)
            .then(res => {
              if (res.code!=='200') {
                this.$message.error(res.message);
              } else {
                console.log(res)
                // reset router or next time u login ,the router is the old one and some errors will occur
                this.$router.push("/")
                localStorage.setItem('user',JSON.stringify(res.data))
                this.$message.success("登陆成功")
              }
            });
      }else {
        const adminId = this.loginForm.adminId;
        const adminPassword = this.loginForm.adminPassword;
        if (!adminId) {
          return this.$message({
            type: "error",
            message: "管理员账号不能为空！",
          });
        }
        if (!adminPassword) {
          return this.$message({
            type: "error",
            message: "密码不能为空！",
          });
        }
        this.request.post("/admin/login", this.loginForm)
            .then(res => {
              if (res.code!=='200') {
                this.$message.error(res.message);
              } else {
                console.log(res)
                // reset router or next time u login ,the router is the old one and some errors will occur
                this.$router.push("/")
                localStorage.setItem('admin',JSON.stringify(res.data))
                this.$message.success("登陆成功")
              }
            });
      }


    },
  },
};
</script>


<style>
.login {
  width: 100vw;
  padding: 0;
  margin: 0;
  height: 100vh;
  font-size: 16px;
  background-position: left top;
  background-image: linear-gradient(to bottom right, #FC466B, #3f5efB);
  color: #1a1a1a;
  font-family: "Source Sans Pro";
  position: relative;
}

.mylogin {
  width: 240px;
  height: 380px;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  padding: 50px 40px 40px 40px;
  box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);
  opacity: 1;
  background-color: white;
}

.inps input {
  border: solid 1px;
  color: #1c1c1c;

  font-size: 12px;
}

.submitBtn {
  width: 200px;
}
</style>
