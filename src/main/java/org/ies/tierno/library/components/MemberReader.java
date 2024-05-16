package org.ies.tierno.library.components;

import lombok.AllArgsConstructor;
import org.ies.tierno.library.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

@AllArgsConstructor
public class MemberReader implements Reader<Member> {

    private static final Logger log = LoggerFactory.getLogger(MemberReader.class);
    private final Scanner scanner;

    @Override
    public Member read() {
        log.info("Introduce los datos de un socio:");
        log.info("NIF:");
        String nif = scanner.nextLine();
        log.info("Nombre:");
        String name = scanner.nextLine();
        log.info("Apellidos:");
        String surname = scanner.nextLine();
        int memberNumber = readMemberNumber();
        int zipCode = readZipCode();

        return new Member(nif, name, surname, memberNumber, zipCode);
    }

    private int readMemberNumber() {
        log.info("Número de socio:");
        Integer memberNumber = null;
        while(memberNumber == null) {
            try {
                memberNumber = scanner.nextInt();
            } catch (InputMismatchException e) {
                log.info("Valor inválido, debe ser un número entero");
            } finally {
                scanner.nextLine();
            }
        }

        return memberNumber;
    }

    private int readZipCode() {
        log.info("Código postal:");
        Integer zipCode = null;
        while(zipCode == null) {
            try {
                zipCode = scanner.nextInt();
            } catch (InputMismatchException e) {
                log.info("Valor inválido, debe ser un número entero");
            } finally {
                scanner.nextLine();
            }
        }

        return zipCode;
    }
}
