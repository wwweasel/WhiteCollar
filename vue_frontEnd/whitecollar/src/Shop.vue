<template>

    <div style="margin-top: 1.5em;">

        <b-card no-body ><!--:header="getShop($route.params.shopId).name + ' Paintings'"--><!--header-tag="header"-->

            <template v-slot:header>
                <b-row>
                    <b-col class="col-12">
                        
                        <b-row>
                            <b-col class="col-4">
                                <div class="text-left">
                                    <h5>{{getShop(shopId).name + ' / Paintings'}}</h5><!---->
                                </div>
                            </b-col>
                            <b-col class="col-8">
                                <b-progress :value="getShopPaintings(Number(shopId)).length" :max="getShop($route.params.shopId).capacity" show-progress variant="secondary"></b-progress>
                            </b-col>
                        </b-row>
                    </b-col>
                </b-row>
            </template>

            

            <b-card-body id="nav-scroller" ref="content" style="position:relative; height:500px; overflow-y:scroll;">
            
                <b-list-group flush>
                    
                    <div v-for="(painting,index) in getShopPaintings(Number(shopId))" v-bind:key="painting.id">
                            
                        <b-list-group-item class="flex-column align-items-start" :variant="index%2==0 ? 'secondary' : 'default'">
                            <b-media>
                                <template v-slot:aside>
                                    <small>{{painting.id}}</small>
                                </template>

                                <div class="d-flex w-100 justify-content-between">
                                    <h3 class="mb-1"><b-badge variant="secondary">{{painting.name}}</b-badge></h3>
                                    
                                    <b-button @click="editPainting(painting.id)" :id="'icon'+painting.id" v-b-toggle="'itemCollapse'+painting.id" size="sm" variant="outline-secondary" v-b-popover.hover.left="'Edit Paining'"><b-icon icon="pencil"></b-icon></b-button>
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
                                <AddPainting @closeCollapse="close(painting.id)"></AddPainting>
                            </b-collapse>
                        </b-list-group-item>

                    </div>

                    <!-- CREATE -->
                    <b-list-group-item >
                        <b-collapse v-model="createToggle"><!-- This will be toggled by the Create Button in the Footer -->
                            <b-alert fade v-if="addPaintingAlert" v-model="addPaintingAlert" variant="danger">{{getShop(Number(shopId)).full}}</b-alert><!--getShop(Number(shopId)).full-->
                            <AddPainting v-else @closeCollapse="createCollapse"></AddPainting><!--@createPainting="createCollapse"-->
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
                <b-nav pills v-b-scrollspy:nav-scroller><!--@submit.prevent="'#createSpy'" action="#createSpy" :action="'http://localhost:8080/store/'+shopId+'#createSpy'" method="get"-->
                    <b-list-group horizontal >
                        <b-button @click="createCollapse" id="createItem" ref="createItem" :variant="createVariant">Create</b-button>
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
    name: 'Shop',
    components: {
      AddPainting
    },
    props:{
        shopId:{
            type: String,
        },
    },
    data() {
      return {        
        createVariant: 'primary',
        createToggle: false,
      }
    },
    methods: {
        ...mapActions(['loadPaintings','deleteSelectedPainting','setFormPainting','setCreateButtonState']),
        handleSubmit(event){
            console.log(event.target.action);
            
        },
        createCollapse(){
            window.location.href = '#createSpy'; // ScrollSpy!

            this.createToggle = !this.createToggle;
            if(this.createToggle){
                this.createVariant = 'danger';
                this.$refs.createItem.innerHTML = 'Cancel';
                
                // Pass a clean Painting to the Form
                this.setFormPainting( {id: null, shopId: Number(this.shopId), authorName: 'Anonymous', name: '', price: 0} );

            }else{
                this.createVariant = 'primary';
                this.$refs.createItem.innerHTML = 'Create';
            }
        },
        editPainting(paintingId){
            // Pass the current Painting to the Form
            this.setFormPainting( this.getPainting(paintingId) );
        },
        deletePainting(paintingId){
            //console.log(paintingId);
            this.deleteSelectedPainting( this.getPainting(paintingId) );
        },
        close(paintingId){
            document.getElementById('icon'+paintingId).click();
        }
    },
    computed: {
        ...mapGetters(['getPainting','getShop','getShopPaintings','getCreateButtonState']),
        addPaintingAlert() {
            return this.getShop(Number(this.shopId)).full !== null;
        },
    },
    
}
</script>


<style scoped>
    .my-row{
        border: 10px solid black; 
    }
</style>

