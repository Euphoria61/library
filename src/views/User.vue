<template>
  <div class="home" style="padding-left: 10px">
    <!--    功能区-->
    <div style="margin: 10px">
      <el-button round type="primary" @click="add">新增</el-button>
    </div>
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" clearable placeholder="请输入关键字" style="width: 20%"/>
      <el-button style="margin-left:5px" type="success" @click="load">查询</el-button>

    </div>
    <!--    表格-->
    <el-table :data="tableData" stripe style="width: 100%;">
      <el-table-column label="ID" prop="id"/>
      <el-table-column label="用户名" prop="username"/>
      <el-table-column label="昵称" prop="nickName"/>
      <el-table-column label="年龄" prop="age"/>
      <el-table-column label="性别" prop="sex"/>
      <el-table-column label="地址" prop="address"/>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="mini" @click="handleEdit(scope.row)"
          >编辑
          </el-button
          >
          <el-popconfirm title="确定删除吗?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button
                  size="mini"
                  type="danger"

              >删除
              </el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!--    分页-->
    <div class="demo-pagination-block" style="margin: 10px 0;text-align: center">
      <el-pagination
          v-model:currentPage="currentPage"
          :page-size="pageSize"
          :page-sizes="[5, 10, 20]"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
    <el-dialog
        v-model="dialogVisible"
        title="提示"
        width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="用户名">
          <el-input v-model="form.username" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickName" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio v-model="form.sex" label="男">男</el-radio>
          <el-radio v-model="form.sex" label="女">女</el-radio>
          <el-radio v-model="form.sex" label="其他">其他</el-radio>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" style="width: 80%" type="textarea"></el-input>
        </el-form-item>
        <!--        气泡提示框-->
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'Home',
  components: {},
  data() {
    return {
      form: {},
      search: '',
      pageSize: 10,
      currentPage: 1,
      total: 0,
      dialogVisible: false,
      tableData: [],
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/user", {//不是post
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    add() {
      this.dialogVisible = true;
      this.form = {}
    },
    save() {
      if (this.form.id) {
        request.put("/user", this.form).then(res => {
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "更新成功！"
            })
          } else {
            this.$message({
              type: "error",
              message: "更新失败！"
            })
          }
          //刷新数据
          this.load()
          //关闭弹窗
          this.dialogVisible = false
        })
      } else {
        request.post("/user", this.form).then(res => {
          this.$message({
            type: "success",
            message: "新增成功！"
          })
          //刷新数据
          this.load()
          //关闭弹窗
          this.dialogVisible = false
        })
      }
    },

    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row)); //避免取消扔更改数据--》进行深拷贝
      this.dialogVisible = true;
    },
    handleDelete(id) {
      console.log(id)
      request.delete("/user/" + id).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "删除成功！"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        //刷新数据
        this.load()
      })

    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    }
  },

}
</script>
