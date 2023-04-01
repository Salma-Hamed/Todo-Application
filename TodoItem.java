package todo;
import java.util.ArrayList;
public class TodoItem{
    
    private String title;
    private String description;
    private int priority;
    private ArrayList category;
    private String start;
    private String end;
    private boolean favorite;

   
    public TodoItem(String t, String desc, int p, ArrayList cat, String st, String e)
    {
        title = t;
        description = desc;
        priority = p;
        category = cat;
        start = st;
        end =  e;
        favorite = false;
    }
    
    public TodoItem(String t, String desc, int p, ArrayList cat, String st, String e, boolean f)
    {
        this(t, desc, p, cat, st, e);
        favorite = f;
    }

    public boolean updateItem(String field, String newValue) throws ItemNotFound
    {
        switch(field)
        {
            case "description":
                description = newValue;
                break;
            case "priority":
                priority = Integer.parseInt(newValue);
                break;
            case "category":
                category.removeAll(category);
                category.add(newValue);
                break;
            case "start":
                start = newValue;
                break;
            case "end":
                end = newValue;
                break;
            default:
                return false;
        }
        return true;
    }
    
    public void addCat(String cat)
    {
        category.add(cat);
    }
    
    public void setFavorite(boolean fav)
    {
        favorite = fav;
    }
    
    public String getTitle()
    {
        return title;
    }
    public String getDesc()
    {
        return description;
    }
    public ArrayList getCat()
    {
        return category;
    }
    public String getStartDate()
    {
        return start;
    }
    
    public String getEndDate()
    {
        return end;
    }
    
    public int getPriority()
    {
        return priority;
    }
    
    public boolean getFavorite()
    {
        return favorite;
    }
    
    public void showItem()
    {
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Priority: " + priority);
        System.out.println("Category: " + category);
        System.out.println("Start Date: " + start);
        System.out.println("End Date: " + end);
        System.out.print("Favorite: ");
        if(favorite == true)
        {
            System.out.println("Yes");
        }
        else
        {
            System.out.println("No");
        }
    }

}
