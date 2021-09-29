package ru.academits.popov.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getCount() {
        return count;
    }

    private void checkListEmpty() {
        if (count == 0) {
            throw new NullPointerException("Односвязный список пуст.");
        }
    }

    private void checkIndex(int index) {
        if (index >= count && index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы списка, index = " + index);
        }
    }

    public T getFirst() {
        checkListEmpty();

        return head.getData();
    }

    private ListItem<T> getElementByIndex(int index) {
        checkIndex(index);
        ListItem<T> element = head;

        for (int i = 0; i < index - 1; ++i) {
            element = element.getNext();
        }

        return element;
    }

    public T getDataByIndex(int index) {
        return getElementByIndex(index).getData();
    }

    public T setDataByIndex(int index, T data) {
        //todo не рабоатет
        ListItem<T> element = getElementByIndex(index);

        T oldData = element.getData();
        element.setData(data);
        count++;

        return oldData;
    }

    public T deleteFirstElement() {
        T oldData = head.getData();
        head = head.getNext();
        count--;

        return oldData;
    }

    public T deleteByIndex(int index) {
        if (index == 0) {
            deleteFirstElement();
        }

        if (index == count - 1 && count > 1) {
            getElementByIndex(index - 1).setNext(null);
        }

        ListItem<T> element = getElementByIndex(index);
        ListItem<T> prev = getElementByIndex(index - 1);

        T oldData = element.getData();
        prev.setNext(element.getNext());
        count--;

        return oldData;
    }

    public void addFirst(T newHead) {
        if (head == null){
            head = new ListItem<>(newHead);
            head.setNext(null);
        }

        ListItem<T> oldHead = new ListItem<>(head.getData(), head.getNext());
        head.setData(newHead);
        head.setNext(oldHead);
        count++;
    }

    public void addByIndex(int index, T newData) {
        if (index == 0) {
            addFirst(newData);
        }

        if (index == count) {
            ListItem<T> end = getElementByIndex(count - 1);
            end.setData(newData);
            end.setNext(null);
        }

        ListItem<T> prev = getElementByIndex(index - 1);
        ListItem<T> oldElement = prev.getNext();

        prev.getNext().setData(newData);
        prev.getNext().setNext(oldElement);

        count++;
    }

    public boolean deleteByValues(T data) {
        boolean isDeleted = false;
        if (head.getData() == data) {
            deleteFirstElement();
        }

        for (ListItem<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            if (p.getData() == data) {
                prev.setNext(p.getNext());
                isDeleted = true;
                count--;
            }
        }

        return isDeleted;
    }

//    public SinglyLinkedList<T> copy(){
//        SinglyLinkedList<T> copyList = new SinglyLinkedList<>();
//
//        if(head == null){
//            return copyList;
//        }
//
//        copyList.head = new ListItem<>(head.getData());
//        ListItem<T> nextElement = head.getNext();
//        head.setNext(nextElement);
//        ListItem<T> item = head.getNext();
//
//        while (item != null){
//            nextElement.
//        }
//    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        ListItem<T> element = head;

        for (int i = 0; i < count; ++i) {
            stringBuilder.append(element.getData());
            stringBuilder.append(", ");
            element = element.getNext();
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
