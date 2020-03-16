<template>
    <div class="maindiv">
        <h2>参数设置</h2>
        <hr/>
        <el-form :model="parametersForm" :rules="rules" ref="parametersForm" label-width="150px" class="form">
            <el-form-item label="网络选择" prop="net">
                <el-select v-model="parametersForm.net" placeholder="请选择网络">
                    <el-option
                            v-for="item in netoptions"
                            :key="item.id"
                            :label="item.cnname"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="训练轮数Epochs" prop="epochs">
                <el-input v-model="parametersForm.epochs"></el-input>
            </el-form-item>
            <el-form-item label="学习率Learningrate" prop="learningrate">
                <el-input v-model="parametersForm.learningrate"></el-input>
            </el-form-item>
            <el-form-item label="批大小Batch" prop="batch">
                <el-input v-model="parametersForm.batch"></el-input>
            </el-form-item>
            <el-form-item label="显卡设备号" prop="gpuid">
                <el-input v-model="parametersForm.gpuid"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('parametersForm')">下一步</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>


<script>
    import {AXIOS} from '../http-common'

    export default {
        data() {
            return {
                parametersForm: {
                    net: '',
                    epochs: '',
                    learningrate: '',
                    batch: '',
                    gpuid: '',
                },
                rules: {
                    net: [
                        {required: true, message: '请选择网络', trigger: 'blur'}
                    ],
                    epochs: [
                        {required: true, message: '请填写训练轮数', trigger: 'blur'}
                    ],
                    learningrate: [
                        {required: true, message: '请填写学习率', trigger: 'blur'}
                    ],
                    batch: [
                        {required: true, message: '请填写批大小', trigger: 'blur'}
                    ],
                    gpuid: [
                        {required: true, message: '请填写显卡设备号', trigger: 'blur'}
                    ]
                },
                netoptions: []
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$router.push({path: '/'});
                    } else {
                        return false;
                    }
                });
            }
        }, mounted() {
            const _this = this;
            AXIOS.get(`/nets`)
                .then(response => {
                        _this.netoptions = response.data;
                    }
                )
                .catch(e => {
                    console.error(e);
                })
        }
    }
</script>


<style lang="scss">
    @import "../../assets/style/main.scss";
</style>
