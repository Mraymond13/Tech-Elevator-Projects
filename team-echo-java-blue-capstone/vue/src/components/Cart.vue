 <template>
  <div class="cart">
    <div class="title" >  <h3> &nbsp; Shopping Cart </h3> 
    <h1><button class="btn btn-success" @click.prevent="checkout">Check Out</button></h1> 
    
    </div>
    
     <h2 class="empty" v-show="!cartItems.length">Your cart is empty! Please add some tools! </h2>
        
        
    <div class="cart-items">
      <div class="cartItem" v-for="tool in cartItems" v-bind:key="tool.id">
        <h3>{{ tool.name}}</h3>
        <p>
          <img :src="tool.image">
        </p>
        <p>
          <b><i>
          {{tool.description}}
          </i></b>
        </p>
        <p>
          <b>Category: &nbsp;</b>
          {{tool.category_name}}
        </p>
      

        <button class="btn badge badge-warning float-right" title="Remove from cart" 
        @click.prevent="removeFromCart(tool)"> Remove from Cart </button>
         
      </div>
      <div>
       
      </div>
    </div>
    <!-- add button to check out -->
  </div>
</template>

<script>
import ToolsService from "@/services/ToolsService"
export default {
  name: "shopping-cart",
  data() {
    return {
      tool: "",
      tools: []
    };
  },
  computed: {
    cartItems() {
      return this.$store.state.cart;
    }
  },
    methods: {
     removeFromCart(tool) {
        this.$store.commit('REMOVE_FROM_CART', tool);
    },
    checkout() {
        ToolsService.addToTools(this.$store.state.user)
        .then(response => {
            if (response.status === 200) {
              this.$store.commit('EMPTY_CART');
              this.$router.push('/mytools');
              
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, "adding");
          });
      },




    
    } 
  
};
</script>

<style>



div.cart-items {
  display: flex;
  justify-content: flex-start;
}
div.cartItem {
  border: 1px black solid;
  margin: 1vw;
  padding: 1vw;
  text-align: center;
}
div.cartItem h3 {
  margin: 0;
  padding: 0;
}


h3 {
  text-decoration: none;
  font-family: "Josefin Sans", sans-serif;
}
div.cartItem {
  display: inline-block;
  border: 1px black solid;
  border-radius: 8px;
  padding: 1rem;
  margin: 24px;
  width: 30%;
  background: rgba(200, 207, 209, 0.3);

  align-content: center;
}

div.title {
background: rgb(247, 181, 106);
display: flex;
 margin: auto;
 padding: 2;
  align-items: center;
  font-family: "Josefin Sans", sans-serif;
 justify-content: space-between; 

}

button.btn.btn-success {
 justify-content: space-around; 
 background-color: #007bff;
 margin-right: 2em;
 font-family: "Josefin Sans", sans-serif;
 
box-shadow: inset 0 0 2px 1px black;
    border-radius: 10px;
    padding: 10px;
    width: 100%;
    margin: auto;
  
}


h2.empty {
  padding: 100px 0;
text-decoration: none;
  text-align: center;
  
}
h1{
  margin-right: 10px;
}

</style>