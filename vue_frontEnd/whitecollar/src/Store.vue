<template>

    <div style="margin-top: 1.5em;">

        <b-card no-body ><!--:header="getItem($route.params.storeId).name + ' Paintings'"--><!--header-tag="header"-->

            <template v-slot:header>
                <b-row>
                    <b-col class="col-12">
                        
                        <b-row>
                            <b-col class="col-4">
                                <div class="text-left">
                                    <h5>{{getItem(storeId).name + ' / Paintings'}}</h5><!---->
                                </div>
                            </b-col>
                            <b-col class="col-8">
                                <b-progress :value="getPaintingsByStoreId($route.params.storeId).length" :max="getItem($route.params.storeId).capacity" show-progress variant="secondary"></b-progress>
                            </b-col>
                        </b-row>
                    </b-col>
                </b-row>
            </template>

            

            <b-card-body id="nav-scroller" ref="content" style="position:relative; height:500px; overflow-y:scroll;">
            
                <b-list-group flush>
                    
                    <!--v-bind:class="{ 'active' : isSelected(0) }" v-on:click="selected = 0" v-on:mouseleave="mouseleave(painting.id)"-->
                    
                    <div v-for="(painting,index) in getPaintingsByStoreId(storeId)" v-bind:key="painting.id">
                            
                        <b-list-group-item class="flex-column align-items-start" :variant="index%2==0 ? 'secondary' : 'default'">
                            <b-media>
                                <template v-slot:aside>
                                    <small>{{painting.id}}</small>
                                </template>

                                <div class="d-flex w-100 justify-content-between">
                                    <h3 class="mb-1"><b-badge variant="secondary">{{painting.name}}</b-badge></h3>
                                    
                                    <b-button :id="'icon'+painting.id" v-b-toggle="'itemCollapse'+painting.id" size="sm" variant="outline-secondary" v-b-popover.hover.left="'Edit Paining'"><b-icon icon="pencil"></b-icon></b-button>
                                </div>
                                
                                <div class="d-flex w-100 justify-content-start">
                                    <p>{{'Author: '+painting.authorName}}</p>
                                </div>
                                
                                <div class="d-flex w-100 justify-content-between">
                                    <p><b-badge variant="secondary">{{'Price: '+painting.price+'€'}}</b-badge></p>
                                    <b-button @click="deletePainting(painting.id)" size="sm" variant="outline-secondary" v-b-popover.hover.left="'Delete Paining'"><b-icon icon="trash"></b-icon></b-button>
                                </div>

                            <!-- EDIT -->
                            </b-media>
                            <b-collapse :id="'itemCollapse'+painting.id">
                                <AddPainting v-bind:storeId="Number(storeId)" v-bind:paintingId="Number(painting.id)" v-bind:paintingName="painting.name" v-bind:paintingAuthorName="painting.authorName" v-bind:paintingPrice="Number(painting.price)" @createPainting="close(painting.id)"></AddPainting>
                            </b-collapse>
                        </b-list-group-item>

                    </div>

                    <!-- CREATE -->
                    <b-list-group-item >
                        <b-collapse v-model="createToggled">
                            <AddPainting v-bind:storeId="Number(storeId)" v-bind:paintingId="null" @createPainting="createCollapse"></AddPainting>
                        </b-collapse>
                    </b-list-group-item>

                    <b-list-group-item>
                        <div></div>
                    </b-list-group-item>

                    <b-list-group-item id="createSpy">
                        <div></div>
                    </b-list-group-item>

                </b-list-group>
            </b-card-body>


            <template v-slot:footer>
                <b-nav pills v-b-scrollspy:nav-scroller>
                    <b-list-group horizontal >
                        <form action="http://localhost:8080/#createSpy">
                            <b-button type="submit" @click="createCollapse" id="createItem" ref="createItem" :variant="createVariant">Create</b-button>
                        </form>
                        <!--<b-list-group-item id="createItem" ref="createItem" href="#createSpy" @click="createCollapse" :variant="createVariant">Create</b-list-group-item><-->
                    </b-list-group>
                </b-nav>
            </template>

        </b-card>
    
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import AddPainting from '@/components/AddPainting.vue';
export default {
    name: 'Store',
    components: {
      AddPainting
    },
    props:{
        storeId:{
            type: String,
        },
    },
    data() {
      return {
        createCollapseVis: false,
        
        createToggled: false,
        createVariant: 'primary',

      }
    },
    methods: {
        ...mapActions(['deleteSelectedPainting']),
        createCollapse(){
            this.createToggled = !this.createToggled;
            if(this.createToggled){
                this.createVariant = 'danger';
                this.$refs.createItem.innerHTML = 'Cancel';
                //console.log(this.$refs.createItem);
            }else{
                this.createVariant = 'primary';
                this.$refs.createItem.innerHTML = 'Create';
            }
        },
        deletePainting(paintingId){
            //console.log(paintingId);
            this.deleteSelectedPainting(paintingId);
        },
        close(paintingId){
            document.getElementById('icon'+paintingId).click();
        }
    },
    computed: {
        ...mapGetters(['getPaintingsByStoreId','getItem','getPainting']),
    },
    beforeDestroy(){
      console.log("Before destruction from Store");
    }
    
}
</script>


<style scoped>
    .my-row{
        border: 10px solid black; 
    }
</style>

