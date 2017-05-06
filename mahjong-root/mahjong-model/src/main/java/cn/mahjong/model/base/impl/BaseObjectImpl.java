package cn.mahjong.model.base.impl;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import cn.mahjong.model.base.BaseObject;

@MappedSuperclass
public class BaseObjectImpl implements BaseObject, Serializable {

	private static final long serialVersionUID = -3615693440740252497L;
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
