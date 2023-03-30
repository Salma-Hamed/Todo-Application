package todo.application;

import java.awt.event.ItemEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoApplication {
    
    public boolean addItem(Path p, TodoItem item){
        try (InputStream in = Files.newInputStream(p);
        BufferedReader reader =
          new BufferedReader(new InputStreamReader(in))) {
        String line = null;
        // read file first and check if item exists
        while ((line = reader.readLine()) != null) {
            String fileTitle = line.substring(0, 3);
            String itemTitle = item.getTitle();
            if(fileTitle.equals(itemTitle)){
                System.out.println("Item Found and cannot be repeated");
                return false;
            }
        }
        /// if not found then write it
        try (OutputStream out = new BufferedOutputStream(
        Files.newOutputStream(p, CREATE, APPEND))) {
            String data = item.getTitle() + "," + item.getDescription() + ", [ ";
            ArrayList<String> category = item.getCategory();
            data += category.get(0);
            for(int i=1; i<category.size(); ++i){
                data += ", ";
                data += category.get(i);
            }
            data += " ] , " + String.valueOf(item.getPriority()) +", " +item.getStartDate()+", " +item.getEndDate();
            
        out.write(data.getBytes(), 0, data.length());
        
        } catch (IOException x) {
        System.err.println(x);
        }
    } catch (IOException x) {
        System.err.println(x);
    }
        return true;
    }

    public static void main(String[] args) {
        
        TodoApplication app = new TodoApplication();
        Path file = Paths.get("./items.txt");;
        try {
            Files.createFile(file);
        } catch (FileAlreadyExistsException x) {
            System.err.format("file named %s" +
                " already exists%n", file);
        } catch (IOException x) {
            System.err.format("createFile error: %s%n", x);
        }

        Scanner sc = new Scanner(System.in);
        ArrayList <TodoItem> allItems = new ArrayList<>();
        
        System.out.println("Choose one of the following features:");
        System.out.println("if you want to exit anytime Type -1");
        System.out.println("1- Add item");
        System.out.println("2- Update item");
        System.out.println("3- Delete item");
        System.out.println("4- Show all items");
        System.out.println("5- Show top 5 nearest by date");
        System.out.println("6- Search by title");
        System.out.println("7- Search by start date");
        System.out.println("8- Search by end date");
        System.out.println("9- Add item to a category");
        System.out.println("10- Add item to a favorite");

        String s = sc.next();
        
        while(!s.equals("-1")){
            switch (s){
                case "-1":
                    break;
                case "1":
                    //String newTitle;
                    System.out.println("Type title: " );
                    TodoItem item = new TodoItem(sc.next());
                    System.out.println("Type description: ");
                    sc.next();
                    item.setDescription(sc.nextLine());
                    System.out.println("Type priority in numbers: ");
                    //sc.next();
                    item.setPriority(sc.nextInt());
                    System.out.println("Type category: ");
                    ArrayList<String> arr = new ArrayList<>();
                    arr.add(sc.next());
                    item.setCategory(arr);
                    System.out.println("Type start date in this format MM-DD-YYYY: ");
                    item.setStartDate(sc.next());
                    System.out.println("Type end date in this format MM-DD-YYYY: ");
                    item.setEndDate(sc.next());
                    
                    //allItems.add(item);
                    app.addItem(file, item);
                    break;
                case "2":
                    
                    break;
                case "3":
                    
                    break;
                case "4":
                    
                    break;
                case "5":
                    
                    break;
                case "6":
                    
                    break;
                case "7":
                    
                    break;
                case "8":
                    
                    break;
                case "9":
                    
                    break;
                case "10":
                    
                    break;
                        
            }
            
            System.out.println("Choose one of the following features:");
            System.out.println("if you want to exit anytime Type -1");
            System.out.println("1- Add item");
            System.out.println("2- Update item");
            System.out.println("3- Delete item");
            System.out.println("4- Show all items");
            System.out.println("5- Show top 5 nearest by date");
            System.out.println("6- Search by title");
            System.out.println("7- Search by start date");
            System.out.println("8- Search by end date");
            System.out.println("9- Add item to a category");
            System.out.println("10- Add item to a favorite");
            s = sc.next();
        }
        System.out.println("Sorry to see you going! ");


    }
    
}
