/*
 * code https://github.com/mohsen-mahmoudi/excel-object-mapping
 */
package io.github.mapper.excel.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author redcrow
 */
public class TypeConverters {

    private static final Logger log = LoggerFactory.getLogger(TypeConverters.class);

    private static Map<Class, TypeConverter> converter;

    private static void registerConverter() {
        TypeConverter stringConverter = new StringTypeConverter();
        TypeConverter intConverter = new IntegerTypeConverter();
        TypeConverter doubleConverter = new DoubleTypeConverter();
        TypeConverter boolConverter = new BooleanTypeConverter();
        TypeConverter dateType = new DateTypeConverter();


        log.debug("Registering converters...");
        converter = new HashMap<>();
        log.debug("Adding String converter : {}",stringConverter);
        converter.put(String.class, stringConverter);
        log.debug("Adding Integer converter : {}",intConverter);
        converter.put(Integer.class, intConverter);
        log.debug("Adding Double converter : {}",doubleConverter);
        converter.put(Double.class, doubleConverter);
        log.debug("Adding Boolean converter : {}",boolConverter);
        converter.put(Boolean.class, boolConverter);
        log.debug("Adding Date converter : {}",dateType);
        converter.put(Date.class, dateType);
    }

    public static Map<Class, TypeConverter> getConverter() {
        if (converter == null) {
            registerConverter();
        }
        return converter;
    }
}
