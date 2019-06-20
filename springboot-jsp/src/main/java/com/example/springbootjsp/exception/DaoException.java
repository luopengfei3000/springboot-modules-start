
package com.example.springbootjsp.exception;

public class DaoException extends RuntimeException {
    private static final long serialVersionUID = 8335164454108338833L;

    public DaoException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DaoException(String s) {
        super(s);
    }
}
