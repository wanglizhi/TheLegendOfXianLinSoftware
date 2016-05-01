package battle;

import java.awt.*;
import java.util.ArrayList;

import tools.*;
import shop.*;

//ʹ��ҩƷ�Ĳ˵�
public class DrugMenu {
//����ͼ
Image backImage;

//��ť
GameButton drugButton;
ArrayList<GameButton> drugButtons=new ArrayList<GameButton>();
//��ťͼƬ
ArrayList<Image> buttonImages=new ArrayList<Image>();
//�˵����ֵ�λ��
int x;
int y;
//�Ƿ񻭳�
boolean isDraw;

//������ͼƬ
Image introduceImage;
//����ͼλ��
int introX;
int introY;
//����ͼ�Ƿ񻭳�
boolean isDrawIntro;
//��������
String introString;

//ս���������
BattlePanel bp;

DrugPack drugPack;
//��ǰӢ��
Hero currentHero;

public DrugMenu(BattlePanel bp){
	this.x=340;
	this.y=200;
	
	this.introX=220;
	
	backImage=Reader.readImage("image/ҩƷ�˵�/ҩƷ��ʾ��.png");
	this.bp=bp;
	isDraw=false;
	
	if(DrugPack.drugList.size()==0){
		drugPack=new DrugPack();
	}
	
	getImage();
	addButton();
}

//����ͼƬ
public void getImage(){
	for(int i=1;i<=6;i++){
		for(int j=1;j<=3;j++){
			Image image=Reader.readImage("image/ҩƷ�˵�/ҩƷ"+i+"��ť"+j+".png");
			buttonImages.add(image);
		}
	}
	for(int i=1;i<=3;i++){
		Image image=Reader.readImage("image/ҩƷ�˵�/����"+i+".png");
		buttonImages.add(image);
	}
}

//��Ӱ�ť
public void addButton(){
	for(int i=0;i<buttonImages.size();i=i+3){
		drugButton=new GameButton(395, 226+i*10, 215, 28, buttonImages.get(i), buttonImages.get(i+1), buttonImages.get(i+2), bp);
		drugButtons.add(drugButton);
	}
}

   //����Ƿ��ƶ�������˵�
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

	
	//�������Ƿ����˵�
		public void checkPressed(){
			for(GameButton button:drugButtons){
			button.isPressedButton(bp.currentX, bp.currentY);
		}
	}
	
    //����Ƿ��ɿ����
	public void checkReleased(){
		//���½�ҩ
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
		
		//���·��ذ�ť
		if(drugButtons.get(6).isclicked==true){
			isDraw=false;
			bp.command.isDraw=true;
		}
		
		for(GameButton button:drugButtons){
			button.isRelesedButton(bp.currentX, bp.currentY);
	    }
	}
	
	//���ҩƷ��Ŀ
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
			//��ʾ�˺�ֵ
			for(HurtValue hurt:bp.hurtValues){
				hurt.start();
			}
			drug.setNumberGOT(drug.getNumberGOT()-1);
			progressGo();
		}else{
			bp.reminder.show(19);
		}
	}
	//���ȼ���
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
	
	
	//ȷ����ǰӢ��
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
	
	//�����˵�
	public void drawDrugMenu(Graphics g){
		if(isDraw){
			g.drawImage(backImage, x, y, bp);
			for(GameButton button:drugButtons){
				button.drawButton(g);
			}
			//����ʣ���ҩƷ����
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
