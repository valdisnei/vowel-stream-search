package com.rnaufal.vowel.stream.search.finder;

import com.rnaufal.vowel.stream.search.stream.Stream;

import java.util.*;

/**
 * Created by rnaufal on 17/11/16.
 */
public class VowelStreamFinder {

    public static final char NOT_FOUND = ' ';

    private static final String VOWELS = "AEIOUaeiou";

    private VowelStreamFinder() {

    }

    public static char firstChar(Stream input) {
        if (input == null) {
            throw new IllegalArgumentException("input cannot be null");
        }

        Map<Character, Boolean> vowelsByOccurrence = new LinkedHashMap<>();
        Map<Character, List<Character>> predecessors = new HashMap<>();

        char previousChar = ' ';
        while (input.hasNext()) {
            char currentChar = input.getNext();

            registerOccurrenceIfVowel(vowelsByOccurrence, currentChar);
            computePredecessor(predecessors, currentChar, previousChar);

            previousChar = currentChar;
        }

        return searchVowel(vowelsByOccurrence, predecessors);
    }

    private static Character searchVowel(Map<Character, Boolean> vowelsByOccurrence,
                                         Map<Character, List<Character>> predecessors) {
        for (Map.Entry<Character, Boolean> vowelOccurrence : vowelsByOccurrence.entrySet()) {
            if (!vowelOccurrence.getValue()) {
                continue;
            }

            for (Character vowelPredecessor : predecessors.get(vowelOccurrence.getKey())) {
                if (isVowel(vowelPredecessor)) {
                    continue;
                }

                for (Character consonantPredecessor : predecessors.get(vowelPredecessor)) {
                    if (isVowel(consonantPredecessor)) {
                        return vowelOccurrence.getKey();
                    }
                }
            }
        }
        return NOT_FOUND;
    }

    private static void computePredecessor(Map<Character, List<Character>> predecessors,
                                           char currentChar,
                                           char previousChar) {
        List<Character> characters = predecessors.get(currentChar);
        if (characters == null) {
            characters = new ArrayList<>();
            predecessors.put(currentChar, characters);
        }
        if (previousChar != ' ') {
            characters.add(previousChar);
        }
    }

    private static void registerOccurrenceIfVowel(Map<Character, Boolean> vowelsByOccurrence,
                                                  char currentChar) {
        if (isVowel(currentChar)) {
            if (vowelsByOccurrence.get(currentChar) == null) {
                vowelsByOccurrence.put(currentChar, true);
            } else {
                vowelsByOccurrence.put(currentChar, false);
            }
        }
    }

    private static boolean isVowel(char c) {
        return VOWELS.indexOf(c) != -1;
    }
}
