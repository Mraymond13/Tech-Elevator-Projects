<template>
  <div>
    <h4>My Tools</h4>
    <div class="tools" v-for="tool in tools" :key="tool.name">
      <h2>{{tool.name}}</h2>
      <p>
        <img :src="tool.image">
      </p>

      <p>
        <b>Return Date: &nbsp;</b>
        {{tool.date_available}}
      </p>
    </div>
  </div>
</template>

<script>
import toolService from "@/services/ToolsService";
export default {
  name: "my-tools",
  data() {
    return {
      tool: "",
      tools: []
    };
  },
  methods: {
    listmyTools() {
      toolService
        .getToolsAtId(this.$store.state.user)
        .then(response => {
          this.tools = response.data;
        })
        .catch(err => console.error(err));
    }
  },
  created() {
    this.listmyTools();
  }
};
</script>

<style>

h4 {

background: rgb(247, 181, 106);
display: flex;
flex-wrap: wrap;
flex-direction: column;
  justify-content: center;
  align-items: center;
  font-family: "Josefin Sans", sans-serif;
}







</style>
