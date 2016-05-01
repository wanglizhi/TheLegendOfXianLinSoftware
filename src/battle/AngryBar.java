package battle;
import java.awt.*;
import java.util.*;
import tools.*;

//怒气槽类
public class AngryBar {
    //背景层
	Image back;
	//当前图片
	Image currentImage;
	//图片集合
	ArrayList<Image> images=new ArrayList<Image>();
	//计时用编号
	int code;
	//是否画出
	boolean isDraw;
	//是否停止
	boolean isStop;
	//底图的坐标
	int backX;
	int backY;
	//里部图的坐标
	int dx1;
	int dy1;
	int dx2;
	int dy2;
	int sx1;
	int sy1;
	int sx2;
	int sy2;
	
	//战斗面板引用
	BattlePanel bp;
	//当前英雄
	Hero hero;
	
	//构造方法
	public AngryBar(BattlePanel bp,Hero hero){
		this.bp=bp;
		this.hero=hero;
		
		switch(hero.getRoleCode()){
		   //张小凡
		case 1:
			backX=170;
			backY=510-96/2+20;
			dx1=backX+8;
			dy1=backY+8+80;
			dx2=dx1+80;
			dy2=dy1;
			break;
			//文敏
		case 2:
			backX=170+322;
			backY=510-96/2+20;
			dx1=backX+8;
			dy1=backY+8+80;
			dx2=dx1+80;
			dy2=dy1;
			break;
			//陆雪琪
		case 3:
			backX=170+322*2;
			backY=510-96/2+20;
			dx1=backX+8;
			dy1=backY+8+80;
			dx2=dx1+80;
			dy2=dy1;
			break;
		}
		
		sx1=0;
		sy1=80;
		sx2=80;
		sy2=80;
		
		loadImage();
		currentImage=images.get(0);
		
		isDraw=true;
		isStop=false;
		
	}
	
	//载入图片
	public void loadImage(){
		back=Reader.readImage("image/怒气槽/底.png");
		for(int i=1;i<=4;i++){
			Image image=Reader.readImage("image/怒气槽/"+i+".png");
			images.add(image);
		}
	}
	
	//画出
	public void drawAngryBar(Graphics g){
		if(isDraw){
			g.drawImage(back, backX, backY, bp);
			g.drawImage(currentImage, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bp);
		}
	}
	
	//更新
	public void update(){
		if(!isStop){
			int height=(int)(((double)hero.getAngryValue()/hero.getHpMax())*100);
			dy1=backY+8+80-height;
			sy1=80-height;
			if(hero.wheatherAngry()){
				if(code<4){
					currentImage=images.get(code);
					code++;
				}
				if(code==4){
					code=0;
					currentImage=images.get(code);
				}
			}
		}
	}
	
}
