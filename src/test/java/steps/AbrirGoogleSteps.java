package steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Quando;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import pages.AbrirGoogle;
import support.BaseSteps;

public class AbrirGoogleSteps extends BaseSteps {

    AbrirGoogle abrirGoogle = new AbrirGoogle(driver);

    @Dado("^Que acesso a pagina da\"([^\"]*)\"$")
    public void queAcessoAPagina(String texto) throws Throwable {
        abrirGoogle.openPagina(texto);
    }

    @E("^Preecher o campo usuario \"([^\"]*)\" com \"([^\"]*)\"$")
    public void euPreecherOCampoEmail(String email, String caracteres) throws Throwable {
        abrirGoogle.preencherCampoEmail(email, caracteres);
    }

    @E("^Preecher o campo senha \"([^\"]*)\" com \"([^\"]*)\"$")
    public void euPreecherOCampoSenha(String senha, String caracteres) throws Throwable {
        abrirGoogle.preencherCampoSenha(senha, caracteres);
    }

    @Entao("^Efetuo login com sucesso \"([^\"]*)\" com \"([^\"]*)\"$")
    public void efetuarLogin(String texto, String campo) throws Throwable {
        abrirGoogle.clicarEmLogar(texto);
        abrirGoogle.validarLogin(campo);
    }

}
