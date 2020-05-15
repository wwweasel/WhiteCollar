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
                  <h5>Stores</h5>
                </div>
              </b-col>
              <b-col class="col-9">
                <div class="text-right">
                  <b-button-group size="sm">
                    <b-button id="createButton" variant="primary" @click="showModal">Create</b-button>
                    <b-button id="dangerButton" variant="danger" @click="del">Delete</b-button>
                    <b-button id="isolateButton" variant="outline-primary" @click="isolate">Isolate</b-button>
                  </b-button-group>
                </div>
              </b-col>
            </b-row>

          </b-col>
        </b-row>
              
      </template>

      <!-- Body/Table -->
      <b-table sticky-header hover :head-variant="headVariant" ref="table" id="TableB" :busy="tableBusy" selectable :select-mode="selectMode" selected-variant="primary" :items="getItems" :fieldsComputed="getFields" @row-selected="onRowSelected" responsive="sm" :current-page="currentPage" :per-page="perPage"><!---->
     
        <!-- This Block changes the visibility of the selected row -->
        <template v-slot:cell(selected)="{ rowSelected }">
          <template v-if="rowSelected">
            <span aria-hidden="true">&check;</span>
            <span class="sr-only">Selected</span>
          </template>
          <template v-else>
            <span aria-hidden="true">&nbsp;</span>
            <span class="sr-only">Not selected</span>
          </template>
        </template>

        <!--"'nameInput'+row.item.id"  -->
        <template v-slot:cell(name)="row">
          <b-form-input v-if="row.item.actions.buttonToggle" v-bind:id="'nameInput'+row.item.id" v-model="row.item.name" placeholder="enter the Store's name here" size="sm">{{row.item.name}}</b-form-input><!--disabled-->
          <router-link v-else v-bind:to="'/store/'+row.item.id">{{row.item.name}}</router-link>
        </template>

        <!--  -->
        <template v-slot:cell(capacity)="row">
          <b-form-input v-if="row.item.actions.buttonToggle" v-bind:id="'capacityInput'+row.item.id" v-model="row.item.capacity" placeholder="enter the Store's capacity here" size="sm">{{row.item.capacity}}</b-form-input><!--disabled-->
          <div v-else >{{row.item.capacity}}</div>
        </template>

        <!--  -->
        <template v-slot:cell(load)="row">
          <b-progress v-bind:id="row.item.id" :value="row.item.load" :max="row.item.capacity" show-value variant="secondary"></b-progress>
        </template>

        <!--  -->
        <template v-slot:cell(actions)="row">
          <b-button v-bind:id="row.item.id" v-bind:variant="row.item.actions.buttonVariant" @click="toggleButtons" size="sm" >Edit</b-button><!--<b-icon icon="pencil-square" ></b-icon>-->
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
                  <b-pagination align="fill" size="sm" v-model="currentPage" :total-rows="getItemsCount" :per-page="perPage" first-text="First" prev-text="Prev" next-text="Next" last-text="Last"></b-pagination>   
                </b-col>
              </b-row>
            </b-col>
            
        </b-row>
        
      </template>

    </b-card>
    
    <editModal v-on:emit-store="createStore"></editModal>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import EditModal from '@/components/EditModal.vue';

export default {
    name: "TableB",
    components: {
      'editModal': EditModal
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

        tableBusy: false
           
      }
    },
    methods: {
        ...mapActions(['toggleButton','loadItems','loadFields','addStore','loadPaintings','editStore']),
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
      toggleButtons(event){
        
        if( this.getButtonstate(event.target.id) ){ // Vuex
            
            const editStoreFrag = {
              id: event.target.id,
              name: document.querySelector('#nameInput'+event.target.id).value,
              capacity: document.querySelector('#capacityInput'+event.target.id).value,
            }
          this.editStore(editStoreFrag);
          
          this.tableBusy = true
          this.$refs.table.refresh()
          this.tableBusy = false
        }

        this.toggleButton(event.target.id); // Vuex
      },
      del(){
        console.log(this.ids);
        //this.items = this.items.filter( input => input.id!==id);
      },
      isolate(){
        console.log(this.getItem(3).name);
        console.log(this.getItem(3).capacity);
      },
      createStore(newStore){
        this.addStore(newStore);
			},
      showModal(){
        this.$bvModal.show( "modal" );
      }
    
    },
    computed: {
        ...mapGetters(['getItems','getFields', 'getItemsCount','getItem','getButtonstate']),
    },
    created(){
        //console.log("CREATED:");
        this.loadFields();
        this.loadItems();
        this.loadPaintings();
    },
    beforeDestroy(){
      console.log("Before destruction from TableB");
    }
}
</script>