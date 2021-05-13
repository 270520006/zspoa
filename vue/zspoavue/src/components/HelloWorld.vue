<template>
  <div class="hello">
{{userData}}      
    <!-- <el-table
      :data="userData"
      style="width: 100%">
      <el-table-column
        prop="userName"
        label="用户名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="userPassword"
        label="密码"
        width="180">
      </el-table-column>
      <el-table-column
        prop="userPhone"
        label="电话">
      </el-table-column>
    </el-table> -->
 
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      userData:[]
    }
  },
  methods:{
    async nnn(){
        const res = await this.$http.get('acti/task/member/list')
        console.log(res.data.memberList.members);
        this.userData=res.data.memberList.members;
    }
  },
  created(){
    this.nnn()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
