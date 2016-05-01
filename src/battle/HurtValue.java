package battle;

import java.util.*;
import tools.*;
import java.awt.*;

//用于显示伤害值的类
public class HurtValue {
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
int hurt;
//类型 1.伤害 2.回复
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

//战斗面板引用
BattlePanel bp;

//构造函数
public HurtValue(BattlePanel bp){
	this.bp=bp;
	
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
	unit=hurt%10;
	ten=(int) ((hurt%100)/10);
	hundred=(int) ((hurt%1000)/100);
	thousand=(int) (hurt/1000);
}

//根据数字获得当前图片集合
public void getCurrentImages(){
	//用于判断首数字的信号
	boolean firstNum;
	switch(type){
	//伤害值
	case 1:
		//千位
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
public void drawHurtValue(Graphics g){
	if(isDraw){
	for(int i=0;i<currentImages.size();i++){
		g.drawImage(currentImages.get(i), x+16*i, y, bp);
	}
  }
}

//重载版本的显示伤害值
public void show(int hurt,int type,int x,int y){
	this.hurt=hurt;
	this.type=type;
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

//更新
public void update(){
	if(!isStop){
		if(code<=5){
			this.y-=2;
			code++;
		}
		else if(code<=10){
			this.y+=1;
			code++;
		}else{
			isStop=true;
		    isDraw=false;
		    code=1;
		    //清空
		    currentImages.clear();
		}
	}
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
