package battle;
import java.awt.*;

import tools.Reader;

//战斗中出现的状态类
public class BattleState {
    //持续的回合数
	int roundNum;
	//战斗面板引用
	BattlePanel bp;
	//类型
	int type;
	//是否有效
	boolean isUsable;
	//是否检查
	boolean isCheck;
	//成功的概率
	int successRate;
	//被附加状态的人物编号 1.张小凡 2.文敏 3.陆雪琪 5.怪物1 6.怪物2 7.怪物3
	int roleCode;
	//状态显示的图片
	Image  stateImage;
	//显示的坐标
	int x;
	int y;
	
	//构造方法
	public BattleState(BattlePanel bp){
		this.bp=bp;
	}
	
	
	//画出状态显示
	public void drawState(Graphics g){
		if(isUsable){
			g.drawImage(stateImage, x, y, bp);
		}
	}
	
	//获取图片
	public void getImage(){
		switch(type){
		case 1:
			stateImage=Reader.readImage("image/状态/敏捷提升.png");
			break;
		case 2:
			stateImage=Reader.readImage("image/状态/武力提升.png");
			break;
		case 3:
			stateImage=Reader.readImage("image/状态/精气提升.png");
			break;
		case 4:
			stateImage=Reader.readImage("image/状态/体力提升.png");
			break;
		case 5:
			stateImage=Reader.readImage("image/状态/敏捷下降.png");
			break;
		case 6:
			stateImage=Reader.readImage("image/状态/武力下降.png");
			break;
		case 7:
			stateImage=Reader.readImage("image/状态/精气下降.png");
			break;
		case 8:
			stateImage=Reader.readImage("image/状态/体力下降.png");
			break;
		case 9:
			stateImage=Reader.readImage("image/状态/中毒.png");
			break;
		case 10:
			stateImage=Reader.readImage("image/状态/麻痹.png");
			break;
		case 11:
			stateImage=Reader.readImage("image/状态/金钟罩.png");
			break;
		case 12:
			stateImage=Reader.readImage("image/状态/潜能爆发.png");
			break;
		}
	}
	
	//设置
	public void set(int roundNum,int type,int successRate,int roleCode,int x,int y){	
	    double p=Math.random()*100;
	    if(p<successRate){
	    	//如果之前还有状态的效果没有清除
	    	if(isUsable){
	    		Return();
	    	}
	    	this.isUsable=true;
	    	this.roundNum=roundNum;
	    	this.x=x;
	    	this.y=y;
		    this.type=type;
		    getImage();
		    this.roleCode=roleCode;
		    this.isCheck=true;
	    }
	}
	
	//检查
	public void check(){
		if(isUsable){
			if(bp.currentRound==roleCode&&isCheck){
				isCheck=false;
			    if(roundNum>0){
				   roundNum--;
				   Excute();
			    }else{
			  Return();
			  clear();
			}
			}
			if(bp.currentRound!=roleCode){
				isCheck=true;
			}
		}
	}
	
	//根据角色编号来判断哪个觉得执行状态的效果
	public void Excute(){
		switch(roleCode){
		case 1:
			bp.zxf.excuteState();
			break;
		case 2:
			bp.yj.excuteState();
			break;
		case 3:
			bp.lxq.excuteState();
			break;
		case 5:
			bp.em1.excuteState();
			break;
		case 6:
			bp.em2.excuteState();
			break;
		case 7:
			bp.em3.excuteState();
			break;
		}
	}
	
	//根据角色编号来判断哪个角色要执行状态的恢复
	public void Return(){
		switch(roleCode){
		case 1:
			bp.zxf.returnFromState();
			break;
		case 2:
			bp.yj.returnFromState();
			break;
		case 3:
			bp.lxq.returnFromState();
			break;
		case 5:
			bp.em1.returnFromState();
			break;
		case 6:
			bp.em2.returnFromState();
			break;
		case 7:
			bp.em3.returnFromState();
			break;
		}
	}
	
	
	//清空
	public void clear(){
		isUsable=false;
		isCheck=false;
		type=0;
		roundNum=0;
		roleCode=0;
		successRate=0;
	}
}
