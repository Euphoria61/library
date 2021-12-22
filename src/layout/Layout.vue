<template>
  <div>
    <!--   头部-->
    <Header :user="user"/>
    <!--   主体-->
    <div style="display: flex">
      <!--     侧边栏-->
      <Aside :user="user"/>
      <!--     内容-->
      <router-view style="flex: 1"  @userInfo="refreshUser" :user="user"/>
    </div>
  </div>

</template>

<style>


</style>
<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";

export default {
 name: "Layout",
 
  components: {
    Header,
    Aside
  },
  data() {
    return {
      user: {}
    }
  },
  created() {
    this.refreshUser()
  },
  methods: {
    refreshUser() {
     
     let str = sessionStorage.getItem("user") || "{}"
     this.user = JSON.parse(str)
   
    }
  }
}
</script>
