package menu;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import tools.*;

public class MagicAnimation {
		FatherPanel fp;	
		//ͼƬ����
		Image image;
		int hero;
		String skillName="";
		int skillNumber;
		
		int length=0;//��������
		//�������ֵ�����
		private int AnimationX;
		private int AnimationY;
		int code=1;
		String[] magicDiscription;
		int x_discription;
		int y_discription;
	
		public MagicAnimation(int hero,int skillNumber,int length,int x,int y,String[] discription,int a,int b,FatherPanel f){
		fp=f;
		this.hero=hero;
		this.skillNumber=skillNumber;
		this.length=length;
			this.length=length;
			this.AnimationX=x;
			this.AnimationY=y;
			this.magicDiscription=discription;
			importImage();
			x_discription=a;
			y_discription=b;
		}

		private void importImage() {
			// TODO Auto-generated method stub
			skillName+="image/���ܶ���/";
			switch(this.hero){
			case 1:
				skillName+="��С������";
				break;
			case 2:
				skillName+="½ѩ������";
				break;
			case 4:
				skillName+="��������";
				break;
			default: break;
			
			}
			switch(this.skillNumber){
			case 1:skillName+="1";break;
			case 2:skillName+="2";break;
			case 3:skillName+="3";break;
			case 4:skillName+="4";break;
			case 5:skillName+="5";break;
			default: break;
			}
			image=Reader.readImage(skillName+"/"+code+".png");
		}

		public int getAnimationX() {
			return AnimationX;
		}

		public void setAnimationX(int animationX) {
			AnimationX = animationX;
		}

		public int getAnimationY() {
			return AnimationY;
		}

		public void setAnimationY(int animationY) {
			AnimationY = animationY;
		}
		
		public void drawMagicAnimation(Graphics g){
			g.setColor(Color.white);
			g.setFont(new Font("�Ķ��ֱָ��п�", Font.BOLD, 27));g.drawString(magicDiscription[0], x_discription, y_discription);
			g.drawString(magicDiscription[1], x_discription, y_discription+34);
			g.drawImage(image, AnimationX, AnimationY, fp);
		}
		
		public void update(){
			if(code<length){
				code++;
				image=Reader.readImage(skillName+"/"+code+".png");
			}
		}
		
}
