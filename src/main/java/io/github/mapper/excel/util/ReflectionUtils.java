/*
 * code https://github.com/mohsen-mahmoudi/excel-object-mapping
 */
package io.github.mapper.excel.util;

import static io.github.mapper.excel.util.CollectionUtils.isEmpty;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.mapper.excel.annotation.Column;
import io.github.mapper.excel.converter.TypeConverter;
import io.github.mapper.excel.converter.TypeConverters;

/**
 * @author redcrow
 * @author Mohsen.Mahmoudi
 */
public class ReflectionUtils {

    private static final Logger log = LoggerFactory.getLogger(ReflectionUtils.class);

    private static String toUpperCaseFirstCharacter(String str) {
        log.debug("Converting first letter to upper case for string: {}", str);
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static void setValueOnField(Object instance, Field field, Object value) throws Exception {
        log.debug("Setting value of : {} on field : {}, for the instance : {}", value, field, instance);

        //TODO : to add JAVA 9 semantics
        Class<?> clazz = instance.getClass();
        String setMethodName = "set" + toUpperCaseFirstCharacter(field.getName());

        Set<Map.Entry<Class, TypeConverter>> converterSet = TypeConverters.getConverter().entrySet();

        for (Map.Entry<Class, TypeConverter> entry : converterSet) {

            log.debug("Checking if field type for the method name : {} is compatible with converter for : {}",
                    setMethodName, entry);

            Class entryClass = entry.getKey();

            if (field.getType().equals(entryClass)) {

                log.debug("Field : {} of field type, {} found compatible with converter for class : {}", field, field.getType(), entryClass);

                log.debug("Creating invokeable method for : {} and type : {}", setMethodName, entryClass);
                Method method = clazz.getDeclaredMethod(setMethodName, entryClass);
                log.debug("{} method has been invoked",method);

                Column column = getColumn(field);

                TypeConverter converter = entry.getValue();

                log.debug("Invoking method : {} for instance : {} with class type converter" +
                        " : {}, converting the value : {}", method, instance, converter, value);
                method.invoke(instance, converter.convert(value, column == null ? null : column.pattern()));
            }
        }
    }

    private static Column getColumn(Field field) {
        log.debug("Fetching annotation from field : {}", field);
        Column column = field.getAnnotation(Column.class);
        log.debug("Annotation : {} fetched ...", column);
        return column;
    }

    public static void eachFields(Class<?> clazz, EachFieldCallback callback) throws Throwable {

        log.debug("Adding Fields in class: {} to callBack : {}", clazz,callback);

        Field[] fields = clazz.getDeclaredFields();

        if (!isEmpty(fields)) {

            log.debug("There are : {} Fields in class : {}", fields.length, clazz);
            for (Field field : fields) {
                log.debug(" {}, \n", field);
            }

            for (Field field : fields) {

                log.debug("Getting callBack for field : {}", field);

                if (field.isAnnotationPresent(Column.class)) {
                    callBackAnnotatedField(callback, field);
                }

                if (!field.isAnnotationPresent(Column.class)) {
                    callBackNonAnotatedField(callback, field);
                }
            }
        }
    }

    private static void callBackNonAnotatedField(EachFieldCallback callback, Field field) throws Throwable {
        log.debug("{} CallBack for nonAnnotated field : {} ....", callback, field);

        callback.each(field, field.getName(), null);

    }

    private static void callBackAnnotatedField(EachFieldCallback callback, Field field) throws Throwable {

        log.debug("Getting callBack for annotated field : {}", field);

        String columnName = field.getAnnotation(Column.class).name();

        if (!columnName.isEmpty()) {
            log.debug("Using callBack for columnName : {}", columnName);
            callBackWithNameGiven(callback, field, columnName);
        }

        if (columnName.isEmpty()) {
            log.debug("Column name is empty, using index for callBack...");
            callBackWithIndexGiven(callback, field);
        }
    }

    private static void callBackWithIndexGiven(EachFieldCallback callback, Field field) throws Throwable {
        int columnIndex = field.getAnnotation(Column.class).index();
        log.debug("callback for field : {} for index  : {}", field, columnIndex);
        callback.each(field, null, field.getAnnotation(Column.class).index());
    }

    private static void callBackWithNameGiven(EachFieldCallback callback, Field field, String columnName) throws Throwable {
        log.debug("callback for field :{} with columnName : {}", field, columnName);
        callback.each(field, columnName, null);
    }

    public static Field mapNameToField(Class<?> clazz, String name) throws Throwable {
        Field[] fields = clazz.getDeclaredFields();
        if (!isEmpty(fields)) {
            for (Field field : fields) {
                if (field.getName().equals(name)) {
                    return field;
                }
            }
        }
        throw new Exception("Error -- " + name + " Property of Class is not Found...");
    }
}
