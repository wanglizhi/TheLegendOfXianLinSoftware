package shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import tools.Reader;

public class ShopReader {

	static BufferedReader br;

	// 读取收shop的方法
	public static ArrayList<Drug> readDrug() {
		ArrayList<Drug> druglist = new ArrayList<Drug>();
		File file = new File("sources/Shop/drug.txt");
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				Drug drug = new Drug();
				// 将/看做分隔符
				String[] lineArray = new String[10];
				lineArray = line.split("/");
				drug.setName(lineArray[0]);
				drug.setAddHp(Integer.parseInt(lineArray[1]));
				drug.setAddMp(Integer.parseInt(lineArray[2]));
				ImageIcon drugPicture = new ImageIcon("sources/Shop/药品/回复类/"
						+ lineArray[3]);
				drug.setPicture(drugPicture.getImage());
				drug.setReduceMoney(Integer.parseInt(lineArray[4]));
				druglist.add(drug);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return druglist;
	}

	public static ArrayList<Equipment> readEquipment(String s) {
		ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();
		File file = new File("sources/Shop/" + s + ".txt");
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				Equipment equipment = new Equipment();
				// 将/看做分隔符
				String[] lineArray = new String[10];
				lineArray = line.split("/");
				equipment.setName(lineArray[0]);
				equipment.setAddPhysicalPower(Integer.parseInt(lineArray[1]));
				equipment.setAddAgile(Integer.parseInt(lineArray[2]));
				equipment.setAddStrength(Integer.parseInt(lineArray[3]));
				equipment.setAddSpirit(Integer.parseInt(lineArray[4]));
				equipment.setPicture(Reader.readImage("sources/Shop/装备/" + s
						+ "/" + lineArray[5]));
				equipment.setReduceMoney(Integer.parseInt(lineArray[6]));
				if(lineArray.length==9){
					equipment.setUser(Integer.parseInt(lineArray[8]));
				}
				equipmentList.add(equipment);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return equipmentList;
	}

}
