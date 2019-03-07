<template>
  <div class="commerce-search">
    <div class="commerce-search__input">
      <input type="text" :placeholder="placeholder" :id="id">
    </div>
    <a href="#" class="commerce-search__button">
      <commerce-icon/>
    </a>
  </div>
</template>

<script>
import MiniumIcon from "./MiniumIcon";

export default {
  props: {
    id: String,
    placeholder: String
  },
  components: {
    MiniumIcon
  }
};
</script>
