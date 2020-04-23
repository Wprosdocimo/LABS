package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import br.com.alura.leilao.R;
import br.com.alura.leilao.api.retrofit.client.TesteWebClient;
import br.com.alura.leilao.formatter.FormatadorDeMoeda;
import br.com.alura.leilao.model.Leilao;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class ListaLeilaoTelaTest {
    private static final String ERRO_BANCO_DE_DADOS_NAO_FOI_LIMPO = "Banco de dados não foi limpo!!!";
    private static final String ERRO_LEILAO_NAO_FOI_SALVO = "Leilão não foi salvo: ";
    private final TesteWebClient webClient = new TesteWebClient();

    @Rule
    public ActivityTestRule<ListaLeilaoActivity> activity =
            new ActivityTestRule<>(ListaLeilaoActivity.class, true, false);
    private FormatadorDeMoeda formatadorDeMoeda = new FormatadorDeMoeda();;

    @Before
    public void setup() throws IOException {
        limpaBancoDeDadosDaApi();
    }

    @Test
    public void deve_AparecerUmLeilao_QuandoCarregarUmLeilaoNaApi() throws IOException {
        tentaSalvarLeilaoNaAPI(new Leilao("Carro"));

        activity.launchActivity(new Intent());

        onView(allOf(
                withText("Carro"),
                withId(R.id.item_leilao_descricao)))
                .check(matches(isDisplayed()));

        final String formatoEsperado = formatadorDeMoeda.formata(0.00);

        onView(allOf(
                withText(formatoEsperado),
                withId(R.id.item_leilao_maior_lance)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void deve_AparecerDoisLeiloes_QuandoCarregaDoisLeiloesNaApi() throws IOException {
        tentaSalvarLeilaoNaAPI(new Leilao("Carro"), new Leilao("Computador"));

        activity.launchActivity(new Intent());


//        onView(allOf(
//                withText("Carro"),
//                withId(R.id.item_leilao_descricao)))
//                .check(matches(isDisplayed()));
//
//        final String formatoEsperadoCarro = formatadorDeMoeda.formata(0.00);
//        onView(allOf(
//                withText(formatoEsperadoCarro),
//                withId(R.id.item_leilao_maior_lance)))
//                .check(matches(isDisplayed()));
//
//        onView(allOf(
//                withText("Computador"),
//                withId(R.id.item_leilao_descricao)))
//                .check(matches(isDisplayed()));
//
//        final String formatoEsperadoComputador = formatadorDeMoeda.formata(0.00);
//
//        onView(allOf(
//                withText(formatoEsperadoComputador),
//                withId(R.id.item_leilao_maior_lance)))
//                .check(matches(isDisplayed()));

        onView(withId(R.id.lista_leilao_recyclerview))
                .check(matches(apareceLeilao(0,"Carro", 0.00)));
        onView(withId(R.id.lista_leilao_recyclerview))
                .check(matches(apareceLeilao(1,"Computador", 0.00)));

    }

    private Matcher<? super View> apareceLeilao(final int posicao, final String descricaoEsperada, final double maiorLanceEsperado) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(RecyclerView item) {
                View viewDoViewHolder = item.findViewHolderForAdapterPosition(posicao).itemView;
                TextView textViewDescricao = viewDoViewHolder.findViewById(R.id.item_leilao_descricao);
                final boolean temDescricaoEsperada = textViewDescricao.getText()
                        .toString().equals(descricaoEsperada);
                final TextView textViewMaiorLance = viewDoViewHolder.findViewById(R.id.item_leilao_maior_lance);
                final FormatadorDeMoeda formatador = new FormatadorDeMoeda();
                final boolean temMaiorLanceEsperado = textViewMaiorLance.getText().toString().equals(formatador.formata(maiorLanceEsperado));
                return temDescricaoEsperada && temMaiorLanceEsperado;
            }
        };
    }

    private void tentaSalvarLeilaoNaAPI(Leilao... leiloes) throws IOException {
        for (Leilao leilao :
                leiloes) {
            Leilao leilaoSalvo = webClient.salva(leilao);
            if (leilaoSalvo == null) {
                Assert.fail(ERRO_LEILAO_NAO_FOI_SALVO + leilao.getDescricao());
            }
        }

    }


    @After
    public void tearDown() throws IOException {
        limpaBancoDeDadosDaApi();
    }

    private void limpaBancoDeDadosDaApi() throws IOException {
        boolean bancoDeDadosNaoFoiLimpo = !webClient.limpaBancoDeDados();
        if (bancoDeDadosNaoFoiLimpo) {
            Assert.fail(ERRO_BANCO_DE_DADOS_NAO_FOI_LIMPO);
        }
    }
}