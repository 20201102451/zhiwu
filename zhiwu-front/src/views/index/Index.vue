<template>
  <el-container>
    <el-header>
      <Header @toMySpace="toMySpace"></Header>
    </el-header>
    <Container_Main ref="container_main"></Container_Main>
  </el-container>
</template>


<script>
import Header from '@/views/index/Header.vue';
import Container_Main from './Container_Main.vue';

export default {
  components: {
    Header,
    Container_Main
  },
  data() {
    return {}
  },
  methods: {
    toMySpace(){
      console.log('here')
      this.$refs['container_main'].curTab="我的空间"
    }
  },
}


</script>

<style scoped>
.el-header {
  background-color: #FFF;
  color: #333;
  text-align: center;
  line-height: 60px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

body > .el-container {
  margin-bottom: 40px;
}

</style>