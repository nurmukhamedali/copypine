<template>
  <v-flex d-flex xs6 sm4 md3>
    <v-card class="ma-2">
        <v-card-title primary-title>
          <div>
            <div class="headline">
              <span>$</span>{{ product.price }}
            </div>
            <small>{{ product.name }}</small>
<!--            <small>{{ product.brand }}</small>-->
          </div>
        </v-card-title>
      <v-divider></v-divider>
      <v-card-actions>
<!--        <v-btn round depressed dark small>-->
<!--          <v-icon small>add</v-icon>-->
<!--          Add-->
<!--        </v-btn>-->
        <v-btn icon @click="edit"><v-icon>edit</v-icon></v-btn>
        <v-btn icon @click="del"><v-icon>delete</v-icon></v-btn>
      </v-card-actions>
    </v-card>
  </v-flex>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: "ProductCard",
  props: ['product', 'editProduct'],
  methods: {
    ...mapActions(['removeProductAction']),
    edit() {
      this.editProduct(this.product)
    },
    del() {
      this.removeProductAction(this.product)
    }
  }
}
</script>

<style scoped>

</style>