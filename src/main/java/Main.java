import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static String readRawDataToString() throws Exception{
        ClassLoader classLoader = Main.class.getClassLoader();
        String result = IOUtils.toString(Objects.requireNonNull(classLoader.getResourceAsStream("RawData.txt")));
        return result;
    }

    public static void main(String[] args) throws Exception{
        ListParser lp = new ListParser();
        String output = (new Main()).readRawDataToString();



    }
}
