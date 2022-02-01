package DZ9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public abstract class ReaderFile {
    public InputStream read(String path) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
