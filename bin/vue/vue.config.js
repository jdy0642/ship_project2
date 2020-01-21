module.exports = {
  "outputDir": "../src/main/resources/static",
  "indexPath": "../static/index.html",
  "devServer": {
    "proxy": "http://localhost:8080,http://localhost:8081"
  },
  "transpileDependencies": [
    "vuetify"
  ]
}