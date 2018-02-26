/*
 * code https://github.com/mohsen-mahmoudi/excel-object-mapping
 */
package io.github.mapper.excel.util;

import java.lang.reflect.Field;

/**
 * @author redcrow
 * @author Mohsen.Mahmoudi
 */
public interface EachFieldCallback {

    /**
     *
     * @param field The field to callBack
     * @param name The name of the field (if given)
     * @param index The index of the field (if given)
     * @throws Throwable
     */
    void each(Field field, String name, Integer index) throws Throwable;
}
