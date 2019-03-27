<template xmlns:slot="http://www.w3.org/1999/html">
  <div class="app-container">
    <slot name="search"></slot>

    <el-table v-loading="listLoading" :data="list" height="600" border fit highlight-current-row style="width: 100%">
      <slot name="table"></slot>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit"
                @pagination="search"/>
  </div>
</template>

<script>
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

  export default {
    name: 'PageTable',
    components: {Pagination},
    filters: {},
    methods: {
      search() {
      }
    }
  }
</script>

<style scoped>
  .edit-input {
    padding-right: 100px;
  }

  .cancel-btn {
    position: absolute;
    right: 15px;
    top: 10px;
  }
</style>
