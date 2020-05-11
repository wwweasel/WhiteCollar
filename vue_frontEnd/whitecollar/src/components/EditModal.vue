<template>
    <div>
      <b-modal id="modal" v-bind:title="title" @show="resetModal" @hidden="resetModal" @ok="handleOk">

          <form ref="form" @submit.stop.prevent="handleSubmit">
            <b-form-group :state="nameState" label="Name" label-for="name-input" invalid-feedback="Name is required" label-cols-sm="2">
              <b-form-input id="name-input" v-model="name" :state="nameState" required ></b-form-input>
            </b-form-group>
            <b-form-group :state="capacityState" label="Capacity" label-for="capacity-input" invalid-feedback="Capacity is required" label-cols-sm="2">
              <b-form-input id="capacity-input" v-model="capacity" :state="capacityState" required ></b-form-input>
            </b-form-group>
          </form>

      </b-modal>
    </div>
</template>

<script>
  export default {
    name: "EditModal",
    computed: {
      title(){
        if(this.storeid){
          return 'Edit Store with id: ' + this.storeid
        }else{
          return 'Create a new Store'
        }
        
      }
    },
    data() {
      return {        
        name: '',
        nameState: null,
        submittedNames: [],

        capacity: '',
        capacityState: null,
      }
    },
    methods: {
      checkFormValidity() {
        const valid = this.$refs.form.checkValidity()
        this.nameState = valid
        this.capacityState = valid
        return valid
      },
      resetModal() {
        this.name = ''
        this.nameState = null
        this.capacity = ''
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
        // Push the name to submitted names
        const newStore={
          id: null,
          name: this.name,
          capacity: this.capacity,
          load: 40
        }
        // Hide the modal manually
        this.resetModal();
        this.$nextTick(() => {
          this.$bvModal.hide('modal')
        })

        this.$emit('emit-store', newStore);
      }
    }
  }
</script>