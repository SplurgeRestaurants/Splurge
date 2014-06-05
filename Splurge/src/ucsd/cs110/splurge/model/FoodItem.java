package ucsd.cs110.splurge.model;

import android.graphics.Bitmap;

public class FoodItem {

	private String mName;
	private Bitmap mImage;
	private boolean mSelected;
	private double mPrice;
	private String mDescription;

	public FoodItem(String name) {
		this.mName = name;
	}

	public FoodItem(FoodItem item) {
		mName = item.mName;
		mImage = item.mImage;
		mSelected = item.mSelected;
		mPrice = item.mPrice;
		mDescription = item.mDescription;
	}

	public String getName() {
		return mName;
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
		return mSelected;
	}

	public void setSelected(boolean selected) {
		this.mSelected = selected;
	}

	public double getPrice() {
		return mPrice;
	}

	public void setPrice(double d) {
		this.mPrice = d;
	}

	public void setDescription(String description) {
		mDescription = description;
	}

	public String getDescription() {
		return mDescription;
	}
}
