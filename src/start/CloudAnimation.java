package start;

import java.awt.Graphics;
import java.awt.Image;


public class CloudAnimation {
	//�Ƴ��ֵ�����
	int x,y;
	//�Ƶ�ͼƬ
	Image cloudImage;
	//��һ���ƶ���ƫ����
	int move;
	//�Ƿ�ı��ʼ����
	boolean isChange;
	//���ƶ��ķ���
	int direction;
	//������panel
	StartPanel panel;
	
	public CloudAnimation(int x,int y,Image cloudImage,int direction,StartPanel panel){
		//��x��yΪ��ʼλ��
		this.x=x;
		this.y=y;
		this.cloudImage=cloudImage;
		move=10;
		this.direction=direction;
		this.panel=panel;
		this.isChange=false;
	}
	
	public void updateCoordinate(){
		//0Ϊ�������ƶ���1Ϊ�������ƶ���2Ϊ�������ƶ�
		switch(direction){
		case 0:
			if(!isChange){
				y-=move;
				if(y+1024<640)
					isChange=true;
			}
			else{
				y+=move;
				if(y>360)
					isChange=false;
			}
			break;
		}
	}
	
	public void drawAnimation(Graphics g){
		switch(direction){
		case 0:	
			g.drawImage(cloudImage,x,y,panel);
			break;
		case 1:
			g.drawImage(cloudImage,x,y,panel);	
			g.drawImage(cloudImage,-x,y,panel);	
			break;
		}
	}
}
