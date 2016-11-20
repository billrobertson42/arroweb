package arro.adapter;

import org.junit.Test;

import javax.servlet.http.Cookie;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.Assert.*;

public class WrappedResponseCookieTest {

    public <T> void testAttribute(Consumer<T> setter, Supplier<T> getter, Supplier<Boolean> modified, Supplier<T> originalGetter, T oldValue, T newValue) {
        assertEquals(oldValue, originalGetter.get());
        assertEquals(oldValue, getter.get());
        assertFalse(modified.get());

        setter.accept(newValue);

        assertEquals(oldValue, originalGetter.get());
        assertTrue(modified.get());
        assertEquals(newValue, getter.get());

    }

    @Test
    public void testConstructWrapped() {
        Cookie original = new Cookie("name", "a");
        ResponseCookieAdapter subject = new ResponseCookieAdapter(original);
        assertEquals("name", subject.getName());
        assertSame(original, subject.getWrapped());
        assertTrue(subject.isExisting());
        assertFalse(subject.isDeleted());
        subject.delete();
        assertTrue(subject.isDeleted());
    }

    @Test
    public void testConstructNew() {
        ResponseCookieAdapter subject = new ResponseCookieAdapter("name", "value");
        assertEquals("name", subject.getName());
        assertEquals("value", subject.getValue());
        assertNotNull(subject.getWrapped());
        assertFalse(subject.isExisting());
        assertFalse(subject.isDeleted());
        subject.delete();
        assertTrue(subject.isDeleted());
    }

    @Test
    public void testValue() {
        Cookie original = new Cookie("name", "a");
        ResponseCookieAdapter subject = new ResponseCookieAdapter(original);
        testAttribute(subject::setValue, subject::getValue, subject::isModified, original::getValue, "a", "b");
    }

    @Test
    public void testHttpOnly() {
        Cookie original = new Cookie("name", "a");
        ResponseCookieAdapter subject = new ResponseCookieAdapter(original);
        testAttribute(subject::setHttpOnly, subject::isHttpOnly, subject::isModified, original::isHttpOnly, false, true);
    }

    @Test
    public void testVersion() {
        Cookie original = new Cookie("name", "a");
        ResponseCookieAdapter subject = new ResponseCookieAdapter(original);
        testAttribute(subject::setVersion, subject::getVersion, subject::isModified, original::getVersion, 0, 1);
    }

    @Test
    public void testSecure() {
        Cookie original = new Cookie("name", "a");
        ResponseCookieAdapter subject = new ResponseCookieAdapter(original);
        testAttribute(subject::setSecure, subject::getSecure, subject::isModified, original::getSecure, false, true);
    }

    @Test
    public void testPath() {
        Cookie original = new Cookie("name", "a");
        original.setPath("/foo");
        ResponseCookieAdapter subject = new ResponseCookieAdapter(original);
        testAttribute(subject::setPath, subject::getPath, subject::isModified, original::getPath, "/foo", "/bar");
    }

    @Test
    public void testMaxAge() {
        Cookie original = new Cookie("name", "a");
        ResponseCookieAdapter subject = new ResponseCookieAdapter(original);
        testAttribute(subject::setMaxAge, subject::getMaxAge, subject::isModified, original::getMaxAge, -1, 10);
    }

    @Test
    public void testComment() {
        Cookie original = new Cookie("name", "a");
        original.setComment("yo");
        ResponseCookieAdapter subject = new ResponseCookieAdapter(original);
        testAttribute(subject::setComment, subject::getComment, subject::isModified, original::getComment, "yo", "yo yo yo");
    }

    @Test
    public void testDomain() {
        Cookie original = new Cookie("name", "a");
        original.setDomain("example.com");
        ResponseCookieAdapter subject = new ResponseCookieAdapter(original);
        testAttribute(subject::setDomain, subject::getDomain, subject::isModified, original::getDomain, "example.com", "www.example.com");
    }

}