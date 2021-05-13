<template>
  <div class="hello">
      <h1>复习一波vue{{name}}</h1>
   <div class="block" align="center" >
    <span class="demonstration">默认</span>
    <el-slider v-model="value1" style="width:300px;"   ></el-slider>
    <el-button type="primary" round v-model="adata" @click="aaa">主要按钮 </el-button>
    {{adata}}
  </div>
  </div>
</template>

<script>
export default {
   
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      name: '朱少鹏',
      adata: ''
    }
  },
  methods:{
      aaa:function(){
            this.$http({
            url: this.$http.adornUrl('api/acti/task/list'),
            method: 'get',
            params: this.$http.adornParams({})
            }).then(({data}) => {
                alert(data);
            })
        }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style  >
 
</style>
