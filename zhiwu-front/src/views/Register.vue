<template>
  <div class="wrapper">
    <div style="margin:150px auto;background-color: #fff; width: 350px; height: 500px; padding: 20px; border-radius: 10px">
      <div style="margin:20px 0; text-align: center; font-size: 24px"><b>注册</b></div>

      <el-form :model="user" :rules="rules" ref="loginForm">
        <el-form-item prop="uid">
          <el-input placeholder="请输入学号" size="medium" prefix-icon="el-icon-user" v-model="user.studentId"></el-input>
        </el-form-item>
        <el-form-item prop="username">
          <el-input placeholder="请输入用户名" size="medium"  prefix-icon="el-icon-user" v-model="user.userName"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" size="medium"  prefix-icon="el-icon-lock" show-password
                    v-model="user.userPassword"></el-input>
        </el-form-item>

        <el-form-item prop="confirmedPassword">
          <el-input placeholder="确认密码" size="medium"  prefix-icon="el-icon-lock" show-password
                    v-model="user.confirmedPassword"></el-input>
        </el-form-item>

        <el-form-item prop="passwordQuestion">
          <el-input placeholder="密保问题" size="medium"  prefix-icon="el-icon-lock"
                    v-model="user.passwordQuestion"></el-input>
        </el-form-item>
        <el-form-item prop="passwordAnswer">
          <el-input placeholder="密保问题答案" size="medium"  prefix-icon="el-icon-lock"
                    v-model="user.passwordAnswer"></el-input>
        </el-form-item>

        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small" autocomplete="off" @click="login">确定</el-button>
          <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/login')">返回登陆</el-button>
        </el-form-item>
      </el-form>

    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      user: {
        studentId:"",
        userName:"",
        userPassword:""
      },
      rules:{
        studentId:[
          {required:true, message:'请输入学号',trigger:'blur'},
          {min:8,max:8, message:'请输入8位学号',trigger:'blur'}
        ],
        userPassword:[
          {required:true, message:'请输入密码',trigger:'blur'},
          {min:3,max:20, message:'长度在3到20个字符',trigger:'blur'}
        ],
        confirmedPassword:[
          {required:true, message:'请确认密码',trigger:'blur'},
          {min:3,max:20, message:'长度在3到20个字符',trigger:'blur'}
        ],
        passwordQuestion:[
          {required:true, message:'请输入密保问题',trigger:'blur'}
        ],
        passwordAnswer:[
          {required:true, message:'请输入答案',trigger:'blur'}
        ]
      }
    }
  },
  methods:{
    login(){
      this.$refs["loginForm"].validate((valid)=>{
        if (valid) {
          if (this.user.userPassword !== this.user.confirmedPassword) {
            this.$message.error("两次输入的密码不相同")
            return false
          }
          this.request.post("/user/register", this.user)
              .then(res => {
                if (res.code!=='200') {
                  this.$message.error(res.message);
                } else {
                  this.$message.success("注册成功")
                  this.$router.push("/login")
                }
              });
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #FC466B, #3f5efB);
  overflow: hidden;
}
</style>