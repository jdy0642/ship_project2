<template>
<div id="app">
  <v-app id="inspire" >
    <v-container class="text-center">
      <v-row class="fill-height" align="center" justify="center">
        <template v-for="(item, i) of items">
          <v-col :key="i" cols="12"  md="6" >
            <v-hover v-slot:default="{ hover }">
              <v-card :elevation="hover ? 12 : 2" :class="{ 'on-hover': hover }">
                <v-img :src="item.img" height="830px" >
                  <v-card-title class="title white--text">
                    <v-row class="fill-height flex-column" justify="space-between">
                      <p class="mt-4 subheading text-left">{{ item.title }}</p>
<!--   
                      <div class="align-self-center">
                        <v-btn v-for="(icon, index) of icons" :key="index" :class="{ 'show-btns': hover }" color="transparent" icon >
                          <v-icon :class="{ 'show-btns': hover }" color="transparent" >
                            {{ icon }}
                          </v-icon>
                        </v-btn>
                      </div> -->
                    </v-row>
                  </v-card-title>
                </v-img>
              </v-card>
            </v-hover>
          </v-col>
        </template>
      </v-row>
    </v-container>
  </v-app>
</div>
</template>

<script>
export default {
 data: () => ({
    icons: ['mdi-rewind', 'mdi-play', 'mdi-fast-forward'],
    items: [
      {
        // title: 'New Releases',
        img: 'http://lorempixel.com/output/nightlife-q-c-640-480-5.jpg',
      },
      {
        // title: 'Rock',
        img: 'https://images.unsplash.com/photo-1498038432885-c6f3f1b912ee?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2100&q=80',
      },
    ],
    transparent: 'rgba(255, 255, 255, 0)',
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
.v-card {
transition: opacity .4s ease-in-out;
}

.v-card:not(.on-hover) {
opacity: 0.6;
 }

.show-btns {
color: rgba(255, 255, 255, 1) !important;
}
</style>
