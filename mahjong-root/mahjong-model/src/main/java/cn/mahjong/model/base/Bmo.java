package cn.mahjong.model.base;

import java.util.Date;

public interface Bmo extends BaseObject {

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getUpdateDate();

	public void setUpdateDate(Date updateDate);

	public Boolean getIsDelete();

	public void setIsDelete(Boolean isDelete);
}
