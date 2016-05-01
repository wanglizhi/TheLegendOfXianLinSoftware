package battle;
import tools.*;
import java.awt.*;
import java.util.*;
//游标类
public class Mouse {
   //图片引用
	Image mouseImage;
	//当前的图片
	Image currentImage;
    //图片集合
	ArrayList<Image> images =new ArrayList<Image>();
	//坐标
	int x;
	int y;
	//是否画出
	boolean isDraw;
	//是否停止
	boolean isStop;
	//战斗面板引用
	BattlePanel bp;
	
	//编号
	int code;
	
	//构造方法
	public Mouse(BattlePanel bp){
		this.bp=bp;
		x=bp.currentX;
		y=bp.currentY;
		getImage();
		isDraw=true;
		isStop=false;
	}
	
	//读取图片
	public void getImage(){
		for(int i=1;i<=8;i++){
			mouseImage =Reader.readImage("image/鼠标图/"+i+".png");
			images.add(mouseImage);
		}
	}
	
	//画出游标
	public void drawMouse(Graphics g){
		if(isDraw){
		g.drawImage(currentImage, x, y, bp);
		}
	}
	
	//更新方法
	public void update(){
		x=bp.currentX;
		y=bp.currentY;
		if(!isStop&&code<8){
			currentImage=images.get(code);
			code++;
		}
		else if(code==8){
			code=0;
		}
	}
}
