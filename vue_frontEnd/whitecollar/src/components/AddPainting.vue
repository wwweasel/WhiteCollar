<template>
    <div>
        <b-form inline class="d-flex justify-content-between" @submit="createPainting"> 
            <b-input v-model="newPainting.authorName" id="authorname" class="flex-fill" placeholder="Authorname"></b-input>                                
            <b-input v-model="newPainting.name" id="name" class="flex-fill" placeholder="Name"></b-input>
            <b-input v-model="newPainting.price" type="number" id="price" class="flex-fill" placeholder="Price"></b-input>
            <b-button type="submit" id="save" class="flex-fill" variant="secondary">Save</b-button>
        </b-form>
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
export default {
    name: 'AddPainting',
    props:{
        storeId:{
            type: Number,
        },
        paintingId:{
            type: Number,
            default: null
        },
        paintingName:{
            type: String,
            default: ''
        },
        paintingAuthorName:{
            type: String,
            default: 'anonymous'
        },
        paintingPrice:{
            type: Number,
            default: 0
        }
    },
    data(){
    return{
        newPainting: { 
            id: this.paintingId,
            storeId: this.storeId,
            authorName: this.paintingAuthorName,
            name: this.paintingName,
            price: this.paintingPrice
        }
    }
    },
    methods: {
        ...mapActions(['addPainting']),
        createPainting(event){
            event.preventDefault();
            this.addPainting({...this.newPainting});
            this.$emit('createPainting');// So that the parent(Store.vue) can collapse the form after createion. 
        },
    },
    computed: {
        ...mapGetters(['getPainting']),
    },
    beforeDestroy(){
      console.log("Before destruction from Store");
    }
}
</script>