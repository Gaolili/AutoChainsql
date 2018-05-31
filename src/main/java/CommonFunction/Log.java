package CommonFunction;

//import com.google.inject.Singleton;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

//clazz.getCanonicalName() 获取类名字，为将来case执行获取case名和page名和action名
public class Log {

    private final Class<?> clazz;
    private Logger logger;

    /**
     *
     * @param clazz
     */
   public  Log(Class<?> clazz) {
        this.clazz = clazz;
        this.logger = LogManager.getLogger(this.clazz);
    }


    /**
     * @author Young
     * @param message
     *
     */
    public void info(String message) {
        logger.info(clazz.getName()   + ": " + message);
    }

    /**
     * @author Young
     * @param message
     */
    public void debug(String message) {
        logger.debug(clazz.getCanonicalName() + ": " + message);
    }

    /**
     * @author Young
     * @param message
     */
    public void error(String message) {
        logger.error(clazz.getCanonicalName() + ": " + message);
    }

    /**
     * @author Young
     * @param message
     */
    public void trace(String message) {
        logger.trace(clazz.getCanonicalName() + ": " + message);
    }

    /**
     * @author Young
     * @param message
     */
    public void warn(String message) {
        logger.warn(clazz.getCanonicalName() + ": " + message);
    }

    /**
     * @author Young
     * @param message
     */
    public void fatal(String message) {
        logger.fatal(clazz.getCanonicalName() + ": " + message);
    }
}
