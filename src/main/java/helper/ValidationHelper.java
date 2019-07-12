package helper;

import static util.StaticValues.Resource.TAG_FILTER;

public class ValidationHelper {

    public static String validateTagFilter(String tagFilter){
        if (tagFilter!=null && tagFilter.equals(TAG_FILTER)) return tagFilter;
        else return null;
    }
}
