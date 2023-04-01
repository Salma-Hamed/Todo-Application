package todo;
import java.util.*;

public class TodoList{
    static ArrayList<TodoItem> allItems = new ArrayList<>();
    static ArrayList<TodoItem> favorites = new ArrayList<>();
    static ArrayList<String> categories = new ArrayList<>();

    static boolean found;
    static int option;
    static String title;
    
    static void fillFavArray()
    {
        for(int i = 0; i < allItems.size(); i++)
        {
            if(allItems.get(i).getFavorite()== true)
            {
                favorites.add(allItems.get(i));
            }
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
        System.out.println("11- Add item to a favorites list");
        System.out.println("12- Remove item from favorites list");
        System.out.println("13- Show favorites list");
        System.out.println("14- Show ToDo list based Category");
    }
    
    static boolean checkTimeInterval(String st, String end)
    {
        // Year
        String comp1 = st.substring(6);
        String comp2 = end.substring(6);
        if(Integer.parseInt(comp1) > Integer.parseInt(comp2))
        {
            return false;
        }
        // Month
        comp1 = st.substring(3, 5);
        comp2 = end.substring(3, 5);
        if(comp1.charAt(0) == '0')
        {
            comp1 = comp1.charAt(1)+"";
        }
        if(comp2.charAt(0) == '0')
        {
            comp2 = comp2.charAt(1)+"";
        }
        if(Integer.parseInt(comp1) > Integer.parseInt(comp2))
        {
            return false;
        }
        // Day
        comp1 = st.substring(0, 2);
        if(comp1.charAt(0) == '0')
        {
            comp1 = comp1.charAt(1)+"";
        }
        comp2 = end.substring(0, 2);
        if(comp2.charAt(0) == '0')
        {
            comp2 = comp2.charAt(1)+"";
        }
        if(Integer.parseInt(comp1) > Integer.parseInt(comp2))
        {
            return false;
        }
        return true;
    }
    
    static void addItem()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the item title:");
        String title = sc.nextLine();
        System.out.println("Enter the item description:");
        String desc = sc.nextLine();
        System.out.println("Enter the item priority:");
        int p = sc.nextInt();
        System.out.println("Enter the item category:"+categories.toString());
        String item = sc.next();
        ArrayList<String> cat = new ArrayList<>();
        if (checkIsCategoryFound(item)){
            cat.add(item);
        }else {
            System.out.println("Enter Valid Category");
             sc.next();

        }
        System.out.println("Enter the item start date in the MM-DD-YYYY format:");
        String start = sc.next();
        System.out.println("Enter the item end date in the MM-DD-YYYY format:");
        String end = sc.next();
        while(!checkTimeInterval(start, end))
        {
            System.out.println("Invalid end date");
            System.out.println("Enter the item end date in the MM-DD-YYYY format:");
            end = sc.next();
        }
        TodoItem i = new TodoItem(title, desc, p, cat, start, end);
        allItems.add(i);
    }
    
    static void showAllItems()
    {
        System.out.println("My todo list:");
        for(int i = 0; i < allItems.size(); i++)
        {
            allItems.get(i).showItem();
            System.out.println("*******************************");
        }
        if(allItems.size() == 0)
        {
            System.out.println("The list is empty");
        }
    }
    
    static int searchByTitle(String t)
    {
        for(int i = 0; i < allItems.size(); i++)
        {
            if(allItems.get(i).getTitle().equals(t))
            {
                return i;
            }
        }
        return -1;
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
    
    static void addToFav(String t) throws ItemAlreadyExists, ItemNotFound
    {
        for(int i = 0; i < favorites.size(); i++)
        {
            if(favorites.get(i).getTitle().equals(title))
            {
                throw new ItemAlreadyExists();
            }
        }
        int idx = searchByTitle(t);
        if(idx != -1)
        {
            favorites.add(allItems.get(idx));
        }
        else
        {
            throw new ItemNotFound();
        }
        
    }
    
    static void getFavorites()
    {
        System.out.println("My favorite list:");
        for(int i = 0; i < favorites.size(); i++)
        {
            favorites.get(i).showItem();
            System.out.println("*******************************");
        }
        if(favorites.size() == 0)
        {
            System.out.println("Favorites list is empty!!");
        }
    }
    
    static void removeFromFav(String title) throws ItemNotFound
    {
        for(int i = 0; i < favorites.size(); i++)
        {
            if(favorites.get(i).getTitle().equals(title))
            {
                favorites.remove(i);
                return;
            }
        }
        throw new ItemNotFound();
    }
    static void deleteItem(String title){
        if(searchByTitle(title)!=-1){
            int index=searchByTitle(title);
            allItems.remove(index);

        }else {
            System.out.println("There is no item with this title!!");
        }

    }
    static int addItemToCategory(String title){
        if (searchByTitle(title)==-1){
            System.out.println("Title Not Found");
        }else {
            return searchByTitle(title);
        }
        return 0;
    }
//    static void getFiveNearestBYDate(){
//       Collections.sort(allItems, new Comparator<TodoItem>() {
//           @Override
//           public int compare(TodoItem o1, TodoItem o2) {
//               return o1.getStartDate().compareTo(o2.getStartDate());
//           }
//       });
//        Collections.reverse(allItems);
//        System.out.println("The Top 5 Nearest BY Date: ");
//        for(int i = 0; i < 5; i++)
//        {
//            allItems.get(i).showItem();
//            System.out.println("*******************************");
//        }
//        if(allItems.size() == 0)
//        {
//            System.out.println("The list is empty");
//        }
//
//
//    }
    static Boolean checkIsCategoryFound(String category)
    {
        for(int i = 0; i < categories.size(); i++)
        {
            if(categories.get(i).equals(category))
            {
                return true;
            }
        }
        return false;
    }
    static void searchByCat(String cat)
    {

        for(int i = 0; i < allItems.size(); i++)
        {
            if(allItems.get(i).getCategory().get(0).equals(cat))
            {
                allItems.get(i).showItem();
                System.out.println("*******************************");
                if(allItems.size() == 0)
                {
                    System.out.println("The list is empty");
                }


            }else {
                System.out.println("There is No Item with this category");
            }
        }
    }
    
    public static void main(String []args)
    {
        Scanner s = new Scanner(System.in);
        
        // Filling favorites list
        fillFavArray();
        
        /**/
        ArrayList<String> favorites2 = new ArrayList<>();
        favorites2.add("cat1");
        TodoItem i1 = new TodoItem("item1", "i1 desc", 2, favorites2, "2-2-2021", "6-6-2021");
        TodoItem i2 = new TodoItem("item1", "i1 desc", 2, favorites2, "2-2-2022", "6-6-2022");
        TodoItem i3 = new TodoItem("item1", "i1 desc", 2, favorites2, "2-2-2023", "6-6-2023");
        TodoItem i4 = new TodoItem("item1", "i1 desc", 2, favorites2, "3-10-2023", "6-6-2023");
        TodoItem i5 = new TodoItem("item1", "i1 desc", 2, favorites2, "2-11-2023", "6-6-2023");
        allItems.add(i1);
        allItems.add(i2);
        allItems.add(i3);
        allItems.add(i4);
        allItems.add(i5);

        categories.add("sport");
        categories.add("fun");
        categories.add("study");
        categories.add("activities");
        categories.add("art");
        categories.add("travel");
        categories.add("general");
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
            while(option < 1 || option > 14)
            {
                System.out.println("Invalid input, please enter another number");
                option = s.nextInt();
            }

            found = false;
            switch(option)
            {
                // Add item
                case 1:
                    addItem();
                    break;

                // Update item
                case 2:
                    System.out.println("Enter the item title");
                    title = s.next();
                    System.out.println("Which field do you want to update?");
                    String field = s.next();
                    System.out.println("Enter the new value");
                    String newValue = s.next();
                    int idx = searchByTitle(title);
                    boolean updated;
                    if(idx != -1)
                    {
                        System.out.println("Item before update:");
                        allItems.get(idx).showItem();
                        try 
                        {
                            updated = allItems.get(idx).updateItem(field, newValue);
                            if(updated)
                            {
                                System.out.println("Item after update:");
                                allItems.get(idx).showItem();
                            }
                            else
                            {
                                System.out.println("No such field!!");
                            }
                            
                        } catch (ItemNotFound e) {
                            System.out.println("Item doesn't exist in favorites list");
                        }
                    }
                    else
                    {
                        System.out.println("There is no item with this title!!");
                    }
                    break;

                // Delete item
                case 3:
                    System.out.println("Enter the title you want to delete");
                    String t=s.next();
                    System.out.println("Are you sure to delete this item press 1:Yes . 2:No ");
                    int choice=s.nextInt();
                    if(choice==1){
                        deleteItem(t);
                        System.out.println("Deleted Successfully");
                    }
                    else if (choice==2) {
                        break;
                    }

                    break;

                // Show all items
                case 4:
                    showAllItems();
                    break;

                // Show top 5 nearest by date
                case 5:

//                    getFiveNearestBYDate();

                    break;

                // Search by title
                case 6:
                    System.out.println("Enter the item title");
                    title = s.next();
                    int index = searchByTitle(title);
                    if(index != -1)
                    {
                        allItems.get(index).showItem();
                    }
                    else
                    {
                        System.out.println("There is no item with this title");
                    }
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
                    System.out.println("Write a Title you want to added to another category");
                    String title=s.next();
                    System.out.println("Write a category you want to add");
                    System.out.println("The Category we have");
                    System.out.println(categories.toString());

                    String category=s.next();
                    int catIndex=addItemToCategory(title);
                    if (checkIsCategoryFound(category)){
                        allItems.get(catIndex).addCat(category);
                        System.out.println("Category Added Successfully");

                    }else {
                        System.out.println("Enter Valid Category");
                         s.next();
                    }
                    break;
                    
                // Add item to a favorite
                case 11:
                    System.out.println("Enter the title of the item");
                    title = s.next();
                    try 
                    {
                        addToFav(title);
                    } 
                    catch (ItemAlreadyExists e) 
                    {
                        System.out.println("Item already exists");
                    }
                    catch (ItemNotFound e) 
                    {
                        System.out.println("Item doesn't exist in the todo list");
                    }
                    break;
                    
                // remove an item from favorites list
                case 12:
                    System.out.println("Enter the title of the item");
                    title = s.next();
                    try 
                    {
                        removeFromFav(title);
                    } 
                    catch (ItemNotFound e) 
                    {
                        System.out.println("Item doesn't exist in favorites");
                    }
                    
                    break;  
                    
                // Show favorites list
                case 13:
                    getFavorites();
                    break;
                case 14:
                    System.out.println("Enter The Category : "+categories.toString());
                    String categoryx=s.next();
                    searchByCat(categoryx);

                    break;
            }
        }
       
        
    }
}
