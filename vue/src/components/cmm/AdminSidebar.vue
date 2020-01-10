<template>
	<div>
		<ul class="menu">
			<li v-for="i of sidebars" :key="i.menu">
				<router-link :to="i.link">{{i.menu}}</router-link>
			</li>
		</ul>
	</div>
</template>
<script>
export default {
	name:'adminSidebar',
	data () {
		return {
		}
	}
}
</script>
<style scoped>
li {
  margin:10px 0px 10px;
}
</style>