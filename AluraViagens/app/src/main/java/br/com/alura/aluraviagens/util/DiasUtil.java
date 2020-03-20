package br.com.alura.aluraviagens.util;

public class DiasUtil {

    private static final String PLURAL = " dias";
    private static final String SINGULAR = " dia";

    public static String formataEmTexto(int quantidadeDeDias) {
        if(quantidadeDeDias > 1){
            return quantidadeDeDias + PLURAL;
        }
        return quantidadeDeDias + SINGULAR;
    }
}
