<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div  style="margin-bottom:10px">
  
      
     <el-input v-model="search" placeholder="请输入关键字" style="width: 20%;margin-left:10px" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div >
    <!--    搜索区域-->
 
    <el-table
       
        :data="tableData"
        
        border
        stripe
        style="width: 99%">
<!--        @selection-change="handleSelectionChange"-->
  <el-table-column
 
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          prop="borrow_time"
          label="借阅日期"
          sortable
      >
      </el-table-column>
      <el-table-column
          prop="book_name"
          label="图书名称">
      </el-table-column>
        <el-table-column
          prop="code"
          label="图书编号">
      </el-table-column>
      <el-table-column
          prop="time"
          label="剩余时间(天)">
      </el-table-column>
        <el-table-column
          prop="cost"
          label="费用">
      </el-table-column>
     <el-table-column label="操作" width="180">
        <template #default="scope">
          <el-button size="mini" @click="returnbook(scope.row)">归还图书</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-left:400px">
    
         <el-pagination
      v-model:currentPage="currentPage"
      :page-sizes="[5, 10,15]"
      :page-size="pageSize"
      layout=" sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    >
    </el-pagination>

      <el-dialog title="还书信息" v-model="dialogVisible" width="30%">
        <el-form :model="form" label-width="120px">
              <el-form-item label="还书人">
            <el-input v-model="form2.stu_name" style="width: 80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="图书编号">
            <el-input v-model="form.code" style="width: 80%" disabled></el-input>
          </el-form-item>
        
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button @click="toreturn" :plain="true">确认</el-button>
        
          </span>
        </template>
      </el-dialog>


    </div>
  </div>
</template>

<script>


import request from "@/utils/request";

export default {
  name: 'Book',
  components: {

  },
  data() {
    return {
      user: {},
      loading: true,
      form: {},
       form2: {},
      dialogVisible: false,
      
      search: '',
      currentPage: 1,
      pageSize: 10, 
      fieldValue:"",
     total:0,
      tableData: [],
     ids: []
    }
  },
  created() {
  this.load()
  },
  methods: {

   
    handleSelectionChange(val) {
      this.ids = val.map(v => v.id)   // [{id,name}, {id,name}] => [id,id]
    },
  
    load() {
      this.loading = true
        let str = sessionStorage.getItem("user") || "{}"
    this.form2 = JSON.parse(str)
       let params = new URLSearchParams();
      params.append('stu_id',this.form2.stu_id);

     
      request.post("/get_my_info",params).then(res => {
        this.loading = false
        this.tableData = res.data
     
     
      })
    },
       toreturn(){

      let params = new URLSearchParams();
      console.log(this.form2.stu_id);
      params.append('stu_id',this.form2.stu_id);
      params.append('code',this.form.code);
      request.post("/return_book",params).then(res => {
        this.loading = false
         this.tableData = res.data
      })
       this.dialogVisible = false
        ElMessage({
        message: '还书成功！',
        type: 'success',
      })
       this.load()
       
    },
   
 returnbook(row){
        let str = sessionStorage.getItem("user") || "{}"
    this.form2 = JSON.parse(str)
    
       this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
  
    
    handleSizeChange(pageSize) {   // 改变当前每页的个数触发
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(currentPage) {  // 改变当前页码触发
      this.currentPage = currentPage
      this.load()
    }
  }
}
</script>