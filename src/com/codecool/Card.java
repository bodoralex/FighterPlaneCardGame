package com.codecool;


/**
 * Created by pata on 2017.02.27..
 */
public enum Card{
//api change: speed,maxmagasság,max felszállási súly,range
    BELLAIRCOBRA ("P–39 Airacobra",626, 10700, 3800, 840),
    BELLKINGCOBRA("P–63 Kingcobra",660,13100,4900,725),
    REPUBLICTHUNDERBOLT ("P–47 Thunderbolt",697, 13100, 7938, 1290),
    NAMUSTANG ("P–51 Mustang",708, 12800, 5490, 1355),
    MESSERSCHMITTBF ("BF-109 Messer",640, 12000, 3400, 1000),
    FOCKEWULFTA ("Ta-152 Höhenjaeger",759, 15100, 5217, 1500),
    FOCKEWULFFW ("FW-190 Dora",685, 12000, 4840, 835),
    MESSERSCHMITTME ("ME-262 Schwalbe",900, 11450, 7130, 1050),
    NORTHROPBLACKWIDOW ("P–61 Black Widow",589, 10600, 16420, 982),
    BLOHMVOSS141 ("BV-141 Assymetrical",368, 10000, 6100, 1900),
    ANTONOVA40 ("A-40 Tank",150, 4000, 7804, 500),
    HORTENHO ("Ho-229 Flying wing",977, 16000, 8100, 800),
    SUPERMARINESPITFIRE ("Spitfire mk.vb",595, 11125, 3039, 756),
    HAWKERHURRICANE ("Hurricane mk.iic",547, 10970, 3950, 965),
    GLOSTERGLADIATOR ("Gladiator mk.i",420, 10000, 2088, 650),
    BRISTOLBEAUFIGHTER ("Beaufighter mk.x",515, 5795, 11521, 2000),
    MIKOYANMIG3 ("MiG-3",640, 12000, 3600, 820),
    YAKOVLEVYAK7 ("Yak-7",495, 9500, 3000, 643),
    POLIKARPOV ("Polikarpov I-16",525, 9700, 2095, 700),
    LAVOCHKINLAGG ("LaGG-3",575, 9700, 3190, 1000),
    NAKAJIMA ("Ki-43 Hayabusa",536, 11200, 2925, 1100),
    MITSUBISHIZERO ("A6M Zero",660, 10000, 2996, 1600),
    KAWASAKI ("Ki-61 Hien",580, 11600, 3480, 580),
    MITSUBISHIRAIDEN ("J2M3-21 Raiden",655, 11430, 3300, 560),
    FIATCR42 ("CR-42",441, 10210, 2300, 780),
    FIATG50 ("Fiat G.50",470, 10700, 2402, 445),
    MACCHIMC202 ("Macchi MC-202",600, 11500, 2930, 765),
    REGGIANERE ("Reggiane RE2000",530, 11200, 2839, 545),
    MORANE ("Morane Saulnier 406",486, 10000, 2500, 1000),
    DEWOITINE ("Dewoitine DW520",560, 10000, 2785, 1250),
    BLOCH ("Bloch MB170",530, 11000, 7175, 1650),
    LIORÉ ("Lioré-et-Olivier LeO 45",495, 9000, 11398, 2900),
    VALTION ("VL Myrsky",535, 9500, 3213, 500),
    BUFFALO ("Brewster Buffalo",517, 10100, 3247, 1500),
    CURTISHAWK ("P–36 Hawk",500, 9967, 2732, 860),
    FOKKER ("Fokker D.XXI",460, 11350, 2000, 930),
    HEINKEL ("Heinkel HE-122A0",488, 8000, 2000, 1100),
    MAVAG ("Mávag Héja",530, 10500, 3240, 1100),
    JUNKERS88 ("Junkers JU-88",510, 9000, 14000, 2430),
    JUNKERS52 ("Junkers JU-52",195, 3400, 7000, 1000);

    private final String name;
    private final int speed;
    private final int maxHeight;
    private final int maxTakeoffWeight;
    private final int range;

    Card (String name, int speed, int maxHeight, int maxTakeoffWeight, int range) {

        this.name = name;
        this.speed = speed;
        this.maxHeight = maxHeight;
        this.maxTakeoffWeight = maxTakeoffWeight;
        this.range = range;

    }
	public String getName(){return name;}

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
}
