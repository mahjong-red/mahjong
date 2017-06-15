package cn.mahjong.core.base;

import java.util.List;

import cn.mahjong.model.base.BaseObject;
import cn.mahjong.model.base.impl.BaseObjectImpl;
import cn.mahjong.utils.search.PageQuery;

public interface BaseService {
	
	public void save(BaseObject baseObject);

	public void saveOrUpdate(BaseObject baseObject);
	
	public void update(BaseObject baseObject);

	public BaseObject getObject(Class<? extends BaseObjectImpl> clazz, long id);

	public BaseObject getObject(String className, long id);

	public BaseObject loadObject(Class<? extends BaseObjectImpl> clazz, long id);

	public BaseObject loadObject(String className, long id);

	public List<? extends BaseObject> loadAll(Class<? extends BaseObjectImpl> clazz);

	public void delete(BaseObject object);

	public void deleteAll(Class<? extends BaseObjectImpl> clazz);

	public List<?> find(PageQuery pageQuery);
	
	public void attach(BaseObject baseObject) ;

	public void refresh(BaseObject baseObject);
	
	public void merge(BaseObject baseObject);
	
	public void deleteBatch(List<BaseObject> list);
	
	public void deleteBatch(List<Long> idlist,Class<? extends BaseObject> c);
}
