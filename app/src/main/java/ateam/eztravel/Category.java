package ateam.eztravel;

/**
 * Created by Robert on 2/28/2015.
 */
public class Category<T>
{
    private String _categoryName;
    private Node<T> _head;

    public Category(String name)
    {
        _categoryName = name;
    }

    public boolean Add(T t)
    {
        Node temp = _head;

        // Find end of linked list
        while (temp._next != null)
        {
            temp = temp._next;
        }

        // Add new node
        Node newNode = new Node<T>();
        newNode._data = t;

        // Add to end of list
        temp._next = newNode;

        return true;
    }
}
