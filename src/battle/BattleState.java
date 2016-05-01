package battle;
import java.awt.*;

import tools.Reader;

//ս���г��ֵ�״̬��
public class BattleState {
    //�����Ļغ���
	int roundNum;
	//ս���������
	BattlePanel bp;
	//����
	int type;
	//�Ƿ���Ч
	boolean isUsable;
	//�Ƿ���
	boolean isCheck;
	//�ɹ��ĸ���
	int successRate;
	//������״̬�������� 1.��С�� 2.���� 3.½ѩ�� 5.����1 6.����2 7.����3
	int roleCode;
	//״̬��ʾ��ͼƬ
	Image  stateImage;
	//��ʾ������
	int x;
	int y;
	
	//���췽��
	public BattleState(BattlePanel bp){
		this.bp=bp;
	}
	
	
	//����״̬��ʾ
	public void drawState(Graphics g){
		if(isUsable){
			g.drawImage(stateImage, x, y, bp);
		}
	}
	
	//��ȡͼƬ
	public void getImage(){
		switch(type){
		case 1:
			stateImage=Reader.readImage("image/״̬/��������.png");
			break;
		case 2:
			stateImage=Reader.readImage("image/״̬/��������.png");
			break;
		case 3:
			stateImage=Reader.readImage("image/״̬/��������.png");
			break;
		case 4:
			stateImage=Reader.readImage("image/״̬/��������.png");
			break;
		case 5:
			stateImage=Reader.readImage("image/״̬/�����½�.png");
			break;
		case 6:
			stateImage=Reader.readImage("image/״̬/�����½�.png");
			break;
		case 7:
			stateImage=Reader.readImage("image/״̬/�����½�.png");
			break;
		case 8:
			stateImage=Reader.readImage("image/״̬/�����½�.png");
			break;
		case 9:
			stateImage=Reader.readImage("image/״̬/�ж�.png");
			break;
		case 10:
			stateImage=Reader.readImage("image/״̬/���.png");
			break;
		case 11:
			stateImage=Reader.readImage("image/״̬/������.png");
			break;
		case 12:
			stateImage=Reader.readImage("image/״̬/Ǳ�ܱ���.png");
			break;
		}
	}
	
	//����
	public void set(int roundNum,int type,int successRate,int roleCode,int x,int y){	
	    double p=Math.random()*100;
	    if(p<successRate){
	    	//���֮ǰ����״̬��Ч��û�����
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
	
	//���
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
	
	//���ݽ�ɫ������ж��ĸ�����ִ��״̬��Ч��
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
	
	//���ݽ�ɫ������ж��ĸ���ɫҪִ��״̬�Ļָ�
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
	
	
	//���
	public void clear(){
		isUsable=false;
		isCheck=false;
		type=0;
		roundNum=0;
		roleCode=0;
		successRate=0;
	}
}
