package com.rnaufal.vowel.stream.search.main;

import com.rnaufal.vowel.stream.search.finder.VowelStreamFinder;
import com.rnaufal.vowel.stream.search.stream.CharSequenceStream;

/**
 * Created by rnaufal on 17/11/16.
 */
public class MainApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("\nInvocar comando da seguinte forma: ./gradlew build run -PappArgs={stream}. Substituir {stream} pela stream desejada.");
            System.exit(0);
        }
        char foundChar = VowelStreamFinder.firstChar(new CharSequenceStream(args[0]));
        if (foundChar != VowelStreamFinder.NOT_FOUND) {
            System.out.println("\nOutput: " + foundChar);
        } else {
            System.out.println("\nVogal nao localizada. Por favor, tente outra stream.");
        }
    }
}
