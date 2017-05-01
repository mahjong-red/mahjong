package cn.mahjong.model.base;

import java.io.Serializable;
import java.util.Date;

public interface BaseObject extends Serializable {

	public long getId();

	public void setId(long id);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getUpdateDate();

	public void setUpdateDate(Date updateDate);

}
