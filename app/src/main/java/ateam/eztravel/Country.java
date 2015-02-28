package ateam.eztravel;

import java.util.ArrayList;
import java.util.List;

/**
 * Country.java
 *
 * This object encapsulates the data representing countries in our database
 *
 * @author Robert House
 */
public class Country
{
    private String _name;
    private List<String> _languages;
    private List<Category<Fact>> _facts;

    /**
     * Instantiate and name country
     *
     * @param name Name of the country
     */
    public Country(String name)
    {
        _languages = new ArrayList<String>();
        _facts = new ArrayList<Category<Fact>>();
        _name = name;
    }

    /**
     * Get name of the country
     *
     * @return Name of country
     */
    public String GetName()
    {
        return _name;
    }

    /**
     * Add fact to this country
     *
     * @param s String containing factoid data
     * @param category Category to add this factoid to
     */
    public void AddFact(String s, int category)
    {
        // Add fact to this country
        _facts.get(category).Add(new Fact(s));
    }

    /**
     * Add category data container to this country
     *
     * @param s Name of the category
     */
    public void AddCategory(String s)
    {
        // Create new category
        _facts.add(new Category<Fact>(s));
    }

    /**
     * Add language to this country
     *
     * @param s Name of the language
     */
    public void AddLanguage(String s)
    {
        _languages.add(s);
    }

}
