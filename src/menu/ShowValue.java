package menu;
import java.util.*;
import tools.*;
import java.awt.*;

import javax.swing.ImageIcon;

public class ShowValue {
	
	//图片引用
	Image image;
	//图片集合
	ArrayList<Image> images=new ArrayList<Image>();
	//当前显示的图片集合
	ArrayList<Image> currentImages=new ArrayList<Image>();
	//出现位置
	int x;
	int y;
	//伤害值
	int value;
	//类型 1.增加 2.减少
	int type;
	//个位
	int unit;
	//十位
	int ten;
	//百位
	int hundred;
	//千位
	int thousand;
	//编号
	int code;

	//是否画出
	boolean isDraw;
	//是否停止
	boolean isStop;

	//equip面板的引用
	FatherPanel fp;

	//构造函数
	public ShowValue(FatherPanel bp){
		this.fp=bp;
		
		code=1;
		isDraw=false;
		isStop=true;
		
		loadImage();
	}
	//读入图片
	public void loadImage(){
		for(int i=0;i<=9;i++){
			image=Reader.readImage("image/伤害值数字/伤害/"+i+".png");
			images.add(image);
		}
		for(int i=0;i<=9;i++){
			image=Reader.readImage("image/伤害值数字/回复/"+i+".png");
			images.add(image);
		}
		
	}

	//计算各位数字
	public void calcalate(){
		unit=value%10;
		ten=(int) ((value%100)/10);
		hundred=(int) ((value%1000)/100);
		thousand=(int) (value/1000);
	}

	//根据数字获得当前图片集合
	public void getCurrentImages(){
		//用于判断首数字的信号
		boolean firstNum;
		Image a=new ImageIcon("sources/菜单/装备/上升.png").getImage();
		Image b=new ImageIcon("sources/菜单/装备/下降.png").getImage();
		
		switch(type){
		//伤害值
		
		case 0:
			//千位
			currentImages.add(a);
			switchNum(thousand,0);
			if(thousand==0){
				firstNum=false;
			}else{
				firstNum=true;
				currentImages.add(image);
			}
		    //百位
			switchNum(hundred, 0);
			if(firstNum==false&&hundred==0){
				firstNum=false;
			}else{
				firstNum=true;
				currentImages.add(image);
			}
			
			//十位
			switchNum(ten, 0);
			if(firstNum==false&&ten==0){
				firstNum=false;
			}else{
				firstNum=true;
				currentImages.add(image);
			}
			
			//个位
			switchNum(unit, 0);
			currentImages.add(image);
		    break;
		
		case 1:
			//千位
			currentImages.add(a);
			switchNum(thousand,0);
			if(thousand==0){
				firstNum=false;
			}else{
				firstNum=true;
				currentImages.add(image);
			}
		    //百位
			switchNum(hundred, 0);
			if(firstNum==false&&hundred==0){
				firstNum=false;
			}else{
				firstNum=true;
				currentImages.add(image);
			}
			
			//十位
			switchNum(ten, 0);
			if(firstNum==false&&ten==0){
				firstNum=false;
			}else{
				firstNum=true;
				currentImages.add(image);
			}
			
			//个位
			switchNum(unit, 0);
			currentImages.add(image);
		    break;
		
		//回复值
		case 2:
			currentImages.add(b);
			//千位
			switchNum(thousand,10);
			if(thousand==0){
				firstNum=false;
			}else{
				firstNum=true;
				currentImages.add(image);
			}
		    //百位
			switchNum(hundred,10);
			if(firstNum==false&&hundred==0){
				firstNum=false;
			}else{
				firstNum=true;
				currentImages.add(image);
			}
			
			//十位
			switchNum(ten, 10);
			if(firstNum==false&&ten==0){
				firstNum=false;
			}else{
				firstNum=true;
				currentImages.add(image);
			}
			
			//个位
			switchNum(unit, 10);
			currentImages.add(image);
		    break;
		
		}		
	}

	//画出
	public void drawShowValue(Graphics g){
		if(this.type>0){
			if(isDraw){
			g.drawImage(currentImages.get(0), x-20, y, fp);
			for(int i=1;i<currentImages.size();i++){
				g.drawImage(currentImages.get(i), x+18*i, y, fp);
			}
			}
		}
		 if(this.type==0) {
			if(isDraw){
				
				for(int i=1;i<currentImages.size();i++){
					g.drawImage(currentImages.get(i), x, y, fp);
				}
			}
		}
			  currentImages.clear();
		
		
	}
	public void show(boolean isShowDifference,int hurt,int x ,int y){
		this.type=0;
		this.value=hurt;
		calcalate();
		getCurrentImages();
		this.x=x;
		this.y=y;
	}
	//重载版本的显示伤害值
	public void show(int hurt,int x,int y){
		this.value=hurt;
		if(value<0){
			this.type=2;
			this.value=-value;
		}else {
			this.type=1;
			
		}
		calcalate();
		getCurrentImages();
		this.x=x;
		this.y=y;
		
	}

	//开始
	public void start(){
		isDraw=true;
		isStop=false;
	}
	public void close(){
		isDraw=false;
	}


	//对应数字和图片
	public void switchNum(int num,int offset){
		switch(num){
	    case 0:
		    image=images.get(0+offset);
		    break;
	    case 1:
	    	image=images.get(1+offset);
	    	break;
	    case 2:
	    	image=images.get(2+offset);
	    	break;
	    case 3:
	    	image=images.get(3+offset);
	    	break;
	    case 4:
	    	image=images.get(4+offset);
	    	break;
	    case 5:
	    	image=images.get(5+offset);
	    	break;
	    case 6:
	    	image=images.get(6+offset);
	    	break;
	    case 7:
	    	image=images.get(7+offset);
	    	break;
	    case 8:
	    	image=images.get(8+offset);
	    	break;
	    case 9:
	    	image=images.get(9+offset);
	    	break;
	     }
	   }
}


