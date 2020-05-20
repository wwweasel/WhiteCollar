<template>
    <div>
      <b-modal id="modal" v-bind:title="title" @show="resetModal" @hidden="resetModal" @ok="handleOk">

          <form ref="form" @submit.stop.prevent="handleSubmit">
            <b-form-group :state="nameState" label="Name" label-for="name-input" invalid-feedback="Name is required" label-cols-sm="2">
              <b-form-input id="name-input" v-model="name" :state="nameState" required ></b-form-input><!--:placeholder="namePlaceholder"-->
            </b-form-group>
            <b-form-group :state="capacityState" label="Capacity" label-for="capacity-input" invalid-feedback="Capacity is required" label-cols-sm="2">
              <b-form-input id="capacity-input" type="number" v-model="capacity" :state="capacityState" required ></b-form-input>
            </b-form-group>
          </form>

      </b-modal>
    </div>
</template>

<script>
  import { mapGetters, mapActions } from 'vuex';
  export default {
    name: "EditModal",
    computed: {
      ...mapGetters(['getFormShop']),
      title(){
        if(this.getFormShop.id){
          return 'Edit Shop with id: ' + this.getFormShop.id
        }else{
          return 'Create a new Shop'
        }
      },
      name:{
        get(){
          return this.getFormShop.name;
        },
        set(value){
          let clone = { ...this.getFormShop };
          clone.name = value;
          this.setFormShop(clone);
        }
      },
      capacity:{
        get(){
          return this.getFormShop.capacity;
        },
        set(value){
          let clone = { ...this.getFormShop };
          clone.capacity = value;
          this.setFormShop(clone);
        }
      },
    },
    data() {
      return {
        nameState: null,
        capacityState: null,
      }
    },
    methods: {
      ...mapActions(['addShop','setFormShop','loadShops']),
      checkFormValidity() {
        const valid = this.$refs.form.checkValidity()
        this.nameState = valid
        this.capacityState = valid
        return valid
      },
      resetModal() {
        //this.newShop.id = null,
        //this.newShop.name = ''
        this.nameState = null
        //this.newShop.capacity = ''
        this.capacityState = null
      },
      handleOk(bvModalEvt) {
        // Prevent modal from closing
        bvModalEvt.preventDefault()
        // Trigger submit handler
        this.handleSubmit()
      },
      handleSubmit() {
        // Exit when the form isn't valid
        if (!this.checkFormValidity()) {
          return
        }
        
        // console.log("Before: ");
        // console.log(this.getFormShop.id);
        // console.log(this.getFormShop.name);
        // console.log(this.getFormShop.capacity);    
        this.addShop(this.getFormShop);
        // Hide the modal manually
        this.resetModal();
        this.$nextTick(() => {
          this.$bvModal.hide('modal')
        })
      }
    }
  }
</script>