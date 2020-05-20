import Vue from "vue";
import {AXIOS} from './http-commons.js'

const state = {
    fields:  [],
    shops: [],
    formShop: {id: null, name: '', capacity: 1},
    paintings: [],
    formPainting: {id: null, shopId: null, authorName: 'Anonymous', name: '', price: 0},

    createButtonState: false

};

const getters = {
    getShops: (state) => state.shops,
    getShop: (state) => (id) => {
        return state.shops.find( shop => shop.id == id )
    },
    getFormShop: (state) => state.formShop,
    getShopsCount: (state) => state.shops.length,

    getFields: (state) => state.fields,
    
    getPaintings: (state) => state.paintings,
    getShopPaintings: (state) => (shopId) => {
        return state.paintings.filter( painting => painting.shopId === shopId );
    },
    getPainting: (state) => (id) => {
        return state.paintings.find( painting => painting.id == id)
    },
    getFormPainting: (state) => state.formPainting,
    getPaintingsCount: (state) => state.paintings.length,

    getCreateButtonState: (state) => state.createButtonState,
    
};

const actions = {
    async loadShops({commit}){
        const response = await AXIOS.get('http://localhost:8081/stores/' );
        commit('commitShops',response.data);
    },
    async setFormShop({commit}, store){
        commit('commitFormShop',store);
    },
    async loadPaintings({commit}){
        const response = await AXIOS.get('http://localhost:8081/paintings' );
        commit('commitPaintings', response.data);
    },
    // async loadShopPaintings({commit},storeId){
    //     const response = await AXIOS.get('http://localhost:8081/stores/' + storeId + '/paintings' );
    //     commit('commitShopPaintings', response.data);
    // },
    async loadFields({commit}){
        let fields = ['id', 'name', 'capacity','load'];
        fields = [...fields,'actions'];
        commit('commitFields',fields);
    },
    async addShop({commit}, shop){
        const response = await AXIOS.post('http://localhost:8081/stores/', shop );       
        commit('commitAddShop', response.data);
    },
    async deleteShop({commit}, shopId){
        const response = await AXIOS.delete('http://localhost:8081/stores/'+shopId );
        if(response.status === 204){
            commit('commitDeleteShop', shopId);
        }else{
            alert("Could not delete Shop with ID: " + shopId);
        }
    },
    async addPainting({commit}, painting){
        console.log("vuex: " + painting.id +" "+painting.shopId);
        await AXIOS.post('http://localhost:8081/stores/' + painting.storeId + '/paintings', painting )
        .then(response => commit('commitAddPainting', response.data) )
        .catch(error => {
            const index = state.shops.findIndex(shop => shop.id === painting.shopId);
            if(index!==-1){
                Vue.set(state.shops[index], 'full', error.response.data.message );
            }
            alert(state.shops[index].full);
            //console.log("Error:" + state.shops[index].full);
        } );//alert(error.response.data.message)

    },
    async deleteSelectedPainting({commit}, painting){
        const response = await AXIOS.delete('http://localhost:8081/stores/'+ painting.shopId + "/paintings/" + painting.id );
        if(response.status === 204){
            commit('commitdeleteSelectedPainting', painting.id);
        }else{
            alert("Could not delete Painting with ID: " + painting.id);
        }
    },
    async setFormPainting({commit}, formPainting){
        commit('commitFormPainting',formPainting);
    },

    setCreateButtonState({commit}){
        commit('commitCreateButtonState');
    }

};

const mutations = {
    // Table / Store
    commitShops:(state, shops) => {
        state.shops = shops;
        state.shops = shops.map( shop => {
            const newObj = {...shop, load: 0, actions: { editCaption: 'edit', editButtonToggle: false, editButtonVariant: 'outline-primary', deleteCaption: 'delete', deleteButtonVariant: 'outline-danger' }, full: null };
            //console.log(item.name);
            return newObj;
        }); 
    },
    commitFormShop:(state, shop) => {
        state.formShop = shop;
    },
    commitFields:(state, fields) => {
        state.fields = fields;
    },

    // Store
    commitAddShop: (state, newShop) => {

        newShop = {...newShop, load: 0, actions: { editCaption: 'edit', editButtonToggle: false, editButtonVariant: 'outline-primary', deleteCaption: 'delete', deleteButtonVariant: 'outline-danger' }, full: null };

        const index = state.shops.findIndex(shop => shop.id === newShop.id);
        if(index!==-1){
            state.shops.splice(index,1,newShop);
        }else{
            state.shops.unshift(newShop); // unshift is the opposite of push
        }
    },
    commitDeleteShop:(state, shopId) => {
        state.shops = state.shops.filter(shop => shop.id !== shopId);
    },

    // Paintings
    commitPaintings: (state, paintings) => {
        state.paintings = paintings;
    },
    // commitShopPaintings: (state, shopPaintings) => {
    //     state.shopPaintings = shopPaintings;
    // },
    commitTogglePaintingSelectState: (state,paintingId) => {
        state.paintings.forEach( painting => {
            if(painting.id == paintingId){
                painting.selected = !painting.selected;
            }
        });
    },
    commitAddPainting: (state, newPainting) => {
        const index = state.paintings.findIndex(painting => painting.id === newPainting.id);
        if(index!==-1){
            state.paintings.splice( index, 1, newPainting );
        }else{
            state.paintings.unshift( newPainting );

            // Alos increment the Shop load by one
            const index = state.shops.findIndex(shop => shop.id === newPainting.shopId);
            if(index!==-1){
                Vue.set(state.shops[index], 'load', state.shops[index].load+1);
            }

            state.createButtonState = false;
        }
        
    },
    commitdeleteSelectedPainting: (state, paintingId) => {
        state.paintings = state.paintings.filter( painting => painting.id != paintingId);
    },
    commitFormPainting: (state, formPainting) => {
        state.formPainting = formPainting;
    },

    commitCreateButtonState: (state) => {
        state.createButtonState = ! state.createButtonState;
    }
};


export default{
    state,
    getters,
    actions,
    mutations
}
