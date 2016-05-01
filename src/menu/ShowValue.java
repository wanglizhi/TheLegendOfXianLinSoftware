package menu;
import java.util.*;
import tools.*;
import java.awt.*;

import javax.swing.ImageIcon;

public class ShowValue {
	
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
	int value;
	//���� 1.���� 2.����
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

	//equip��������
	FatherPanel fp;

	//���캯��
	public ShowValue(FatherPanel bp){
		this.fp=bp;
		
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
		unit=value%10;
		ten=(int) ((value%100)/10);
		hundred=(int) ((value%1000)/100);
		thousand=(int) (value/1000);
	}

	//�������ֻ�õ�ǰͼƬ����
	public void getCurrentImages(){
		//�����ж������ֵ��ź�
		boolean firstNum;
		Image a=new ImageIcon("sources/�˵�/װ��/����.png").getImage();
		Image b=new ImageIcon("sources/�˵�/װ��/�½�.png").getImage();
		
		switch(type){
		//�˺�ֵ
		
		case 0:
			//ǧλ
			currentImages.add(a);
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
		
		case 1:
			//ǧλ
			currentImages.add(a);
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
			currentImages.add(b);
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
	//���ذ汾����ʾ�˺�ֵ
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

	//��ʼ
	public void start(){
		isDraw=true;
		isStop=false;
	}
	public void close(){
		isDraw=false;
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


