package todo;
public class ItemNotFound extends Exception{
    public ItemNotFound()
    {
        System.out.println("There is no item with this title!!");
    }
}