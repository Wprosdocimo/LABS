package br.com.alura.aluraviagens.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MoedaUtil {

    private static final String PORTUGUES = "pt";
    private static final String BRASIL = "br";

    public static String formataParaBrasileiro(BigDecimal valor) {
        NumberFormat formatoBrasileiro = DecimalFormat.getCurrencyInstance(
                new Locale(PORTUGUES, BRASIL));
        return formatoBrasileiro.format(valor);
    }
}
