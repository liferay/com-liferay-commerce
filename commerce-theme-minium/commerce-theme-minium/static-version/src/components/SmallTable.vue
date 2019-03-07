<template>
  <table class="commerce-small-table">
    <thead>
      <tr>
        <th>Select Order</th>
        <th class="u-tac">Status</th>
        <th class="u-tar">Last Modified</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="n in 10" :key="n">
        <td>FA987563</td>
        <td class="u-tac">
          <div class="commerce-dot commerce-dot--good"></div>
        </td>
        <td class="u-tar">09.01.18</td>
      </tr>
    </tbody>
  </table>
</template>
