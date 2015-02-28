package ateam.eztravel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 2/20/2015.
 */
public class Language
{
    private String _name;
    private List<Category<Phrase>> _categories = new ArrayList<Category<Phrase>>();

    public Language(String languageName)
    {
        _name = languageName;
    }

    public String getName()
    {
        return _name;
    }

    public void AddCategory(String category)
    {
        Category<Phrase> temp = new Category<Phrase>(category);

        _categories.add(temp);
    }

    public Category<Phrase> GetCategory(int index)
    {
        return _categories.get(index);
    }

    public boolean AddPhrase(String phrase, String phonetic, int category)
    {
        // Grab category
        Category<Phrase> temp = _categories.get(category);

        // Construct Phrase
        Phrase p = new Phrase(phrase, phonetic);

        // Add phrase to the list
        temp.Add(p);

        return true;
    }
}
