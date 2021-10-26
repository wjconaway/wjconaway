package week.two.model;

public class Vehical {
	String model;
	int hp;
	int topSpeed;
	int fuelEco;
	double engine;
	
	public Vehical(String model, int hp, int topSpeed, int fuelEco,double engine) {
		this.model = model;
		this.hp = hp;
		this.topSpeed = topSpeed;
		this.fuelEco = fuelEco;
		this.engine = engine;
	}
	
	public void specs() {
		System.out.println(this.model + " has "+ hp+ "hp"+" and a top speed of "+topSpeed
				+ ".  \nThe fuel economy has a combined EPA of "+fuelEco+" and comes "
						+ "with a "+engine+"liter engine.");
	}
	
		
	
	
	
	

}
