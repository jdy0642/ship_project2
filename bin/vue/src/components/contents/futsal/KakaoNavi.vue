<template>
	<div id="navi">
		<v-btn @click="naviLoad()"></v-btn>
		<div id="naviView" style="width:100%; height:400px;"></div>
	</div>
</template>
<script>
export default{
	name: 'navi',
	data(){
		return {
			name: "현대백화점 판교점",
			x: 127.11205203011632,
			y: 37.39279717586919,
			coordType: 'wgs84'
		}
	},
	created(){
		
	},
	mounted(){
		let container = document.getElementById('navi');
        let naviOptions = {
            /* center: new daum.maps.LatLng(위도, 경도),
			level: 4 //지도의 레벨(확대, 축소 정도) */
			
        };
        let navi = new daum.maps.Map(container, mapOptions)
	},
	methods:{
		naviLoad(){
			new Kakao.Navi.start(this.name,this.x,this.y,this.coordType)
		}
	}
}
</script>
<style scoped>
</style>