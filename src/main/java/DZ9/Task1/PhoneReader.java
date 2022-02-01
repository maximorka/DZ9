package DZ9.Task1;

import DZ9.ReaderFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class PhoneReader extends ReaderFile {
    private final int sizeNumberForFirstRules = 12;
    private final int sizeNumberForSecondRules = 14;

    public void dataReader(InputStream inputStream) throws IOException {

        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String phoneNumber = scanner.nextLine();
            if (checkForRules(phoneNumber))
                System.out.println(phoneNumber);

        }
        scanner.close();
        inputStream.close();
    }

    private boolean checkForRules(String line) {
        line = line.trim();
        char[] buf = line.toCharArray();
        if (Character.toString(buf[0]).matches("\\d")) {
            return checkForFirstRules(buf);
        } else if (Character.toString(buf[0]).matches("[(]")) {
            return checkForSecondRules(buf);
        } else {
            return false;
        }
    }

    private boolean checkForFirstRules(char[] buf) {
        int res = 1;
        if (buf.length > sizeNumberForFirstRules)
            return false;
        for (int i = 1; i < 3; i++) {
            res = res & (Character.toString(buf[i]).matches("\\d") ? 1 : 0);
        }
        res = res & (Character.toString(buf[3]).matches("[-]") ? 1 : 0);
        for (int i = 4; i < 7; i++) {
            res = res & (Character.toString(buf[i]).matches("\\d") ? 1 : 0);
        }
        res = res & (Character.toString(buf[7]).matches("[-]") ? 1 : 0);
        for (int i = 8; i < 12; i++) {
            res = res & (Character.toString(buf[i]).matches("\\d") ? 1 : 0);
        }
        return res == 1;
    }

    private boolean checkForSecondRules(char[] buf) {
        int res = 1;
        if (buf.length > sizeNumberForSecondRules)
            return false;
        for (int i = 1; i < 4; i++) {
            res = res & (Character.toString(buf[i]).matches("\\d") ? 1 : 0);
        }
        res = res & (Character.toString(buf[4]).matches("[)]") ? 1 : 0);
        res = res & (Character.toString(buf[5]).matches("[ ]") ? 1 : 0);
        for (int i = 6; i < 9; i++) {
            res = res & (Character.toString(buf[i]).matches("\\d") ? 1 : 0);
        }
        res = res & (Character.toString(buf[9]).matches("[-]") ? 1 : 0);
        for (int i = 10; i < 13; i++) {
            res = res & (Character.toString(buf[i]).matches("\\d") ? 1 : 0);
        }
        return res == 1;
    }

    public static void main(String[] args) throws IOException {
        PhoneReader phoneReader = new PhoneReader();
        phoneReader.dataReader(phoneReader.read("file.txt"));

    }

}
