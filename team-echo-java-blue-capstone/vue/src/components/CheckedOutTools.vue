<template>
  <div>
    <h4 >Checked Out Tools
        </h4>

<div class="usearch">
<input class="searchboxT" type="text" v-model="search" placeholder="Search by Username">
  </div>
    <div class="tools" v-for="tool in filteredTools" :key="tool.username">
      <p class="tool-name">{{tool.name}}</p>
  
    
    
      <p>Reserved by: {{tool.username}}</p>

      <p>
        <b>Return Date: &nbsp;</b>
        {{tool.date_available}}
      </p>
      <div class="row justify-content-end">
        <button class="btn btn-primary" @click.prevent="returnTool(tool)">Mark Returned</button>
      </div>
    </div>
  </div>
</template>

<script>
import toolService from "@/services/ToolsService";
export default {
  name: "checked-out-tools",
  data() {
    return {
      tool: "",
      tools: [],
       search: "",
    };
  },
  methods: {
    returnTool(tool) {
      toolService
        .toolReturned(tool)
        .then(response => {
          if (response.status === 200) {
            toolService.email(tool);
            let index = this.tools.findIndex(t => t.tool_id == tool.tool_id);
            this.tools.splice(index,1); 
          }
        })
        .catch(error => {

          console.error(error);
          // this.handleErrorResponse(error, "adding");
        });
    },
    listCheckedOutTools() {
      toolService
        .getCheckedOutTools()
        .then(response => {
          this.tools = response.data;
        })
        .catch(err => console.error(err));
    }
  },
  created() {
    this.listCheckedOutTools();
  },
  computed: {
    filteredTools: function() {
      let filteredList = this.tools;
      if (this.category) {
        filteredList = filteredList.filter(tool => {
          return tool.username == this.username;
        });
      }

      return filteredList.filter(tool => {
        return tool.username.toLowerCase().match(this.search.toLowerCase());
      });
    }
  }
};
</script>

<style>

h4 {
  display: flex;
 margin: auto;
  align-items: center;
  font-family: "Josefin Sans", sans-serif;
 justify-content: space-between; 

}
.searchboxT {
display: flex;
 justify-content: space-around; 
 background-color: white;
 margin-right: 1em;
 font-family: "Josefin Sans", sans-serif;
 margin: auto;
    border-radius: 5px;
    width: 100%;
  
} 
.usearch{
  margin-left: 5px;
  margin-right: 5px;
} 

.tool-name{
  font-size: 30px;
  margin-bottom: 5px;
  text-decoration: underline;
}



</style>
