package com.rnaufal.vowel.stream.search.main;

import com.rnaufal.vowel.stream.search.finder.VowelStreamFinder;
import com.rnaufal.vowel.stream.search.stream.CharSequenceStream;

/**
 * Created by rnaufal on 17/11/16.
 */
public class MainApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso correto deve ser: comando [stream]");
            System.exit(0);
        }
        char foundChar = VowelStreamFinder.firstChar(new CharSequenceStream(args[0]));
        if (foundChar != VowelStreamFinder.NOT_FOUND) {
            System.out.println("Output: " + foundChar);
        } else {
            System.out.println("Carecter nao localizado. Tente outra stream.");
        }
    }
}
