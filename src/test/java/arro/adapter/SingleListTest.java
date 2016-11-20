package arro.adapter;

import org.junit.After;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.*;

public class SingleListTest {

    private List<String> subject = new SingleList("foo");

    @After
    public void after() {
        assertEquals(1, subject.size());
        assertEquals("foo", subject.get(0));
    }

    @Test
    public void generalTest() {
        assertEquals(1, subject.size());
        assertEquals("foo", subject.get(0));
        assertFalse(subject.isEmpty());
        assertTrue(subject.contains("foo"));
        assertFalse(subject.contains("bar"));

        Iterator<String> it = subject.iterator();
        assertTrue(it.hasNext());
        assertEquals("foo", it.next());
        assertFalse(it.hasNext());

        Object[] a = subject.toArray();
        assertNotNull(a);
        assertEquals(1, a.length);
        assertEquals("foo", a[0]);

        String[] as = subject.toArray(new String[1]);
        assertNotNull(as);
        assertEquals(1, as.length);
        assertEquals("foo", as[0]);

        as = subject.toArray(new String[0]);
        assertNotNull(as);
        assertEquals(1, as.length);
        assertEquals("foo", as[0]);

        as = subject.toArray(new String[2]);
        assertNotNull(as);
        assertEquals(2, as.length);
        assertEquals("foo", as[0]);
        assertNull(as[1]);

        List<String> other = new ArrayList<>();
        other.add("foo");
        assertTrue(subject.containsAll(other));
        other.add("foo");
        assertTrue(subject.containsAll(other));
        other.add("bar");
        assertFalse(subject.containsAll(other));

        assertEquals(0, subject.indexOf("foo"));
        assertEquals(0, subject.lastIndexOf("foo"));

        assertEquals(-1, subject.indexOf("bar"));
        assertEquals(-1, subject.lastIndexOf("bar"));

        assertEquals(subject, subject);
        assertEquals(subject, Arrays.asList("foo"));
        assertNotEquals(subject, Collections.emptyList());
        assertNotEquals(subject, "bob");
        assertEquals("foo".hashCode(), subject.hashCode());

        assertTrue(subject.subList(0, 0).isEmpty());
        assertEquals(subject, subject.subList(0, 1));

        ListIterator<String> li = subject.listIterator();
        assertEquals(1, li.nextIndex());
        assertEquals(-1, li.previousIndex());
        assertTrue(li.hasNext());
        assertFalse(li.hasPrevious());
        assertEquals("foo", li.next());
        assertEquals(1, li.nextIndex());
        assertEquals(0, li.previousIndex());
        assertFalse(li.hasNext());
        assertTrue(li.hasPrevious());
        assertEquals("foo", li.previous());

        li = subject.listIterator(0);
        assertEquals(1, li.nextIndex());
        assertEquals(-1, li.previousIndex());
        assertTrue(li.hasNext());
        assertFalse(li.hasPrevious());
        assertEquals("foo", li.next());
        assertEquals(1, li.nextIndex());
        assertEquals(0, li.previousIndex());
        assertFalse(li.hasNext());
        assertTrue(li.hasPrevious());
        assertEquals("foo", li.previous());

    }

    @Test(expected = NoSuchElementException.class)
    public void testIterateTooFar() {
        Iterator<String> it = subject.iterator();
        assertEquals("foo", it.next());
        it.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        subject.add("bar");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
        subject.remove("bar");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddAll() {
        List<String> l = Arrays.asList("bar");
        subject.addAll(l);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddAllIndexed() {
        List<String> l = Arrays.asList("bar", "bar");
        subject.addAll(1, l);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveAll() {
        subject.removeAll(Arrays.asList("foo"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRetainAll() {
        subject.retainAll(Arrays.asList("foo"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testClear() {
        subject.clear();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOOB() {
        subject.get(1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveIndexed() {
        subject.remove(0);
    }

    @Test(expected =  UnsupportedOperationException.class)
    public void testSet() {
        subject.set(0, "bar");
    }

    @Test(expected =  UnsupportedOperationException.class)
    public void testAddIndexed() {
        subject.add(1, "bar");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void sublistOOBRight() {
        subject.subList(0, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void sublistOOBLeft() {
        subject.subList(-1, 0);
    }

    @Test(expected = NoSuchElementException.class)
    public void testLINextOOB() {
        ListIterator<String> li = subject.listIterator();
        li.next();
        li.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testLIPreviousOOB() {
        ListIterator<String> li = subject.listIterator();
        li.previous();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLIRemove() {
        ListIterator<String> li = subject.listIterator();
        li.remove();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLIAdd() {
        ListIterator<String> li = subject.listIterator();
        li.add("bar");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLISet() {
        ListIterator<String> li = subject.listIterator();
        li.set("bar");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLIOOB() {
        ListIterator<String> li = subject.listIterator(1);
    }
}