package ateam.eztravel;

import java.io.InputStream;

/**
 * Created by Robert on 2/27/2015.
 */
public class Model
{
    LanguageManager _languageManager;
    //CountryManager _countryManager;

    public Model(InputStream languages, InputStream countries)
    {
        _languageManager = new LanguageManager(languages);
        //_countryManager = new CountryManager(countries);
    }

    public void RegisterLanguages()
    {
        for(int i = 0; i < 10; i++)
        {

        }
    }

}
