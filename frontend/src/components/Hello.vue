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
                border
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
                    label="日期">
            </el-table-column>
            <el-table-column
                    label="操作">
            </el-table-column>
        </el-table>
        <div class="block">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="this.response.current"
                    :page-size="10"
                    layout="total, prev, pager, next"
                    :total="this.response.total">
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
            var response = [];
            var resultdata = [];
            return {
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

            remove(node, data) {
                const parent = node.parent;
                const children = parent.data.children || parent.data;
                const index = children.findIndex(d => d.id === data.id);
                children.splice(index, 1);
            },
            load(tree, treeNode, resolve) {
                setTimeout(() => {
                    resolve([
                        {
                            id: 31,
                            date: '2016-05-01',
                            name: '王小虎',
                            address: '上海市普陀区金沙江路 1519 弄'
                        }, {
                            id: 32,
                            date: '2016-05-01',
                            name: '王小虎',
                            address: '上海市普陀区金沙江路 1519 弄'
                        }
                    ])
                }, 1000)
            },
            getProjectData() {
                AXIOS.get(`/project`)
                    .then(response => {
                        this.response = response.data;
                        this.resultdata = response.data.records;
                        //处理数据
                        for (let i = 0; i < this.resultdata.length; i++) {
                            this.resultdata[i].hasChildren = true;
                        }
                    })
                    .catch(e => {
                        this.errors.push(e)
                    })
            }
            ,
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            }
            ,
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
            }
        }, mounted() {
            this.getProjectData();
        }
    };
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
