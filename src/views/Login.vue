<template>
  <div
    style="
      background-color: #9900ff;
      width: 100%;
      height: 100vh;
      overflow: hidden;
    "
  >
    <div style="width: 400px; margin: 150px auto">
      <div
        style="
          color: #00e6b8;
          text-align: center;
          padding: 30px 0;
          font-size: 30px;
        "
      >
        图书管理系统
      </div>
      <el-form ref="form" :model="form">
        <el-form-item prop="username">
          <el-input
            v-model="form.username"
            prefix-icon="i-user"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="form.pwd"
            prefix-icon="i-lock"
            placeholder="请输入密码"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item prop="select" style="color: #000000">
          <el-radio-group v-model="form.select" style="margin-left:100px">
            <el-radio   label="学生"  style="color: #000000"></el-radio>
            <el-radio   label="管理员" style="color: #000000"></el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="login"
            >登录</el-button
          >
        </el-form-item>
        <el-form-item>
          <el-button
            style="width: 100%"
            type="primary"
            @click="$router.push('/register')"
            >注册</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request"


export default {
  name: "Login",

  data() {
    return {
      form: {},
    };
  },

  methods: {
    login() {
      let params = new URLSearchParams();
      params.append('username', this.form.username);
      params.append('pwd', this.form.pwd);
      params.append('select', this.form.select);
      this.$refs["form"].validate((valid) => {
        if (valid) {
          console.log(this.form);
          request.post("/check_login",params).then((res) => {
            if (res.code === "0") {
              this.$message({
                type: "success",
                message: "登录成功！",
              });
              sessionStorage.setItem("user", JSON.stringify(res.data)); //缓存用户信息
              this.$router.push("/"); //跳转
            } else {
              
              this.$message({
                type: "error",
                message: res.msg,
              });
            }
          });
        }
      });
    },
  },
};
</script>

<style scoped>
</style>