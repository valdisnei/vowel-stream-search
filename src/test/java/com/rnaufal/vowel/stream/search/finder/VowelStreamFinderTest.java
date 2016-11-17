package com.rnaufal.vowel.stream.search.finder;

import com.rnaufal.vowel.stream.search.stream.CharSequenceStream;
import com.rnaufal.vowel.stream.search.stream.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by rnaufal on 17/11/16.
 */
@RunWith(Parameterized.class)
public class VowelStreamFinderTest {

    @Parameterized.Parameters(name = "input:{0} - output:{1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new CharSequenceStream("aAbBABacafe"), 'e'},
                {new CharSequenceStream("AAAAAaaaa"), ' '},
                {new CharSequenceStream("afe"), 'e'},
                {new CharSequenceStream("cafe"), 'e'},
                {new CharSequenceStream("aoBABacaee"), 'A'},
                {new CharSequenceStream("   abacate"), 'e'},
                {new CharSequenceStream("teleferico"), 'i'},
                {new CharSequenceStream("ttttttt"), ' '},
                {new CharSequenceStream("telefone"), 'o'},
        });
    }

    private final Stream input;

    private final char expected;

    public VowelStreamFinderTest(Stream input, char expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void shouldFindVowelOnStream() {
        char actual = VowelStreamFinder.firstChar(input);
        assertThat(actual, is(equalTo(expected)));
    }
}