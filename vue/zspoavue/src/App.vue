<template>
  <div id="background">
     
     
    <br/>
    <router-view/>
    <br/>
  </div>
  
</template>

<script>
export default {
  name: 'App',

}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
#background{
  /* width: 100%; */
   background-image: url("./assets/background.jpg");
   background-repeat: no-repeat;
   background-size:cover;
}
*{
  padding: 0px;
  margin: 0px;
}
</style>
