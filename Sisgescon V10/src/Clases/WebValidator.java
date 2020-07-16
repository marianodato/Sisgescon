/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

 import java.util.regex.Matcher;
   import java.util.regex.Pattern;

public class WebValidator {

private Pattern pattern1;
private Pattern pattern2;
private Matcher matcher;


private static final String WEB_PATTERN1 = 
    "http[s]{0,1}:\\/\\/(?:www.){0,1}.*\\.com(?:\\.[a-z]{2}){0,1}";

private static final String WEB_PATTERN2 = 
    "(?:www.){0,1}.*\\.com(?:\\.[a-z]{2}){0,1}";


public WebValidator() {
    pattern1 = Pattern.compile(WEB_PATTERN1);
    pattern2 = Pattern.compile(WEB_PATTERN2);
    
}

/**
 * Validate hex with regular expression
 * 
 * @param hex
 *            hex for validation
 * @return true valid hex, false invalid hex
 */
public boolean validate(final String hex) {

    matcher = pattern1.matcher(hex);
    matcher = pattern2.matcher(hex);
    return matcher.matches();

}
}