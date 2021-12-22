<template>
  <div style="padding: 10px">
    <!--    库存管理功能区域-->
    <div style="margin-bottom: 10px">
      <el-button type="success" plain @click="addnew">新建图书</el-button>
      <el-button type="success" plain @click="add">图书入库</el-button>

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
            type="success"
            plain
            size="mini"
            @click="putupInfo(scope.row)"
            >图书上架</el-button
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

      <el-dialog title="图书上架" v-model="dialogVisible" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="图书编号">
            <el-input
              v-model="form.code"
              style="width: 80%"
              disabled
            ></el-input>
          </el-form-item>

          <el-form-item label="书架号" prop="region">
            <el-select v-model="form.selectregion" placeholder="请选择书架号">
              <el-option
                v-for="(region, index) in regions"
                :key="index"
                :label="region.shelf_code + region.pos"
                :value="region.shelf_id"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button @click="putup">确认</el-button>
          </span>
        </template>
      </el-dialog>
      <el-dialog title="图书入库" v-model="dialogVisible2" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="图书编号">
            <el-input v-model="form.code" style="width: 80%"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible2 = false">取消</el-button>
            <el-button @click="putin">确认</el-button>
          </span>
        </template>
      </el-dialog>

      <el-dialog title="新建图书" v-model="dialogVisible3" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="图书名称">
            <el-input v-model="form.book_name" style="width: 80%"></el-input>
          </el-form-item>

          <el-form-item label="课程" prop="course">
            <el-select v-model="form.selectcourse" placeholder="请选择课程">
              <el-option
                v-for="(courseInfoList, index) in courseInfoLists"
                :key="index"
                :label="courseInfoList.course_name"
                :value="courseInfoList.course_id"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="分类一" prop="kp1">
            <el-select v-model="form.selectkp1" placeholder="请选择分类一">
              <el-option
                v-for="(kp1InfoList, index) in kp1InfoLists"
                :key="index"
                :label="kp1InfoList.kp1_name"
                :value="kp1InfoList.kp1_id"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="分类二" prop="kp2">
            <el-select v-model="form.selectkp2" placeholder="请选择分类二">
              <el-option
                v-for="(kp2InfoList, index) in kp2InfoLists"
                :key="index"
                :label="kp2InfoList.kp2_name"
                :value="kp2InfoList.kp2_id"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible3 = false">取消</el-button>
            <el-button @click="confirmAdd">确认</el-button>
          </span>
        </template>
      </el-dialog>
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
      form3: {},
      dialogVisible: false,
      dialogVisible2: false,
      dialogVisible3: false,
      search: "",
      currentPage: 1,
      pageSize: 10,
      fieldValue: "",
      total: 10,
      tableData: [],
      ids: [],
      regions: "",
      selectcourse: "",
      selectkp1: "",
      selectkp2: "",

      courseInfoLists: "",
      kp1InfoLists: "",
      kp2InfoLists: "",
    };
  },
  created() {
    this.load();
  },
  methods: {
    confirmAdd() {
      let params = new URLSearchParams();
      params.append("book_name", this.form.book_name);
      params.append("course_id", this.form.selectcourse);
      params.append("kp1_id", this.form.selectkp1);
      params.append("kp2_id", this.form.selectkp2);

      request.post("/insert_a_book", params).then((res) => {});
      this.dialogVisible3 = false;
      this.load();
    },

    addnew() {
      this.form = {};
      this.dialogVisible3 = true;
      let params = new URLSearchParams();

      request.post("/show_book_insert_courseInfoList", params).then((res) => {
        this.courseInfoLists = res.data;
        console.log(this.courseInfoLists);
      });
      request.post("/show_book_insert_kp1InfoList", params).then((res) => {
        this.kp1InfoLists = res.data;
        console.log(this.kp1InfoLists);
      });
      request.post("/show_book_insert_kp2InfoList", params).then((res) => {
        this.kp2InfoLists = res.data;
        console.log(this.kp2InfoList);
      });
    },

    putin() {
      let params = new URLSearchParams();
      console.log(this.form.code);
      params.append("code", this.form.code);
      request.post("/put_book_into_inventory", params).then((res) => {});
      this.dialogVisible2 = false;
      this.load();
    },
    putupInfo(row) {
      this.form = JSON.parse(JSON.stringify(row));
      let params = new URLSearchParams();
      params.append("field_value", this.fieldValue);
      params.append("field_name", "book_id");
      params.append("current_page", this.currentPage);
      params.append("page_size", this.pageSize);
      params.append("code", this.form.code);
      request.post("/show_book_put", params).then((res) => {
        this.loading = false;
        this.regions = res.data;
        console.log(this.regions);
      });
      this.dialogVisible = true;
    },
    putup() {
      let params = new URLSearchParams();
      params.append("field_value", this.fieldValue);
      params.append("field_name", "book_id");
      params.append("current_page", this.currentPage);
      params.append("page_size", this.pageSize);
      params.append("code", this.form.code);
      params.append("shelf_id", this.form.selectregion);
      request.post("/put_book_into_shelf", params).then((res) => {});
      this.dialogVisible = false;
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
      request.post("/show_book_inventory", params).then((res) => {
        this.loading = false;
        this.tableData = res.data;
      });
    },
    add() {
      this.dialogVisible2 = true;
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