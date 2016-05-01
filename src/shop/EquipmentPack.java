package shop;

import java.util.ArrayList;

public class EquipmentPack {
	//设置不同种装备的list
	public static ArrayList<Equipment> helmetList = new ArrayList<Equipment>();
	public static ArrayList<Equipment> armorList = new ArrayList<Equipment>();
	public static ArrayList<Equipment> weaponList = new ArrayList<Equipment>();
	public static ArrayList<Equipment> gloveList = new ArrayList<Equipment>();
	public static ArrayList<Equipment> shoeList = new ArrayList<Equipment>();
	public static ArrayList<Equipment> decorationList = new ArrayList<Equipment>();
	
	public EquipmentPack(){
		helmetList=ShopReader.readEquipment("头");
		armorList=ShopReader.readEquipment("盔甲");
		gloveList=ShopReader.readEquipment("手");
		shoeList=ShopReader.readEquipment("脚");
		decorationList=ShopReader.readEquipment("饰品");
		weaponList=ShopReader.readEquipment("武器");		
	}
	
	public static ArrayList<Equipment> listTable(String s){
		switch(s){
		case "armor":
		return armorList;
		case "helmet":
		return helmetList;
		case "glove":
		return gloveList;
		case "shoe":
			return shoeList;
		case "weapon":
			return weaponList;
		case "decoration":
			return decorationList;
		default:
			return null;
		}
	}
//对装备栏中的装备数量进行操作
public static void addEqupment(String name,int number){
	for(Equipment e1:helmetList)
		if(e1.getName().equals(name)){
			number=e1.getNumberGOT()+number;
			e1.setNumberGOT(number);
		}
	for(Equipment e2:armorList)
		if(e2.getName().equals(name)){
			number=e2.getNumberGOT()+number;
			e2.setNumberGOT(number);
		}
	for(Equipment e3:weaponList)	
		if(e3.getName().equals(name)){
			number=e3.getNumberGOT()+number;
			e3.setNumberGOT(number);
		}
	for(Equipment e4:gloveList)	
		if(e4.getName().equals(name)){
			number=e4.getNumberGOT()+number;
			e4.setNumberGOT(number);
		}
	for(Equipment e5:shoeList)	
		if(e5.getName().equals(name)){
			number=e5.getNumberGOT()+number;
			e5.setNumberGOT(number);
		}
	for(Equipment e6:decorationList)		
		if(e6.getName().equals(name)){
			number=e6.getNumberGOT()+number;
			e6.setNumberGOT(number);
		}
}
}

