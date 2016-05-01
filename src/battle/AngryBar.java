package battle;
import java.awt.*;
import java.util.*;
import tools.*;

//ŭ������
public class AngryBar {
    //������
	Image back;
	//��ǰͼƬ
	Image currentImage;
	//ͼƬ����
	ArrayList<Image> images=new ArrayList<Image>();
	//��ʱ�ñ��
	int code;
	//�Ƿ񻭳�
	boolean isDraw;
	//�Ƿ�ֹͣ
	boolean isStop;
	//��ͼ������
	int backX;
	int backY;
	//�ﲿͼ������
	int dx1;
	int dy1;
	int dx2;
	int dy2;
	int sx1;
	int sy1;
	int sx2;
	int sy2;
	
	//ս���������
	BattlePanel bp;
	//��ǰӢ��
	Hero hero;
	
	//���췽��
	public AngryBar(BattlePanel bp,Hero hero){
		this.bp=bp;
		this.hero=hero;
		
		switch(hero.getRoleCode()){
		   //��С��
		case 1:
			backX=170;
			backY=510-96/2+20;
			dx1=backX+8;
			dy1=backY+8+80;
			dx2=dx1+80;
			dy2=dy1;
			break;
			//����
		case 2:
			backX=170+322;
			backY=510-96/2+20;
			dx1=backX+8;
			dy1=backY+8+80;
			dx2=dx1+80;
			dy2=dy1;
			break;
			//½ѩ��
		case 3:
			backX=170+322*2;
			backY=510-96/2+20;
			dx1=backX+8;
			dy1=backY+8+80;
			dx2=dx1+80;
			dy2=dy1;
			break;
		}
		
		sx1=0;
		sy1=80;
		sx2=80;
		sy2=80;
		
		loadImage();
		currentImage=images.get(0);
		
		isDraw=true;
		isStop=false;
		
	}
	
	//����ͼƬ
	public void loadImage(){
		back=Reader.readImage("image/ŭ����/��.png");
		for(int i=1;i<=4;i++){
			Image image=Reader.readImage("image/ŭ����/"+i+".png");
			images.add(image);
		}
	}
	
	//����
	public void drawAngryBar(Graphics g){
		if(isDraw){
			g.drawImage(back, backX, backY, bp);
			g.drawImage(currentImage, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bp);
		}
	}
	
	//����
	public void update(){
		if(!isStop){
			int height=(int)(((double)hero.getAngryValue()/hero.getHpMax())*100);
			dy1=backY+8+80-height;
			sy1=80-height;
			if(hero.wheatherAngry()){
				if(code<4){
					currentImage=images.get(code);
					code++;
				}
				if(code==4){
					code=0;
					currentImage=images.get(code);
				}
			}
		}
	}
	
}
