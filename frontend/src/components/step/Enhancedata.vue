<template>
    <div class="maindiv">
        <h2>数据增强策略</h2>
        <hr/>
        <el-form :model="enhanceForm" ref="enhanceForm" label-width="100px" class="form" :rules="rules">
            <el-form-item label="增强策略" prop="etype">
                <el-row>
                    <el-col :span="8">
                        <div class="selector" @click="enhanceForm.etype=1"
                             :style="enhanceForm.etype===1?'border: 2px #439fe6 solid;':''">
                            <img src="../../assets/icon/disabled.png" alt="" class="icon"/>
                            <p>不做增强</p>
                            <img src="../../assets/icon/checker.png" alt="" class="checker"
                                 v-if="enhanceForm.etype===1"/>
                        </div>
                    </el-col>
                    <el-col :span="8">
                        <div class="selector" @click="enhanceForm.etype=2"
                             :style="enhanceForm.etype===2?'border: 2px #439fe6 solid;':''">
                            <img src="../../assets/icon/auto.png" alt="" class="icon"/>
                            <p>自动增强</p>
                            <img src="../../assets/icon/checker.png" alt="" class="checker"
                                 v-if="enhanceForm.etype===2"/>
                        </div>
                    </el-col>
                    <el-col :span="8">
                        <div class="selector" @click="enhanceForm.etype=3"
                             :style="enhanceForm.etype===3?'border: 2px #439fe6 solid;':''">
                            <img src="../../assets/icon/hand.png" alt="" class="icon"/>
                            <p>手动增强</p>
                            <img src="../../assets/icon/checker.png" alt="" class="checker"
                                 v-if="enhanceForm.etype===3"/>
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
            <el-form-item label="手动增强类型" prop="etypeCld" v-if="enhanceForm.etype===3">
                <el-checkbox-group v-model="enhanceForm.etypeCld">
                    <el-checkbox border label="1">去雾</el-checkbox>
                    <el-checkbox border label="2">对比度增强</el-checkbox>
                    <el-checkbox border label="3">清晰度增强</el-checkbox>
                </el-checkbox-group>
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
            //自定义校验规则
            var validatecls = (rule, value, callback) => {
                if (this.enhanceForm.etype === 3 && value.length === 0) {
                    callback(new Error('请选择手动增强类型'));
                } else {
                    callback();
                }
            };
            return {
                desc: '',
                enhanceForm: {
                    etype: 0,
                    etypeCld: []
                }, rules: {
                    etypeCld: [
                        {validator: validatecls, trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //存储
                        this.enhanceForm.etypeCld = JSON.stringify(this.enhanceForm.etypeCld);
                        this.$store.commit('setmretail', this.enhanceForm);
                        this.$router.push({path: '/parameters'});
                    } else {
                        return false;
                    }
                });
            }
        }, watch: {
            'enhanceForm.etype'(val) {
                if (val === 1) {
                    this.desc = '系统将不对数据进行增强';
                } else if (val === 2) {
                    this.desc = '系统将对数据进行自动增强';
                } else if (val === 3) {
                    this.desc = '请手动选择加强的方式';
                }
            }
        }, mounted() {
            this.enhanceForm.etype = 1;
        }
    }
</script>

<style lang="scss">
    @import "../../assets/style/main.scss";
</style>
