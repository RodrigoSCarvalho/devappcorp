package com.devappcorp.projetodevappcorp.services.validations;

import com.devappcorp.projetodevappcorp.entities.Author;

public class Validations {
    public static boolean validaEmail(String email) {
        int arroba = 0, ponto = 0;
        boolean tamanho = false;
        String dominio = email.split("@")[1];

        if (email != null || email != "") {
            if (email.length() > 3) {
                tamanho = true;
            }
            for (int i = 0; i < email.length(); i++) {
                if ('@' == email.charAt(i)) {
                    arroba += 1;
                }
                if ('.' == dominio.charAt(i)) {
                    ponto += 1;
                }
            }
            return tamanho == true && arroba == 1 && ponto >= 1;
        }
        return false;
    }

    public static boolean validaOrcid(String orcid) {
        if (orcid != null) {
            return orcid.length() >= 15 && orcid.length() <= 19;
        }
        return false;
    }
}
