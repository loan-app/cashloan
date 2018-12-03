package com.rongdu.cashloan.cl.util;

import org.apache.commons.beanutils.*;
import org.apache.commons.beanutils.expression.Resolver;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

/**
 * 增加版BeanUtils，可设置属性的映射方法，例如加前后辍，转下划线等
 */
public class BeanUtilsExt {

    private static PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance().getPropertyUtils();

    private static ConvertUtilsBean convertUtils = BeanUtilsBean.getInstance().getConvertUtils();
    
    public static void main(String[] args) throws Exception {
        Test1 test1 = new Test1();
        test1.setName("zyx");
        test1.setAge(18);
        Test2 test2 = new Test2();

        // java8
//        copyPropertiesExt(test2, test1, orig -> orig + "D30");
        // java7
        copyPropertiesExt(test2, test1, new KeyMapping() {
            @Override
            public String mapping(String orig) {
                return orig + "D30";
            }
        });

        System.out.println(test1);
        System.out.println(test2);
    }

    private static Object convert(Object value, Class type) {
        Converter converter = convertUtils.lookup(type);
        if (converter != null) {
            return converter.convert(type, value);
        } else {
            return value;
        }
    }

    private static void copyProperty(Object bean, String name, Object value, KeyMapping keyMapping)
            throws IllegalAccessException, InvocationTargetException {

        // Trace logging (if enabled)

        // Resolve any nested expression to get the actual target bean
        Object target = bean;
        Resolver resolver = propertyUtils.getResolver();
        while (resolver.hasNested(name)) {
            try {
                target = propertyUtils.getProperty(target, resolver.next(name));
                name = resolver.remove(name);
            } catch (NoSuchMethodException e) {
                return; // Skip this property setter
            }
        }

        // Declare local variables we will require
        String propName = resolver.getProperty(name); // Simple name of target property
        Class type = null;                            // Java type of target property
        int index  = resolver.getIndex(name);         // Indexed subscript value (if any)
        String key = resolver.getKey(name);           // Mapped key value (if any)

        //
        // Calculate the target property type
        if (target instanceof DynaBean) {
            DynaClass dynaClass = ((DynaBean) target).getDynaClass();
            DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
            if (dynaProperty == null) {
                return; // Skip this property setter
            }
            type = dynaProperty.getType();
        } else {
            PropertyDescriptor descriptor = null;
            try {
                descriptor =
                        propertyUtils.getPropertyDescriptor(target, keyMapping.mapping(propName));
                if (descriptor == null) {
                    return; // Skip this property setter
                }
            } catch (NoSuchMethodException e) {
                return; // Skip this property setter
            }
            type = descriptor.getPropertyType();
            if (type == null) {
                // Most likely an indexed setter on a POJB only
                return;
            }
        }

        // Convert the specified value to the required type and store it
        if (index >= 0) {                    // Destination must be indexed
            value = convert(value, type.getComponentType());
            try {
                propertyUtils.setIndexedProperty(target, propName,
                        index, value);
            } catch (NoSuchMethodException e) {
                throw new InvocationTargetException
                        (e, "Cannot set " + propName);
            }
        } else if (key != null) {            // Destination must be mapped
            // Maps do not know what the preferred data type is,
            // so perform no conversions at all
            // FIXME - should we create or support a TypedMap?
            try {
                propertyUtils.setMappedProperty(target, propName,
                        key, value);
            } catch (NoSuchMethodException e) {
                throw new InvocationTargetException
                        (e, "Cannot set " + propName);
            }
        } else {                             // Destination must be simple
            value = convert(value, type);
            try {
                propertyUtils.setSimpleProperty(target, keyMapping.mapping(propName), value);
            } catch (NoSuchMethodException e) {
                throw new InvocationTargetException
                        (e, "Cannot set " + propName);
            }
        }

    }

    public static void copyPropertiesExt(Object dest, Object orig, KeyMapping keyMapping) throws Exception {
        if (keyMapping == null) {
            keyMapping = new KeyMapping() {
                @Override
                public String mapping(String orig) {
                    return orig;
                }
            };
        }
        // Validate existence of the specified beans
        if (dest == null) {
            throw new IllegalArgumentException
                    ("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }
        // Copy the properties, converting as necessary
        if (orig instanceof DynaBean) {
            DynaProperty[] origDescriptors =
                    ((DynaBean) orig).getDynaClass().getDynaProperties();
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                // Need to check isReadable() for WrapDynaBean
                // (see Jira issue# BEANUTILS-61)
                if (propertyUtils.isReadable(orig, name) &&
                        propertyUtils.isWriteable(dest, name)) {
                    Object value = ((DynaBean) orig).get(name);
                    copyProperty(dest, name, value, keyMapping);
                }
            }
        } else if (orig instanceof Map) {
            Iterator entries = ((Map) orig).entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                String name = (String)entry.getKey();
                if (propertyUtils.isWriteable(dest, name)) {
                    copyProperty(dest, name, entry.getValue(), keyMapping);
                }
            }
        } else /* if (orig is a standard JavaBean) */ {
            PropertyDescriptor[] origDescriptors =
                    propertyUtils.getPropertyDescriptors(orig);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                if (propertyUtils.isReadable(orig, name) &&
                        propertyUtils.isWriteable(dest, keyMapping.mapping(name))) {
                    try {
                        Object value =
                                propertyUtils.getSimpleProperty(orig, name);
                        copyProperty(dest, name, value, keyMapping);
                    } catch (NoSuchMethodException e) {
                        // Should not happen
                    }
                }
            }
        }
    }
}
