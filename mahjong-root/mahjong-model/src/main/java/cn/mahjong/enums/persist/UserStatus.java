package cn.mahjong.enums.persist;

public enum UserStatus implements PersistEnum<UserStatus> {
	
	ENABLE(1, "启用"), DISABLE(2, "未启用");

	private Integer val;

	private String description;

	private UserStatus(Integer val, String description) {
		this.val = val;
		this.description = description;
	}

	@Override
	public Integer getPersistedValue() {
		return val;
	}

	@Override
	public UserStatus returnEnum(Integer persistedValue) {
		for (UserStatus userStatus : UserStatus.values()) {
			if (userStatus.getPersistedValue() == persistedValue) {
				return userStatus;
			}
		}
		return null;
	}

	public Integer getVal() {
		return val;
	}

	public void setVal(Integer val) {
		this.val = val;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
