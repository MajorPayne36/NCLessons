package com.Lesson1.Exceptions;

/**
 * Class for thrown exceptions in DI using
 */
public class DIException extends Exception {

    /**
     * Exception
     */
    private Exception ex;

    /**
     * Any message
     */
    private String msg = "";

    public DIException (Exception ex){
        this.ex=ex;
    }

    public DIException (String msg){
        this.msg=msg;
    }

    public DIException (String msg, Exception ex){
        this.msg=msg;
        this.ex=ex;
    }

    public Exception getEx() {
        return ex;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        if (ex != null) {
            return this.msg + ex.toString();
        }
        else {
            return super.toString();
        }
    }
}
