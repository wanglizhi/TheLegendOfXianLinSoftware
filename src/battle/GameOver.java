package battle;

import java.awt.*;

import main.GameLauncher;
import tools.*;

//我方全灭之后的图案
public class GameOver {
Image image1;
Image image2;

//左图的八个坐标
int ldx1;
int ldy1;
int ldx2;
int ldy2;
int lsx1;
int lsy1;
int lsx2;
int lsy2;

//右图的八个坐标
int rdx1;
int rdy1;
int rdx2;
int rdy2;
int rsx1;
int rsy1;
int rsx2;
int rsy2;

//计数器
int code;
//是否画出
boolean isDraw;
//是否停止
boolean isStop;
//战斗面板引用
BattlePanel bp;

public GameOver(BattlePanel bp){
	this.bp=bp;
	loadImage();
	this.isDraw=false;
	this.isStop=true;
	ldx1=0;
	ldy1=0;
	ldx2=0;
	ldy2=640;
	lsx1=0;
	lsy1=0;
	lsx2=0;
	lsy2=640;
	
	rdx1=1024;
	rdy1=0;
	rdx2=1024;
	rdy2=640;
	rsx1=512;
	rsy1=0;
	rsx2=512;
	rsy2=640;
}

//载入图片
public void loadImage(){
	image1=Reader.readImage("image/全灭图/全灭图1.png");
	image2=Reader.readImage("image/全灭图/全灭图2.png");
}

//画出
public void drawGameOver(Graphics g){
	if(isDraw){
	g.drawImage(image1, ldx1, ldy1, ldx2, ldy2, lsx1, lsy1, lsx2, lsy2, bp);
	g.drawImage(image2, rdx1, rdy1, rdx2, rdy2, rsx1, rsy1, rsx2, rsy2, bp);
	}
}

//更新
public void update(){
	if(!isStop){
		if(lsx2<512){
			lsx2+=8;
			ldx2+=8;
			rsx1-=8;
			rdx1-=8;
		}
		if(lsx2==512){
			if(code<10){
				code++;
			}
			if(code==10){
				//跳转回开始界面
				if(bp.em1.name.equals("罹年居士")){
					GameLauncher.switchTo("scene");
					bp.em1=null;
					bp.enemies.clear();
					bp.zxf.deadAnimation.isDraw=false;
					bp.zxf.isDraw=true;
                    ZhangXiaoFan.hp=ZhangXiaoFan.hpMax/2;
					bp.yj.deadAnimation.isDraw=false;
					bp.yj.isDraw=true;
					YuJie.hp=YuJie.hpMax/2;
					bp.heroes.clear();
				}else{
				GameLauncher.switchTo("start");
				bp.em1=null;
				bp.em2=null;
				bp.em3=null;
				bp.enemies.clear();
				if(bp.zxf!=null){
				bp.zxf.deadAnimation.isDraw=false;
				bp.zxf.isDraw=true;
				}
				if(bp.yj!=null){
				bp.yj.deadAnimation.isDraw=false;
				bp.yj.isDraw=true;
				}
				if(bp.lxq!=null){
				bp.lxq.isDraw=true;
				bp.lxq.deadAnimation.isDraw=false;
				}
				bp.heroes.clear();
				}
				isStop=true;
			}
		}
	}
}
}
