

<template>
  <div class="tool-list">
    <div class="flexbox sticky-top">
      <div class="search">
        <input class="searchbox" type="text" v-model="search" placeholder="Search Tools">

        <!-- </div> -->
        <!-- <div class="flexbox"> -->
        <label for="category">
          <b>Select Category:</b>
        </label>
        <select id="category" v-model="category">
          <option value>Choose A Category</option>
          <option
            v-for="category in categories"
            :key="category.category_id"
            :value="category.category_id"
          >{{category.name}}</option>>
        </select>
        <!-- </div> -->
        <!-- <div class="flexbox"> -->
        <label for="available">
          <b>Availability</b>
        </label>
        <select id="available" v-model="available">
          <option value>Show All</option>
          <option :value="true">Available</option>>
          <option :value="false">Unvailable</option>
        </select>
      </div>
      
    </div>
    
    
    <div class="tools" v-for="tool in filteredTools" :key="tool.name">
      <h2>{{tool.name}}</h2>
      <br>
      <p>
        <img :src="tool.image">
      </p>
      <p>
        
       <em> {{tool.description}} </em>
      </p>
      <p>
        <b>Category: &nbsp;</b>
        {{tool.category_name}}
      </p>
      <p v-if="!tool.available">
        <b >Return Date: &nbsp;</b>
        {{tool.date_available}}
      </p>
      
      <!-- <div class="row justify-content-end">
        <input type="button" class="btn btn-primary" value="Add to Cart"  v-if="tool.available" @click.once="addToCart(tool)"/>
        
      </div> -->
<div class="row justify-content-end">
<button class="btn btn-primary" v-if="tool.available" @click.once="addToCart(tool)">
{{ isToolInCart(tool)? 'Added to cart': 'Add to Cart'}}</button>
</div>

      <div class="row justify-content-end">
        <button class="btn btn-danger"  v-if="!tool.available" @click.once="addToReservation(tool, $event)">Notify when available</button>
      
      </div>
    </div>
  </div>
</template>



<script>
import toolService from "@/services/ToolsService";
export default {
  name: "tool-list",
  data() {
    return {
      tool: "",
      tools: [],
      search: "",
      category: "",
      available: "",
      categories: []
    };
  },

  methods: {
    listTools() {
      toolService
        .getTools()
        .then(response => {
          this.tools = response.data;
        })
        .catch(err => console.error(err));
    },
    listToolsByCategories() {
      toolService
        .getToolsByCategories()
        .then(response => {
          this.categories = response.data;
        })
        .catch(err => console.error(err));
    },
    addToCart(tool) {
      this.$store.commit("ADD_TOOL_TO_CART", tool);
     
        
      
    },
    addToReservation (tool, event) {
      event.target.innerText="On Waiting List";
  toolService.addReservationToTable (this.$store.state.user, tool)
  .then(response => {
            if (response.status === 201) {
              this.$router.push('/');
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, "adding");
          });

    },
    isToolInCart(tool){
      return this.$store.state.cart.find(t => t.tool_id === tool.tool_id)
    }
  },

  created() {
    this.listTools();
    this.listToolsByCategories();
  },
  computed: {
    filteredTools: function() {
      let filteredList = this.tools;
      if (this.category) {
        filteredList = filteredList.filter(tool => {
          return tool.category_id == this.category;
        });
      }
      if (this.available) {
        filteredList = filteredList.filter(tool => {
          return tool.available;
        });
      } else if (this.available === false) {
        filteredList = filteredList.filter(tool => {
          return !tool.available;
        });
      }
      return filteredList.filter(tool => {
        return tool.name.toLowerCase().match(this.search.toLowerCase());
      });
    }
  }
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Roboto:400,400i,700");
@import url("https://fonts.googleapis.com/css2?family=Lemonada:wght@700&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap");
* {
  font-family:"Josefin Sans", sans-serif;
  padding: 0;
  margin: 0;
}


.tool-list {

  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
}
button {

margin-right: 40%;

}

.searchbox {
  border-radius: 8px;
  margin: 15px;
}

select#category {
  border-radius: 8px;
  margin: 15px;
  padding: 0.2rem;
}


select#available {
  border-radius: 8px;
  margin: 15px;
  padding: 0.2rem;
}


p {
  display: flex;
  justify-content: left;
}

img {
  display: flex;
  flex-wrap: wrap;
  margin-right: auto;
  margin-left: auto;
  width: 50%;
  height: 50%;
  align-content: center;
  justify-content: center;
  
}

h2 {
  text-decoration: underline;
  font-family: "Josefin Sans", sans-serif;
  text-align: center;
  
}

div.tools {
    display: inline-block;
  
  flex-direction: column;
  border: 2px grey solid;
  border-radius: 8px;
  padding: .5rem;
  margin: 20px;
  width: 30%;
  background-color: white;
justify-content: space-between;
  align-content: center;
}

html,
body {
  width: 100%;
  height: 100%;
  /* background: url(https://contattafiles.s3.us-west-1.amazonaws.com/tecommunity/2NnuWQRAa9hv4M5/jGlzr.png);  */
   /* background: #3A3E46; */
   background: white;
 
}
p {

  text-align: center;
  align-content: center;
  justify-content: center;
  align-items: center;
}

.flexbox {
   background: #007bff; 
  /* background: rgb(179, 47, 47); */
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;


}

.search {
  margin: 20px;
  color: white;
}

.search > h3 {
  font-weight: normal;
}

.search > p {
  color: white;
  margin-bottom: 15px;
  text-shadow: 0 1px #0091c2;
}

.search > div {
  display: inline-block;
  position: relative;
  filter: drop-shadow(0 1px #0091c2);
}

.search > div:after {
  content: "";
  background: white;
  width: 4px;
  height: 20px;
  position: absolute;
  top: 40px;
  right: 2px;
  transform: rotate(135deg);
}

.search > div > input {
  color: white;
  font-size: 16px;
  background: transparent;
  width: 25px;
  height: 25px;
  padding: 10px;
  border: solid 3px white;
  outline: none;
  border-radius: 35px;
  transition: width 0.5s;
  border: 3px black solid;
}

b, strong {
    font-weight: inherit;
}

button.btn.btn-danger {
   justify-content: space-around; 
 margin-right: 1em;
 font-family: "Josefin Sans", sans-serif;
 margin: auto;
box-shadow: inset 0 0 2px 1px black;
    border-radius: 10px;
    padding: 10px;
    width: 50%;
    margin: auto;
}

button.btn.btn-primary {
   justify-content: space-around; 
 margin-right: 1em;
 font-family: "Josefin Sans", sans-serif;
 margin: auto;
box-shadow: inset 0 0 2px 1px black;
    border-radius: 10px;
    padding: 10px;
    width: 50%;
    margin: auto;
}

/* div.flexbox.sticky-top {
  position: fixed;
  top: 0;
  width: 100%;
} */
</style>

