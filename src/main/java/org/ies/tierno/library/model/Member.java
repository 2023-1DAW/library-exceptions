package org.ies.tierno.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
    private String nif;
    private String name;
    private String surname;
    private int memberNumber;
    private int zipCode;
}
