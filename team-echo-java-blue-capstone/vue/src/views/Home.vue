<template>
  <div class="home">
   <nav-bar />
   
    <h1> Tool Inventory</h1>
   
  <tool-list/>
  
  </div>
  
</template>

<script>

import ToolList from '@/components/ToolList'
import NavBar from '@/components/NavBar'


export default {
  name: "home",

  components: {
    ToolList,
    NavBar
  
  },
 

};

</script>
<style>
div.home > h1 {
font-family: 'Josefin Sans', sans-serif;
text-decoration: underline;
text-align: center;
background:white;
border-radius: 10px;
margin-top: 3px;
margin-bottom: 3px;
}


</style>