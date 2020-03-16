<template>
    <div class="maindiv">
        <h2>创建任务</h2>
        <hr/>
        <el-form :model="missionForm" :rules="rules" ref="missionForm" label-width="100px" class="form">
            <el-form-item label="任务名称" prop="name">
                <el-input v-model="missionForm.name"></el-input>
            </el-form-item>
            <el-form-item label="任务描述" prop="desc">
                <el-input type="textarea" v-model="missionForm.desc" :autosize="{ minRows: 4, maxRows: 8}"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('missionForm')">下一步</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import {AXIOS} from '../http-common'
    export default {
        data() {
            return {
                missionForm: {
                    name: '',
                    desc: '',
                    pid: ''
                },
                rules: {
                    name: [
                        {required: true, message: '请输入任务名称', trigger: 'blur'}
                    ],
                    desc: [
                        {required: true, message: '请填写任务描述', trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if(this.$route.params.pid){
                            this.$store.commit('setpid',this.$route.params.pid);
                        }
                        this.missionForm.pid=this.$store.state.pid;
                        //存储
                        AXIOS.post(`/mission/add`,
                            this.missionForm
                        )
                            .then(response => {
                                this.$store.commit('setmid', response.data);
                                this.$notify({
                                    title: '新任务创建成功！',
                                    message: '请继续接下来的步骤！',
                                    type: 'success'
                                });
                            })
                            .catch(e => {
                                console.error(e);
                            })
                        this.$router.push({path: '/addData'});
                    } else {
                        return false;
                    }
                });
            }
        }
    }
</script>


<style lang="scss">
    @import "../../assets/style/main.scss";
</style>
