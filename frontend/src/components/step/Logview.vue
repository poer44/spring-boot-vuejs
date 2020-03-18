<template>
    <div class="maindiv">
        <h2>日志查看</h2>
        <hr/>
        <el-row>
            <el-col :span="3" class="min-div">
                <p class="weight_p">准确率</p>
                <el-progress type="circle" :percentage="93"></el-progress>
            </el-col>
            <el-col :span="3" class="min-div">
                <p class="weight_p">回归率</p>
                <el-progress type="circle" :percentage="86"></el-progress>
            </el-col>
        </el-row>
        <p class="weight_p">日志</p>
        <div class="logdiv">
            <span v-html="text"></span>
        </div>
    </div>
</template>

<script>
    // // 引入 ECharts 主模块
    // var echarts = require('echarts/lib/echarts');
    // // 引入柱状图
    // require('echarts/lib/chart/pie');
    // // 引入提示框和标题组件
    // require('echarts/lib/component/tooltip');
    // require('echarts/lib/component/title');
    export default {
        data() {
            return {
                text: '',
            }
        },
        mounted() {
            const _this = this;
            // 打开一个WebSocket
            var ws = new WebSocket('ws://' + window.document.domain + ':8044/logsocket/' + this.$route.params.mid);
            // 响应onmessage事件:
            ws.onmessage = function (msg) {
                _this.text += msg.data;
                const div = document.getElementsByClassName('logdiv')[0];
                div.scrollTop = div.scrollHeight;
            };

        }
    }
</script>

<style lang="scss">
    @import "../../assets/style/main.scss";
</style>
