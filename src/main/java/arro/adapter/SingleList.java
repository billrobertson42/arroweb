package arro.adapter;

import java.util.*;

public class SingleList implements List<String> {
    private final String value;

    public SingleList(String value) {
        this.value = value;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return value.equals(o);
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int idx = 0;

            @Override
            public boolean hasNext() {
                return idx == 0;
            }

            @Override
            public String next() {
                if (idx == 0) {
                    ++idx;
                    return value;
                }
                throw new NoSuchElementException("" + idx);
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[]{value};
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length == 0) {
            return (T[]) new String[]{value};
        }
        a[0] = (T) value;
        if (a.length > 1)
            a[1] = null;
        return a;
    }

    @Override
    public boolean add(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c != null) {
            Iterator<?> it = c.iterator();
            return it.hasNext() && value.equals(it.next()) && !it.hasNext();
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get(int index) {
        if (index == 0) {
            return value;
        }
        throw new IndexOutOfBoundsException("" + index);
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        return value.equals(o) ? 0 : -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return indexOf(o);
    }

    @Override
    public ListIterator<String> listIterator() {
        return new ListIterator<String>() {
            private int idx = 0;

            @Override
            public boolean hasNext() {
                return idx == 0;
            }

            @Override
            public String next() {
                if (idx == 0) {
                    ++idx;
                    return value;
                }
                throw new NoSuchElementException("" + idx);
            }

            @Override
            public boolean hasPrevious() {
                return idx == 1;
            }

            @Override
            public String previous() {
                if (idx == 1) {
                    --idx;
                    return value;
                }
                throw new NoSuchElementException("" + idx);
            }

            @Override
            public int nextIndex() {
                return 1;
            }

            @Override
            public int previousIndex() {
                return idx == 0 ? -1 : 0;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(String s) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(String s) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        if (index == 0) {
            return listIterator();
        }
        throw new IndexOutOfBoundsException("" + index);
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        if (fromIndex == 0 && toIndex == 1) {
            return this;
        }
        throw new IndexOutOfBoundsException(fromIndex + "," + toIndex);
    }
}
