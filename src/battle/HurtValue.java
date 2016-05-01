package battle;

import java.util.*;
import tools.*;
import java.awt.*;

//������ʾ�˺�ֵ����
public class HurtValue {
//ͼƬ����
Image image;
//ͼƬ����
ArrayList<Image> images=new ArrayList<Image>();
//��ǰ��ʾ��ͼƬ����
ArrayList<Image> currentImages=new ArrayList<Image>();
//����λ��
int x;
int y;
//�˺�ֵ
int hurt;
//���� 1.�˺� 2.�ظ�
int type;
//��λ
int unit;
//ʮλ
int ten;
//��λ
int hundred;
//ǧλ
int thousand;
//���
int code;

//�Ƿ񻭳�
boolean isDraw;
//�Ƿ�ֹͣ
boolean isStop;

//ս���������
BattlePanel bp;

//���캯��
public HurtValue(BattlePanel bp){
	this.bp=bp;
	
	code=1;
	isDraw=false;
	isStop=true;
	
	loadImage();
}
//����ͼƬ
public void loadImage(){
	for(int i=0;i<=9;i++){
		image=Reader.readImage("image/�˺�ֵ����/�˺�/"+i+".png");
		images.add(image);
	}
	for(int i=0;i<=9;i++){
		image=Reader.readImage("image/�˺�ֵ����/�ظ�/"+i+".png");
		images.add(image);
	}
}

//�����λ����
public void calcalate(){
	unit=hurt%10;
	ten=(int) ((hurt%100)/10);
	hundred=(int) ((hurt%1000)/100);
	thousand=(int) (hurt/1000);
}

//�������ֻ�õ�ǰͼƬ����
public void getCurrentImages(){
	//�����ж������ֵ��ź�
	boolean firstNum;
	switch(type){
	//�˺�ֵ
	case 1:
		//ǧλ
		switchNum(thousand,0);
		if(thousand==0){
			firstNum=false;
		}else{
			firstNum=true;
			currentImages.add(image);
		}
	    //��λ
		switchNum(hundred, 0);
		if(firstNum==false&&hundred==0){
			firstNum=false;
		}else{
			firstNum=true;
			currentImages.add(image);
		}
		
		//ʮλ
		switchNum(ten, 0);
		if(firstNum==false&&ten==0){
			firstNum=false;
		}else{
			firstNum=true;
			currentImages.add(image);
		}
		
		//��λ
		switchNum(unit, 0);
		currentImages.add(image);
	    break;
	
	//�ظ�ֵ
	case 2:
		//ǧλ
		switchNum(thousand,10);
		if(thousand==0){
			firstNum=false;
		}else{
			firstNum=true;
			currentImages.add(image);
		}
	    //��λ
		switchNum(hundred,10);
		if(firstNum==false&&hundred==0){
			firstNum=false;
		}else{
			firstNum=true;
			currentImages.add(image);
		}
		
		//ʮλ
		switchNum(ten, 10);
		if(firstNum==false&&ten==0){
			firstNum=false;
		}else{
			firstNum=true;
			currentImages.add(image);
		}
		
		//��λ
		switchNum(unit, 10);
		currentImages.add(image);
	    break;
	
	}		
}

//����
public void drawHurtValue(Graphics g){
	if(isDraw){
	for(int i=0;i<currentImages.size();i++){
		g.drawImage(currentImages.get(i), x+16*i, y, bp);
	}
  }
}

//���ذ汾����ʾ�˺�ֵ
public void show(int hurt,int type,int x,int y){
	this.hurt=hurt;
	this.type=type;
	calcalate();
	getCurrentImages();
	this.x=x;
	this.y=y;
	
}

//��ʼ
public void start(){
	isDraw=true;
	isStop=false;
}

//����
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
		    //���
		    currentImages.clear();
		}
	}
}

//��Ӧ���ֺ�ͼƬ
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
