package battle;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import media.MusicReader;

import tools.Reader;

//�ܵ������Ķ���
public class BeAttackedAnimation {
	//��ǰͼƬ����
	Image currentImage;
	//ͼƬ����
	ArrayList<Image> Images=new ArrayList<Image>();
	//���
	int code;
	//��ǰ����
	int currentTime;
	//����
	int times;
	//����
	int length;
	//����λ��
	int x;
	int y;
	//ս���������
	BattlePanel bp;
	//�Ƿ񻭳�
	boolean isDraw;
	//�Ƿ�ֹͣ
	boolean isStop;
	
	//���ͼƬ
	public void getImage (String name,int length){
		for(int i=1;i<=length;i++){
			Image image=Reader.readImage("image/��������/"+name+"/"+i+".png");
			Images.add(image);
		}
		currentImage=Images.get(0);
	}
	
	//���췽��
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
	
	//�õ����Ŵ���
	public void getTimes(int times){
		this.times=times;
	}
	//��������
	public void drawAnimation(Graphics g){
		if(isDraw&&currentImage!=null){
			g.drawImage(currentImage, x, y, bp);
		}
	}
	
	//����
	public void update(){
		if(!isStop&&code<length){
			currentImage=Images.get(code);
			code++;
		}else if(code==length){
			code=0;
			currentImage=Images.get(code);
			
			if(currentTime==times){
			//ֹͣ����
			isStop=true;
			//���ٻ���
			isDraw=false;
			currentTime=1;
			
			//���³���
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
            MusicReader.readmusic("����.wav");
			}
	   }
  }

