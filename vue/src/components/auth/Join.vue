<template>
<div id="app">
  <v-btn color="indigo darken-1" dark fixed center @click="dialog = !dialog" style="font-size:15px"> join </v-btn>
    <v-dialog v-model="dialog" width="600px"  >
      <v-card>
        <v-card-title class="red darken-2" style="font-color:white" > CREATE ACCOUNT </v-card-title>
  <v-container>   
    <v-layout justify-center >
      <v-flex >
        
          <v-form ref="form">
            <v-container >
              <v-layout wrap justify-center>

                <v-flex   md8 style="padding:0px;">
                  <v-text-field style="margin:0px;" v-validate="'required|max:10'"  required="required"
                  center v-model="userid" label="ID" :rules="idRules"></v-text-field>
                </v-flex>

                <v-flex xs8 md8 style="padding:0px;">
                  <v-text-field  style="margin:0px;" label="PASSWORD" v-model="passwd"
                    :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'" required
                    :type="show1 ? 'text' : 'password'" @click:append="show1 = !show1"></v-text-field>
                </v-flex>

                <v-flex  md8 style="padding:0px;">
                  <v-text-field v-model="name" style="margin:0px;" required label="NAME" class="purple-input"></v-text-field>
                </v-flex>
                
                <v-flex xs8 md8 style="padding:0px;">
                    <v-text-field v-model="tel" style="margin:0px;" required label="TEL"></v-text-field>
                </v-flex>

                <v-flex  md8 style="padding:0px;">
                  <v-text-field style="margin:0px;" v-validate="'required|max:10'"  required
                  center v-model="email" label="EMAIL" :rules="emailRules"></v-text-field>
                </v-flex>

                <v-flex xs8 md8 style="padding:0px;">
                    <v-text-field v-model="job" style="margin:0px;" required label="JOB"></v-text-field>
                </v-flex>

                <v-flex xs8 md8>
                  <v-select v-model="male" :items="['남', '여']" label="GENDER" required></v-select>
                </v-flex>

                <!-- <v-flex xs8 md8 style="padding:0px;" >
                  <v-menu ref="startMenu" :close-on-content-click="false" offset-y
                    :return-value.sync="trip.start" min-width="290px">
                    <template v-slot:activator="{ on }">
                      <v-text-field v-model="trip.start" label="BIRTHDAY" 
                      readonly v-on="on"></v-text-field>
                    </template>
                    <v-date-picker v-model="date" no-title scrollable>
                      <v-spacer></v-spacer>
                      
                      <v-btn text color="primary" @click="$refs.startMenu.save(date)">
                        OK</v-btn>
                      <v-btn text color="error" @click="$refs.startMenu.isActive = false">
                        Cancel</v-btn>
                    </v-date-picker>
                  </v-menu>
                </v-flex> -->

               <v-flex xs8 md8 style="padding:0px;">
                    <v-text-field v-model="age" style="margin:0px;" required label="AGE"></v-text-field>
                </v-flex>
                <!-- <v-flex xs8 md8 style="padding:0px;">
                    <v-text-field v-model="interest" style="margin:0px;" required label="INTEREST"></v-text-field>
                </v-flex> -->
                <v-flex xs8 md8> 
                  <v-autocomplete v-model="interest" label="INTEREST" :items="['FUTSAL', 'BASKETBALL', 'LOL', 
                  'FIFA', 'STARCRAFT', 'X']" required></v-autocomplete>
                </v-flex>

                <v-flex xs8 md8> 
                  <v-checkbox v-model="checkbox" :rules="[v => !!v || 'You must agree to continue!']"
                            label="Do you agree?" required></v-checkbox>
                </v-flex>

              </v-layout>
            </v-container>
          </v-form>
        
        <v-card-actions >
          <v-spacer></v-spacer>
          <!-- <v-btn text color="success" @click="$refs.form.validate()"> validate</v-btn> -->
          <v-btn text color="warning" @click="$refs.form.reset()"> Reset</v-btn>
          <v-btn text color="primary" @click="join();$refs.form.reset()">Submit</v-btn>
          <v-btn text color="error" @click="dialog = false">Cancel</v-btn>
        </v-card-actions>

      </v-flex>
    </v-layout>
  </v-container>
    </v-card>
  </v-dialog>
</div>
</template>
<script>
import axios from 'axios'
export default {
    name: 'join',
    data () {
      return {
        context : 'http://localhost:8080',
        dialog:false,
        show1: false,
        checkbox:false,
        idRules: [
          v => !!v || '아이디를 입력해주세요',
          v => v.length <= 10 || '아이디는 10자를 넘을 수 없습니다',
        ],
        emailRules: [
          v => !!v || '이메일을 입력해주세요',
          v => /.+@.+/.test(v) || '유효하지 않은 이메일 형태입니다',
        ],
        // date: null,
      //   trip: {
      //     name: '',
      //     location: null,
      //     start: null,
      //     end: null,
      // },
        select: null,
        userid:'',
        passwd:'',
        name: '',
        tel:'',
        email:'',
        job:'',
        male:'',
        age:'',
        interest:''
        }
      },
      methods:{
      join(){
        alert('조인 진입')
        alert(this.interest)
        let url = `${this.context}/join`
        let data =  {
          userid : this.userid,
          passwd : this.passwd,
          name: this.name,
          tel:this.tel,
          email:this.email,
          job:this.job,
          male:this.male=="남" ? true :false,
          age:this.age,
          interest:this.interest
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
                this.dialog= false
                this.$router.push({path:'/'})

            }else{
                alert(`조인 실패`)
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
@import 'https://fonts.googleapis.com/css?family=Montserrat|Open+Sans';
.theme--light.v-card{
  color:white;
}
</style>