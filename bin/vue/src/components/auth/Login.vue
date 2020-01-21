<template>
<div id="app" >
  <v-btn color="indigo darken-1" dark fixed center @click="dialog = !dialog"  style="font-size:15px" > login </v-btn>
    <v-dialog v-model="dialog" width="400px" >
      <v-card >
        <v-card-title class="indigo " font-color="white" > LOGIN </v-card-title>
        <v-container fluid  >
          <v-layout wrap  >
            <v-flex xs8  >
              <v-text-field center prepend-icon="people" v-model="userid" label="ID" required></v-text-field>
              <v-text-field prepend-icon="lock" label="PASSWORD" type="password" v-model="passwd"></v-text-field>
              <v-checkbox v-model="checkbox" label="로그인 유지" ></v-checkbox>
              <v-btn v-for="icon of icons" :key="icon" class="mx-4 white--text" icon @click="socialgo()">
                <v-icon size="24px">{{ icon }}</v-icon>
              </v-btn>
            </v-flex>
            
          </v-layout>
        </v-container>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn text color="primary" @click="login()">LOGIN</v-btn>
        <v-btn text color="red" @click="dialog = false">CANCEL</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</div>

</template>
<script>
import axios from 'axios'
import {store} from '../../store'
export default {
  components:{
  },
   data(){
      return {
        checkbox: false,
        dialog: false,
        context : 'http://localhost:8080',
        result : '',
        userid : '',
        passwd : '',
        person : '',
        state : store.state,
        icons: [
                'mdi-facebook-box',
                'mdi-google-plus',
                'mdi-instagram',
        ]
      }
    },
   methods:{
      login(){
        let url = `${this.context}/login`
        let data =  {
         userid : this.userid,
         passwd : this.passwd
        }
        let headers= {
              'authorization': 'JWT fefege..',
              'Accept' : 'application/json',
              'Content-Type': 'application/json'
        }
      axios
      .post(url, data, headers)
      .then(res=>{
            if(res.data.result === "SUCCESS"){
                store.state.person = res.data.person
                if(this.state.person.role != 'student'){
                    this.state.authCheck = true
                    this.$router.push({path:'/'})
                }else{
                    this.state.authCheck = false
                }
                this.dialog=false
                this.$router.push({path:'/'})
            }else{
                alert(`로그인 실패`)
                this.$router.go({path: '/login'})
            }
         this.result = res.data
      })
      .catch(()=>{
         alert('axios fail')
        })
      }
   }
}
</script>
<style scoped>
.layout {
  justify-content:center;
}
.theme--light.v-card{
  color:white;
}

</style>