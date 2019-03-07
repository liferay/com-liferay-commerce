<template>
  <div class="minium-toast (minium-toast--success) (minium-toast--fail)">
    <div class="minium-toast__icon"><commerce-icon /></div>
    <div class="minium-toast__content">
      <slot />
    </div>
  </div>
</template>

<script>
import MiniumIcon from "./MiniumIcon";

export default {
  components: {
    MiniumIcon
  }
};
</script>
