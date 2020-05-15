import axios from 'axios';

const state = {
    fields:  [],
    items: [],
    paintings: []
};

const getters = {
    getItems: (state) => state.items,
    getItem: (state) => (id) => {
        return state.items.find( item => item.id == id )
    },
    getItemsCount: (state) => state.items.length,

    getFields: (state) => state.fields,
    
    getPaintingsByStoreId: (state) => (storeId) =>{
        return state.paintings.filter( painting => painting.storeId == storeId )
    },
    getPaintings: (state) => state.paintings,
    getPainting: (state) => (id) => {
        return state.paintings.find( painting => painting.id == id)
    },
    getPaintingsCount: (state) => state.paintings.length,
    
    getButtonstate: (state) => (id) => {
        return state.items.find( item => item.id == id ).actions.buttonToggle
    },
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
    async loadItems({commit}){
        //console.log("!!!! loadItems !!!!!");
        let items = [
            { id: 1, name: 'StoreOne', capacity: 40 },
            { id: 2, name: 'StoreTwo', capacity: 21 },
            { id: 3, name: 'StoreThree', capacity: 89 },
            { id: 4, name: 'StoreFour', capacity: 38 },
            { id: 5, name: 'StoreFive', capacity: 40 },
            { id: 6, name: 'StoreSix', capacity: 21 },
            { id: 7, name: 'StoreSeven', capacity: 89 },
            { id: 8, name: 'StoreEight', capacity: 38 },
            { id: 9, name: 'StoreNine', capacity: 40 },
            { id: 10, name: 'StoreTen', capacity: 21 },
            { id: 11, name: 'StoreEleven', capacity: 89 },
            { id: 12, name: 'StoreTwelve', capacity: 38 }
        ];
        items = items.map( item => {
            const newObj = {...item, actions: { caption: 'edit', buttonToggle: false, buttonVariant: 'outline-primary' } };
            return newObj;
        }); 

        commit('commitItems',items);
        
    },
    async loadPaintings({commit}){

        let paintings = [
            { id: 1, storeId: 1, authorName: 'author name', name: 'PaintingOne', price: 40 },
            { id: 2, storeId: 2, authorName: 'aut horname', name: 'PaintingTwo', price: 21 },
            { id: 3, storeId: 3, authorName: 'author name', name: 'PaintingThree', price: 89 },
            { id: 4, storeId: 1, authorName: 'author name', name: 'PaintingFour', price: 38 },
            { id: 5, storeId: 1, authorName: 'author name', name: 'PaintingOne', price: 40 },
            { id: 6, storeId: 1, authorName: 'authorna me', name: 'PaintingOne', price: 21 },
            { id: 7, storeId: 2, authorName: 'aut horname', name: 'PaintingOne', price: 89 },
            { id: 8, storeId: 1, authorName: 'author name', name: 'PaintingOne', price: 38 },
            { id: 9, storeId: 3, authorName: 'aut horname', name: 'PaintingOne', price: 40 },
            { id: 10, storeId: 1, authorName: 'author name', name: 'PaintingOne', price: 21 },
            { id: 11, storeId: 2, authorName: 'authorname', name: 'PaintingOne', price: 89 },
            { id: 12, storeId: 4, authorName: 'authorna me', name: 'PaintingOne', price: 38 },
            { id: 13, storeId: 1, authorName: 'author name', name: 'PaintingOne', price: 40 },
            { id: 14, storeId: 2, authorName: 'aut horname', name: 'PaintingTwo', price: 21 },
            { id: 15, storeId: 3, authorName: 'author name', name: 'PaintingThree', price: 89 },
            { id: 16, storeId: 1, authorName: 'author name', name: 'PaintingFour', price: 38 },
            { id: 17, storeId: 1, authorName: 'author name', name: 'PaintingOne', price: 40 },
            { id: 18, storeId: 1, authorName: 'authorna me', name: 'PaintingOne', price: 21 },
            { id: 19, storeId: 2, authorName: 'aut horname', name: 'PaintingOne', price: 89 },
            { id: 20, storeId: 1, authorName: 'author name', name: 'PaintingOne', price: 38 },
            { id: 21, storeId: 3, authorName: 'aut horname', name: 'PaintingOne', price: 40 },
            { id: 22, storeId: 1, authorName: 'author name', name: 'PaintingOne', price: 21 },
            { id: 23, storeId: 2, authorName: 'authorname', name: 'PaintingOne', price: 89 },
            { id: 24, storeId: 4, authorName: 'authorna me', name: 'PaintingOne', price: 38 }
        ];
        // paintings = paintings.map( painting => {
        //     const newObj = {...painting, selected: false };
        //     return newObj;
        // });

        commit('commitPaintings',paintings);
        
    },
    async loadFields({commit}){
        
        let fields = ['id', 'name', 'capacity', 'load'];
        fields = [...fields, 'actions'];
        commit('commitFields',fields);
    },
    async addStore({commit}, newStore){
        // Usually you POST to the Database and feed the respond to the commit

        // Create an ID
        newStore.id = this.getters.getItemsCount+1;
        // Add editButton
        newStore = {...newStore, actions: { caption: 'edit', buttonToggle: false, buttonVariant: 'outline-primary' } };
        console.log(newStore);
        commit('commitAddStore', newStore);
    },
    async editStore({commit}, editStoreFrag){
        commit('commitEditStore',editStoreFrag);
    },
    async toggleButton({commit},btnId){
        commit('toggleButton',btnId);
    },
    async addPainting({commit}, newPainting){
        // Usually you POST to the Database and feed the respond to the commit
        commit('commitAddPainting', newPainting);
    },
    async deleteSelectedPainting({commit}, id){
        commit('commitdeleteSelectedPainting', id);
    }

};

const mutations = {
    // Table / Store
    commitItems:(state, items) => {
        state.items = items;
    },
    commitFields:(state, fields) => {
        state.fields = fields;
    },
    // Button
    toggleButton: (state, btnId) => {
        state.items.forEach(function(item){
            if(item.id == btnId){
                item.actions.buttonToggle = !item.actions.buttonToggle;
                if(item.actions.buttonToggle){
                    item.actions.buttonVariant = 'outline-danger';
                }else{
                    item.actions.buttonVariant = 'outline-primary';
                }
            }

        });
    },
    // Store
    commitAddStore: (state,newStore) => {
        state.items.push(newStore);
    },
    commitEditStore: (state, editStoreFrag)=>{
        state.items.forEach( item => {
            if(item.id == editStoreFrag.id){
                item.name = editStoreFrag.name;
                item.capacity = editStoreFrag.capacity;
            }
        });
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
    commitAddPainting: (state, newPainting) => {
        
        if(newPainting.id===null){
            let num = 0;
            state.paintings.forEach(painting => {
                if( painting.id > num ){
                    num = painting.id;
                }
            });
            newPainting.id = num +1;
            //state.paintings.forEach(painting=>console.log(painting.id));
            state.paintings.push(newPainting);

        }else{
            const index = state.paintings.findIndex(painting => painting.id === newPainting.id);
            if(index!==-1){
                state.paintings.splice(index,1,newPainting);
            }
        }
        
    },
    commitdeleteSelectedPainting: (state,id) => {
        state.paintings = state.paintings.filter( painting => painting.id != id);
    }
};


export default{
    state,
    getters,
    actions,
    mutations
}
