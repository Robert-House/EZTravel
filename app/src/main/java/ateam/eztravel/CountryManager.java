package ateam.eztravel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * CountryManager.java
 *
 * Handles the creation and storage of country objects
 *
 * @author Robert House
 */
public class CountryManager
{
    List<Country> _countries;

    /**
     * Instantiates this manager and loads raw data
     *
     * @param countries InputStream containing raw country information
     */
    public CountryManager(InputStream countries)
    {
        _countries = new ArrayList<Country>();
        Load(countries);
    }

    /**
     * Get number of countries in this manager
     *
     * @return Number of countries
     */
    public int NumCountries()
    {
        return _countries.size();
    }

    /**
     * Search this manager for a country
     *
     * @param s Name of the country to search for
     * @return Country if found, null if not found
     */
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

    /**
     * Helper Method to handle exceptions involved with parsing XML files
     *
     * @param is InputStream containing raw country data
     */
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

    /**
     * Helper member that converts raw country data to country objects
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
        String text = null;
        int count = -1; // MUST be -1 because of the way the data is structured in XML
        Country tempCountry = null;

        // Grab first tag type
        int eventType = reader.getEventType();

        // While document != end
        while (eventType != XmlPullParser.END_DOCUMENT)
        {
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
                        // Create new category
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

                // This contains the text data inbetween each read. If you have comments on the
                // same line, the parser will pick it up. Put comments before or after a tag or
                // else you will parse garbage data
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
