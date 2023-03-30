package todo;
import java.util.ArrayList;
import java.util.Scanner;
public class TodoList{
    public static void main(String []args)
    {
        Scanner s = new Scanner(System.in);
        ArrayList<TodoItem> allItems = new ArrayList<TodoItem>();
        
        System.out.Println("Choose one of the following features:");
        System.out.Println("1- Add item");
        System.out.Println("2- Update item");
        System.out.Println("3- Delete item");
        System.out.Println("4- Show all items");
        System.out.Println("5- Show top 5 nearest by date");
        System.out.Println("6- Search by title");
        System.out.println("7- Search by start date");
        System.out.println("8- Search by end date");
        System.out.println("9- Add item to a category");
        System.out.println("10- Add item to a favorite");

        int option = s.nextInt();

        // check input 1 : 10
        if(option < 1 || option > 10)
        {}
    }
}
