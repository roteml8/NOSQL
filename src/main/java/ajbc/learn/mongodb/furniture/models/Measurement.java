package ajbc.learn.mongodb.furniture.models;

import org.bson.types.ObjectId;

public class Measurement {
	
	private static int counter = 0;
	private int _id;
	private double height;
	private double weight;
	private double depth;
	
	public Measurement(double height, double weight, double depth) {
		this._id = counter++;
		this.height = height;
		this.weight = weight;
		this.depth = depth;
	}
	
	
	
	public Measurement(int _id, double height, double weight, double depth) {
		this._id = _id;
		this.height = height;
		this.weight = weight;
		this.depth = depth;
	}



	public Measurement()
	{
		
	}



	public int get_id() {
		return _id;
	}



	public void set_id(int _id) {
		this._id = _id;
	}



	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}



	@Override
	public String toString() {
		return "Measurement [_id=" + _id + ", height=" + height + ", weight=" + weight + ", depth=" + depth + "]";
	}


	
	

}
