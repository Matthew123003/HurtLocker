import javax.management.AttributeNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
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

    //Regex compiler
    private Pattern fieldName = Pattern.compile(fieldPattern);

    private ArrayList<Items> itemList = new ArrayList<>();

    public ArrayList<Items> getItemList(){
        return itemList;
    }

    public String[] stringSplitter (String string) {
        String[] splitStrings = string.split(stringSplitPattern);
        return splitStrings;
    }

    public String itemCounter(String string) {
        StringBuilder itemChart = new StringBuilder();
        LinkedHashMap<String, Integer> priceCounter = new LinkedHashMap<>();
        int stringCount = 0;
        for(Items item : itemList) {
            if(item.getName().equals(string)) {
                stringCount ++;
                if(priceCounter.containsKey(item.getPrice())) {
                    priceCounter.put(item.getPrice(), priceCounter.get(item.getPrice())+1);
                } else if (item.getPrice().equals("blank")) {
                    stringCount--;
                } else {
                    priceCounter.put(item.getPrice(), 1);
                }
            }

        }
        itemChart.append("name:\t" + string + "\t\t" + "seen: " + stringCount + " times\n");
        itemChart.append("=============\t\t=============\n");
        int counter  = 0;
        for(String key: priceCounter.keySet()) {
            itemChart.append("Price:\t" + key + "\t\t" + "seen: " + priceCounter.get(key) + " times");
            if(counter == 0) {
                itemChart.append("\n-------------\t\t-------------\n");
            }
            counter ++;
        }

        return itemChart.toString();
    }

    public String nameCleanup(String string) {
        if(string.toLowerCase().charAt(0) == 'm') {
            return "Milk";
        } else if (string.toLowerCase().charAt(0) == 'a') {
            return "Apples";
        } else if (string.toLowerCase().charAt(0) == 'b') {
            return "Bread";
        } else if (string.toLowerCase().charAt(0) == 'c') {
            return "Cookies";
        } else {
            return string;
        }
    }

    public String findItemField(String string, int index) throws AttributeNotFoundException {
        try {
            Matcher m = fieldName.matcher((stringSplitter(string)[index]));
            if(m.find()) {
                return nameCleanup(m.group());
            } else {
                errorCount ++;
                throw new AttributeNotFoundException();
            }
        } catch (AttributeNotFoundException e) {
            System.err.println("An Error was thrown: " + e);
            return "blank";
        }
    }

    public void createItem(String input) throws AttributeNotFoundException {
        String[] itemArray = input.split(lineSplitPattern);
        for(int i = 0; i < itemArray.length; i ++) {
            addItemToList(findItemField(itemArray[i], 1), findItemField(itemArray[i],3), findItemField(itemArray[i], 5), findItemField(itemArray[i], 7));
        }
    }

    public void addItemToList(String name, String price, String type, String date) {
        itemList.add(new Items(name, price, type, date));
    }

    public String ListConstructor() {
        StringBuilder sb = new StringBuilder();
        sb.append(itemCounter("Milk"));
        sb.append("\n\n");
        sb.append(itemCounter("Bread"));
        sb.append("\n");
        sb.append(itemCounter("Cookies"));
        sb.append("\n");
        sb.append(itemCounter("Apples"));
        sb.append("\n\n");
        sb.append("Errors\t\t\tseen: " + errorCount + " times");
        return sb.toString();
    }

}
