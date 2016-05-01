package battle;
import java.awt.*;
import tools.*;

//����������
public class BackgroundAnimation {
//��ǰͼƬ
Image currentImage;

//����
int x;
int y;
//�Ƿ񻭳�
boolean isDraw;
//�Ƿ�ֹͣ
boolean isStop;
//�Ƿ����
boolean isOver;
//���
int code;
//����
int length;
//����
String name;
//ս���������
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

//������������
public void drawBackAnimation(Graphics g){
	if(isDraw){
	g.drawImage(currentImage, x, y, bp);
	}
}

//����
public void update(){
	if(!isStop){
	if(code<length){
		currentImage=Reader.readImage("image/��������/"+name+"/"+(code+1)+".jpg");
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
