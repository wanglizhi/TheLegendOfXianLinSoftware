package shop;

import java.util.ArrayList;

public class DrugPack{
	public static ArrayList<Drug> drugList=new ArrayList<Drug>();
	
	public DrugPack(){
		drugList=ShopReader.readDrug();
	}
	//��DrugPack�е�ҩƷ���в���
	public static void addDrug(String name,int number){
		for(Drug drug: drugList)
			if(drug.getName().equals(name)){
				number=drug.getNumberGOT()+number;
				drug.setNumberGOT(number);
			}
	}
}
