package shop;

import java.awt.Image;

public class Drug {
	private String name;
	private int addHp;
	private int addMp;
	public int number;
	private int numberGOT;
	private Image picture;
	private int reduceMoney;
	private int purchaseNumber;
	
	public Image getPicture(){
		return picture;
	}
	public void setPicture(Image picture) {
		this.picture = picture;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAddHp() {
		return addHp;
	}
	public void setAddHp(int addHp) {
		this.addHp = addHp;
	}
	public int getAddMp() {
		return addMp;
	}
	public void setAddMp(int addMp) {
		this.addMp = addMp;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getReduceMoney() {
		return reduceMoney;
	}
	public void setReduceMoney(int reduceMoney) {
		this.reduceMoney = reduceMoney;
	}
	public int getPurchaseNumber() {
		return purchaseNumber;
	}
	public void setPurchaseNumber(int purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}
	public int getNumberGOT() {
		return numberGOT;
	}
	public void setNumberGOT(int numberGOT) {
		this.numberGOT = numberGOT;
	}
	
} 
