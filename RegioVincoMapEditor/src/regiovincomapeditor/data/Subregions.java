
package regiovincomapeditor.data;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author li zhao
 */
public class Subregions 
{
    public static final String DEFAULT_NAME = "NULL";
    public static final String DEFAULT_CAPITAL = "NULL";
    public static final String DEFAULT_LEADER = "NULL";
    public static final int DEFAULT_RED = 0;
    public static final int DEFAULT_GREEN = 0;
    public static final int DEFAULT_BLUE = 0;
    
    final StringProperty Names;
    final StringProperty Capitals;
    final StringProperty Leaders;
    
    final IntegerProperty Red;
    final IntegerProperty Green;
    final IntegerProperty Blue;
    
    
    public Subregions()
    {
        Names = new SimpleStringProperty(DEFAULT_NAME);
        Capitals = new SimpleStringProperty(DEFAULT_CAPITAL);
        Leaders = new SimpleStringProperty(DEFAULT_LEADER);
        Red = new SimpleIntegerProperty(DEFAULT_RED);
        Green = new SimpleIntegerProperty(DEFAULT_GREEN);
        Blue = new SimpleIntegerProperty(DEFAULT_BLUE);
    }
    
    public Subregions(String initNames, String initCapitals, String initLeader, int red, int green, int blue)
    {
        this();
        Names.set(initNames);
        Capitals.set(initCapitals);
        Leaders.set(initLeader);
        Red.set(red);
        Green.set(green);
        Blue.set(blue);
    }
    
    
    public String getName() {return Names.get();}
    public void setName(String value) {Names.set(value);}
    public StringProperty NameProperty() {return Names;}
    
    public String getCapital() {return Capitals.get();}
    public void setCapital(String value) {Capitals.set(value);}
    public StringProperty capitalProperty() {return Capitals;}
    
    
    public String getLeader() {return Leaders.get();}
    public void setLeader(String value) {Leaders.set(value);}
    public StringProperty LeaderProperty() {return Leaders;}
    
    public int getRed() {return Red.get();}
    public void setRed(int value){Red.set(value);}
    public IntegerProperty RedPreperty(){return Red;}
    
    public int getGreen(){return Green.get();}
    public void setGreen(int value){Green.set(value);}
    public IntegerProperty GreenPreperty(){return Green;}
    
    public int getBlue(){return Blue.get();}
    public void setBlue(int value){Blue.set(value);}
    public IntegerProperty BluePreperty(){return Blue;}
    

    
    public void reset()
    {
        setName(DEFAULT_NAME);
        setCapital(DEFAULT_CAPITAL);
        setLeader(DEFAULT_LEADER);
        setRed(DEFAULT_RED);
        setGreen(DEFAULT_GREEN);
        setBlue(DEFAULT_BLUE);
        
    }
    

}
