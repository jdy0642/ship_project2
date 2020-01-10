<template>
<v-card :style="pageNation" justify="center" elevation="20">
  <div style="margin: auto;" v-for="(time,index) in timeArray(now,selectIndex)" :key="index">
    <v-btn max-width="120" max-height="80" min-height="50" min-width="70"  
      rounded :color="selected(time,selectTime)" @click="tableChange(index,time)">
      {{timeToDateAndWeek(time)}}
    </v-btn>
    <!-- boot strap
    <button @click="tableChange(index,time)" :class="selected(time,selectTime)">{{timeToDateAndWeek(time)}}</button> -->    
  </div>
</v-card>  
</template>
<script>
export default {
  data () {
    return {
      now : Date.now(),
      selectIndex : 0,
      selectTime : Date.now(),
      blockSize : 8
    }
  },
  computed:{
    pageNation(){
      return `display: grid; grid-template-columns: repeat(${this.blockSize}, ${100/this.blockSize}%); height: 100%;`
    }
  },
  methods: {
    timeArray(now,select){
      const start = (select > 14-this.blockSize ? 14-this.blockSize : 
        (select==0 ? select : select - 1))
      const utc = (parseInt(now/3600/1000/24)*24 +
        (new Date(now).getHours() >= 9 ? -9 : 15) )*3600*1000
      return Array.from({length : this.blockSize},
      (_,k) => (start == 0 && k == 0 ? now : utc + (start+k)*24*1000*3600) )
    },
    timeToDateAndWeek(time){
      const temp = new Date(time)
      const date = temp.getDate()
      return `${(date==1 ? temp.getMonth()+1: '')} ${date} ${["일","월","화","수","목","금","토"][temp.getDay()]}`
    },
    tableChange(index,time){
      this.selectTime = time
      this.selectIndex = index
      this.$emit("send",time)
    },
    selected(time,selectTime){
      //return "vspButton " + (time == selectTime ? "selected" : "") bootstrap
      return (time == selectTime ? "#319bde" : "#a5cae8")
    }
  }
}
</script>
<style scoped>
/* .vspButton {
  max-width: 100px;
  min-width: 80px;
  height: 50px;
  padding: 2px 7px;
  font-size: 12px;
  display: inline-block;
  margin-bottom: 0;
  font-weight: 400;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
  touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  background-image: none;
  border: 1px solid;
  border-radius: 4px;
  background-color: transparent;
}*/
.selected{
  background-color: #31b0d5;
}
</style>