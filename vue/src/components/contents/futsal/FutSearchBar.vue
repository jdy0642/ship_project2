<template>
<div>
	<div class="row" ma-auto>
		<div style="width: 100%">
			<div style="margin: auto; width: 40%;" class="input-group">
				<input v-model="stadiumName" @keyup.enter="submit" type="text" class="form-control" placeholder="Search for...">
				<span class="input-group-btn">
					<button @click="submit" class="btn btn-default" type="button">Go!</button>
				</span>
				<v-btn>즐겨찾기</v-btn>
				<v-btn @click="gps">현재위치</v-btn>
			</div>
			{{weather(stadiumName)}}
		</div>
	</div>
</div>
</template>
<script>
import axios from "axios"
//import { store } from '@/store'
export default{
	created(){
	},
	data(){
		return {
			stadiumName : '',
			location: '',
		}
	},
	computed: {
		
	},
	methods:{
		submit(){
			this.$emit("sendStadiumName",this.stadiumName)
		},
		gps(){
			let location={}
			let send = (location) => {this.$emit("sendLocation",location)}
			navigator.geolocation.getCurrentPosition(async function(pos) {
				location.lat = pos.coords.latitude
				location.lng = pos.coords.longitude
				await send(location)
			})
			this.location = location
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
  }
}
</script>
<style scoped>
div{
	text-align: center
}
</style>