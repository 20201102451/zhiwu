<template>
    <div>
        <div class="header-user">
            
            <label>
                <div class="avatar"><el-avatar :size="40" :src="circleUrl"></el-avatar></div>

                <el-dropdown trigger="click">
                    <span class="el-dropdown-link">{{ username }}<i class="el-icon-arrow-down el-icon--right"></i></span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item icon="el-icon-user-solid">个人中心</el-dropdown-item>
                        <el-dropdown-item icon="el-icon-remove">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </label>
        </div>
    </div>
</template>


<script>
export default {
    data() {
        return {
            username: "20201102451",
            circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
        }
    },
    methods: {
        
    }
}


</script>

<style scoped>
.header-user {
    /* display: inline-block; */
    position: absolute;
    right: 10%;

    .el-dropdown-link {
        cursor: pointer;
        color: black;
    }

    .avatar {
        position: absolute;
        right: 110%;
        top: 20%;
    }
}
</style>