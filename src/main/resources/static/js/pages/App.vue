<template>
  <v-app>
    <v-toolbar app>
      <v-toolbar-title>Pinemelon</v-toolbar-title>
      <v-spacer></v-spacer>
      <span v-if="profile">{{ profile.username }}</span>
      <v-btn v-if="profile" icon href="/logout">
        <v-icon>logout</v-icon>
      </v-btn>
    </v-toolbar>
    <v-content>
      <v-container v-if="!profile">You have to sign in via
        <a href="/login">Google</a>
      </v-container>
<!--      <v-container v-if="profile">-->
<!--        <category-list :categories="categories"/>-->
<!--      </v-container>-->
      <v-container v-if="profile">
        <product-list/>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
  import { mapState, mapMutations } from 'vuex'
  import ProductList from "components/product/ProductList.vue";
  import { addHandler } from "util/ws";

  export default {
    components: {
      ProductList
    },
    computed: mapState(['profile']),
    methods: mapMutations(['addProductMutation', 'updateProductMutation', 'removeProductMutation']),
    created() {
      addHandler(data => {
        if (data.objectType === 'PRODUCT'){
          switch (data.eventType){
            case 'CREATE':
              this.addProductMutation(data.body)
              break
            case 'UPDATE':
              this.updateProductMutation(data.body)
              break
            case 'REMOVE':
              this.removeProductMutation(data.body)
              break
            default:
              console.error(`Looks like the event type is unknown "${data.eventType}"`)
          }
        } else {
          console.error(`Looks like the event type is unknown "${data.objectType}"`)
        }
      })
    }
  }
</script>

<style>
.main-app {
  color: maroon;
}
</style>