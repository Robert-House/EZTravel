package ateam.eztravel;

/**
 * Category.java
 *
 * This object acts as a linked list to the data fragments grouped
 * within
 *
 * @author Robert House
 */
public class Category<T>
{
    private String _categoryName;
    private Node<T> _head = null;

    /**
     * Create a category to group data fragments
     *
     * @param name Name of the category to create
     */
    public Category(String name)
    {
        _categoryName = name;
    }

    /**
     * Add data fragment to this category
     *
     * @param t Template data fragment
     * @return If operation was successful
     */
    public boolean Add(T t)
    {
        Node temp = _head;

        // If list is empty
        if (temp == null)
        {
            Node<T> newNode = new Node<T>();
            newNode._data = t;
            _head = newNode;

            return true;
        }

        // Find end of linked list
        while (temp._next != null)
        {
            temp = temp._next;
        }

        // Add new node
        Node<T> newNode = new Node<T>();
        newNode._data = t;

        // Add to end of list
        temp._next = newNode;

        return true;
    }
}
