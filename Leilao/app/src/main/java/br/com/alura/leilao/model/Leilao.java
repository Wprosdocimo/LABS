package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = 0.0;
    private double menorLance = 0.0;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public String getDescricao() {
        return descricao;
    }

    public void propoe(Lance lance){
        if (lanceNaoValido(lance)) return;
        lances.add(lance);
        double valorLance = lance.getValor();
        if (defineMaiorEMenorLanceParaPrimeiroLance(valorLance)) return;
        Collections.sort(lances);
        calculaMaiorLance(valorLance);
        calculaMenorLance(valorLance);
    }

    public boolean defineMaiorEMenorLanceParaPrimeiroLance(double valorLance) {
        if (lances.size() == 1){
            maiorLance = valorLance;
            menorLance = valorLance;
            return true;
        }
        return false;
    }

    private boolean lanceNaoValido(Lance lance) {
        double valorLance = lance.getValor();
        if (lanceForMenorQueUltimoLance(valorLance))
            throw new RuntimeException("Lance foi menor que maior lance");
        if (temLances()){
            Usuario usuarioNovo = lance.getUsuario();
            if (usuarioForMesmoQueDoUltimoLance(usuarioNovo))
                throw new RuntimeException("Mesmo usuario do ultimo lance");
            int lancesDoUsuario = 0;
            if (usuarioDeuCincoLances(usuarioNovo, lancesDoUsuario))
                throw  new RuntimeException("Usuario já deu cinco lances");
        }
        return false;
    }

    private boolean temLances() {
        return !lances.isEmpty();
    }

    private boolean usuarioDeuCincoLances(Usuario usuarioNovo, int lancesDoUsuario) {
        for (Lance l:
             lances) {
            Usuario usuarioExistente = l.getUsuario();
            if (usuarioExistente.equals(usuarioNovo)){
                lancesDoUsuario++;
                if (lancesDoUsuario == 5){
                    return true;
                }
            }

        }
        return false;
    }

    private boolean usuarioForMesmoQueDoUltimoLance(Usuario usuarioNovo) {
        Usuario ultimoUsuario = lances.get(0).getUsuario();
        if (usuarioNovo.equals(ultimoUsuario)){
            return true;
        }
        return false;
    }

    private boolean lanceForMenorQueUltimoLance(double valorLance) {
        if (maiorLance > valorLance){
            return true;
        }
        return false;
    }

    private void calculaMenorLance(double valorLance) {
        if (valorLance < menorLance) {
            menorLance = valorLance;
        }
    }

    private void calculaMaiorLance(double valorLance) {
        if (valorLance > maiorLance) {
            maiorLance = valorLance;
        }
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public List<Lance> treMaioresLances() {
        int quantidadeMaximaDeLances = lances.size();
        if (quantidadeMaximaDeLances > 3) {
            quantidadeMaximaDeLances = 3;
        }
        return lances.subList(0, quantidadeMaximaDeLances);
    }

    public int quantidadeLances() {
        return lances.size();
    }
}
