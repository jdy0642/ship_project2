<template>
<div>
	<vue-daum-map
		:appKey="appKey"
		:center.sync="center"
		:level.sync="level"
		:mapTypeId="mapTypeId"
		:libraries="libraries"
		:draggable="true"
		:scrollwheel="true"
		@load="onLoad"
		@click="moveCenter"
		style="width:500px;height:400px;"/>
	<v-text-field v-model="page" @keyup.enter="crawl()"/>
	<a href="http://192.168.5.54:3000">채</a>
</div>
	<!--  @center_changed=""
		@zoom_start=""
		@zoom_changed=""
		@bounds_changed=""
		@dblclick=""
		@rightclick=""
		@mousemove=""
		@dragstart=""
		@drag=""
		@dragend=""
		@idle=""
		@tilesloaded=""
		@maptypeid_changed="" 
	-->
</template>
<script>
import VueDaumMap from 'vue-daum-map'
import axios from 'axios'
import {store} from '@/store'
export default{
	components:{VueDaumMap},
	data(){
		return {
			appKey: '28d9076d78b899a3f85bb1c12320b0c3', // 테스트용 appkey
			center: {lat:33.450701, lng:126.570667}, // 지도의 중심 좌표
			level: 3, // 지도의 레벨(확대, 축소 정도),
			mapTypeId: VueDaumMap.MapTypeId.NORMAL, // 맵 타입
			libraries: ['services','clusterer'], // 추가로 불러올 라이브러리
			map: '', // 지도 객체. 지도가 로드되면 할당됨.
			page: '',
			getStadiumData: '',
			table: '',
			axi:''
		}
	},
	computed:{
		moveCenter(){
			return {lat:33.450701, lng:126.570667}
		}
	},
	methods: {
        // 지도가 로드 완료되면 load 이벤트 발생
        onLoad (map) {
            this.map = map
		},
		search(){
			let yy = this.map.services.Places()
			yy.keywordSearch('풋살',(result,status)=>{
				this.mapp = result
				alert(status === this.map.services.Status.Ok ? 'ok':'fail') 
			})
		},
		test(){
			axios.get(`https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/
				yamine?api_key=RGAPI-5bfa2b4b-0bbf-4861-a97a-dfd697046e26`)
			.then(res=>{
				//Access-Control-Allow-Origin: '*'
				this.lol = res.data
			})
			.catch(()=>{
			})
		},
		//https://developers.kakao.com/docs/restapi/local#%ED%82%A4%EC%9B%8C%EB%93%9C-%EA%B2%80%EC%83%89
		crawl(){
			axios({url: 'http://dapi.kakao.com/v2/local/search/keyword.json',
				headers:{
					Authorization: 'KakaoAK 28d9076d78b899a3f85bb1c12320b0c3'
				},
				method: 'GET',
				params: {
					query: '풋살장',
					page: this.page
				}
			}).then(res=>{
				this.getStadiumData = res.data.documents
				alert(`${this.page}페이지  ${res.data.documents.length}개`)
				const stadiumAddr = i => this.getStadiumData[i].address_name
				const stadiumTel = i => this.getStadiumData[i].phone
				const stadiumName = i => this.getStadiumData[i].place_name
				const rannum = () => ['4','5','6'][parseInt(Math.random()*3)]
				const rangender = () => ['female','male'][parseInt(Math.random()*2)]
				const ranrating = () => parseInt(Math.random()*3+1)
				const rantime = x => x + Math.random()*1000*3600*24*13
				const ranfacility = () => 'size0,shower0,park0,shoes0,wear0'
				const remain = () => parseInt(Math.random()*12)
				let table = Array.from({length : 15},(_,i) => ({
					time: rantime(Date.now()), stadiumname: stadiumName(i),
					stadiumaddr: stadiumAddr(i), stadiumtel: stadiumTel(i),
					num : rannum(), gender: rangender(),difficulty: ranrating(),
					shoes: 'shoes0', stadiumfacility: ranfacility(),
					stadiumimg: '1,2,3', remain: remain(), adminname: '펭수'
					}))
				this.table = table
				axios.post(`${store.state.context}/futsal/insertdummy`,table,store.state.header)
				.catch(e => {
					alert(e)
				})
			}).catch(e=>{
				this.getStadiumData = e
			})
		}
  }
}
</script>
<style scoped>
</style>