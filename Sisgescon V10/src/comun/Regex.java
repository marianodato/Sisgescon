package comun;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author juan
 */
public class Regex {
    
    public static boolean evaluar(String cadena, String patron) {
        
        Pattern pattern;
        pattern = Pattern.compile(patron);
        Matcher matcher;
        matcher = pattern.matcher(cadena);
        
        return matcher.find();
    }
    
}
