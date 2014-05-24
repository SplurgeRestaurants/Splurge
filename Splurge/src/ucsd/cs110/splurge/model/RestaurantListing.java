package ucsd.cs110.splurge.model;

public class RestaurantListing {

	private String mRestaurantName;
	private int mRestaurantId;

	public RestaurantListing(String name, int id) {
		setRestaurantName(name);
		setRestaurantId(id);
	}

	public String getRestaurantName() {
		return mRestaurantName;
	}

	public int getRestaurantId() {
		return mRestaurantId;
	}

	private void setRestaurantName(String name) {
		mRestaurantName = name;
	}

	private void setRestaurantId(int id) {
		mRestaurantId = id;
	}

	@Override
	public boolean equals(Object o) {
		return o != null
				&& (o instanceof RestaurantListing)
				&& ((RestaurantListing) o).getRestaurantId() == getRestaurantId();
	}
}
