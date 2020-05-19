import {AXIOS} from './http-commons.js'

const state = {
    fields:  [],
    shops: [],
    formShop: {id: null, name: '', capacity: 1},
    paintings: [],
    formPainting: {id: null, authorName: 'Anonymous', name: '', price: 0}

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
    getPainting: (state) => (id) => {
        return state.paintings.find( painting => painting.id == id)
    },
    getFormPainting: (state) => state.formPainting,
    getPaintingsCount: (state) => state.paintings.length,

    getSelectedPaintingIds: (state)=>{
        let filtered =  state.paintings.filter( painting => painting.selected );
        let output = []
        filtered.forEach(painting=>{
            output.push(painting.id);
        });
        return output;
    }
    
};

const actions = {
    async loadShops({commit}){

        const response = await AXIOS.get('http://localhost:8081/stores/' );
        commit('commitShops',response.data);
        
    },
    async setFormShop({commit}, store){
        commit('commitFormShop',store);
    },
    async loadPaintings({commit},storeId){
        const response = await AXIOS.get('http://localhost:8081/stores/' + storeId + '/paintings' );
        commit('commitPaintings', response.data);
    },
    async loadFields({commit}){
        let fields = ['id', 'name', 'capacity', 'load'];
        fields = [...fields, 'actions'];
        commit('commitFields',fields);
    },
    async addShop({commit}, shop){
        const response = await AXIOS.post('http://localhost:8081/stores/', shop );
        let clone = response.data;
        clone = {...clone, actions: { caption: 'edit', buttonToggle: false, buttonVariant: 'outline-primary' } };
        commit('commitAddShop', clone);
    },
    async deleteShop(shopId){
        const response = await AXIOS.delete('http://localhost:8081/stores/'+shopId );
        console.log("Deleted Shop: " + response.data);
        //commit('commitDeleteShop', response.data);
    },
    async addPainting({commit}, painting){
        const response = await AXIOS.post('http://localhost:8081/stores/' + painting.storeId + '/paintings', painting );
        commit('commitAddPainting', response.data);
    },
    async deleteSelectedPainting({commit}, id){
        commit('commitdeleteSelectedPainting', id);
    },
    async setFormPainting({commit}, formPainting){
        commit('commitFormPainting',formPainting);
    }

};

const mutations = {
    // Table / Store
    commitShops:(state, shops) => {
        state.shops = shops;
        state.shops = shops.map( shop => {
            const newObj = {...shop, actions: { editCaption: 'edit', editButtonToggle: false, editButtonVariant: 'outline-primary', deleteCaption: 'delete', deleteButtonVariant: 'outline-danger' } };
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
        const index = state.shops.findIndex(shop => shop.id === newShop.id);
        if(index!==-1){
            state.shops.splice(index,1,newShop);
        }else{
            state.shops.unshift(newShop); // unshift is the opposite of push
        }
    },
    commitDeleteShop:(state,shopId) => {
        state.shops = state.shops.filter(shop => shop.id !== shopId);
    },

    // Paintings
    commitPaintings: (state, paintings) => {
        state.paintings = paintings;
    },
    commitTogglePaintingSelectState: (state,paintingId) => {
        state.paintings.forEach( painting => {
            if(painting.id == paintingId){
                painting.selected = !painting.selected;
            }
        });
    },
    commitAddPainting: (state, paintingDTO) => {
        
        const index = state.paintings.findIndex(painting => painting.id === paintingDTO.id);
        if(index!==-1){
            state.paintings.splice(index,1,paintingDTO);
        }else{
            state.paintings.unshift(paintingDTO);
        }
        
    },
    commitdeleteSelectedPainting: (state, id) => {
        state.paintings = state.paintings.filter( painting => painting.id != id);
    },
    commitFormPainting: (state, formPainting) => {
        state.formPainting = formPainting;
    }
};


export default{
    state,
    getters,
    actions,
    mutations
}
