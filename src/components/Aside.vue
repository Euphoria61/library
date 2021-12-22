<template>
  <div>
    <el-menu
        class="el-menu-vertical-demo"
        :default-active="path"
        router
        style="width: 170px;min-height: calc(100vh - 50px)">
    
       <el-menu-item index="/plan">书架平面图</el-menu-item>
       <el-menu-item index="/book">图书列表</el-menu-item>
       <el-menu-item index="/borrow"  v-if="this.user.stu_name !=='管理员'">借阅记录</el-menu-item>
       <el-menu-item index="/return"  v-if="this.user.stu_name !=='管理员'">归还记录</el-menu-item>
       <el-menu-item index="/person"  v-if="this.user.stu_name !=='管理员'">个人中心</el-menu-item>
       <!-- <el-menu-item index="/scrap"  v-if="this.user.stu_name ==='管理员'">报废记录</el-menu-item> -->
       <el-menu-item index="/stock"  v-if="this.user.stu_name ==='管理员'">库存管理</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside",
  props: ['user'],
  data(){
    return {
      path: this.$route.path
    }
  }
}
</script>

<style scoped>

</style>