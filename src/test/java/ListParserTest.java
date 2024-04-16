import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ListParserTest {
    ListParser listParser;

    @Before
    public void setUp() throws Exception {
        listParser = new ListParser();
    }

    @Test
    public void stringSplitterTest () throws Exception {
        String expected = "Milk";
        String actual = listParser.stringSplitter(Main.readRawDataToString())[1];
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stringSplitterTestToFailure () {
        String expected = "";
        String actual = listParser.stringSplitter("naME:;price:1.23;type:Food;expiration:1/02/2016##")[1];
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void nameCleanup() throws Exception {
        String expected = "Milk";
        String actual = listParser.nameCleanup("milk");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createItem() throws Exception {
        String expectedName = "Milk";
        String expectedPrice = "3.23";
        listParser.createItem("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##");
        String actualName = listParser.getItemList().get(0).getName();
        String actualPrice = listParser.getItemList().get(0).getPrice();
        Assert.assertEquals(expectedName, actualName);
        Assert.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void addItemToList() throws Exception {
        int expectedLength = 1;
        listParser.addItemToList("Milk", "1.23", "Food", "1/25/2016");
        int actualLength = listParser.getItemList().size();
        Assert.assertEquals(expectedLength, actualLength);
    }

    @Test
    public void findItemField() throws Exception {
        String expected = "Milk";
        String actual = listParser.findItemField(Main.readRawDataToString(), 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findItemFieldExceptionErrorCounter() throws Exception {
        int errorsBeforeTheCounter = 1;
        String expected = "Bread";
        String actual = listParser.findItemField("naME:;price:1.23;type:Food;expiration:1/02/2016##", 1);
        int errorsAfterTheCounter = listParser.getErrorCount();
        Assert.assertEquals(errorsBeforeTheCounter, errorsAfterTheCounter);
    }
}
