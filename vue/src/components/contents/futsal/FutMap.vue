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
  props:['propSearchWord'],
  data(){
    return {
		mapData:{
		appKey: '789b2dc91d9235fae744572478c25f39', // 테스트용 appkey
		center: {lat:33.450701, lng:126.570667}, // 지도의 중심 좌표
		level: 3, // 지도의 레벨(확대, 축소 정도),
		mapTypeId: VueDaumMap.MapTypeId.NORMAL, // 맵 타입
		libraries: ['services', 'clusterer', 'drawing'], // 추가로 불러올 라이브러리
		},
		markers: [],
		mapObject: null, // 지도 객체. 지도가 로드되면 할당됨.
		searchWord: this.propSearchWord
    }
  },
	watch: {
		propSearchWord: function (val){
			this.searchWord = val
			this.markerDel()
			this.marker()
		}
	},
  methods: {
    // 지도가 로드 완료되면 load 이벤트 발생
    onLoad(map) {
		// 지도의 현재 영역을 얻어옵니다
		let daummaps = window.daum.maps
		// 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
		map.addControl(new daummaps.ZoomControl()
			, daummaps.ControlPosition.TOPRIGHT); 
		this.mapObject = map
		this.marker()
    },
    marker(){
		let daummaps = window.daum.maps
		daummaps.services.Places()
		daummaps.services.keywordSearch(`${this.searchWord} 풋살 경기장`, this.placesSearchCB,null)
	},
	markerDel(){
		this.markers.map(i=>{
			i.setMap(null);
		})
		this.markers = []
	},
	placesSearchCB(data, status){
		let map = this.mapObject
		let daummaps = window.daum.maps
		if (status === daummaps.services.Status.OK) {
			let bounds = new daummaps.LatLngBounds();
			for (let i=0; i<data.length; i++) {
				this.displayMarker(data[i])
				bounds.extend(new daummaps.LatLng(data[i].y, data[i].x));
			}
			// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
			map.setBounds(bounds);
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
			this.$emit("send",place.place_name)
		})
	}
  }
}
</script>
<style scoped>
</style>