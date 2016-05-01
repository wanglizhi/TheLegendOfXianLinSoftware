package start;

import java.awt.Graphics;
import java.awt.Image;


public class CloudAnimation {
	//云出现的坐标
	int x,y;
	//云的图片
	Image cloudImage;
	//云一次移动的偏移量
	int move;
	//是否改变初始方向
	boolean isChange;
	//云移动的方向
	int direction;
	//所化的panel
	StartPanel panel;
	
	public CloudAnimation(int x,int y,Image cloudImage,int direction,StartPanel panel){
		//此x，y为初始位置
		this.x=x;
		this.y=y;
		this.cloudImage=cloudImage;
		move=10;
		this.direction=direction;
		this.panel=panel;
		this.isChange=false;
	}
	
	public void updateCoordinate(){
		//0为云相上移动，1为云向右移动，2为云向左移动
		switch(direction){
		case 0:
			if(!isChange){
				y-=move;
				if(y+1024<640)
					isChange=true;
			}
			else{
				y+=move;
				if(y>360)
					isChange=false;
			}
			break;
		}
	}
	
	public void drawAnimation(Graphics g){
		switch(direction){
		case 0:	
			g.drawImage(cloudImage,x,y,panel);
			break;
		case 1:
			g.drawImage(cloudImage,x,y,panel);	
			g.drawImage(cloudImage,-x,y,panel);	
			break;
		}
	}
}
