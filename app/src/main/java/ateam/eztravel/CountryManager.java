package ateam.eztravel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 2/27/2015.
 */
public class CountryManager
{
    int _numCountries;
    List<Country> _countries;

    public CountryManager(InputStream countries)
    {
        _numCountries = 0;
        _countries = new ArrayList<Country>();
        Load(countries);
    }

    public int NumCountries()
    {
        return _numCountries;
    }

    public Country FindCountry(String s)
    {
        for (int i = 0; i < _countries.size(); i++)
        {
            if (_countries.get(i).GetName() == s)
            {
                return _countries.get(i);
            }
        }

        // Country not found
        return null;
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
        String text = null;
        int count = -1;
        Country tempCountry = null;

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
                        // Add to language list
                        tempCountry.AddLanguage(text);
                    }
                    else if (name.equals("fact"))
                    {
                        // Parse in fact
                        tempCountry.AddFact(text, count);
                    }
                    else if (name.equals("category"))
                    {
                        // create new category
                        tempCountry.AddCategory(text);

                    }
                    else if (name.equals("name"))
                    {
                        // Create country with name
                        tempCountry = new Country(text);
                    }
                    else if(name.equals("country"))
                    {
                        // Add country to list
                        _countries.add(tempCountry);

                        // Reset Counter
                        count = -1;
                    }
                    break;
                case XmlPullParser.TEXT:
                    text = reader.getText();
                    break;
            }
            // Get next event
            eventType = reader.next();
        }
        // End Read
    }
}
