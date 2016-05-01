package battle;
import tools.*;

import java.util.*;
import java.awt.*;

//我方战斗单位死亡之后播放的动画
public class DeadAnimation {
	    //当前图片
		Image currentImage;
		//图片集合
		ArrayList<Image> images=new ArrayList<Image>();
		//画出的坐标
		int x;
		int y;
		//长度
		int length;
		//编号..计时器
		int code;
		//是否画出
		boolean isDraw;
		//是否停止
		boolean isStop;
		//战斗面板引用
		BattlePanel bp;
		//英雄引用
		Hero hero;
		
		//构造方法
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
		
		//载入图片
		public void loadImage(int length,String name){
			for(int i=1;i<=length;i++){
				Image image=Reader.readImage("image/死亡动画/"+name+"/"+i+".png");
			    images.add(image);
			}
		}
		
		//画出
		public void drawDeadAniamtion(Graphics g){
			if(isDraw){
				g.drawImage(currentImage, x, y, bp);
			}
		}
		
		//开始
		public void start(){
			hero.setIsDraw(false);
			isDraw=true;
			isStop=false;
		}
		
		//更新
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
