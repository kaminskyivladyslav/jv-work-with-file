package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = getBuilder(fromFileName);
        int supply = 0;
        int buy = 0;
        String[] info = builder.toString().split("[,\r\n]+");
        if ((info.length % 2) == 0) {
        for (int i = 0; i < info.length; i += 2) {
            if (info[i].equals("supply")) {
                supply += Integer.parseInt(info[i + 1]);
        }   else if (info[i].equals("buy")) {
                buy += Integer.parseInt(info[i + 1]);
            }
        }
        }
        int result = supply - buy;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(result)
                .append(System.lineSeparator());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(reportBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write a file", e);
        }

    }

    private static StringBuilder getBuilder(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
        return builder;
    }
}
