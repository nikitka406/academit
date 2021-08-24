package ru.academits.popov.myarraylist;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modifyCount;

    public MyArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Размер списка не может быть ментьше нуляб size = " + size);
        }
        //noinspection unchecked
        items = (E[]) new Object[size];
        this.size = size;
        modifyCount = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Искомый элемент не должен быть null, o = " + o);
        }

        for (E element : items) {
            if (element.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Добавляемый элемент не должен равняться null, e = " + e);
        }

        ensureCapacity(size + 1);

        items[size] = e;
        size++;
        modifyCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = (int) o;

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Индекс выходит за границы списка, index = " + index);
        }

        if (size - 1 - index >= 0) System.arraycopy(items, index + 1, items, index, size - 1 - index);

        size--;
        modifyCount++;

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        trimToSize(0);
        modifyCount++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Индекс выходит за границы списка, index = " + index);
        }

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Индекс выходит за границы списка, index = " + index);
        }

        E oldElement = items[index];
        items[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Индекс выходит за границы списка, index = " + index);
        }

        E oldElement = items[index];
        ensureCapacity(size+1);
        size++;
        modifyCount++;
        items[index] = element;

        for (int i = index+1; i < size; ++i){
            E temp = items[i];
            items[i] = oldElement;
            oldElement = temp;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Индекс выходит за границы списка, index = " + index);
        }

        E oldElement = items[index];

        if (size - 1 - index >= 0) System.arraycopy(items, index + 1, items, index, size - 1 - index);

        size--;
        modifyCount++;

        return oldElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (items[i] == o) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (items[i] == o) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    public void ensureCapacity(int capacity) {
        if (capacity < items.length) {
            return;
        }

        items = Arrays.copyOf(items, capacity);
    }

    public void trimToSize(int size) {
        if (size == this.size) {
            return;
        }

        if (size > this.size) {
            throw new IllegalArgumentException("Обрезаемый размер должен быть меньше существующего размера, size = " + size);
        }

        items = Arrays.copyOf(items, size);
        this.size = 0;
    }
}
