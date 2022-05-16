import Vue from 'vue'

const categories = Vue.resource('admin/categories{/id}')

export default {
    add: category => categories.save({}, category),
    update: category => categories.update({id: category.id}, category),
    remove: id => categories.remove({id}),
}