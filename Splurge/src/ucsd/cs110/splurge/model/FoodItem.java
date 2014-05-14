package ucsd.cs110.splurge.model;

public class FoodItem {

	private String name;
	private int imageId;
	private boolean selected;
	private int price;

	public FoodItem(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return getName();
	}

	public void setImage(int imageId) {
		this.imageId = imageId;
	}

	public int getImage() {
		return imageId;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}