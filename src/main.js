import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'
import '@/assets/css/global.css'

import * as ElIconModules from '@element-plus/icons'
// 导入转换图标名称的函数
import {transElIconName} from './utils/utils.js'

const app = createApp(App)
installElementPlus(app)
app.use(store).use(router).mount('#app')
// 统一注册el-icon图标
for (let iconName in ElIconModules) {
    app.component(transElIconName(iconName), ElIconModules[iconName])
}
