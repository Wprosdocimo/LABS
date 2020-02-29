from ExtratorArgumentosURL import ExtratorArgumentoUrl


url = "https://bytebank.com/cambio?moedaorigem=moedadestino&moedadestino=dolar&valor=1500,00"

argumentosUrl = ExtratorArgumentoUrl(url)
moedaorigem, moedadestino = argumentosUrl.extraiArgumentos()
print(moedaorigem, moedadestino)