package menu;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import tools.*;
/*
 * 
 * 继承自GameBu 主要实现是否显示的问题；
 */
public class MenuButton	extends GameButton {
	int isDraw=0;
	static int Yes=1;
	static int No=0;
	
	public MenuButton(int x, int y, int width, int height, Image normalImage,
			Image waitclickImage, Image pressedImage, JPanel mp) {
		super(x, y, width, height, normalImage, waitclickImage, pressedImage, mp);
		// TODO Auto-generated constructor stub
		this.isDraw=MenuButton.Yes;
	}
	public void drawButton(Graphics g){
		if(this.isDraw==MenuButton.Yes){
			g.drawImage(buttonImage,x,y,mp);
		}
		if(this.isDraw==MenuButton.No){
			
		}
	}
	
	public void isPressedButton(int currentX,int currentY){
		
		if(this.isDraw==MenuButton.Yes){
			if(currentX>x-15&&currentX<(x+width-15)&&currentY>(y-6)&&currentY<(y+height-6)){
				buttonImage=pressedImage;
				isclicked=true;
			}else
			buttonImage=normalImage;
		}else{
			
		}
	}
	
	public boolean isIsMoveIn(){
		return isMoveIn;
	}
}
