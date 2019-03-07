<template>
  <div class="commerce-suggestions">
    <div class="commerce-suggestions__wrapper">
      <div class="commerce-suggestions__item u-hoverable">
        Search "keyword" in Pending Orders
      </div>
      <div class="commerce-suggestions__item u-hoverable">
        Search "keyword" in All Content
      </div>
      <div class="commerce-suggestions__label">
        Pending Orders
      </div>
      <div class="commerce-suggestions__item u-hoverable" v-for="n in 3" :key="n">
        <div class="commerce-item commerce-item--search">
          <img class="commerce-item__image" src="http://placehold.it/100" alt="">
          <div class="commerce-item__content">
            Vestibulum Marmitta commodo urna<br>
            AR385672
          </div>
        </div>
      </div>
      <div class="commerce-suggestions__item u-hoverable">
        Search "keyword" in Pending Orders
      </div>
      <div class="commerce-suggestions__label">
        Catalog
      </div>
      <div class="commerce-suggestions__item u-hoverable" v-for="n in 3" :key="n + 3">
        <div class="commerce-item commerce-item--search">
          <img class="commerce-item__image" src="http://placehold.it/100" alt="">
          <div class="commerce-item__content">
            Vestibulum Marmitta commodo urna<br>
            AR385672
          </div>
        </div>
      </div>
      <div class="commerce-suggestions__item u-hoverable">
        Search "keyword" in Catalog
      </div>
    </div>
    <div class="commerce-suggestions__hints">
      <commerce-icon /> to navigate
      <commerce-icon /> to select
      <commerce-icon /> to dismiss
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
