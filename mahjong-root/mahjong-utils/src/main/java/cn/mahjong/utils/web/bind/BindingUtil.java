package cn.mahjong.utils.web.bind;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.proxy.HibernateProxyHelper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;

import cn.mahjong.model.base.BaseObject;
import cn.mahjong.model.base.impl.BaseObjectImpl;
import cn.mahjong.utils.search.PageQuery;
import cn.mahjong.utils.search.SearchType;

public class BindingUtil {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static SimpleDateFormat dataTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static void bindPageProperty(PageQuery pageQuery, HttpServletRequest request) {
		ServletRequestDataBinder binder = new ServletRequestDataBinder(pageQuery);
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Double.class, true));
		binder.bind(request);
	}

	@SuppressWarnings("unchecked")
	public static void bindSearchProperty(PageQuery pageQuery, HttpServletRequest request) {
		Map<String, String[]> paramaterMap = request.getParameterMap();

		for (Entry<String, String[]> entry : paramaterMap.entrySet()) {
			if (StringUtils.startsWith(entry.getKey(), "__search__")) {

				String originalParamaterName = entry.getKey();
				String paramaterName = null;
				SearchType searchType = null;
				Object value1 = null;
				Object value2 = null;

				paramaterName = StringUtils.substringAfter(originalParamaterName, "__search__");

				Field field = ReflectionUtils.findField(pageQuery.getTargetClass(), paramaterName);
				if (field == null) {
					throw new RuntimeException("Can not find field[" + originalParamaterName + "] in target class["
							+ pageQuery.getClass().getName() + "]");
				}

				Class<?> fieldClass = field.getType();
				String stringValue = request.getParameter(originalParamaterName);

				if (String.class.equals(fieldClass)) {
					value1 = stringValue;
					searchType = SearchType.EQUAL;
				}
				else if (Integer.class.equals(fieldClass) || Integer.TYPE.equals(fieldClass)) {
					if (stringValue.contains("<>")) {
						String strStartValue = StringUtils.substringBefore(stringValue, "<>");
						String strEndValue = StringUtils.substringAfter(stringValue, "<>");

						value1 = StringUtils.isBlank(strStartValue) ? Integer.MIN_VALUE : new Integer(strStartValue);
						value2 = StringUtils.isBlank(strStartValue) ? Integer.MAX_VALUE : new Integer(strEndValue);
						searchType = SearchType.RANGE;
					}
					else {
						value1 = new Integer(stringValue);
						searchType = SearchType.EQUAL;
					}
				}
				else if (Long.class.equals(fieldClass) || Long.TYPE.equals(fieldClass)) {
					if (stringValue.contains("<>")) {
						String strStartValue = StringUtils.substringBefore(stringValue, "<>");
						String strEndValue = StringUtils.substringAfter(stringValue, "<>");

						value1 = StringUtils.isBlank(strStartValue) ? Long.MIN_VALUE : new Long(strStartValue);
						value2 = StringUtils.isBlank(strStartValue) ? Long.MAX_VALUE : new Long(strEndValue);
						searchType = SearchType.RANGE;
					}
					else {
						value1 = new Long(stringValue);
						searchType = SearchType.EQUAL;
					}
				}
				else if (Short.class.equals(fieldClass) || Short.TYPE.equals(fieldClass)) {
					if (stringValue.contains("<>")) {
						String strStartValue = StringUtils.substringBefore(stringValue, "<>");
						String strEndValue = StringUtils.substringAfter(stringValue, "<>");

						value1 = StringUtils.isBlank(strStartValue) ? Short.MIN_VALUE : new Short(strStartValue);
						value2 = StringUtils.isBlank(strStartValue) ? Short.MAX_VALUE : new Short(strEndValue);
						searchType = SearchType.RANGE;
					}
					else {
						value1 = new Short(stringValue);
						searchType = SearchType.EQUAL;
					}
				}
				else if (Double.class.equals(fieldClass) || Double.TYPE.equals(fieldClass)) {
					if (stringValue.contains("<>")) {
						String strStartValue = StringUtils.substringBefore(stringValue, "<>");
						String strEndValue = StringUtils.substringAfter(stringValue, "<>");

						value1 = StringUtils.isBlank(strStartValue) ? Double.MIN_VALUE : new Double(strStartValue);
						value2 = StringUtils.isBlank(strStartValue) ? Double.MAX_VALUE : new Double(strEndValue);
						searchType = SearchType.RANGE;
					}
					else {
						value1 = new Double(stringValue);
						searchType = SearchType.EQUAL;
					}
				}
				else if (Float.class.equals(fieldClass) || Float.TYPE.equals(fieldClass)) {
					if (stringValue.contains("<>")) {
						String strStartValue = StringUtils.substringBefore(stringValue, "<>");
						String strEndValue = StringUtils.substringAfter(stringValue, "<>");

						value1 = StringUtils.isBlank(strStartValue) ? Float.MIN_VALUE : new Float(strStartValue);
						value2 = StringUtils.isBlank(strStartValue) ? Float.MAX_VALUE : new Float(strEndValue);
						searchType = SearchType.RANGE;
					}
					else {
						value1 = new Float(stringValue);
						searchType = SearchType.EQUAL;
					}
				}
				else if (BigDecimal.class.equals(fieldClass)) {
					if (stringValue.contains("<>")) {
						String strStartValue = StringUtils.substringBefore(stringValue, "<>");
						String strEndValue = StringUtils.substringAfter(stringValue, "<>");

						value1 =
								StringUtils.isBlank(strStartValue) ? new BigDecimal(Double.MIN_VALUE) : new BigDecimal(
										strStartValue);
						value2 =
								StringUtils.isBlank(strStartValue) ? new BigDecimal(Double.MAX_VALUE) : new BigDecimal(
										strEndValue);
						searchType = SearchType.RANGE;
					}
					else {
						value1 = new BigDecimal(stringValue);
						searchType = SearchType.EQUAL;
					}
				}
				else if (Date.class.equals(fieldClass)) {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					if (stringValue.contains("<>")) {
						try {
							String strStartValue = StringUtils.substringBefore(stringValue, "<>");
							String strEndValue = StringUtils.substringAfter(stringValue, "<>");

							if (StringUtils.isNotBlank(strStartValue)) {
								value1 = simpleDateFormat.parse(strStartValue);
							}

							if (StringUtils.isNotBlank(strEndValue)) {
								Calendar calender = Calendar.getInstance();
								calender.setTime(simpleDateFormat.parse(strEndValue));
								calender.add(Calendar.DAY_OF_YEAR, 1);
								value2 = calender.getTime();
							}
						}
						catch (ParseException e) {
							throw new RuntimeException(e);
						}
					}
					searchType = SearchType.RANGE;
				}
				else if (Boolean.class.equals(fieldClass)) {
					value1 = new Boolean(stringValue);
					searchType = SearchType.EQUAL;
				}
				else if (BaseObject.class.isAssignableFrom(fieldClass)) {
					paramaterName = paramaterName + ".id";
					value1 = new Long(stringValue);
					searchType = SearchType.EQUAL;
				}
				else {
					throw new RuntimeException("Can not found paramater [" + originalParamaterName + "]");
				}
				pageQuery.addSearchItem(paramaterName, searchType, value1, value2);
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void registerEditor(DataBinder dataBinder, String attributeName, MutablePropertyValues values, Object value) {
		Object object = (BaseObject) dataBinder.getTarget();
		Field field = ReflectionUtils.findField(object.getClass(), attributeName);
		if (field == null) {
			throw new RuntimeException("Field not found:" + attributeName + " in " + object.getClass().getName() );
		}
		
		Class fieldClass = field.getType();
		if (String.class.equals(fieldClass)) {
			dataBinder.registerCustomEditor(String.class, attributeName,  new StringTrimmerEditor(true));
			values.addPropertyValue(attributeName, value);
		}
		
	}
	
	public static void bindObject(BaseObject baseObject, HttpServletRequest request, Validator validator) {
		MutablePropertyValues mpvs = new MutablePropertyValues();
		addBindValues(mpvs, request);
		bindObject(baseObject, mpvs, validator);
	}
	
	public static void bindObject(BaseObject baseObject, Map<String, Object> requestParamaterMap, Validator validator) {
		MutablePropertyValues mpvs = new MutablePropertyValues();
		addBindValues(mpvs, requestParamaterMap);
		bindObject(baseObject, mpvs, validator);
	}
	
	@SuppressWarnings("unchecked")
	public static void addBindValues(MutablePropertyValues mpvs, ServletRequest request) {
		Map<String, Object> parameterMap = request.getParameterMap();
		
		for (Entry<String , Object> entry : parameterMap.entrySet()) {
			String key = entry.getKey();
			String[] values = (String[]) entry.getValue();
			
			if (StringUtils.equals("id", key)) {
				continue;
			}

			mpvs.addPropertyValue(key, StringUtils.join(values, ","));
		}
	}
	
	public static void addBindValues(MutablePropertyValues mpvs, Map<String, Object> requestParamaterMap) {
		for (Entry<String , Object> entry : requestParamaterMap.entrySet()) {
			String key = entry.getKey();
			String value = (String) entry.getValue();
			
			if (StringUtils.equals("id", key)) {
				continue;
			}

			mpvs.addPropertyValue(key, value);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void bindObject(BaseObject baseObject, MutablePropertyValues mpvs, Validator validator) {
		List<PropertyValue> propertyValueList = mpvs.getPropertyValueList();
		DataBinder dataBinder = new DataBinder(baseObject);
		
		for (PropertyValue property : propertyValueList) {
			String propertyName = property.getName();
			
			Class targetObjectClass = HibernateProxyHelper.getClassWithoutInitializingProxy(baseObject);
			
			Field field = ReflectionUtils.findField(targetObjectClass, property.getName());
			if (field == null) {
				continue;
			}
			
			Class fieldClass = field.getType();
			if (String.class.equals(fieldClass)) {
				dataBinder.registerCustomEditor(String.class, propertyName, new StringTrimmerEditor(true));
			}
			else if (Date.class.equals(fieldClass)) {
				String dateStr = (String) property.getValue();
				
				if (dateStr != null && dateStr.length() > 10) {
					dataBinder.registerCustomEditor(Date.class, propertyName, new CustomDateEditor(dataTimeFormat, true));
				}
				else {
					dataBinder.registerCustomEditor(Date.class, propertyName, new CustomDateEditor(dateFormat, true));
				}
			}
			else if (Boolean.class.equals(fieldClass) || Boolean.TYPE.equals(fieldClass)) {
				dataBinder.registerCustomEditor(Boolean.class, propertyName, new CustomBooleanEditor("true", "false", true));
			}
			else if (isNumberType(fieldClass)) {
				if (Integer.TYPE.equals(fieldClass)) {
					fieldClass = Integer.class;
				}
				else if (Long.TYPE.equals(fieldClass)) {
					fieldClass = Long.class;
				}
				else if (Double.TYPE.equals(fieldClass)) {
					fieldClass = Double.class;
				}
				else if (Short.TYPE.equals(fieldClass)) {
					fieldClass = Short.class;
				}
				dataBinder.registerCustomEditor(fieldClass, propertyName, new CustomNumberEditor(fieldClass, true));
			}
			else if (BaseObject.class.isAssignableFrom(fieldClass)) {
				if (!fieldClass.isInterface()) {
					//dataBinder.registerCustomEditor(fieldClass, field.getName(), new AniuBussinessObjectEdit(fieldClass, (BaseService) SpringContextUtil.getBean("baseService")));
				}
				else {
					String className = fieldClass.getName() + "Impl";
					Class<? extends BaseObjectImpl> clazz = null;
					try {
						clazz = (Class<? extends BaseObjectImpl>) Class.forName(className);
					}
					catch (ClassNotFoundException e) {
						new RuntimeException("Class not found:" + className, e);
					}
					//dataBinder.registerCustomEditor(fieldClass, field.getName(), new AniuBussinessObjectEdit(clazz, (BaseService) SpringContextUtil.getBean("baseService")));
				}
			}
			else if (Collection.class.isAssignableFrom(fieldClass)) {
				Type fieldGenericType = field.getGenericType();
				if (fieldGenericType instanceof ParameterizedType) {
					ParameterizedType pt = (ParameterizedType) fieldGenericType;
					Class targetClass = (Class)pt.getActualTypeArguments()[0];
					if (targetClass.isInterface()) {
						String className = targetClass.getName() + "Impl";
						try {
							targetClass = Class.forName(className);
						}
						catch (ClassNotFoundException e) {
							new RuntimeException("Class not found:" + className, e);
						}
					}
					
					if (property.getValue() instanceof String) {
						String value = (String) property.getValue();
						if (isJsonString(value)) {
							//dataBinder.registerCustomEditor(fieldClass, field.getName(), new AniuCollectionJsonEdit(fieldClass, targetClass, (BaseService) SpringContextUtil.getBean("baseService")));
						}
						else {
							//dataBinder.registerCustomEditor(fieldClass, field.getName(), new AniuCollectionStringArrayEdit(fieldClass, targetClass, (BaseService) SpringContextUtil.getBean("baseService")));
						}
					}
				}
			}
		}
		
		dataBinder.bind(mpvs);
		
		if (validator != null) {
			dataBinder.addValidators(validator);
			dataBinder.validate();
		}
		
		BindingResult bindingResult = dataBinder.getBindingResult();
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (ObjectError error : bindingResult.getAllErrors()) {
				errorMessage += error.getDefaultMessage() + "\n";
			}
			throw new RuntimeException(errorMessage);
		}
		
	}
	
	private static boolean isJsonString(String value) {
		if ((StringUtils.startsWith(value, "{") && StringUtils.endsWith(value, "}"))
			||(StringUtils.startsWith(value, "[") && StringUtils.endsWith(value, "]"))){
			return true;
		}
		else {
			return false;
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static boolean isNumberType(Class fieldClass) {
		if (BigDecimal.class.equals(fieldClass)) {
			return true;
		}
		else if (Long.class.equals(fieldClass) || Long.TYPE.equals(fieldClass)) {
			return true;
		}
		else if (Integer.class.equals(fieldClass) || Integer.TYPE.equals(fieldClass)) {
			return true;
		}
		else if (Double.class.equals(fieldClass) || Double.TYPE.equals(fieldClass)) {
			return true;
		}
		else if (Short.class.equals(fieldClass) || Short.TYPE.equals(fieldClass)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List convertToDtoList(List<?> datas, Class dataClass, Class dtoClass) {
		List<Object> dtoResult = new ArrayList<Object>();
		
		try {
			for (Object data : datas) {
				if (dtoClass != null) {
					Constructor<?> constructor = dtoClass.getConstructor(new Class[] { dataClass });
					Object dto = constructor.newInstance(new Object[] { data });
					dtoResult.add(dto);
				}
				else {
					dtoResult.add(data);
				}
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return dtoResult;
	}

}
