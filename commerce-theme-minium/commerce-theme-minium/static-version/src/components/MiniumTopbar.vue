<template>
  <div class="minium-topbar">
    <div class="minium-topbar__start">
      <a href="#" class="commerce-topbar-button">
        <commerce-icon/>Back
      </a>
      <label class="commerce-topbar-button (is-active) js-toggle-search" for="commerce-search-input">
        <commerce-icon/>
      </label>
    </div>
    <div class="minium-topbar__middle">
      <minium-tabs></minium-tabs>
    </div>
    <div class="minium-topbar__end">
      <div class="minium-topbar__account-selector-wrapper"></div>
      <div class="minium-topbar__cart-wrapper"></div>
    </div>
    <div class="minium-topbar__search" id="commerce-search">
      <!-- <commerce-search placeholder="Search Product Name, SKU, Client…" id="commerce-search-input"/> -->
    </div>
  </div>
</template>

<script>
import AccountList from "./AccountList";
import MiniumIcon from "./MiniumIcon";
import MiniumSearch from "./MiniumSearch";
import MiniumTabs from "./MiniumTabs";
import SmallTable from "./SmallTable";

export default {
  components: {
    AccountList,
    MiniumIcon,
    MiniumSearch,
    MiniumTabs,
    SmallTable
  }
};
</script>
