<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="margin-bottom: 10px">
     
     
      <el-input
     v-model="fieldValue"
        placeholder="请输入图书名称"
        style="width: 20%; margin-left: 10px"
        clearable
      ></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load2" >查询</el-button
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
            type="primary"
            size="mini"
            @click="borrowInfo(scope.row)"
            v-if="this.user.stu_name !== '管理员'"
            >借阅</el-button
          >
          <el-button
            size="mini"
            @click="handleEdit(scope.row)"
            v-if="this.user.stu_name !== '管理员'"
            >查看</el-button
          >
          <el-button
            type="danger"
            plain
            size="mini"
            @click="scrapInfo(scope.row)"
            v-if="this.user.stu_name === '管理员'"
            >报废处理</el-button
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

      <el-dialog title="图书信息" v-model="dialogVisible" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="图书编号">
            <el-input
              v-model="form.code"
              style="width: 80%"
              disabled
            ></el-input>
          </el-form-item>
          <el-form-item label="图书名称">
            <el-input
              v-model="form.book_name"
              style="width: 80%"
              disabled
            ></el-input>
          </el-form-item>
          <el-form-item label="课程名">
            <el-input
              v-model="form.course_name"
              style="width: 80%"
              disabled
            ></el-input>
          </el-form-item>
          <el-form-item label="分类一">
            <el-input
              v-model="form.kp1_name"
              style="width: 80%"
              disabled
            ></el-input>
          </el-form-item>
          <el-form-item label="分类二">
            <el-input
              v-model="form.kp2_name"
              style="width: 80%"
              disabled
            ></el-input>
          </el-form-item>
          <el-form-item label="所在位置">
            <el-input v-model="form.pos" style="width: 80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="位置编号">
            <el-input
              v-model="form.shelf_code"
              style="width: 80%"
              disabled
            ></el-input>
          </el-form-item>
      
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">确认</el-button>
          </span>
        </template>
      </el-dialog>

      <el-dialog title="借阅信息确认" v-model="dialogVisible2" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="借阅人">
            <el-input
              v-model="form2.stu_name"
              style="width: 80%"
              disabled
            ></el-input>
          </el-form-item>
          <el-form-item label="图书编号">
            <el-input
              v-model="form.code"
              style="width: 80%"
              disabled
            ></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible2 = false">取 消</el-button>
            <el-button type="primary" @click="borrow" :plain="true">确 定</el-button>
          </span>
        </template>
        
      </el-dialog>
      <el-dialog title="报废处理确认" v-model="dialogVisible3" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="处理人">
            <el-input
              v-model="this.user.stu_name"
              style="width: 80%"
              disabled
            ></el-input>
          </el-form-item>
          <el-form-item label="图书编号">
            <el-input
              v-model="form.code"
              style="width: 80%"
              disabled
            ></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible3 = false">取 消</el-button>
            <el-button type="primary" @click="scrap">确 定</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import {  ref } from 'vue'
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
      pageSize: 10,
      fieldValue: "",
      total:10,
      tableData: [],
      ids: [],
    };
  },
  created() {
    this.load();
  },
  methods: {
    //报废处理
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
    //报废信息确认
    scrapInfo(row) {
      console.log(this.user.stu_id);
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible3 = true;
    },

    handleSelectionChange(val) {
      this.ids = val.map((v) => v.id); // [{id,name}, {id,name}] => [id,id]
    },
   
    load() {
      let str = sessionStorage.getItem("user") || "{}";
      this.user = JSON.parse(str);
     
      this.loading = true;
      let params = new URLSearchParams();
      params.append("field_value", this.fieldValue);
      params.append("field_name", "book_id");
      params.append("current_page", this.currentPage);
      params.append("page_size", this.pageSize);
    

      request.post("/get_book_list", params).then((res) => {
        this.loading = false;
        this.tableData = res.data;
      });
    },
    //查询
    load2() {
      console.log(this.fieldValue);
      let str = sessionStorage.getItem("user") || "{}";
      this.user = JSON.parse(str);
     
      this.loading = true;
      let params = new URLSearchParams();
      params.append("field_value", this.fieldValue);
      params.append("field_name", "book_name");
      params.append("current_page", this.currentPage);
      params.append("page_size", this.pageSize);
     

      request.post("/get_book_list", params).then((res) => {
        this.loading = false;
        this.tableData = res.data;
      });
    },
    
    handleEdit(row) {
   
      let params = new URLSearchParams();
      console.log(row.code + this.user.stu_id);

      params.append("code", row.code);
      request.post("/show_book_info", params).then((res) => {
        this.form = res.data;
      });
      this.dialogVisible = true;
    },
    //借阅
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
      ElMessage({
        message: '借阅成功！',
        type: 'success',
      })
      this.load();
    },
    //借阅信息
    borrowInfo(row) {
      let str = sessionStorage.getItem("user") || "{}";
      this.form2 = JSON.parse(str);

      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible2 = true;
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