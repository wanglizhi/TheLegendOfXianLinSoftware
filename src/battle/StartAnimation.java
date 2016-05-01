package battle;
import tools.*;
import java.awt.*;

public class StartAnimation {

BattlePanel bp;
int code;
//����ͼƬ
Image left;
Image right;
//�������
int leftX;
int leftY;
//�Ҳ�����
int rightX;
int rightY;

boolean isDraw;
boolean isStop;

//���췽��
public StartAnimation(BattlePanel bp){
	this.bp=bp;
	leftX=0;
	leftY=0;
	rightX=0;
	rightY=0;
	//����ͼƬ �Ҳ�ͼƬ�������ͬ
	left=Reader.readImage("image/����/����.png");
	right=left;
	
	isDraw=true;
    isStop=false;
}

//����
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
