package battle;
import java.awt.*;
import java.util.*;
import tools.*;

//战斗标记指示类
public class Instruct {
//当前图片
Image currentImage;
ArrayList<Image> images=new ArrayList<Image>();
//编号
int code;
//长度
int length;
//出现位置
int x;
int y;
//战斗面板
BattlePanel bp;
//是否画出
boolean isDraw;
//是否停止
boolean isStop;

public void getImage(){
	for(int i=1;i<=length;i++){
	Image image=Reader.readImage("image/指示图/"+i+".png");
	images.add(image);
	}
	
	currentImage=images.get(0);
}

//构造方法
public Instruct(BattlePanel bp){
	this.bp=bp;
	this.length=5;
	isDraw=false;
	isStop=true;
	
	getImage();
}

//开始出现
public void start(){
	int i=bp.currentRound;
	switch(i){
	//张小凡
	case 1:
		x=bp.zxf.x+210;
		y=bp.zxf.y+75;
		break;
	//文敏
	case 2:
		x=bp.yj.x+85;
		y=bp.yj.y-20;
		break;
	//陆雪琪
	case 3:
		x=bp.lxq.x+45;
		y=bp.lxq.y-20;
		break;
	}
	isDraw=true;
	isStop=false;
}
//结束
public void end(){
	isDraw=false;
	isStop=true;
}

//画出
public void drawInstruct(Graphics g){
	
	if(isDraw){
	g.drawImage(currentImage, x, y, bp);
	}
}

//更新
public void update(){
	if(!isStop&&code<5){
		currentImage=images.get(code);
	    code++;
}else if(code==5){
code=0;
}
}
}
