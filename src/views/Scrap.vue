<template>
  <div style="padding: 10px">
    <!--    报废记录功能区域-->
    <div style="margin-bottom: 10px">
     
      <el-input
        v-model="search"
        placeholder="请输入关键字"
        style="width: 20%; margin-left: 10px"
        clearable
      ></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load"
        >查询</el-button
      >
    </div>
    <!--    搜索区域-->

    <el-table :data="tableData" size="mini" border stripe style="width: 99%">
      <!--        @selection-change="handleSelectionChange"-->
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column prop="code" label="图书编号" sortable> </el-table-column>
      <el-table-column prop="book_name" label="图书名称"> </el-table-column>
      <el-table-column prop="course_name" label="课程名"> </el-table-column>
      <el-table-column prop="kp1_name" label="分类一"> </el-table-column>
      <el-table-column prop="kp2_name" label="分类二"> </el-table-column>
      <el-table-column label="封面">
        <template #default="scope">
          <el-image
            style="width: 100px; height: 100px"
            :src="scope.row.cover"
            :preview-src-list="[scope.row.cover]"
          >
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180">
        <template #default="scope">
          <el-button
            type="danger"
            plain
            size="mini"
            @click="undo(scope.row)"
          
            >撤销</el-button
          >
      </template>
      </el-table-column>
    </el-table>
    <div style="margin-left: 400px">
        <el-pagination
        v-model:currentPage="currentPage"
        :page-sizes="[5, 10, 15]"
        :page-size="pageSize"
        layout=" sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      >
      </el-pagination>

    
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Book",
  props: ["user"],
  components: {},
  data() {
    return {
      user: {},
      loading: true,
      form: {},
      form2: {},
      dialogVisible: false,
      dialogVisible2: false,
      dialogVisible3: false,
      search: "",
      currentPage: 1,
      pageSize: 5,
      fieldValue: "",
      total: 8,
      tableData: [],
      ids: [],
    };
  },
  created() {
    this.load();
  },
  methods: {
      undo(row){
            this.form2 = JSON.parse(JSON.stringify(row));
      let params = new URLSearchParams();
      params.append("field_value", this.fieldValue);
      params.append("field_name", "book_id");
      params.append("current_page", this.currentPage);
      params.append("page_size", this.pageSize);
       params.append("code", this.form2.code);
      request.post("/undo_delete", params).then((res) => {
        this.loading = false;
        this.tableData = res.data;
      });
     this.load();
      },
    scrap() {
      let params = new URLSearchParams();
     
       params.append("code", this.form.code);
      request.post("/delete_a_book", params).then((res) => {
        this.loading = false;
        this.tableData = res.data;
      });
      this.dialogVisible3 = false;
      this.load();

    },
    scrapInfo(row) {
      console.log(this.user.stu_id);
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible3 = true;
    },

    deleteBatch() {
      if (!this.ids.length) {
        this.$message.warning("请选择数据！");
        return;
      }
      request.post("/book/deleteBatch", this.ids).then((res) => {
        if (res.code === "0") {
          this.$message.success("批量删除成功");
          this.load();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    handleSelectionChange(val) {
      this.ids = val.map((v) => v.id); // [{id,name}, {id,name}] => [id,id]
    },
    filesUploadSuccess(res) {
      console.log(res);
      this.form.cover = res.data;
    },
    load() {
     
      this.loading = true;
      let params = new URLSearchParams();
      params.append("field_value", this.fieldValue);
      params.append("field_name", "book_id");
      params.append("current_page", this.currentPage);
      params.append("page_size", this.pageSize);
 

      request.post("/show_delete_recode", params).then((res) => {
        this.loading = false;
        this.tableData = res.data;
      });
    },
    add() {
      this.dialogVisible = true;
      this.form = {};
      if (this.$refs["upload"]) {
        this.$refs["upload"].clearFiles(); // 清除历史文件列表
      }
    },
    save() {
      // if (this.form.id) {  // 更新
      //   request.put("/book", this.form).then(res => {
      //     console.log(res)
      //     if (res.code === '0') {
      //       this.$message({
      //         type: "success",
      //         message: "更新成功"
      //       })
      //     } else {
      //       this.$message({
      //         type: "error",
      //         message: res.msg
      //       })
      //     }
      //     this.load() // 刷新表格的数据
      //     this.dialogVisible = false  // 关闭弹窗
      //   })
      // }  else {  // 新增
      //   request.post("/book", this.form).then(res => {
      //     console.log(res)
      //     if (res.code === '0') {
      //       this.$message({
      //         type: "success",
      //         message: "新增成功"
      //       })
      //     } else {
      //       this.$message({
      //         type: "error",
      //         message: res.msg
      //       })
      //     }

      //     this.load() // 刷新表格的数据
      //     this.dialogVisible = false  // 关闭弹窗
      //   })
      // }
      this.dialogVisible = false; // 关闭弹窗
    },
    handleEdit(row) {
      // this.form = JSON.parse(JSON.stringify(row))
      // this.dialogVisible = true
      // this.$nextTick(() => {
      //   if (this.$refs['upload']) {
      //     this.$refs['upload'].clearFiles()  // 清除历史文件列表
      //   }
      // })
      let params = new URLSearchParams();
      console.log(row.code + this.user.stu_id);

      params.append("code", row.code);
      request.post("/show_book_info", params).then((res) => {
        this.form = res.data;
      });
      this.dialogVisible = true;
    },
    borrow() {
      let params = new URLSearchParams();
      console.log(this.form2.stu_id);
      params.append("stu_id", this.form2.stu_id);
      params.append("code", this.form.code);
      request.post("/borrow_book", params).then((res) => {
        this.loading = false;
        this.tableData = res.data;
      });
      this.dialogVisible2 = false;
      this.load();
    },

    borrowInfo(row) {
      let str = sessionStorage.getItem("user") || "{}";
      this.form2 = JSON.parse(str);

      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible2 = true;
    },
    handleDelete(id) {
      console.log(id);
      request.delete("/book/" + id).then((res) => {
        if (res.code === "0") {
          this.$message({
            type: "success",
            message: "删除成功",
          });
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
        this.load(); // 删除之后重新加载表格的数据
      });
    },
    handleSizeChange(pageSize) {
      // 改变当前每页的个数触发
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(currentPage) {
      // 改变当前页码触发
      this.currentPage = currentPage;
      this.load();
    },
  },
};
</script>