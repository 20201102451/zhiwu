<template>
  <div class="container" >
    <div>
      <h2 style="text-align: center">公告板</h2>
    </div>
    <div class="infinite-list-wrapper">
      <ul
          class="infinite-list"
          v-infinite-scroll="load"
          style="overflow:auto;width:100%;height:65vh"
      >
        <el-card v-for="(item,index) in list" class="list-item">
          <li :key="index">
            <h1>公告</h1>
            <h4>内容: </h4>
            <div></div>
            <span style="padding-left: 20px"></span>
            <span>{{item.noticeContent}}</span>
            <div style="padding-top: 2.5rem"></div>
            <h5>发送者: {{item.senderId}}</h5>
            <h5>时间:{{item.sendTime}}</h5>
          </li>
        </el-card>

      </ul>
      <p v-if="loading">加载中...</p>
      <p v-if="noMore">没有更多了</p>
    </div>
  </div>


</template>

<script>
export default {
  data() {
    return {
      count: 1, //起始页数值为0
      loading: false,
      totalPages: 10, //取后端返回内容的总页数
      list: [] //后端返回的数组
    }
  },
  computed: {
    noMore() {
      return this.count >= this.totalPages - 1;
    },
    disabled() {
      return this.loading || this.noMore
    }
  },
  created() {
    this.getNotice();
  },
  methods: {
    load() {
      //滑到底部时进行加载
      this.loading = true;
      setTimeout(() => {
        this.count += 1; //页数+1
        this.getNotice(); //调用接口，此时页数+1，查询下一页数据
      }, 500);
    },
    //没有后端的情况
    // getMessage(){
    //   let templist = [
    //     {
    //       title:"我爱您 你却爱着她"
    //     },
    //     {
    //       title:"我爱您 你却爱着她"
    //     },
    //     {
    //       title:"我爱您 你却爱着她"
    //     },
    //     {
    //       title:"我爱您 你却爱着她"
    //     }
    //   ];
    //   this.list = this.list.concat(templist);
    //   this.loading = false;
    // },
    getNotice() {
      this.request.get('/notice/page', {
        params: {
          pageNum: this.count,
          pageSize: 5,
          searchContent: ""
        }
      }).then(res => {
        console.log(res.data.records);
        this.list = this.list.concat(res.data.records);
        //因为每次后端返回的都是数组，所以这边把数组拼接到一起 concat() 方法用于连接两个或多个数组。 该方法不会改变现有的数组,而仅仅会返回被连接数组的一个副本
        this.loading = false;
      })

    }
  }

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

.infinite-list-wrapper {
  height: 100%;
  width: 85%;
  padding: 2rem;

  .list-item {
    height: 300px;
  }
}

.container {
  height: 10vh;
  width: 100%;
}

</style>