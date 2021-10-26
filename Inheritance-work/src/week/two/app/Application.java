package week.two.app;

import week.two.model.Genesis;
import week.two.model.Upgrades;

public class Application {

	public static void main(String[] args) {
		Upgrades u = new Upgrades(70);
		
		Genesis g = new Genesis("G80", 300, 160, 26 ,2.5);
		
		g.specs();
		u.opt();
		

	}

}
