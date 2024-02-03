<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <span>Lista de proveedores</span>
      </div>
      <div class="card-body">
        <grid-component :data="proveedoresData" />
        
      </div>
      <div class="card-footer">
        <button @click="toggleNewProv">Nuevo Proveedor</button>
      </div>
    </div>
  </div>
</template>


<script>
import config from '@/config.js';
import axios from 'axios';
import GridComponent from '@/components/grid/GridComponent.vue'

export default {
  components: {
    GridComponent
  },
  data() {
    return {
      proveedoresData: {},
    };
  },
  mounted() {
    this.getProveedores();
  },
  methods: {
    getProveedores() {
        axios.get(config.proveedoresEndpoint + '?page=0&size=10')
        .then(response => {
            this.proveedoresData = response.data;
            console.log(this.proveedoresData);
        })
        .catch(error => {
          console.error('Error al obtener proveedores:', error);
        });
    },
    toggleNewProv() {
        this.$emit('toggleNewProv');
    }
  },
};
</script>

