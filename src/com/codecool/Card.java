package com.codecool;

import java.io.IOException;

/**
 * Created by pata on 2017.02.27..
 */
public enum Card {
	//api change: speed,maxmagasság,max felszállási súly,range,pictureID
	BELLAIRCOBRA("P–39 Airacobra", 626, 10700, 3800, 840, "BELLAIRCOBRA.jpg"),
	BELLKINGCOBRA("P–63 Kingcobra", 660, 13100, 4900, 725, "BELLKINGCOBRA.jpg"),
	REPUBLICTHUNDERBOLT("P–47 Thunderbolt", 697, 13100, 7938, 1290, "REPUBLICTHUNDERBOLT.jpg"),
	NAMUSTANG("P–51 Mustang", 708, 12800, 5490, 1355, "NAMUSTANG.jpg"),
	MESSERSCHMITTBF("BF-109 Messer", 640, 12001, 3400, 1000, "MESSERSCHMITTBF.jpg"),
	FOCKEWULFTA("Ta-152 Höhenjaeger", 759, 15100, 5217, 1550, "FOCKEWULFTA.jpg"),
	FOCKEWULFFW("FW-190 Dora", 685, 12000, 4840, 835, "FOCKEWULFFW.jpg"),
	MESSERSCHMITTME("ME-262 Schwalbe", 900, 11450, 7130, 1050, "MESSERSCHMITTME.jpg"),
	NORTHROPBLACKWIDOW("P–61 Black Widow", 589, 10600, 16420, 982, "NORTHROPBLACKWIDOW.jpg"),
	BLOHMVOSS141("BV-141 Assymetrical", 368, 10001, 6100, 1500, "BLOHMVOSS141.jpg"),
	ANTONOVA40("A-40 Tank", 150, 4000, 7804, 150, "ANTONOVA40.jpg"),
	HORTENHO("Ho-229 Flying wing", 977, 16000, 8100, 810, "HORTENHO.jpg"),
	SUPERMARINESPITFIRE("Spitfire mk.vb", 595, 11125, 3039, 756, "SUPERMARINESPITFIRE.jpg"),
	HAWKERHURRICANE("Hurricane mk.iic", 547, 10970, 3950, 965, "HAWKERHURRICANE.jpg"),
	GLOSTERGLADIATOR("Gladiator mk.i", 420, 10020, 2088, 650, "GLOSTERGLADIATOR.jpg"),
	BRISTOLBEAUFIGHTER("Beaufighter mk.x", 515, 5795, 11521, 2000, "BRISTOLBEAUFIGHTER.jpg"),
	MIKOYANMIG3("MiG-3", 640, 12050, 3600, 820, "MIKOYANMIG3.jpg"),
	YAKOVLEVYAK7("Yak-7", 495, 9500, 3000, 643, "YAKOVLEVYAK7.jpg"),
	POLIKARPOV("Polikarpov I-16", 525, 9700, 2095, 700, "POLIKARPOV.jpg"),
	LAVOCHKINLAGG("LaGG-3", 575, 9700, 3190, 985, "LAVOCHKINLAGG.jpg"),
	NAKAJIMA("Ki-43 Hayabusa", 536, 11200, 2925, 760, "NAKAJIMA.jpg"),
	MITSUBISHIZERO("A6M Zero", 660, 10075, 2996, 800, "MITSUBISHIZERO.jpg"),
	KAWASAKI("Ki-61 Hien", 580, 11600, 3480, 580, "KAWASAKI.jpg"),
	MITSUBISHIRAIDEN("J2M3-21 Raiden", 655, 11430, 3300, 560, "MITSUBISHIRAIDEN.jpg"),
	FIATCR42("CR-42", 441, 10210, 2300, 780, "FIATCR42.jpg"),
	FIATG50("Fiat G.50", 470, 10700, 2402, 445, "FIATG50.jpg"),
	MACCHIMC202("Macchi MC-202", 600, 11500, 2930, 765, "MACCHIMC202.jpg"),
	REGGIANERE("Reggiane RE2000", 530, 11200, 2839, 545, "REGGIANERE.jpg"),
	MORANE("Morane Saulnier 406", 486, 10025, 2500, 1015, "MORANE.jpg"),
	DEWOITINE("Dewoitine DW520", 560, 10050, 2785, 1250, "DEWOITINE.jpg"),
	BLOCH("Bloch MB170", 530, 11000, 7175, 1650, "BLOCH.jpg"),
	LIORE("Liore-et-Olivier LeO 45", 495, 9000, 11398, 2900, "LIORE.jpg"),
	VALTION("VL Myrsky", 535, 9500, 3213, 500, "VALTION.jpg"),
	BUFFALO("Brewster Buffalo", 517, 10100, 3247, 670, "BUFFALO.jpg"),
	CURTISHAWK("P–36 Hawk", 500, 9967, 2732, 860, "CURTISHAWK.jpg"),
	FOKKER("Fokker D.XXI", 460, 11350, 2000, 930, "FOKKER.jpg"),
	HEINKEL("Heinkel HE-122A0", 488, 8000, 2000, 600, "HEINKEL.jpg"),
	MAVAG("Mavag Heja", 530, 10500, 3240, 580, "MAVAG.jpg"),
	JUNKERS88("Junkers JU-88", 510, 9000, 14000, 2430, "JUNKERS88.jpg"),
	JUNKERS52("Junkers JU-52", 195, 3400, 7000, 1010, "JUNKERS52.jpg");

	private final String name;
	private final int speed;
	private final int maxHeight;
	private final int maxTakeoffWeight;
	private final int range;
	private final String pictureID;

	Card(String name, int speed, int maxHeight, int maxTakeoffWeight, int range, String pictureID) {

		this.name = name;
		this.speed = speed;
		this.maxHeight = maxHeight;
		this.maxTakeoffWeight = maxTakeoffWeight;
		this.range = range;
		this.pictureID = pictureID;

	}

	public String getName() {
		return name;
	}

	public int getSpeed() {
		return speed;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public int getMaxTakeoffWeight() {
		return maxTakeoffWeight;
	}

	public int getRange() {
		return range;
	}

	public String getID() {
		return pictureID;
	}

	public String toString() {

		return this.getName()  +
				" :\t(1) Speed: " + this.getSpeed() +
				" || (2) Maximum height: " + this.getMaxHeight() +
				" || (3) Maximum takeoff weight: " + this.getMaxTakeoffWeight() +
				" || (4) Maximum range: " + this.getRange();
	}
}
