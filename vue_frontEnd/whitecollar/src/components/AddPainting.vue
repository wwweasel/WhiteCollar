<template>
    <div>
        <b-form inline class="d-flex justify-content-between" @submit="createPainting"> 
            <b-input v-model="name" id="name" class="flex-fill" placeholder="Name"></b-input>
            <b-input v-model="authorName" id="authorname" class="flex-fill" placeholder="Authorname"></b-input>                                
            <b-input v-model="price" type="number" id="price" class="flex-fill" placeholder="Price"></b-input>
            <b-button type="submit" id="save" class="flex-fill" variant="secondary">Save</b-button>
        </b-form>
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
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
        
    }
    },
    methods: {
        ...mapActions(['addPainting','setFormPainting']),
        createPainting(event){
            event.preventDefault();
            this.addPainting( this.getFormPainting );
            this.$emit('closeCollapse');// So that the parent(Shop.vue) can collapse the form after createion. 
        },
    },
}
</script>