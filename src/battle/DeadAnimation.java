package battle;
import tools.*;

import java.util.*;
import java.awt.*;

//�ҷ�ս����λ����֮�󲥷ŵĶ���
public class DeadAnimation {
	    //��ǰͼƬ
		Image currentImage;
		//ͼƬ����
		ArrayList<Image> images=new ArrayList<Image>();
		//����������
		int x;
		int y;
		//����
		int length;
		//���..��ʱ��
		int code;
		//�Ƿ񻭳�
		boolean isDraw;
		//�Ƿ�ֹͣ
		boolean isStop;
		//ս���������
		BattlePanel bp;
		//Ӣ������
		Hero hero;
		
		//���췽��
		public DeadAnimation(int x,int y,int length,String name,BattlePanel bp,Hero hero){
			this.x=x;
			this.y=y;
			loadImage(length, name);
			this.bp=bp;
			this.hero=hero;
			this.length=length;
			this.currentImage=images.get(0);
			
			isDraw=false;
			isStop=true;
		}
		
		//����ͼƬ
		public void loadImage(int length,String name){
			for(int i=1;i<=length;i++){
				Image image=Reader.readImage("image/��������/"+name+"/"+i+".png");
			    images.add(image);
			}
		}
		
		//����
		public void drawDeadAniamtion(Graphics g){
			if(isDraw){
				g.drawImage(currentImage, x, y, bp);
			}
		}
		
		//��ʼ
		public void start(){
			hero.setIsDraw(false);
			isDraw=true;
			isStop=false;
		}
		
		//����
		public void update(){
			if(!isStop){
				if(code<length){
					currentImage=images.get(code);
					code++;
				}
				if(code==length){
					code=0;
					currentImage=images.get(code);
				}
			}
		}
		
}
