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
    public void testItemCounter() throws Exception {
        try {
            String expected = "name:\tMilk\t\tseen: 6 times\n" +
                    "=============\t\t=============\n" +
                    "Price:\t3.23\t\tseen: 5 times\n" +
                    "-------------\t\t-------------\n" +
                    "Price:\t1.23\t\tseen: 1 times";
            listParser.createItem(Main.readRawDataToString());
            String actual = listParser.itemCounter("Milk");
            Assert.assertEquals(expected, actual);
        } catch (Exception e) {
            System.err.println(e);
        }
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

    @Test
    public void testGroceryListConstructor() throws Exception {
        String expected = "name:\tMilk\t\tseen: 6 times\n" +
                "=============\t\t=============\n" +
                "Price:\t3.23\t\tseen: 5 times\n" +
                "-------------\t\t-------------\n" +
                "Price:\t1.23\t\tseen: 1 times\n" +
                "\n" +
                "name:\tBread\t\tseen: 6 times\n" +
                "=============\t\t=============\n" +
                "Price:\t1.23\t\tseen: 6 times\n" +
                "-------------\t\t-------------\n" +
                "\n" +
                "name:\tCookies\t\tseen: 8 times\n" +
                "=============\t\t=============\n" +
                "Price:\t2.25\t\tseen: 8 times\n" +
                "-------------\t\t-------------\n" +
                "\n" +
                "name:\tApples\t\tseen: 4 times\n" +
                "=============\t\t=============\n" +
                "Price:\t0.25\t\tseen: 2 times\n" +
                "-------------\t\t-------------\n" +
                "Price:\t0.23\t\tseen: 2 times\n" +
                "\n" +
                "Errors\t\t\tseen: 4 times";
        listParser.createItem(Main.readRawDataToString());
        String actual = listParser.ListConstructor();
        Assert.assertEquals(expected, actual);
    }
}
