import org.apache.commons.io.IOUtils;

public class Main {

    public static String readRawDataToString() throws Exception{
        ClassLoader classLoader = Main.class.getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        ListParser lp = new ListParser();
        String output = (new Main()).readRawDataToString();
        lp.createItem(output);
        System.out.println(lp.ListConstructor());



    }
}
