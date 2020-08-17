<template>
  <div>
   
    <div> 
      <img class="userphoto" :src="user_data.user_image"> 
      </div> 
      
      <p> User: {{$store.state.user.username}} </p>
    <p>Email Address: {{$store.state.user.email_address}}</p>

   
    <!-- <form> -->
      <!-- <div class="form-group">
        <label for="username" class="sr-only">Username</label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="New Username"
          v-model="user.username"
          required
          autofocus
        >
      </div>
      <button type="button" class="btn btn-primary">Update Username</button>

      <div class="form-group">
        <label for="password" class="sr-only">Password</label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="New Password"
          v-model="user.password"
          required
        >
      </div>
      <button type="button" class="btn btn-primary">Update Password</button>

      <div class="form-group">
        <label for="password" class="sr-only">Email</label>
        <input
          type="email"
          id="email"
          class="form-control"
          placeholder="New Email Address"
          v-model="user.email_address"
          required
        >
         <button type="button" class="btn btn-primary">Update Email Address</button>
      </div>
      </form> -->
    <form class="form-register" @submit.prevent="addImage(user.user_image)">
     
      <div class="form-group">
        <label for="image" class="sr-only">User Image</label>
        <input
          type="text"
          id="user_image"
          class="form-control"
          placeholder="Insert Image URL"
          v-model="user.user_image"
        >
      </div>
      <button type="submit" class="btn btn-primary">Upload Profile Image</button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';
export default {
  name: "login",
  components: {},
  data() {
    return {
    user:{},
        user_data: {},
        
      
      users: []
    }; 
  },
  methods: {

      getUserAtId() {
          authService.getUser(this.$store.state.user)
            .then(response => {
        this.user_data = response.data;
            console.log(response.data);
            
      
            })
      },
      
    addImage(){
      authService.userAddsImage(this.$store.state.user, this.user.user_image)
      .then(response => {
        if (response.status == 200) {
          this.user_data.user_image = this.user.user_image;
          
        }
      });
    }
    
    
  },
  created() {
        this.getUserAtId()

    }
};
</script>
<style>
button.btn.btn-primary {
  display: flex;
  width: 25%;
}

input#username.form-control {
  display: flex;
  width: 25%;
}

input#password.form-control {
  display: flex;
  width: 25%;
}

input#email.form-control {
  display: flex;
  width: 25%;
}
.userphoto {
height: 20%;
width: 20%;
margin-bottom: 10px;
margin-top: 10px;
}

#user_image{
  width: 25%;
  
}
p {
  
  display:flex;
  justify-content: center;
  font-family: "Josefin Sans", sans-serif;
}
</style>