<template>
<div class="app">
    <Menu :theme="theme2" class="left">
        <Submenu name="1">
            <template slot="title">
                <Icon type="ios-paper"/>
                内容管理
            </template>
            <router-link to="/bootstrap"><MenuItem name="1-1">订单管理</MenuItem></router-link>
            <router-link to="/bootstrap"><MenuItem name="1-2">菜品管理</MenuItem></router-link>
        </Submenu>
        <Submenu name="3">
            <template slot="title">
                <Icon type="ios-stats"/>
                统计分析
            </template>
            <router-link to="/"><MenuItem name="3-1">今日送货单</MenuItem></router-link>
            <router-link to="/"><MenuItem name="3-2">菜品排行</MenuItem></router-link>
            <router-link to="/"><MenuItem name="3-3">销售统计</MenuItem></router-link>
        </Submenu>
        <Submenu name="2">
            <template slot="title">
                <Icon type="ios-people"/>
                系统管理
            </template>
            <router-link to="/bootstrap"><MenuItem name="2-1">管理员设置</MenuItem></router-link>
            <router-link to="/bootstrap"><MenuItem name="2-1">顾客信息</MenuItem></router-link>
        </Submenu>
    </Menu>
    <div class="right">
        <router-view class="view"></router-view>
    </div>
    <!--  <br>-->
    <!--  <div id="app">-->
    <!--    <div id="nav">-->
    <!--      <router-link to="/">Hello</router-link> |-->
    <!--      <router-link to="/callservice">Service</router-link> |-->
    <!--      <router-link to="/bootstrap">Bootstrap</router-link> |-->
    <!--      <router-link to="/user">User</router-link>-->
    <!--    </div>-->
    <!--    <router-view :hellomsg="msg"></router-view>-->
    <!--  </div>-->
</div>
</template>

<script>
    import Vue from 'vue';
    import iView from 'iview';
    import 'iview/dist/styles/iview.css';

    Vue.use(iView);
    export default {
        name: 'app',
        data() {
            return {
                theme2: 'light'
            }
        }
    }
</script>

<style lang="scss">
    .app {
        height:100%;
        width:100%;
        .left {
            height: 100%;
            float: left;
            width:240px;
        }

        .right {
            padding: 20px;
            box-sizing: border-box;
            overflow: hidden;
            width:calc(100% - 240px);
            .view{
                width:100%;
            }
        }

    }
</style>
