package ateam.eztravel;

/**
 * Fact.java
 *
 * This object encapsulates text information that describes a factoid for the
 * country object it is assigned to.
 *
 * @author Robert House
 */
public class Fact
{
    // The string data gets stored here
    private String _fact;

    /**
     * Store data directly on instantiation
     * @param s String containing factoid data
     */
    public Fact(String s)
    {
        _fact = s;
    }

    /**
     * Retrieve fact information
     * @return String containing factoid
     */
    public String GetFact()
    {
        return _fact;
    }
}
