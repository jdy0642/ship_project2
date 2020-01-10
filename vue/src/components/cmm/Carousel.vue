<template>
<div id="app" >
    <v-carousel height="873px">
      <v-carousel-item v-for="item of items" :key="item.id" v-html="item.content" @click="carougo(item.link)">
          </v-carousel-item>
    </v-carousel>
</div>
</template>

<script>
export default {
 data: () => ({
    items: [
      {id: "1", link:"/futsal",
      content: '<iframe width="1400" height="873" src="https://www.youtube.com/embed/TW1u_O3IyTc" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>'},
      {id: "2",
      content:'<iframe width="1400" height="873" src="https://www.youtube.com/embed/krDYaQsVi2Q" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>'}
    ]
  }),
  methods:{
    carougo(x){
      if(x===undefined){
        alert('준비중인 컨텐츠 입니다.')
      }else{
        this.$router.push({path:`${x}`})
      }
    }
  }
}
</script>
<style scoped>
</style>
