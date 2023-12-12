<template>
  <div class="emailBody">
    <div class="emailSpace">
      <div class="emailHeader"><img src="../../assets/emailHeader.png" style="border-radius: 8px;" /></div>
      <div class="emailButton">
        <el-button type="primary" plain style="margin-right: 5em;" @click="centerDialogVisible = true">发送信笺</el-button>
        <el-button type="primary" plain @click="getEmail">打捞信笺</el-button>
      </div>
    </div>
    <!-- 发送飞鸽信笺 -->
    <el-dialog title="发送飞鸽信笺" :visible.sync="centerDialogVisible" width="30%" center>
      <el-input type="textarea" :autosize="{ minRows: 10, maxRows: 15 }" placeholder="请输入内容" v-model="emailContent"
        maxlength="703" show-word-limit>
      </el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="sendEmail">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 查看飞鸽信笺 -->
    <el-dialog title="查看飞鸽信笺" :visible.sync="centerDialogVisible2" width="31.4%" center @close="closeLookEmail">
      <div class="lookEmail">
        &emsp;&emsp;<span class="textCSS">{{
          email.emailContent }}</span><br /><span v-html="emptyText" class="textCSS"></span>
        <!-- <span class="textCSS" style="float: right;">&emsp;{{ email.senderName }}&emsp;</span> -->


        <el-dropdown trigger="click" style="float: right; font-size: large;">
          <span class="textCSS">
            {{ email.senderName }}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click="">访问空间</el-dropdown-item>
            <el-dropdown-item ><el-button type="text" @click="upchat(email.senderId)"> 发起私聊 </el-button></el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>


      </div>


      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible2 = false">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>


<script >
export default {
  data() {
    return {
      emailContent: '',
      centerDialogVisible: false,
      centerDialogVisible2: false,
      currentUserId: '',
      email: {
        emailId: '',
        senderId: '',
        senderName: '李丁',
        emailContent: '大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大口径的板卡五百打开大大大'
      },
      emptyText: ''
    }
  },
  methods: {
    sendEmail() {
      this.centerDialogVisible = false;
      if (!this.$store.state.isAdmin) {
        this.currentUserId = JSON.parse(localStorage.getItem("user")).userId.toString();
      }
      console.log(this.currentUserId);
      //封装对象
      const email = {
        senderId: this.currentUserId,
        emailContent: this.emailContent
      }
      //发送请求
      this.request.put('/email/send', email)
        .then(res => {
          //接收后台返回数据
          console.log(res.code);
          const code = res.code;
          if (code == '200') {
            this.sendEmailSuccess(res.data);
          } else {
            this.sendEmailFail(res.data);
          }
        })
      this.emailContent = null;
    },
    getEmail() {
      if (!this.$store.state.isAdmin) {
        this.currentUserId = JSON.parse(localStorage.getItem("user")).userId.toString();
      }
      // console.log(this.currentUserId);
      for (let i = 0; i < 54; i++) {
        this.emptyText += '&emsp;';
      }
      //发送请求
      this.request.get('/email/get', {
        params: {
          receiverId: this.currentUserId
        }
      })
        .then(res => {
          // console.log(res.data);
          if (res.code == "500") {
            this.sendEmailFail(res.message);
            this.centerDialogVisible2 = false;
          } else {
            this.centerDialogVisible2 = true;
            this.email = res.data;
          }
        })
    },
    closeLookEmail() {
      this.emptyText = '';
    },
    sendEmailSuccess(msg) {
      this.$notify({
        title: '成功',
        message: msg,
        type: 'success'
      });
    },
    sendEmailFail(msg) {
      this.$notify.error({
        title: '错误',
        message: msg
      });
    },
    upchat(createId){
      //定义对象结构
      console.log("666")
      const newlist={
        createrId:this.currentUserId,
        associaterId:createId,
      }
      console.log(newlist)
      this.request.post('/chat/addList',JSON.stringify(newlist)).then(res=>{
        console.log(res.code);
        const code = res.code;
        if (code == '200') {
          this.sendPostSuccess(res.data);
        } else {
          this.sendPostFail(res.data);
        }
      });
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
    },
  }
}
</script>


<style scoped>
.textCSS {
  text-decoration: underline wavy #0DB3BB;
  line-height: 2em;
  text-underline-offset: 0.5em;
  color: black;
}

.lookEmail {
  background-image: url('../../assets/lookEmailBGI.jpg');
  box-sizing: border-box;
  width: 533px;
  height: 753px;
  padding-top: 1em;
  padding-left: 1em;
  padding-right: 1em;
  font-size: large;
}

.emailBody {
  background-image: url('../../assets/emailBGI.jpg');
  width: 100%;
  height: 50em;
  /* background-size: 100%; */
}

.emailSpace {
  background-color: #f1c181;
  opacity: 0.85;
  position: absolute;
  right: 39em;
  top: 15em;
  height: 20em;
  width: 30em;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
}

.emailHeader {
  text-align: center;
  margin-top: 2em;
}

.emailButton {
  text-align: center;
  margin-top: 6em;
}
</style>
