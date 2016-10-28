package exception;

/**
 * Created by adric on 28/10/2016.
 */
public class BeanException extends Exception{

    // The value in french in the text
    private EnumException enumException;

    public BeanException(EnumException e){
        this.enumException = e;
    }

    public EnumException getEnumException() {
        return enumException;
    }

    @Override
    public String toString() {
        return "BeanException{" +
                "enumException=" + enumException +
                '}';
    }
}
