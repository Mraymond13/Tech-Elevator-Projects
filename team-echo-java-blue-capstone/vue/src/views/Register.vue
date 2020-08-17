<template>
<div>
  <div>
<img class = "homepage" src="https://contattafiles.s3.us-west-1.amazonaws.com/tecommunity/y81QjXBS4TXve03/Screen%20Shot%202020-08-11%20at%204.25.36%20PM.png" />
</div>

  <div id="register" class="text-center form-group">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-bold">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-group">
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="usernam"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      </div>
      <div class="form-group">
      <label for="email_address" class="sr-only">Email Address</label>
      <input
        type="email_address"
        id="email_address"
        class="form-control"
        placeholder="Email Address"
        v-model="user.email_address"
        required
      />
      </div>
      <div class="form-group">
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="passwor"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
       
      </div>
      <div class="form-group">
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      </div>
       <button class="btn btn-lg btn-primary btn-block ca" type="submit">
        Create Account
      </button>
      <router-link button class="btn btn-lg btn-primary btn-block" :to="{ name: 'login' }">Have an account?</router-link>
     
    </form>
  </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        emailAddress: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>

input#passwor.form-control {
display: block;
    width: 100%;
    height: calc(1.5em + .75rem + 2px);
    padding: .375rem .75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    border-radius: .25rem;
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
input#usernam.form-control {
display: block;
    width: 100%;
    height: calc(1.5em + .75rem + 2px);
    padding: .375rem .75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    border-radius: .25rem;
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;

}

a.btn.btn-lg.btn-primary.btn-block{

      padding: 3px;
    width: 85%;
    margin: auto;
    margin: 10px;
    justify-content: space-around;
    
    font-family: "Josefin Sans", sans-serif;
    
    box-shadow: inset 0 0 2px 1px black;
    border-radius: 10px;


 
}

</style>
