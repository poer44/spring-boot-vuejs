<template>
    <div id="app">
        <el-menu
                class="el-menu-demo"
                mode="horizontal"
                background-color="#000"
                text-color="#fff"
                active-text-color="#ffd04b">
            <div class="logodiv">
                <img src="./assets/pico.png" alt=""/>
                <span>meiyapico</span>
            </div>
        </el-menu>
        <el-container>
            <el-menu
                    default-active="2"
                    class="left"
                    @open="handleOpen"
                    @close="handleClose"
                    background-color="#f2f2f2"
                    text-color="#000"
                    active-text-color="#128FDC" router>
                <div class="modeldiv">
                    <img src="./assets/model.png" alt=""/>
                    <p>模型训练</p>
                </div>
                <el-menu-item index="/">
                    <i class="el-icon-menu"></i>
                    <span slot="title">总览</span>
                </el-menu-item>
                <el-submenu index="/callservice">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>训练</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="/bootstrap">选项1</el-menu-item>
                        <el-menu-item index="/user">选项2</el-menu-item>
                        <el-menu-item index="/callservice">选项3</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>

            </el-menu>

            <div id="nav">
                <router-view></router-view>
            </div>

        </el-container>

    </div>
</template>

<script>

    export default {
        name: 'app',
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>

<style lang="scss">
    @import "./assets/style/app.scss";

</style>
