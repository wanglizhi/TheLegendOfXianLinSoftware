package battle;

import java.awt.*;

import main.GameLauncher;
import tools.*;

//�ҷ�ȫ��֮���ͼ��
public class GameOver {
Image image1;
Image image2;

//��ͼ�İ˸�����
int ldx1;
int ldy1;
int ldx2;
int ldy2;
int lsx1;
int lsy1;
int lsx2;
int lsy2;

//��ͼ�İ˸�����
int rdx1;
int rdy1;
int rdx2;
int rdy2;
int rsx1;
int rsy1;
int rsx2;
int rsy2;

//������
int code;
//�Ƿ񻭳�
boolean isDraw;
//�Ƿ�ֹͣ
boolean isStop;
//ս���������
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

//����ͼƬ
public void loadImage(){
	image1=Reader.readImage("image/ȫ��ͼ/ȫ��ͼ1.png");
	image2=Reader.readImage("image/ȫ��ͼ/ȫ��ͼ2.png");
}

//����
public void drawGameOver(Graphics g){
	if(isDraw){
	g.drawImage(image1, ldx1, ldy1, ldx2, ldy2, lsx1, lsy1, lsx2, lsy2, bp);
	g.drawImage(image2, rdx1, rdy1, rdx2, rdy2, rsx1, rsy1, rsx2, rsy2, bp);
	}
}

//����
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
				//��ת�ؿ�ʼ����
				if(bp.em1.name.equals("����ʿ")){
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
