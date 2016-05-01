package start;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class StartButton{
	int currentX;
	int currentY;
	int x;
	int y;
	int width;
	int height;
	Image normalImage;
	Image waitclickImage;
	Image pressedImage;
	public Image buttonImage;
	JPanel mp;
	boolean isclicked=false;
	//属于此按钮的动画
	StartAnimation animation;
	
	public StartButton(int x,int y,int width,int height,Image normalImage,Image waitclickImage,Image pressedImage,JPanel mp,StartAnimation animation){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.normalImage=normalImage;
		this.waitclickImage=waitclickImage;
		this.pressedImage=pressedImage;
		this.buttonImage=normalImage;
		this.mp=mp;
		this.animation=animation;
		this.isclicked=false;
	}
	
	public void isMoveIn(int currentX, int currentY){
		if(currentX>x-15&&currentX<(x+width-15)&&currentY>(y-6)&&currentY<(y+height-6)){
			buttonImage=waitclickImage;
			animation.startAnimation();
		}
		else{
			buttonImage=normalImage; 
			animation.stopButtonAnimation();
		}
	}
	
	public void isPressedButton(int currentX,int currentY){
		if(currentX>x-15&&currentX<(x+width-15)&&currentY>(y-6)&&currentY<(y+height-6)){
			buttonImage=pressedImage;
			isclicked=true;
			animation.stopButtonAnimation();
		}
		else
			buttonImage=normalImage;
	}
	
	public void isRelesedButton(int currentX, int currentY){
		if(currentX>x-15&&currentX<(x+width-15)&&currentY>(y-6)&&currentY<(y+height-6)){
			buttonImage=waitclickImage;	
			isclicked=false;

		}
		else
			buttonImage=normalImage;
	}
	
	public void drawButton(Graphics g){
		g.drawImage(buttonImage,x,y,mp);
		animation.drawAnimation(g);
	}
	

	public boolean isIsclicked() {
		return isclicked;
	}

	public void setIsclicked(boolean isclicked) {
		this.isclicked = isclicked;
	}
}


