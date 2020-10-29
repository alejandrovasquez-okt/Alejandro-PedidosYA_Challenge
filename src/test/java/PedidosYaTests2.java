import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PedidosYaTests2 {
    public static WebDriver driver_2 = new FirefoxDriver();
    public static String url_base = "https://www.pedidosya.com.uy/";
    PedidosYaMethods pedidosYaMethods = new PedidosYaMethods();

    @Before
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "/Users/alejandrovasquez/Documents/Proyectos Oktana/geckodriver");
    }
    @Test(priority = 2)
    public void pedidosYASecondTest() throws Exception {
        String ciudad = "Montevideo";
        String direccion = "Av. 18 de Julio 2071";
        String sugerencia = "Pizza";
        String filtro = "cigarrillos";
        String filtro_2 = "tarjeta-oca";
        String numero_Resultados_por_filtro; String numero_Resultados_por_filtro_2;

        driver_2.manage().window().maximize();
        driver_2.get(url_base);
        driver_2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        pedidosYaMethods.seleccionarCiudad(driver_2,ciudad);
        pedidosYaMethods.enviarDireccionSearch(driver_2,direccion);
        pedidosYaMethods.enviarBusquedaSearch(driver_2,sugerencia);
        pedidosYaMethods.clickConfirmarBusqueda(driver_2);
        pedidosYaMethods.enviarPaginaResultados(driver_2);
        driver_2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        pedidosYaMethods.validarPaginaResultados(driver_2);
        driver_2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        pedidosYaMethods.contarResultadosIniciales(driver_2);
        pedidosYaMethods.scrollearAlInicio(driver_2);
        pedidosYaMethods.scrollearAlInicio(driver_2);
        numero_Resultados_por_filtro = pedidosYaMethods.recuperarNumeroResultadosFiltro(driver_2,filtro);
        System.out.println("\nThe filter is: "+filtro+"   |   Number of results: "+numero_Resultados_por_filtro);
        pedidosYaMethods.seleccionarFiltro(driver_2,filtro);
        pedidosYaMethods.validarNumeroResultadoFiltro(driver_2,numero_Resultados_por_filtro);
        numero_Resultados_por_filtro_2 = pedidosYaMethods.recuperarNumeroResultadosFiltro(driver_2,filtro_2);
        pedidosYaMethods.scrollearAlInicio(driver_2);
        pedidosYaMethods.scrollearAlInicio(driver_2);
        System.out.println("\nThe filter is: "+filtro_2+"   |   Number of results: "+numero_Resultados_por_filtro_2);
        pedidosYaMethods.seleccionarFiltro(driver_2,filtro_2);
        pedidosYaMethods.validarNumeroResultadoFiltro(driver_2,numero_Resultados_por_filtro_2);
        pedidosYaMethods.scrollearAlInicio(driver_2);
        pedidosYaMethods.scrollearAlInicio(driver_2);
        pedidosYaMethods.cambiarOrdenAlfabetico(driver_2);
        driver_2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        pedidosYaMethods.validarOrdenActual(driver_2);
        pedidosYaMethods.contarEstrellasPrimerResultado(driver_2);
        pedidosYaMethods.clickPrimerResultado(driver_2);
        pedidosYaMethods.clickInformacion(driver_2);
        pedidosYaMethods.verificarHorarios(driver_2);
        pedidosYaMethods.verificarInformacionVital(driver_2);
        pedidosYaMethods.clickOpinions(driver_2);
        pedidosYaMethods.retrieveOpinions(driver_2);
        driver_2.close();
    }
}
