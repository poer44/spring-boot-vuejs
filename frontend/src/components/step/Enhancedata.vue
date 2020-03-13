<template>
    <div class="maindiv">
        <h2>数据增强策略</h2>
        <hr/>
        <el-form :model="enhanceForm" ref="enhanceForm" label-width="100px" class="form">
            <el-form-item label="增强策略" prop="type">
                <el-row>
                    <el-col :span="8">
                        <div class="selector" @click="enhanceForm.type=1"
                             :style="enhanceForm.type===1?'border: 2px #439fe6 solid;':''">
                            <img src="../../assets/icon/disabled.png" alt="" class="icon"/>
                            <p>不做增强</p>
                            <img src="../../assets/icon/checker.png" alt="" class="checker"
                                 v-if="enhanceForm.type===1"/>
                        </div>
                    </el-col>
                    <el-col :span="8">
                        <div class="selector" @click="enhanceForm.type=2"
                             :style="enhanceForm.type===2?'border: 2px #439fe6 solid;':''">
                            <img src="../../assets/icon/auto.png" alt="" class="icon"/>
                            <p>自动增强</p>
                            <img src="../../assets/icon/checker.png" alt="" class="checker"
                                 v-if="enhanceForm.type===2"/>
                        </div>
                    </el-col>
                    <el-col :span="8">
                        <div class="selector" @click="enhanceForm.type=3"
                             :style="enhanceForm.type===3?'border: 2px #439fe6 solid;':''">
                            <img src="../../assets/icon/hand.png" alt="" class="icon"/>
                            <p>手动增强</p>
                            <img src="../../assets/icon/checker.png" alt="" class="checker"
                                 v-if="enhanceForm.type===3"/>
                        </div>
                    </el-col>
                    <el-alert
                            :closable="false"
                            type="info"
                            :title="desc"
                            show-icon>
                    </el-alert>
                </el-row>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('enhanceForm')">下一步</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                enhanceForm: {
                    type: 0
                },
                desc: ''
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$router.push({path: '/parameters'});
                    } else {
                        return false;
                    }
                });
            }
        }, watch: {
            'enhanceForm.type'(val) {
                if (val === 1) {
                    this.desc = '系统将不对数据进行增强';
                } else if (val === 2) {
                    this.desc = '系统将对数据进行自动增强';
                }
            }
        }, mounted() {
            this.enhanceForm.type = 1;
        }
    }
</script>

<style lang="scss">
    @import "../../assets/style/main.scss";
</style>
