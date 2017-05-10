package cn.mahjong.enums.persist;

public enum ResourceType implements PersistEnum<ResourceType> {
	
	SITE(1, "网站"), AREA(2, "模块"), MENU(3, "菜单"), RESOURCE(4, "资源");

	private Integer val;

	private String description;

	private ResourceType(Integer val, String description) {
		this.val = val;
		this.description = description;
	}

	@Override
	public Integer getPersistedValue() {
		return val;
	}

	@Override
	public ResourceType returnEnum(Integer persistedValue) {
		for (ResourceType userStatus : ResourceType.values()) {
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
