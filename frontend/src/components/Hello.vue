<template>
    <div>
        <el-row>
            <router-link to="/Newproject">
                <el-button type="primary" plain>新建项目</el-button>
            </router-link>
        </el-row>
        <el-table
                :data="resultdata"
                style="width: 100%"
                row-key="id"
                lazy
                :load="load"
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
            <el-table-column
                    prop="name"
                    label="名称"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="typeName"
                    label="种类"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="crtm"
                    label="日期"
                    sortable>
                <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ dayparse(new Date(scope.row.crtm)) }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    prop="desc"
                    label="描述"
                    sortable>
            </el-table-column>
            <el-table-column
                    label="操作">
                <template slot-scope="scope">
                    <span v-if="(scope.row.id+'').indexOf('m')!==-1">
                        &emsp;<i class="el-icon-pie-chart"></i>
                        <router-link :to="{name:'Logview',params:{mid:scope.row.id.substring(1)}}">查看日志</router-link>
                    </span>
                    <span v-if="(scope.row.id+'').indexOf('m')===-1">
                        <router-link :to="{name:'Newmission',params:{pid:scope.row.id}}">新建任务</router-link>
                    </span>
                </template>
            </el-table-column>
        </el-table>
        <div class="block">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="response.current"
                    :page-size="response.size"
                    layout="total, prev, pager, next"
                    :total="response.total">
            </el-pagination>
        </div>
    </div>

</template>

<script>
    import {AXIOS} from './http-common'
    import dayjs from 'dayjs'

    let id = 1;
    export default {
        data() {
            var resultdata = [];
            return {
                response: {current: 1, total: 0, size: 10},
                resultdata: JSON.parse(JSON.stringify(resultdata))
            }
        },

        methods: {
            append(data) {
                const newChild = {id: id++, label: 'testtest', children: []};
                if (!data.children) {
                    this.$set(data, 'children', []);
                }
                data.children.push(newChild);
            },
            dayparse(str) {
                return dayjs(new Date(str)).format('YYYY-MM-DD HH:mm:ss');
            }, indexOf(str, search) {
                return str.indexOf(search);
            },
            remove(node, data) {
                const parent = node.parent;
                const children = parent.data.children || parent.data;
                const index = children.findIndex(d => d.id === data.id);
                children.splice(index, 1);
            },
            load(tree, treeNode, resolve) {
                AXIOS.get(`/mission/` + tree.id)
                    .then(response => {
                        //处理数据
                        for (let i = 0; i < response.data.length; i++) {
                            response.data[i].hasChildren = false;
                            response.data[i].id = 'm' + this.resultdata[i].id;
                        }
                        resolve(response.data)
                    })
                    .catch(e => {
                        console.error(e);
                    })
            },
            getProjectData() {
                const _this = this;
                AXIOS.get(`/project`, {
                    params: {
                        current: _this.response.current,
                        size: _this.response.size
                    }
                })
                    .then(response => {
                        this.response = response.data;
                        this.resultdata = response.data.records;
                    })
                    .catch(e => {
                        console.error(e);
                    })
            }
            ,
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            }
            ,
            handleCurrentChange(val) {
                this.getProjectData();
            }
        }
        ,
        mounted() {
            this.getProjectData();
        }
    }
    ;
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .custom-tree-container {
        margin-top: 20px;
    }

    .el-tree span {
        font-size: 20px;
    }
</style>
