package todo;
import java.util.ArrayList;
import java.util.Scanner;
public class TodoList{
    static ArrayList<TodoItem> allItems = new ArrayList<>();
    static ArrayList<TodoItem> favorites = new ArrayList<>();
    static boolean found;
    static int option;
    
    static void searchByTitle(String t)
    {
        for(int i = 0; i < allItems.size(); i++)
        {
            if(allItems.get(i).getTitle().equals(t))
            {
                found = true;
                allItems.get(i).showItem();
                return;
            }
        }
        System.out.println("There is no item with this title");
    }
    
    static void searchByStartDate(String stDate)
    {
        for(int i = 0; i < allItems.size(); i++)
        {
            if(allItems.get(i).getStartDate().equals(stDate))
            {
                found = true;
                allItems.get(i).showItem();
            }
        }
        if(!found)
        {
            System.out.println("There is no item with this start date");
        }
    }
    
    
    static void searchByEndDate(String endDate)
    {
        for(int i = 0; i < allItems.size(); i++)
        {
            if(allItems.get(i).getEndDate().equals(endDate))
            {
                found = true;
                allItems.get(i).showItem();
            }
        }
        if(!found)
        {
            System.out.println("There is no item with this end date");
        }
    }
    
    static void searchByPriority(int priority)
    {
        for(int i = 0; i < allItems.size(); i++)
        {
            if(allItems.get(i).getPriority() == priority)
            {
                found = true;
                allItems.get(i).showItem();
            }
        }
        if(!found)
        {
            System.out.println("There is no item with this priority");
        }
    }
    
    static void getFavorites()
    {
        for(int i = 0; i < favorites.size(); i++)
        {
            favorites.get(i).showItem();
        }
        if(favorites.size() == 0)
        {
            System.out.println("Favorites list is empty!!");
        }
    }
    
    static void printOptions()
    {
        System.out.println("Choose one of the following features or -1 to terminate: ");
        System.out.println("1- Add item");
        System.out.println("2- Update item");
        System.out.println("3- Delete item");
        System.out.println("4- Show all items");
        System.out.println("5- Show top 5 nearest by date");
        System.out.println("6- Search by title");
        System.out.println("7- Search by start date");
        System.out.println("8- Search by end date");
        System.out.println("9- Search by end priority");
        System.out.println("10- Add item to a category");
        System.out.println("11- Add item to a favorite");
        System.out.println("12- Show favorites list");
    }
    public static void main(String []args)
    {
        Scanner s = new Scanner(System.in);
        
        // Filling favorites list
        for(int i = 0; i < allItems.size(); i++)
        {
            if(allItems.get(i).getFavorite()== true)
            {
                favorites.add(allItems.get(i));
            }
        }
        
        
        /**/
        ArrayList<String> favorites2 = new ArrayList<>();
        favorites2.add("cat1");
        TodoItem i1 = new TodoItem("item1", "i1 desc", 2, favorites2, "2-2-2022", "6-6-2022");
        allItems.add(i1);
        /**/
        
        
        
        while(true)
        {
            // Displaying features
            printOptions();

            // Checking the input
            option = s.nextInt();
            if(option == -1)
            {
                System.exit(0);
            }
            while(option < 1 || option > 12)
            {
                System.out.println("Invalid input, please enter another number");
                option = s.nextInt();
            }

            found = false;
            switch(option)
            {
                // Add item
                case 1:
                    break;

                // Update item
                case 2:
                    break;

                // Delete item
                case 3:
                    break;

                // Show all items
                case 4:
                    break;

                // Show top 5 nearest by date
                case 5:
                    break;

                // Search by title
                case 6:
                    System.out.println("Enter the item title");
                    String t = s.next();
                    searchByTitle(t);
                    break;

                // Search by start date
                case 7:
                    System.out.println("Enter the item start date");
                    String stDate = s.next();
                    searchByStartDate(stDate);
                    break;

                // Search by end date
                case 8:
                    System.out.println("Enter the item end date");
                    String endDate = s.next();
                    searchByEndDate(endDate);
                    break;
                    
                // Search by priority
                case 9:
                    System.out.println("Enter the item priority");
                    int priority = s.nextInt();
                    searchByPriority(priority);
                    break;
                    
                // Add item to a category
                case 10:
                    break;
                    
                // Add item to a favorite
                case 11:
                    break;
                    
                // Show favorites list
                case 12:
                    getFavorites();
                    break;
                    
            }
        }
       
        
    }
}
