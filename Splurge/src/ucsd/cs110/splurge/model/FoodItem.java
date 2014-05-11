package ucsd.cs110.splurge.model;

public class FoodItem {

	private String name;

	public FoodItem(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return getName();
	}
}
