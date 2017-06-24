package cn.mahjong.core.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class RedisValueSerializer implements RedisSerializer<Serializable> {

	/**
	 * @see org.springframework.data.redis.serializer.RedisSerializer#serialize(java.lang.Object)
	 */
	@Override
	public byte[] serialize(Serializable t) throws SerializationException {
		
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        
        try {
        	oos = new ObjectOutputStream(baos);
        	
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            
            oos.writeObject(t);
            
            byte[] bytes = baos.toByteArray();
            
            return bytes;
        } catch (Exception e) {
        } finally {
        	try {
				baos.close();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        return null;
	}

	/**
	 * @see org.springframework.data.redis.serializer.RedisSerializer#deserialize(byte[])
	 */
	@Override
	public Serializable deserialize(byte[] bytes) throws SerializationException {
		
		if (bytes == null || bytes.length <= 0){
			return null;
		}
		
		ByteArrayInputStream bais = null;
		
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            
            return (Serializable) ois.readObject();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	try {
				bais.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        return null;
	}
}
