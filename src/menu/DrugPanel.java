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

public class DrugPanel extends FatherPanel{

	private static final long serialVersionUID = -1886842159260678221L;
	DrugPack drugPack;
	Drug currentDrug;
	Hero hero;
	MenuButton use_button;
	
	
	int width_button=120;
	int height_button=40;
	Image druaImage;
	int x_picture=820;
	int y_picture=178;
	
	int x_start_point=448;
	int y_start_point=190;

	int	x_button=x_picture;
	int y_button=y_picture+240;
	String message1="";
	String message2="";
	String message3="";
	int x_message=510;
	int y_message=586;
	//draw value bar
	
	String s1;
	String s2;
	String s3;
	String s4;
	int x_value=107;
	int y_value=320;
	int signal=0;//一些标志 此为 物品包是否为空;
	ArrayList<Drug> list=new ArrayList<Drug>();
	int hp,mp;
	public DrugPanel(MenuPanel a,ZhangXiaoFan h1,LuXueQi h2,YuJie h4) {
		super(a,h1,h2,h4);
		
		this.setName("thingPanel");
		scoll=new Scoll(this);
		drugPack=new DrugPack();

		addButton();
		upDateDrugList();
		hero1.refreshValue();
		hero2.refreshValue();
		hero4.refreshValue();
		hp=hero1.getHp();
		mp=hero1.getMp();
		
		
	}

	private void addButton() {
		// TODO Auto-generated method stub
		Image image1=new ImageIcon("sources/菜单/物品/使用1.png").getImage();
		Image image2=new ImageIcon("sources/菜单/物品/使用2.png").getImage();
		Image image3=new ImageIcon("sources/菜单/物品/使用3.png").getImage();
		use_button=new MenuButton(x_button, y_button, width_button,
				height_button, image1, image2, image3, this);
		use_button.isDraw=MenuButton.No;
		
		
	}
	public void readBackgroundImage() {
		// TODO Auto-generated method stub
		backgroundImage = Reader.readImage("sources/菜单/物品/物品3.png");
	}
	
	public void drawThisPanel(Graphics g) {
		use_button.drawButton(g);
		drawEquipment(g);		
		drawValueBar(g);
	}
	

	private void drawEquipment(Graphics g) {
		// TODO Auto-generated method stub
		
		// 为画图而给出的坐标
				int x = x_start_point, y = y_start_point;
				g.setFont(new Font("文鼎粗钢笔行楷", Font.BOLD, 26));
				g.setColor(Color.white);
				
				
					for(Drug e:DrugPack.drugList){	
						if(e.getNumberGOT()>0){
							g.drawString(e.getName(), x, y);			
							g.drawString(""+e.getNumberGOT(), x + 180, y);
							y += 32;
						}	
					}
				
					if(currentDrug!=null){
				
					g.drawImage(currentDrug.getPicture(), x_picture, y_picture, this);
					g.setFont(new Font("文鼎粗钢笔行楷", Font.BOLD, 24));
					
						//物品说明	
						message1=currentDrug.getName();				
						message2=": 生命 +"+currentDrug.getAddHp();
						message3="魔法 +"+currentDrug.getAddMp();
					}else {
						message1="没药了...";
						message2="快去药店买点吧~";
						message3="";
					}
				
				g.drawString(message1, x_message, y_message);
				g.drawString(message2, x_message+105, y_message);
				g.drawString(message3, x_message+265, y_message);
				
			}
				
				private void isMoveIn() {
					int originalY=y_start_point-32;
					//边框大小;
					
					upDateDrugList();
					if(list.size()!=0){
						
							for (int i = 0; i < list.size(); i++) {
								if (currentX > x_start_point 
										&&currentX < x_start_point+130 
										&& currentY > originalY
										&& currentY < (originalY + 32)) {
									currentDrug=list.get(i);
									y_button=originalY+5;
									use_button.isDraw=MenuButton.Yes;
								}
							originalY += 32;
							}
						
					}else {
						use_button.isDraw=MenuButton.No;
					}
				}

	private void drawValueBar(Graphics g) {
		// TODO Auto-generated method stub
		hero1.refreshValue();
		hero2.refreshValue();
		hero4.refreshValue();
		g.setColor(Color.blue);
		g.setFont(new Font("文鼎粗钢笔行楷", Font.BOLD, 20));
		switch(scoll.whichHero){
		case 1:
			hero=hero1;
			break;
		case 2:
			hero=hero2;
			break;
		case 3:
			hero=hero3;
			break;
		case 4:
			hero=hero4;
			break;
		default: break;
		}
		s1="生命值:"+hero.getHp()+"/"+hero.getHpMax();
		s2="魔法值:"+hero.getMp()+"/"+hero.getMpMax();
		
		g.drawString(s1, x_value, y_value);
		g.drawString(s2, x_value, y_value+45);
		
		
		
	}
	@Override
	public void drawSpecialImage(Graphics g) {
	
	}
	
	@Override
	public void checkAllButtonReleased() {
		// TODO Auto-generated method stub
		
		scoll.checkReleased();
		use_button.isRelesedButton(currentX, currentY);
	}
	@Override
	public void checkAllButtonMoveIn() {
		// TODO Auto-generated method stub
		
		scoll.checkMoveIn();
		use_button.isMoveIn(currentX, currentY);
		isMoveIn();
	}
	@Override
	public void checkAllButtonPressed() {
		// TODO Auto-generated method stub
		
		scoll.checkPressed();
		use_button.isPressedButton(currentX, currentY);
		
		if(use_button.isclicked){
			if(currentDrug!=null){
			currentDrug.setNumberGOT((currentDrug.getNumberGOT()-1));
			playMusic();
			addValue();
			if(currentDrug.getNumberGOT()==0){
				currentDrug=null;
				use_button.isDraw=MenuButton.No;
			}
			}
			upDateDrugList();
		
		}
		
	}
	private void playMusic() {
		// TODO Auto-generated method stub
		if(currentDrug.getAddHp()>currentDrug.getAddMp()&&currentDrug.getAddHp()<2100){
			MusicReader.readmusic("命+.wav");
		}
		if(currentDrug.getAddHp()>currentDrug.getAddMp()&&currentDrug.getAddHp()>=2100){
			MusicReader.readmusic("命++.wav");
		}
		if(currentDrug.getAddMp()>currentDrug.getAddHp()&&currentDrug.getAddMp()<2000){
			MusicReader.readmusic("魔+.wav");
		}
		if(currentDrug.getAddMp()>currentDrug.getAddHp()&&currentDrug.getAddMp()>=2000){
			MusicReader.readmusic("魔++.wav");
		}
	}

	 public  void upDateDrugList() {
		// TODO Auto-generated method stub
		list.clear();
		for(Drug e:DrugPack.drugList){
			if(e.getNumberGOT()>0){
				list.add(e);
			}
		}
		
	}
	 
	 public void showDrug(){
		 if(list.size()>0){
				currentDrug=list.get(0);
				use_button.isDraw=1;
			}else {
				currentDrug=null;
				use_button.isDraw=0;
			}
	 }
	private void addValue() {
		// TODO Auto-generated method stub
		hero1.refreshValue();
		hero2.refreshValue();
		hero4.refreshValue();
		int h;
		int m;
		switch(scoll.whichHero){
		case 1:
			hero=hero1;		
			break;
		case 2:
			hero=hero2;
			break;
		case 4:
			hero=hero4;
			break;
		default:
			break;
		}
	
		hp=hero.getHp();
		mp=hero.getMp();
		h=hero.getHp()+currentDrug.getAddHp();
		m=hero.getMp()+currentDrug.getAddMp();
		if(h>=hero.getHpMax()){
			hero.setHp(hero.getHpMax());
		}else{
			hero.setHp(h);
		}
		if(m>=hero.getMpMax()){
			hero.setMp(hero.getMpMax());
		}else {
			hero.setMp(m);
		}
		
		repaint();
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> saveEquipInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialEquipInfo(ArrayList<String> menuInfo) {
		// TODO Auto-generated method stub
		
	}

}
