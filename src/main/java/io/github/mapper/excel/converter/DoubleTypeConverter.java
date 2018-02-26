/*
 * code https://github.com/mohsen-mahmoudi/excel-object-mapping
 */
package io.github.mapper.excel.converter;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author redcrow
 */
public class DoubleTypeConverter implements TypeConverter<Double> {

    private static final Logger log = LoggerFactory.getLogger(DoubleTypeConverter.class);
    
    @Override
    public Double convert(Object value, String... pattern) {
        if (value == null) {
            return null;
        }

        if (value instanceof Double) {
            return (Double) value;
        }

        if (value instanceof String) {
            try {
                return Double.valueOf(((String) value).trim());
            } catch (NumberFormatException ex) {

                // Trivial. Could happen now and then

              //log.warn("NumberFormatException encountered while converting : {} string to Double",value);

               return null;
            }
        }

        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).doubleValue();
        }

        return null;
    }

}
