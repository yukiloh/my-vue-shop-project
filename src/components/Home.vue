<template>

  <el-button type="info" @click="logout">退出</el-button>
</template>

<script>
  export default {
    name: "Home",
    methods: {
      //退出功能,清空sessionStorage
      logout() {
        window.sessionStorage.clear();
        this.$router.push('login');
        this.$message.success('退出成功！');
      }
    }

  }
</script>

<style scoped>

</style>