package menu;
import media.*;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import battle.*;

import shop.*;

import tools.*;

public class EquipPanel extends FatherPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 679068773868529283L;
	//所有的装备
	EquipmentPack allEquipment;
	//当前的list
	ArrayList<Equipment> currentList;
	int CURRENTLIST=1;
	//1 weapon 2 armor 3 helmet 4 shoe 5 glove 6 decoration
	final int WEAPON=1;
	final int ARMOR=2;
	final int HELMET=3;
	final int SHOE=4;
	final int GLOVE=5;
	final int DECORATION=6;
	//每个英雄的装备包
	ArrayList<EquipPack> heroEquipPack;
	ArrayList<Hero> hero;
	EquipPack equipPack_hero1;
	EquipPack equipPack_hero2;
	//	EquipPack equipPack_hero3;
	EquipPack equipPack_hero4;
	//为了方便 当前英雄的背包
	EquipPack currentPack;

	//装备包分类button
	MenuButton weaponButton;
	MenuButton shoeButton;
	MenuButton helmetButton;
	MenuButton armorButton;
	MenuButton gloveButton;
	MenuButton decorationButton;
	MenuButton[] buttonlist=new MenuButton[6];
	//一个使用按钮 三个弃用按钮（后两个为收拾准备）;
	MenuButton use_button;
	MenuButton abandon_button;

	MenuButton[] useButtonList=new MenuButton[2];
	//总装备包的当前list的某个被选中的装备 
	Equipment currentEquipment;

	//英雄的当前装备
	Equipment heroEquipment;

	String name_currentList;
	//一些画图的变量 
	int x_currentImage=790+32;
	int y_currentImage=180;
	int x_heroEquipment_image=300+32;
	String message1,message2;


	int x_start_point=548;
	int y_start_point=177;

	int x_message=x_start_point-3;
	int y_message=578;

	int signal;//迫不得已 一些标志符号····
	int isEquiped=0;
	int canBeEquiped=0;
	//画出当前英雄的各个值;
	String s1;
	String s2;
	String s3;
	String s4;
	int x_value=125;
	int y_value=410;
	ArrayList<Equipment> list=new ArrayList<Equipment>();
	Image Equiped;
	Image can_not_use;

	ShowValue[] showValue;
	int showPP;
	int showAngile;
	int showStrength;
	int showSpirit;
	public EquipPanel(MenuPanel a,ZhangXiaoFan h1,
			LuXueQi h2,YuJie h4) {
		super(a,h1,h2,h4);
		this.setName("equipPanel");
		scoll=new Scoll(this);
		addButton();

		allEquipment=new EquipmentPack();
		currentList=EquipmentPack.weaponList;
		equipPack_hero1=new EquipPack(hero1);
		equipPack_hero2=new EquipPack(hero2);
		equipPack_hero4=new EquipPack(hero4);

		//初始化 武器包
		addPack();
		heroEquipPack=new ArrayList<EquipPack>();
		heroEquipPack.add(equipPack_hero1);
		heroEquipPack.add(equipPack_hero2);
		heroEquipPack.add(equipPack_hero4);
hero=new ArrayList<Hero>();
hero.add(h1);
hero.add(h2);
hero.add(h4);
		currentPack=equipPack_hero1;
		heroEquipment=currentPack.weapon;
		if(heroEquipment!=null){
			abandon_button.isDraw=MenuButton.Yes;
		}
		Equiped=new ImageIcon("sources/菜单/装备/已装备.png").getImage();
		can_not_use=new ImageIcon("sources/菜单/装备/不能使用.png").getImage();
		s1="";
		s2="";
		s3="";
		s4="";
		hero1.refreshValue();
		hero2.refreshValue();
		hero4.refreshValue();

		list.clear();
		for(Equipment e:currentList){
			if(e.getNumberGOT()>0){
				list.add(e);
			}
		}
		if(list.size()>0){
			currentEquipment=currentList.get(0);
			signal=1;
			use_button.isDraw=MenuButton.Yes;
		}else{
			currentEquipment=null;
			signal=0;
		}

		showValue = new ShowValue[4];
		addShowValueBar();

	}

	private void addShowValueBar() {
		// TODO Auto-generated method stub
		showValue[0]=new ShowValue(this);	
		showValue[1]=new ShowValue(this);	
		showValue[2]=new ShowValue(this);	
		showValue[3]=new ShowValue(this);
	}



	private void addPack() {
		// TODO Auto-generated method stub
		equipPack_hero1.weapon=EquipmentPack.weaponList.get(6);
		equipPack_hero2.weapon=EquipmentPack.weaponList.get(0);
		equipPack_hero4.weapon=EquipmentPack.weaponList.get(7);

		ZhangXiaoFan.agile+=equipPack_hero1.weapon.getAddAgile();
		ZhangXiaoFan.strength+=equipPack_hero1.weapon.getAddStrength();
		ZhangXiaoFan.sprit+=equipPack_hero1.weapon.getAddSpirit();
		ZhangXiaoFan.physicalPower+=equipPack_hero1.weapon.getAddPhysicalPower();

		LuXueQi.agile+=equipPack_hero2.weapon.getAddAgile();
		LuXueQi.strength+=equipPack_hero2.weapon.getAddStrength();
		LuXueQi.sprit+=equipPack_hero2.weapon.getAddSpirit();
		LuXueQi.physicalPower+=equipPack_hero2.weapon.getAddPhysicalPower();

		YuJie.agile+=equipPack_hero4.weapon.getAddAgile();
		YuJie.strength+=equipPack_hero4.weapon.getAddStrength();
		YuJie.sprit+=equipPack_hero4.weapon.getAddSpirit();
		YuJie.physicalPower+=equipPack_hero4.weapon.getAddPhysicalPower();

		hero1.refreshValue();
		hero2.refreshValue();
		hero4.refreshValue();
	}



	private void addButton() {
		// TODO Auto-generated method stub
		//各项button的位置大小数据
		int x_equipButton=x_start_point-16;
		int y_equipButton=y_start_point-42;
		int width_button=43;
		int height_button=20;
		int x_useButton=x_currentImage+5+25;
		int y_useButton=y_currentImage+250;
		int x_abandonButton=x_heroEquipment_image+23;
		int y_abandonButton=y_useButton;

		int width=120;
		int height=40;
		Image image1;
		Image image2;
		Image image3;

		//euuipButton;
		image1=new ImageIcon("sources/菜单/装备/武器1.png").getImage();
		image2=new ImageIcon("sources/菜单/装备/武器2.png").getImage();
		weaponButton=new MenuButton(x_equipButton, y_equipButton, width_button, height_button, image1, image2, image1, this);

		image1=new ImageIcon("sources/菜单/装备/盔甲1.png").getImage();
		image2=new ImageIcon("sources/菜单/装备/盔甲2.png").getImage();
		//盔甲
		armorButton=new MenuButton(x_equipButton+1*width_button, y_equipButton, width_button, height_button, image1, image2, image1, this);

		image1=new ImageIcon("sources/菜单/装备/头盔1.png").getImage();
		image2=new ImageIcon("sources/菜单/装备/头盔2.png").getImage();
		helmetButton=new MenuButton(x_equipButton+2*width_button, y_equipButton, width_button, height_button, image1, image2, image1, this);

		image1=new ImageIcon("sources/菜单/装备/靴子1.png").getImage();
		image2=new ImageIcon("sources/菜单/装备/靴子2.png").getImage();
		shoeButton=new MenuButton(x_equipButton+3*width_button, y_equipButton, width_button, height_button, image1, image2, image1, this);

		image1=new ImageIcon("sources/菜单/装备/护臂1.png").getImage();
		image2=new ImageIcon("sources/菜单/装备/护臂2.png").getImage();
		gloveButton=new MenuButton(x_equipButton+4*width_button, y_equipButton, width_button, height_button, image1, image2, image1, this);

		image1=new ImageIcon("sources/菜单/装备/饰品1.png").getImage();
		image2=new ImageIcon("sources/菜单/装备/饰品2.png").getImage();
		decorationButton=new MenuButton(x_equipButton+5*width_button, y_equipButton, width_button, height_button, image1, image2, image1, this);
		buttonlist[0]=weaponButton;
		buttonlist[1]=armorButton;
		buttonlist[2]=helmetButton;
		buttonlist[3]=shoeButton;
		buttonlist[4]=gloveButton;
		buttonlist[5]=decorationButton;


		image1=new ImageIcon("sources/菜单/装备/使用1.png").getImage();
		image2=new ImageIcon("sources/菜单/装备/使用2.png").getImage();
		image3=new ImageIcon("sources/菜单/装备/使用3.png").getImage();
		use_button=new MenuButton(x_useButton, y_useButton, width, height, image1, image2, image3, this);

		use_button.isDraw=MenuButton.No;
		image1=new ImageIcon("sources/菜单/装备/弃用1.png").getImage();
		image2=new ImageIcon("sources/菜单/装备/弃用2.png").getImage();
		image3=new ImageIcon("sources/菜单/装备/弃用3.png").getImage();
		abandon_button=new MenuButton(x_abandonButton, y_abandonButton, width, height, image1, image2, image3, this);
		abandon_button.isDraw=MenuButton.No;
		//默认的不显示···isDraw=no；
		useButtonList[0]=use_button;
		useButtonList[1]=abandon_button;



	}



	@Override
	public void readBackgroundImage() {
		// TODO Auto-generated method stub
		backgroundImage = Reader.readImage("sources/菜单/装备/装备4.png");
	}

	@Override
	public void drawSpecialImage(Graphics g) {
		// TODO Auto-generated method stub


	}
	public void drawThisPanel(Graphics g) {
		// TODO Auto-generated method stub
		for(MenuButton button:buttonlist){
			button.drawButton(g);
		}
		for(MenuButton button:useButtonList){
			button.drawButton(g);
		}

		drawEquipment(g);
		drawWarning(g);
		drawHeroStuff(g);
		drawValueBar(g);


	}

	private void drawWarning(Graphics g) {
		// TODO Auto-generated method stub
		switch(isEquiped){
		case 0: break;
		case 1:
			g.drawImage(Equiped, 800, 330, this);
			MusicReader.readmusic("禁止.wav");
			break;
		default: break;

		}
		isEquiped=0;

		switch(canBeEquiped){
		case 0: break;
		case 1:
			g.drawImage(can_not_use, 800, 330, this);
			MusicReader.readmusic("禁止.wav");
			break;
		default: break;

		}
		canBeEquiped=0;
	}



	private void addHeroValue(){

		switch(scoll.whichHero){
		case 1:
			ZhangXiaoFan.agile+=currentEquipment.getAddAgile();
			ZhangXiaoFan.strength+=currentEquipment.getAddStrength();
			ZhangXiaoFan.sprit+=currentEquipment.getAddSpirit();
			ZhangXiaoFan.physicalPower+=currentEquipment.getAddPhysicalPower();
			break;
		case 2:
			LuXueQi.agile+=currentEquipment.getAddAgile();
			LuXueQi.strength+=currentEquipment.getAddStrength();
			LuXueQi.sprit+=currentEquipment.getAddSpirit();
			LuXueQi.physicalPower+=currentEquipment.getAddPhysicalPower();
			break;
		case 4:
			YuJie.agile+=currentEquipment.getAddAgile();
			YuJie.strength+=currentEquipment.getAddStrength();
			YuJie.sprit+=currentEquipment.getAddSpirit();
			YuJie.physicalPower+=currentEquipment.getAddPhysicalPower();
			break;
		default: break;
		}


		hero4.refreshValue();
		hero1.refreshValue();
		hero2.refreshValue();

	}
	private void minusHeroValue(){
		switch(scoll.whichHero){
		case 1:
			ZhangXiaoFan.agile-=heroEquipment.getAddAgile();
			ZhangXiaoFan.strength-=heroEquipment.getAddStrength();
			ZhangXiaoFan.sprit-=heroEquipment.getAddSpirit();
			ZhangXiaoFan.physicalPower-=heroEquipment.getAddPhysicalPower();
			break;
		case 2:
			LuXueQi.agile-=heroEquipment.getAddAgile();
			LuXueQi.strength-=heroEquipment.getAddStrength();
			LuXueQi.sprit-=heroEquipment.getAddSpirit();
			LuXueQi.physicalPower-=heroEquipment.getAddPhysicalPower();
			break;
		case 4:
			YuJie.agile-=heroEquipment.getAddAgile();
			YuJie.strength-=heroEquipment.getAddStrength();
			YuJie.sprit-=heroEquipment.getAddSpirit();
			YuJie.physicalPower-=heroEquipment.getAddPhysicalPower();
			break;
		default: break;
		}


		hero4.refreshValue();
		hero1.refreshValue();
		hero2.refreshValue();

	}
	private void drawValueBar(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
		g.setFont(new Font("文鼎粗钢笔行楷", Font.BOLD, 22));
		switch(scoll.whichHero){
		case 1:
			s1="体力："+ZhangXiaoFan.physicalPower;
			s2="敏捷："+ZhangXiaoFan.agile;
			s3="武力："+ZhangXiaoFan.strength;
			s4="精气："+ZhangXiaoFan.sprit;
			break;
		case 2:
			s1="体力："+LuXueQi.physicalPower;
			s2="敏捷："+LuXueQi.agile;
			s3="武力："+LuXueQi.strength;
			s4="精气："+LuXueQi.sprit;
			break;
		
		case 4:
			s1="体力："+YuJie.physicalPower;
			s2="敏捷："+YuJie.agile;
			s3="武力："+YuJie.strength;
			s4="精气："+YuJie.sprit;

			break;
		default: break;
		}

		g.drawString(s1, x_value, y_value);
		g.drawString(s2, x_value, y_value+26);
		g.drawString(s3, x_value, y_value+52);
		g.drawString(s4, x_value, y_value+78);



	}


	private void drawHeroStuff(Graphics g) {
		// TODO Auto-generated method stub


		if(heroEquipment!=null){
			g.drawImage(heroEquipment.getPicture(), 
					x_heroEquipment_image, y_currentImage, this);
			abandon_button.isDraw=MenuButton.Yes;
		}else {
			abandon_button.isDraw=MenuButton.No;
		}
		int vgap=20;
		if(currentPack.weapon!=null){
			s1="武器："+currentPack.weapon.getName();
		}else s1="武器: 无";
		if(currentPack.armor!=null){
			s2="盔甲："+currentPack.armor.getName();
		}else s2="盔甲: 无";
		if(currentPack.helmet!=null){
			s3="头盔:"+currentPack.helmet.getName();
		}else s3="头盔: 无 ";
		if(currentPack.shoe!=null){
			s4="战靴:"+currentPack.shoe.getName();
		}else s4="战靴: 无 ";
		g.setColor(Color.red);
		g.setFont(new Font("文鼎粗钢笔行楷", Font.BOLD, 21));
		g.drawString(s1, x_value, y_value-7*vgap);
		g.drawString(s2, x_value, y_value-6*vgap);
		g.drawString(s3, x_value, y_value-5*vgap);
		g.drawString(s4, x_value, y_value-4*vgap);
		if(currentPack.glove!=null){
			s1="护臂:"+currentPack.glove.getName();
		}else s1="护臂: 无";
		if(currentPack.decoration!=null){
			s2="饰品:"+currentPack.decoration.getName();
		}else s2="饰品: 无";
		g.drawString(s1, x_value, y_value-3*vgap);
		g.drawString(s2, x_value, y_value-2*vgap);


	}



	private void drawEquipment(Graphics g) {
		// TODO Auto-generated method stub

		// 为画图而给出的坐标
		int x = x_start_point, y = y_start_point;

		g.setFont(new Font("文鼎粗钢笔行楷", Font.BOLD, 20));
		g.setColor(Color.white);

		for(Equipment e:currentList){
			if(e.getNumberGOT()>0){
				list.add(e);
			}
		}
		for (Equipment e :currentList) {
			if(e.getNumberGOT()==0){
				continue;
			}
			g.drawString(e.getName(), x, y);			
			g.drawString("   "+e.getNumberGOT(), x + 150, y);
			y += 22;
		}

		g.setFont(new Font("文鼎粗钢笔行楷",Font.BOLD,19));
		if(signal==1){

			showValueDifference();
			for(ShowValue s:showValue){
				s.drawShowValue(g);
			}
			g.drawImage(currentEquipment.getPicture(), x_currentImage, y_currentImage, this);
			//物品说明
			message1=currentEquipment.getName();				
			message2=" : 体力+"+currentEquipment.getAddPhysicalPower()+
					" 敏捷+"+currentEquipment.getAddAgile()+" 武力+"+
					currentEquipment.getAddStrength()+
					" 精气+"+currentEquipment.getAddSpirit();

		}else {
			message1="无装备";			
			message2="快去装备店购买吧~！";
		}
		g.drawString(message1, x_message, y_message);
		g.drawString(message2, x_message+75, y_message);
		if(heroEquipment!=null){
			message1=heroEquipment.getName();				
			message2=" : 体力+"+heroEquipment.getAddPhysicalPower()+
					" 敏捷+"+heroEquipment.getAddAgile()+" 武力+"+
					heroEquipment.getAddStrength()+
					" 精气+"+heroEquipment.getAddSpirit();
			g.drawString(message1, 57, y_message);
			g.drawString(message2, 61+75, y_message);
		}
	}
	//}
	private void showValueDifference() {
		// TODO Auto-generated method stub
		int gap=80;
		int gap2=25;
		int y=388;
		if(heroEquipment!=null){
			showPP=currentEquipment.getAddPhysicalPower()-heroEquipment.getAddPhysicalPower();
			showAngile=currentEquipment.getAddAgile()-heroEquipment.getAddAgile();
			showStrength=currentEquipment.getAddStrength()-heroEquipment.getAddStrength();
			showSpirit=currentEquipment.getAddSpirit()-heroEquipment.getAddSpirit();
			showValue[0].show(showPP, x_value+gap, y);
			showValue[1].show(showAngile, x_value+gap, y+1*gap2);
			showValue[2].show(showStrength, x_value+gap, y+2*gap2);
			showValue[3].show(showSpirit, x_value+gap, y+3*gap2);


		}else{
			showValue[0].show(currentEquipment.getAddPhysicalPower(), x_value+gap, y);
			showValue[1].show(currentEquipment.getAddAgile(), x_value+gap, y+1*gap2);
			showValue[2].show(currentEquipment.getAddStrength(), x_value+gap, y+2*gap2);
			showValue[3].show(currentEquipment.getAddSpirit(), x_value+gap, y+3*gap2);

		}
		for(ShowValue s:showValue){
			s.start();
		}
	}

	private void isMoveIn() {
		int originalY=y_start_point-22;
		list.clear();
		for(Equipment e:currentList){
			if(e.getNumberGOT()>0){
				list.add(e);
			}
		}
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {

				if (currentX > x_start_point &&
						currentX < x_start_point+70 
						&& currentY > originalY
						&& currentY < (originalY + 22)) {

					currentEquipment=list.get(i);
					signal=1;
					use_button.isDraw=MenuButton.Yes;
				}

				originalY += 22;
			}
		}

	}



	@Override
	public void checkAllButtonReleased() {
		// TODO Auto-generated method stub
		scoll.checkReleased();
		for(MenuButton button:buttonlist){
			button.isRelesedButton(currentX, currentY);
		}
		for(MenuButton button:useButtonList){
			button.isRelesedButton(currentX, currentY);
		}
	}
	@Override
	public void checkAllButtonMoveIn() {
		// TODO Auto-generated method stub

		scoll.checkMoveIn();
		isMoveIn();//判断当前装备;
		for(MenuButton button:buttonlist){
			button.isMoveIn(currentX, currentY);
		}
		for(MenuButton button:useButtonList){
			button.isMoveIn(currentX, currentY);
		}
	}
	@Override
	public void checkAllButtonPressed() {
		// TODO Auto-generated method stub

		scoll.checkPressed();
		if(scoll.hero1.isclicked){

			heroEquipment=equipPack_hero1.weapon;
if(heroEquipment!=null){
	abandon_button.isDraw=1;
}else abandon_button.isDraw=0;
			currentPack=equipPack_hero1;
			currentList=EquipmentPack.weaponList;
			CURRENTLIST=WEAPON;
			list.clear();
			for(Equipment e:currentList){
				if(e.getNumberGOT()>0){
					list.add(e);
				}
			}
			if(list.size()>0){
				currentEquipment=list.get(0);
				signal=1;
				use_button.isDraw=1;
			}else {
				signal=0;
				currentEquipment=null;
				use_button.isDraw=0;
			}


		}
		if(scoll.hero2.isclicked){
			heroEquipment=equipPack_hero2.weapon;
			if(heroEquipment!=null){
				abandon_button.isDraw=1;
			}else abandon_button.isDraw=0;
			CURRENTLIST=WEAPON;
			currentPack=equipPack_hero2;
			currentList=EquipmentPack.weaponList;
			list.clear();
			for(Equipment e:currentList){
				if(e.getNumberGOT()>0){
					list.add(e);
				}
			}
			if(list.size()>0){
				use_button.isDraw=1;
				currentEquipment=list.get(0);
				signal=1;
			}else {
				signal=0;
				currentEquipment=null;use_button.isDraw=0;
			}


		}

		if(scoll.hero4.isclicked){
			heroEquipment=equipPack_hero4.weapon;if(heroEquipment!=null){
				abandon_button.isDraw=1;
			}else abandon_button.isDraw=0;
			CURRENTLIST=WEAPON;
			currentPack=equipPack_hero4;
			currentList=EquipmentPack.weaponList;
			list.clear();
			for(Equipment e:currentList){
				if(e.getNumberGOT()>0){
					list.add(e);
				}
			}
			if(list.size()>0){
				use_button.isDraw=1;
				currentEquipment=list.get(0);
				signal=1;
			}else {
				signal=0;use_button.isDraw=0;
				currentEquipment=null;
			}

		}
		repaint();

		for(MenuButton button:buttonlist){
			button.isPressedButton(currentX, currentY);
		}
		for(MenuButton button:useButtonList){
			button.isPressedButton(currentX, currentY);
		}

		//判断当前的人物  背包
		judgeCurrentPack();

		if(use_button.isIsclicked()){
			if(currentEquipment.getUser()==0||
					currentEquipment.getUser()==scoll.whichHero){
				doUseButton();
			}else {
				canBeEquiped=1;
			}
		}

		if(abandon_button.isIsclicked()){
			String name=heroEquipment.getName();
			MusicReader.readmusic("弃用.wav");
			for(Equipment e:currentList){
				if(name.equals(e.getName())){
					e.setNumberGOT(e.getNumberGOT()+1);	
					break;
				}
			}	minusHeroValue();
			//说明没有该物品  添加之；
			heroEquipment=null;


			switch(CURRENTLIST){
			case WEAPON:
				currentPack.weapon=null;
				break;
			case ARMOR:
				currentPack.armor=null;
				break;
			case HELMET:
				currentPack.helmet=null;			
				break;
			case SHOE:
				currentPack.shoe=null;
				break;
			case GLOVE:
				currentPack.glove=null;
				break;
			case DECORATION:
				currentPack.decoration=null;
				break;
			default: break;
			}

			list.clear();
			for(Equipment e:currentList){
				if(e.getNumberGOT()>0){
					list.add(e);
				}
			}
			if(list.size()>0){
				currentEquipment=list.get(0);
				signal=1;
				use_button.isDraw=1;

			}else {
				currentEquipment=null;
				signal=0;
				use_button.isDraw=0;
			}
			//	repaint();

		}
	}





	private void doUseButton() {
		// TODO Auto-generated method stub
		if(heroEquipment==null){
			isEquiped=0;
			currentEquipment.setNumberGOT(currentEquipment.getNumberGOT()-1);
			if(currentEquipment.getNumberGOT()==0){
				signal=0;
				use_button.isDraw=MenuButton.No;
			}else signal=1;
			switch(CURRENTLIST){
			case WEAPON:
				currentPack.weapon=currentEquipment;
				heroEquipment=currentPack.weapon;
				MusicReader.readmusic("武器.wav");
				break;
			case ARMOR:
				currentPack.armor=currentEquipment;
				heroEquipment=currentPack.armor;
				MusicReader.readmusic("盔甲.wav");
				break;
			case HELMET:
				currentPack.helmet=currentEquipment;
				heroEquipment=currentPack.helmet;
				MusicReader.readmusic("头盔.wav");
				break;
			case SHOE:
				currentPack.shoe=currentEquipment;
				heroEquipment=currentPack.shoe;
				MusicReader.readmusic("靴子.wav");
				break;
			case GLOVE:
				currentPack.glove=currentEquipment;
				heroEquipment=currentPack.glove;
				MusicReader.readmusic("手套.wav");
				break;
			case DECORATION:
				currentPack.decoration=currentEquipment;
				heroEquipment=currentPack.decoration;
				MusicReader.readmusic("首饰.wav");
				break;
			default: break;
			}

			addHeroValue();

		}else{
			isEquiped=1;
		}
		list.clear();
		for(Equipment e:currentList){
			if(e.getNumberGOT()>0){
				list.add(e);
			}
		}

	}

	private void judgeCurrentPack() {
		// TODO Auto-generated method stub


		if(weaponButton.isIsclicked()){
			currentList=EquipmentPack.weaponList;
			heroEquipment=currentPack.weapon;
			CURRENTLIST=WEAPON;
			MusicReader.readmusic("换list.wav");
			list.clear();
			for(Equipment e:currentList){
				if(e.getNumberGOT()>0){
					list.add(e);
				}
			}
			if(list.size()>0){

				currentEquipment=list.get(0);
				use_button.isDraw=1;
				signal=1;
			}else {
				signal=0;use_button.isDraw=0;
				currentEquipment=null;
			}
		}else if(armorButton.isIsclicked()){
			currentList=EquipmentPack.armorList;
			heroEquipment=currentPack.armor;
			CURRENTLIST=ARMOR;
			MusicReader.readmusic("换list.wav");

			list.clear();
			for(Equipment e:currentList){
				if(e.getNumberGOT()>0){
					list.add(e);
				}
			}

			if(list.size()>0){

				currentEquipment=list.get(0);
				use_button.isDraw=1;
				signal=1;
			}else {
				signal=0;use_button.isDraw=0;
				currentEquipment=null;
			}
		}else if(helmetButton.isIsclicked()){
			currentList=EquipmentPack.helmetList;
			heroEquipment=currentPack.helmet;
			CURRENTLIST=HELMET;
			MusicReader.readmusic("换list.wav");

			list.clear();
			for(Equipment e:currentList){
				if(e.getNumberGOT()>0){
					list.add(e);
				}
			}
			if(list.size()>0){

				currentEquipment=list.get(0);
				use_button.isDraw=1;
				signal=1;
			}else {
				signal=0;use_button.isDraw=0;
				currentEquipment=null;
			}
		}else if(shoeButton.isIsclicked()){
			currentList=EquipmentPack.shoeList;
			heroEquipment=currentPack.shoe;
			CURRENTLIST=SHOE;
			MusicReader.readmusic("换list.wav");

			list.clear();
			for(Equipment e:currentList){
				if(e.getNumberGOT()>0){
					list.add(e);
				}
			}
			if(list.size()>0){
				use_button.isDraw=1;
				currentEquipment=list.get(0);
				signal=1;
			}else {
				signal=0;use_button.isDraw=0;
				currentEquipment=null;
			}

		}else if(gloveButton.isIsclicked()){
			currentList=EquipmentPack.gloveList;
			heroEquipment=currentPack.glove;
			CURRENTLIST=GLOVE;
			MusicReader.readmusic("换list.wav");

			list.clear();
			for(Equipment e:currentList){
				if(e.getNumberGOT()>0){
					list.add(e);
				}
			}
			if(list.size()>0){
				use_button.isDraw=1;
				currentEquipment=list.get(0);
				signal=1;
			}else {
				signal=0;use_button.isDraw=0;
				currentEquipment=null;
			}
		}else if(decorationButton.isIsclicked()){
			currentList=EquipmentPack.decorationList;
			heroEquipment=currentPack.decoration;
			CURRENTLIST=DECORATION;
			MusicReader.readmusic("换list.wav");

			list.clear();
			for(Equipment e:currentList){
				if(e.getNumberGOT()>0){
					list.add(e);
				}
			}
			if(list.size()>0){
				use_button.isDraw=1;
				currentEquipment=list.get(0);
				signal=1;
			}else {
				use_button.isDraw=0;
				signal=0;
				currentEquipment=null;
			}
		}
	}



	@Override
	public void update() {

	}
	public ArrayList<String> saveEquipInfo(){
		ArrayList<String> equipInfo=new ArrayList<String>();
		EquipPack ep;

		for(int i=0;i<3;i++){
			ep=heroEquipPack.get(i);

			//hero
			if(ep.weapon!=null){
				equipInfo.add(ep.weapon.getName());
			}else {
				equipInfo.add("null");
			}
			if(ep.armor!=null){
				equipInfo.add(ep.armor.getName());
			}else {
				equipInfo.add("null");
			}
			if(ep.helmet!=null){
				equipInfo.add(ep.helmet.getName());
			}else {
				equipInfo.add("null");
			}

			if(ep.shoe!=null){
				equipInfo.add(ep.shoe.getName());
			}else {
				equipInfo.add("null");
			}
			if(ep.glove!=null){
				equipInfo.add(ep.glove.getName());
			}else {
				equipInfo.add("null");
			}
			if(ep.decoration!=null){
				equipInfo.add(ep.decoration.getName());
			}else {
				equipInfo.add("null");
			}

		}
		return equipInfo;

	}

	public void initialEquipInfo(ArrayList<String> equipInfo){
		EquipPack ep;
		Hero he;
		for(int i=0;i<3;i++){
			ep=heroEquipPack.get(i);
			he=hero.get(i);
			if(equipInfo.get(0+i*6).equals("null")){
				ep.weapon=null;
			}else {
				ep.weapon=null;
				for(Equipment equip:EquipmentPack.weaponList){
					if(equipInfo.get(0+i*6).equals(equip.getName())){
						ep.weapon=equip;
						he.setAgile(he.getAgile()+equip.getAddAgile());
						he.setSprit(he.getSprit()+equip.getAddSpirit());
						he.setStrength(he.getStrength()+equip.getAddStrength());
						he.setPhysicalPower(he.getPhysicalPower()+equip.getAddPhysicalPower());
						break;
					}
				}

			}
			if(equipInfo.get(1+i*6).equals("null")){
				ep.armor=null;
			}else {
				ep.armor=null;
				for(Equipment equip:EquipmentPack.armorList){
					if(equipInfo.get(1+i*6).equals(equip.getName())){
						ep.armor=equip;
						he.setAgile(he.getAgile()+equip.getAddAgile());
						he.setSprit(he.getSprit()+equip.getAddSpirit());
						he.setStrength(he.getStrength()+equip.getAddStrength());
						he.setPhysicalPower(he.getPhysicalPower()+equip.getAddPhysicalPower());
						break;
					}
				}
			}
			if(equipInfo.get(2+i*6).equals("null")){
				ep.helmet=null;
			}else {
				ep.helmet=null;
				for(Equipment equip:EquipmentPack.helmetList){
					if(equipInfo.get(2+i*6).equals(equip.getName())){
						ep.helmet=equip;
						he.setAgile(he.getAgile()+equip.getAddAgile());
						he.setSprit(he.getSprit()+equip.getAddSpirit());
						he.setStrength(he.getStrength()+equip.getAddStrength());
						he.setPhysicalPower(he.getPhysicalPower()+equip.getAddPhysicalPower());
						break;
					}
				}
			}
			if(equipInfo.get(3).equals("null")){
				ep.shoe=null;
			}else {
				ep.shoe=null;
				for(Equipment equip:EquipmentPack.shoeList){
					if(equipInfo.get(3+i*6).equals(equip.getName())){
						ep.shoe=equip;
						he.setAgile(he.getAgile()+equip.getAddAgile());
						he.setSprit(he.getSprit()+equip.getAddSpirit());
						he.setStrength(he.getStrength()+equip.getAddStrength());
						he.setPhysicalPower(he.getPhysicalPower()+equip.getAddPhysicalPower());
						break;
					}
				}
			}
			if(equipInfo.get(4+i*6).equals("null")){
				ep.glove=null;
			}else {
				ep.glove=null;
				for(Equipment equip:EquipmentPack.gloveList){
					if(equipInfo.get(4+i*6).equals(equip.getName())){
						ep.glove=equip;
						he.setAgile(he.getAgile()+equip.getAddAgile());
						he.setSprit(he.getSprit()+equip.getAddSpirit());
						he.setStrength(he.getStrength()+equip.getAddStrength());
						he.setPhysicalPower(he.getPhysicalPower()+equip.getAddPhysicalPower());
						break;
					}
				}
			}
			if(equipInfo.get(5).equals("null")){
				ep.decoration=null;
			}else {
				ep.decoration=null;
				for(Equipment equip:EquipmentPack.decorationList){
					if(equipInfo.get(5+i*6).equals(equip.getName())){
						ep.decoration=equip;
						he.setAgile(he.getAgile()+equip.getAddAgile());
						he.setSprit(he.getSprit()+equip.getAddSpirit());
						he.setStrength(he.getStrength()+equip.getAddStrength());
						he.setPhysicalPower(he.getPhysicalPower()+equip.getAddPhysicalPower());
						break;
					}
				}
			}

		}	
		hero1.refreshValue();
		hero2.refreshValue();
		hero4.refreshValue();

	}
}
