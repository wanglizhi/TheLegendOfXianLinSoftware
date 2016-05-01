package shop;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JPanel;

import tools.*;

public class ShopAnimation {
		//ͼƬ����
		Image image;
		ArrayList<Image> images=new ArrayList<Image>();
		
		//�������ֵ�����
		private int AnimationX;
		private int AnimationY;
	
		JPanel mp;
		
		public ShopAnimation(String s,int x,int y,int length,JPanel panel){
			this.setAnimationX(x);
			this.setAnimationY(y);
			for(int i=1;i<=length;i++){
	    		image=Reader.readImage("sources/Shop/�̵�����/"+s+"/"+s+" ("+i+").png");
	    		images.add(image);
	    		
			}
			this.mp=panel;
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
		
}
