import resource.SearchItemResources;
import service.ItemServiceFileImpl;
import service.ItemServiceMapImpl;

public class ApiSearchItem {
    public static void main(String[] args) {
        new SearchItemResources(new ItemServiceFileImpl());
    }
}
