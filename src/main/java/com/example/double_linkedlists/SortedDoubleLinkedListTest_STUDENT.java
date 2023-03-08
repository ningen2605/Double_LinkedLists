package com.example.double_linkedlists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedListTest_STUDENT {

    SortedDoubleLinkedList<Games> sortedLinkedGames;
    GamesComparator comparator;

    public Games game1 = new Games("Super Mario Brothers", "Two", 1999);
    public Games game2 = new Games("Dwarf Fortress", "DF", 1997);
    public Games game3 = new Games("The Legend of Zelda", "Breath of The Wild", 2017);
    public Games game4 = new Games("The Elder Scrolls", "Oblivion", 1995);
    public Games game5 = new Games("Gundam", "Evolutions", 2022);

    @Before
    public void setUp() throws Exception {
        comparator = new GamesComparator();
        sortedLinkedGames = new SortedDoubleLinkedList<>(comparator);
    }

    @After
    public void tearDown() throws Exception {
        comparator = null;
        sortedLinkedGames = null;
    }

    @Test
    public void testAddToEnd() {
        try {
            sortedLinkedGames.addToEnd(game5);
            assertTrue("Did not throw an UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw an UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }

    @Test
    public void testAddToFront() {
        try {
            sortedLinkedGames.addToFront(game1);
            assertTrue("Did not throw an UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw an UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }

    @Test
    public void testIteratorSuccessfulNext() {
        sortedLinkedGames.add(game1);
        sortedLinkedGames.add(game2);
        sortedLinkedGames.add(game3);
        sortedLinkedGames.add(game4);
        ListIterator<Games> iterator = sortedLinkedGames.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(game1, iterator.next());
        assertEquals(game4, iterator.next());
        assertEquals(game2, iterator.next());
        assertEquals(true, iterator.hasNext());
    }


    @Test
    public void testIteratorSuccessfulPrevious() {
        sortedLinkedGames.add(game1);
        sortedLinkedGames.add(game2);
        sortedLinkedGames.add(game3);
        sortedLinkedGames.add(game4);
        ListIterator<Games> iterator = sortedLinkedGames.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(game1, iterator.next());
        assertEquals(game4, iterator.next());
        assertEquals(game2, iterator.next());
        assertEquals(true, iterator.hasPrevious());
        assertEquals(game2, iterator.previous());
        assertEquals(game4, iterator.previous());
        assertEquals(game1, iterator.previous());

    }


    @Test
    public void testIteratorNoSuchElementException() {
        sortedLinkedGames.add(game5);
        sortedLinkedGames.add(game3);
        sortedLinkedGames.add(game2);
        sortedLinkedGames.add(game4);
        // Band game order = game4, game2, game3, game5
        ListIterator<Games> iterator = sortedLinkedGames.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals(game4, iterator.next());
        assertEquals(game2, iterator.next());
        assertEquals(game3, iterator.next());
        assertEquals(game5, iterator.next());
        assertEquals(false, iterator.hasNext());

        try {
            iterator.next();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }
    }

    @Test
    public void testIteratorUnsupportedOperationException() {
        sortedLinkedGames.add(game5);
        sortedLinkedGames.add(game3);
        sortedLinkedGames.add(game2);
        sortedLinkedGames.add(game4);
        ListIterator<Games> iterator = sortedLinkedGames.iterator();

        try {
            iterator.remove();
            assertTrue("Did not throw a UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw a UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }

    @Test
    public void testAddGame() {
        // Game order = game1, game4, game2, game3, game5
        sortedLinkedGames.add(game1);
        sortedLinkedGames.add(game2);
        sortedLinkedGames.add(game3);
        assertEquals(game1, sortedLinkedGames.getFirst());
        assertEquals(game3, sortedLinkedGames.getLast());
        sortedLinkedGames.add(game4);
        sortedLinkedGames.add(game5);
        assertEquals(game1, sortedLinkedGames.getFirst());
        assertEquals(game5, sortedLinkedGames.getLast());

        assertEquals(game5, sortedLinkedGames.retrieveLastElement());
        assertEquals(game3, sortedLinkedGames.getLast());
    }

    @Test
    public void testRemoveFirstGame() {
        // Game order = game1, game4, game2, game3, game5
        sortedLinkedGames.add(game3);
        sortedLinkedGames.add(game4);
        assertEquals(game4, sortedLinkedGames.getFirst());
        assertEquals(game3, sortedLinkedGames.getLast());
        sortedLinkedGames.add(game1);
        assertEquals(game1, sortedLinkedGames.getFirst());
        // remove the first element
        sortedLinkedGames.remove(game1, comparator);
        assertEquals(game4, sortedLinkedGames.getFirst());
    }

    @Test
    public void testRemoveEndGame() {
        // Band order = game1, game4, game2, game3, game5
        sortedLinkedGames.add(game2);
        sortedLinkedGames.add(game4);
        assertEquals(game4, sortedLinkedGames.getFirst());
        assertEquals(game2, sortedLinkedGames.getLast());
        sortedLinkedGames.add(game3);
        assertEquals(game3, sortedLinkedGames.getLast());
        // remove last element with remove method
        sortedLinkedGames.remove(game3, comparator);
        assertEquals(game2, sortedLinkedGames.getLast());
    }

    @Test
    public void testRemoveMiddleGame() {
        // game order = game1, game4, game2, game3, game5
        sortedLinkedGames.add(game2);
        sortedLinkedGames.add(game4);
        assertEquals(game4, sortedLinkedGames.getFirst());
        assertEquals(game2, sortedLinkedGames.getLast());
        sortedLinkedGames.add(game3);
        assertEquals(game4, sortedLinkedGames.getFirst());
        assertEquals(game3, sortedLinkedGames.getLast());
        assertEquals(3, sortedLinkedGames.getSize());
        // remove from middle of list
        sortedLinkedGames.remove(game2, comparator);
        assertEquals(game4, sortedLinkedGames.getFirst());
        assertEquals(game3, sortedLinkedGames.getLast());
        assertEquals(2, sortedLinkedGames.getSize());
    }

    private class GamesComparator implements Comparator<Games> {

        @Override
        public int compare(Games first, Games second) {
            return first.getGameName().compareTo(second.getGameName());
        }
    }

    private class Games {
        String gameName;
        String title;
        int year;

        public Games(String gameName, String title, int year) {
            this.gameName = gameName;
            this.title = title;
            this.year = year;
        }

        public String getGameName() {
            return gameName;
        }

        public String getTitle() {
            return title;
        }

        public int getYear() {
            return year;
        }

        public String toString() {
            return (getGameName() + " " + getTitle() + " " + getYear());
        }

    }

}