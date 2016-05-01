package battle;

import java.awt.*;

import tools.Reader;
//进度条类
public class ProgressBar {
   //进度条图片引用及其坐标
	Image Bar;
	int BarX;
	int BarY;
   //小头像图片引用及横坐标
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
	//是否画出
	boolean isDraw;
	//是否停止
	boolean isStop;
	
	BattlePanel bp;
	
	//构造方法
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
	//获取图片
	public void getImage(){
		Bar=Reader.readImage("image/进度条/进度条.png");
		if(bp.zxf!=null){
		zhang=bp.zxf.headImage;
		}
		if(bp.yj!=null){
		yujie=bp.yj.headImage;
		}
		if(bp.lxq!=null){
		luxueqi=bp.lxq.headImage;
		}
	    pet=Reader.readImage("image/小精灵/头像.png");
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
	
	//更新进度
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
				//将控制器绘制出来
				bp.command.isDraw=true;
		
				//指示器画出;
				bp.instruct.start();
				
			}
			else if (YuX-BarX>=400){
				isStop=true;
				bp.currentRound=2;
				//将控制器绘制出来
				bp.command.isDraw=true;
			
				//指示器画出;
				bp.instruct.start();
			}
			else if(LuX-BarX>=400){
				isStop=true;
				bp.currentRound=3;
				//将控制器绘制出来
				bp.command.isDraw=true;
			
				//指示器画出;
				bp.instruct.start();
			}
			else if(petX-BarX>=400){
				isStop=true;
				bp.currentRound=4;
				bp.currentPattern=1;
				
				//选择攻击对象
				bp.pet.enemyToAttack();
			}
			else if(bp.em1!=null&&Enemy1X-BarX>=400){
				   isStop=true;
				   bp.currentRound=5;
				   //暂时设定攻击模式为普通攻击
                   bp.enemyAI.skillToUse(bp.em1);
                   //选择攻击对象
                   if(bp.currentBeAttacked!=4){
                   bp.enemyAI.heroToAttack();
                   }
			   }
			else if(bp.em2!=null&&Enemy2X-BarX>=400){
				   isStop=true;
				   bp.currentRound=6;
				   //暂时设定攻击模式为普通攻击
				   bp.enemyAI.skillToUse(bp.em2);
                   //选择攻击对象
				   if(bp.currentBeAttacked!=4){
                   bp.enemyAI.heroToAttack();
				   }
			   }
			else if(bp.em3!=null&&Enemy3X-BarX>=400){
				isStop=true;
				bp.currentRound=7;
				//暂时设定攻击模式为普通攻击
				bp.enemyAI.skillToUse(bp.em3);
				//选择攻击对象
				if(bp.currentBeAttacked!=4){
				bp.enemyAI.heroToAttack();
				}
			}
		}
	}
	
	//画出自己
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
