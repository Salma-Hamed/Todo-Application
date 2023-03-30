package todo;
import java.util.ArrayList;
public class TodoItem{
    
    private String title;
    private String description;
    private int priority;
    private ArrayList category;
    private String start;
    private String end;

    public TodoItem(String t, String desc, int p, ArrayList cat, String st, String e)
    {
        title = t;
        description = desc;
        priority = p;
        category = cat;
        start = st;
        end =  e;
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

}
