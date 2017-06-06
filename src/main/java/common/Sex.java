package common;

public enum Sex {

	MAN(1),
	WOMAN(2),
	OTHER(3);
	
	private int value;
	
	private Sex(int v) {
		this.value = v;
	}
	
	public int getValue() {
		return this.value;
	}
}
