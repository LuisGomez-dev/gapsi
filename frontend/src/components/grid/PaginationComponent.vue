<template>
  <div>
    <button @click="goToPage(currentPage - 1)" :disabled="currentPage === 1">Anterior</button>
    <span>{{ currentPage }} de {{ totalPages }}</span>
    <button @click="goToPage(currentPage + 1)" :disabled="currentPage === totalPages">Siguiente</button>
  </div>
</template>

<script>
export default {
  props: {
    totalPages: {
      type: Number,
      required: true,
    },
    currentPage: {
      type: Number,
      required: true,
    },
  },
  methods: {
    goToPage(page) {
      if (page >= 1 && page <= this.totalPages) {
        this.$emit('pageChanged', page);
      }
    },
  },
};
</script>

<style>
/* Estilos según sea necesario */
</style>
