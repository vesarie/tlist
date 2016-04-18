package tlist.servlets;

import javax.servlet.http.HttpServletRequest;

public abstract class FormValidator<T> {

    public static final String msgSuffix = "ErrorMsg";

    protected HttpServletRequest request;
    protected int errorCount;

    public FormValidator(HttpServletRequest request) {
        this.request = request;
        this.errorCount = 0;
    }

    public abstract void readInto(T model);

    protected void setErrorMsg(String name, String msg) {
        request.setAttribute(name + msgSuffix, msg);
        errorCount++;
    }

    public int getErrorCount() {
        return errorCount;
    }

}
