<template>
<div>
	<div class="row" ma-auto>
		<div style="width: 100%">
			<div style="margin: auto; width: 40%;" class="input-group">
				<input v-model="stadium" @keyup.enter="submit" type="text" class="form-control" placeholder="Search for...">
				<span class="input-group-btn">
					<button @click="submit" class="btn btn-default" type="button">Go!</button>
				</span>
			</div>
			{{weather(stadium)}}
		</div>
	</div>
</div>
</template>
<script>
import axios from "axios"
export default{
	data(){
		return {
			stadium : ''
		}
	},
	computed: {
		
	},
	methods:{
		submit(){
			this.$emit("send",this.stadium)
		},
		weather(stadium){
			let weather = ''
			
			axios
			.get(`http://api.openweathermap.org/data/2.5/weather?q=
			${stadium==='' ? 'SEOUL' : stadium}
			,KR&APPID=cd9a51369c3fc19f9fb85b2f2508b5d5`)
			.then(res=>{
				weather.city = res.data.name
				weather.temp = res.data.main.temp
				weather.img = res.data.weather[0].icon
				weather.imgUrl = `http://openweathermap.org/img/wn/${res.data.weather[0].icon}@2x.png`
				weather.wind = res.data.wind.speed
			})
			.catch(e=>{
				alert('axios fail'+e)
			})
			return weather
		}
      }
	
}
</script>
<style scoped>
div{
	text-align: center
}
</style>