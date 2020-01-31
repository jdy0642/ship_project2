<template>
<div>
   <vue-daum-map
    :appKey="mapData.appKey"
    :center.sync="mapData.center"
    :level.sync="mapData.level"
    :mapTypeId="mapData.mapTypeId"
    :libraries="mapData.libraries"
    @load="onLoad"
	:style="`height: 100%; width: 100%;`">
	</vue-daum-map>
</div>
</template>
    /* @center_changed="onMapEvent('center_changed', $event)"
    @zoom_start="onMapEvent('zoom_start', $event)"
    @zoom_changed="onMapEvent('zoom_changed', $event)"
    @bounds_changed="onMapEvent('bounds_changed', $event)"
    @click="onMapEvent('click', $event)"
    @dblclick="onMapEvent('dblclick', $event)"
    @mousemove="onMapEvent('mousemove', $event)"
    @dragstart="onMapEvent('dragstart', $event)"
    @drag="onMapEvent('drag', $event)"
    @dragend="onMapEvent('dragend', $event)"
    @idle="onMapEvent('idle', $event)"
    @tilesloaded="onMapEvent('tilesloaded', $event)"
    @maptypeid_changed="onMapEvent('maptypeid_changed', $event)" */
<script>
import VueDaumMap from 'vue-daum-map'
export default {
  components:{VueDaumMap},
	props:['propSearchWord','propLocation'],
  data(){
    return {
		mapData:{
			appKey: '789b2dc91d9235fae744572478c25f39', // 테스트용 appkey
			center: {lat:37.5605672, lng:126.94334860559148},
			level: 3, // 지도의 레벨(확대, 축소 정도),
			mapTypeId: VueDaumMap.MapTypeId.NORMAL, // 맵 타입
			libraries: ['services', 'clusterer', 'drawing'], // 추가로 불러올 라이브러리
		},
		markers: [],
		mapObject: null, // 지도 객체. 지도가 로드되면 할당됨.
		searchWord: '',
		temp: '',
    }
  },
	watch: {
		propSearchWord: function(param){
			this.searchChanged(param)
		},
		propLocation: function(param){
			this.locationChanged(param)
		}
	},
  methods: {
		searchChanged(param){
			//alert(`${param}변경`)
			this.searchWord = param
			this.markerDel()
			this.marker()
		},
		locationChanged(param){
			//alert(`현재위치로 이동 ${param.lat} ${param.lng}`)
			this.mapObject.setLevel(3);
			this.mapObject.setCenter(new window.daum.maps.LatLng(param.lat, param.lng))
			this.markerDel()
			this.searchAddrFromCoords(param.lng,param.lat,(result,status) =>{
				if (status === window.daum.maps.services.Status.OK) {
					this.displayMarker({y: param.lat,x: param.lng,place_name: `현재위치 : ${result[0].address_name}`})
					this.searchWord = `${result[0].address_name} 풋살`
					this.marker()
				}
			})
		},
    // 지도가 로드 완료되면 load 이벤트 발생
    onLoad(map) {
		let daummaps = window.daum.maps
		map.addControl(new daummaps.ZoomControl()
			, daummaps.ControlPosition.TOPRIGHT);
		this.mapObject = map
		if(!this.propLocation){
			this.searchChanged(this.propSearchWord)
		} else{
			this.locationChanged(this.propLocation)
		}
		/* alert(`검색어:${this.propSearchWord} 현재위치:${this.propLocation.lat},${this.propLocation.lng}`) */
    },
    marker(){
			let daummaps = window.daum.maps
			if(this.searchWord!==''){
				daummaps.services.Places()
				daummaps.services.keywordSearch(`${this.searchWord}`, this.placesSearchCB,null)
			}
		},
		markerDel(){
			this.markers.map(i=>{
				i.setMap(null);
			})
			this.markers = []
		},
		searchAddrFromCoords(lng, lat, callback) {
			let geocoder = new window.daum.maps.services.Geocoder()
    // 좌표로 행정동 주소 정보를 요청합니다
			geocoder.coord2RegionCode(lng, lat, callback)
		},
		placesSearchCB(data, status){
			let daummaps = window.daum.maps
			if (status === daummaps.services.Status.OK) {
				let bounds = new daummaps.LatLngBounds();
				for (let i=0; i<data.length; i++) {
					this.displayMarker(data[i])
					bounds.extend(new daummaps.LatLng(data[i].y, data[i].x));
				}
				// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
				this.mapObject.setBounds(bounds);
			}
		},
		displayMarker(place){
			let map = this.mapObject
			let daummaps = window.daum.maps
			let infowindow = new daummaps.InfoWindow({zIndex:1});
			let marker = new daummaps.Marker({
				map: map,
				position: new daummaps.LatLng(place.y, place.x)
			})
			this.markers.push(marker);
			daummaps.event.addListener(marker, 'mouseover',() =>{
				infowindow.setContent(`<div style="padding:5px;font-size:12px;color:black;">${place.place_name}</div>`);
				infowindow.open(map, marker);
			})
			daummaps.event.addListener(marker, 'mouseout',() =>{
				infowindow.close()
			})
			daummaps.event.addListener(marker, 'click', () => {
				infowindow.close()
				this.searchWord = place.place_name
				this.$emit("sendStadiumName",place)
			})
		}
  }
}
</script>
<style scoped>
</style>