package by.epam.ft.entity;

import org.apache.log4j.Logger;

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
    private static final Logger logger = Logger.getLogger(TagLocale.class);

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doStartTag() {

        JspWriter out = pageContext.getOut();
        String language = String.valueOf(pageContext.getSession().getAttribute(LOCALE_LANGUAGE));
        String country = String.valueOf(pageContext.getSession().getAttribute(LOCALE_COUNTRY));
        Locale current = new Locale(language, country);
        ResourceBundle rb = ResourceBundle.getBundle(LANG_PROPERTIES_FILE, current);
        try {
            out.write(rb.getString(name));
        } catch (IOException e) {
            logger.error("Problems with locale function", e);
        }
        return SKIP_BODY;
    }
}
