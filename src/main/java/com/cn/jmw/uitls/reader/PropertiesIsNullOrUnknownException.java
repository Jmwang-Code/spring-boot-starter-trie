package com.cn.jmw.uitls.reader;

import java.io.Serial;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年12月07日 19:10
 * @Version 1.0
 */
public class PropertiesIsNullOrUnknownException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -4979550248468209713L;
    private transient int extendedMessageState;
    private transient String extendedMessage;

    public PropertiesIsNullOrUnknownException() {
    }

    public PropertiesIsNullOrUnknownException(String s) {
        super(s);
    }

    public synchronized Throwable fillInStackTrace() {
        if (this.extendedMessageState == 0) {
            this.extendedMessageState = 1;
        } else if (this.extendedMessageState == 1) {
            this.extendedMessage = this.getExtendedNPEMessage();
            this.extendedMessageState = 2;
        }

        return super.fillInStackTrace();
    }

    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            synchronized(this) {
                if (this.extendedMessageState == 1) {
                    this.extendedMessage = this.getExtendedNPEMessage();
                    this.extendedMessageState = 2;
                }

                return this.extendedMessage;
            }
        } else {
            return message;
        }
    }

    private native String getExtendedNPEMessage();
}
