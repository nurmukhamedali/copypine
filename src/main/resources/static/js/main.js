function getIndex(list, id){
    for (let i = 0; i < list.length; i++) {
        if (list[i].id === id){
            return i;
        }
    }
    return -1;
}

var categoryApi = Vue.resource('admin/categories{/id}');

Vue.component('category-form', {
    props: ['categories', 'categoryAttr'],
    data: function (){
        return {
            id: null,
            name: ''
        }
    },
    watch: {
        categoryAttr: function(newVal, oldVal){
            this.name = newVal.name;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder="Enter category name" v-model="name"/>' +
            '<input type="button" value="Create" @click="post">' +
        '</div>',
    methods: {
        post: function () {
            var category = {name: this.name};

            if (this.id) {
                categoryApi.update({id: this.id}, category).then(result => {
                    result.json().then(data => {
                        let index = getIndex(this.categories, data.id);
                        this.categories.splice(index, 1, data);
                        this.name = '';
                        this.id = '';
                    })
                })
            } else {
                categoryApi.save({}, category).then(result => {
                    result.json().then(data => {
                        this.categories.push(data);
                        this.name = '';
                    })
                })
            }
        }
    }
})

Vue.component('category-card', {
    props: ['category', 'editMethod', 'categories'],
    template:
        '<div>' +
        '<i>({{ category.id }})</i>{{ category.name }}' +
        '<span>' +
            '<input type="button" value="Edit" @click="edit" />' +
            '<input type="button" value="Delete" @click="del">' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.category);
        },
        del: function () {
            categoryApi.remove({id: this.category.id}).then(result => {

                if (result.ok){
                    this.categories.splice(this.categories.indexOf(this.category), 1)
                }
            })
        }
    }
});

Vue.component('category-list', {
    props: ["categories"],
    data: function (){
        return {
            category: null
        }
    },
    template:
        '<div>' +
        '<h3>Categories</h3>' +
        '<div>' +
            '<category-form :categories="categories" :categoryAttr="category"/>' +
            '<category-card v-for="category in categories" :key="category.id" ' +
                ':category="category" ' +
                ':editMethod="editMethod"' +
                ':categories="categories"' +
                '></category-card>' +
        '</div>' +
        '</div>',

    methods: {
        editMethod: function (category) {
            this.category = category;
        }
    }
})

var app = new Vue({
    el: '#app',
    data: {
        categories: frontendData.categories,
        profile: frontendData.profile

    },
    created: function () {
        // categoryApi.get().then(result =>
        //     result.json().then(data =>
        //         data.forEach(category => this.categories.push(category))
        //     )
        // )
    },
    template:
        '<div>' +
        '<div v-if="!profile">You have to sign in via <a href="/login">Google</a></div>' +
        '<div v-else>' +
            '<div>{{ profile.username }}&nbsp;<a href="/logout">Sign out</a></div>' +
            '<category-list :categories="categories"/>' +
        '</div>' +
        '</div>'
});