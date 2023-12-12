<template>
  <div class="Chat">
    <div style="float:left; color: #f163c1;font-size: 50px">
      <div style="color: #f163c1; margin: 5px 50px; font-size: 50px">{{text}}</div>

    </div>

    <div style="float: right">
      <ul class="infinite-list" v-infinite-scroll="load"
          style="overflow:auto; height: 49em; width: 25em; list-style: none; float: right;">
        <li v-for="item in msgList" class="infinite-list-item">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span style="font-weight: bolder;">{{ item.createrId }}与{{ item.associaterId }}的聊天框</span>
              <el-button style="float: right; padding: 3px 0" type="text"
                         @click="loadComment(item.msgListId)">聊天
              </el-button>
            </div>
            <div class="content">
<!--              这里其实是消息的内容{{ item.msgListId }}-->
            </div>
            <el-divider content-position="right"><!--这里其实是消息的时间{{ item.msgListId }}--></el-divider>
          </el-card>
        </li>
      </ul>
    </div>
    <p v-if="loading">加载中...</p>
    <p v-if="noMore">没有更多了</p>
    <!--这是抽屉聊天弹出框-->
    <el-drawer title="我是标题" :visible.sync="drawer" :with-header="false" :direction="direction" size="70%">
      <!--这是消息的显示-->
      <el-card style="height: 600px">
        <ul v-for="(item, index) in msg" :key="index" style="list-style: none;">
          <li style="color:#0DB3BB">
<!--            {{item.sendTime}}-->
            {{item.senderId}}对{{item.receiverId}}说：
            <el-card class="msg-card">
              <div style="color: #f163c1">
                {{ item.msgContent }}
              </div>
            </el-card>
          </li>
        </ul>
      </el-card>
      <!-- 发送消息的地方 -->
      <div class="editComment">
        <span>发送消息</span>
        <el-input type="textarea" :rows="1" placeholder="请输入内容" v-model="msgContent" maxlength="20" show-word-limit style="margin-top: 1%;">
        </el-input>
        <el-button style="float: right; margin-top:5px;" type="primary" plain @click="sendMsg">发送</el-button>
      </div>
    </el-drawer>
  </div>
</template>
<script>
import chat from "@/views/index/Chat.vue";

export default {
  data() {
    return {
      msg: [],
      count: 0,
      loading: false,
      drawer: false,
      direction: 'ltr',
      msgList: [],
      currentCount:0,
      currentUserId: 'Admin',
      text:'快来和你的小伙伴聊天吧!!!!!!!!',
      list_id:'',
      msgContent:'',
      msgYourUserId:'',
    };
  },
  computed: {
    chat() {
      return chat
    },
    noMore() {
      return this.count >= 20
    },
    disabled() {
      return this.loading || this.noMore
    }
  },
  created() {
    // const socket = new WebSocket("ws://localhost:8181");
    // socket.onopen = function (event) {
    //   console.log("连接已打开");
    // };
    // socket.onmessage = function (event) {
    //   console.log("收到回复" + event.data);
    // };
    // socket.onclose = function (event) {
    //   console.log("连接已关闭");
    // };
    if (!this.$store.state.isAdmin) {
      this.currentUserId = JSON.parse(localStorage.getItem('user')).userId;
    }else{
      this.text = '管理员请发布公共，禁止聊天';
    }
  },
  methods: {
    load() {
      this.loading = false;
      let uid = this.currentUserId;
      if(this.currentUserId != 'Admin'){
      this.request.get('/chat/getbypage', {
        params:{
          currentCount:this.currentCount,
          uid:uid
        }
      })
          .then(res=>{
        this.msgList=res.data;
        this.msgYourUserId=res.data.receiverId;
        console.log(res);
      })
      this.currentCount+= this.msgList.length;
      }
    },
    loadComment(msgID) {
      console.log(msgID);
      this.list_id=msgID;
      this.drawer = true;
      if(this.currentUserId != 'Admin'){
        this.request.get('/chat/gets', {
          params:{
            msg_list_id: msgID,
          }
        })
            .then(res=>{
              this.msg=res.data;
              // if(this.msg.length!=0) {
              //   this.msgYourUserId = this.msg[0].receiverId;
              // }
              // console.log(msg[0].msgId);
              // console.log(msg[0].msgContent);
            })
      }
    },
    sendMsg() {
      // socket.send("Hello");
      //定义对象结构
      const newmsg ={
        msgListId:this.list_id,
        senderId:this.currentUserId,
        receiverId:this.msgYourUserId,
        msgContent:this.msgContent,
      }
      console.log(newmsg);
      this.request.post('/chat/add',JSON.stringify((newmsg)))
          .then(res=>{
            console.log(res.code);
            const code = res.code;
            if (code == '200') {
              this.sendMsgSuccess(res.data);
              this.loadComment(this.list_id);
            } else {
              this.sendMsgFail(res.data);
            }
          })
      this.msgContent =null;
    },
    sendMsgSuccess(msg) {
      this.$notify({
        title: '成功',
        message: msg,
        type: 'success'
      });
    },
    sendMsgFail(msg) {
      this.$notify.error({
        title: '错误',
        message: msg
      });
    }
  },
}
</script>
<style scoped>
.editComment {
  margin-left: 7%;
  margin-right: 5%;
}

.msg-card {
  margin-right: 5%;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.Chat {
  margin-top: 1%;
  height: 50em;
  width: 80em;
}

::-webkit-scrollbar {
  display: none; /* 隐藏滚动条 */
}
</style>
