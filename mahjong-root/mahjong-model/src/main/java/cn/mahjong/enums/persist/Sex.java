package cn.mahjong.enums.persist;


public enum Sex implements PersistEnum<Sex> {
	
	MAN(1,"男"),WOMAN(2,"女");
	
	private Integer val;
	
	private String description;
	
	Sex(int val,String description){
		this.val = val;
		this.description = description;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Integer getPersistedValue() {
		return getVal();
	}

	@Override
	public Sex returnEnum(Integer persistedValue) {
		for (Sex sex : Sex.values()) {
			if (sex.val == persistedValue) {
				return sex;
			}
		}
		return null;
	}
	
}
