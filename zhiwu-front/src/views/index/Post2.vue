<template>
    <div class="postCSS">
        <ul class="infinite-list" v-infinite-scroll="load"
            style="overflow:auto; height: 49em; width: 80em; list-style: none;" infinite-scroll-disabled="disabled">
            <li v-for="(item, index) in postList" :key="index" class="infinite-list-item">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span style="font-weight: bolder;">{{ item.postTitle }}</span>
                        <el-button style="float: right; padding: 3px 0" type="text"
                            @click="loadComment(item.postId)">查看评论</el-button>
                        <el-button v-show="canDelete(item.createId)"
                            style="float: right; padding: 3px; margin-right: 5px; color: red;"
                            @click="deletePost(item.postId)" type="text">删除</el-button>
                    </div>
                    <div class="content">
                        {{ item.postContent }}
                    </div>
                    <el-divider content-position="right">
                        <!-- 作者 -->
                        <el-dropdown trigger="click">
                            <span class="el-dropdown-link">
                                {{ item.createName }}<i class="el-icon-arrow-down el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item @click="">访问空间</el-dropdown-item>
                                <el-dropdown-item @click="">发起私聊</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </el-divider>
                </el-card>
            </li>
        </ul>
        <p v-if="loading">加载中...</p>
        <p v-if="noMore">没有更多了</p>
        <!--这是抽屉评论弹出框-->
        <el-drawer title="我是标题" :visible.sync="drawer" :with-header="false">
            <div v-show="commentList == null" style="margin-top: 5em; margin-bottom: 3em; text-align: center;">暂无评论</div>
            <ul v-for="(item, index) in commentList" :key="index" style="list-style: none;">
                <li>
                    <el-card class="comment-card">
                        <div slot="header" class="clearfix">
                            <el-dropdown trigger="click">
                                <span class="el-dropdown-link">
                                    {{ item.userName }}<i class="el-icon-arrow-down el-icon--right"></i>
                                </span>
                                <el-dropdown-menu slot="dropdown">
                                    <el-dropdown-item @click="">访问空间</el-dropdown-item>
                                    <el-dropdown-item @click="">发起私聊</el-dropdown-item>
                                </el-dropdown-menu>
                            </el-dropdown>
                        </div>
                        <div>
                            {{ item.commentContent }}
                        </div>
                    </el-card>
                </li>
            </ul>
            <!-- 编辑评论的地方 -->
            <div class="editComment">
                <span>编辑评论</span>
                <el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="commentContent" maxlength="255"
                    show-word-limit style="margin-top: 1%;">
                </el-input>
                <el-button style="float: right;" type="primary" plain @click="sendComment">发布</el-button>
            </div>
        </el-drawer>
    </div>
</template>

<script>
import { formToJSON } from 'axios';
export default {
    data() {
        return {
            count: 0,
            loading: false,
            drawer: false,
            direction: 'rtl',
            postList: [],
            currentCount: 0,
            commentLinkPostId: 0,
            commentList: [],
            commentContent: '',
            currentUserId: ''
        }
    },
    created() {
        if (!this.$store.state.isAdmin) {
            this.currentUserId = JSON.parse(localStorage.getItem("user")).userId.toString();

        }
    }
    , computed: {

        noMore() {
            return this.count >= 20
        },
        disabled() {
            return this.loading || this.noMore
        }
    },
    methods: {
        canDelete(createId){
            return createId == this.currentUserId || this.$store.state.isAdmin == true
        },
        load() {
            // console.log(this.$store.state.tabIndex);
            console.log(this.currentUserId);

            this.loading = false;
            //请求数据
            if (this.currentCount == 0) {
                this.request.get('/post/getbypage', {
                    params: {
                        currentCount: this.currentCount,
                        plateId: this.$store.state.tabIndex.toString()
                    }
                })
                    .then(res => {
                        if (res.data != null) {
                            this.postList = res.data;
                            this.currentCount += this.postList.length;
                        }
                    })
            } else {
                this.request.get('/post/getbypage', {
                    params: {
                        currentCount: this.currentCount,
                        plateId: this.$store.state.tabIndex.toString()
                    }
                })
                    .then(res => {
                        if (res.code == "999") {
                            // this.count == 20;
                        } else {
                            for (let i = 0; i < res.data.length; i++) {
                                this.postList.push(res.data[i]);
                            }
                            this.currentCount += 6;
                            console.log(this.postList);
                        }
                    })
            }
        },
        loadComment(postId) {
            this.commentLinkPostId = postId;
            this.drawer = true;
            //向后台发送请求，查询该帖子相关的所有评论
            this.request.get('comment/get',
                {
                    params: {
                        postId: this.commentLinkPostId
                    }
                }).then(res => {
                    this.commentList = res.data;
                })

        },
        sendComment() {
            //定义对象结构
            const comment = {
                postId: this.commentLinkPostId,
                userId: JSON.parse(localStorage.getItem("user")).userId.toString(),
                commentContent: this.commentContent
            }
            console.log(comment);
            this.request.post('/comment/add', JSON.stringify(comment))
                .then(res => {
                    //接收后台返回数据
                    console.log(res.code);
                    const code = res.code;
                    if (code == '200') {
                        this.sendPostSuccess(res.data);
                        this.loadComment(this.commentLinkPostId);
                    } else {
                        this.sendPostFail(res.data);
                    }
                })
        },
        deletePost(postId) {
            this.$confirm('确认删除?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                console.log(postId);
                //发送删除请求
                this.request.delete('/post/del', {
                    params: {
                        postId: postId
                    }
                })
                    .then(res => {
                        console.log(res.code);
                    })
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
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
        }

    }
}


</script>

<style scoped>
.el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
}

.editComment {
    margin-left: 7%;
    margin-right: 5%;
}


.comment-card {
    margin-right: 5%;
}

.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}

.clearfix:after {
    clear: both
}

.box-card {
    width: 75em;
}

::-webkit-scrollbar {
    display: none;
    /* 隐藏滚动条 */
}

.postCSS {
    margin-top: 1%;
    height: 50em;
}
</style>
