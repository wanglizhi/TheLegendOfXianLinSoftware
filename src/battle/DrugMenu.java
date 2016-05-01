package battle;

import java.awt.*;
import java.util.ArrayList;

import tools.*;
import shop.*;

//使用药品的菜单
public class DrugMenu {
//背景图
Image backImage;

//按钮
GameButton drugButton;
ArrayList<GameButton> drugButtons=new ArrayList<GameButton>();
//按钮图片
ArrayList<Image> buttonImages=new ArrayList<Image>();
//菜单出现的位置
int x;
int y;
//是否画出
boolean isDraw;

//介绍性图片
Image introduceImage;
//介绍图位置
int introX;
int introY;
//介绍图是否画出
boolean isDrawIntro;
//介绍文字
String introString;

//战斗面板引用
BattlePanel bp;

DrugPack drugPack;
//当前英雄
Hero currentHero;

public DrugMenu(BattlePanel bp){
	this.x=340;
	this.y=200;
	
	this.introX=220;
	
	backImage=Reader.readImage("image/药品菜单/药品显示框.png");
	this.bp=bp;
	isDraw=false;
	
	if(DrugPack.drugList.size()==0){
		drugPack=new DrugPack();
	}
	
	getImage();
	addButton();
}

//读入图片
public void getImage(){
	for(int i=1;i<=6;i++){
		for(int j=1;j<=3;j++){
			Image image=Reader.readImage("image/药品菜单/药品"+i+"按钮"+j+".png");
			buttonImages.add(image);
		}
	}
	for(int i=1;i<=3;i++){
		Image image=Reader.readImage("image/药品菜单/返回"+i+".png");
		buttonImages.add(image);
	}
}

//添加按钮
public void addButton(){
	for(int i=0;i<buttonImages.size();i=i+3){
		drugButton=new GameButton(395, 226+i*10, 215, 28, buttonImages.get(i), buttonImages.get(i+1), buttonImages.get(i+2), bp);
		drugButtons.add(drugButton);
	}
}

   //检查是否移动鼠标进入菜单
	public void checkMoveIn(){
		for(GameButton button:drugButtons){
				button.isMoveIn(bp.currentX, bp.currentY);
		}	
		
		for(int i=0;i<6;i++){
			if(bp.currentX>395&&bp.currentX<610&&bp.currentY>226+i*30&&bp.currentY<226+(i+1)*30){
				introduceImage=DrugPack.drugList.get(i).getPicture();
				introString="hp "+DrugPack.drugList.get(i).getAddHp()+" mp "+DrugPack.drugList.get(i).getAddMp();
				this.introY=226+i*30;
				isDrawIntro=true;
			   }
			}
		}

	
	//检查鼠标是否点击菜单
		public void checkPressed(){
			for(GameButton button:drugButtons){
			button.isPressedButton(bp.currentX, bp.currentY);
		}
	}
	
    //检查是否松开鼠标
	public void checkReleased(){
		//按下金创药
		if(drugButtons.get(0).isclicked==true){
			checkDrugNumber(DrugPack.drugList.get(0),1);
		}
		
		if(drugButtons.get(1).isclicked==true){
			checkDrugNumber(DrugPack.drugList.get(1), 2);
		}
		
		if(drugButtons.get(2).isclicked==true){
			checkDrugNumber(DrugPack.drugList.get(2), 1);
		}
		
		if(drugButtons.get(3).isclicked==true){
			checkDrugNumber(DrugPack.drugList.get(3), 2);
		}
		
		if(drugButtons.get(4).isclicked==true){
			checkDrugNumber(DrugPack.drugList.get(4), 1);
		}
		
		if(drugButtons.get(5).isclicked==true){
			checkDrugNumber(DrugPack.drugList.get(5), 2);
		}
		
		//按下返回按钮
		if(drugButtons.get(6).isclicked==true){
			isDraw=false;
			bp.command.isDraw=true;
		}
		
		for(GameButton button:drugButtons){
			button.isRelesedButton(bp.currentX, bp.currentY);
	    }
	}
	
	//检查药品数目
	public void checkDrugNumber(Drug drug,int type){
		if(drug.getNumberGOT()>0){
			bp.hurtValues.clear();
			HurtValue hurtValue=new HurtValue(bp);
			if(type==1){
				hurtValue.show(drug.getAddHp(), 2, currentHero.getShowX(), currentHero.getShowY());
				if(drug.getAddHp()+currentHero.getHp()>currentHero.getHpMax()){
					currentHero.setHp(currentHero.getHpMax());
				}else{
					currentHero.setHp(currentHero.getHp()+drug.getAddHp());
				}
			}else{
				hurtValue.show(drug.getAddMp(), 2, currentHero.getShowX(), currentHero.getShowY());
				if(drug.getAddMp()+currentHero.getMp()>currentHero.getMpMax()){
					currentHero.setMp(currentHero.getMpMax());
				}else{
					currentHero.setMp(currentHero.getMp()+drug.getAddMp());
				}
			}
			
			bp.hurtValues.add(hurtValue);
			//显示伤害值
			for(HurtValue hurt:bp.hurtValues){
				hurt.start();
			}
			drug.setNumberGOT(drug.getNumberGOT()-1);
			progressGo();
		}else{
			bp.reminder.show(19);
		}
	}
	//进度继续
	public void progressGo(){
		switch(bp.currentRound){
		case 1:
			bp.progressBar.ZhangX=bp.progressBar.BarX;
			break;
		case 2:
			bp.progressBar.YuX=bp.progressBar.BarX;
			break;
		case 3:
			bp.progressBar.LuX=bp.progressBar.BarX;
			break;
		}
		bp.currentRound=0;
		isDraw=false;
		bp.progressBar.isStop=false;	
	}
	
	
	//确定当前英雄
	public void checkHero(){
		switch(bp.currentRound){
		case 1:
			currentHero=bp.zxf;
			break;
		case 2:
			currentHero=bp.yj;
			break;
		case 3:
			currentHero=bp.lxq;
			break;
		}
	}
	
	//画出菜单
	public void drawDrugMenu(Graphics g){
		if(isDraw){
			g.drawImage(backImage, x, y, bp);
			for(GameButton button:drugButtons){
				button.drawButton(g);
			}
			//画出剩余的药品数量
			for(int i=0;i<=5;i++){
			g.drawString(DrugPack.drugList.get(i).getNumberGOT()+"", 575, 246+i*30);
			}
			
			if(isDrawIntro){
				g.drawImage(introduceImage, introX, introY, bp);
				g.drawString(introString, introX+10, introY+20);
			}
		}
	}
}
