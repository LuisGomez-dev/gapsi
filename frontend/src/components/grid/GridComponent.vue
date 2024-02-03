<template>
    <div>
      <div class="table-responsive"> 
        <table style="width:100%"> 
          <thead class="thead-dark"> 
            <tr>
              <th>Nombre</th>
              <th>Razón Social</th>
              <th>Dirección</th>
              <th>Borrar</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="proveedor in proveedores" :key="proveedor.nombre">
              <td>{{ proveedor.nombre }}</td>
              <td>{{ proveedor.razonSocial }}</td>
              <td>{{ proveedor.direccion }}</td>
              <td>
                <button @click="borrarProveedor(proveedor)">Borrar</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <pagination :totalPages="totalPages" :currentPage="currentPage" @pageChanged="handlePageChange" />
    </div>
</template>

<script>
import axios from 'axios';
import config from '@/config.js';
import Pagination from '@/components/grid/PaginationComponent.vue'; 

export default {
  props: {
    data: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      proveedores: [],
      totalPages: 10,
      currentPage: 1,
    };
  },
  watch: {
    data: {
      handler() {
        this.loadData();
      },
      immediate: true,
    },
  },
  methods: {
    loadData() {
      this.proveedores = this.data.content;
      this.totalPages = this.data.totalPages;
    },
    handlePageChange(newPage) {
      this.currentPage = newPage;
      this.getProveedores();
    },
    getProveedores() {
      axios.get(config.proveedoresEndpoint + '?page=' + (this.currentPage - 1) + '&size=10')
        .then(response => {
            this.proveedores = response.data.content;
            this.currentPage = response.pageable.pageNumber;
            this.totalPages = response.totalPages;
        })
        .catch(error => {
          console.error('Error al obtener proveedores:', error);
        });
    },
    borrarProveedor(proveedor) {
      const url = config.proveedoresEndpoint + '/' + proveedor.nombre;

      axios.delete(url)
        .then(response => {
          console.log('Proveedor eliminado exitosamente:');
          console.log(`Borrando proveedor: ${proveedor.nombre}`);
          this.getProveedores() ;
        })
        .catch(error => {
          console.error('Error al eliminar proveedor:', error);
        });

    },
  },
  components: {
    Pagination,
  },
};
</script>

