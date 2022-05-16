import Vue from 'vue'

const products = Vue.resource('admin/products{/id}')

export default {
    add: product => products.save({}, product),
    update: product => products.update({id: product.id}, product),
    remove: id => products.remove({id}),
}