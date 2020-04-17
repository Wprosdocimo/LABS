package br.com.alura.leilao.model;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import br.com.alura.leilao.builder.LeilaoBuilder;
import br.com.alura.leilao.exceptions.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exceptions.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exceptions.UsuarioJaDeuCincoLancesException;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LeilaoTest {

    private static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario ALEX = new Usuario("Alex");

    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {
        String descricaoDevolvida = CONSOLE.getDescricao();

//        assertEquals("Console", descricaoDevolvida);
        assertThat(descricaoDevolvida, is(equalTo("Console")));
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(ALEX, 200.0));
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

//        assertEquals(200.0, maiorLanceDevolvido, DELTA);
        assertThat(maiorLanceDevolvido, closeTo(200.0, DELTA));

    }

    @Test
    public void deve_DevolverMaiorLance_QuandorecebeMaisDeUmValorEmOrdemCrescente() {
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 200.0));
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(ALEX, 200.0));
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(200.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandorecebeMaisDeUmValorEmOrdemCrescente() {
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 200.0));
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExados3Lances() {
        CONSOLE.propoe(new Lance(ALEX, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 300.0));
        CONSOLE.propoe(new Lance(ALEX, 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

//        assertEquals(3, tresMaioresLancesDevolvidos.size());
//        assertThat(tresMaioresLancesDevolvidos, hasSize(equalTo(3)));
//        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
//        assertThat(tresMaioresLancesDevolvidos, hasItem(new Lance(ALEX, 400.0)));
//        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
//        assertEquals(200.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);

//        assertThat(tresMaioresLancesDevolvidos, contains(
//                new Lance(ALEX, 400.0),
//                new Lance(new Usuario("Fran"), 300.0),
//                new Lance(ALEX, 200.0)
//        ));

        assertThat(tresMaioresLancesDevolvidos,
                both(Matchers.<Lance>hasSize(3))
                        .and(contains(
                                new Lance(ALEX, 400.0),
                                new Lance(new Usuario("Fran"), 300.0),
                                new Lance(ALEX, 200.0)
                        )));
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances() {
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(ALEX, 200.0));
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasDoisLances() {
        CONSOLE.propoe(new Lance(ALEX, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 300.0));
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoMaisDeTresLances() {
        CONSOLE.propoe(new Lance(ALEX, 200.0));
        final Usuario FRAN = new Usuario("Fran");
        CONSOLE.propoe(new Lance(FRAN, 300.0));
        CONSOLE.propoe(new Lance(ALEX, 400.0));
        CONSOLE.propoe(new Lance(FRAN, 500.0));
        List<Lance> tresMaioresLancesDevolvidosPara4Lances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosPara4Lances.size());
        assertEquals(500.0, tresMaioresLancesDevolvidosPara4Lances.get(0).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidosPara4Lances.get(1).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidosPara4Lances.get(2).getValor(), DELTA);

        CONSOLE.propoe(new Lance(ALEX, 700.0));
        List<Lance> tresMaioresLancesDevolvidosPara5Lances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosPara5Lances.size());
        assertEquals(700.0, tresMaioresLancesDevolvidosPara5Lances.get(0).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesDevolvidosPara5Lances.get(1).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidosPara5Lances.get(2).getValor(), DELTA);

    }


    @Test
    public void deve_DevolverValor0ParaMaiorLance_QuandoNaoTiverLance() {
        final double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverValor0ParaMenorLance_QuandoNaoTiverLance() {
        final double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test(expected = LanceMenorQueUltimoLanceException.class)
    public void deve_LancarException_QuandoReceberLanceMenorQueMaiorLance() {
        CONSOLE.propoe(new Lance(ALEX, 500.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 400.0));

    }

    @Test(expected = LanceSeguidoDoMesmoUsuarioException.class)
    public void naoDeve_AdicionarLanceDoMesmoUsuario_QuandoMesmoUsuarioDoUltimoLance() {
        CONSOLE.propoe(new Lance(ALEX, 500.0));
        CONSOLE.propoe(new Lance(new Usuario("Alex"), 600.0));
    }

    @Test(expected = UsuarioJaDeuCincoLancesException.class)
    public void naoDeve_AdicionarLanceDoUsuario_SeJaTiverFeitoCincoLances() {
        CONSOLE.propoe(new Lance(ALEX, 200.0));
        final Usuario FRAN = new Usuario("Fran");
        CONSOLE.propoe(new Lance(FRAN, 300.0));
        CONSOLE.propoe(new Lance(ALEX, 400.0));
        CONSOLE.propoe(new Lance(FRAN, 500.0));
        CONSOLE.propoe(new Lance(ALEX, 600.0));
        CONSOLE.propoe(new Lance(FRAN, 700.0));
        CONSOLE.propoe(new Lance(ALEX, 800.0));
        CONSOLE.propoe(new Lance(FRAN, 900.0));
        CONSOLE.propoe(new Lance(ALEX, 1000.0));
        CONSOLE.propoe(new Lance(FRAN, 1100.0));
        CONSOLE.propoe(new Lance(ALEX, 1200.0));
    }

    @Test(expected = UsuarioJaDeuCincoLancesException.class)
    public void naoDeve_AdicionarLance_QuandoUsuarioDerCincoLances() {
        final Usuario FRAN = new Usuario("Fran");

        final Leilao console = new LeilaoBuilder("Console")
                .lance(ALEX, 100.0)
                .lance(FRAN, 200.0)
                .lance(ALEX, 300.0)
                .lance(FRAN, 400.0)
                .lance(ALEX, 500.0)
                .lance(FRAN, 600.0)
                .lance(ALEX, 700.0)
                .lance(FRAN, 800.0)
                .lance(ALEX, 900.0)
                .lance(FRAN, 1000.0)
                .lance(ALEX, 1100.0)
                .build();


    }


}