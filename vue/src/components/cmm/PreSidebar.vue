<template>
	<div>
		<ul class="menu">
			<li v-for="i of sidebars" :key="i.menu">
				<router-link :to="i.link">{{i.menu}}</router-link> <br/>
			</li>
		</ul>
	</div>
</template>
<script>
export default {
	name:'preSidebar',
	data(){
		return {
			sidebars: [
				{menu:"게시글 목록",link:"/list"},
				{menu:"게시글 검색",link:"/search"}
			]
		}
	}
}
</script>
<style scoped>
li {
  margin:10px 30px 10px 0px;
}
</style>