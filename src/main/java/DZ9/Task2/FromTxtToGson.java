package DZ9.Task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import DZ9.ReaderFile;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FromTxtToGson extends ReaderFile {

    public List addObjectToList(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream);
        List<User> userList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            try {
                String line = scanner.nextLine();
                int index = line.indexOf(" ");
                String name = line.substring(0, index);
                String age = line.substring(index + 1, line.length());
                userList.add(new User(name, Integer.parseInt(age)));
            } catch (Exception ex) {
                System.out.println("Not next line");
            }
        }
        return  userList;
    }

    public void writeToFileInGson(List list) throws IOException {
        List<User> listUser = list;
        Writer writer = new FileWriter("newF.txt");
        String[] buf = new String[list.size()];
        int index = 0;
        for (User user : listUser) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(user);
            buf[index++] = json;
        }
        writer.write(Arrays.toString(buf));
        writer.close();
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FromTxtToGson fromTxtToGson =new FromTxtToGson();

        fromTxtToGson.writeToFileInGson(fromTxtToGson.addObjectToList(fromTxtToGson.read("user.txt")));
    }


}
