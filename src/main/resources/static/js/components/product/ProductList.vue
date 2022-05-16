<template>
  <div>
  <h3>Products</h3>
    <v-layout align-start justify-space-around row reverse wrap>
      <v-flex d-flex xs12 sm5 md3>
        <product-form :productAttr="product"/>
      </v-flex>
      <v-flex d-flex xs12 sm7 md9>
        <v-layout align-start justify-start row wrap>
          <product-card v-for="product in sortedProducts"
                        :key="product.id"
                        :product="product"
                        :editProduct="editProduct"/>
        </v-layout>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import ProductCard from "components/product/ProductCard.vue";
import ProductForm from "components/product/ProductForm.vue";

export default {
  name: "ProductList",
  components: {
    ProductCard,
    ProductForm
  },
  data() {
    return {
      product: null
    }
  },
  computed: mapGetters(['sortedProducts']),
  methods: {
    editProduct(product) {
      this.product = product;
    }
  }
}
</script>

<style scoped>

</style>