<template>
<div id="app" style="height:685px">
    <form class="ma-4">
      <v-text-field
        label="카드네임"
        v-model="name"
        :counter="30"
        @input="$v.name.$touch()"
        @blur="$v.name.$touch()"
        required></v-text-field>
      <v-card flat>
      <v-card-text>
        <v-container fluid>
          <v-row>
            <v-col cols="12" sm="6" md="6">
             <v-radio-group v-model="row" row>
        <v-radio label="개인/2인랭크게임" value="rank" ></v-radio>
        <v-radio label="칼바람나락" value="kal"></v-radio>
      </v-radio-group>
      <div style="border:1px solid black"></div>
              <v-radio-group v-model="ex8" column>
                <v-radio
                  label="브론즈"
                  color="primary"
                  value="primary"
                ></v-radio>
                <v-radio
                  label="실버"
                  color="secondary"
                  value="secondary"
                ></v-radio>
                <v-radio
                  label="골드"
                  color="success"
                  value="success"
                ></v-radio>
                <v-radio
                  label="플레티넘"
                  color="info"
                  value="info"
                ></v-radio>
                <v-radio
                  label="다이아"
                  color="warning"
                  value="warning"
                ></v-radio>
                <v-radio
                  label="마스터"
                  color="error"
                  value="error"
                ></v-radio>
                
              </v-radio-group>
              
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
    </v-card>
      <textarea placeholder="내용입력" autocomplete="nope" id="comment" name="a1670651c2" cols="130" rows="6" maxlength="65525" aria-required="true" required="required"></textarea>
      
      <v-btn @click="lol()">방생성</v-btn>
      <v-btn @click="lol()">방취소</v-btn>
    </form>

</div>
</template>
<script>
export default{
  data(){
    return{
      items: ['실버', '브론즈', '골드', '플레티넘'],
      row:'rank'
    }
  },
  methods:{
    lol(){
      this.$router.push({path:'/lol'})
    }
  }
}
</script>