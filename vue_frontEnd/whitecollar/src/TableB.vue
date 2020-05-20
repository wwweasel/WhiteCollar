<template>
  <div class="tableHead" style="margin-top: 1.5em;">

    <b-card header-tag="header" footer-tag="footer">
      <!-- Header -->
      <template v-slot:header>
        <b-row>
          <b-col class="col-12">
            
            <b-row>
              <b-col class="col-3">
                <div class="text-left">
                  <h5>Shops</h5>
                </div>
              </b-col>
              <b-col class="col-9">
                <div class="text-right">
                  <b-button-group size="sm">
                    <b-button id="createButton" variant="primary" @click="showModal(null)">Create</b-button>
                  </b-button-group>
                </div>
              </b-col>
            </b-row>

          </b-col>
        </b-row>
              
      </template>

      <!-- Body/Table -->
      <b-table sticky-header hover :head-variant="headVariant" ref="TableB" id="TableB" :busy="tableBusy" selectable :select-mode="selectMode" selected-variant="primary" :items="getShops" :fieldsComputed="getFields" @row-selected="onRowSelected" responsive="sm" :current-page="currentPage" :per-page="perPage"><!---->
     
        <!-- This Block changes the visibility of the selected row 
        <template v-slot:cell(selected)="{ rowSelected }">
          <template v-if="rowSelected">
            <span aria-hidden="true">&check;</span>
            <span class="sr-only">Selected</span>
          </template>
          <template v-else>
            <span aria-hidden="true">&nbsp;</span>
            <span class="sr-only">Not selected</span>
          </template>
        </template>-->

        <!--"'nameInput'+row.item.id"  -->
        <template v-slot:cell(name)="row">
          <router-link v-bind:to="'/store/'+row.item.id">{{row.item.name}}</router-link>  
        </template>

        <!-- -->
        <template v-slot:cell(load)="row">
          <b-progress :value="getShopPaintings(Number(row.item.id)).length" :max="getShop(row.item.id).capacity" show-progress variant="secondary"></b-progress>
        </template>
        

        <!--  -->
        <template v-slot:cell(actions)="row">
          <b-button v-bind:variant="row.item.actions.editButtonVariant" @click="showModal(Number(row.item.id))" size="sm" >Edit</b-button><!--<b-icon icon="pencil-square" ></b-icon>-->
          <b-button v-bind:variant="row.item.actions.deleteButtonVariant" @click="del(row.item.id)" size="sm">Delete</b-button>
        </template>

      </b-table>

      <!-- Footer -->
      <template v-slot:footer>

        <b-row>
            <b-col class="col-sm-12">
              <b-row>
                <b-col class="col-sm-2">
                  <b-form-select v-model="perPage" id="perPageSelect" size="sm" :options="pageOptions"></b-form-select>
                </b-col>

                <b-col class="col-sm-10">
                  <b-pagination align="fill" size="sm" v-model="currentPage" :total-rows="getShopsCount" :per-page="perPage" first-text="First" prev-text="Prev" next-text="Next" last-text="Last"></b-pagination>   
                </b-col>
              </b-row>
            </b-col>
            
        </b-row>
        
      </template>

    </b-card>
    
    <shopModal></shopModal>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import ShopModal from '@/components/ShopModal.vue';

export default {
    name: "TableB",
    components: {
      'shopModal': ShopModal
    },
    data() {
      return {
        selectMode: 'multi',
        selected: [],
        headVariant: "light",

        currentPage: 1,
        perPage: 5,
        pageOptions: [5, 10, 15, 20, 50],
        ids: [],

        tableBusy: false,
           
      }
    },
    methods: {
        ...mapActions(['loadShops','loadFields','deleteShop','setFormShop','loadPaintings','loadPaintings']),
      onRowSelected(items) {
        this.selected = items;
        this.ids = [];
        for( var item of this.selected){
          this.ids.push(item.id);
        }
        //console.log(this.ids);
      },
      selectAllRows() {
        this.$refs.table.selectAllRows()
      },
      clearSelected() {
        this.$refs.table.clearSelected()
      },
      del(shopId){
        this.deleteShop(shopId); // Vuex
        this.tableBusy = !this.tableBusy
        this.$refs.TableB.refresh()
        if(this.headVariant=='light'){
          this.headVariant='dark';
        }else{
          this.headVariant='light';
        }
        this.tableBusy = !this.tableBusy
      },
      showModal(id){
        if(id!==null){
          this.setFormShop( this.getShop(id) );
        }else{
          this.setFormShop( {id: null, name: '', capacity: 1} );
        }
        //console.log('passed stor id: '+id);
        this.$bvModal.show( "modal" );
      },
    
    },
    computed: {
        ...mapGetters(['getShops','getFields', 'getShopsCount','getShop','getShopPaintings']),
    },
    created(){
        //console.log("CREATED:");
        this.loadFields();
        this.loadShops();
        this.loadPaintings();
    },
    
}
</script>