package battle;

import java.awt.*;
import java.util.*;
import tools.*;

//战斗提示类
public class Reminder {
//图片引用
	Image currentImage;
//图片集合
	ArrayList<Image> images=new ArrayList<Image>();
//是否显示
	boolean isDraw;
//是否停止
	boolean isStop;
	//中心
	int centreX;
	int centreY;
	//编号
	int code;
	//目标矩形第一个角的坐标
	int dx1;
	int dy1;
	//目标矩形第二个角的坐标
	int dx2;
	int dy2;
	//源矩形第一个角的坐标
	int sx1;
	int sy1;
	//源矩形第二个角的坐标
	int sx2;
	int sy2;
	//战斗面板引用
	BattlePanel bp;
	
	//构造方法
	public Reminder(BattlePanel bp,int centreX,int centreY){
		this.bp=bp;
		
		isDraw=false;
		isStop=true;
		
		sx1=0;
		sy1=0;
		sx2=128;
		sy2=24;
		
		this.dx1=centreX;
		this.dx2=centreX;
		this.dy1=centreY;
		this.dy2=centreY;
		this.centreX=centreX;
		this.centreY=centreY;
		
		loadImage();
	}
	
	//载入图片
	public void loadImage(){
		for(int i=1;i<=22;i++){
			Image image=Reader.readImage("image/提示图/"+i+".png");
			images.add(image);
		}
	}
	
	//画出
	public void drawReminder(Graphics g){
		if(isDraw){
			g.drawImage(currentImage, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bp);
		}
	}
	//显示提示
	public void show(int i){
		currentImage=images.get(i);
		isDraw=true;
		isStop=false;
	}
	//更新
	public void update(){
		if(!isStop){
			if(dx1>centreX-60){
			dx1-=5;
			dx2+=5;
			dy1-=1;
			dy2+=1;
			}else{			
			code++;
			if(code==10){
				isDraw=false;
				isStop=true;	
				code=0;
				dx1=centreX;
				dx2=centreX;
				dy1=centreY;
				dy2=centreY;
			}
			}
		}
	}
}
