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
	<v-btn @click="search">dd</v-btn>
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
			mapp: ''
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
		}
    }
}
</script>
<style scoped>
</style>