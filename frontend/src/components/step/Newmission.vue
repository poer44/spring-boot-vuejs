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
    export default {
        data() {
            return {
                missionForm: {
                    name: '',
                    desc: ''
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
                        this.$router.push({path: '/addData'});
                    } else {
                        return false;
                    }
                });
            }
        }
    }
</script>


<style  lang="scss">
    @import "../../assets/style/main.scss";
</style>
