<template>
    <div >
        <!--<b-form inline class="d-flex justify-content-between" v-on:submit.prevent="submit">

            <label label-for="name"></label>
            <b-input v-model.trim="$v.name.$model" id="name" :class="{'is-invalid':true}" placeholder="Name"></b-input>

            <label label-for="authorname"></label>
            <b-input v-model.trim="$v.authorName.$model" id="authorname" :class="{'is-invalid':true}" placeholder="Authorname"></b-input>   

            <label label-for="price"></label>
            <b-input v-model.trim="$v.price.$model" type="number" id="price" :class="{'is-invalid':true }" placeholder="Price"></b-input>

            <b-button type="submit" id="save" variant="secondary">Save</b-button>

        </b-form>
        <b-form inline v-if="submitStatus === 'ERROR'">
            <b-alert variant="success" show class="error" v-if="!$v.name.required">Name is required</b-alert>
            <b-alert variant="success" show class="error" v-if="!$v.authorName.required">AuthorName is required</b-alert>
            <b-alert variant="success" show class="error" v-if="!$v.price.required">Price is required</b-alert>
        </b-form>-->

        <b-row class="justify-content-md-between">
            <b-col xs="12" md="3"><b-input v-model.trim="$v.name.$model" id="name" :class="{'is-invalid':validationStatus($v.name)}" placeholder="Name"></b-input></b-col>
            <b-col xs="12" md="3"><b-input v-model.trim="$v.authorName.$model" id="authorname" :class="{'is-invalid':validationStatus($v.authorName)}" placeholder="Authorname"></b-input></b-col>
            <b-col xs="12" md="3"><b-input v-model.trim="$v.price.$model" type="number" id="price" :class="{'is-invalid':validationStatus($v.price) }" placeholder="Price"></b-input></b-col>
            <b-col xs="12" md="3"><b-button block @click="submit" id="save" variant="secondary">Save</b-button></b-col>
        </b-row>
        <b-row v-if="submitStatus=='ERROR'" class="justify-content-md-start">
            <b-col xs="12" md="3"><small class="error" v-if="!$v.name.required || !$v.name.minLength" style="color: red;">Name is required and has to have a minimum length of 2 Characters.</small></b-col>
            <b-col xs="12" md="3"><small class="error" v-if="!$v.authorName.required || !$v.authorName.minLength" style="color: red;">AuthorName is required and has to have a minimum length of 2 Characters.</small></b-col>
            <b-col xs="12" md="3"><small class="error" v-if="!$v.price.required || !$v.price.minValue" style="color: red;">Price is required and has to have a minimum value of 0.</small></b-col>
        </b-row>

    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import { required, minLength, minValue } from 'vuelidate/lib/validators' //, between
export default {
    name: 'AddPainting',
    computed: {
        ...mapGetters(['getFormPainting']),
        authorName:{
            get(){
                return this.getFormPainting.authorName;
            },
            set(value){
                let clone = { ...this.getFormPainting };
                clone.authorName = value;
                this.setFormPainting(clone);
            }
        },
        name:{
            get(){
                return this.getFormPainting.name;
            },
            set(value){
                let clone = { ...this.getFormPainting };
                clone.name = value;
                this.setFormPainting(clone);
            }
        },
        price:{
            get(){
                return this.getFormPainting.price;
            },
            set(value){
                let clone = { ...this.getFormPainting };
                clone.price = value;
                this.setFormPainting(clone);
            }
        },
    },
    data(){
        return{
            submitStatus: null,
        }
    },
    validations: {
        name: {
            required,
            minLength: minLength(2)
        },
        authorName: {
            required,
            minLength: minLength(2)
        },
        price: {
            required,
            minValue: minValue(0)
        }
    },
    methods: {
        ...mapActions(['addPainting','setFormPainting']),
        submit(event){
            event.preventDefault();
            this.$v.$touch()
            //if(this.$v.$pending || this.$v.$error) return;

            if (this.$v.$invalid) {
                this.submitStatus = 'ERROR'
            } else {
                this.submitStatus = 'OK'
                this.addPainting( this.getFormPainting );
                this.$emit('closeCollapse');// So that the parent(Shop.vue) can collapse the form after createion. 
            }            
        },
        validationStatus(validation){// This function defines if the input is rendered in red or normal-> returns a boolean
            return typeof validation != 'undefined' ? validation.$error : false;
        }
    },
}
</script>