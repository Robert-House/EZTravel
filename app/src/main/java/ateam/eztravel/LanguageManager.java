package ateam.eztravel;

import android.util.Xml;

import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * LanguageManager.java
 *
 * This object handles the creation and storage of Language objects
 *
 * @author Robert House
 */
public class LanguageManager
{
    // hashmap containing the different languages
    private HashMap<Integer, Language> _languageTable;

    /**
     * Creates language objects from disk
     *
     * @param is InputStream containing raw language data
     */
    public LanguageManager(InputStream is)
    {
        _languageTable = new HashMap<Integer,Language>();
        Load(is);
    }

    /**
     * Allows for easy lookup of languages by string at the cost
     * of running the hashing function
     *
     * @param s Name of language to lookup
     * @return Language object if found/ null if not found
     */
    public Language GetLanguage(String s)
    {
        return _languageTable.get(Hash(s));
    }

    /**
     * Helper Method to handle exceptions involved with parsing XML files
     *
     * @param is InputStream containing raw language data
     */
    public void Load(InputStream is) {

        XmlPullParserFactory pullParserFactory;
        try
        {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser reader = pullParserFactory.newPullParser();

            reader.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
            reader.setInput(is, null);

            // Begin Parsing XML file
            ParseXML(reader);
        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Creates a unique ID based off the name of the language
     *
     * @param s Name of language
     * @return Integer ID of language
     */
    private int Hash(String s) {
        int hashkey = 0;

        for (int i = 0; i < s.length(); i++)
        {
            hashkey += s.charAt(i);
        }
        return hashkey / s.length();
    }

    /**
     * Helper member that converts raw language data to language objects
     *
     * @param reader
     * @throws XmlPullParserException
     * @throws IOException
     */
    private void ParseXML(XmlPullParser reader) throws XmlPullParserException,IOException
    {
        ///////////////////////
        // START PARSING XML //
        ///////////////////////
        String name = null;
        int count = -1; // MUST be -1 because of the way the data is structured in XML
        int hash = 0;
        String phrase = null;
        String phonetic = null;
        Language temp = null;
        String test = null;

        // Grab first tag type
        int eventType = reader.getEventType();

        // While document != end
        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            // Handle events
            switch(eventType)
            {
                case XmlPullParser.START_DOCUMENT:
                    break;

                case XmlPullParser.START_TAG:

                    // Get name of the next tag
                    name = reader.getName();

                    if (name.equals("category"))
                    {
                        // Increment count so we can add data to the next category
                        count++;
                    }
                    break;

                // Because of the way this parsing object handles tags, it was best to read
                // in the data into a separate string instead of calling reader.getText().
                // There was a bug that would skip ALL endtags, thus failing to instantiate
                // the objects.
                case XmlPullParser.END_TAG:

                    // Get name of the next tag
                    name = reader.getName();

                    if (name.equals("language"))
                    {
                        // Push language onto the hashmap
                        _languageTable.put(hash,temp);

                        // Reset category counter
                        count = -1;
                    }
                    else if (name.equals("name"))
                    {
                        // Create Language
                        temp = new Language(test);
                        // Generate hash
                        hash = Hash(test);
                    }
                    else if(name.equals("phrase"))
                    {
                        // Store phrase text
                        phrase = test;
                    }
                    else if (name.equals("phonetic"))
                    {
                        // Store phonetic text
                        phonetic = test;

                        // Add phrase and phonetic to a phrase object
                        temp.AddPhrase(phrase, phonetic, count);
                    }
                    else if (name.equals("category"))
                    {
                        temp.AddCategory(test);
                    }
                    break;

                // This contains the text data inbetween each read. If you have comments on the
                // same line, the parser will pick it up. Put comments before or after a tag or
                // else you will parse garbage data
                case XmlPullParser.TEXT:
                    test = reader.getText();
                    break;
            }

            // Get next event
            eventType = reader.next();
        }
        // End Read
    }
}