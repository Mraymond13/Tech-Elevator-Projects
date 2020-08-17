<template>
 <div class="cart">
    <nav-bar />
      <cart/>
  </div>
</template>

<script>

import Cart from '@/components/Cart'
import NavBar from '@/components/NavBar'
export default {
    name: 'shopping-cart',
    components: {
        Cart,
        NavBar
        
        }
    }

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
div.cartItem h3
{
   margin: 0;
   padding: 0;
}

</style>
