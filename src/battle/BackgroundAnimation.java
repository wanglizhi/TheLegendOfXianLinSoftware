package battle;
import java.awt.*;
import tools.*;

//背景动画类
public class BackgroundAnimation {
//当前图片
Image currentImage;

//坐标
int x;
int y;
//是否画出
boolean isDraw;
//是否停止
boolean isStop;
//是否完毕
boolean isOver;
//编号
int code;
//长度
int length;
//名称
String name;
//战斗面板引用
BattlePanel bp;

public BackgroundAnimation(BattlePanel bp,String name,int length){
	this.bp=bp;
	isDraw=false;
	isStop=true;
	this.length=length;
	this.name=name;
	x=0;
	y=0;
}

public BackgroundAnimation(BattlePanel bp){
	this.bp=bp;
	isDraw=false;
	isStop=true;
	x=0;
	y=0;
}

public void set(String name,int length){
	this.length=length;
	this.name=name;
}

//画出背景动画
public void drawBackAnimation(Graphics g){
	if(isDraw){
	g.drawImage(currentImage, x, y, bp);
	}
}

//更新
public void update(){
	if(!isStop){
	if(code<length){
		currentImage=Reader.readImage("image/背景动画/"+name+"/"+(code+1)+".jpg");
		code++;
	}
	if(code==length){
		code=0;
		currentImage=null;
		isDraw=false;
		isStop=true;
		isOver=true;
	}
  }
}
}
