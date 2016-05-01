package shop;

import java.util.ArrayList;

public class DrugPack{
	public static ArrayList<Drug> drugList=new ArrayList<Drug>();
	
	public DrugPack(){
		drugList=ShopReader.readDrug();
	}
	//对DrugPack中的药品进行操作
	public static void addDrug(String name,int number){
		for(Drug drug: drugList)
			if(drug.getName().equals(name)){
				number=drug.getNumberGOT()+number;
				drug.setNumberGOT(number);
			}
	}
}
