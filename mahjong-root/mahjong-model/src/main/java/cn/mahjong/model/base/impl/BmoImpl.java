package cn.mahjong.model.base.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import cn.mahjong.model.base.Bmo;
import cn.mahjong.model.sys.user.User;
import cn.mahjong.model.sys.user.impl.UserImpl;

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

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserImpl.class)
	@Cascade(value = { CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "create_user_id")
	protected User createUser;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserImpl.class)
	@Cascade(value = { CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "update_user_id")
	protected User updateUser;
	
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

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
}
