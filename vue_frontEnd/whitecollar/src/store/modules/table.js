//import axios from 'axios';

const state = {
    fields:  [],
    items: [],
};

const getters = {
    getItems: (state) => state.items,

    getFields: (state) => state.fields,

    getItemsCount: (state) => state.items.length,

};

const actions = {
    async toggleButton({commit},btnId){
        commit('toggleButton',btnId);
    },
    async loadItems({commit}){

        let items = [
            { id: 1, name: 'StoreOne', capacity: 40, load: 40 },
            { id: 2, name: 'StoreTwo', capacity: 21, load: 40 },
            { id: 3, name: 'StoreThree', capacity: 89, load: 40 },
            { id: 4, name: 'StoreFour', capacity: 38, load: 40 },
            { id: 5, name: 'StoreFive', capacity: 40, load: 44 },
            { id: 6, name: 'StoreSix', capacity: 21, load: 40 },
            { id: 7, name: 'StoreSeven', capacity: 89, load: 40 },
            { id: 8, name: 'StoreEight', capacity: 38, load: 44 },
            { id: 9, name: 'StoreNine', capacity: 40, load: 40 },
            { id: 10, name: 'StoreTen', capacity: 21, load: 40 },
            { id: 11, name: 'StoreEleven', capacity: 89, load: 44 },
            { id: 12, name: 'StoreTwelve', capacity: 38, load: 40 }
        ];
        items = items.map( item => {
            const newObj = {...item, actions: { caption: 'edit', buttonToggle: false, buttonVariant: 'outline-primary' } };
            return newObj;
        }); 

        commit('commitItems',items);
        
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
    }
};

const mutations = {
    commitItems:(state, items) => {
        state.items = items;
    },
    commitFields:(state, fields) => {
        state.fields = fields;
    },
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
    commitAddStore: (state,newStore) => {
        state.items.push(newStore);
    }
};


export default{
    state,
    getters,
    actions,
    mutations
}
