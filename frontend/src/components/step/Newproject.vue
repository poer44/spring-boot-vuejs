<template>
    <div class="maindiv">
        <h2>创建项目</h2>
        <hr/>
        <el-form :model="projectForm" :rules="rules" ref="projectForm" label-width="100px" class="form">
            <el-form-item label="项目名称" prop="name">
                <el-input v-model="projectForm.name"></el-input>
            </el-form-item>
            <el-form-item label="项目描述" prop="desc">
                <el-input type="textarea" v-model="projectForm.desc" :autosize="{ minRows: 4, maxRows: 8}"></el-input>
            </el-form-item>
            <el-form-item label="项目类型" prop="type">
                <el-row>
                    <el-col :span="12">
                        <div class="selector" @click="projectForm.type=1"
                             :style="projectForm.type===1?'border: 2px #439fe6 solid;':''">
                            <img src="../../assets/icon/img.png" alt="" class="icon"/>
                            <p>图像分类</p>
                            <img src="../../assets/icon/checker.png" alt="" class="checker" v-if="projectForm.type===1"/>
                        </div>
                    </el-col>
                    <el-col :span="12">
                        <div class="selector" @click="projectForm.type=2"
                             :style="projectForm.type===2?'border: 2px #439fe6 solid;':''">
                            <img src="../../assets/icon/point.png" alt="" class="icon"/>
                            <p>目标检测</p>
                            <img src="../../assets/icon/checker.png" alt="" class="checker" v-if="projectForm.type===2"/>
                        </div>
                    </el-col>
                </el-row>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('projectForm')">下一步</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>


<script>
    export default {
        data() {
            return {
                projectForm: {
                    name: '',
                    desc: '',
                    type: 1
                },
                rules: {
                    name: [
                        {required: true, message: '请输入项目名称', trigger: 'blur'}
                    ],
                    desc: [
                        {required: true, message: '请填写项目描述', trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        }
    }
</script>


<style lang="scss">
    .maindiv {
        padding: 40px;
        width: 100%;

        .form {
            width: 700px;
        }
    }

    .selector {
        width: 150px;
        height: 150px;
        border: 2px #dcdfe6 solid;
        border-radius: 5px;
        text-align: center;
        position: relative;

        .icon {
            padding: 10px 10px 0 10px;
            width: 110px;
            height: 90px;
        }

        .checker {
            position: absolute;
            top: 0;
            right: 0;
            width: 150px;
            height: 150px;
        }

        p {
            font-size: 20px;
            padding-top: 0;
        }
    }
</style>
