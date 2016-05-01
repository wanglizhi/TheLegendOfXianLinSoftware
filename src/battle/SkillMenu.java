package battle;
import java.awt.*;

import tools.*;

import java.util.*;
//���ܲ˵���
public class SkillMenu {
//������ʾ��ı���
Image background;
//��ť
GameButton skillButton;
ArrayList<GameButton> zhangButtons=new ArrayList<GameButton>();
ArrayList<GameButton> wenButtons=new ArrayList<GameButton>();
ArrayList<GameButton> luButtons=new ArrayList<GameButton>();
ArrayList<GameButton> skillButtons;
//���ذ�ť
GameButton returnButton;
//ͼƬ����
ArrayList<Image> zhangImages=new ArrayList<Image>();
ArrayList<Image> wenImages=new ArrayList<Image>();
ArrayList<Image> luImages=new ArrayList<Image>();
//�˵����ֵ�λ��
int x;
int y;
//�Ƿ񱻻���
boolean isDraw;

//������ͼƬ
Image introduceImage;
//����ͼλ��
int introX;
int introY;
//����ͼ�Ƿ񻭳�
boolean isDrawIntro;
//�����ǽ���ͼ
ArrayList<Image> zhangIntros=new ArrayList<Image>();
ArrayList<Image> wenIntros=new ArrayList<Image>();
ArrayList<Image> luIntros=new ArrayList<Image>();

//ս���������
BattlePanel bp;

//���췽��
public SkillMenu(BattlePanel bp){
	this.x=340;
	this.y=200;
	
	this.introX=160;
	
	background=Reader.readImage("image/���ܲ˵�/������ʾ��.png");
	this.bp=bp;
	isDraw=false;
	
	getImage();
	addButton();
	skillButtons=zhangButtons;
}

//����ͼƬ
public void getImage(){
	//������С���ļ��ܰ�ťͼƬ
	if(bp.zxf!=null){
	for(int i=1;i<=ZhangXiaoFan.skillNumber;i++){
		for(int j=1;j<=3;j++){
			Image image=Reader.readImage("image/���ܲ˵�/���ܰ�ť/��С��/����"+i+"��ť"+j+".png");
			zhangImages.add(image);
		}
		Image intro=Reader.readImage("image/����˵��/��С��/"+i+".png");
		zhangIntros.add(intro);
	}
  }
	
//���������ļ��ܰ�ťͼƬ
	if(bp.yj!=null){
		for(int i=1;i<=YuJie.skillNumber;i++){
			for(int j=1;j<=3;j++){
				Image image=Reader.readImage("image/���ܲ˵�/���ܰ�ť/����/����"+i+"��ť"+j+".png");
				wenImages.add(image);
			}
			Image intro=Reader.readImage("image/����˵��/����/"+i+".png");
			wenIntros.add(intro);
		}
	}
	
//����½ѩ���ļ��ܰ�ťͼƬ
	if(bp.lxq!=null){
		for(int i=1;i<=LuXueQi.skillNumber;i++){
			for(int j=1;j<=3;j++){
				Image image=Reader.readImage("image/���ܲ˵�/���ܰ�ť/½ѩ��/����"+i+"��ť"+j+".png");
				luImages.add(image);
			}
			Image intro=Reader.readImage("image/����˵��/½ѩ��/"+i+".png");
			luIntros.add(intro);
		}
	}
}

//���°�ť�󷢶�����
public void afterClicked(int currentPattern,boolean isSlectable,int currentBeAttacked){
	//�Ѳ˵����ص�
	isDraw=false;
	bp.currentPattern=currentPattern;
   //�򿪹���ѡ��
	bp.enemySlector.isSlectable=isSlectable;
	bp.currentBeAttacked=currentBeAttacked;
	//ָʾ������
	bp.instruct.end();
}

//��Ӱ�ť�ķ���
public void addButton(){
	//������С���İ�ť
	if(bp.zxf!=null){
		for(int i=0;i<zhangImages.size();i=i+3){
			skillButton=new GameButton(395, 226+i*10, 215 ,28, zhangImages.get(i), zhangImages.get(i+1), zhangImages.get(i+2), bp);
			zhangButtons.add(skillButton);
		}
	}
	
	//���������İ�ť
	if(bp.yj!=null){
		for(int i=0;i<wenImages.size();i=i+3){
			skillButton=new GameButton(395, 226+i*10, 215 ,28, wenImages.get(i), wenImages.get(i+1), wenImages.get(i+2), bp);
			wenButtons.add(skillButton);
		}
	}
	
   //����½ѩ���İ�ť
	if(bp.lxq!=null){
		for(int i=0;i<luImages.size();i=i+3){
			skillButton=new GameButton(395, 226+i*10, 215, 28, luImages.get(i), luImages.get(i+1), luImages.get(i+2), bp);
			luButtons.add(skillButton);
		}
	}
}

//��鵱ǰΪ˭�Ļغ�
public void checkRound(){
	if(bp.currentRound==1){
		skillButtons=zhangButtons;
		returnButton=new GameButton(395, 226+zhangButtons.size()*30, 215, 28, Reader.readImage("image/���ܲ˵�/���ܰ�ť/����/����1.png"), Reader.readImage("image/���ܲ˵�/���ܰ�ť/����/����2.png"), Reader.readImage("image/���ܲ˵�/���ܰ�ť/����/����3.png"), bp);
	}
	else if(bp.currentRound==2){
		skillButtons=wenButtons;
		returnButton=new GameButton(395, 226+wenButtons.size()*30, 215, 28, Reader.readImage("image/���ܲ˵�/���ܰ�ť/����/����1.png"), Reader.readImage("image/���ܲ˵�/���ܰ�ť/����/����2.png"), Reader.readImage("image/���ܲ˵�/���ܰ�ť/����/����3.png"), bp);
	}else if(bp.currentRound==3){
		skillButtons=luButtons;
		returnButton=new GameButton(395, 226+luButtons.size()*30, 215, 28, Reader.readImage("image/���ܲ˵�/���ܰ�ť/����/����1.png"), Reader.readImage("image/���ܲ˵�/���ܰ�ť/����/����2.png"), Reader.readImage("image/���ܲ˵�/���ܰ�ť/����/����3.png"), bp);
	}
}

//����Ƿ��ƶ�������˵�
	public void checkMoveIn(){
		for(GameButton button:skillButtons){
				button.isMoveIn(bp.currentX, bp.currentY);	
		}
		returnButton.isMoveIn(bp.currentX,bp.currentY);
		
		//��С���Ļغ�
		if(bp.currentRound==1){
			for(int i=0;i<zhangButtons.size();i++){
				if(bp.currentX>395&&bp.currentX<610&&bp.currentY>226+i*30&&bp.currentY<226+(i+1)*30){
					introduceImage=zhangIntros.get(i);
					this.introY=226+i*30;
					isDrawIntro=true;
				 }
			}
		}
		
		if(bp.currentRound==2){
			for(int i=0;i<wenButtons.size();i++){
				if(bp.currentX>395&&bp.currentX<610&&bp.currentY>226+i*30&&bp.currentY<226+(i+1)*30){
					introduceImage=wenIntros.get(i);
					this.introY=226+i*30;
					isDrawIntro=true;
				   }
				}
		}
		
		if(bp.currentRound==3){
			for(int i=0;i<luButtons.size();i++){
				if(bp.currentX>395&&bp.currentX<610&&bp.currentY>226+i*30&&bp.currentY<226+(i+1)*30){
					introduceImage=luIntros.get(i);
					this.introY=226+i*30;
					isDrawIntro=true;
				   }
				}
		}
		
	}
	
	//�������Ƿ����˵�
	public void checkPressed(){
		for(GameButton button:skillButtons){
				button.isPressedButton(bp.currentX, bp.currentY);
		}
		returnButton.isPressedButton(bp.currentX, bp.currentY);
	}
	
	//����Ƿ��ɿ����
		public void checkReleased(){
			
		if(returnButton.isclicked==true){
			isDraw=false;
			bp.command.isDraw=true;
		}
			
		if(bp.currentRound==1){
			if(skillButtons.get(0).isclicked==true){
				afterClicked(2, true, 0);
			}
			if(skillButtons.get(1).isclicked==true){
				afterClicked(3, true, 0);
			}
			if(ZhangXiaoFan.skillNumber>=3){
			   if(skillButtons.get(2).isclicked==true){
				afterClicked(4, true, 0);
			}
			}
			if(ZhangXiaoFan.skillNumber>=4){
			if(skillButtons.get(3).isclicked==true){
				afterClicked(5, false, 8);
			}
			}
			if(ZhangXiaoFan.skillNumber>=5){
			if(skillButtons.get(4).isclicked==true){
               afterClicked(6,false,8);
			}
			}
			
		}
		
		if(bp.currentRound==2){
			if(skillButtons.get(0).isclicked==true){
				afterClicked(2, true, 0);
			}
			if(skillButtons.get(1).isclicked==true){
				afterClicked(3, true, 0);
			}
			if(YuJie.skillNumber>=3){
			if(skillButtons.get(2).isclicked==true){
				afterClicked(4, false, 8);
			}
			}
			if(YuJie.skillNumber>=4){
			if(skillButtons.get(3).isclicked==true){
				afterClicked(5, false, 4);
			}
			}
			if(YuJie.skillNumber>=5){
			if(skillButtons.get(4).isclicked==true){
               afterClicked(6,true,0);
			}
			}
		}
		
		if(bp.currentRound==3){
			if(skillButtons.get(0).isclicked==true){
				afterClicked(2, false, 8);	
			}
			if(skillButtons.get(1).isclicked==true){
				afterClicked(3, false, 8);
			}
			if(LuXueQi.skillNumber>=3){
			if(skillButtons.get(2).isclicked==true){
				afterClicked(4, false, 8);
			}
			}
			if(LuXueQi.skillNumber>=4){
			if(skillButtons.get(3).isclicked==true){
				afterClicked(5, true, 0);
			}
			}
			if(LuXueQi.skillNumber>=5){
			if(skillButtons.get(4).isclicked==true){
				afterClicked(6, false, 8);
			}
			}
		}
	    
			
		for(GameButton button:skillButtons){
				button.isRelesedButton(bp.currentX, bp.currentY);
		}
		returnButton.isRelesedButton(bp.currentX, bp.currentY);
	}

//�����˵�
public void drawSkillMenu(Graphics g){
	if(isDraw){
		g.drawImage(background, x, y, bp);
		for(GameButton button:skillButtons){
			button.drawButton(g);
		}
		returnButton.drawButton(g);
		
		if(isDrawIntro){
			g.drawImage(introduceImage, introX, introY, bp);
		}
	}
}

}
