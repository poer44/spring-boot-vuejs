<template>
  <div class="maindiv">
    <h2>数据集上传</h2>
    <hr />
    <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple>
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>

  </div>
</template>

<script>
  import {AXIOS} from './http-common'

  export default {
    name: 'user',

    data () {
      return {
        response: [],
        errors: [],
        user: {
          lastName: '',
          firstName: '',
          id: 0
        },
        showResponse: false,
        retrievedUser: {},
        showRetrievedUser: false
      }
    },
    methods: {
      // Fetches posts when the component is created.
      createUser () {
        var params = new URLSearchParams()
        params.append('firstName', this.user.firstName)
        params.append('lastName', this.user.lastName)

        AXIOS.post(`/user`, params)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response = response.data
            this.user.id = response.data
            console.log(response.data)
            this.showResponse = true
          })
          .catch(e => {
            console.error(e);
          })
      },
      retrieveUser () {
        AXIOS.get(`/user/` + this.user.id)
          .then(response => {
            // JSON responses are automatically parsed.
            this.retrievedUser = response.data
            console.log(response.data)
            this.showRetrievedUser = true
          })
          .catch(e => {
            console.error(e);
          })
      }
    }
  }

</script>


<style  lang="scss">
  @import "../assets/style/main.scss";
</style>

