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

    public void updateItem(String t, String desc, int p, ArrayList cat, String st, String e) throws ItemNotFound
    {
        if(title == t)
        {
            description = desc;
            priority = p;
            category = cat;
            start = st;
            end =  e;
        }
        else
        {
            throw new ItemNotFound();
        }
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
    }

}
