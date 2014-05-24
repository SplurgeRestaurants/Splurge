package ucsd.cs110.splurge.model;

import android.graphics.Bitmap;

public class FoodItem {

	private String name;
	private Bitmap mImage;
	private boolean selected;
	private double price;

	public FoodItem(String name) {
		this.name = name;
	}

	public FoodItem(FoodItem item) {
		name = item.name;
		mImage = item.mImage;
		selected = item.selected;
		price = item.price;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	public void setImage(Bitmap image) {
		mImage = image;
	}

	public Bitmap getImage() {
		return mImage;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = d;
	}
}
