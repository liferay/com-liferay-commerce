<template>
  <a
    href="#"
    :class="[
      'commerce-button',
      big && 'commerce-button--big',
      small && 'commerce-button--small',
      outline && 'commerce-button--outline'
    ]"
  >
    <slot />
  </a>
</template>

<script>
export default {
  props: {
    block: Boolean,
    big: Boolean,
    outline: Boolean,
    small: Boolean,
    text: String
  }
};
</script>
