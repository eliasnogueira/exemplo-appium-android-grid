# Exemplo de automação para Android com Appium com execução em Grid

Este projeto é um exemplo de automação funcional para interface gráfica (UI) em Android usando a ferramenta Appium e Selenium Grid.

### Ferramentas utilizadas
* [Eclipse IDE](http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/lunasr2) com Maven (Maven Project)
* Appium ([biblioteca](https://github.com/appium/java-client) e [aplicação](https://bitbucket.org/appium/appium.app/downloads/))
* Selenium WebDriver ([server](http://docs.seleniumhq.org/download/) para execução em grid)
* [node.js](https://nodejs.org/) (para execução na grid. de forma standalone utilizamos a aplicação do appium)

Também é necessário que você tenha:
* Java no classpath (JAVA_HOME e PATH apontando para a pasta *bin* do Java)
* Android SDK no classpath (ANDROID_HOME e o PATH com as pastas *tools* e *platform-tools*)
* node.js no path

### Scripts de Teste
* [TesteAceitacaoBasico.java](https://github.com/eliasnogueira/exemplo-appium-android-grid/blob/master/src/test/java/com/eliasnogueira/test/TesteAceitacaoBasico.java): script para rodar o script sem grid, em dispositivos físicos ou emulados
* [TesteAceitacaoGrid.java](https://github.com/eliasnogueira/exemplo-appium-android-grid/blob/master/src/test/java/com/eliasnogueira/test/TesteAceitacaoGrid.java): script para rodar o script com grid, em dispositivos físicos ou emulados

O script com a grid utiliza para diferencias os dispositivos a capacidade *udid*, que é o nome do dispositivo via *adb devices*
Essa é uma boa abordagem, pois você pode ter mais de um dispositivo da mesma versão (Lollipop, por exemplo) com diferentes focos. Logo uma das maneiras de ter essa "duplicidade" é através do nome do dispositivo.

* ive/README.md](https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md)
* [plugins/onedrive/README.md](https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md)

### Execução
Primeiro instalar a aplicação que está na pasta *app*
```adb install TrianguloApp.apk``


##### Teste em Grid #####
1. Iniciar a aplicação do Appium
   1. Na aplicação é necessário preencher o campo *Device Name* para Android
2. Executar o script de teste

##### Teste com Grid #####
Executar a série de comandos abaixo para a execução do teste

1. Iniciar o Selenium Grid via linha de comando ```java -jar selenium-server-standalone-2.45.0.jar -role hub```
2. Acessar o console da grid (não haverá máquinas conectadas) ```http://localhost:4444/grid/console```
3. Iniciar um nó (dispositivo) e registra-lo a grid ```appium --nodeconfig /Users/eliasnogueira/Documents/workspace/android-appium-parallel-test/json/androidAppium1.json -p 4724 -U 192.168.56.100:5555```
4. Adicionar o nome do dispositivo através da capacidade *udid*
5. Alterar a URL do AndroidDriver, apontando para a grid ```http://localhost:4444/wd/hub```
6. Executar o teste


#### Observações ####
* O registro do nó no hub requer o node.js instalado e no path do SO.
* O parâmetro *--nodeconfig* irá pegar o arquivo .json e registrar o dispositivo (plataforma, porta, url, etc...). Acesse a pasta [json](https://github.com/eliasnogueira/exemplo-appium-android-grid/tree/master/json) para ver os arquivos de configuração do nó
* O parâmetro *-p* é a posta do node.js que será usada no nó. Sempre utilize portas não comumente utilizadas. Pode ser que, quando ocorra um erro, o nó pare de responder na grid. Para voltar a conectar na grid é necessário matar o processo que está usando a porta e executar o comando novamente
* O parâmetro -U é o nome do dispositivo. Ele é inserido para um maior controle para a execução na grid, já que estamos usando o nome do dispositivo para isso
