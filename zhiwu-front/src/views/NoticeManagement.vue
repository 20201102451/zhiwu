<template>
  <div style="width: 100%;height: 100%">


    <div style="margin: 10px 0">
      <el-input suffix-icon="el-icon-search" placeholder="请输入名字" v-model="searchContent"
                style="width: 200px;margin-left: 5px"></el-input>
      <el-button size="small" style="margin-left: 5px" type="primary" @click="loadData">搜索</el-button>
      <el-button size="small" style="margin-left: 5px" type="warning" @click="reset">刷新</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button size="small" type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"
                                                                       style="margin-left: 4px"></i></el-button>
      <el-popconfirm
          confirm-button-text='好的'
          cancel-button-text='不用了'
          icon="el-icon-info"
          icon-color="red"
          title="确定批量删除选中的数据？"
          style="margin: 5px"
          @confirm="deleteBatch"
      >
        <el-button size="small" type="danger" slot="reference">批量删除<i class="el-icon-remove-outline"
                                                                          style="margin-left: 4px"></i></el-button>
      </el-popconfirm>

    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="headerBackground"
              @selection-change="handleSelectionChanged"
    >
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="noticeId" label="公告ID" width="140"></el-table-column>
      <el-table-column prop="senderId" label="发送者ID" width="120"></el-table-column>
      <el-table-column prop="noticeContent" label="公告内容"></el-table-column>
      <el-table-column prop="sendTime" label="公告时间"></el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEditRow(scope.row)">编辑<i class="el-icon-edit"
                                                                             style="margin-left: 4px"></i></el-button>
          <el-popconfirm
              confirm-button-text='好的'
              cancel-button-text='不用了'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除该条公告吗？"
              style="margin-left: 5px"
              @confirm="handleDeleteRow(scope.row.noticeId)"
          >
            <el-button slot="reference" type="danger">
              删除<i class="el-icon-remove-outline" style="margin-left: 4px"></i>
            </el-button>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalNum">
      </el-pagination>
    </div>

    <el-dialog title="修改公告" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="公告ID">
          <el-input v-model="form.noticeId" readonly autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="发送者ID">
          <el-input v-model="form.senderId" readonly autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="公告内容">
          <el-input  type="textarea" v-model="form.noticeContent" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="发送时间">
          <el-input v-model="form.sendTime" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false; loadData()">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="发送公告" :visible.sync="dialogAddVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="发送者ID">
          <el-input v-model="addForm.senderId" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="公告内容">
          <el-input  type="textarea" v-model="addForm.noticeContent" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="发送时间">
          <el-input v-model="addForm.sendTime" disabled autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false; loadData()">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "UserBoard",
  data() {
    return {
      tableData: [],
      totalNum: 0,
      pageNum: 1,
      pageSize: 10,

      searchContent: "",

      dialogFormVisible: false,
      form: {},
      dialogAddVisible: false,
      addForm: {},

      adminInfo: {},
    }
  },
  methods: {
    loadData() {
      // request.get('http://localhost:9090/user/page?pageNum='+this.pageNum+'&pageSize='+this.pageSize)
      //     .then(res=>{
      //       this.tableData=res.records;
      //       this.totalNum=res.total;
      //     })
      this.request.get('/notice/page', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          searchContent: this.searchContent,
        }
      }).then(res => {
        this.tableData = res.data.records;
        this.totalNum = res.data.total;
      })
      this.$forceUpdate()

    },
    reset() {
      this.searchContent = ""
      this.loadData()
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.loadData();
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum;
      this.loadData();
    },
    handleAdd() {
      this.addForm.sendTime=this.getCurrentTime()
      this.dialogAddVisible = true;
    },
    handleEditRow(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    handleDeleteRow(id) {
      this.request.delete("/notice/" + id)
          .then(res => {
            if (res.code==='200') {
              this.$message.success('删除成功')
              this.loadData();
            } else {
              this.$message.error('删除失败')
              this.loadData();
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

      const formattedTime = year +"-"+ month +"-"+ day +" "+ hours +":"+ minutes +":"+ seconds;
      return formattedTime
    },
    deleteBatch() {
      let ids = this.multipleSelection.map(v => v.noticeId)  // [ {}, {}, {} ] => [ 1,2,3 ]
      this.request.post("/notice/del/batch", ids)
          .then(res => {
            if (res.code==='200') {
              this.$message.success('批量删除成功')
              this.loadData();
            } else {
              this.$message.error('批量删除失败')
              this.loadData();
            }
          })
    },
    handleSelectionChanged(val) {
      this.multipleSelection = val
    },
    save() {
      this.request.post("/notice/save", this.form)
          .then(res => {
            if (res.code==='200') {
              this.$message.success('保存成功')
              this.dialogFormVisible = false;
              this.loadData();
            } else {
              this.$message.error('保存失败')
              this.dialogFormVisible = false;
              this.loadData();
            }
          })
    },
    add() {
      this.request.post("/notice/save", this.addForm)
          .then(res => {
            if (res.code==='200') {
              this.$message.success('保存成功')
              this.dialogAddVisible = false;
              this.loadData();
              this.addForm.noticeContent=""
            } else {
              this.$message.error('保存失败')
              this.dialogAddVisible = false;
              this.addForm.noticeContent=""
              this.loadData();
            }
          })
    },
  },

  created() {
    this.adminInfo = localStorage.getItem('admin') ? JSON.parse(localStorage.getItem('admin')) : {};
    console.log(this.adminInfo)
    this.addForm.senderId = this.adminInfo.adminId
    this.loadData();
  }
}
</script>

<style scoped>
.headerBackground {
  background-color: #eee !important;
}

</style>