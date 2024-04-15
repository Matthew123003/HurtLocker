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
    private String lineSplitPattern = "(##)";

    //Regex compilers
    private Pattern fieldName = Pattern.compile(fieldPattern);
    private Pattern splitter = Pattern.compile(stringSplitPattern);
    private Pattern itemSplitter = Pattern.compile(lineSplitPattern);

    private ArrayList<Items> itemList = new ArrayList<>();

    public ArrayList<Items> getItemList(){
        return itemList;
    }

    public String[] itemSplit(String string){
        String[] splitItem = string.split(lineSplitPattern);
        return splitItem;
    }

    public String toSingleLine(String[] strings){
        StringBuilder sb = new StringBuilder();
        for(String str : strings){
            sb.append(str).append("\n");
        }

        return sb.toString();
    }

    public String[] stringSpilt(String string){
        String[] splitStr = string.split(stringSplitPattern);
        return splitStr;
    }

}
