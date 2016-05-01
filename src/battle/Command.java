package battle;
import tools.*;
import java.awt.*;
import java.util.*;

//����̨��
public class Command {
    //��ť����
	GameButton attack;
	GameButton skill;
	GameButton defend;
	GameButton thing;
	//��ť����
	ArrayList<GameButton> gameButtons=new ArrayList<GameButton>();
	//ͼƬ����
	Image normalImage;
	Image waitclickImage;
	Image pressedImage;
	//����ť��λ��
	int x;
    int y;
	//�Ƿ񱻻���
	boolean isDraw;
	//ս���������
	BattlePanel bp;
	
	//���췽��
	public Command(BattlePanel bp){
		
		//��������ť
		normalImage=Reader.readImage("image/��ťͼ/��1.png");
		waitclickImage=Reader.readImage("image/��ťͼ/��2.png");
		pressedImage=Reader.readImage("image/��ťͼ/��3.png");
		this.x=500;
		this.y=300;
		this.bp=bp;
		attack=new GameButton(x, y,58,62,normalImage, waitclickImage, pressedImage, bp);
		gameButtons.add(attack);
		
		//��������ť
		normalImage=Reader.readImage("image/��ťͼ/��1.png");
		waitclickImage=Reader.readImage("image/��ťͼ/��2.png");
		pressedImage=Reader.readImage("image/��ťͼ/��3.png");
		skill=new GameButton(x, y-62,58,62, normalImage, waitclickImage, pressedImage, bp);
		gameButtons.add(skill);
		
		//��������ť
		normalImage=Reader.readImage("image/��ťͼ/��1.png");
		waitclickImage=Reader.readImage("image/��ťͼ/��2.png");
		pressedImage=Reader.readImage("image/��ťͼ/��3.png");
		defend=new GameButton(x-58, y+40, 58, 62, normalImage, waitclickImage, pressedImage, bp);
		gameButtons.add(defend);
		
		//�����ﰴť
		normalImage=Reader.readImage("image/��ťͼ/��1.png");
		waitclickImage=Reader.readImage("image/��ťͼ/��2.png");
		pressedImage=Reader.readImage("image/��ťͼ/��3.png");
		thing=new GameButton(x+58, y+40, 58, 62, normalImage, waitclickImage, pressedImage, bp);
		gameButtons.add(thing);
		
		isDraw=false;
	}
	
	//����Ƿ��ƶ����������̨
	public void checkMoveIn(){
		for(GameButton button:gameButtons){
				button.isMoveIn(bp.currentX, bp.currentY);	
		}
	}
	
	//�������Ƿ�������̨
	public void checkPressed(){
		for(GameButton button:gameButtons){
			button.isPressedButton(bp.currentX, bp.currentY);
		}
	}
	
	//����Ƿ��ɿ����
	public void checkReleased(){
		//���� �� ��ť�Ƿ񱻰���
		if(attack.isclicked==true){
			//�ѿ���̨���ص�
			isDraw=false;
			bp.currentPattern=1;
			//�򿪹���ѡ��
			bp.enemySlector.isSlectable=true;

		}
		
		if(skill.isclicked==true){
			//�ѿ���̨���ص�
			isDraw=false;
			bp.skillMenu.checkRound();
			bp.skillMenu.isDraw=true;
		}
		
		if(thing.isclicked==true){
			//�ѿ���̨���ص�
			isDraw=false;
			bp.drugMenu.checkHero();
			bp.drugMenu.isDraw=true;
		}
		
		if(defend.isclicked==true){
			switch(bp.currentRound){
			case 1:
				if(bp.zxf.isAngry){
			      //�ѿ���̨���ص�
			         isDraw=false;
			      //ѡ����������
			         bp.currentBeAttacked=8;
			       //����ģʽ
			         bp.currentPattern=7;
				}else{
					bp.reminder.show(21);
				}
				break;
			case 2:
				if(bp.yj.isAngry){
					 //�ѿ���̨���ص�
			         isDraw=false;
			      //ѡ����������
			         bp.currentBeAttacked=8;
			       //����ģʽ
			         bp.currentPattern=7;
				}else{
					bp.reminder.show(21);
				}
				break;
			case 3:
				if(bp.lxq.isAngry){
					 //�ѿ���̨���ص�
			         isDraw=false;
			      //ѡ����������
			         bp.currentBeAttacked=8;
			       //����ģʽ
			         bp.currentPattern=7;
				}else{
					bp.reminder.show(21);
				}
				break;
			}
		
		}
		
		
		for(GameButton button:gameButtons){
			button.isRelesedButton(bp.currentX, bp.currentY);
		}
	}
	
	//��������̨
	public void drawCommand(Graphics g){
		if(isDraw){
		for(GameButton button:gameButtons){
			button.drawButton(g);
		   }
		}
	}
}
