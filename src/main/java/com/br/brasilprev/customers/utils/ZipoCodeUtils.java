package com.br.brasilprev.customers.utils;

public class ZipoCodeUtils {
    public static boolean valid(String zipCode) {
        if (!zipCode.matches("\\d{8}")) {
            return false;
        }
        return true;
    }
}
