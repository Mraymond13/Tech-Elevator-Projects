<template>
<div>
<div>
<img class = "homepage" src="https://contattafiles.s3.us-west-1.amazonaws.com/tecommunity/y81QjXBS4TXve03/Screen%20Shot%202020-08-11%20at%204.25.36%20PM.png" />
</div>
 

<p class = "intro">

  Gain access to world class tools with toolbx

</p>


  <div id="login" class="text-center form-group">


    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-bold">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <div class="form-group">
      <label for="username" class="sr-only">Username </label>
      <input
        type="text"
        id="username"
        class="form"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      </div>
      <div class="form-group">
      <label for="password" class="sr-only">Password </label>
      <input
        type="password"
        id="password"
        class="form-password"
        placeholder="Password"
        v-model="user.password"
        required
      />
      </div>
      <button type="submit" class="btn btn-primary btn-lg btn-block">Sign in</button>
      <div>
      <router-link class="new-account" :to="{ name: 'register' }">Need an account?</router-link>
      </div>
    </form>
  </div>
  </div>

</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>
<style>
.form-group {
  margin: 10px;
 display: flex;
 justify-content: center;
  align-items: center;


}

.mb-3 {
 background: #007BFF;
 color: white;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 10px;
  font-weight: 100;
  box-shadow: inset 0 0 2px 1px black;
    border-radius: 10px;
    padding: 10px;

}

.font-weight-bold {
    font-weight: 200;
}

.btn {
  margin-bottom: 15px;
}

a.new-account {
padding: 5px;
width: 80%;
margin: auto;
width: 100%;
}

button.btn.btn-primary.btn-lg.btn-block {
padding: 10px;
width: 80%;
margin-right: 1em;
margin: 10px;
}

input#username.form {
display: flex;
width: 130%;
justify-content: space-around;
margin-right: 1em;
padding: 5px;
font-family: "Josefin Sans", sans-serif;

}

input#password.form-password {
display: flex;
width: 130%;
justify-content: space-around;
margin-right: 1em;
font-family: "Josefin Sans", sans-serif;
padding: 5px;
}

.homepage {

width: 20%;
height: 20%;

}

#login {
  border: 2px grey solid;
  border-radius: 8px;
  padding: .5rem;
  margin: 20px;
  width: 20%;
  position: absolute;
  top: 30%;
  left: 38.9%;
  
}

.intro {

  margin-top: 15px;
  font-family: "Josefin Sans", sans-serif;
}
 
</style>

