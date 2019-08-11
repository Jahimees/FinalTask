package by.epam.ft.entity;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static by.epam.ft.constant.AttributeAndParameterConstant.LOCALE_COUNTRY;
import static by.epam.ft.constant.AttributeAndParameterConstant.LOCALE_LANGUAGE;
import static by.epam.ft.constant.PageConstant.LANG_PROPERTIES_FILE;

public class TagLocale extends TagSupport {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        String language = String.valueOf(pageContext.getSession().getAttribute(LOCALE_LANGUAGE));
        String country = String.valueOf(pageContext.getSession().getAttribute(LOCALE_COUNTRY));
        Locale current = new Locale(language, country);
        ResourceBundle rb = ResourceBundle.getBundle(LANG_PROPERTIES_FILE, current);
        try {
            out.write(rb.getString(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
