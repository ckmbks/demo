<template>
  <page-table ref="pageTable">
    <template slot="search">
      <div class="filter-container">
        <el-input placeholder="用户名" v-model="listQuery.userName" style="width: 200px;" class="filter-item"
                  @keyup.enter.native="handleFilter"/>
        <el-button v-waves class="filter-item" type="primary" icon="el-icon-search"
                   @click="dosearch">搜索
        </el-button>

        <!--<el-select v-model="listQuery.importance" :placeholder="$t('table.importance')" clearable style="width: 90px"-->
        <!--class="filter-item">-->
        <!--<el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item"/>-->
        <!--</el-select>-->
        <!--<el-select v-model="listQuery.type" :placeholder="$t('table.type')" clearable class="filter-item"-->
        <!--style="width: 130px">-->
        <!--<el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name+'('+item.key+')'"-->
        <!--:value="item.key"/>-->
        <!--</el-select>-->
        <!--<el-select v-model="listQuery.sort" style="width: 140px" class="filter-item" @change="handleFilter">-->
        <!--<el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key"/>-->
        <!--</el-select>-->
        <!--<el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">{{-->
        <!--$t('table.search') }}-->
        <!--</el-button>-->
        <!--<el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit"-->
        <!--@click="handleCreate">{{ $t('table.add') }}-->
        <!--</el-button>-->
        <!--<el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download"-->
        <!--@click="handleDownload">{{ $t('table.export') }}-->
        <!--</el-button>-->
        <!--<el-checkbox v-model="showReviewer" class="filter-item" style="margin-left:15px;" @change="tableKey=tableKey+1">-->
        <!--{{-->
        <!--$t('table.reviewer') }}-->
        <!--</el-checkbox>-->
      </div>
    </template>
    <template slot="table">
      <el-table-column align="center" label="ID" min-width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="180px" align="center" label="用户名">
        <template slot-scope="scope">
          <span>{{ scope.row.userName }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="120px" align="center" label="电话">
        <template slot-scope="scope">
          <span>{{ scope.row.phone }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="120px" align="center" label="性别">
        <template slot-scope="scope">
          <span>{{ scope.row.sex }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="120px" align="center" label="体重">
        <template slot-scope="scope">
          <span>{{ scope.row.weight }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="120px" align="center" label="用户类型">
        <template slot-scope="scope">
          <span>{{ scope.row.userType }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="120px" align="center" label="添加时间">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="120px" align="center" label="添加人">
        <template slot-scope="scope">
          <span>{{ scope.row.createUser }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="Actions" min-width="120">
        <template slot-scope="scope">
          <router-link :to="'/example/edit/'+scope.row.id">
            <el-button type="primary" size="small" icon="el-icon-edit">Edit</el-button>
          </router-link>
        </template>
      </el-table-column>
    </template>
  </page-table>
</template>
<script>
  import {getUserPage} from '@/api/login'
  import PageTable from '@/components/Table/PageTable';
  import BasePage from '@/components/Table/BasePage';
  import waves from '@/directive/waves' // Waves directive

  PageTable.methods.getPage = function () {
    return getUserPage(this.listQuery);
  }

  export default {
    name: 'UserList',
    components: {PageTable},
    // extends: BasePage,
    directives: { waves },
    filters: {
      statusFilter(status) {
        const statusMap = {
          published: 'success',
          draft: 'info',
          deleted: 'danger'
        }
        return statusMap[status]
      }
    },
    data() {
      return {
        tableKey: 0,
        list: null,
        total: 0,
        listLoading: true,
        listQuery: {
          page: 1,
          limit: 20,
          importance: undefined,
          userName: undefined,
          type: undefined,
          sort: '+id'
        },
        rules: {
          type: [{required: true, message: 'type is required', trigger: 'change'}],
          timestamp: [{type: 'date', required: true, message: 'timestamp is required', trigger: 'change'}],
          title: [{required: true, message: 'title is required', trigger: 'blur'}]
        },
      }
    },
    methods: {
      dosearch() {
        this.$refs.pageTable.search()
      }
    }
  }
</script>
