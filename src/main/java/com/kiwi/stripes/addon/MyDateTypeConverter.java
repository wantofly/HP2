package com.kiwi.stripes.addon;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import net.sourceforge.stripes.validation.ScopedLocalizableError;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.ValidationError;

public class MyDateTypeConverter implements TypeConverter<Date> {

	private Locale locale;
    private DateFormat[] formats;

    public void setLocale(Locale locale) {
        this.locale = locale;
        this.formats = getDateFormats();
    }
    public Locale getLocale() {
        return locale;
    }

    public static final Pattern PRE_PROCESS_PATTERN = Pattern.compile("(?<!GMT)[\\s,/\\.]+");

    public static final String[] formatStrings = new String[] {
    		"yyyy-MM-dd HH:mm:ss",
            "yyyy M d"
    };

    protected DateFormat[] getDateFormats() {
        SimpleDateFormat[] dateFormats = new SimpleDateFormat[formatStrings.length];

        for (int i=0; i<formatStrings.length; i++) {
            dateFormats[i] = new SimpleDateFormat(formatStrings[i], locale);
            dateFormats[i].setLenient(false);
        }

        return dateFormats;
    }
    public Date convert(String input,
                        Class<? extends Date> targetType,
                        Collection<ValidationError> errors) {

        Date date = null;

        for (DateFormat format : this.formats) {
            try {
                date = format.parse(input);
                break;
            }
            catch (ParseException pe) { /* Do nothing, we'll get lots of these. */ }
        }
        if (date != null) {
            return date;
        }
        else {
            errors.add( new ScopedLocalizableError("converter.date", "invalidDate") );
            return null;
        }
    }
}
