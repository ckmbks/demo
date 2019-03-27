<template xmlns:slot="http://www.w3.org/1999/html">
  <div class="app-container">
    <slot name="search"></slot>

    <el-table v-loading="listLoading" :data="list" height="600" border fit highlight-current-row style="width: 100%">
      <slot name="table"></slot>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit"
                @pagination="refresh"/>
  </div>
</template>

<script>
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

  export default {
    name: 'PageTable',
    components: {Pagination},
    filters: {},
    data() {
      return {
        list: null,
        total: 0,
        listLoading: true,
        listQuery: {
          page: 1,
          limit: 20
        }
      }
    },
    created() {
      this.search()
    },
    methods: {
      refresh() {
        this.listLoading = true
        this.getPage(this.listQuery).then(response => {
          this.list = response.data.list
          this.total = response.data.total
          this.listLoading = false
        })
      },
      getPage() {
      },
      search() {
        this.listQuery.page = 1
        this.refresh()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.refresh()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.refresh()
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
