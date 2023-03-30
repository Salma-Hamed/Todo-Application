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
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoApplication {
    public String i = "0";
    
    public void displayItems(ArrayList arr){
        System.out.println("ID,  Title,    Description,    Priority, Categories,  StartDate,  EndDate");
        for(int i=0; i<arr.size(); ++i){
            //showitem();
        }
    }
    
    public void addItemsToFile(Path p, ArrayList<TodoItem> items){
        /*String newStr = p.toString().substring(0, p.toString().length()-4) ;
        newStr += String.valueOf(i);
        Path newPath = Paths.get(newStr);
        */
        String data = "";
        boolean exists = false;
            
            try (OutputStream out = new BufferedOutputStream(
            Files.newOutputStream(p, CREATE, APPEND))) {
            for(int i=0; i<items.size(); ++i){
                data += items.get(i).getTitle() + ",    " + items.get(i).getDescription() + ", " + 
                            items.get(i).getCategory() +",   " + String.valueOf(items.get(i).getPriority()) +",   " + 
                            items.get(i).getStartDate()+",   " +items.get(i).getEndDate();
                    data += "\n";
            }
            out.write(data.getBytes(), 0, data.length());

            } catch (IOException x) {
            System.err.println(x);
            }
    }

    public static void main(String[] args) throws IOException {
        
        TodoApplication app = new TodoApplication();
        Path file = Paths.get("./items.txt");
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
        int newId;
        
        while(!s.equals("-1")){
            switch (s){
                case "-1":
                    break;
                case "1":
                    TodoItem item = new TodoItem(app.i);
                    newId = Integer.parseInt(app.i);
                    newId++;
                    app.i = Integer.toString(newId);
                    System.out.println("Type title: " );
                    item.setTitle(sc.next());
                    System.out.println("Type description: ");
                    s = sc.next();
                    item.setDescription(s + sc.nextLine());
                    System.out.println("Type priority in numbers: ");
                    item.setPriority(sc.nextInt());
                    System.out.println("Type category: ");
                    item.setCategory(sc.next());
                    System.out.println("Type start date in this format MM-DD-YYYY: ");
                    item.setStartDate(sc.next());
                    System.out.println("Type end date in this format MM-DD-YYYY: ");
                    item.setEndDate(sc.next());
                    
                    allItems.add(item);
                    
                    break;
                case "2":
                    System.out.println();
                    app.displayItems(allItems);
                    System.out.print("Note you will have to update the whole item \n"
                            + "choose the number of the item you want to update: ");
                    int updatedItemIndex = sc.nextInt();
                    TodoItem updatedItem = allItems.get(updatedItemIndex);
                    
                    System.out.println("Type title: " );
                    updatedItem.setTitle(sc.next());
                    System.out.println("Type description: ");
                    sc.next();
                    updatedItem.setDescription(sc.nextLine());
                    System.out.println("Type priority in numbers: ");
                    updatedItem.setPriority(sc.nextInt());
                    System.out.println("Type category: ");
                    updatedItem.setCategory(sc.next());
                    System.out.println("Type start date in this format MM-DD-YYYY: ");
                    updatedItem.setStartDate(sc.next());
                    System.out.println("Type end date in this format MM-DD-YYYY: ");
                    updatedItem.setEndDate(sc.next());
                    
                    //allItems.remove(updatedItemIndex-1);
                    //allItems.add(updatedItem);
                    break;
                case "3":
                    
                    break;
                case "4":
                    app.displayItems(allItems);
                    
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
            app.addItemsToFile(file, allItems);
            
            System.out.println();
            System.out.println("to continue type yes, to exit type anything");
            if(sc.next().equals("yes")){
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
                System.out.println();
            }else
                break;
            
        }
        
        System.out.println("Sorry to see you going! ");


    }
    
}
