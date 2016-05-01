package battle;

import java.awt.*;

import tools.Reader;
//��������
public class ProgressBar {
   //������ͼƬ���ü�������
	Image Bar;
	int BarX;
	int BarY;
   //Сͷ��ͼƬ���ü�������
	Image zhang;
	int ZhangX;
	Image yujie;
	int YuX;
	Image luxueqi;
	int LuX;
	Image pet;
	int petX;
	Image enemy1;
	int Enemy1X;
	Image enemy2;
	int Enemy2X;
	Image enemy3;
	int Enemy3X;
	//�Ƿ񻭳�
	boolean isDraw;
	//�Ƿ�ֹͣ
	boolean isStop;
	
	BattlePanel bp;
	
	//���췽��
	public ProgressBar(int x,int y,BattlePanel bp){
		this.BarX=x;
		this.BarY=y;
		
		this.bp=bp;
		ZhangX=BarX;
		YuX=BarX;
		LuX=BarX;
		petX=BarX;
		Enemy1X=BarX;
		Enemy2X=BarX;
		Enemy3X=BarX;
		
		isDraw=true;
		isStop=false;
		
		getImage();
		
	}
	//��ȡͼƬ
	public void getImage(){
		Bar=Reader.readImage("image/������/������.png");
		if(bp.zxf!=null){
		zhang=bp.zxf.headImage;
		}
		if(bp.yj!=null){
		yujie=bp.yj.headImage;
		}
		if(bp.lxq!=null){
		luxueqi=bp.lxq.headImage;
		}
	    pet=Reader.readImage("image/С����/ͷ��.png");
		if(bp.em1!=null){
		enemy1=bp.em1.headImage;
		}
		if(bp.em2!=null){
		enemy2=bp.em2.headImage;
		}
		if(bp.em3!=null){
		enemy3=bp.em3.headImage;
		}
	}
	
	//���½���
	public void updateProgress(){
		if(!isStop&&isDraw){
			if(ZhangX-BarX<400&&YuX-BarX<400&&LuX-BarX<400&&Enemy1X-BarX<400&&Enemy2X-BarX<400&&Enemy3X-BarX<400&&petX-BarX<400){
				if(bp.zxf!=null&&!bp.zxf.isDead){
				ZhangX+=ZhangXiaoFan.speed;
				}
				if(bp.yj!=null&&!bp.yj.isDead){
				YuX+=YuJie.speed;
				}
				if(bp.lxq!=null&&!bp.lxq.isDead){
				LuX+=LuXueQi.speed;
				}
				if(bp.pet!=null){
				petX+=bp.pet.speed;
				}
				if(bp.em1!=null){
				Enemy1X+=bp.em1.speed;
				}
				if(bp.em2!=null){
				Enemy2X+=bp.em2.speed;
				}
				if(bp.em3!=null){
				Enemy3X+=bp.em3.speed;
				}
			}else if(ZhangX-BarX>=400){
				isStop=true;
				bp.currentRound=1;
				//�����������Ƴ���
				bp.command.isDraw=true;
		
				//ָʾ������;
				bp.instruct.start();
				
			}
			else if (YuX-BarX>=400){
				isStop=true;
				bp.currentRound=2;
				//�����������Ƴ���
				bp.command.isDraw=true;
			
				//ָʾ������;
				bp.instruct.start();
			}
			else if(LuX-BarX>=400){
				isStop=true;
				bp.currentRound=3;
				//�����������Ƴ���
				bp.command.isDraw=true;
			
				//ָʾ������;
				bp.instruct.start();
			}
			else if(petX-BarX>=400){
				isStop=true;
				bp.currentRound=4;
				bp.currentPattern=1;
				
				//ѡ�񹥻�����
				bp.pet.enemyToAttack();
			}
			else if(bp.em1!=null&&Enemy1X-BarX>=400){
				   isStop=true;
				   bp.currentRound=5;
				   //��ʱ�趨����ģʽΪ��ͨ����
                   bp.enemyAI.skillToUse(bp.em1);
                   //ѡ�񹥻�����
                   if(bp.currentBeAttacked!=4){
                   bp.enemyAI.heroToAttack();
                   }
			   }
			else if(bp.em2!=null&&Enemy2X-BarX>=400){
				   isStop=true;
				   bp.currentRound=6;
				   //��ʱ�趨����ģʽΪ��ͨ����
				   bp.enemyAI.skillToUse(bp.em2);
                   //ѡ�񹥻�����
				   if(bp.currentBeAttacked!=4){
                   bp.enemyAI.heroToAttack();
				   }
			   }
			else if(bp.em3!=null&&Enemy3X-BarX>=400){
				isStop=true;
				bp.currentRound=7;
				//��ʱ�趨����ģʽΪ��ͨ����
				bp.enemyAI.skillToUse(bp.em3);
				//ѡ�񹥻�����
				if(bp.currentBeAttacked!=4){
				bp.enemyAI.heroToAttack();
				}
			}
		}
	}
	
	//�����Լ�
	public void drawProgressBar(Graphics g){
		if(isDraw){
			g.drawImage(Bar, BarX, BarY, bp);
			if(bp.zxf!=null){
				g.drawImage(zhang, ZhangX, BarY, bp);
			}
			if(bp.yj!=null){
				g.drawImage(yujie, YuX,BarY,bp);
			}
			if(bp.lxq!=null){
				g.drawImage(luxueqi, LuX, BarY, bp);
			}
			if(bp.pet!=null){
				g.drawImage(pet,petX,BarY,bp);
			}
			if(bp.em1!=null){
				g.drawImage(enemy1, Enemy1X, BarY, bp);
			}
			if(bp.em2!=null){
				g.drawImage(enemy2, Enemy2X, BarY, bp);
			}
			if(bp.em3!=null){
				g.drawImage(enemy3, Enemy3X, BarY, bp);
			}
		}
	}
}
