<template>
    <div>
      <div class="card">
        <div class="card-header">
          <span>Agregar Proveedor</span>
        </div>
        <div class="card-body">
          <form @submit.prevent="agregarProveedor">
            <div class="mb-3">
              <label for="nombre" class="form-label">Nombre:</label>
              <input v-model="nuevoProveedor.nombre" type="text" class="form-control" id="nombre" required>
            </div>
            <div class="mb-3">
              <label for="razonSocial" class="form-label">Razón Social:</label>
              <input v-model="nuevoProveedor.razonSocial" type="text" class="form-control" id="razonSocial" required>
            </div>
            <div class="mb-3">
              <label for="direccion" class="form-label">Dirección:</label>
              <input v-model="nuevoProveedor.direccion" type="text" class="form-control" id="direccion" required>
            </div>
            <button type="submit" class="btn btn-primary">Guardar</button>
            <button type="button" @click="toggleProv" class="btn btn-primary">Cerrar</button>
          </form>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import config from '@/config.js';
  import axios from 'axios';

  export default {
    data() {
      return {
        nuevoProveedor: {
          nombre: '',
          razonSocial: '',
          direccion: '',
        },
      };
    },
    methods: {
      agregarProveedor() {
        axios.post( config.proveedoresEndpoint, this.nuevoProveedor)
        .then(response => {
          console.log('Proveedor agregado exitosamente:', response.data);
          this.$emit('toggleProv');
        })
        .catch(error => {
          console.error('Error al agregar proveedor:', error);
        });
      },  
      toggleProv() {
        this.$emit('toggleProv');
      },
    },
  };
  </script>
  

  