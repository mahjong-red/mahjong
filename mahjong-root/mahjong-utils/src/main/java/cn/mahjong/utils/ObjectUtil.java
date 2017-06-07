package cn.mahjong.utils;

import java.lang.reflect.Method;

import org.apache.commons.lang3.reflect.MethodUtils;

public class ObjectUtil {

	public static Object getInvokeValue(Object obj, String methodName) {
		try {
			Method method = MethodUtils.getAccessibleMethod(obj.getClass(), methodName);
			return method.invoke(obj);
		} catch (Exception e) {
			return null;
		}
	}
}
