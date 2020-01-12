<template>
<div>
	<fut-head :style="`height: ${height}vh`"/>
  <v-card class="card">
    <v-card-title><h1><a href="/stadium/19/matches/">{{matchInfo.name}}</a></h1></v-card-title> 
    <v-card-subtitle>{{matchInfo.addr}}<br />{{matchInfo.time}}</v-card-subtitle>
    <v-card-action>
      <v-chip outlined>ddd</v-chip><v-chip outlined>ddd</v-chip><v-chip outlined>ddd</v-chip>
    </v-card-action>
    <v-card-text>{{matchInfo.name}} {{matchInfo.time}} 의 경기는
        <code>100%</code> 확률로 정상 진행되고 있습니다.
    </v-card-text>
  </v-card>
  <v-card class="card">
    <v-card-title>특이사항</v-card-title>
    <v-card-subtitle>{{rating}} 일반 매치는 실력에 상관없이 누구나 참여하실 수 있습니다.</v-card-subtitle>
    <v-row class="justify-center pa-1">
      <v-col
        v-for="n of matchInfo.rule"
        :key="n" :cols="12/`${matchInfo.rule.length}`">
        <v-card>
          <v-img :src="n.img" :alt="n.text"/>
          <v-card-text class="justify-center">{{n.text}}</v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-card>
  <v-divider/>
  <v-card class="card">
    <h3>구장 시설</h3>
    <v-row class="justify-center pa-1">
      <v-col
        v-for="n of matchInfo.stadium"
        :key="n" :cols="12/`${matchInfo.stadium.length}`">
        <v-card>
          <v-img :src="n.img" :alt="n.text"/>
          <v-card-text>{{n.text}}</v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-card>
  <v-card class="card">
    <h4>구장 특이사항</h4>
    <li justify-left 
      v-for="item of [
        '5대5 구장의 최소 인원은 8명입니다.',
        '모든 5vs5구장은 정원 모집 시 삼파전으로 진행합니다.',
        '신분당선 판교역 1번 출구 방',
        '다산건물 전용 엘리베이터 사용',
        '주차 : 평일 2시간 무료 / 주말 무료',
        '(평일 이용시 주차 차량 번호 기입 필수, 2시간 이상 주차시 추가 비용 발생)',
        '화장실은1층 화장실 이용',
        '자판기 및 흡연 구역 있음'
      ]" :key="item">
      {{item}}
    </li>

    <span class="contentAnchor">더 자세한 정보는 <a href="">{{matchInfo.name}} 의 시설 정보</a>에서 확인하세요</span>
  </v-card>
  <v-divider/>
  <v-card class="card">
    <div class="d-flex">
      <v-img elevation-6 src="https://pds.joins.com/news/component/htmlphoto_mmdata/201910/26/ce877ed2-0800-457f-b9a6-a86044718d40.jpg"/>
      <div>
        <v-card-title>매니저  : {{matchInfo.adminName}}</v-card-title>
        <v-card-subtitle>한분한분 같이 웃고 즐기며 소통하는 매니저 박상준입니다. 
                매 경기 안 다치고 소중한 시간 재미있게 즐길수 있게 최선을 다하겠습니다. 
                같이 플랩으로 오세요!</v-card-subtitle>
        <a class="pa-4" href="/manager/apply/">플랩풋볼 매니저에 대해 알아보기</a>
      </div>
    </div>
  </v-card>
  <v-divider/>
  <v-card class="card">
    <h3>주의사항</h3>
    <ul>
      <span>플랩풋볼 매치는 참가자 간의 신뢰를 바탕으로 진행됩니다.</span>
      <h4>클린 플랩풋볼</h4>
      <li>다른 참가자들을 위해 시간을 준수해 주세요.</li>
      <li>풋살화 또는 스터드가 없는 운동화를 착용해주세요.</li>
      <li>불필요한 언행, 지시 등은 삼가해주세요.</li>
      <li>과도한 경쟁, 승부욕은 나와 상대방의 부상을 야기할 수 있습니다.</li>
      <li>폭언, 폭행은 이용 정지의 심각한 사유가 될 수 있습니다.</li>
    </ul>
    <ul>
      <h4>이용 제한이 되는 경우</h4>
      <li>경기 시작 1시간 30분 전까지 신청 취소 없이 무단 불참할 경우</li>
      <li>비가 와도 매치가 진행되는 경우 위와 동일</li>
      <li>1회 위반 시 한 달, 2회 위반 시 이용이 영구 정지됩니다.</li>
    </ul>
    <v-divider/>
    <h3>취소/환불</h3>
    <ul>
      <h4>매치가 취소되는 경우</h4>
      <li>각 구장별 최소 인원 (6 vs 6 구장 10명 / 5 vs 5 구장 8명) 이 되지 않을 경우 경기가 취소 될 수 있으며 진행 여부 안내는 1시간 30분 전까지 카카오톡 알림톡을 통해 안내드리고 있습니다.</li>
      <li>당일 기상악화의 경우에도 환급율은 동일하며 신청 전 꼭 기상 확인 바랍니다.</li>
      <li>인원이 모집되는 경우 우천 시에도 진행되며 참석이 어려울 경우 진행 확정 전 사전 취소 부탁드립니다.</li>
      <li>경기 중 플레이가 어려울 정도로 비가 오는 경우에는 현장에서 판단합니다.</li>
    </ul>
    <ul>
      <h4>신청을 취소할 경우</h4>
      <li>당일 기상악화의 경우에도 환급율은 동일하며 신청 전 꼭 기상 확인 바랍니다.</li>
      <li>인원이 모집되는 경우 우천 시에도 진행됩니다.</li>
      <li>매치시작 1시간 30분 전까지 취소하지 않고 불참하면 향후 이용 제한이 있습니다.</li>
      <li>신청 취소 시 아래 환불 규정에 따라 환불이 진행됩니다.</li>
    </ul>
    <ul>
      <h4>환불 규정</h4>
      <li>경기 2일 전 신청 취소시: 전액 환급</li>
      <li>경기 1일 전 신청 취소시: 참가비의 80% 환금</li>
      <li>경기 당일 3시간 전 신청 취소시: 참가비의 20% 환급</li>
      <li>경기 당일 3시간 미만 신청 취소시: 0%</li>
      <li>인원 부족으로 경기가 취소될 경우에는 최소 1시간 30분 전에 개별적으로 매치 취소 안내를 드리며 해당 캐시는 당일내로 전액 환급 처리됩니다.</li>
      <li>경기 중 부상에 대한 책임은 해당 개인에게 귀속됩니다.</li>
    </ul>
  </v-card>
</div>
</template>

<script>
import FutHead from './FutHead'
export default {
  components:{FutHead},
  data(){
    return{
      height: 30,
      matchInfo: {
        time: "xx일 xx월 xx년 xx시",
        name: "어디어디 경기장",
        addr: "어디어디 어디 어디  주소",
        rating: "별이 3개",
        gender: "게이",
        adminName: "펭수",
        rule: [
          {text:'주소 복사하기', img:'https://plab-football.s3.amazonaws.com/static/img/ic_6vs6.svg'},
          {text: '주소 복사하기', img:'https://plab-football.s3.amazonaws.com/static/img/ic_male.svg'},
          {text: '주소 복사하기', img:'https://plab-football.s3.amazonaws.com/static/img/ic_every.svg'},
          {text:'주소 복사하기', img:'https://plab-football.s3.amazonaws.com/static/img/ic_turf.svg'} ,
          {text: '주소 복사하기', img:'https://plab-football.s3.amazonaws.com/static/img/ic_minmax.svg'}
        ],
        stadium: [
          {text:' 2',img:'https://plab-football.s3.amazonaws.com/static/img/ic_size.svg'},
          {text: ' 3',img:'https://plab-football.s3.amazonaws.com/static/img/ic_shower.svg'},
          {text: ' 4',img:'https://plab-football.s3.amazonaws.com/static/img/ic_parking_pay.svg'},
          {text: ' 5',img:'https://plab-football.s3.amazonaws.com/static/img/ic_rentalshoes.svg'},
          {text: ' 6',img:'https://plab-football.s3.amazonaws.com/static/img/ic_rentalwear.svg'}
        ],
      texts: ''}
    }
  },
  methods:{
  }
}
</script>
<style scoped>
.card{
  width: 80%;
  padding: 4px;
  text-align: left;
}
</style>