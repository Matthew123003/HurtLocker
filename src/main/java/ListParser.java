import com.sun.tools.javac.jvm.Items;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ListParser {
    private int errorCount = 0;

    public int getErrorCount() {
        return errorCount;
    }

    //Regex Patterns
    private String fieldPattern = "([A-Za-z0-9.]+)";
    private String stringSplitPattern = "([;:^@%*!])";
    private String itemSplitPattern = "(##)";

    //Regex compilers
    private Pattern fieldName = Pattern.compile(fieldPattern);
    private Pattern splitter = Pattern.compile(stringSplitPattern);
    private Pattern itemSplitter = Pattern.compile(itemSplitPattern);

    private ArrayList<Items> itemList = new ArrayList<>();

    public ArrayList<Items> getItemList(){
        return itemList;
    }

    public String[] stringSpilt(String string){
        return null;
    }

    public String itemSplit(){
        return null;
    }

}
