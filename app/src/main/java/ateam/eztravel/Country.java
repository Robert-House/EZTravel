package ateam.eztravel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 2/26/2015.
 */
public class Country
{
    private String _name;
    private List<String> _languages;
    private List<Category<Fact>> _facts;

    public Country(String name)
    {
        _languages = new ArrayList<String>();
        _name = name;
    }

    public String GetName()
    {
        return _name;
    }

    public void AddFact(String s, int category)
    {
        // Add fact to this country
        _facts.get(category).Add(new Fact(s));
    }

    public void AddCategory(String s)
    {
        // Create new category
        _facts.add(new Category<Fact>(s));
    }

    public void AddLanguage(String s)
    {
        _languages.add(s);
    }

}
