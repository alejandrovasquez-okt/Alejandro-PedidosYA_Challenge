import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PedidosYaTests {
    public static WebDriver driver = new ChromeDriver();
    public static String url_base = "https://www.pedidosya.com.uy/";
    PedidosYaMethods pedidosYaMethods = new PedidosYaMethods();

    @Before
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/alejandrovasquez/Documents/Proyectos Oktana/chromedriver");
    }
    @Test(priority = 1)
    public void pedidosYAFirstTest() throws Exception {
        String ciudad = "Montevideo";
        String direccion = "Av. 18 de Julio 2071";
        String sugerencia = "Pizza";
        String filtro = "cigarrillos";
        String filtro_2 = "tarjeta-oca";
        String numero_Resultados_por_filtro; String numero_Resultados_por_filtro_2;

        driver.manage().window().maximize();
        driver.get(url_base);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        pedidosYaMethods.seleccionarCiudad(driver,ciudad);
        pedidosYaMethods.enviarDireccionSearch(driver,direccion);
        pedidosYaMethods.enviarBusquedaSearch(driver,sugerencia);
        pedidosYaMethods.clickConfirmarBusqueda(driver);
        pedidosYaMethods.enviarPaginaResultados(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        pedidosYaMethods.validarPaginaResultados(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        pedidosYaMethods.contarResultadosIniciales(driver);
        pedidosYaMethods.scrollearAlInicio(driver);
        pedidosYaMethods.scrollearAlInicio(driver);
        numero_Resultados_por_filtro = pedidosYaMethods.recuperarNumeroResultadosFiltro(driver,filtro);
        System.out.println("\nThe filter is: "+filtro+"   |   Number of results: "+numero_Resultados_por_filtro);
        pedidosYaMethods.seleccionarFiltro(driver,filtro);
        pedidosYaMethods.validarNumeroResultadoFiltro(driver,numero_Resultados_por_filtro);
        numero_Resultados_por_filtro_2 = pedidosYaMethods.recuperarNumeroResultadosFiltro(driver,filtro_2);
        pedidosYaMethods.scrollearAlInicio(driver);
        pedidosYaMethods.scrollearAlInicio(driver);
        System.out.println("\nThe filter is: "+filtro_2+"   |   Number of results: "+numero_Resultados_por_filtro_2);
        pedidosYaMethods.seleccionarFiltro(driver,filtro_2);
        pedidosYaMethods.validarNumeroResultadoFiltro(driver,numero_Resultados_por_filtro_2);
        pedidosYaMethods.scrollearAlInicio(driver);
        pedidosYaMethods.scrollearAlInicio(driver);
        pedidosYaMethods.cambiarOrdenAlfabetico(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        pedidosYaMethods.validarOrdenActual(driver);
        pedidosYaMethods.contarEstrellasPrimerResultado(driver);
        pedidosYaMethods.clickPrimerResultado(driver);
        pedidosYaMethods.clickInformacion(driver);
        pedidosYaMethods.verificarHorarios(driver);
        pedidosYaMethods.verificarInformacionVital(driver);
        pedidosYaMethods.clickOpinions(driver);
        pedidosYaMethods.retrieveOpinions(driver);
        driver.close();
    }
}
