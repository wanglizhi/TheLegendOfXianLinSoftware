package battle;
import java.awt.*;
import tools.*;
//状态栏类
public class StateBlank {
//三主角状态栏背景图片
Image zhangBack;
Image wenBack;
Image luBack;
//生命值和灵力
Image hpImage;
Image mpImage;
//第一张背景坐标
int x;
int y;
//第一张血条坐标
int hpX;
int hpY;
//第一张精气坐标
int mpX;
int mpY;
//血条与精气的宽度和高度
int zHpWidth;
int zMpWidth;
int wHpWidth;
int wMpWidth;
int lHpWidth;
int lMpWidth;
int height;

//字体
Font font;

//是否画出
boolean isDraw;
//是否停止
boolean isStop;
//战斗面板引用
BattlePanel bp;

public StateBlank(BattlePanel bp){
this.bp=bp;

x=0;
y=510;

hpX=90;
hpY=580;

mpX=90;
mpY=600;

height=8;
zHpWidth=(int)(((double)ZhangXiaoFan.hp/ZhangXiaoFan.hpMax)*140);
zMpWidth=(int)(((double)ZhangXiaoFan.mp/ZhangXiaoFan.mpMax)*140);
wHpWidth=(int)(((double)YuJie.hp/YuJie.hpMax)*140);
wMpWidth=(int)(((double)YuJie.mp/YuJie.mpMax)*140);
lHpWidth=(int)(((double)LuXueQi.hp/LuXueQi.hpMax)*140);
lMpWidth=(int)(((double)LuXueQi.mp/LuXueQi.mpMax)*140);

getImage();
isDraw=true;
isStop=true;
}

public void getImage(){
	if(bp.zxf!=null){
		zhangBack=Reader.readImage("image/状态栏/张小凡.png");
	}
	if(bp.yj!=null){
		wenBack=Reader.readImage("image/状态栏/文敏.png");
	}
	if(bp.lxq!=null){
		luBack=Reader.readImage("image/状态栏/陆雪琪.png");
	}
	hpImage=Reader.readImage("image/状态栏/生命值.png");
	mpImage=Reader.readImage("image/状态栏/灵力.png");
}

//画出状态栏
public void drawStateBlank(Graphics g){
	if(isDraw){
		g.drawImage(zhangBack, x, y, bp);
		g.drawImage(wenBack, x+322, y, bp);
		g.drawImage(luBack, x+322*2, y, bp);
	
		
		if(bp.zxf!=null){
			g.drawImage(hpImage, hpX, hpY, hpX+zHpWidth, hpY+height, 0, 0, zHpWidth, height, bp);
			g.drawImage(mpImage, mpX, mpY, mpX+zMpWidth, mpY+height, 0, 0, zMpWidth, height, bp);
			g.setColor(Color.black);
			g.drawString("当前等级 :"+ZhangXiaoFan.level, hpX, hpY-20);
			g.drawString(ZhangXiaoFan.hp+"/"+ZhangXiaoFan.hpMax, hpX+	150, hpY+10);
			g.drawString(ZhangXiaoFan.mp+"/"+ZhangXiaoFan.mpMax, mpX+150, mpY+10);
		}
		
		if(bp.yj!=null){
			g.drawImage(hpImage, hpX+322, hpY, hpX+322+wHpWidth, hpY+height, 0, 0, wHpWidth, height, bp);
			g.drawImage(mpImage, mpX+322, mpY, mpX+322+wMpWidth, mpY+height, 0, 0, wMpWidth, height, bp);
			g.setColor(Color.black);
			g.drawString("当前等级 :"+YuJie.level, hpX+322, hpY-20);
			g.drawString(YuJie.hp+"/"+YuJie.hpMax, hpX+322+150, hpY+10);
			g.drawString(YuJie.mp+"/"+YuJie.mpMax, mpX+322+150, mpY+10);
		}
		
		if(bp.lxq!=null){
			g.drawImage(hpImage, hpX+322*2, hpY, hpX+322*2+lHpWidth, hpY+height, 0, 0, lHpWidth, height, bp);
			g.drawImage(mpImage, mpX+322*2, mpY, mpX+322*2+lMpWidth, mpY+height, 0, 0, lMpWidth, height, bp);
			g.setColor(Color.black);
			g.drawString("当前等级 :"+LuXueQi.level, hpX+322*2, hpY-20);
			g.drawString(LuXueQi.hp+"/"+LuXueQi.hpMax, hpX+322*2+150, hpY+10);
			g.drawString(LuXueQi.mp+"/"+LuXueQi.mpMax, mpX+322*2+150, mpY+10);
		}
	}
}

public void update(){
	if(zHpWidth>(int)(((double)ZhangXiaoFan.hp/ZhangXiaoFan.hpMax)*140)){
		zHpWidth--;
	}
	if(zHpWidth<(int)(((double)ZhangXiaoFan.hp/ZhangXiaoFan.hpMax)*140)){
		zHpWidth++;
	}
	if(zMpWidth>(int)(((double)ZhangXiaoFan.mp/ZhangXiaoFan.mpMax)*140)){
		zMpWidth--;
	}
	if(zMpWidth<(int)(((double)ZhangXiaoFan.mp/ZhangXiaoFan.mpMax)*140)){
		zMpWidth++;
	}
	if(wHpWidth>(int)(((double)YuJie.hp/YuJie.hpMax)*140)){
		wHpWidth--;
	}
	if(wHpWidth<(int)(((double)YuJie.hp/YuJie.hpMax)*140)){
		wHpWidth++;
	}
	if(wMpWidth>(int)(((double)YuJie.mp/YuJie.mpMax)*140)){
		wMpWidth--;
	}
	if(wMpWidth<(int)(((double)YuJie.mp/YuJie.mpMax)*140)){
		wMpWidth++;
	}
	if(lHpWidth>(int)(((double)LuXueQi.hp/LuXueQi.hpMax)*140)){
		lHpWidth--;
	}
	if(lHpWidth<(int)(((double)LuXueQi.hp/LuXueQi.hpMax)*140)){
		lHpWidth++;
	}
	if(lMpWidth>(int)(((double)LuXueQi.mp/LuXueQi.mpMax)*140)){
		lMpWidth--;
	}
	if(lMpWidth<(int)(((double)LuXueQi.mp/LuXueQi.mpMax)*140)){
		lMpWidth++;
	}
}

}
