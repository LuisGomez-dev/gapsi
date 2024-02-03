<script setup>
import ListProvComponent from '@/components/ListProvComponent.vue';
import NewProvComponent from '@/components/NewProvComponent.vue';
import { ref } from 'vue';

const showNewProvComponent = ref(false);

const toggleNewProvComponent = () => {
  showNewProvComponent.value = !showNewProvComponent.value;
};
</script>

<template>
  <main>
    <ListProvComponent v-if="!showNewProvComponent" @toggleNewProv="toggleNewProvComponent" />
    <NewProvComponent v-if="showNewProvComponent" @toggleProv="toggleNewProvComponent" />
  </main>
</template>
