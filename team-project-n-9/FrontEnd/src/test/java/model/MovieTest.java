package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MovieTest {

    Movie testMovie;

    @BeforeEach
    public void init() {
        testMovie = new Movie("Batman","someone","bat","2008","nolan","bale");
    }


    @Test
    public void moviesShouldEqualWhenSame()
    {
        //given
        Movie sameMovie = new Movie("Batman","someone","bat","2008","nolan","bale");

        //when
        boolean expected = true;
        boolean actual = sameMovie.equals(testMovie);

        //then
        assertEquals(actual,expected);
    }

    @Test
    public void moviesShouldNotEqualWhenDifferent()
    {
        //given
        Movie sameMovie = new Movie("not Batman","not someone","not bat","not 2008","not nolan","not bale");

        //when
        boolean expected = false;
        boolean actual = sameMovie.equals(testMovie);

        //then
        assertEquals(expected,actual);
    }

    @Test
    public void moviesShouldNotEqualWhenAtLeastOneDifferent()
    {
        //given
        Movie differentMovie = new Movie("not Batman","someone","not bat","2008","not nolan","bale");

        //when
        boolean actual = differentMovie.equals(testMovie);

        //then
        assertFalse(actual);
    }

    @Test
    public void movieShouldHaveMethodToParseItselfAsCSVWithNewline()
    {
        //given
        // testMovie is defined

        //when
        String actual = testMovie.movieAsCsv();
        String expected = "\nBatman,someone,bat,2008,nolan,bale";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void movieWithSameTitleShouldPartialMatch()
    {
        //given
        Movie sameTitle = new Movie("Batman","","","","","");

        //when
        boolean actual = testMovie.partialMatch(sameTitle);

        //then
        assertTrue(actual);
    }

    @Test
    public void movieWithSamePCoShouldPartialMatch()
    {
        //given
        Movie samePCo = new Movie("","someone","","","","");

        //when
        boolean actual = testMovie.partialMatch(samePCo);

        //then
        assertTrue(actual);
    }

    @Test
    public void movieWithSameGenreShouldPartialMatch()
    {
        //given
        Movie sameGenre = new Movie("","","bat","","","");

        //when
        boolean actual = testMovie.partialMatch(sameGenre);

        //then
        assertTrue(actual);
    }

    @Test
    public void movieWithSameYearShouldPartialMatch()
    {
        //given
        Movie sameYear = new Movie("","","","2008","","");

        //when
        boolean actual = testMovie.partialMatch(sameYear);

        //then
        assertTrue(actual);
    }

    @Test
    public void movieWithSameDirectorShouldPartialMatch()
    {
        //given
        Movie sameDirector = new Movie("","","","","nolan","");

        //when
        boolean actual = testMovie.partialMatch(sameDirector);

        //then
        assertTrue(actual);
    }

    @Test
    public void movieWithSameActorShouldPartialMatch()
    {
        //given
        Movie someMatch = new Movie("","","bat","","","bale");

        //when
        boolean actual = testMovie.partialMatch(someMatch);

        //then
        assertTrue(actual);
    }

    @Test
    public void movieWithMultipleMatchingFieldShouldPartialMatch()
    {
        //given
        Movie sameActor = new Movie("Batman","","","2008","","bale");

        //when
        boolean actual = testMovie.partialMatch(sameActor);

        //then
        assertTrue(actual);
    }

    @Test
    public void movieWithOneDifferingFieldShouldNotPartialMatch()
    {
        //given
        Movie diff = new Movie("Batman","this is different","bat","2008","nolan","bale");

        //when
        boolean actual = testMovie.partialMatch(diff);

        //then
        assertFalse(actual);
    }

    @Test
    public void movieWithAllDifferingFieldsShouldNotPartialMatch()
    {
        //given
        Movie allDiff = new Movie("not Batman","someone","not bat","2008","not nolan","bale");

        //when
        boolean actual = testMovie.partialMatch(allDiff);

        //then
        assertFalse(actual);

    }

    @Test
    public void identicalMoviesShouldPartialMatch()
    {
        //given
        Movie sameMovie = testMovie;

        //when
        boolean actual = testMovie.partialMatch(sameMovie);

        //then
        assertTrue(actual);
    }
}
