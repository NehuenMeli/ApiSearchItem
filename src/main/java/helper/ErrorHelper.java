package helper;

import model.StandarResponse;
import util.ItemException;
import util.StatusResponse;

public abstract class ErrorHelper {
    public static StandarResponse buildErrorResponse(ItemException exception){
        return new StandarResponse(StatusResponse.ERROR,exception.getMessage());
    }
}
