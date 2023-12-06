<template>
    <el-container>
        <el-aside width="200px">
            <el-tabs :tab-position="tabPosition" style="height: 200px;" @tab-click="getDataHandler">
                <el-tab-pane label="单身广场"></el-tab-pane>
                <el-tab-pane label="曝光板块"></el-tab-pane>
                <el-tab-pane label="角色管理"></el-tab-pane>
                <el-tab-pane label="管理员小公告"></el-tab-pane>
            </el-tabs>
        </el-aside>
        <el-main>
            <Post></Post>
        </el-main>
    </el-container>
</template>

<script>
import Post from './Post.vue';
export default {
    components: {
        Post
    },
    data() {
        return {
            tabPosition: 'left',

        }
    },
    methods: {
        getDataHandler(tab, event) {
            console.log(tab, event);
            console.log(tab.index)
            if (tab.index == 0) {

            } else if (tab.index == 1) {

            } else if (tab.index == 2) {

            } else if (tab.index == 3) {

            }
        }
    }
}


</script>

<style scoped>
.el-aside {
    margin-top: 1%;
    background-color: #E9EEF3;
    /*#E9EEF3*/
    color: #333;
    text-align: center;
    line-height: 200px;
}

.el-main {
    margin-top: 1%;
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
}

body>.el-container {
    margin-bottom: 40px;
}
</style>