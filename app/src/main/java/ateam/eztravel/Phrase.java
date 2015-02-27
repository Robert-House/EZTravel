package ateam.eztravel;

/**
 * Created by Robert on 2/26/2015.
 */
public class Phrase
{
    private String _phrase;
    private String _phonetic;

    public Phrase(String phrase, String phonetic)
    {
        _phrase = phrase;
        _phonetic = phonetic;
    }

    public String GetPhrase()
    {
        return _phrase;
    }

    public String GetPhonetic()
    {
        return _phonetic;
    }
}
