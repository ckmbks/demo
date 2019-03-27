<script>
  export default {
    name: 'BasePage',
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
