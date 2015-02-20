package ateam.eztravel;

import java.util.List;

/**
 * Created by Robert on 2/20/2015.
 */
public class Language
{
    private String _name;
    List<String> _phrases;

    public Language(String languageName)
    {
        _name = languageName;
    }

    public String getName()
    {
        return _name;
    }
}
