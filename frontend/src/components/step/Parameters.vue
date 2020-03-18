<template>
    <div class="maindiv">
        <h2>参数设置</h2>
        <el-button type="primary" @click="gonni()">超参优化（NNI）</el-button>
        <hr/>
        <el-form :model="parametersForm" :rules="rules" ref="parametersForm" label-width="150px" class="form">
            <el-form-item label="网络选择" prop="net">
                <el-select v-model="parametersForm.nid" placeholder="请选择网络">
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
                <el-button type="success" @click="submitForm('parametersForm')">开始训练</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>


<script>
    import {AXIOS} from '../http-common'

    export default {
        data() {
            //自定义校验规则-是否数字
            var validatenum = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('该选项不能为空'));
                }
                if (Number.isNaN(Number(value))) {
                    callback(new Error('请输入数字值'));
                } else {
                    callback();
                }

            };
            return {
                parametersForm: {
                    nid: '',
                    epochs: '',
                    learningrate: '',
                    batch: '',
                    gpuid: '',
                },
                rules: {
                    nid: [
                        {required: true, message: '请选择网络', trigger: 'blur'}
                    ],
                    epochs: [
                        {validator: validatenum, required: true, trigger: 'blur'}
                    ],
                    learningrate: [
                        {validator: validatenum, required: true, trigger: 'blur'}
                    ],
                    batch: [
                        {validator: validatenum, required: true, trigger: 'blur'}
                    ],
                    gpuid: [
                        {validator: validatenum, required: true, trigger: 'blur'}
                    ]
                },
                netoptions: []
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //存储
                        this.$store.commit('setmretail', this.parametersForm);
                        //提交到数据库，开始训练
                        AXIOS.post(`/mretail/add`,
                            this.$store.state.mission_details)
                            .then(response => {
                                this.$notify({
                                    title: '模型参数设置成功！',
                                    message: '即将开始训练。可在 首页-查看日志 查看训练进度',
                                    type: 'success'
                                });
                            })
                            .catch(e => {
                                console.error(e);
                            })
                        AXIOS.get(`/start_train`, {
                                params: {
                                    mid: this.$store.state.mid
                                }
                            }
                        )
                            .then(response => {

                            })
                            .catch(e => {
                                console.error(e);
                            })
                        this.$router.push({path: '/'});
                    } else {
                        return false;
                    }
                });
            },
            gonni() {
                window.open("http://" + window.document.domain + ":8045");
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
