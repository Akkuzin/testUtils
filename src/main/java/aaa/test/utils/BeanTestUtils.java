package aaa.test.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

public class BeanTestUtils {

	public static Field getField(Class<?> clazz, String name) {
		try {
			return clazz.getDeclaredField(name);
		} catch (NoSuchFieldException e) {
			return null;
		}
	}

	public static Field getField(Class<?> clazz, PropertyDescriptor property) {
		return property == null ? null : getField(clazz, property.getName());
	}

}
