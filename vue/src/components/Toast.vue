<template>
  <transition name="fade-toast">
    <div v-if="visible" class="toast" :class="type">
      {{ message }}
    </div>
  </transition>
</template>

<script>
export default {
  data() {
    return {
      visible: false,
      message: '',
      type: 'success', // 'success' | 'error'
      timeout: null
    };
  },
  methods: {
    show(msg, type = 'success') {
      this.message = msg;
      this.type = type;
      this.visible = true;

      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        this.visible = false;
      }, 3000);
    }
  }
};
</script>

<style scoped>
.toast {
  position: fixed;
  bottom: 30px;
  left: 50%;
  transform: translateX(-50%);
  padding: 1rem 2rem;
  border-radius: 10px;
  font-weight: bold;
  font-size: 1rem;
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.2);
  z-index: 1000;
  color: #fff;
  min-width: 260px;
  text-align: center;
  transition: all 0.3s ease;
}

/* Toast Types */
.success {
  background-color: #28a745;
}

.error {
  background-color: #dc3545;
}

/* Enter/Leave Transitions */
.fade-toast-enter-active,
.fade-toast-leave-active {
  transition: all 0.4s ease;
}

.fade-toast-enter-from,
.fade-toast-leave-to {
  opacity: 0;
  transform: translateX(-50%) translateY(20px) scale(0.95);
}
</style>
