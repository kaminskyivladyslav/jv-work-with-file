package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int START_POSITION = 0;
    public static final int INFO_ABOUT_POSITION = 1;
    public static final int NEXT_POSITION = 2;
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(result(readFile(fromFileName)), toFileName);
    }

    private static String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value)
                        .append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
        return builder.toString();
    }

    private static String result(String readFile) {
        int supply = START_POSITION;
        int buy = START_POSITION;
        String[] info = readFile.split("\\W+");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = START_POSITION; i < info.length; i += NEXT_POSITION) {
            if (info[i].equals(SUPPLY)) {
                supply += Integer.parseInt(info[i + INFO_ABOUT_POSITION]);
            }
            if (info[i].equals(BUY)) {
                buy += Integer.parseInt(info[i + INFO_ABOUT_POSITION]);
            }
        }
        int result = supply - buy;
        stringBuilder.append(SUPPLY)
                .append(",")
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY)
                .append(",")
                .append(buy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(",")
                .append(result)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private static void writeToFile(String result, String toFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
