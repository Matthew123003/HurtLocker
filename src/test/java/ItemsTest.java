import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemsTest {
    Items items;
    @Before
    public void setUp() throws Exception {
        items = new Items("Milk", "3.23", "Food", "05/24/2016");
    }

    @Test
    public void getName() throws Exception {
        String expected = "Milk";
        String actual = items.getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setName() throws Exception {
        String expected = "Matt";
        items.setName("Matt");
        String actual = items.getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPrice() throws Exception {
        String expected = "3.23";
        String actual = items.getPrice();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setPrice() throws Exception {
        String expected = "7.50";
        items.setPrice("7.50");
        String actual = items.getPrice();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getType() throws Exception {
        String expected = "Food";
        String actual = items.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setType() throws Exception {
        String expected = "Plane";
        items.setType("Plane");
        String actual = items.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getExpiration() throws Exception {
        String expected = "05/24/2016";
        String actual = items.getExpirationDate();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setExpiration() throws Exception {
        String expected = "12/31/1999";
        items.setExpirationDate("12/31/1999");
        String actual = items.getExpirationDate();
        Assert.assertEquals(expected, actual);
    }

}
