import resource.SearchItemResources;
import service.ItemServiceMapImpl;

public class ApiSearchItem {
    public static void main(String[] args) {
        SearchItemResources resources = new SearchItemResources(new ItemServiceMapImpl());
    }
}
