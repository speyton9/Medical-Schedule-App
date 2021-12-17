<template>
  <div class="office-list-outer">
    <office-list />
  </div>
</template>

<script>
import OfficeList from "../components/OfficeList.vue";

export default {
  name: "office-list-view",
  components: {
    OfficeList,
  },
};
</script>

<style scoped>

.office-list-outer {
  display: flex;
  justify-content: center;
}
</style>

