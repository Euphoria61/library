<template>
  <div>
    <el-page-header content="个人信息" style="margin-top: 10px;margin-left: 10px" @back="goBack"/>
    <el-card style="width: 40%; margin: 10px">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label-width="0" style="text-align: center">
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="form.stu_id" disabled></el-input>
        </el-form-item>
        
        <el-form-item label="姓名">
          <el-input v-model="form.stu_name"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="form.sex"></el-input>
        </el-form-item>
        <el-form-item label="生日">
          <el-input v-model="form.birth"></el-input>
        
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.home"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.pwd" show-password></el-input>
        </el-form-item>
      </el-form>
      <div style="text-align: center">
        <el-button type="primary" @click="update" :plain="true">保存</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import request from "@/utils/request";
import router from "@/router";
import { ElMessage } from 'element-plus'
export default {
  name: "Person",
  data() {
    return {
      form: {}
    }
  },
  created() {
    let str = sessionStorage.getItem("user") || "{}"
    this.form = JSON.parse(str)
  },
  methods: {
    update() {
    let params = new URLSearchParams();
 params.append("studentInfo", this.form);
      request.post("/update_stu_info", params).then((res) => {
        this.courseInfoLists = res.data;
        console.log(this.courseInfoLists);
      });
 ElMessage({
        message: '成功更改信息！',
        type: 'success',
      })

    },
    goBack() {
      router.push("/")
    }
  }
}
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>