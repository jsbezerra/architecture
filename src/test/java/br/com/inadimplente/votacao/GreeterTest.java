package br.com.inadimplente.votacao;

import br.com.inadimplente.access.Authenticator;
import br.com.inadimplente.example.Greeter;
import br.com.inadimplente.kernel.AbstractView;
import br.com.inadimplente.kernel.producer.EntityManagerProducer;
import br.com.inadimplente.mail.MailSender;
import br.com.inadimplente.restaurante.Restaurante;
import br.com.inadimplente.usuario.Usuario;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static  org.junit.Assert.*;

import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class GreeterTest {

    @Inject
    Greeter greeter;

    @Inject
    private VotacaoBusiness votacaoBusiness;

    @Inject VotacaoDAO votacaoDAO;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(Greeter.class)
                .addPackage(AbstractView.class.getPackage())
                .addPackage(VotacaoBusiness.class.getPackage())
                .addPackage(Authenticator.class.getPackage())
                .addPackage(Restaurante.class.getPackage())
                .addPackage(Votacao.class.getPackage())
                .addPackage(Usuario.class.getPackage())
                .addPackage(MailSender.class.getPackage())
                .addPackage(EntityManagerProducer.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void should_create_greeting() {
        assertNotNull(votacaoBusiness);
        assertNotNull(votacaoDAO);
        assertNotNull(votacaoBusiness);
        //String votacao = votacaoBusiness.bullshit();
        //assertNull(votacao);
        //votacaoBusiness.createNovaVotacao();

        //votacaoBusiness.createNovaVotacao();
        //Votacao votacao1 = votacaoBusiness.getVotacaoAtual();
        //votacaoBusiness.createNovaVotacao();
        //Votacao votacao2 = votacaoBusiness.getVotacaoAtual();
        //Assert.assertEquals(votacao1, votacao2);
        //Assert.assertEquals("Hello, Earthling!", greeter.createGreeting("Earthling"));
        //greeter.greet(System.out, "Earthling");
    }
}