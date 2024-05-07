package org.ies.tierno.library.exceptions;

public class MemberNotFoundException extends Exception {
    private final String nif;

    public MemberNotFoundException(String nif) {
        this.nif = nif;
    }

    public String getNif() {
        return nif;
    }

    @Override
    public String toString() {
        return "MemberNotFoundException{" +
                "nif='" + nif + '\'' +
                '}';
    }
}
