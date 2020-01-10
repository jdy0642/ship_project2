<template>
<!-- <div id="demo" class="carousel slide" data-ride="carousel">
  <ul class="carousel-indicators">
    <li data-target="#demo" data-slide-to="0" class="active"></li>
    <li data-target="#demo" data-slide-to="1"></li>
    <li data-target="#demo" data-slide-to="2"></li>
  </ul>
  <div>
    <div class="carousel-item active">
      <img :width="width" :height="height" src="https://blog.hmgjournal.com/images/contents/article/20161214-Reissue-night-football-03.jpg" alt="Los Angeles">
    </div>
    <div class="carousel-item">
      <img :width="width" :height="height" src="http://641109.igkorea.net/data/editor/1803/7ec6014758f9af0fd497c55e30ef7fd1_1522042126_13.jpg" alt="Chicago">
    </div>
    <div class="carousel-item">
      <img :width="width" :height="height" src="http://641109.igkorea.net/data/editor/1803/7ec6014758f9af0fd497c55e30ef7fd1_1522042791_19.jpg" alt="New York">
    </div>
  </div>
  <a class="carousel-control-prev" href="#demo" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#demo" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>
</div> -->
  <v-carousel :height="height"  cycle interval="2000">
    <v-carousel-item
      v-for="(item, i) of items" :key="i" :src="item.src" @click="msg(item.msg)">
      <!-- <v-row class="fill-height" align="center" justify="center">
        <div class="display-3">Slide {{ i + 1 }}</div>
      </v-row> -->
    </v-carousel-item>
  </v-carousel>
</template>
<script>
export default({
	data(){
		return{
      height: '100%',
      items: [
        {msg: '#ffddcc',src: 'https://blog.hmgjournal.com/images/contents/article/20161214-Reissue-night-football-03.jpg'},
        {msg: '#eeddcc',src: 'http://641109.igkorea.net/data/editor/1803/7ec6014758f9af0fd497c55e30ef7fd1_1522042126_13.jpg'},
        {msg: '#eecccc',src: 'http://641109.igkorea.net/data/editor/1803/7ec6014758f9af0fd497c55e30ef7fd1_1522042791_19.jpg'}
      ]
    }
	},
	methods:{
		msg(x){
			alert(x)
		}
	}
})
</script>
<style scoped>
</style>