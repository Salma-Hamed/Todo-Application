
package todo.application;

public class ItemNotFound extends Throwable{
    public ItemNotFound()
    {
        System.out.println("There is no item with this title!!");
    }
}
