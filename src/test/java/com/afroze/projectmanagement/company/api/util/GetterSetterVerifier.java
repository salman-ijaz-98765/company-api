package com.afroze.projectmanagement.company.api.util;

import com.google.common.base.Defaults;
import com.google.common.collect.Sets;
import org.springframework.lang.NonNull;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetterSetterVerifier<T> {

    private Class<T> type;
    private Set<String> excludes;
    private Set<String> includes;

    /**
     * Creates a getter / setter verifier to test properties for a particular class.
     *
     * @param type The class that we are testing
     */
    private GetterSetterVerifier(@NonNull final Class<T> type) {
        this.type = type;
    }

    /**
     * Method used to identify the properties that we are going to test.  If
     * no includes are specified, then all the properties are considered for
     * testing.
     *
     * @param include The name of the property that we are going to test.
     * @return This object, for method chaining.
     */
    public GetterSetterVerifier<T> include(@NonNull final String include) {
        if (includes == null) {
            includes = Sets.newHashSet();
        }

        includes.add(include);
        return this;
    }

    /**
     * Method used to identify the properties that will be ignored during
     * testing.  If no excludes are specified, then no properties will be
     * excluded.
     *
     * @param exclude The name of the property that we are going to ignore.
     * @return This object, for method chaining.
     */
    public GetterSetterVerifier<T> exclude(@NonNull final String exclude) {
        if (excludes == null) {
            excludes = Sets.newHashSet();
        }

        excludes.add(exclude);
        return this;
    }

    /**
     * Verify the class's getters and setters
     */
    public void verify() {
        try {
            final BeanInfo beanInfo = Introspector.getBeanInfo(type);
            final PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();

            for (final PropertyDescriptor property : properties) {
                if (shouldTestProperty(property)) {
                    testProperty(property);
                }
            }
        } catch (final Exception e) {
            throw new AssertionError(e.getMessage());
        }
    }

    /**
     * Determine if we need to test the property based on a few conditions.
     * 1. The property has both a getter and a setter.
     * 2. The property was not excluded.
     * 3. The property was considered for testing.
     *
     * @param property The property that we are determining if we going to test.
     * @return True if we should test the property.  False if we shouldn't.
     */
    private boolean shouldTestProperty(@NonNull final PropertyDescriptor property) {
        if (property.getWriteMethod() == null || property.getReadMethod() == null) {
            return false;
        } else if (excludes != null && excludes.contains(property.getDisplayName())) {
            return false;
        }

        return includes == null || includes.contains(property.getDisplayName());
    }

    /**
     * Test an individual property by getting the read method and write method
     * and passing the default value for the type to the setter and asserting
     * that the same value was returned.
     *
     * @param property The property that we are testing.
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    private void testProperty(@NonNull final PropertyDescriptor property) throws IllegalAccessException,
            InstantiationException,
            InvocationTargetException, NoSuchMethodException {
        final Object target = type.getDeclaredConstructor().newInstance();
        final Object setValue = Defaults.defaultValue(property.getPropertyType());

        final Method getter = property.getReadMethod();
        final Method setter = property.getWriteMethod();

        setter.invoke(target, setValue);
        final Object getValue = getter.invoke(target);

        assertEquals(setValue, getValue, property.getDisplayName() + " getter / setter do not produce the same result.");
    }

    /**
     * Factory method for easily creating a test for the getters and setters.
     *
     * @param type The class that we are testing the getters and setters for.
     * @return An object that can be used for testing the getters and setters
     * of a class.
     */
    public static <T> GetterSetterVerifier<T> forClass(@NonNull final Class<T> type) {
        return new GetterSetterVerifier<T>(type);
    }
}