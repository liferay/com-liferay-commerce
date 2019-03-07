<template>
  <div class="commerce-account-list">
    <div class="commerce-account-list__title">Select Account...</div>
    <a href="#" class="commerce-account-list__item u-hoverable" v-for="n in 10" :key="n">
      <img src="/avatar.jpg" alt="">
      <span>Forward Auto Service</span>
    </a>
  </div>
</template>
