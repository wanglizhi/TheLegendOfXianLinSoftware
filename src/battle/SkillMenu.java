package battle;
import java.awt.*;

import tools.*;

import java.util.*;
//技能菜单类
public class SkillMenu {
//技能显示框的背景
Image background;
//按钮
GameButton skillButton;
ArrayList<GameButton> zhangButtons=new ArrayList<GameButton>();
ArrayList<GameButton> wenButtons=new ArrayList<GameButton>();
ArrayList<GameButton> luButtons=new ArrayList<GameButton>();
ArrayList<GameButton> skillButtons;
//返回按钮
GameButton returnButton;
//图片引用
ArrayList<Image> zhangImages=new ArrayList<Image>();
ArrayList<Image> wenImages=new ArrayList<Image>();
ArrayList<Image> luImages=new ArrayList<Image>();
//菜单出现的位置
int x;
int y;
//是否被画出
boolean isDraw;

//介绍性图片
Image introduceImage;
//介绍图位置
int introX;
int introY;
//介绍图是否画出
boolean isDrawIntro;
//各主角介绍图
ArrayList<Image> zhangIntros=new ArrayList<Image>();
ArrayList<Image> wenIntros=new ArrayList<Image>();
ArrayList<Image> luIntros=new ArrayList<Image>();

//战斗面板引用
BattlePanel bp;

//构造方法
public SkillMenu(BattlePanel bp){
	this.x=340;
	this.y=200;
	
	this.introX=160;
	
	background=Reader.readImage("image/技能菜单/技能显示框.png");
	this.bp=bp;
	isDraw=false;
	
	getImage();
	addButton();
	skillButtons=zhangButtons;
}

//读入图片
public void getImage(){
	//读入张小凡的技能按钮图片
	if(bp.zxf!=null){
	for(int i=1;i<=ZhangXiaoFan.skillNumber;i++){
		for(int j=1;j<=3;j++){
			Image image=Reader.readImage("image/技能菜单/技能按钮/张小凡/技能"+i+"按钮"+j+".png");
			zhangImages.add(image);
		}
		Image intro=Reader.readImage("image/技能说明/张小凡/"+i+".png");
		zhangIntros.add(intro);
	}
  }
	
//读入文敏的技能按钮图片
	if(bp.yj!=null){
		for(int i=1;i<=YuJie.skillNumber;i++){
			for(int j=1;j<=3;j++){
				Image image=Reader.readImage("image/技能菜单/技能按钮/文敏/技能"+i+"按钮"+j+".png");
				wenImages.add(image);
			}
			Image intro=Reader.readImage("image/技能说明/文敏/"+i+".png");
			wenIntros.add(intro);
		}
	}
	
//读入陆雪琪的技能按钮图片
	if(bp.lxq!=null){
		for(int i=1;i<=LuXueQi.skillNumber;i++){
			for(int j=1;j<=3;j++){
				Image image=Reader.readImage("image/技能菜单/技能按钮/陆雪琪/技能"+i+"按钮"+j+".png");
				luImages.add(image);
			}
			Image intro=Reader.readImage("image/技能说明/陆雪琪/"+i+".png");
			luIntros.add(intro);
		}
	}
}

//按下按钮后发动攻击
public void afterClicked(int currentPattern,boolean isSlectable,int currentBeAttacked){
	//把菜单隐藏掉
	isDraw=false;
	bp.currentPattern=currentPattern;
   //打开怪物选择
	bp.enemySlector.isSlectable=isSlectable;
	bp.currentBeAttacked=currentBeAttacked;
	//指示器结束
	bp.instruct.end();
}

//添加按钮的方法
public void addButton(){
	//创建张小凡的按钮
	if(bp.zxf!=null){
		for(int i=0;i<zhangImages.size();i=i+3){
			skillButton=new GameButton(395, 226+i*10, 215 ,28, zhangImages.get(i), zhangImages.get(i+1), zhangImages.get(i+2), bp);
			zhangButtons.add(skillButton);
		}
	}
	
	//创建文敏的按钮
	if(bp.yj!=null){
		for(int i=0;i<wenImages.size();i=i+3){
			skillButton=new GameButton(395, 226+i*10, 215 ,28, wenImages.get(i), wenImages.get(i+1), wenImages.get(i+2), bp);
			wenButtons.add(skillButton);
		}
	}
	
   //创建陆雪琪的按钮
	if(bp.lxq!=null){
		for(int i=0;i<luImages.size();i=i+3){
			skillButton=new GameButton(395, 226+i*10, 215, 28, luImages.get(i), luImages.get(i+1), luImages.get(i+2), bp);
			luButtons.add(skillButton);
		}
	}
}

//检查当前为谁的回合
public void checkRound(){
	if(bp.currentRound==1){
		skillButtons=zhangButtons;
		returnButton=new GameButton(395, 226+zhangButtons.size()*30, 215, 28, Reader.readImage("image/技能菜单/技能按钮/返回/返回1.png"), Reader.readImage("image/技能菜单/技能按钮/返回/返回2.png"), Reader.readImage("image/技能菜单/技能按钮/返回/返回3.png"), bp);
	}
	else if(bp.currentRound==2){
		skillButtons=wenButtons;
		returnButton=new GameButton(395, 226+wenButtons.size()*30, 215, 28, Reader.readImage("image/技能菜单/技能按钮/返回/返回1.png"), Reader.readImage("image/技能菜单/技能按钮/返回/返回2.png"), Reader.readImage("image/技能菜单/技能按钮/返回/返回3.png"), bp);
	}else if(bp.currentRound==3){
		skillButtons=luButtons;
		returnButton=new GameButton(395, 226+luButtons.size()*30, 215, 28, Reader.readImage("image/技能菜单/技能按钮/返回/返回1.png"), Reader.readImage("image/技能菜单/技能按钮/返回/返回2.png"), Reader.readImage("image/技能菜单/技能按钮/返回/返回3.png"), bp);
	}
}

//检查是否移动鼠标进入菜单
	public void checkMoveIn(){
		for(GameButton button:skillButtons){
				button.isMoveIn(bp.currentX, bp.currentY);	
		}
		returnButton.isMoveIn(bp.currentX,bp.currentY);
		
		//张小凡的回合
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
	
	//检查鼠标是否点击菜单
	public void checkPressed(){
		for(GameButton button:skillButtons){
				button.isPressedButton(bp.currentX, bp.currentY);
		}
		returnButton.isPressedButton(bp.currentX, bp.currentY);
	}
	
	//检查是否松开鼠标
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

//画出菜单
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
