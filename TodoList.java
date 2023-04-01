package todo;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
public class TodoList{
    static ArrayList<TodoItem> allItems = new ArrayList<>();
    static ArrayList<TodoItem> favorites = new ArrayList<>();
    static ArrayList<String> categories = new ArrayList<>();
    
    static boolean found;
    static int option;
    static String title;
    
    static void categoryInit()
    {
        categories.add("Activities");
        categories.add("Sports");
        categories.add("Art");
        categories.add("Fun");
        categories.add("Travel");
        categories.add("Study");
        categories.add("Generic");
    }
    
    static void fillAllItems(String line)
    {
        int comIdx, newPriority;
        boolean newFav;
        String newTitle, newDesc, newStart, newEnd;
        ArrayList <String> newCat = new ArrayList<>();
        
        
        if(!line.contains(","))
        {
            return;
        }
        // title
        comIdx = line.indexOf(",");
        newTitle = line.substring(0, comIdx);
        line = line.substring(comIdx + 1);

        // description
        comIdx = line.indexOf(",");
        newDesc = line.substring(0, comIdx);
        line = line.substring(comIdx + 1);

        // priority
        comIdx = line.indexOf(",");
        newPriority = Integer.parseInt(line.substring(0, comIdx));
        line = line.substring(comIdx + 1);

        // category
        line = line.substring(1); //removing [
        comIdx = line.indexOf(",");
        String last = "";
        while(line.contains("]"))
        {
            newCat.add(line.substring(0, comIdx));
            line = line.substring(comIdx + 1);
            comIdx = line.indexOf(",");
        }
        last = newCat.get(newCat.size()-1);
        newCat.set(newCat.size()-1, last.substring(0, last.length()-1));

        // start date
        comIdx = line.indexOf(",");
        newStart = line.substring(0, comIdx);
        line = line.substring(comIdx + 1);

        // end date
        comIdx = line.indexOf(",");
        newEnd = line.substring(0, comIdx);
        line = line.substring(comIdx + 1);

        // favorite
        if(line.equals("true"))
        {
            newFav = true;
        }
        else
        {
            newFav = false;
        }
       
       
        
        TodoItem newItem = new TodoItem(newTitle, newDesc, newPriority, newCat, newStart, newEnd, newFav);
        allItems.add(newItem);
        
    }
    
    static void writeInFile()
    {
        try (PrintWriter p = new PrintWriter(new FileOutputStream("database.txt", false))) 
        {
            p.println("My Todo List:");
            for(int i = 0; i < allItems.size(); i++)
            {
                p.print(allItems.get(i).getTitle() + ",");
                p.print(allItems.get(i).getDesc() + ",");
                p.print(allItems.get(i).getPriority() + ",[");
                int size = allItems.get(i).getCat().size();
                for(int j = 0; j < size; j++)
                {
                    p.print(allItems.get(i).getCat().get(j));
                    if(j != allItems.get(i).getCat().size()-1)
                    {
                        p.print(",");
                    }
                    else
                    {
                        p.print("],");
                    }
                }
                p.print(allItems.get(i).getStartDate() + ",");
                p.print(allItems.get(i).getEndDate() + ",");
                p.println(allItems.get(i).getFavorite());
            }
        } catch (FileNotFoundException e1) 
        {
            e1.printStackTrace();
        }
    }
    
    static void fillFavArray()
    {
        favorites.remove(favorites);
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
    }
    
    static boolean checkTimeInterval(String st, String end)
    {
        // Year
        String comp1 = st.substring(6);
        String comp2 = end.substring(6);
        if(Integer.valueOf(comp1) > Integer.valueOf(comp2))
        {
            return false;
        }
        else if(Integer.valueOf(comp1) < Integer.valueOf(comp2))
        {
            return true;
        }
        // Month
        comp1 = st.substring(3, 5);
        comp2 = end.substring(3, 5);
        System.out.print("");
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
        if(Integer.parseInt(comp1) < Integer.parseInt(comp2))
        {
            return true;
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
        if(Integer.valueOf(comp1) > Integer.valueOf(comp2))
        {
            System.out.print("3");
            return false;
        }
        System.out.println("4");
        return true;
    }
    
    static void addItem()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the item title:");
        title = sc.nextLine();
        while(searchByTitle(title) != -1)
        {
            System.out.println("Item title already exists, please enter another title");
            title = sc.nextLine();
        }
        System.out.println("Enter the item description:");
        String desc = sc.nextLine();
        System.out.println("Enter the item priority:");
        int p = sc.nextInt();
        System.out.println("Enter the item category:");
        ArrayList<String> cat = new ArrayList<>();
        String c = sc.next();
        while(categoryIsFound(c) == -1)
        {
            System.out.println("Category doesn't exist!!");
            System.out.println("Please enter one of these categories: " + categories.toString());
            c = sc.next();
        }
        cat.add(categories.get(categoryIsFound(c)));
        System.out.println("Enter the item start date in the DD-MM-YYYY format:");
        String start = sc.next();
        System.out.println("Enter the item end date in the DD-MM-YYYY format:");
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
    
    static void updateItem()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the item title");
        title = s.nextLine();
        System.out.println("Which field do you want to update?");
        String field = s.next();
        System.out.println("Enter the new value");
        String newValue = s.nextLine();
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
                    fillFavArray();
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
                
    }
    
    static boolean deleteItem(String t)
    {
        int ind = searchByTitle(t);
        if(ind != -1)
        {
            // remove from favorites if found 
            try
            {
                removeFromFav(t);
            }
            catch(ItemNotFound e)
            {}
            allItems.remove(ind);
            return true;
        }
        return false;
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
            if(allItems.get(i).getTitle().equalsIgnoreCase(t))
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
            if(favorites.get(i).getTitle().equalsIgnoreCase(t))
            {
                throw new ItemAlreadyExists();
            }
        }
        int idx = searchByTitle(t);
        if(idx != -1)
        {
            allItems.get(idx).setFavorite(true);
            favorites.add(allItems.get(idx));
        }
        else
        {
            throw new ItemNotFound();
        }
        
    }
    
    static void removeFromFav(String t) throws ItemNotFound
    {
        int idx = searchByTitle(t);
        for(int i = 0; i < favorites.size(); i++)
        {
            if(favorites.get(i).getTitle().equalsIgnoreCase(t))
            {
                favorites.remove(i);
                allItems.get(idx).setFavorite(false);
                return;
            }
        }
        throw new ItemNotFound();
    }
    
    static void getFavorites()
    {
        System.out.println("My favorites list:");
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
    
    static int categoryIsFound(String category)
    {
        for(int i = 0; i < categories.size(); i++)
        {
            if(categories.get(i).equalsIgnoreCase(category))
            {
                return i;
            }
        }
        return -1;
    }
    
    static void addItemToCategory(String t, String category)
    {
        int index = searchByTitle(t);
        if (index == -1)
        {
            System.out.println("Item doesn't exist");
            return;
        }
        if(allItems.get(index).getCat().contains(category))
        {
            System.out.println("Item is already added to this category!!");
            return;

        }
        allItems.get(index).addCat(category);
        // Update favorites
        fillFavArray();
        System.out.println("Category Added Successfully");
    }
    
    
    public static void main(String []args) throws FileNotFoundException, IOException
    {
        Scanner s = new Scanner(System.in);
        categoryInit();
        try
        {
            FileInputStream fstream = new FileInputStream("database.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
            // Skip the first line
            br.readLine(); 
            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
                // fill in allItems arraylist
                fillAllItems(strLine);
                
            }
            
            br.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File doesn't exist");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        // Filling favorites list
        fillFavArray();
        
        while(true)
        {
            // Displaying features
            printOptions();

            // Checking the input
            option = s.nextInt();
            if(option == -1)
            {
               break;
            }
            while(option < 1 || option > 13)
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
                    updateItem();
                    break;

                // Delete item
                case 3:
                    System.out.println("Enter the item title");
                    s.nextLine();
                    title = s.nextLine();
                    if(deleteItem(title))
                    {
                        System.out.println("Item deleted successfully");
                    }
                    else
                    {
                        System.out.println("Item doesn't exist!!");
                    }
                    break;
                    
                // Show all items
                case 4:
                    showAllItems();
                    break;

                // Show top 5 nearest by date
                case 5:
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
                    System.out.println("Enter the title of the item");
                    s.nextLine();
                    String title = s.nextLine();
                    System.out.println("Choose the item category: " + categories.toString());
                    String category = s.next();
                    while(categoryIsFound(category) == -1)
                    { 
                        System.out.println("Category doesn't exist!!");
                        System.out.println("Please enter one of these categories: " + categories.toString());
                        category = s.next();
                    }
                    addItemToCategory(title, categories.get(categoryIsFound(category)));
                    
                    break;
                    
                // Add item to a favorite
                case 11:
                    System.out.println("Enter the title of the item");
                    s.nextLine();
                    title = s.nextLine();
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
                    s.nextLine();
                    title = s.nextLine();
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
                    
            }
        }
        
        // Stroring data
        writeInFile();
        
    }
}
