import Vue from 'vue'
import Router from 'vue-router'
import PreSidebar from '@/components/cmm/PreSidebar.vue'
import AdminSidebar from  '@/components/cmm/AdminSidebar.vue'
import Test from  '@/components/cmm/Test.vue'
import StudentSidebar from  '@/components/cmm/StudentSidebar.vue'
import Carousel from  '@/components/cmm/Carousel.vue'
import Join from '@/components/auth/Join.vue'
import Login from '@/components/auth/Login.vue'
import MyPage from '@/components/auth/MyPage.vue'
import Admin from '@/components/admin/Admin.vue'
import Register from '@/components/admin/Register.vue'
import MemberManage from '@/components/admin/MemberManage.vue'
import FutsalMypage from  '@/components/member/FutsalMypage.vue'
import StudentList from  '@/components/member/StudentList.vue'
import MyPageUpdate from  '@/components/member/MyPageUpdate.vue'
import List from '@/components/article/List.vue'
import Remove from '@/components/article/Remove.vue'
import Search from '@/components/article/Search.vue'
import Update from '@/components/article/Update.vue'
import Write from '@/components/article/Write.vue'
import Futsal from '@/components/contents/Futsal.vue'
import Lol from '@/components/contents/Lol.vue'
import StudentFindSome from  '@/components/admin/StudentFindSome.vue'
import StudentFindId from  '@/components/admin/StudentFindId.vue'
import FutsalHome from '@/components/futsal/FutsalHome.vue'
import FutsalMatch from '@/components/futsal/MatchPage.vue'
import CreateRoom from '@/components/lol/CreateRoom.vue'
import JoinRank from '@/components/lol/JoinRank.vue'
import JoinKal from '@/components/lol/JoinKal.vue'
import Kalbaram from '@/components/lol/Kalbaram.vue'

Vue.use(Router)
export default new Router({
     mode: 'history',
     routes: [
          {path:'/presidebar', name:'presidebar',  component: PreSidebar},
          {path:'/adminsidebar', name:'adminsidebar',  component: AdminSidebar},
          {path:'/studentsidebar', name:'studentsidebar',  component: StudentSidebar},
          {path:'/join', name:'join', component: Join},
          {path:'/studentlist', name:'studentlist',  component: StudentList},
          {path:'/admin', name:'admin', component: Admin},
          {path:'/mypage', name:'mypage', component:  MyPage},
          {path:'/futsalmypage', name:'futsalmypage',  component: FutsalMypage},
          {path:'/mypageupdate', name:'mypageupdate',  component: MyPageUpdate},
          {path:'/studentfindsome',  name:'studentfindsome', component: StudentFindSome},
          {path:'/studentfindid', name:'studentfindid',  component: StudentFindId},
          {path:'/login', name:'login', component: Login},
          {path:'/list', name:'list', component: List},
          {path:'/search', name:'search', component:  Search},
          {path:'/update', name:'update', component:  Update},
          {path:'/write', name:'write', component: Write},
          {path:'/futsal', name:'futsal', component:  Futsal},
          {path:'/lol', name:'lol', component: Lol},
          {path:'/remove', name:'remove', component:  Remove},
          {path: '/futsalhome', name: 'futsalhome', component: FutsalHome},
          {path: '/futsalhome/match', name: 'futsalmatch', component: FutsalMatch},
          {path: '/createroom', name: 'createroom', component: CreateRoom},
          {path: '/register', name: 'register', component: Register},
          {path: '/test', name: 'test', component: Test},
          {path: '/joinrank', name: 'joinrank', component: JoinRank},
          {path: '/joinkal', name: 'joinkal', component: JoinKal},
          {path: '/kalbaram', name: 'kalbaram', component: Kalbaram},
          {path: '/membermanage', name: 'membermanage', component: MemberManage},
          {path: '/', name: 'carousel', component: Carousel}
          
     ]    
})