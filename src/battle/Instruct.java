package battle;
import java.awt.*;
import java.util.*;
import tools.*;

//ս�����ָʾ��
public class Instruct {
//��ǰͼƬ
Image currentImage;
ArrayList<Image> images=new ArrayList<Image>();
//���
int code;
//����
int length;
//����λ��
int x;
int y;
//ս�����
BattlePanel bp;
//�Ƿ񻭳�
boolean isDraw;
//�Ƿ�ֹͣ
boolean isStop;

public void getImage(){
	for(int i=1;i<=length;i++){
	Image image=Reader.readImage("image/ָʾͼ/"+i+".png");
	images.add(image);
	}
	
	currentImage=images.get(0);
}

//���췽��
public Instruct(BattlePanel bp){
	this.bp=bp;
	this.length=5;
	isDraw=false;
	isStop=true;
	
	getImage();
}

//��ʼ����
public void start(){
	int i=bp.currentRound;
	switch(i){
	//��С��
	case 1:
		x=bp.zxf.x+210;
		y=bp.zxf.y+75;
		break;
	//����
	case 2:
		x=bp.yj.x+85;
		y=bp.yj.y-20;
		break;
	//½ѩ��
	case 3:
		x=bp.lxq.x+45;
		y=bp.lxq.y-20;
		break;
	}
	isDraw=true;
	isStop=false;
}
//����
public void end(){
	isDraw=false;
	isStop=true;
}

//����
public void drawInstruct(Graphics g){
	
	if(isDraw){
	g.drawImage(currentImage, x, y, bp);
	}
}

//����
public void update(){
	if(!isStop&&code<5){
		currentImage=images.get(code);
	    code++;
}else if(code==5){
code=0;
}
}
}
