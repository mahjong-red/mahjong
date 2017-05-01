package cn.mahjong.model.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseObjectImpl implements BaseObject, Serializable {

	private static final long serialVersionUID = -3615693440740252497L;
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = true)
	protected Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = true)
	protected Date updateDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String toString() {
		return "id=" + this.getId() + "#" + this.getClass().getName();
	}
}
