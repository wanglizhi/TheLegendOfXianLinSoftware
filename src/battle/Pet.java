package battle;
import java.awt.*;
import tools.*;

//½ѩ���ٻ�����С������
public class Pet {
Image petImage;
//Сͷ��
Image headImage;
//������
int power;
//�ٶ�
int speed;
//λ������
int x;
int y;
//ս���������
BattlePanel bp;
//��ǰ��������
Enemy currentEnemy;
//��ǰ��ɵ��˺�
int currentDamage;
//��ǰ��ɵ��˺�����
int currentDamageType;

//�Ƿ񻭳�
boolean isDraw;
//�Ƿ�ֹͣ
boolean isStop;
//��ʱ��
int code;

//���췽��
public Pet(BattlePanel bp){
	this.bp=bp;
	x=700;
	y=400;
	loadImage();
	loadAnimation();
	isDraw=true;
	isStop=false;
	
	speed=(int)((ZhangXiaoFan.speed+LuXueQi.speed+YuJie.speed)/3);
	power=(int)((ZhangXiaoFan.hurt+LuXueQi.hurt+YuJie.hurt)/3);
}
//ѡ�񹥻�����
public void enemyToAttack(){
	int i=0;
	while(true){
		i=(int)(Math.random()*3)+5;
		if(i==5&&bp.em1!=null){
			bp.currentBeAttacked=5;
			break;
		}
		if(i==6&&bp.em2!=null){
			bp.currentBeAttacked=6;
			break;
		}
		if(i==7&&bp.em3!=null){
			bp.currentBeAttacked=7;
			break;
		}
	}
}

//�����˺�
public void calDamage(){
	switch(bp.currentBeAttacked){
	case 5:
		currentEnemy=bp.em1;
		break;
	case 6:
		currentEnemy=bp.em2;
		break;
	case 7:
		currentEnemy=bp.em3;
		break;
	}
	
	currentDamage=power-currentEnemy.defense;
	if(currentDamage<0){
		currentDamage=0;
	}
	currentDamageType=1;
	currentEnemy.hp-=currentDamage;
	currentEnemy.hp-=currentDamage;
	HurtValue hurtValue=new HurtValue(bp);
	hurtValue.show(currentDamage, currentDamageType,currentEnemy.x,currentEnemy.y);
	bp.hurtValues.add(hurtValue);
}

//����
public void attack(){
	isDraw=false;
	bp.skillAnimation.set("С���鹥��", 22, 120, 135, 8, 1, 6, 16, 22, 90, 210, 0);
	bp.skillAnimation.isDraw=true;
	bp.skillAnimation.isStop=false;
}

//����ͼƬ
public void loadImage(){
	petImage=Reader.readImage("image/С����/С����.png");
	headImage=Reader.readImage("image/С����/ͷ��.png");
}
//���붯��
public void loadAnimation(){
}
//����
public void drawPet(Graphics g){
	if(isDraw){
		g.drawImage(petImage, x, y, bp);
	}
}

//����
public void update(){
	if(!isStop){
		if(code<5){
			y--;
			code++;
		}
		if(code>=5&&code<10){
			y++;
			code++;
		}
		if(code==10){
			code=0;
		}
	}
}

}
