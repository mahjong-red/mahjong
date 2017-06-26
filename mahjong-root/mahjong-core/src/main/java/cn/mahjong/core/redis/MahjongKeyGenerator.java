package cn.mahjong.core.redis;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

public class MahjongKeyGenerator implements KeyGenerator{

	@Override
	public Object generate(Object target, Method method, Object... params) {
		StringBuilder sb = new StringBuilder();  
        sb.append(target.getClass().getName());
        sb.append(":");
        sb.append(method.getName());  
        sb.append(":");
        for (Object obj : params) {  
            sb.append(obj.toString());  
        }
        return sb.toString();
	}

}
