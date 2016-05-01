package battle;

import java.awt.*;
import java.util.*;
import tools.*;

//ս����ʾ��
public class Reminder {
//ͼƬ����
	Image currentImage;
//ͼƬ����
	ArrayList<Image> images=new ArrayList<Image>();
//�Ƿ���ʾ
	boolean isDraw;
//�Ƿ�ֹͣ
	boolean isStop;
	//����
	int centreX;
	int centreY;
	//���
	int code;
	//Ŀ����ε�һ���ǵ�����
	int dx1;
	int dy1;
	//Ŀ����εڶ����ǵ�����
	int dx2;
	int dy2;
	//Դ���ε�һ���ǵ�����
	int sx1;
	int sy1;
	//Դ���εڶ����ǵ�����
	int sx2;
	int sy2;
	//ս���������
	BattlePanel bp;
	
	//���췽��
	public Reminder(BattlePanel bp,int centreX,int centreY){
		this.bp=bp;
		
		isDraw=false;
		isStop=true;
		
		sx1=0;
		sy1=0;
		sx2=128;
		sy2=24;
		
		this.dx1=centreX;
		this.dx2=centreX;
		this.dy1=centreY;
		this.dy2=centreY;
		this.centreX=centreX;
		this.centreY=centreY;
		
		loadImage();
	}
	
	//����ͼƬ
	public void loadImage(){
		for(int i=1;i<=22;i++){
			Image image=Reader.readImage("image/��ʾͼ/"+i+".png");
			images.add(image);
		}
	}
	
	//����
	public void drawReminder(Graphics g){
		if(isDraw){
			g.drawImage(currentImage, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bp);
		}
	}
	//��ʾ��ʾ
	public void show(int i){
		currentImage=images.get(i);
		isDraw=true;
		isStop=false;
	}
	//����
	public void update(){
		if(!isStop){
			if(dx1>centreX-60){
			dx1-=5;
			dx2+=5;
			dy1-=1;
			dy2+=1;
			}else{			
			code++;
			if(code==10){
				isDraw=false;
				isStop=true;	
				code=0;
				dx1=centreX;
				dx2=centreX;
				dy1=centreY;
				dy2=centreY;
			}
			}
		}
	}
}
