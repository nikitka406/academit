package ru.academits.popov.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getCount() {
        return count;
    }

    private void checkListEmpty() {
        if (count == 0) {
            throw new NoSuchElementException("Односвязный список пуст.");
        }
    }

    private void checkIndex(int index, int capacity) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть больше или равен нуля. Индекс = " + index);
        }

        if (index >= capacity) {
            throw new IndexOutOfBoundsException("Индекс = " + index +
                    " должен быть строго меньше максимально допустимого значения = " + capacity);
        }
    }

    public T getFirst() {
        checkListEmpty();

        return head.getData();
    }

    private ListItem<T> getItemByIndex(int index) {
        checkIndex(index, count);
        ListItem<T> item = head;

        for (int i = 0; i < index; ++i) {
            item = item.getNext();
        }

        return item;
    }

    public T getDataByIndex(int index) {
        return getItemByIndex(index).getData();
    }

    public T setDataByIndex(int index, T data) {
        ListItem<T> item = getItemByIndex(index);

        T oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public T deleteFirst() {
        checkListEmpty();

        T deletedData = head.getData();
        head = head.getNext();
        count--;

        return deletedData;
    }

    public T deleteByIndex(int index) {
        checkIndex(index, count);

        if (index == 0) {
            return deleteFirst();
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);
        ListItem<T> currentItem = previousItem.getNext();

        T deletedData = currentItem.getData();
        previousItem.setNext(currentItem.getNext());
        count--;

        return deletedData;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void addByIndex(int index, T data) {
        checkIndex(index, count + 1);

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);
        ListItem<T> currentItem = previousItem.getNext();

        previousItem.setNext(new ListItem<>(data, currentItem));

        count++;
    }

    public boolean deleteByData(T data) {
        if (head == null) {
            return false;
        }

        if (head.getData().equals(data)) {
            deleteFirst();
            return true;
        }

        for (ListItem<T> item = head.getNext(), previousItem = head; item != null; previousItem = item, item = item.getNext()) {
            if (item.getData().equals(data)) {
                previousItem.setNext(item.getNext());
                count--;
                return true;
            }
        }

        return false;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> result = new SinglyLinkedList<>();

        if (head == null) {
            return result;
        }

        ListItem<T> copyListItem = new ListItem<>(head.getData());

        result.head = copyListItem;
        ListItem<T> item = head.getNext();

        while (item != null) {
            ListItem<T> nextCopyListItem = new ListItem<>(item.getData());
            copyListItem.setNext(nextCopyListItem);
            copyListItem = nextCopyListItem;

            item = item.getNext();
        }

        result.count = count;

        return result;
    }

    public void reverse() {
        if (count <= 1) {
            return;
        }

        for (ListItem<T> currentItem = head.getNext(), previousItem = head, newNextItem = null;
             currentItem != null;
             previousItem = currentItem, currentItem = currentItem.getNext()) {
            previousItem.setNext(newNextItem);
            newNextItem = previousItem;

            if (currentItem.getNext() == null) {
                head = currentItem;
                head.setNext(previousItem);
                return;
            }
        }
    }


    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        ListItem<T> item = head;

        while (item.getNext() != null) {
            stringBuilder.append(item.getData());
            stringBuilder.append(", ");
            item = item.getNext();
        }

        stringBuilder.append(item.getData());
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
