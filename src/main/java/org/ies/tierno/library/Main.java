package org.ies.tierno.library;

import org.ies.tierno.library.components.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var bookLendReader = new BookLendReader(scanner);
        var bookReader = new BookReader(scanner);
        var memberReader = new MemberReader(scanner);
        var libraryReader = new LibraryReader(scanner, bookReader, memberReader, bookLendReader);

        LibraryApp app = new LibraryApp(scanner, libraryReader);

        app.run();
    }
}