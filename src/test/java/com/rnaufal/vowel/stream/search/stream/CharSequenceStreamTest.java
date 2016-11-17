package com.rnaufal.vowel.stream.search.stream;

import com.rnaufal.vowel.stream.search.exception.InvalidStreamStateException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by rnaufal on 17/11/16.
 */
public class CharSequenceStreamTest {

    @Test
    public void shouldNotifyMoreCharactersToProcess() {
        Stream stream = new CharSequenceStream("abcdef");
        assertThat(stream.hasNext(), is(equalTo(true)));
        assertThat(stream.getNext(), is(equalTo('a')));
    }

    @Test
    public void shouldNotifyNoMoreCharactersToProcess() {
        Stream stream = new CharSequenceStream("testefgh");
        while (stream.hasNext()) {
            stream.getNext();
        }
        assertThat(stream.hasNext(), is(equalTo(false)));
    }

    @Test
    public void shouldProcessEachCharacterOfStream() {
        String source = "exercise";
        char[] values = source.toCharArray();

        Stream stream = new CharSequenceStream(source);
        int i = 0;
        while (stream.hasNext()) {
            char actual = stream.getNext();
            assertThat(actual, is(equalTo(values[i++])));
        }
        assertThat(stream.hasNext(), is(equalTo(false)));
    }

    @Test(expected = InvalidStreamStateException.class)
    public void shouldThrowExceptionWhenInvalidStreamState() {
        Stream stream = new CharSequenceStream("abc");
        for (int i = 0; i < 5; i++) {
            stream.getNext();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInvalidStream() {
        new CharSequenceStream(null);
    }
}