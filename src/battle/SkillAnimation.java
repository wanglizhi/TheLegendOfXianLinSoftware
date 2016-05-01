package battle;
import java.awt.*;
import java.util.*;

import tools.*;

public class SkillAnimation {
//��ǰͼƬ����
Image currentImage;
//ͼƬ����
public ArrayList<Image> Images=new ArrayList<Image>();
//���
int code;
//����
int length;
//����λ��
int x;
int y;
//��ʼλ��
int initialX;
int initialY;
//ս���������
BattlePanel bp;
//�Ƿ񻭳�
boolean isDraw;
//�Ƿ�ֹͣ
boolean isStop;
//�Ƿ񷢶��������ź�
boolean isOver;

//����������ʼ���������ı��
int beAttackedCode;
//�����������ŵĴ���
int beAttackedTimes;
//���ܶ��������׶ε��ź�
//��λͼ
int runCode;
//����ͼ
int attackCode;
//����ͼ
int withdrawCode;

//����ڹ���1(�м�)��ƫ����
int offsetTo1;
//����ڹ���2(�Ϸ�)��ƫ����
int offsetTo2;
//����ڹ���3(�·�)��ƫ����
int offsetTo3;

String name;;

//���췽��
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

//���÷���
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
	
	currentImage=Reader.readImage("image/���ܶ���/"+name+"/1.png");
}

//��������
public void drawAnimation(Graphics g){
	if(isDraw&&currentImage!=null){
		g.drawImage(currentImage, x, y, bp);
	}
}

//����
public void update(){
	if(!isStop){
	//����Ϊ����һ
	if(bp.currentBeAttacked==5||bp.currentBeAttacked==1){
		if (code<length){
			currentImage=Reader.readImage("image/���ܶ���/"+name+"/"+(code+1)+".png");
			code++;
	}
	if(code==beAttackedCode&&bp.currentBeAttacked==5){
	//�ù�����ʾ����Ч��
	bp.em1.isDraw=false;
	bp.em1.beAttackedAnimation.getTimes(beAttackedTimes);
	bp.em1.beAttackedAnimation.isDraw=true;
	bp.em1.beAttackedAnimation.isStop=false;
	currentImage=Reader.readImage("image/���ܶ���/"+name+"/"+(code+1)+".png");
	code++;
	}
	if(code==beAttackedCode&&bp.currentBeAttacked==1){
	//����С����ʾ����Ч��
	bp.zxf.isDraw=false;
	bp.zxf.beAttackedAnimation.getTimes(beAttackedTimes);
	bp.zxf.beAttackedAnimation.isDraw=true;
	bp.zxf.beAttackedAnimation.isStop=false;
	currentImage=Reader.readImage("image/���ܶ���/"+name+"/"+(code+1)+".png");
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
			//ֹͣ����
			isStop=true;
			//���ٻ���
			isDraw=false;
			//�������������ź�
			isOver=true;
		    }
	 }//��������һ����
		
	//����Ϊ���˶�
	if(bp.currentBeAttacked==6||bp.currentBeAttacked==2){
		if (code<length){
		currentImage=Reader.readImage("image/���ܶ���/"+name+"/"+(code+1)+".png");
		code++;
	}
    if(code==beAttackedCode&&bp.currentBeAttacked==6){
			//�ù�����ʾ����Ч��
			bp.em2.isDraw=false;
			bp.em2.beAttackedAnimation.getTimes(beAttackedTimes);
			bp.em2.beAttackedAnimation.isDraw=true;
			bp.em2.beAttackedAnimation.isStop=false;
			currentImage=Reader.readImage("image/���ܶ���/"+name+"/"+(code+1)+".png");
			code++;
			}
    if(code==beAttackedCode&&bp.currentBeAttacked==2){
    	//��������ʾ����Ч��
    	bp.yj.isDraw=false;
    	bp.yj.beAttackedAnimation.getTimes(beAttackedTimes);
    	bp.yj.beAttackedAnimation.isDraw=true;
    	bp.yj.beAttackedAnimation.isStop=false;
    	currentImage=Reader.readImage("image/���ܶ���/"+name+"/"+(code+1)+".png");
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
		//ֹͣ����
		isStop=true;
		//���ٻ���
		isDraw=false;
		//�������������ź�
		isOver=true;
	    }
	  }//��������2����
	
	//����Ϊ������
		if(bp.currentBeAttacked==7||bp.currentBeAttacked==3){
			if (code<length){
			currentImage=Reader.readImage("image/���ܶ���/"+name+"/"+(code+1)+".png");
			code++;
		}
	    if(code==beAttackedCode&&bp.currentBeAttacked==7){
	    	    //MusicReader.readmusic("����.wav");
				//�ù�����ʾ����Ч��
				bp.em3.isDraw=false;
				bp.em3.beAttackedAnimation.getTimes(beAttackedTimes);
				bp.em3.beAttackedAnimation.isDraw=true;
				bp.em3.beAttackedAnimation.isStop=false;
				currentImage=Reader.readImage("image/���ܶ���/"+name+"/"+(code+1)+".png");
				code++;
				}
	    if(code==beAttackedCode&&bp.currentBeAttacked==3){
	    	//��½ѩ����ʾ����Ч��
	    	bp.lxq.isDraw=false;
	    	bp.lxq.beAttackedAnimation.getTimes(beAttackedTimes);
	    	bp.lxq.beAttackedAnimation.isDraw=true;
	    	bp.lxq.beAttackedAnimation.isStop=false;
	    	currentImage=Reader.readImage("image/���ܶ���/"+name+"/"+(code+1)+".png");
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
			//ֹͣ����
			isStop=true;
			//���ٻ���
			isDraw=false;
			//�������������ź�
			isOver=true;
		    }
		  }//��������3����
	
	//����Ϊ���˵�ȫ��
	if(bp.currentBeAttacked==8){
		if(code<length){
			currentImage=Reader.readImage("image/���ܶ���/"+name+"/"+(code+1)+".png");
			code++;
		}
		if(code==beAttackedCode){
			//MusicReader.readmusic("����.wav");
			for(Enemy enemy:bp.enemies){
				enemy.isDraw=false;
				enemy.beAttackedAnimation.getTimes(beAttackedTimes);
				enemy.beAttackedAnimation.isDraw=true;
				enemy.beAttackedAnimation.isStop=false;
			}
			currentImage=Reader.readImage("image/���ܶ���/"+name+"/"+(code+1)+".png");
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
			//ֹͣ����
			isStop=true;
			//���ٻ���
			isDraw=false;
			//�������������ź�
			isOver=true;
		    }
	}
	
	//����Ϊ�ҷ�ȫ��
	if(bp.currentBeAttacked==4){
		if(code<length){
			currentImage=Reader.readImage("image/���ܶ���/"+name+"/"+(code+1)+".png");
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
			currentImage=Reader.readImage("image/���ܶ���/"+name+"/"+(code+1)+".png");
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
			//ֹͣ����
			isStop=true;
			//���ٻ���
			isDraw=false;
			//�������������ź�
			isOver=true;
		    }
	   }
       }
	}
}


