<template>
    <div class="maindiv">
        <h2>日志查看</h2>
        <hr/>
        <el-row>
            <el-col :span="3" class="min-div">
                <p class="weight_p">准确率accuracy</p>
                <el-progress type="circle" :percentage="accuracy"></el-progress>
            </el-col>
            <el-col :span="3" class="min-div">
                <p class="weight_p">损失率loss</p>
                <el-progress type="circle" :percentage="loss"></el-progress>
            </el-col>
            <el-col :span="3" class="min-div">
                <p class="weight_p">进度process</p>
                <el-progress type="circle" :percentage="process"></el-progress>
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
                accuracy: 0,
                loss: 0,
                process: 0
            }
        },
        mounted() {
            const _this = this;
            // 打开一个WebSocket
            var ws = new WebSocket('ws://' + window.document.domain + ':8044/logsocket/' + this.$route.params.mid);
            // 响应onmessage事件:
            ws.onmessage = function (msg) {
                var jsonstr = JSON.parse(msg.data);
                if(jsonstr.cmd) {
                    _this.text += jsonstr.cmd + "<br />";
                }
                if(jsonstr.process) {
                    _this.accuracy = Number.parseInt(Number(jsonstr.accuracy) * 100);
                    _this.loss = Number.parseInt(Number(jsonstr.loss) * 100);
                    _this.process = Number.parseInt(Number(jsonstr.process) * 100);
                }
                const div = document.getElementsByClassName('logdiv')[0];
                if (div.scrollHeight)
                    div.scrollTop = div.scrollHeight;
            };

        }
    }
</script>

<style lang="scss">
    @import "../../assets/style/main.scss";
</style>
