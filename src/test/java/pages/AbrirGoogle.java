package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import support.DriverQA;

public class AbrirGoogle {

    private DriverQA driver;



    public AbrirGoogle(DriverQA stepDriver) {
        driver = stepDriver;
    }

    public void openPagina(String texto) {
        driver.openURL("https://now.westwing.com.br/customer/account/login/");
        String xpath = String.format("//button[@aria-label=\"Close Message\"]", texto);
        driver.waitElementAll(xpath, "xpath");
        driver.click(xpath, "xpath");
    }


    public void clicarEmLogar(String texto) {
        String xpath = String.format("//button[@name=\"login\"]", texto);
        driver.click(xpath, "xpath");
    }

    public void preencherCampoEmail(String campo, String valor) throws InterruptedException {
        String xpath = String.format("//input[@id=\"LoginForm_email\"]", campo);
        driver.waitElementAll(xpath, "xpath");
        driver.sendKeys(valor, xpath, "xpath");
    }

    public void preencherCampoSenha(String campo, String valor) throws InterruptedException {
        String xpath = String.format("//input[@id=\"LoginForm_password\"]", campo);
        driver.waitElementAll(xpath, "xpath");
        driver.sendKeys(valor, xpath, "xpath");
    }

    public void validarLogin(String campo) {
        String xpath = String.format("(//span[@class=\"l-header__bottom-item-title\"])[1]", campo);
        driver.waitElementAll(xpath, "xpath");
    }

}

