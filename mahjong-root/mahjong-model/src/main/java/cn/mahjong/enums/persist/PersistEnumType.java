package cn.mahjong.enums.persist;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.springframework.util.ObjectUtils;

public class PersistEnumType implements UserType, ParameterizedType {

	private Method returnEnum;

	private Method getPersistedValue;

	private Class<Enum<?>> enumClass;

	private Object enumObject;

	/**
	 * This method uses the parameter values passed during enum mapping
	 * definition and sets corresponding properties defined
	 */
	@SuppressWarnings("unchecked")
	public void setParameterValues(Properties parameters) {
		if (parameters != null) {
			try {
				enumClass = (Class<Enum<?>>) Class.forName(parameters.get("enumClass").toString());
				enumObject = enumClass.getEnumConstants()[0];
				getPersistedValue = enumClass.getMethod("getPersistedValue");
				returnEnum = enumClass.getMethod("returnEnum", new Class[]{Integer.class});
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public int[] sqlTypes() {
		return new int[]{Types.VARCHAR};
	}

	public Class<?> returnedClass() {
		return enumClass;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		return ObjectUtils.nullSafeEquals(x, y);
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	public boolean isMutable() {
		return false;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		Object deepCopy = deepCopy(value);

		if (!(deepCopy instanceof Serializable))
			return (Serializable) deepCopy;

		return null;
	}

	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return deepCopy(cached);
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return deepCopy(original);
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
		Integer value = (Integer) IntegerType.INSTANCE.get(rs, names[0], session);
		if (value == null)
			return null;
		else {
			try {
				return returnEnum.invoke(enumObject, new Object[]{value});
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
		if (value == null) {
			StringType.INSTANCE.set(st, null, index, session);
		} else {
			try {
				StringType.INSTANCE.set(st, getPersistedValue.invoke(value).toString(), index, session);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

	}
}
