package br.com.alura.aluraviagens.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataUtil {

    public static final String DIA_E_MES = "dd/MM";

    public static String periodoEmTexto(int dias) {
        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat(DIA_E_MES);
        Calendar dataIda = Calendar.getInstance();
        String dataFormatadaIda = formatoBrasileiro.format(dataIda.getTime());
        Calendar dataVolta = Calendar.getInstance();
        dataVolta.add(Calendar.DATE, dias);
        String dataFormatadaVolta = formatoBrasileiro.format(dataVolta.getTime());
        return dataFormatadaIda + " - " + dataFormatadaVolta + " de " + dataVolta.get(Calendar.YEAR);
    }
}
