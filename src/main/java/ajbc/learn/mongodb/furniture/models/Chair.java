package ajbc.learn.mongodb.furniture.models;

import org.bson.types.ObjectId;

public class Chair {
	
	private ObjectId _id;
	
	private String manufacturer;
	private String modelName;
	private boolean isStool;
	private float price;
	private Measurement measurement;
	
	public Chair(ObjectId _id, String manufacturer, String modelName, boolean isStool, float price,
			Measurement measurement) {
		this._id = _id;
		this.manufacturer = manufacturer;
		this.modelName = modelName;
		this.isStool = isStool;
		this.price = price;
		this.measurement = measurement;
	}

	public Chair(String manufacturer, String modelName, boolean isStool, float price,
			Measurement measurement) {
		this.manufacturer = manufacturer;
		this.modelName = modelName;
		this.isStool = isStool;
		this.price = price;
		this.measurement = measurement;
	}
	
	public Chair()
	{
		
	}



	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public boolean isStool() {
		return isStool;
	}

	public void setStool(boolean isStool) {
		this.isStool = isStool;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	@Override
	public String toString() {
		return "Chair [_id=" + _id + ", manufacturer=" + manufacturer + ", modelName=" + modelName + ", isStool="
				+ isStool + ", price=" + price + ", measurement=" + measurement + "]";
	}


	
	

}
