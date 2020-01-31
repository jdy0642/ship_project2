import Vue from 'vue'
import Vuex from 'vuex'
// import axios from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {
    futsal: {
      context: 'http://15.165.26.82',
      header: {
        'authorization': 'JWT fefege..',
        'Accept' : 'application/json',
        'Content-Type': 'application/json',
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36",
        "Access-Control-Allow-Origin": '*'
      },
      matchList:{},
      selectMatch:{},
      fnc: {
        timeToDate(param){
          const time = new Date(param)
          return `${(time.getMonth()+1)}월 ${time.getDate()}일 ${time.getHours()}시`
        },
        timeToDateWeek(param){
          const time = new Date(param)
          return `${time.getFullYear()}년 ${time.getMonth()+1}월 ${time.getDate()}일 
          ${["일","월","화","수","목","금","토"][time.getDay()]}요일 ${time.getHours()}:00`
        },
        linkCopy(val){
          alert(`${val} 복사`)
          let dummy = document.createElement("textarea");
          document.body.appendChild(dummy);
          dummy.value = val;
          dummy.select();
          document.execCommand("copy");
          document.body.removeChild(dummy); 
        },
        utc(time){
          return (parseInt(time/3600/1000/24)*24 + (new Date(time).getHours() >= 9 ? -9 : 15))*3600*1000
        },
        matchFilter(time,stadiumName,table){
          return (stadiumName === '' ? table : table
            .filter(i=> i.stadiumname.match(stadiumName) || i.stadiumaddr.match(stadiumName)))
            .filter(i => time <= i.time && i.time < this.utc(time+24*3600*1000))
            .sort((a,b) => a.time > b.time ? 1 : (a.time < b.time ? -1 : 0))
        }
      }
    },
    person:{},
    authCheck : false,   
  }
})