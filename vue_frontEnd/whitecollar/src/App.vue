<template>
  <div id="app">
    
      <NavBar></NavBar>
      <b-row align-h="center">
        <b-col class="col-10">
          <router-view></router-view><!--This line will be replaced by either TableB.vue or Shop.vuw, depending on the ROUTE-->
        </b-col>
      </b-row>

  </div>
</template>

<script>
import NavBar from "./components/NavBar.vue";
export default {
  name: "App",
  components: {
    NavBar
  }
};
</script>
