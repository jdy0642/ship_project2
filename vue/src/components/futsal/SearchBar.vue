<template>
<div>
	<div class="row">
		<div style="width: 100%">
			<div style="margin: auto; width: 40%;" class="input-group">
			<input v-model="stadium" @keyup.enter="submit" type="text" class="form-control" placeholder="Search for...">
			<span class="input-group-btn">
				<button @click="submit" class="btn btn-default" type="button">Go!</button>
			</span>
			</div>
		</div>
	</div>
</div>
</template>
<script>
export default({
	data(){
		return {
			stadium : ''
		}
	},
	methods:{
		submit(){
			this.$emit("send",this.stadium)
		}
	}
})
</script>
<style scoped>
div{
	text-align: center
}
</style>