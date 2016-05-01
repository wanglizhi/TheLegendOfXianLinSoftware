package menu;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
//用于画出新的鼠标
public class Mouse {
	
	//引用
	FatherPanel fp;
	
	Image mouseImage;//图片引用
	Image currentImage;//当前的图片
	ArrayList<Image> images =new ArrayList<Image>();//图片集合
	int x,y;//坐标
	int code;//编号
			
			//构造方法
	public Mouse(FatherPanel a){
		this.fp=a;
		x=fp.currentX;
		y=fp.currentY;
		getImage();
				
	}
			
			//读取图片
	public void getImage(){
		for(int i=1;i<=8;i++){
			mouseImage =new ImageIcon("sources/菜单/鼠标图/"+i+".png").getImage();
			images.add(mouseImage);
		}
		currentImage=images.get(0);
	}
			
			//画出游标
	public void drawMouse(Graphics g){
		g.drawImage(currentImage, x, y, fp);
	}
			
			//更新方法
	public void update(){
		x=fp.currentX;
		y=fp.currentY;
		if(code<8){
			currentImage=images.get(code);
			code++;
		}
		else if(code==8){
			code=1;
		}
	}
	
}
