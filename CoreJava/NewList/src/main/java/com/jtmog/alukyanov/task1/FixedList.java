package com.jtmog.alukyanov.task1;

import java.util.*;

public class FixedList<E> implements List<E> {
    private final static int DEFAULT_CAPACITY = 10;

    private int maxSize = DEFAULT_CAPACITY;
    private E[] data;

    public FixedList() {
        data = (E[]) new Object[0];
    }

    public FixedList(int capacity) {
        data = (E[]) new Object[0];
        maxSize = capacity;
    }

    public FixedList(Collection<? extends E> c) {
        if (ensureCapacity(c.size())) {
            for (E element : c) {
                add(element);
            }
        }
    }

    private boolean ensureCapacity(int extraLength) {
        if ((data.length + extraLength) < maxSize + 1) {
            return true;
        }
        throw new ArrayElementsFullExeption();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (E element : data) {
            str.append(element + ", ");
        }
        str.replace(str.length() - 2, str.length(), "");
        str.append("]");
        return str.toString();
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        return data.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    private class MyListIterator<E> implements Iterator<E> {
        private int index = 0;
        E[] values;

        private MyListIterator(E[] values) {
            this.values = values;
        }

        @Override
        public boolean hasNext() {
            return index < values.length;
        }

        @Override
        public E next() {
            return values[index++];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator<E>(data);
    }

    @Override
    public boolean add(E e) {
        if (!ensureCapacity(1)) {
            throw new ArrayElementsFullExeption();
        }
        try {
            E[] temp = data;
            data = (E[]) new Object[temp.length + 1];
            System.arraycopy(temp, 0, data, 0, temp.length);
            data[data.length - 1] = e;
            return true;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void add(int index, E element) {
        if (ensureCapacity( 1)) {
            if (index < data.length) {
                E[] temp = data;
                data = (E[]) new Object[data.length + 1];
                System.arraycopy(temp, 0, data, 0, index);
                data[index] = element;
                System.arraycopy(temp, index, data, index + 1, temp.length - index);
            } else {
                if (index > data.length) {
                    throw new ArrayIndexOutOfBoundsException();
                }
            }
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (ensureCapacity(c.size())) {
            for (E element : c) {
                add(element);
            }
            return true;
        }
        throw new ArrayElementsFullExeption();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (ensureCapacity(c.size())) {
            if (index < data.length) {
                E[] temp = data;
                data = (E[]) new Object[temp.length + c.size()];
                System.arraycopy(temp, 0, data, 0, index);
                System.arraycopy(c.toArray(), 0, data, index, c.size());
                System.arraycopy(temp, index, data, index + c.size(), temp.length - index);
                return true;
            } else {
                if (index > data.length) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                addAll(c);
                return true;
            }
        }
        throw new ArrayElementsFullExeption();
    }

    @Override
    public boolean remove(Object o) {
        if (indexOf(o) != -1) {
            remove(indexOf(o));
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        try {
            data = (E[]) new Object[maxSize];
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return data[index];
    }

    @Override
    public E set(int index, E element) {
        E temp = data[index];
        data[index] = element;
        return temp;
    }

    @Override
    public E remove(int index) {
        E oldValue = data[index];
        try {
            E[] temp = data;
            data = (E[]) new Object[temp.length - 1];
            System.arraycopy(temp, 0, data, 0, index);
            System.arraycopy(temp, index + 1, data, index, (temp.length - index - 1));
            return oldValue;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        for (int i = 0; i < maxSize; i++) {
            if (data[i].equals(o)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E> E[] toArray(E[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
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
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
