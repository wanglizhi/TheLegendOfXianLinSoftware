package battle;
import java.awt.*;
import java.util.*;

import tools.*;

public class SkillAnimation {
//当前图片引用
Image currentImage;
//图片集合
public ArrayList<Image> Images=new ArrayList<Image>();
//编号
int code;
//长度
int length;
//出现位置
int x;
int y;
//初始位置
int initialX;
int initialY;
//战斗面板引用
BattlePanel bp;
//是否画出
boolean isDraw;
//是否停止
boolean isStop;
//是否发动结束的信号
boolean isOver;

//被攻击方开始被击动画的编号
int beAttackedCode;
//被击动画播放的次数
int beAttackedTimes;
//技能动画三个阶段的信号
//跑位图
int runCode;
//攻击图
int attackCode;
//撤回图
int withdrawCode;

//相对于怪物1(中间)的偏移量
int offsetTo1;
//相对于怪物2(上方)的偏移量
int offsetTo2;
//相对于怪物3(下方)的偏移量
int offsetTo3;

String name;;

//构造方法
public SkillAnimation(String name,int length,int x,int y,BattlePanel bp,int beAttackedCode,int beAttackedTimes,int runCode,int attackCode,int withdrawCode,int offsetTo1,int offsetTo2,int offsetTo3){
	this.bp=bp;
	
	this.name=name;
	this.x=x;
	this.y=y;
	
	this.initialX=x;
	this.initialY=y;
	
	this.length=length;
	this.beAttackedCode=beAttackedCode;
	this.beAttackedTimes=beAttackedTimes;
	this.runCode=runCode;
	this.attackCode=attackCode;
	this.withdrawCode=withdrawCode;
	this.offsetTo1=offsetTo1;
	this.offsetTo2=offsetTo2;
	this.offsetTo3=offsetTo3;
	
	isDraw=false;
	isStop=true;
	isOver=false;
}

public SkillAnimation(BattlePanel bp){
	this.bp=bp;
	isDraw=false;
	isStop=true;
	isOver=false;
}

//设置方法
public void set(String name,int length,int x,int y,int beAttackedCode,int beAttackedTimes,int runCode,int attackCode,int withdrawCode,int offsetTo1,int offsetTo2,int offsetTo3){
	this.name=name;
	this.x=x;
	this.y=y;
	
	this.initialX=x;
	this.initialY=y;
	
	this.length=length;
	this.beAttackedCode=beAttackedCode;
	this.beAttackedTimes=beAttackedTimes;
	this.runCode=runCode;
	this.attackCode=attackCode;
	this.withdrawCode=withdrawCode;
	this.offsetTo1=offsetTo1;
	this.offsetTo2=offsetTo2;
	this.offsetTo3=offsetTo3;
	
	currentImage=Reader.readImage("image/技能动画/"+name+"/1.png");
}

//画出动画
public void drawAnimation(Graphics g){
	if(isDraw&&currentImage!=null){
		g.drawImage(currentImage, x, y, bp);
	}
}

//更新
public void update(){
	if(!isStop){
	//对象为敌人一
	if(bp.currentBeAttacked==5||bp.currentBeAttacked==1){
		if (code<length){
			currentImage=Reader.readImage("image/技能动画/"+name+"/"+(code+1)+".png");
			code++;
	}
	if(code==beAttackedCode&&bp.currentBeAttacked==5){
	//让怪物显示被击效果
	bp.em1.isDraw=false;
	bp.em1.beAttackedAnimation.getTimes(beAttackedTimes);
	bp.em1.beAttackedAnimation.isDraw=true;
	bp.em1.beAttackedAnimation.isStop=false;
	currentImage=Reader.readImage("image/技能动画/"+name+"/"+(code+1)+".png");
	code++;
	}
	if(code==beAttackedCode&&bp.currentBeAttacked==1){
	//让张小凡显示被击效果
	bp.zxf.isDraw=false;
	bp.zxf.beAttackedAnimation.getTimes(beAttackedTimes);
	bp.zxf.beAttackedAnimation.isDraw=true;
	bp.zxf.beAttackedAnimation.isStop=false;
	currentImage=Reader.readImage("image/技能动画/"+name+"/"+(code+1)+".png");
	code++;
	}
	if(code<runCode){
			y-=Math.round(offsetTo1/runCode);
		}
		if(attackCode<=code&&code<withdrawCode){
			y+=Math.round(offsetTo1/(withdrawCode-attackCode));
		}
		if(code==length){
			code=0;
			currentImage=null;
			x=initialX;
			y=initialY;
			//停止动画
			isStop=true;
			//不再画出
			isDraw=false;
			//发出动画结束信号
			isOver=true;
		    }
	 }//攻击敌人一结束
		
	//对象为敌人二
	if(bp.currentBeAttacked==6||bp.currentBeAttacked==2){
		if (code<length){
		currentImage=Reader.readImage("image/技能动画/"+name+"/"+(code+1)+".png");
		code++;
	}
    if(code==beAttackedCode&&bp.currentBeAttacked==6){
			//让怪物显示被击效果
			bp.em2.isDraw=false;
			bp.em2.beAttackedAnimation.getTimes(beAttackedTimes);
			bp.em2.beAttackedAnimation.isDraw=true;
			bp.em2.beAttackedAnimation.isStop=false;
			currentImage=Reader.readImage("image/技能动画/"+name+"/"+(code+1)+".png");
			code++;
			}
    if(code==beAttackedCode&&bp.currentBeAttacked==2){
    	//让文敏显示被击效果
    	bp.yj.isDraw=false;
    	bp.yj.beAttackedAnimation.getTimes(beAttackedTimes);
    	bp.yj.beAttackedAnimation.isDraw=true;
    	bp.yj.beAttackedAnimation.isStop=false;
    	currentImage=Reader.readImage("image/技能动画/"+name+"/"+(code+1)+".png");
    	code++;
    	}
	if(code<runCode){
		y-=Math.round(offsetTo2/runCode);
	}
	if(attackCode<=code&&code<withdrawCode){
		y+=Math.round(offsetTo2/(withdrawCode-attackCode));
	}
	if(code==length){
		code=0;
		currentImage=null;
		x=initialX;
		y=initialY;
		//停止动画
		isStop=true;
		//不再画出
		isDraw=false;
		//发出动画结束信号
		isOver=true;
	    }
	  }//攻击敌人2结束
	
	//对象为敌人三
		if(bp.currentBeAttacked==7||bp.currentBeAttacked==3){
			if (code<length){
			currentImage=Reader.readImage("image/技能动画/"+name+"/"+(code+1)+".png");
			code++;
		}
	    if(code==beAttackedCode&&bp.currentBeAttacked==7){
	    	    //MusicReader.readmusic("刀声.wav");
				//让怪物显示被击效果
				bp.em3.isDraw=false;
				bp.em3.beAttackedAnimation.getTimes(beAttackedTimes);
				bp.em3.beAttackedAnimation.isDraw=true;
				bp.em3.beAttackedAnimation.isStop=false;
				currentImage=Reader.readImage("image/技能动画/"+name+"/"+(code+1)+".png");
				code++;
				}
	    if(code==beAttackedCode&&bp.currentBeAttacked==3){
	    	//让陆雪琪显示被击效果
	    	bp.lxq.isDraw=false;
	    	bp.lxq.beAttackedAnimation.getTimes(beAttackedTimes);
	    	bp.lxq.beAttackedAnimation.isDraw=true;
	    	bp.lxq.beAttackedAnimation.isStop=false;
	    	currentImage=Reader.readImage("image/技能动画/"+name+"/"+(code+1)+".png");
	    	code++;
	    	}
		if(code<runCode){
			y-=Math.round(offsetTo3/runCode);
		}
		if(attackCode<=code&&code<withdrawCode){
			y+=Math.round(offsetTo3/(withdrawCode-attackCode));
		}
		if(code==length){
			code=0;
			currentImage=null;
			x=initialX;
			y=initialY;
			//停止动画
			isStop=true;
			//不再画出
			isDraw=false;
			//发出动画结束信号
			isOver=true;
		    }
		  }//攻击敌人3结束
	
	//对象为敌人的全体
	if(bp.currentBeAttacked==8){
		if(code<length){
			currentImage=Reader.readImage("image/技能动画/"+name+"/"+(code+1)+".png");
			code++;
		}
		if(code==beAttackedCode){
			//MusicReader.readmusic("刀声.wav");
			for(Enemy enemy:bp.enemies){
				enemy.isDraw=false;
				enemy.beAttackedAnimation.getTimes(beAttackedTimes);
				enemy.beAttackedAnimation.isDraw=true;
				enemy.beAttackedAnimation.isStop=false;
			}
			currentImage=Reader.readImage("image/技能动画/"+name+"/"+(code+1)+".png");
			code++;
		}
		if(code<runCode){
			y-=Math.round(offsetTo1/runCode);
		}
		if(attackCode<=code&&code<withdrawCode){
			y+=Math.round(offsetTo1/(withdrawCode-attackCode));
		}
		if(code==length){
			code=0;
			currentImage=null;
			x=initialX;
			y=initialY;
			//停止动画
			isStop=true;
			//不再画出
			isDraw=false;
			//发出动画结束信号
			isOver=true;
		    }
	}
	
	//对象为我方全体
	if(bp.currentBeAttacked==4){
		if(code<length){
			currentImage=Reader.readImage("image/技能动画/"+name+"/"+(code+1)+".png");
			code++;
		}
		if(code==beAttackedCode){
			for(Hero hero:bp.heroes){
				if(!hero.wheatherDead()){
				hero.setIsDraw(false);
				hero.getBeAttackedAnimation().getTimes(beAttackedTimes);
				hero.getBeAttackedAnimation().isDraw=true;
				hero.getBeAttackedAnimation().isStop=false;
				}
			}
			currentImage=Reader.readImage("image/技能动画/"+name+"/"+(code+1)+".png");
			code++;
		}
		if(code<runCode){
			y-=Math.round(offsetTo1/runCode);
		}
		if(attackCode<=code&&code<withdrawCode){
			y+=Math.round(offsetTo1/(withdrawCode-attackCode));
		}
		if(code==length){
			code=0;
			currentImage=null;
			x=initialX;
			y=initialY;
			//停止动画
			isStop=true;
			//不再画出
			isDraw=false;
			//发出动画结束信号
			isOver=true;
		    }
	   }
       }
	}
}


