package battle;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import media.MusicReader;

import tools.Reader;

//受到攻击的动画
public class BeAttackedAnimation {
	//当前图片引用
	Image currentImage;
	//图片集合
	ArrayList<Image> Images=new ArrayList<Image>();
	//编号
	int code;
	//当前次数
	int currentTime;
	//次数
	int times;
	//长度
	int length;
	//出现位置
	int x;
	int y;
	//战斗面板引用
	BattlePanel bp;
	//是否画出
	boolean isDraw;
	//是否停止
	boolean isStop;
	
	//获得图片
	public void getImage (String name,int length){
		for(int i=1;i<=length;i++){
			Image image=Reader.readImage("image/被击动画/"+name+"/"+i+".png");
			Images.add(image);
		}
		currentImage=Images.get(0);
	}
	
	//构造方法
	public BeAttackedAnimation(String name,int length,int x,int y,BattlePanel bp){
		this.bp=bp;
		getImage(name, length);
		
		this.x=x;
		this.y=y;
		this.length=length;
	    this.currentTime=1;
		
		isDraw=false;
		isStop=true;
	}
	
	//得到播放次数
	public void getTimes(int times){
		this.times=times;
	}
	//画出动画
	public void drawAnimation(Graphics g){
		if(isDraw&&currentImage!=null){
			g.drawImage(currentImage, x, y, bp);
		}
	}
	
	//更新
	public void update(){
		if(!isStop&&code<length){
			currentImage=Images.get(code);
			code++;
		}else if(code==length){
			code=0;
			currentImage=Images.get(code);
			
			if(currentTime==times){
			//停止动画
			isStop=true;
			//不再画出
			isDraw=false;
			currentTime=1;
			
			//重新出现
			if(bp.currentBeAttacked==1){
			bp.zxf.isDraw=true;
			}
			if(bp.currentBeAttacked==2){
			bp.yj.isDraw=true;
			}
			if(bp.currentBeAttacked==3){
			bp.lxq.isDraw=true;
			}
			if(bp.currentBeAttacked==4){
			for(Hero h:bp.heroes){
			if(!h.wheatherDead())
			h.setIsDraw(true);
			}
			}
			if(bp.currentBeAttacked==5){
			bp.em1.isDraw=true;
			}
			if(bp.currentBeAttacked==6){
			bp.em2.isDraw=true;
			}
			if(bp.currentBeAttacked==7){
			bp.em3.isDraw=true;
			}
			
			if(bp.currentBeAttacked==8){
				for(Enemy e:bp.enemies){
					e.isDraw=true;
				}
			}
			}else
			currentTime++;
            MusicReader.readmusic("刀声.wav");
			}
	   }
  }

