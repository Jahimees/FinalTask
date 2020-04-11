package by.epam.ft.service;

/**
 * Class for protection against cross-site scripting
 */
public class ParamsValidator {
    /**
     * Clear input string clears the line of the form: "<script>"
     * to script
     * @param s - String with banned symbols '<' and '>'.
     * @return cleared string
     */
    public static String validateParams(String s){
        s = s.replaceAll("<", " ");
        s = s.replaceAll(">", " ");
        return s;
    }
}
