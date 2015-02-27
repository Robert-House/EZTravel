package ateam.eztravel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Robert on 2/27/2015.
 */
public class CountryManager
{
    int _numCountries;

    public CountryManager()
    {
        _numCountries = 0;
    }

    public int NumCountries()
    {
        return _numCountries;
    }

    private void Load(InputStream is)
    {
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
