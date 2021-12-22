<template>
  <div style="background-color:  #944dff;width: 100%;height: 100vh;overflow: hidden ">
    <div style="width: 400px;margin: 150px auto">
      <div style="color: #00e6b8;text-align: center;padding: 30px 0;font-size:30px ">欢迎注册</div>
      <el-form ref="form" :model="form" :rules="rules">
        <el-form-item prefix-icon="i-user" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item prefix-icon="i-lock" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" show-password></el-input>
        </el-form-item>
        <el-form-item prefix-icon="i-lock" prop="confirm">
          <el-input v-model="form.confirm" placeholder="请再次输入密码" show-password></el-input>
        </el-form-item>
        
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="register">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Register",
  data() {
    return {
      form: {},
      rules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}],
        confirm: [{required: true, message: '请再次输入密码', trigger: 'blur'}]

      }
    }
  },
  methods: {
    register() {
    //  let params = new URLSearchParams();
     
    //    params.append("code", this.form.code);
    //   request.post("/delete_a_book", params).then((res) => {
    //     this.loading = false;
    //     this.tableData = res.data;
    //   });
    }
  }
}
</script>

<style scoped>

</style>