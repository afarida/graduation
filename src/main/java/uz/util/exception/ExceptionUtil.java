package uz.util.exception;

/**
 * Created by Admin on 17.02.2017.
 */
public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id){
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id){
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg){
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg){
        if (!found){
            throw new NotFoundException(msg);
        }
    }

}
