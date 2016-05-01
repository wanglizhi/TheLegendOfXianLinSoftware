package tools;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class GameButton{
	int currentX;
	int currentY;
	protected int x;
	public boolean isIsclicked() {
		return isclicked;
	}

	public void setIsclicked(boolean isclicked) {
		this.isclicked = isclicked;
	}

	protected int y;
	protected int width;
	protected int height;
	protected Image normalImage;
	protected Image waitclickImage;
	protected Image pressedImage;
	public Image buttonImage;
	protected JPanel mp;
	public boolean isclicked=false;
	public boolean isMoveIn=false;
	
	
	public GameButton(int x,int y,int width,int height,Image normalImage,Image waitclickImage,Image pressedImage,JPanel mp){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.normalImage=normalImage;
		this.waitclickImage=waitclickImage;
		this.pressedImage=pressedImage;
		this.buttonImage=normalImage;
		this.mp=mp;
		
	}
	
	public void isMoveIn(int currentX, int currentY){
		if(currentX>x-15&&currentX<(x+width-15)&&currentY>(y-6)&&currentY<(y+height-6)){
			buttonImage=waitclickImage;
			isMoveIn=true;
		}
		else
			buttonImage=normalImage;
		    isMoveIn=false;
	}
	
	public void isPressedButton(int currentX,int currentY){
		if(currentX>x-15&&currentX<(x+width-15)&&currentY>(y-6)&&currentY<(y+height-6)){
			buttonImage=pressedImage;
		
			isclicked=true;
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
	}

}
