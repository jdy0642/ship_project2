<template>
	<naver-maps :height="400" :width="600" :mapOptions="mapOptions" @load="onLoad">
		<naver-marker :lat="37" :lng="127" @click="onMarkerClicked" @load="onMarkerLoaded"></naver-marker>
		<naver-info-window @load="onWindowLoad" :isOpen="info" :marker="marker"><h1>Hello, World!</h1></naver-info-window>
		<naver-circle :lat="37" :lng="127" :radius="1000" :moreOptions="{}"></naver-circle>
  </naver-maps>
</template>
<script>
export default {
	data(){
		return {
			mapOptions: {
				useStyleMap: true,
				zoom: 12,
				lat: 33.450701,
				lng: 126.570667,
				zoomControl: true,
				zoomControlOptions: {
					position: String
				}
			},
			info: false,
      marker: null,
		}
	},
	methods:{
		onLoad(vue) {
			this.axi = vue
		},
		onWindowLoad(vue) {
			this.axi = vue
		},
		onMarkerClicked() {
			this.info = !this.info;
		},
		onMarkerLoaded(vue) {
			this.marker = vue.marker;
		},
	}
}
		
</script>
<style scoped>
</style>