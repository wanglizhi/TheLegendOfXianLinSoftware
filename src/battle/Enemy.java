package battle;
import java.awt.*;
import java.util.*;
import tools.*;

//������
public class Enemy {
//��ɫ���
int roleCode;
//��ǰͼƬ����
Image currentImage;
//ͷ��ͼƬ
Image headImage;
//����
String name;
//ͼƬ����
ArrayList<Image> Images =new ArrayList<Image>();
//��������
int x;
int y;
//��ǰ���
int code;
//ͼƬ����
int length;
//�Ƿ񻭳�
boolean isDraw;
//�Ƿ�ֹͣ
boolean isStop;
//ս���������
BattlePanel bp;

//�Ƿ�����
boolean isDead;

//�ٶ�
int speed;

//ս��״̬
BattleState battleState;

//������������
BeAttackedAnimation beAttackedAnimation;
//������������
int beAttackedLength;
//������������λ��
int beAttackedX;
int beAttackedY;

//��ѡ�к��ͼƬ
Image selectedImage;

//���˵�ս������

//hp��mp
int hp;
int mp;

//������
int hurt;
//ħ������
int skillHurt;
//������
int defense;
int hurtMax;
int skillHurtMax;
int defenseMax;

//����(Ĭ��Ϊ1)
int skillNum=1;

//��ǰ��ɵ��˺�
int currentDamage;
//��ǰ��ɵ��˺�����
int currentDamageType;
//��ǰ����
ArrayList<Hero> currentEnemies=new ArrayList<Hero>();

//���ڵȼ�������

//սʤ����Ի�õľ���
int exp;
//սʤ����Ի�õ���Ʒ
String thing;
//սʤ����Ի�õĽ�Ǯ
int money;

//�洢������Ϣ
String skillName;
int skillLength;
int skillX;
int skillY;
int beAttackedCode;
int beAttackedTimes;
int runCode;
int attackCode;
int withdrawCode;
int offsetTo1;
int offsetTo2;
int offsetTo3;

public void setSkill(String skillName,int skillLength,int skillX,int skillY,int beAttackedCode,int beAttackedTimes,int runCode,int attackCode,int withdrawCode,int offsetTo1,int offsetTo2,int offsetTo3){
	this.skillName=skillName;
	this.skillLength=skillLength;
	this.skillX=skillX;
	this.skillY=skillY;
	this.beAttackedCode=beAttackedCode;
	this.beAttackedTimes=beAttackedTimes;
	this.runCode=runCode;
	this.attackCode=attackCode;
	this.withdrawCode=withdrawCode;
	this.offsetTo1=offsetTo1;
	this.offsetTo2=offsetTo2;
	this.offsetTo3=offsetTo3;
}

public void calDamage(){
	switch(bp.currentBeAttacked){
	case 1:
		currentEnemies.clear();
		currentEnemies.add(bp.zxf);
		break;
	case 2:
		currentEnemies.clear();
		currentEnemies.add(bp.yj);
		break;
	case 3:
		currentEnemies.clear();
		currentEnemies.add(bp.lxq);
		break;
		//�ҷ�ȫ���ܵ�����
	case 4:
		currentEnemies.clear();
		if(bp.zxf!=null&&!bp.zxf.isDead){
			currentEnemies.add(bp.zxf);
		}
		if(bp.yj!=null&&!bp.yj.isDead){
			currentEnemies.add(bp.yj);
		}
		if(bp.lxq!=null&&!bp.lxq.isDead){
			currentEnemies.add(bp.lxq);
		}
	}
	
	switch(bp.currentPattern){
	//��ͨ����
	case 1:
		for(Hero currentEnemy:currentEnemies){
		currentDamage=hurt-currentEnemy.getDefense()+(int)(Math.random()*5);
		currentDamageType=1;
		if(currentDamage<0){
    		currentDamage=0;
    	}
		
		//���ҷ���λ���Ϸ�ŭֵ
		if(!currentEnemy.wheatherAngry()){
		currentEnemy.setAngryValue(currentEnemy.getAngryValue()+currentDamage);
		}
		//����ҷ���λ�Ƿ�ﵽ��ŭ
		if(currentEnemy.getAngryValue()>=(int)(currentEnemy.getHpMax()*0.8)){
			currentEnemy.setAngry(true);
		}
		
		currentEnemy.setHp(currentEnemy.getHp()-currentDamage);
		//hp����0ʱ���0
		if(currentEnemy.getHp()<0){
			currentEnemy.setHp(0);
		}
		HurtValue hurtValue=new HurtValue(bp);
    	hurtValue.show(currentDamage, currentDamageType,currentEnemy.getShowX(),currentEnemy.getShowY());
    	bp.hurtValues.add(hurtValue);
		}
		break;
		//Ⱥ�幥��
	case 2:
		for(Hero currentEnemy:currentEnemies){
			currentDamage=(int)(hurt*0.6)-currentEnemy.getDefense()+(int)(Math.random()*5);
			currentDamageType=1;
			if(currentDamage<0){
	    		currentDamage=0;
	    	}
			
			//���ҷ���λ���Ϸ�ŭֵ
			if(!currentEnemy.wheatherAngry()){
			currentEnemy.setAngryValue(currentEnemy.getAngryValue()+currentDamage);
			}
			//����ҷ���λ�Ƿ�ﵽ��ŭ
			if(currentEnemy.getAngryValue()>=(int)(currentEnemy.getHpMax()*0.8)){
				currentEnemy.setAngry(true);
			}
			
			currentEnemy.setHp(currentEnemy.getHp()-currentDamage);
			//hp����0ʱ���0
			if(currentEnemy.getHp()<0){
				currentEnemy.setHp(0);
			}
			HurtValue hurtValue=new HurtValue(bp);
	    	hurtValue.show(currentDamage, currentDamageType,currentEnemy.getShowX(),currentEnemy.getShowY());
	    	bp.hurtValues.add(hurtValue);
			}
			break;
	}
}

public Enemy(String name,int roleCode,BattlePanel bp){
	this.roleCode=roleCode;
	initial(name,roleCode);
	this.hurtMax=hurt;
	this.skillHurtMax=skillHurt;
	this.defenseMax=defense;
	this.name=name;
	getImage(name, this.length);
	isDraw=true;
	isStop=false;
	this.bp=bp;
	battleState=new BattleState(bp);
	loadAnimation(name,this.beAttackedLength);
}

//��ʼ�����������
public void initial(String name,int roleCode){
	switch(roleCode){
	case 5:
		this.x=100;
		this.y=220;
		break;
	case 6:
		this.x=60;
		this.y=90;
		break;
	case 7:
		this.x=60;
		this.y=330;
		break;
	}
	
	switch(name){
	case "����1":
		this.beAttackedX=x;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=11;
		this.hurt=250;
		this.skillHurt=hurt;
		this.defense=60;
		this.hp=250;
		this.exp=200;
		skillNum=2;
		thing="��ҩ/1";
		money=1000;
		break;
	case "����2":
		this.beAttackedX=x-125;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=10;
		this.hurt=260;
		this.skillHurt=hurt;
		this.defense=60;
		this.hp=300;
		this.exp=200;
		thing="���Ʒ�/1";
		money=1200;
		break;
	case "��":
		this.beAttackedX=x-70;
		this.beAttackedY=y-60; 
		this.length=4;
		this.beAttackedLength=6;
		this.speed=4;
		this.hurt=200;
		this.skillHurt=hurt;
		this.defense=50;
		this.hp=600;
		this.exp=400;
		skillNum=2;
		thing="�������/2";
		money=2000;
		break;
	case "���ָ���1":
		this.beAttackedX=x-50;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=5;
		this.hurt=150;
		this.skillHurt=hurt;
		this.defense=55;
		this.hp=200;
		this.exp=150;
		thing="��ҩ/1";
		money=1200;
		break;
	case "���ָ���2":
		this.beAttackedX=x-20;
		this.beAttackedY=y;
		this.length=5;
		this.beAttackedLength=6;
		this.speed=12;
		this.hurt=440;
		this.skillHurt=hurt;
		this.defense=130;
		this.hp=500;
		this.exp=500;
		thing="��ũҩ��/1";
		money=3800;
		break;
	case "�轣��":
		this.beAttackedX=x-20;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=6;
		this.hurt=160;
		this.skillHurt=hurt;
		this.defense=40;
		this.hp=280;
		this.exp=160;
		skillNum=2;
		thing="���Ʒ�/1";
		money=1000;
		break;
	case "��������":
		this.beAttackedX=x-10;
		this.beAttackedY=y;
		this.length=9;
		this.beAttackedLength=6;
		this.speed=13;
		this.hurt=450;
		this.skillHurt=hurt;
		this.defense=220;
		this.hp=460;
		this.exp=330;
		thing="���ǵ�/1";
		money=1200;
		break;
	case "��������":
		this.beAttackedX=x-30;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=14;
		this.hurt=450;
		this.skillHurt=hurt;
		this.defense=150;
		this.hp=520;
		this.exp=360;
		thing="���鵤/1";
		money=1200;
		break;
	case "��������":
		this.beAttackedX=x-30;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=14;
		this.hurt=500;
		this.skillHurt=hurt;
		this.defense=200;
		this.hp=3500;
		this.exp=3500;
		skillNum=2;
		thing="����/2";
		money=2200;
		break;
	case "��ѧ�ȴ����":
		this.beAttackedX=x-10;
		this.beAttackedY=y-10;
		this.length=8;
		this.beAttackedLength=6;
		this.speed=12;
		this.hurt=280;
		this.skillHurt=hurt;
		this.defense=100;
		this.hp=300;
		this.exp=300;
		thing="���ǵ�/1";
		money=1300;
		break;
	case "��ѧ�ȵ���":
		this.beAttackedX=x-20;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=15;
		this.hurt=300;
		this.skillHurt=hurt;
		this.defense=100;
		this.hp=400;
		this.exp=350;
		thing="���鵤/1";
		money=1300;
		break;
	case "��ѧ�Ȼ���":
		this.beAttackedX=x-20;
		this.beAttackedY=y;
		this.length=3;
		this.beAttackedLength=6;
		this.speed=13;
		this.hurt=300;
		this.skillHurt=hurt;
		this.defense=140;
		this.hp=500;
		this.exp=400;
		thing="���ǵ�/1";
		money=1300;
		break;
	case "��ѧ������":
		this.beAttackedX=x-20;
		this.beAttackedY=y-10;
		this.length=8;
		this.beAttackedLength=6;
		this.speed=16;
		this.hurt=330;
		this.skillHurt=hurt;
		this.defense=160;
		this.hp=2800;
		this.exp=2800;
		skillNum=2;
		thing="���ۿ�/2";
		money=2300;
		break;
	case "��ѧ��Сboss":
		this.beAttackedX=x-20;
		this.beAttackedY=y-10;
		this.length=6;
		this.beAttackedLength=6;
		this.speed=16;
		this.hurt=480;
		this.skillHurt=hurt;
		this.defense=120;
		this.hp=2800;
		this.exp=2500;
		skillNum=2;
		thing="��Ȫ/2";
		money=2300;
		break;
	case "����ʿ":
		this.beAttackedX=x-60;
		this.beAttackedY=y;
		this.length=6;
		this.beAttackedLength=6;
		this.speed=ZhangXiaoFan.speed+6;
		this.hurt=9999;
		this.skillHurt=9999;
		this.defense=9999;
		this.hp=9999;
		this.exp=9999;
		skillNum=2;
		thing="�������յ�/2";
		money=9999;
		break;
	case "�����":
		this.beAttackedX=x-125;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=23;
		this.hurt=700;
		this.skillHurt=hurt;
		this.defense=300;
		this.hp=6000;
		this.exp=2000;
		skillNum=2;
		thing="̩��/2";
		money=5000;
		break;
	case "Ե������":
		this.beAttackedX=x-30;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=11;
		this.hurt=480;
		this.skillHurt=hurt;
		this.defense=140;
		this.hp=4500;
		this.exp=2500;
		skillNum=2;
		thing="�����޻�/2";
		money=3000;
		break;
	case "�������":
		this.beAttackedX=x-50;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=9;
		this.hurt=350;
		this.skillHurt=hurt;
		this.defense=100;
		this.hp=2500;
		this.exp=1500;
		skillNum=2;
		thing="��ֽ�/2";
		money=2000;
		break;
	case "���":
		this.beAttackedX=x-5;
		this.beAttackedY=y-5;
		this.length=8;
		this.beAttackedLength=6;
		this.speed=17;
		this.hurt=620;
		this.skillHurt=hurt;
		this.defense=200;
		this.hp=6000;
		this.exp=4500;
		skillNum=2;
		thing="ǧ���Ǻ�/2";
		money=5000;
		break;
	case "�������":	
		this.beAttackedX=x-20;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=25;
		this.hurt=800;
		this.skillHurt=hurt;
		this.defense=350;
		this.hp=8000;
		this.exp=4000;
		skillNum=2;
		thing="������/2";
		money=8000;
		break;
	case "��Ҷ":
		this.beAttackedX=x-20;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=13;
		this.hurt=500;
		this.skillHurt=hurt;
		this.defense=140;
		this.hp=3200;
		this.exp=3000;
		skillNum=2;
		thing="տ¬/2";
		money=4000;
		break;
	case "���������":
		this.beAttackedX=x-125;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=16;
		this.hurt=600;
		this.skillHurt=hurt;
		this.defense=180;
		this.hp=4500;
		this.exp=3500;
		skillNum=1;
		thing="��ѩ˿��/2";
		money=4000;
		break;
	case "����󻤷�":
		this.beAttackedX=x-125;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=18;
		this.hurt=600;
		this.skillHurt=hurt;
		this.defense=190;
		this.hp=4800;
		this.exp=4500;
		skillNum=2;
		thing="�Ͻ��/2";
		money=4000;
		break;
	case "���������":
		this.beAttackedX=x-125;
		this.beAttackedY=y;
		this.length=4;
		this.beAttackedLength=6;
		this.speed=19;
		this.hurt=620;
		this.skillHurt=hurt;
		this.defense=200;
		this.hp=5000;
		this.exp=5500;
		skillNum=2;
		thing="��������/2";
		money=4000;
		break;
	case "����ʿ����":
		this.beAttackedX=x-60;
		this.beAttackedY=y;
		this.length=6;
		this.beAttackedLength=6;
		this.speed=18;
		this.hurt=600;
		this.skillHurt=600;
		this.defense=190;
		this.hp=2000;
		this.exp=2000;
		skillNum=2;
		thing="��ũҩ��/1";
		money=3000;
		break;
	}
}

//����ͼƬ
public void getImage(String name,int length){
	for(int i=1;i<=length;i++){
		Image image=Reader.readImage("image/����/"+name+"/"+i+".png");
		Images.add(image);
	}
	
	headImage=Reader.readImage("image/����/"+name+"/Сͷ.png");
	selectedImage=Reader.readImage("image/����/"+name+"/ѡ��.png");
}

public void loadAnimation(String name,int beAttackedLength){
	//���뱻������
	beAttackedAnimation=new BeAttackedAnimation(name+"����", beAttackedLength, this.beAttackedX, this.beAttackedY, bp);
	
	//���빥������
	switch(name){
	case "����1":
		setSkill("����/����1����", 16, this.x-50, this.y-235,6, 1, 6, 10, 16, this.y-ZhangXiaoFan.showY, this.y-YuJie.showY,this.y-LuXueQi.showY-30);
		break;
	case "����2":
		setSkill("����/����2����", 16, this.x-50, this.y-200,6, 1, 6, 10, 16, this.y-ZhangXiaoFan.showY+20,this.y-YuJie.showY+30,this.y-LuXueQi.showY);
		break;
	case "��":
		setSkill("����/�󵶹���", 26, this.x-70, this.y-160, 9, 3, 9, 19, 26, this.y-ZhangXiaoFan.showY-20, this.y-YuJie.showY, this.y-LuXueQi.showY-30);
		break;
	case "���ָ���1":
		setSkill("����/���ָ���1����", 26, this.x-10, this.y-170,9, 3, 10, 18, 26, this.y-ZhangXiaoFan.showY+20, this.y-YuJie.showY+30, this.y-LuXueQi.showY+10);
		break;
	case "���ָ���2":
		setSkill("����/���ָ���2����", 26, this.x-10, this.y-170, 17, 1, 9, 20, 26, this.y-ZhangXiaoFan.showY+40, this.y-YuJie.showY+50, this.y-LuXueQi.showY);
		break;
	case "�轣��":
		setSkill("����/�轣�߹���", 25, this.x-10, this.y-170,6, 3, 6, 16, 25, this.y-ZhangXiaoFan.showY+30, this.y-YuJie.showY+30, this.y-LuXueQi.showY);
		break;
	case "��������":
		setSkill("����/�������ӹ���", 29, this.x-10, this.y-170, 7, 3, 8, 20, 29,  this.y-ZhangXiaoFan.showY+30, this.y-YuJie.showY+30, this.y-LuXueQi.showY);
		break;
	case "��������":
		setSkill("����/������������", 28, this.x-10, this.y-170,7, 3, 8, 17, 28, this.y-ZhangXiaoFan.showY, this.y-YuJie.showY+30, this.y-LuXueQi.showY);
		break;
	case "��������":
		setSkill("����/������������", 23, this.x-10, this.y-170,5, 3, 6, 14, 23, this.y-ZhangXiaoFan.showY+20,  this.y-YuJie.showY+20, this.y-LuXueQi.showY);
		break;
	case "��ѧ�ȴ����":
		setSkill("����/��ѧ�ȴ���ӹ���", 15, this.x-10, this.y-170,6, 1, 5, 11, 15, this.y-ZhangXiaoFan.showY-30, this.y-YuJie.showY, this.y-LuXueQi.showY-80);
		break;
	case "��ѧ�ȵ���":
		setSkill("����/��ѧ�ȵ��ӹ���", 11, this.x-20, this.y-150, 4, 1, 4, 8, 11, this.y-ZhangXiaoFan.showY+20, this.y-YuJie.showY+30, this.y-LuXueQi.showY-60);
		break;
	case "��ѧ�Ȼ���":
		setSkill("����/��ѧ�Ȼ�������", 9, this.x-20, this.y-150,5, 1, 4, 6, 9,  this.y-ZhangXiaoFan.showY+10, this.y-YuJie.showY+30, this.y-LuXueQi.showY-40);
		break;
	case "��ѧ������":
		setSkill("����/��ѧ����������", 18, this.x-20, this.y-150, 6, 3, 5, 15, 18, this.y-ZhangXiaoFan.showY, this.y-YuJie.showY+10, this.y-LuXueQi.showY-10);
		break;
	case "��ѧ��Сboss":
		setSkill("����/��ѧ��Сboss����", 14, this.x-20, this.y-150, 10, 1, 6, 11, 14, this.y-ZhangXiaoFan.showY, this.y-YuJie.showY, this.y-LuXueQi.showY-40);
		break;
	case "����ʿ":
		setSkill("����/����ʿ����", 10, this.x-20, this.y-130, 4, 1, 4, 7, 10, this.y-ZhangXiaoFan.showY, this.y-YuJie.showY+20, this.y-LuXueQi.showY);
		break;
	case "�����":
		setSkill("����/����᰹���", 11, this.x-50, this.y-170,5, 1, 4, 7, 11, this.y-ZhangXiaoFan.showY-40, this.y-YuJie.showY, this.y-LuXueQi.showY-50);
		break;
	case "Ե������":
		setSkill("����/Ե�����߹���", 9, this.x-20, this.y-130,5, 1, 4, 7, 9, this.y-ZhangXiaoFan.showY+20, this.y-YuJie.showY+40, this.y-LuXueQi.showY);
		break;
	case "�������":
		setSkill("����/������˹���", 11, this.x-20, this.y-120, 6, 1, 4, 8, 11,  this.y-ZhangXiaoFan.showY+20, this.y-YuJie.showY+40, this.y-LuXueQi.showY-30);
		break;
	case "���":
		setSkill("����/��䭹���", 17, this.x-20, this.y-120, 6, 1, 5, 14, 17, this.y-ZhangXiaoFan.showY, this.y-YuJie.showY, this.y-LuXueQi.showY);
		break;
	case "�������":
		setSkill("����/������䭹���", 11, this.x-50, this.y-140,6, 1, 4, 8, 11, this.y-ZhangXiaoFan.showY+30, this.y-YuJie.showY+40, this.y-LuXueQi.showY);
		break;
	case "��Ҷ":
		setSkill("����/��Ҷ����", 11, this.x-50, this.y-140,3, 2, 3, 8, 11, this.y-ZhangXiaoFan.showY, this.y-YuJie.showY,  this.y-LuXueQi.showY);
		break;
	case "���������":
		setSkill("����/��������ӹ���", 11, this.x-50, this.y-150,6, 1, 6, 10, 16, this.y-ZhangXiaoFan.showY+20,this.y-YuJie.showY+30,this.y-LuXueQi.showY);
		break;
	case "����󻤷�":
		setSkill("����/����󻤷�����", 12, this.x-50, this.y-150,6, 1, 6, 10, 16, this.y-ZhangXiaoFan.showY+20,this.y-YuJie.showY+30,this.y-LuXueQi.showY);
		break;
	case "���������":
		setSkill("����/�������������", 9, this.x-50, this.y-150,6, 1, 6, 10, 16, this.y-ZhangXiaoFan.showY+20,this.y-YuJie.showY+30,this.y-LuXueQi.showY);
		break;
	case "����ʿ����":
		setSkill("����/����ʿ������", 10, this.x-20, this.y-130, 4, 1, 4, 7, 10, this.y-ZhangXiaoFan.showY, this.y-YuJie.showY+20, this.y-LuXueQi.showY);
		break;
	}
	
}

//��������
public void doAction(){
	if(!isStop&&code<length){
		currentImage=Images.get(code);
	    code++;
     }else if(code==length){
     code=0;
  }
}

//��������
public void drawEnemy(Graphics g){
	if(isDraw){
		g.drawImage(currentImage, x, y, bp);
	}
}

//���ս����״̬
public void checkState(){
	switch(battleState.type){
	//�������ݶ�
	case 1:
		speed+=1;
		break;
	case 2:
		hurt+=40;
		break;
	case 3:
		skillHurt+=30;
		break;
	case 4:
		defense+=40;
		break;
		//�������ݶ�
	case 5:
		speed-=1;
		break;
	case 6:
		hurt-=40;
		break;
	case 7:
		skillHurt-=30;
		break;
	case 8:
		defense-=40;
		break;
		//���״̬
	case 10:
		hurt=0;
		skillHurt=0;
		break;
	}
}

//ִ��״̬
public void excuteState(){
	switch(battleState.type){
	//�ж�״̬
	case 9:
		bp.hurtValues.clear();
		int damage=(int)(hp*0.05);
		hp-=damage;
		HurtValue hurtValue=new HurtValue(bp);
		hurtValue.show(damage, 1, x, y);
		bp.hurtValues.add(hurtValue);
		for(HurtValue h:bp.hurtValues){
			h.start();
		}
		break;
	}
}

//��״̬�лָ�
public void returnFromState(){
	switch(battleState.type){
	case 1:
		speed-=1;
		break;
	case 2:
		hurt-=40;
		break;
	case 3:
		skillHurt-=30;
		break;
	case 4:
		defense-=40;
		break;	
	case 5:
		speed+=1;
		break;
	case 6:
		hurt+=40;
		break;
	case 7:
		skillHurt+=30;
		break;
	case 8:
		defense+=40;
		break;
	case 10:
		hurt=hurtMax;
		skillHurt=skillHurtMax;
		break;
	}
}

//����
public void attack(){
	isDraw=false;
	bp.skillAnimation.set(skillName, skillLength, skillX, skillY, beAttackedCode, beAttackedTimes, runCode, attackCode, withdrawCode, offsetTo1, offsetTo2, offsetTo3);
	bp.skillAnimation.isDraw=true;
	bp.skillAnimation.isStop=false;
}
}
