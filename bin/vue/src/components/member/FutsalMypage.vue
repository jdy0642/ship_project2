<template>
<body>
 <!-- <social-sharing url="https://vuejs.org/"
  title="The Progressive JavaScript Framework"
  description="Intuitive, Fast and Composable MVVM for building interactive interfaces."
  quote="Vue is a progressive framework for building user interfaces."
  hashtags="vuejs,javascript,framework"
  twitter-user="vuejs"
  inline-template>
<div>
  <network network="email">
  <i class="fa fa-envelope"></i> Email
  </network>
  <network network="facebook">
  <i class="fa fa-facebook"></i> Facebook
  </network>
  <network network="googleplus">
  <i class="fa fa-google-plus"></i> Google +
  </network>
  <network network="line">
  <i class="fa fa-line"></i> Line
  </network>
  <network network="linkedin">
  <i class="fa fa-linkedin"></i> LinkedIn
  </network>
  <network network="odnoklassniki">
  <i class="fa fa-odnoklassniki"></i> Odnoklassniki
  </network>
  <network network="pinterest">
  <i class="fa fa-pinterest"></i> Pinterest
  </network>
  <network network="reddit">
  <i class="fa fa-reddit"></i> Reddit
  </network>
  <network network="skype">
  <i class="fa fa-skype"></i> Skype
</network>
  <network network="sms">
  <i class="fa fa-commenting-o"></i> SMS
  </network>
  <network network="telegram">
  <i class="fa fa-telegram"></i> Telegram
  </network>
  <network network="twitter">
  <i class="fa fa-twitter"></i> Twitter
  </network>
  <network network="vk">
  <i class="fa fa-vk"></i> VKontakte
</network>
<network network="weibo">
  <i class="fa fa-weibo"></i> Weibo
</network> 
<network network="whatsapp">
<i class="fa fa-whatsapp"></i> Whatsapp
</network>
</div>
</social-sharing> -->
<div class="container">
  <div class="header">
    <div class="menu1">나의경기</div>
    <div class="menu1">경기수 10회</div>
    <div class="menu1">득점수 20득점</div>
    <div class="menu1">MVP 5회</div>
      <div class="menu1">경고횟수 3회</div>
  </div>    
  <div class="item">부천 경기장</div>
  <div class="item">소셜 연동하기</div>
  <div class="hitmap">
  <img class="img1" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIxyvmnUl2qiQ-bgHT6EXd4joOPxiVevGRIJEYMxlpjKO3AaVY&s">
  </div>
  <div class="item">나의 전적 확인</div>
</div>
</body>
</template>
<style scoped>
.container{
    display: grid;
    grid-template-rows: 10px 50px 40px 200px;
    width: 100%;
    border: 1px solid black;
    margin: 10px;
      }
.header{
    border-color: red;
    border: 1px solid black;
    grid-column-start: 1;
    grid-column-end: 3;
}
.menu1{
    float: left;
    width: 20%;
    height : 100%;
    text-align: center;
}
.item{
    border: 1px solid black;
    font: 2em sans-serif;
    text-align: center;
}
.img1{
    border: 1px solid black;
    width: 296%;
    height: 199%;
}
.hitmap{
    border: 1px solid black;
    width: 100px;
    height: 100px;
}
</style>