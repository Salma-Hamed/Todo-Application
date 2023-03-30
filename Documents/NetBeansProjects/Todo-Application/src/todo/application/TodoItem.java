
package todo.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import todo.application.ItemNotFound;

public class TodoItem {
    private String id;
    private String title;
    private String description;
    private int priority;
    private String category;
    private String startDate;
    private String endDate;

    public TodoItem(String id, String title, String desc, int priority, String category, String startDate, String endDate)
    {
        this.id = id;
        this.title = title;
        this.description = desc;
        this.priority = priority;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TodoItem(String id) {
        this.id = id;
    }
    

    public void updateItem(File f,String title, String descr, int pri, String category, String startDate, String endDate)
    {
        try {
            Scanner scanner = new Scanner(f);
            String line = null;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("error in file");
        }
        if(this.title == title)
        {
            this.description = descr;
            this.priority = pri;
            this.category = category;
            this.startDate = startDate;
            this.endDate =  endDate;
        }
        
    }

    public String getId() {
        return id;
    }
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    
}
