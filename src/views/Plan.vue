<template>
  <div>
    <div><h1>书架平面图</h1></div>
    <div class="plan">
      <el-tabs
        :tab-position="tabPosition"
        style="height: 420px"
        @tab-click="handleclick"
      >
        <el-tab-pane label="一楼"  >
          <el-row>
            <el-col v-for="o in 8" :key="o" :span="6">
              <el-card class="box-card" shadow="hover">
                <template #header>
                  <div class="card-header">
                    <span>书架号A-{{o}}</span>
                    <el-button class="button" type="text" @click="first">查看更多</el-button>
                  </div>
                </template>
                <div v-for="list in lists" :key="list" class="text item">
                  {{ list }}
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-tab-pane>

        <el-tab-pane label="二楼"  >
          <el-row  >
            <el-col v-for="o in 8" :key="o" :span="6">
              <el-card class="box-card" shadow="hover" >
                <template #header>
                  <div class="card-header" >
                    <span>书架号G-{{o}}</span>
                    <el-button class="button" type="text"  @click="second">查看更多</el-button>
                  </div>
                </template>
                <div v-for="list2 in list2s" :key="list2" class="text item">
                  {{ list2 }}
                </div>
              </el-card>
            </el-col>
          </el-row></el-tab-pane
        >
        <el-tab-pane label="三楼">
          <el-row>
            <el-col v-for="o in 8" :key="o" :span="6">
              <el-card class="box-card" shadow="hover"> 
                <template #header>
                  <div class="card-header">
                    <span>书架号M-{{ o }}</span>
                    <el-button class="button" type="text"  @click="third">查看更多</el-button>
                  </div>
                </template>
                <div v-for="list in lists" :key="list" class="text item">
                  {{ list }}
                </div>
              </el-card>
            </el-col>
          </el-row></el-tab-pane
        >
        <!-- B,C,D,E,F,G,H,I,G,K -->
      </el-tabs>
      <el-dialog title="书架号:A" v-model="dialogVisible" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="第一层">
            <p v-for="shelf1 in shelf1s" :key="shelf1">
                {{shelf1.book_name}}
            </p>
          </el-form-item>
          <el-form-item label="第二层">
            
          </el-form-item>
           <el-form-item label="第三层">
           
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
           
            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
          </span>
        </template>
      </el-dialog>


       <el-dialog title="书架号:G" v-model="dialogVisible2" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="第一层">
            <p v-for="shelf2 in shelf2s" :key="shelf2">
                {{shelf2.book_name}}
            </p>
          </el-form-item>
          <el-form-item label="第二层">
            
          </el-form-item>
           <el-form-item label="第三层">
           
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
      
            <el-button type="primary" @click="dialogVisible2 = false">确 定</el-button>
          </span>
        </template>
      </el-dialog>


       <el-dialog title="书架号:M" v-model="dialogVisible3" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="第一层">
            <p v-for="shelf3 in shelf3s" :key="shelf3">
                {{shelf3.book_name}}
            </p>
          </el-form-item>
          <el-form-item label="第二层">
            
          </el-form-item>
           <el-form-item label="第三层">
           
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
     
            <el-button type="primary" @click="dialogVisible3 = false">确 定</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  data() {
    return {
      tabPosition: "left",
      lists: ["JAVA程序设计", "C++程序设计", "数据结构", "安卓高级开发"],
      list2s: ["英语阅读", "英语翻译"],
      shelf1s: [],
      shelf2s: [],
      shelf3s: [],
      level: 1,
      i:0,
    dialogVisible:false,
    dialogVisible2:false,
    dialogVisible3:false,
    };
  },
    created() {
      this.load();
    },
  methods: {
    load() {
      let params = new URLSearchParams();
      params.append("level", this.level);
      console.log(this.level);
      request.post("/get_level", params).then((res) => {
        this.loading = false;
        this.tableData = res.data;
      });
    },
    // handleclick(tab, event) {
    //   console.log(tab, event);
    //   if (tab.index == 1) {
    //     //网络请求1
    //     let params = new URLSearchParams();
    //     params.append("level", "2");
    //     console.log(params);
    //     request.post("/get_level", params).then((res) => {
    //       this.shlef2 = res.data;
    //       console.log(this.shlef2);
    //     });
    //     console.log("请求1");
    //   } else if (tab.index == 2) {
    //     //网络请求2
    //       let params = new URLSearchParams();
    //     params.append("level", "3");
    //     console.log(params);
    //     request.post("/get_level", params).then((res) => {
    //       this.shlef3 = res.data;
    //       console.log(this.shlef2);
    //     });
    //     console.log("请求2");
    //   } else {
    //         let params = new URLSearchParams();
    //     params.append("level", "1");
    //     console.log(params);
    //     request.post("/get_level", params).then((res) => {
    //       this.shlef1 = res.data;
    //       console.log(this.shlef2);
    //     });
    //     cons
    //     console.log("请求3");
    //   }
    // },
    first(){
          let params = new URLSearchParams();
        params.append("shelf", "A");
        console.log(params);
        request.post("/get_shelf_1", params).then((res) => {
          this.shelf1s = res.data;
          console.log(this.shelf1s);
        });
        this.dialogVisible = true;
    }
    ,
    second(){
          let params = new URLSearchParams();
        params.append("shelf", "G");
        console.log(params);
        request.post("/get_shelf_2", params).then((res) => {
          this.shelf2s = res.data;
         
        });
        this.dialogVisible2 = true;
    }
    ,
    third(){
          let params = new URLSearchParams();
        params.append("shelf", "M");
        console.log(params);
        request.post("/get_shelf_3", params).then((res) => {
          this.shelf3s = res.data;
          
        });
        this.dialogVisible3 = true;
    }
  },
};
</script>

<style>
h1 {
  text-align: center;
  margin-top: 20px;
}
.plan {
  margin: 60px auto;
  margin-left: 40px;
  height: 400px;
}

.el-tabs__item {
  padding: 50px 20px;
  height: 140px;
}
</style>