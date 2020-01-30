module.exports = {
  "outputDir": "../src/main/resources/static",
  "indexPath": "../static/index.html",
  "devServer": {
    "proxy": "https://kr.api.riotgames.com"
  },
  "transpileDependencies": [
    "vuetify"
  ]
}