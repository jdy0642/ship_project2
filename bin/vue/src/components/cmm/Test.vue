<template>
<div id="app">
  테스트
</div>
</template>

<script>
export default {
    data () {
    return {
      components: [
        'Autocompletes', 'Comboboxes', 'Forms', 'Inputs', 'Overflow Buttons', 'Selects', 'Selection Controls', 'Sliders', 'Textareas', 'Text Fields',
      ],
    }
  },
  methods:{
    radiogo(x){
      this.$router.push({path:`${x}`})
    },
    reset () {
      this.$refs.form.reset()
    },
  }
}
</script>

<style scoped>
</style>