<template>
  <div id="app" >
   
    <div id="nav"  class=navbar navbar-expand-lg fixed-top row>
     <router-link class="icon" v-bind:to="{ name: 'home' }"> <img src="https://contattafiles.s3.us-west-1.amazonaws.com/tecommunity/y81QjXBS4TXve03/Screen%20Shot%202020-08-11%20at%204.25.36%20PM.png" alt="Italian Trulli"> </router-link>&nbsp;
      <router-link  v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;
      <router-link  v-bind:to="{ name: 'mytools' }"> {{$store.state.user.username}}'s Tools </router-link>&nbsp;
      <router-link  v-bind:to="{ name: 'checkedouttools'  }" v-if="$store.state.user.username === 'Librarian' " > Reserved Tools </router-link>&nbsp;
      <router-link  v-bind:to="{ name: 'cart' }" > Cart ({{ cartItems.length }})</router-link>&nbsp;
      <router-link  v-bind:to="{ name: 'userprofile' }" > {{$store.state.user.username}}'s Profile</router-link>&nbsp;
      <router-link  v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;
   
    </div>
    
    <router-view />
  </div>
</template>
<script>
export default {
   computed: {
    cartItems() {
      return this.$store.state.cart;
    }
   }   
}
</script>
<style>

a {
  color: white;
  display: flex;
    text-decoration: none;
    background-color:#007bff;
    border-radius: 10px;
    padding: 10px;
    margin: auto;
    box-shadow: inset 0 0 2px 1px black;

}

#app {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;

}

a:hover {
  background-color: rgb(255, 255, 255);
}

#nav > img {

height: 90px;
width: 7%;
 
}

.router-link-exact-active {
  background:rgb(247, 181, 106);
  color: black;
}
.icon{
  background: none;
  box-shadow: none;
}

h6 {
  position: absolute;
  right: 0px;
  width: 190px;
  

}
 a{
   
  text-decoration: none;
  width: 10%;
  justify-content: center;
  
 }

#nav {
    position : sticky;
    top: 0;
    background: #3A3E46;; 

}


</style>