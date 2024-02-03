<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <span>e-Comerce Gapsi</span>
      </div>
      <div class="card-body">
        <img src="@/assets/persona.png" alt="Candidato" class="logo">
        <div class="body-content">
          <div>{{ candidato }}</div>
          <br/>
          <div   style="text-align: center;">
          <button class="continuar" @click="administracion">Continuar</button>
          </div>
          
        </div>
      </div>
      <div class="card-footer">
        <div class="version">{{ version }}</div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import config from '@/config.js';
  export default {
    data() {
      return {
        version: 'version',
        candidato: 'candidato',
      };
    },
    mounted() {
      this.getWelcomeCandidato();
      this.getWelcomeVersion();
    },
    methods: {
      getWelcomeCandidato() {
        axios.get(config.welcomeCandidatoEndpoint)
          .then(response => {
            console.log(response);
            this.candidato = response.data;
          })
          .catch(error => {
            console.error('Error obteniendo mensaje de bienvenida:', error);
          });
      },
      getWelcomeVersion() {
        axios.get(config.welcomeVersionEndpoint)
          .then(response => {
            this.version = response.data;
          })
          .catch(error => {
            console.error('Error obteniendo versión:', error);
          });
      },
      administracion() {
        this.$router.push('/admin');
      },
    },
  };
</script>

<style scoped>
.container {
  display: flex;
}

.content {
  padding: 20px;
}

.card {
  border: 1px solid rgba(0, 0, 0, 0.125);
  border-radius: 0.25rem;
  overflow: hidden;
  margin: auto;
}
.card-header {
  background-color: #f8f9fa;
  color: #000;
  padding: 10px;
  text-align: left;
}

.card-body {
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.card-footer {
  background-color: #f8f9fa;
  color: #000;
  padding: 10px;
  text-align: right;
}

.logo {
  width: 30%;
  height: auto;
}

.candidato {
  margin-top: 10px;
  align-self: flex-end;
}

.version {
  display: inline-block; 
}


.continuar {
  margin-top: 10px;
  background-color: #007BFF; 
  color: #fff;
  padding: 8px 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

</style>
