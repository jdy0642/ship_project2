<template>
<div>
	<div class="row" ma-auto>
		<div style="width: 100%">
			<div style="margin: auto; width: 40%;" class="input-group">
				<input v-model="stadiumName" @keyup.enter="submit" type="text" class="form-control" placeholder="Search for...">
				<span class="input-group-btn">
					<button @click="submit" class="btn btn-default" type="button">Go!</button>
				</span>
			</div>
			{{weather(stadiumName)}}
			<v-btn @click="crawling()">
				크롤링
			</v-btn>
		</div>
	</div>
</div>
</template>
<script>
import axios from "axios"
//import { store } from '@/store'
export default{
	data(){
		return {
			stadiumName : '',
			getdata: ''
		}
	},
	computed: {
		
	},
	methods:{
		submit(){
			this.$emit("send",this.stadiumName)
		},
		weather(stadiumName){
			let weather = ''			
			axios
			.get(`http://api.openweathermap.org/data/2.5/weather?q=
			${stadiumName==='' ? 'SEOUL' : stadiumName}
			,KR&APPID=cd9a51369c3fc19f9fb85b2f2508b5d5`)
			.then(res=>{
				weather.city = res.data.name
				weather.temp = res.data.main.temp
				weather.img = res.data.weather[0].icon
				weather.imgUrl = `http://openweathermap.org/img/wn/${res.data.weather[0].icon}@2x.png`
				weather.wind = res.data.wind.speed
			})
			.catch(()=>{
				//alert('axios fail'+e)
			})
			return weather
		},
		crawling(){
			/* let loc = '풋살 경기장'
			let page = 1
			let url = `https://dapi.kakao.com/v2/local/search/keyword.json`
			axios.post(url,{},{
			})
			.then(res => {
				this.getdata = res
			}).catch(e => {
				alert(`axios fail ${e}`)
				this.getdata = e
			}) */
			
		}
    }
}
</script>
<style scoped>
div{
	text-align: center
}
</style>