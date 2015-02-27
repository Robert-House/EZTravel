package ateam.eztravel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 2/26/2015.
 */
public class Country
{
    private String _name;
    private List<Integer> _languages;
    //private List<Fact> _facts;

    public Country(String name)
    {
        _languages = new ArrayList<Integer>();
        _name = name;
    }

    public void RegisterLanguage(int id)
    {
        _languages.add(id);
    }

}
