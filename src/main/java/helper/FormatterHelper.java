package helper;

import spark.Request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Formatter;
import java.util.Locale;

import static util.StaticValues.HTTP.ENCODING;

public abstract class FormatterHelper {

    public static Integer getInteger(String integer){
        try {
            if (integer!=null) return Integer.decode(integer);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return null;
    }

    public static String getParameter(Request request, String parameterKey){

        try {
            String parameter = request.queryParams(parameterKey);
            if (parameter==null || parameter.isEmpty()) return null;
            return URLEncoder.encode(parameter, ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getString(String parametrizedString, Object ... args){
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder);
        return formatter.format(parametrizedString, args).toString();
    }
}
