<template>
  <div>

    <b-card header-tag="header" footer-tag="footer">
      <!-- Header -->
      <template v-slot:header>
          <b-row>
            <b-col class="col-12">

              <b-row>
                <b-col class="col-2" align-h="start">
                  <h6>Stores</h6>
                </b-col>
                <b-col class="col-10" align-h="end">
                  <b-button-group size="sm">
                    <b-button id="createButton" variant="primary" @click="showModal">Create</b-button>
                    <b-button id="editButton" variant="outline-primary" @click="showModal">Edit</b-button>
                    <b-button id="dangerButton" variant="danger" @click="del">Delete</b-button>
                    <b-button id="isolateButton" variant="outline-primary" @click="isolate">Isolate</b-button>
                  </b-button-group>
                </b-col>
              </b-row>
              
            </b-col>
          </b-row>
      </template>

      <!-- Body/Table -->
      <b-table ref="table" :busy="isTableBusy" sticky-header hover :head-variant="headVariant" selectable :select-mode="selectMode" selected-variant="primary" :items="getItems" :fields="getFields" @row-selected="onRowSelected" responsive="sm" :current-page="currentPage" :per-page="perPage"><!---->
     
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
    <editModal v-bind:storeid="storeid" v-on:emit-store="updateCreateStore"></editModal>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import EditModal from '@/components/EditModal.vue';

export default {
    name: "Table",
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
        storeid: null,

        isTableBusy: false
           
      }
    },
    methods: {
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
      del(){
        console.log(this.ids);
        //this.items = this.items.filter( input => input.id!==id);
      },
      isolate(){
        console.log(this.ids);
      },
      updateCreateStore(emitStore){
            this.isTableBusy = true;
            if(emitStore.id){
                const bla = this.getItems.map(function(obj){
                    if(emitStore.id == obj.id){
                        obj.name = emitStore.name;
                        obj.capacity = emitStore.capacity;
                        obj.load = emitStore.load;
                    }
                    return obj;
                });
                //this.items = bla;
                console.log("bla: " + bla);
            }else{
                console.log("Create branch");
                //this.items.push(emitStore);
            }
            this.isTableBusy = false;
            this.$refs.table.refresh();
			
      },
      showModal(event){ // Opens a Modal for creating as well as editing a Store
        if(event.target.id=="createButton"){
            this.storeid=null
        }else{
            if(this.ids<1){// If you havent selected a row before clicking the editButton
                alert("Please select the row(s) you want to edit.");
                return;
            }else{
                this.storeid=this.ids[0];
                this.clearSelected();
            }
        }
        this.$bvModal.show( "modal" );
      }
    
    },
    computed: {
      ...mapGetters(['getItems','getItemsCount','getFields']),

    }
}
</script>