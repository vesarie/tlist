package tlist.servlets;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;

public class ServletUtil {

    public static final String msgSuffix = "ErrorMsg";

    public static int parseInt(String s, int defaultValue) {
        try {
            if (s == null) {
                return defaultValue;
            }
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    static Date parseDate(String s, Date defaultValue) {
        try {
            if (s == null) {
                return defaultValue;
            }
            return Date.valueOf(s);
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    static String getStringParameter(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        if (value == null) {
            return "";
        }

        return value.trim();
    }

//    public static int getIntParameter(HttpServletRequest request, String name, int defaultValue) {
//        try {
//            String s = request.getParameter(name);
//            if (s == null) {
//                request.setAttribute(name + msgSuffix, "Missing parameter: " + name);
//                return defaultValue;
//            }
//
//            return Integer.parseInt(s);
//        } catch (NumberFormatException e) {
//            request.setAttribute(name + msgSuffix, "Invalid parameter: " + name);
//            return defaultValue;
//        }
//    }

}
