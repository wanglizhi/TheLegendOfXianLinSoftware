package battle;

//����ѡ������
public class EnemySlector {
//�Ƿ����ѡ��
boolean isSlectable;
//����1��ѡ��Χ
int x1;
int y1;
int width1;
int height1;
//����2��ѡ��Χ
int x2;
int y2;
int width2;
int height2;
//����3��ѡ��Χ
int x3;
int y3;
int width3;
int height3;

//ս�����
BattlePanel bp;

//���췽��
public EnemySlector(BattlePanel bp){
	this.bp=bp;
	isSlectable=false;
	//����1
	if(bp.em1!=null){
		//�õ�ͼƬ�Ŀ�͸�
		x1=bp.em1.x;
		y1=bp.em1.y;
		width1=bp.em1.Images.get(0).getWidth(bp);
		height1=bp.em1.Images.get(0).getHeight(bp);
	}
	
	//����2
		if(bp.em2!=null){
			//�õ�ͼƬ�Ŀ�͸�
			x2=bp.em2.x;
			y2=bp.em2.y;
			width2=bp.em2.Images.get(0).getWidth(bp);
			height2=bp.em2.Images.get(0).getHeight(bp);
		}
		
		//����3
		if(bp.em3!=null){
			//�õ�ͼƬ�Ŀ�͸�
			x3=bp.em3.x;
			y3=bp.em3.y;
			width3=bp.em3.Images.get(0).getWidth(bp);
			height3=bp.em3.Images.get(0).getHeight(bp);
		}	
}

    //�ж��Ƿ��ƶ���ĳ������������
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
    
    //�ж��Ƿ�����ĳ����������
    public void checkClick(int currentX,int currentY){
    	if(isSlectable){
    		//������1
    		if(bp.em1!=null){
    		if(currentX>=x1&&currentX<=x1+width1&&currentY>=y1&&currentY<=y1+height1){
    			bp.currentBeAttacked=5;
    			isSlectable=false;
    			//�ָ���̬
    			bp.em1.isDraw=true;
    			bp.em1.isStop=false;
    		   }
    		}
    		
    		//������2
    		if(bp.em2!=null){
        		if(currentX>=x2&&currentX<=x2+width2&&currentY>=y2&&currentY<=y2+height2){
        		bp.currentBeAttacked=6;
        		isSlectable=false;
        		//�ָ���̬
        		bp.em2.isDraw=true;
    			bp.em2.isStop=false;
        	  }
            }
    		
    		//������3
    		if(bp.em3!=null){
        		if(currentX>=x3&&currentX<=x3+width3&&currentY>=y3&&currentY<=y3+height1){
        		bp.currentBeAttacked=7;
        		isSlectable=false;
        		//�ָ���̬
        		bp.em3.isDraw=true;
    			bp.em3.isStop=false;
        		}
    		}
    		
    }
  }
}
