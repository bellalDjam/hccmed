package be.gest.web.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.data.util.converter.StringToBigDecimalConverter;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.ui.TextField;

public class NumberField extends TextField {
    public NumberField() {
        // Mark the field as numeric.
        // This affects the virtual keyboard shown on mobile devices.
        AttributeExtension ae = new AttributeExtension();
        ae.extend(this);
        ae.setAttribute("type", "number");

        setConverter(new StringToBigDecimalConverter() {
            @Override
            protected NumberFormat getFormat(Locale locale) {
                // do not use a thousands separator, as HTML5 input type
                // number expects a fixed wire/DOM number format regardless
                // of how the browser presents it to the user (which could
                // depend on the browser locale)
                NumberFormat format = new DecimalFormat();
                if (format instanceof DecimalFormat){
                ((DecimalFormat) format).setMaximumFractionDigits(2);
                ((DecimalFormat) format).setMinimumFractionDigits(2);
                ((DecimalFormat) format).setParseBigDecimal(true);
                ((DecimalFormat) format).setDecimalSeparatorAlwaysShown(true);
//                format.setParseIntegerOnly(true);
//                format.setGroupingUsed(false);
                }
                return format;
            }
        });
    }

    public NumberField(String caption) {
        this();
        setCaption(caption);
  
    }
    }
//public class EnduserBigdecimalConverter implements Converter<String, BigDecimal> {
//
//    protected DecimalFormat getFormat(Locale locale) {
//        if (locale == null) {
//            locale = Locale.getDefault();
//        }
//        DecimalFormat dc = (DecimalFormat) NumberFormat.getInstance(locale);
//        dc.applyPattern("#,##0.00");
//        dc.setParseBigDecimal(true);
//        dc.setRoundingMode(RoundingMode.HALF_UP);
//        return dc;
//    }
//
//    @Override
//    public BigDecimal convertToModel(String value, Locale locale)
//            throws ConversionException {
//        if (value == null) {
//            return null;
//        }
//
//        // Remove leading and trailing white space
//        value = value.trim();
//
//        // Parse and detect errors. If the full string was not used, it is
//        // an error.
//        ParsePosition parsePosition = new ParsePosition(0);
//        BigDecimal parsedValue = (BigDecimal) getFormat(locale).parse(value, parsePosition);
//        if (parsePosition.getIndex() != value.length()) {
//            throw new ConversionException("Could not convert '" + value
//                    + "' to " + getModelType().getName());
//        }
//
//        if (parsedValue == null) {
//            // Convert "" to null
//            return null;
//        }
//        return parsedValue;
//    }
//
//    @Override
//    public String convertToPresentation(BigDecimal value, Locale locale)
//            throws ConversionException {
//        if (value == null) {
//            return null;
//        }
//
//        return getFormat(locale).format(value);
//    }
//
//    @Override
//    public Class<BigDecimal> getModelType() {
//        return BigDecimal.class;
//    }
//
//    @Override
//    public Class<String> getPresentationType() {
//        return String.class;
//    }
//}
//For attaching to field, you shuld do some similar to next:
//
//
//AbstractField af = (AbstractField) groupFieldInstance.getField("importe");
//af.setConverter(new EnduserBigdecimalConverter());
//
//You should pay attentiont to this lines inside protected DecimalFormat getFormat(Locale locale) for customization:
//DecimalFormat dc = (DecimalFormat) NumberFormat.getInstance(locale); // Or you can create DecimalFormat directly.
//dc.applyPattern("#,##0.00");
//dc.setParseBigDecimal(true); // IMPORTANT, setting to "true" for always returning BigDecimal
//dc.setRoundingMode(RoundingMode.HALF_UP);