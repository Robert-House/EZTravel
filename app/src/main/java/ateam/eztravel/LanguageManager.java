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
 * Created by Robert on 2/26/2015.
 */
public class LanguageManager {
    private HashMap<Integer, Language> _languageTable;

    public LanguageManager(InputStream is)
    {
        _languageTable = new HashMap<Integer,Language>();
        Load(is);
    }

    public Language GetLanguage(String s) {
        return _languageTable.get(Hash(s));
    }

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

    private int Hash(String s) {
        int hashkey = 0;

        for (int i = 0; i < s.length(); i++)
        {
            hashkey += s.charAt(i);
        }
        return hashkey / s.length();
    }

    private void ParseXML(XmlPullParser reader) throws XmlPullParserException,IOException
    {
        ///////////////////////
        // START PARSING XML //
        ///////////////////////
        String name = null;
        int count = -1;
        int hash = 0;
        String phrase = null;
        String phonetic = null;
        Language temp = null;
        String test = null;

        int eventType = reader.getEventType();

        // While document != end
        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            switch(eventType)
            {
                case XmlPullParser.START_DOCUMENT:
                    break;

                case XmlPullParser.START_TAG:
                    name = reader.getName();
                    if (name.equals("category"))
                    {
                        count++;
                    }
                    break;

                case XmlPullParser.END_TAG:
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
                        phrase = test;
                    }
                    else if (name.equals("phonetic"))
                    {
                        phonetic = test;
                        temp.AddPhrase(phrase, phonetic, count);
                    }
                    else if (name.equals("category"))
                    {
                        temp.AddCategory(test);
                    }
                    break;
                case XmlPullParser.TEXT:
                    test = reader.getText();
                    break;
            }
            // Get next event
            eventType = reader.next();
        }
        // End Read
        // Close XML
    }
}