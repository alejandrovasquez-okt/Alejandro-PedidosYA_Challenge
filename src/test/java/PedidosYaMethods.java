import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
public class PedidosYaMethods {
    public void elementVisible(WebDriver driver, WebElement element){
        WebDriverWait w = new WebDriverWait(driver,40);
        w.until(ExpectedConditions.visibilityOf(element));
    }

    public void elementClickeable(WebDriver driver, WebElement element){
        WebDriverWait w = new WebDriverWait(driver,40);
        w.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void seleccionarCiudad(WebDriver driver,String ciudad){
        WebElement myCity = driver.findElement(By.xpath("//div[@class='content-search']//div[@id='selectCity_chosen']"));
        elementVisible(driver,myCity);
        myCity.click();
        WebElement myOption = driver.findElement(By.xpath(String.format("//div[@class='content-search']//div[@id='selectCity_chosen']//li[text()='%s']",ciudad)));
        myOption.click();
    }

    public void enviarDireccionSearch(WebDriver driver, String direccion){
        WebElement streetAddress = driver.findElement(By.xpath("//div[@class='content-search']//input[@id='address']"));
        streetAddress.click();
        streetAddress.sendKeys(direccion);
    }

    public void enviarBusquedaSearch(WebDriver driver, String sugerencia){
        WebElement mySearch = driver.findElement(By.xpath("//div[@class='content-search']//input[@id='optional']"));
        mySearch.click();
        mySearch.sendKeys(sugerencia);
    }

    public void enviarPaginaResultados(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        WebElement results = driver.findElement(By.xpath("(//div[@class='available']//a)[1]"));
        results.click();
    }

    public void validarPaginaResultados(WebDriver driver){
        WebElement barraFiltros = driver.findElement(By.xpath("//section[@id='filterListContainer']//dl[@class='accordion top']//ul[@class='content_filter_channels content_new_channel']"));
        Assert.assertTrue(barraFiltros.isDisplayed());
    }

    public void clickConfirmarBusqueda(WebDriver driver) throws InterruptedException {
        WebElement btnSearch = driver.findElement(By.xpath("//button[@id='search']"));
        btnSearch.click();
        Thread.sleep(500);
        WebElement btnConfirmSearch = driver.findElement(By.xpath("//div[@class='notFoundContainer']//a[@class='button']"));
        //elementClickeable(driver,btnConfirmSearch);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", btnConfirmSearch);
        Thread.sleep(90);
        executor.executeScript("arguments[0].click();", btnConfirmSearch);
    }

    public String recuperarNumeroResultadosFiltro(WebDriver driver, String filtro){
        WebElement numeroResultadoFiltro = driver.findElement(By.xpath(String.format("//section[@id='filterListContainer']//dl[@class='accordion top']//ul[@class='content_filter_channels content_new_channel']//li//a[@data-key='%s']//i[@class='countFilters']",filtro)));
        return numeroResultadoFiltro.getText();
    }

    public void seleccionarFiltro(WebDriver driver, String filtro){
        WebElement filtroASeleccionar = driver.findElement(By.xpath(String.format("//section[@id='filterListContainer']//dl[@class='accordion top']//ul[@class='content_filter_channels content_new_channel']//li//a[@data-key='%s']",filtro)));
        filtroASeleccionar.click();
    }

    // ssssssjjjjierio3ri3oiro3ir
    public void contarResultadosIniciales(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Search results from the first page: \n");
        int i=0;
        boolean seguir=true;
        //1. Conseguir los resultados de la página mostrada
        do{
            try{
                i++;
                WebElement resultadoBusqueda = driver.findElement(By.xpath(String.format("(//section[@id='resultListContainer']//div[@id='listContent']//ul[@id='resultList']//li[@data-auto='shoplist_restaurant'])[%s]",i)));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resultadoBusqueda);
                System.out.println(i+". "+resultadoBusqueda.getAttribute("title"));
            }catch (NoSuchElementException ex){
                System.out.println("\nThe number of results shown in the first page was less than: "+i);
                seguir = false;
            }
        }while(seguir);
        System.out.println("\nThe number of search results was: "+(i-1));
        //2. Contar el número de páginas de resultados
        WebElement numeroDePaginas = driver.findElement(By.xpath("(//footer//ul[@class='pagination']//li[@data-auto='pagination_item'])[last()]//a"));
        System.out.println("\nTotal page results: "+numeroDePaginas.getText());
        int j= Integer.parseInt(numeroDePaginas.getText());
        int r = i-1;
        //3. Multiplicar
        System.out.println("\nThe total number of search results was: "+(r*j));
    }

    public void validarNumeroResultadoFiltro(WebDriver driver, String numeroResultado) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Search results from the first page: \n");
        int i=0;
        boolean seguir=true;
        //1. Conseguir los resultados de la página mostrada
        do{
            try{
                i++;
                WebElement resultadoBusqueda = driver.findElement(By.xpath(String.format("(//section[@id='resultListContainer']//div[@id='listContent']//ul[@id='resultList']//li[@data-auto='shoplist_restaurant'])[%s]",i)));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resultadoBusqueda);
                System.out.println(i+". "+resultadoBusqueda.getAttribute("title"));
            }catch (NoSuchElementException ex){
                System.out.println("\nThe number of results shown in the first page was less than: "+i);
                seguir = false;
            }
        }while(seguir);
        System.out.println("\nThe number of search results was: "+(i-1));
        i = i-1;
        int j = Integer.parseInt(numeroResultado);
        Assert.assertEquals(i,j);
        System.out.println("The condition was met, the number of results shown is "+i+" and the number according to the filter was "+j);
    }

    public void clickPrimerResultado(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        String xpath = "(//section[@id='resultListContainer']//div[@id='listContent']//ul[@id='resultList']//li[@data-auto='shoplist_restaurant'])[1]";
        WebElement cabeceraResultado1 = driver.findElement(By.xpath(xpath));
        System.out.println("The first result was: "+cabeceraResultado1.getAttribute("title"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", cabeceraResultado1);
    }

    public void scrollearAlInicio(WebDriver driver){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("window.scrollBy(0,-3000)");
        executor.executeScript("window.scrollBy(0,-3000)");
        executor.executeScript("window.scrollBy(0,-2000)");
    }

    public void cambiarOrdenAlfabetico(WebDriver driver){
        WebElement sorteado = driver.findElement(By.xpath("//section[@id='resultListContainer']//section[@class='order']//a[@data-auto='shoplist_ordercontainer']"));
        sorteado.click();
        WebElement opcion = driver.findElement(By.xpath("//section[@id='resultListContainer']//section[@class='order']//ul//li[@value='az']//a"));
        opcion.click();
    }

    public void validarOrdenActual(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        WebElement sorteado = driver.findElement(By.xpath("//section[@id='resultListContainer']//section[@class='order']//a[@data-auto='shoplist_ordercontainer']"));
        System.out.println("\nSorted by: "+sorteado.getText());
        Assert.assertEquals(sorteado.getText(),"Alfabéticamente");
    }

    public void contarEstrellasPrimerResultado(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("\nSearch results from the first page: ");
        System.out.println("Result    |    Number of Stars(Rating)\n");
            try{
                WebElement resultadoBusqueda = driver.findElement(By.xpath("(//section[@id='resultListContainer']//div[@id='listContent']//ul[@id='resultList']//li[@data-auto='shoplist_restaurant'])[1]"));
                WebElement estrellasResultado = driver.findElement(By.xpath("(//section[@id='resultListContainer']//div[@id='listContent']//ul[@id='resultList']//li[@data-auto='shoplist_restaurant'])[1]//span[@class='ranking']//i"));
                System.out.println(resultadoBusqueda.getAttribute("title") + "  |  "+estrellasResultado.getText()+" stars");
            }catch (NoSuchElementException ex){
                System.out.println("\nThere was no result for the search performed.");
            }
    }

    public void clickInformacion(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        WebElement informacionTab = driver.findElement(By.xpath("//nav[@id='profileTabs']//button[@data-link='tab-info']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", informacionTab);
    }

    public void verificarHorarios(WebDriver driver){
        System.out.println("\nSchedule for the restaurant:");
        for(int i=1;i<=7;i++){
            WebElement horarios = driver.findElement(By.xpath(String.format("(//section[@id='restaurantSchedule']//ul//li)[%s]//div[@itemprop='openingHoursSpecification']",i)));
            System.out.println(horarios.getText());
        }
    }

    public void verificarInformacionVital(WebDriver driver){
        System.out.println("\nInformation of the restaurant: ");
        WebElement direccion = driver.findElement(By.xpath("//section[@id='restaurantAddress']//i//span[@class='text']"));
        System.out.println("Address: "+direccion.getText());
        //WebElement distancia = driver.findElement(By.xpath("//section[@id='profileInfo']//div[@id='profileDetails']//span[@class='distance']"));
        //System.out.println("Distance: "+distancia.getAttribute("title"));
        WebElement delivery = driver.findElement(By.xpath("//section[@id='profileInfo']//div[@id='profileDetails']//span[@class='deliveryTime']"));
        System.out.println("Delivery Time: "+delivery.getAttribute("title"));
        WebElement costo = driver.findElement(By.xpath("//section[@id='profileInfo']//div[@id='profileDetails']//span[@class='shippingAmount']"));
        System.out.println("Shipping Cost: "+costo.getAttribute("title"));
        //WebElement minimo = driver.findElement(By.xpath("//section[@id='profileInfo']//div[@id='profileDetails']//span[@class='deliveryAmount']"));
        //System.out.println("Minimum Shipping Amount: "+minimo.getAttribute("title"));
    }

    public void clickOpinions(WebDriver driver){
        WebElement opnionTab = driver.findElement(By.xpath("//nav[@id='profileTabs']//button[@data-link='tab-comments']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", opnionTab);
    }

    public void retrieveOpinions(WebDriver driver) throws InterruptedException {
        Thread.sleep(200);
        System.out.println("\nOpinions of our customers: ");
        for(int i=1;i<=3;i++){
            try{
                WebElement opinion = driver.findElement(By.xpath(String.format("(//section[@id='reviewListContainer']//li[@itemprop='review'])[%s]//p[@itemprop='description']",i)));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", opinion);
                WebElement authorOpinion = driver.findElement(By.xpath(String.format("(//section[@id='reviewListContainer']//li[@itemprop='review'])[%s]//div[@itemprop='author']",i)));
                WebElement dateOpinion = driver.findElement(By.xpath(String.format("(//section[@id='reviewListContainer']//li[@itemprop='review'])[%s]//p[@itemprop='datePublished']",i)));
                WebElement rateOpinion = driver.findElement(By.xpath(String.format("(//section[@id='reviewListContainer']//li[@itemprop='review'])[%s]//i[@class='rating-points box_split_review_05']",i)));
                System.out.println("Opinion " +i);
                System.out.println("By "+authorOpinion.getText());
                System.out.println("Published on "+dateOpinion.getText());
                System.out.println("Rated "+rateOpinion.getText());
                System.out.println(opinion.getText());
            }catch (NoSuchElementException ex){
                System.out.println("\nThe number of opinions shown in the first page was less than: "+i);
            }
        }
    }
}
