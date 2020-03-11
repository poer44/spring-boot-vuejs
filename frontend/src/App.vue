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
                    active-text-color="#ffd04b">
                <div class="modeldiv">
                    <img src="./assets/model.png" alt=""/>
                    <p>模型训练</p>
                </div>
                <el-menu-item index="2">
                    <i class="el-icon-menu"></i>
                    <span slot="title">总览</span>
                </el-menu-item>
                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>训练</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="1-1">选项1</el-menu-item>
                        <el-menu-item index="1-2">选项2</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>

            </el-menu>

            <div id="nav">
                <router-link to="/">Hello</router-link>
                |
                <router-link to="/callservice">Service</router-link>
                |
                <router-link to="/bootstrap">Bootstrap</router-link>
                |
                <router-link to="/user">User</router-link>
                <br>
                <router-view :hellomsg="msg"></router-view>
            </div>

        </el-container>

    </div>
</template>

<script>

    export default {
        name: 'app',
        data() {
            return {
                msg: 'Welcome to your Vue.js powered Spring Boot App'
            }
        }, methods: {
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
    .logodiv {
        img {
            width: 45px;
            height: 50px;
        }

        span {
            color: white;
        }

        margin-left: 20px;
    }

    .modeldiv {
        text-align: center;

        img {
            width: 150px;
            height: 150px;
        }

        p {
            color: black;
            font-size: 30px;
        }
    }

    .left {
        min-width: 200px;
        height: 100%;
    }

    html, body, #app, .el-container {
        height: 100%;
        overflow: hidden;
    }
</style>
