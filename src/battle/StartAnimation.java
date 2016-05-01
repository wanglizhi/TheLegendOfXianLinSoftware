package battle;
import tools.*;
import java.awt.*;

public class StartAnimation {

BattlePanel bp;
int code;
//两侧图片
Image left;
Image right;
//左侧坐标
int leftX;
int leftY;
//右侧坐标
int rightX;
int rightY;

boolean isDraw;
boolean isStop;

//构造方法
public StartAnimation(BattlePanel bp){
	this.bp=bp;
	leftX=0;
	leftY=0;
	rightX=0;
	rightY=0;
	//读入图片 右侧图片与左侧相同
	left=Reader.readImage("image/其他/云雾.png");
	right=left;
	
	isDraw=true;
    isStop=false;
}

//画出
public void drawStartAnimation(Graphics g){
	if(isDraw){
		g.drawImage(left, leftX, leftY, bp);
		g.drawImage(right, rightX, rightY, bp);
	}
}

public void update(){
	if(!isStop){
		if(leftX<1024){
			leftX+=30;
			rightX-=30;
		}
		else{
			left=null;
			right=null;
			isDraw=false;
			isStop=true;
		}
	}
}
}
