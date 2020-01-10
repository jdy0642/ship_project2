<template>
	<div>
		<ul class="menu">
			<li v-for="i of sidebars" :key="i.menu">
				<router-link :to="i.link">{{i.menu}} <br/></router-link>
			</li>
		</ul>
	</div>
</template>
<script>
export default {
	data () {
		return {
			sidebars: this.$store.state.sidebars
		}
	}
}
</script>
<style scoped>
li {
  margin:10px 0px 5px;
}
</style>