package com.eliasnogueira.test;

import static org.testng.Assert.assertEquals;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TesteAceitacaoBasico {

	DesiredCapabilities capacidade;
	AndroidDriver driver;

	/*
	 * Pre condicao para capacidades de inicializacao da app
	 */
	@BeforeMethod
	public void preCondicao() throws MalformedURLException {
		capacidade = new DesiredCapabilities();
		
		// informa qual a plataforma para o dispositivo, da grid e onde irá executar (device ou emulator)
		capacidade.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capacidade.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
		capacidade.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		
		// informa qual o pacote inicial e a activity que será iniciada pela aplicação
		capacidade.setCapability(MobileCapabilityType.APP_PACKAGE, "com.eliasnogueira.trianguloapp");
		capacidade.setCapability(MobileCapabilityType.APP_ACTIVITY, "MainActivity");
		
		// abre a aplicação, informando a conexão com o servidor do appium
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capacidade);
	}

	
	/*
	 * Pos condição do teste, que é o fechamento da app
	 */
	@AfterMethod
	public void posCondicao() {
		driver.closeApp();
	}

	
	/*
	 * Teste para verificar se o triangulo com lados iguais é um equilátero 
	 */
	@Test
	public void testeTriaguloEquilatero() throws MalformedURLException, UnsupportedEncodingException {
		
		/*
		 * O comando para localizacao de um componente android é driver.findElement(By.estrategia)
		 * A estrategia pode ter varias diferencas, mas aqui estamos localizando por id
		 * Este id é obtido através do 'uiautomatorviewer' 
		 * 
		 * Após a localização podemos efetuar alguma ação no componente como:
		 *    - sendKeys: digitar 
		 *    - click: clicar/tap sob um componente
		 */
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtLado1")).sendKeys("3");
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtLado2")).sendKeys("3");
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtLado3")).sendKeys("3");

		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/btnCalcular")).click();

		/*
		 * O assertEquals faz parte do TestNG e está comparando se o texto 'O triângulo é Equilátero' é igual ao
		 * texto que é apresentado logo depois de clicar no botão 'Calcular', onde a função getText() retorna
		 * o texto do componente
		 */
		assertEquals("O triângulo é Equilátero",
				driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txtResultado")).getText());
	}
}
