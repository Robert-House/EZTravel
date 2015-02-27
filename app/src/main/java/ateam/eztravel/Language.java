package ateam.eztravel;

import java.util.ArrayList;
import java.util.List;
import ateam.eztravel.PhraseNode;

/**
 * Created by Robert on 2/20/2015.
 */
public class Language
{
    private String _name;
    private List<PhraseNode> _categories = new ArrayList<PhraseNode>();

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
        PhraseNode temp = new PhraseNode();
        temp._category = category;
        temp._phrase = null;

        _categories.add(temp);
    }

    public PhraseNode GetCategory(int index)
    {
        return _categories.get(index);
    }

    public boolean AddPhrase(String phrase, String phonetic, int category)
    {
        // Grab head node
        PhraseNode temp = _categories.get(category);

        // Get to the end of the list
        while (temp._next != null)
        {
            temp = temp._next;
        }

        // Create new phrasenode
        PhraseNode newPhrase = new PhraseNode();
        newPhrase._category = temp._category;
        newPhrase._phrase = new Phrase(phrase, phonetic);
        newPhrase._next = null;

        // Assign phraseNode
        temp._next = newPhrase;

        return true;
    }
}
