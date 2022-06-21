package ajbc.learn.mongodb.furniture.models;

public class Pillow {
	
	private PillowShape shape;
	private PillowColor color;
	
	
	
	public Pillow(PillowShape shape, PillowColor color) {
		this.shape = shape;
		this.color = color;
	}

	
	public PillowShape getShape() {
		return shape;
	}

	public void setShape(PillowShape shape) {
		this.shape = shape;
	}

	public PillowColor getColor() {
		return color;
	}

	public void setColor(PillowColor color) {
		this.color = color;
	}


	
	@Override
	public String toString() {
		return "Pillow [shape=" + shape + ", color=" + color + "]";
	}



	public enum PillowShape {
		
		SQUARE,
		RECTANGLE;
	}
	
	public enum PillowColor {
		
		RED,
		GREEN,
		BLUE,
		YELLOW,
		PURPLE,
		BROWN;
	}

}
