<template>
<div>
   <vue-daum-map
    :appKey="mapData.appKey"
    :center.sync="mapData.center"
    :level.sync="mapData.level"
    :mapTypeId="mapData.mapTypeId"
    :libraries="mapData.libraries"
    @load="onLoad"
    @click="marker()"
    style="width:100%;height:400px;">
  </vue-daum-map>
  <v-btn @click="test()">롤</v-btn>
  <v-btn @click="test2()">롤2</v-btn>
  <v-btn @click="test3()">롤3</v-btn>
  <div>
    <!-- <span>{{ $socket.connected ? 'Connected' : 'Disconnected' }}</span> -->
  </div>
  <v-btn @click="clickButton('하이')">socket</v-btn>
</div>
</template>
<script>
import axios from 'axios'
import {store} from '@/store'
import VueDaumMap from 'vue-daum-map'
export default {
  components:{VueDaumMap},
  created(){
    this.$socket.$subscribe('SEND', payload => {
      alert(payload)
    })
    this.$socket.$subscribe('user', payload => {
      this.console = payload
    })
    this.console = this.$socket
  },
  sockets: {
    connect() {
      alert('socket connected')
    },
    customEmit(val) {
      alert(`this method was fired by the socket server. eg: io.emit("customEmit", data)${val}`)
    }
  },
  data(){
    return {
      mapData:{
        appKey: '789b2dc91d9235fae744572478c25f39', // 테스트용 appkey
        center: {lat:33.450701, lng:126.570667}, // 지도의 중심 좌표
        level: 3, // 지도의 레벨(확대, 축소 정도),
        mapTypeId: VueDaumMap.MapTypeId.NORMAL, // 맵 타입
        libraries: ['services', 'clusterer', 'drawing'], // 추가로 불러올 라이브러리
      },
      mapObject: null, // 지도 객체. 지도가 로드되면 할당됨.
      console: '',
      searchresult: '',
      lol: '',
      pagination: '',
      location: ''
    }
  },
  methods: {
    clickButton(val) {
      // this.$socket.client is `socket.io-client` instance
      this.$socket.client.emit('SEND', val);
    },
    // 지도가 로드 완료되면 load 이벤트 발생
    onLoad (map) {
      // 지도의 현재 영역을 얻어옵니다
      let daummaps = window.daum.maps
      let bounds = map.getBounds();
      // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
      let boundsStr = bounds.toString();
      let control = new daummaps.ZoomControl();
      map.addControl(control, daummaps.ControlPosition.TOPRIGHT); 
      this.console = ('Daum Map Loaded', boundsStr);
      let marker = new daummaps.Marker({
        map: map,
        position: new daummaps.LatLng(33.450701, 126.570667)
      })
      marker.setMap(map)
      daummaps.event.addListener(marker, 'mouseover',() =>{
          alert('marker mouseover!');
      })
      daummaps.event.addListener(marker, 'click', () => {
          alert('marker click!');
      });
      this.mapObject = map;
    },
    test(){
			axios.get(`/futsal/match/33`)
			.then(res=>{
				this.lol = res.data
			})
			.catch(e=>{
        alert(e)
      })
		},
    test2(){
      var req = new XMLHttpRequest();
      req.open('GET',`/futsal/match/33`, true);
      req.onreadystatechange = function () {
        if (req.readyState == 4) {
          alert(req)
        }
        alert(req)
      }
      req.send();
    },
    test3(){
      // Return a new promise.
      return new Promise(function(resolve, reject) {
        // Do the usual XHR stuff
        var req = new XMLHttpRequest();
        req.open('GET', `${store.state.futsal.context}/futsal/match/33`);
        /* req.setRequestHeader("Access-Control-Allow-Origin", "*")
        req.setRequestHeader("Authorization", "Bearer XXXXX") */
        req.onload = function() {
          // This is called even on 404 etc
          // so check the status
          if (req.status == 200) {
            // Resolve the promise with the response text
            resolve(req.response);
          }
          else {
            // Otherwise reject with the status text
            // which will hopefully be a meaningful error
            reject(Error(req.statusText));
          }
        };

        // Handle network errors
        req.onerror = function() {
          reject(Error("Network Error"));
        };

        // Make the request
        req.send();
      });
    },
		//https://developers.kakao.com/docs/restapi/local#%ED%82%A4%EC%9B%8C%EB%93%9C-%EA%B2%80%EC%83%89
  }
}
</script>
<style scoped>
</style>