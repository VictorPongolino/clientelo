package br.com.alura.clientelo.reports.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class DinheiroUtils {

    private static final NumberFormat PT_BR_CURRENCY = NumberFormat.getInstance(new Locale("pt", "BR"));
    public static String formatarDinheiroBrasileiro(BigDecimal dinheiro) {
        return PT_BR_CURRENCY.format(dinheiro);
    }
}
