package shop;

import java.awt.Image;

public class Equipment{
	private String name;
	private int addPhysicalPower;
	private int addAgile;
	private int addStrength;
	private int addSpirit;
	private int number=1;
	private Image picture;
	private int reduceMoney;
	private int purchaseNumber;
	private int numberGOT;
	//默认使用者为任何人
	private int user=0;
	public Image getPicture() {
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
	public int getAddPhysicalPower() {
		return addPhysicalPower;
	}
	public void setAddPhysicalPower(int addPhysicalPower) {
		this.addPhysicalPower = addPhysicalPower;
	}
	public int getAddAgile() {
		return addAgile;
	}
	public void setAddAgile(int addAgile) {
		this.addAgile = addAgile;
	}
	public int getAddStrength() {
		return addStrength;
	}
	public void setAddStrength(int addStrength) {
		this.addStrength = addStrength;
	}
	public int getAddSpirit() {
		return addSpirit;
	}
	public void setAddSpirit(int addSpirit) {
		this.addSpirit = addSpirit;
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
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
}
