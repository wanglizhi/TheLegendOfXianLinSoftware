package battle;

//怪物选择器类
public class EnemySlector {
//是否可以选择
boolean isSlectable;
//怪物1的选择范围
int x1;
int y1;
int width1;
int height1;
//怪物2的选择范围
int x2;
int y2;
int width2;
int height2;
//怪物3的选择范围
int x3;
int y3;
int width3;
int height3;

//战斗面板
BattlePanel bp;

//构造方法
public EnemySlector(BattlePanel bp){
	this.bp=bp;
	isSlectable=false;
	//怪物1
	if(bp.em1!=null){
		//得到图片的宽和高
		x1=bp.em1.x;
		y1=bp.em1.y;
		width1=bp.em1.Images.get(0).getWidth(bp);
		height1=bp.em1.Images.get(0).getHeight(bp);
	}
	
	//怪物2
		if(bp.em2!=null){
			//得到图片的宽和高
			x2=bp.em2.x;
			y2=bp.em2.y;
			width2=bp.em2.Images.get(0).getWidth(bp);
			height2=bp.em2.Images.get(0).getHeight(bp);
		}
		
		//怪物3
		if(bp.em3!=null){
			//得到图片的宽和高
			x3=bp.em3.x;
			y3=bp.em3.y;
			width3=bp.em3.Images.get(0).getWidth(bp);
			height3=bp.em3.Images.get(0).getHeight(bp);
		}	
}

    //判断是否移动到某个敌人区域内
    public void checkMoveIn(int currentX,int currentY){
    	if(isSlectable){
    		if(bp.em1!=null){
    		if(currentX>=x1&&currentX<=x1+width1&&currentY>=y1&&currentY<=y1+height1){
    			bp.em1.currentImage=bp.em1.selectedImage;
    			bp.em1.isStop=true;
    		}else {
    			bp.em1.isStop=false;
    		}
    		}
    		if(bp.em2!=null){
    		if(currentX>=x2&&currentX<=x2+width2&&currentY>=y2&&currentY<=y2+height2){
    			bp.em2.currentImage=bp.em2.selectedImage;
    			bp.em2.isStop=true;
    		}else {
    			bp.em2.isStop=false;
    		}
    		}
    		if(bp.em3!=null){
    		if(currentX>=x3&&currentX<=x3+width3&&currentY>=y3&&currentY<=y3+height1){
    			bp.em3.currentImage=bp.em3.selectedImage;
    			bp.em3.isStop=true;
    		}else {
    			bp.em3.isStop=false;
    		}
    		}
    	}
    }
    
    //判断是否点击了某个敌人区域
    public void checkClick(int currentX,int currentY){
    	if(isSlectable){
    		//检查怪物1
    		if(bp.em1!=null){
    		if(currentX>=x1&&currentX<=x1+width1&&currentY>=y1&&currentY<=y1+height1){
    			bp.currentBeAttacked=5;
    			isSlectable=false;
    			//恢复动态
    			bp.em1.isDraw=true;
    			bp.em1.isStop=false;
    		   }
    		}
    		
    		//检查怪物2
    		if(bp.em2!=null){
        		if(currentX>=x2&&currentX<=x2+width2&&currentY>=y2&&currentY<=y2+height2){
        		bp.currentBeAttacked=6;
        		isSlectable=false;
        		//恢复动态
        		bp.em2.isDraw=true;
    			bp.em2.isStop=false;
        	  }
            }
    		
    		//检查怪物3
    		if(bp.em3!=null){
        		if(currentX>=x3&&currentX<=x3+width3&&currentY>=y3&&currentY<=y3+height1){
        		bp.currentBeAttacked=7;
        		isSlectable=false;
        		//恢复动态
        		bp.em3.isDraw=true;
    			bp.em3.isStop=false;
        		}
    		}
    		
    }
  }
}
