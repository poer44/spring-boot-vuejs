<template>
    <div class="maindiv">
        <h2>日志查看</h2>
        <hr/>
        <div class="logdiv">
        <span v-html="text"></span>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                text: '',
            }
        },
        mounted() {
            const _this=this;
            // 打开一个WebSocket
            var ws = new WebSocket('ws://'+window.document.domain+':8044/logsocket/'+ this.$route.params.mid);
            // 响应onmessage事件:
            ws.onmessage = function (msg) {
                _this.text += msg.data;
                const div = document.getElementsByClassName('logdiv')[0];
                div.scrollTop = div.scrollHeight;
            };
        }
    }
</script>

<style  lang="scss">
    @import "../../assets/style/main.scss";
</style>
