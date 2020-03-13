<template>
    <div class="maindiv">
        <h2>添加数据</h2>
        <hr/>
        <el-form :model="dataForm" :rules="rules" ref="dataForm" label-width="100px" class="form">
            <el-form-item label="数据集" prop="datachose">
                <el-transfer
                        filterable
                        :filter-method="filterMethod"
                        filter-placeholder="请输入关键字"
                        v-model="dataForm.datachose"
                        :titles="['待选区', '已选区']"
                        :data="data">
                </el-transfer>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('dataForm')">下一步</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        data() {
            const generateData = _ => {
                const data = [];
                const cities = ['上海', '北京', '广州', '深圳', '南京', '西安', '成都'];
                const pinyin = ['shanghai', 'beijing', 'guangzhou', 'shenzhen', 'nanjing', 'xian', 'chengdu'];
                cities.forEach((city, index) => {
                    data.push({
                        label: city,
                        key: index,
                        pinyin: pinyin[index]
                    });
                });
                return data;
            };
            return {
                data: generateData(),
                filterMethod(query, item) {
                    return item.pinyin.indexOf(query) > -1;
                },
                dataForm: {
                    datachose: []
                },
                rules: {
                    datachose: [
                        {required: true, message: '请选择数据集', trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$router.push({path: '/enhanceData'});
                    } else {
                        return false;
                    }
                });
            }
        }
    }
</script>


<style lang="scss">
    @import "../../assets/style/app.scss";
</style>
