<template>
  <el-card style="width: 100%; height: 100%; padding: 20px; border: 1px solid #CCC" title="用户信息">
    <div style="display: flex;flex-direction: row">
      <el-upload
          class="avatar-uploader"
          action="http://localhost:9090/user/avatar"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :data="this.userInfo"
      >
        <img v-if="userInfo.avatar" :src="userInfo.avatar" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>

      <div>
        <div v-if="lover!==null" style="position: absolute;padding-left: 2.5rem;display: flex;flex-direction: row">
          <el-button circle style="height: 3.5rem"><img src="@/assets/redHeart.png" style="width: 2rem"></el-button>

          <div style="padding-top: 1rem;padding-left: 1rem;color: #f163c1">名花有主</div>
        </div>

        <div v-if="lover===null" style="position: absolute;padding-left: 2.5rem;display: flex;flex-direction: row">
          <el-button @click="propose" circle style="height: 3.5rem"><img src="@/assets/grayHeart.png" style="width: 2rem"></el-button>
        </div>

        <el-descriptions class="my-description">
          <el-descriptions-item label="用户ID">{{ this.userInfo.userId }}</el-descriptions-item>
          <el-descriptions-item label="学号">{{ this.userInfo.studentId }}</el-descriptions-item>
          <el-descriptions-item label="姓名">{{ this.userInfo.userName }}</el-descriptions-item>
          <el-descriptions-item label="性别">
            <el-tag size="small">{{ userInfo.sex === 1 ? "男" : "女" }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="手机号">{{ this.userInfo.number }}</el-descriptions-item>
          <el-descriptions-item label="身高">{{ this.userInfo.height }}</el-descriptions-item>


          <el-descriptions-item label="恋爱宣言">{{ this.userInfo.declarationLove }}</el-descriptions-item>

          <template slot="extra">
            <el-button type="primary" size="small" @click="showEditDialog">编辑个人信息</el-button>
          </template>
        </el-descriptions>
      </div>


    </div>
    <el-input
        type="textarea"
        :rows="3"
        placeholder="这人很神秘，没有留下自我介绍"
        v-model="userInfo.userIntro" readonly resize="none">
    </el-input>

    <div style="display: flex;flex-direction: row; width: 100%;padding-top: 2rem">
      <h2 style="flex:9">照片墙</h2>
      <div style=" margin-top:auto; padding-bottom: 0.5rem;padding-right: 1rem">
        <el-button :type="editPic?'info':'success'" size="small" @click="editPic=!editPic">
          {{ editPic ? '取消' : '编辑' }}
        </el-button>
      </div>

    </div>
    <div class="line"></div>

    <div style="display: flex; flex-wrap: wrap">
      <el-upload
          action="http://localhost:9090/file_wall/upload"
          list-type="picture-card"
          class="wallPic"
          :show-file-list="false"
          :on-success="handlePicUploadSuccess"
          :data="this.userInfo"
      >
        <i class="el-icon-plus"></i>
      </el-upload>
      <div v-for="(item,index) in picList" style="position: relative">
        <el-popover
            placement="top"
            width="160"
            v-show="editPic"
            v-model="item.popoverVisible"
            :ref="'popover-' + item.fileId"
        >
          <p>确定删除图片？</p>
          <div style="text-align: right; margin: 0">
            <el-button size="mini" type="text" @click="pCancel(item.fileId)">取消</el-button>
            <el-button type="primary" size="mini" @click="deletePic(item)">确定</el-button>
          </div>
          <el-button
              slot="reference"
              type="danger" size="small" style="position: absolute;right: 0.2rem; z-index: 1000"
          >删除
          </el-button>
        </el-popover>

        <el-image
            class="wallPic"
            :src="item.fileUrl"
            :preview-src-list="urlList">
        </el-image>
      </div>

    </div>


    <el-dialog title="个人信息" :visible.sync="editDialogVisible" width="30%">
      <el-form label-width="80px" size="small" :rules="formRules">
        <!--      <el-upload-->
        <!--          class="avatar-uploader"-->
        <!--          action="http://localhost:9090/file/upload"-->
        <!--          :show-file-list="false"-->
        <!--          :on-success="handleAvatarSuccess"-->
        <!--          >-->
        <!--        <img v-if="form.avatarUrl" :src="form.avatarUrl" class="avatar">-->
        <!--        <i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
        <!--      </el-upload>-->

        <el-form-item label="用户id">
          <el-input v-model="form.userId" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="form.studentId" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="form.userName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex">
            <el-radio :label="1" value="1">男</el-radio>
            <el-radio :label="0" value="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电话号码">
          <el-input v-model="form.number" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="身高">
          <el-input v-model="form.height" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="体重">
          <el-input v-model="form.weight" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="恋爱宣言">
          <el-input
              type="textarea"
              :rows="1"
              placeholder="请输入恋爱宣言"
              v-model="form.declarationLove" maxlength="50" show-word-limit>
          </el-input>
        </el-form-item>
        <el-form-item label="自我介绍">
          <el-input
              type="textarea"
              :rows="3"
              placeholder="请输入自我介绍"
              v-model="form.userIntro" maxlength="200" show-word-limit>
          </el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false;">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

  </el-card>
</template>

<script>
export default {
  name: "Person",
  data() {
    return {
      form: {},
      userInfo: {},
      user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : {},
      formRules: {
        userName: [{required: true, message: "请输入姓名", trigger: "blur"}],
      },
      editDialogVisible: false,

      // pic wall
      picList: [],
      urlList: [],
      visible: false,
      editPic: false,

      //propose
      proposeNotices: [],
      lover:null
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

    this.getLover(this.userInfo.userId)

    this.getPicsByUserId()

    this.$forceUpdate()
  },
  methods: {
    pCancel(id) {
      this.pClose(id)
    },
    pClose(id) {
      console.log(this.$refs['popover-' + id])
      this.$refs['popover-' + id][0].doClose()
    },
    getLover(uid) {
      this.request.get('/user_love/lover/' + this.user.userId)
          .then(res => {
            console.log(res)
            if (res.code === '200') {
              if (res.data == null || res.data.length === 0) {
                this.getPropose();
              } else {
                this.lover=res.data
                console.log(this.lover)
              }
            }
          });
    },
    propose(uid) {
      let propose={
        user1Id: this.userInfo.userId,
        user2Id: "1"
      }
      this.request.post('/user_love/save', propose)
          .then(res => {
            console.log(res)
            if (res.code === '200') {
              console.log(res)
              this.$message.success({
                message: "已发送请求",
                duration:1500
              })
              this.$forceUpdate()
            }
          })
    },
    getPropose() {
      console.log(this.user.userId)
      this.request.get('/user_love/propose_for/' + this.user.userId)
          .then(res => {
            console.log(res)
            const h = this.$createElement
            if (res.code === '200') {
              let proposeList = res.data
              for (let propose of proposeList) {
                let notice = this.$notify({
                  title: '交往请求',
                  // message: 'id为'+propose.user1Id+'的用户请求与您交往',
                  message: h(
                      "p",
                      {
                        style: "width: 350px;;display:flex;flex-direction:column",
                      },
                      [
                        h("div", null, 'id为 ' + propose.user1Id + ' 的用户请求与您交往'),
                        h(
                            "p",
                            {
                              style: "width: 350px;;display:flex;flex-direction:row",
                            },
                            [h(
                                "el-button",
                                {
                                  style: "width:5rem;color: #409EFF;cursor: pointer;",
                                  on: {
                                    click: () => {
                                      this.agreePropose(propose)
                                    }
                                  },
                                },
                                '同意'
                            ),
                              h(
                                  "el-button",
                                  {
                                    style: "width:5rem;color: #409EFF;cursor: pointer;",
                                    on: {
                                      click: () => {
                                        this.rejectPropose(propose,notice)
                                      }
                                    },
                                  },
                                  '拒绝'
                              ),]
                        ),

                      ]
                  ),

                  duration: 0
                });
                this.proposeNotices.push(notice)
              }

              this.$forceUpdate()
            }
          })
    },
    rejectPropose(propose,notice) {
      this.request.post('/user_love/', propose)
          .then(res => {
            console.log(res)
            if (res.code === '200') {
              console.log(res)
              this.$forceUpdate()
            }
          })
      notice.close()
    },
    agreePropose(propose) {
      propose.determineTime = this.getCurrentTime()
      this.request.post('/user_love/agree', propose)
          .then(res => {
            console.log(res)
            if (res.code === '200') {
              console.log(res)
              this.$forceUpdate()
            }
          })

      for (let notice of this.proposeNotices) {
        console.log(notice)
        notice.close()
      }

    },
    getPicsByUserId() {
      this.request.get('/file_wall/getByUserId/' + this.user.userId)
          .then(res => {
            console.log(res)
            if (res.code === '200') {
              this.picList = res.data
              this.urlList = this.picList.map(item => {
                item.popoverVisible = false
                return item.fileUrl
              })
              console.log('picList', this.picList)
              this.$forceUpdate()
            }
          })

    },
    showEditDialog() {
      this.form = Object.assign({}, this.userInfo)
      this.editDialogVisible = true
    },
    save() {
      console.log(this.form)
      this.request.post("/user/save", this.form)
          .then(res => {
            if (res) {
              this.$message.success('保存成功')
              this.editDialogVisible = false
              this.getCurrentUser()
            } else {
              this.$message.error('保存失败')
            }
          })
    },
    getCurrentUser() {
      let uid = JSON.parse(localStorage.getItem('user')).userId
      this.request.get('/user/' + uid)
          .then(res => {
            this.userInfo = res.data
            localStorage.setItem('user', JSON.stringify(res.data))
            return res.data
          })
    },
    cancel() {
      this.$router.back()
    },
    handleAvatarSuccess(res) {
      this.userInfo.avatar = res
      console.log('new avatar', res)
      this.$forceUpdate()
    },
    handlePicUploadSuccess(res) {
      console.log('upload pic', res)
      this.$message.success({
        message: "图片上传成功",
        duration: 1000
      })
      this.getPicsByUserId()
      this.$forceUpdate()
    },
    deletePic(pic) {
      console.log(pic)
      this.request.delete("/file_wall/delete/" + pic.fileId)
          .then(res => {
            if (res) {
              this.$message.success('删除成功')
              this.pClose(pic.fileId)
              this.getPicsByUserId()
            } else {
              this.$message.error('删除失败')
            }
          })

    },
    getCurrentTime() {
      const now = new Date();

      const year = now.getFullYear();
      const month = ('0' + (now.getMonth() + 1)).slice(-2);
      const day = ('0' + now.getDate()).slice(-2);
      const hours = ('0' + now.getHours()).slice(-2);
      const minutes = ('0' + now.getMinutes()).slice(-2);
      const seconds = ('0' + now.getSeconds()).slice(-2);

      const formattedTime = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
      return formattedTime
    },
  },

}
</script>

<style>
.avatar-uploader {
  text-align: center;
  padding-bottom: 10px;
  float: left;
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

.wallPic {
  width: 148px;
  height: 148px;
  display: block;
  padding: 0.3rem;
}


.my-description {
  padding-left: 3.5rem;
  padding-top: 1rem;
}

/* 横线 */
.line {
  float: right;
  width: 100%;
  height: 1px;
  margin-top: -0.5em;
  background: #d4c4c4;
  position: relative;
  text-align: center;
}

</style>