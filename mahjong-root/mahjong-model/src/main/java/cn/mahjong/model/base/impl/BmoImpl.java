package cn.mahjong.model.base.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.mahjong.model.base.Bmo;

/**
 * 业务模型对象 所有业务模型对象的基类
 * 
 * @author lzq
 */
@MappedSuperclass
public class BmoImpl extends BaseObjectImpl implements Bmo {

	private static final long serialVersionUID = 4219499092061670870L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = true)
	protected Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = true)
	protected Date updateDate;

	@Column(name="is_delete",nullable=false,columnDefinition="BIT DEFAULT FALSE")
	protected Boolean isDelete = false;
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
}
