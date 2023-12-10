<template>
    <div class="postCSS">
        <ul class="infinite-list" v-infinite-scroll="load"
            style="overflow:auto; height: 49em; width: 80em; list-style: none;" infinite-scroll-disabled="disabled">
            <li v-for="item in postList" class="infinite-list-item">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span style="font-weight: bolder;">{{ item.postTitle }}</span>
                        <el-button style="float: right; padding: 3px 0" type="text"
                            @click="loadComment(item.postID)">查看评论</el-button>
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
            <ul v-for="(item, index) in commentList" :key="index" style="list-style: none;">
                <li>
                    <el-card class="comment-card">
                        <div slot="header" class="clearfix">
                            <span>{{ item.userName }}:</span>
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
                <el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="textarea" maxlength="255" show-word-limit
                    style="margin-top: 1%;">
                </el-input>
                <el-button style="float: right;" type="primary" plain @click="sendComment">发布</el-button>
            </div>

        </el-drawer>
    </div>
</template>

<script>
export default {
    data() {
        return {
            count: 0,
            loading: false,
            drawer: false,
            direction: 'rtl',
            postList: [],
            currentCount: 0,
            commentList: [
                {
                    commentID: '1',
                    postID: '',
                    userID: '894651',
                    userName: '用户1',
                    commentContent: '这是第一段评论'
                },
                {
                    commentID: '2',
                    postID: '',
                    userID: '894651',
                    userName: '用户2',
                    commentContent: '这是第二段评论'
                },
                {
                    commentID: '3',
                    postID: '',
                    userID: '894651',
                    userName: '用户3',
                    commentContent: '这是第三段评论'
                }
            ],
            textarea: ''
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
        load() {
            this.loading = false;
            //请求数据
            this.request.get('/post/get', this.currentCount)
                .then(res => {
                    this.postList = res;
                    console.log(res);
                })
            this.currentCount+= this.postList.length;
        },
        loadComment(postID) {
            console.log(postID);
            this.drawer = true;
            for (let i = 0; i < this.commentList.length; i++) {
                this.commentList[i].postID = postID;
            }
        },
        sendComment() {
            alert(this.textarea)
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