package battle;
import java.awt.*;
import java.util.*;

import tools.*;

public class YuJie implements Hero{
	//��ɫ���
	int roleCode=2;
	//��ɫ��ǰ��ͼƬ����
    Image currentImage ;
    //��ɫ��ͼƬ��
    ArrayList<Image> Images=new ArrayList<Image>();
    //��ɫ��Сͷ��
    Image headImage;
    //�Ƿ񱻻���
    boolean isDraw;
    //�������Ƿ�ֹͣ
    boolean isStop;
    //�Ƿ�����
    boolean isDead;
  	//��������
    BeAttackedAnimation beAttackedAnimation;
  	//ʤ������
    VictoryAnimation victoryAnimation;
    //��������
    DeadAnimation deadAnimation;
    
    //λ������
    int x;
    int y;
    //�˺�ֵ��ʾλ��
    public static int showX;
    public static int showY;
    //ս�����
    BattlePanel bp;
    //���
    int code=0;
    //��ǰ��������
    public static int skillNumber=3;
    //ս��״̬
    BattleState battleState;
    
    //����ս������
    
    //hp��mp
    public static int hp;
    public static int mp;
    
    //ŭ��ֵ
    public static int angryValue=0;
    //�Ƿ��ŭ
    boolean isAngry=false;
    
    //hp��mp����
    public static int hpMax;
    public static int mpMax;
    
    //����
    public static int physicalPower=14;
    //����
    public static int sprit=12;
    //����
   public static int agile=17;
    //����
    public static int strength=18;
    
    //�ٶ�
    public static int speed;
    //������
    public static int hurt;
    //���ܹ�����
    public static int skillHurt;
    //������
    public static int defense;
    //���ܷ���
    public static int skillDefense;
    
    //��ǰ��ɵ��˺�ֵ
    int currentDamage;
    //��ǰ��ɵ��˺����� 1.�˺� 2.�ظ�
    int currentDamageType;
    //��ǰ�����Ķ��󼯺�
    ArrayList<Enemy> currentEnemies=new ArrayList<>();
    
    
    //�й������Ｖ�������
    
    //�ȼ�
    public static int level=3;
    //�������¼�����Ҫ�ľ���
    public static int expToLevelUp;
    //��ǰ����
    public static int exp=0;
    //�Ƿ�����
    boolean isLevelUp;
    //�Ƿ�ϰ�ü���
    boolean isGetSkill;
    
    ArrayList<String> roleInfo=new ArrayList<String>();
    
    
    //�������������ֵ
    public void refreshValue(){
    	hpMax=physicalPower*70;
    	mpMax=sprit*30;
    	hurt=strength*10;
    	skillHurt=sprit*8;
    	speed=(int)agile/2;
    	defense=physicalPower*5;
    	skillDefense=physicalPower*2+sprit*3;
    	
    	if(hp>=hpMax){
    		hp=hpMax;
    	}
    	if(mp>=mpMax){
    		mp=mpMax;
    	}
    }
    
    //�����˺�
    public void calDamage(){
    	switch(bp.currentBeAttacked){
    	case 5:
    		currentEnemies.clear();
    		currentEnemies.add(bp.em1);
    		break;
    	case 6:
    		currentEnemies.clear();
    		currentEnemies.add(bp.em2);
    		break;
    	case 7:
    		currentEnemies.clear();
    		currentEnemies.add(bp.em3);
    		break;
    	case 8:
    		currentEnemies.clear();
    		if(bp.em1!=null){
    		currentEnemies.add(bp.em1);
    		}
    		if(bp.em2!=null){
    		currentEnemies.add(bp.em2);
    		}
    		if(bp.em3!=null){
    		currentEnemies.add(bp.em3);
    		}
    		break;
    	}
    switch(bp.currentPattern){
    //��ͨ����
    case 1:
    	for(Enemy currentEnemy:currentEnemies){
    	currentDamage=hurt-currentEnemy.defense+(int)(Math.random()*15);
    	currentDamageType=1;
    	if(currentDamage<0){
    		currentDamage=0;
    	}
    	currentEnemy.hp-=currentDamage;
    	HurtValue hurtValue=new HurtValue(bp);
    	hurtValue.show(currentDamage, currentDamageType,currentEnemy.x,currentEnemy.y);
    	bp.hurtValues.add(hurtValue);
    	}
    	break;
    //����1 ��������
    case 2:
    	attackSkill(135, 15, 80);
    	//ʹ����������80���ʽ����ж�״̬
    	for(Enemy e:currentEnemies){
    		e.battleState.set(2, 9, 80, e.roleCode, e.x, e.y);
    		e.checkState();
    	}
    	break;
    //����2 ׷������
    case 3:
    	attackSkill(250, 15, 120);
    	//������������
    	battleState.set(2, 1, 100, 2, showX, showY);
    	bp.yj.checkState();
    	break;
    //����3 ��������
    case 4:
    	attackSkill(100, 15, 150);
    	break;
    //����4 ���ֻش�
    case 5:
    	for(Hero hero:bp.heroes){
    		currentDamage=(int)(hero.getHpMax()*0.4)+(int)(Math.random()*20);
    		currentDamageType=2;
    		hero.setHp(hero.getHp()+currentDamage);
    		if(hero.getHp()>=hero.getHpMax()){
    			hero.setHp(hero.getHpMax());
    		}
    		
    		//ʹ���������︴��
    		hero.setDead(false);
    		hero.getDeadAnimation().isDraw=false;
    		hero.getDeadAnimation().isStop=true;
    		hero.setIsDraw(true);
    		
    		
    		HurtValue hurtValue=new HurtValue(bp);
    		hurtValue.show(currentDamage, currentDamageType, hero.getShowX(), hero.getShowY());
    		bp.hurtValues.add(hurtValue);
    	}
    	mp-=120;
    	break;
    	//����5 ��Ӱ����
    case 6:
    	    attackSkill(600, 50, 200);
    	    //������������
    	    battleState.set(3, 2, 100, 2, showX, showY);
    	    bp.yj.checkState();
        	break;
    } 
   }
    
 //�����ͼ���
 public void attackSkill(int baseHurt,int offsetHurt,int mpUse){
	 for(Enemy currentEnemy:currentEnemies){
     	currentDamage=skillHurt-currentEnemy.defense+(int)(Math.random()*offsetHurt)+baseHurt;
     	currentDamageType=1;
    	if(currentDamage<0){
    		currentDamage=0;
    	}
     	currentEnemy.hp-=currentDamage;
     	HurtValue hurtValue=new HurtValue(bp);
     	hurtValue.show(currentDamage, currentDamageType,currentEnemy.x,currentEnemy.y);
     	bp.hurtValues.add(hurtValue);
     	}
     	mp-=mpUse;
 }
    
  //����ʱ���õķ���
    public void levelUp(){
    	isLevelUp=true;
    	//��������
    	level++;
    	//��������
    	physicalPower+=2;
    	sprit+=1;
    	agile+=2;
    	strength+=3;
    	
    	//����Щ����ʱ����µļ���
    	if(level==2||level==5||level==10){
    		skillNumber++;
    		isGetSkill=true;
    	}
    	
    	//�������
    	exp=exp-expToLevelUp;
    	//ˢ������ֵ
    	refreshValue();
    	
    	hp=hpMax;
    	mp=mpMax;
    	expToLevelUp=(int)(500*((Math.pow(1.4,level))));
    }
    
    //���췽��
    public YuJie(){
    	
    }
    public YuJie(int x,int y,BattlePanel bp){
    	this.x=x;
    	this.y=y;
    	YuJie.showX=x+50;
    	YuJie.showY=y;
    	this.bp=bp;
    	
    	getImage();
    	isDraw=true;
    	isStop=false;
        
    	
    	physicalPower=14+(level-3)*2;
    	sprit=12+(level-3)*1;
    	agile=17+(level-3)*2;
    	strength=18+(level-3)*3;
    	
    	loadAnimation();
    	refreshValue();
    	hp=hpMax;
    	mp=mpMax;
    	expToLevelUp=(int)(500*((Math.pow(1.4,level))));
    	battleState=new BattleState(bp);
    }
    
    //���ս����״̬
    public void checkState(){
    	switch(battleState.type){
    	//�������ݶ�
    	case 1:
    		agile+=2;
    		speed=(int)agile/2;
    		break;
    	case 2:
    		strength+=4;
    		hurt=strength*10;
    		break;
    	case 3:
    		sprit+=4;
    		skillHurt=sprit*8;
    		skillDefense=physicalPower*2+sprit*3;
    		break;
    	case 4:
    		physicalPower+=4;
    		defense=physicalPower*5;
        	skillDefense=physicalPower*2+sprit*3;
    		break;
    	case 5:
    		agile-=2;
    		speed=(int)agile/2;
    		break;
    	case 6:
    		strength-=4;
    		hurt=strength*10;
    		break;
    	case 7:
    		sprit-=4;
    		skillHurt=sprit*8;
    		skillDefense=physicalPower*2+sprit*3;
    		break;
    	case 8:
    		physicalPower-=4;
    		defense=physicalPower*5;
        	skillDefense=physicalPower*2+sprit*3;
    		break;
    	case 10:
    		hurt=0;
    		skillHurt=0;
    		break;
    		//������
    	case 11:
    		defense=9999;
    		skillDefense=9999;
    		break;
    		//Ǳ�ܱ���
    	case 12:
    		physicalPower*=2;
    		sprit*=2;
    		agile*=2;
    		strength*=2;
    		refreshValue();
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
    		hurtValue.show(damage, 1, showX, showY);
    		bp.hurtValues.add(hurtValue);
    		for(HurtValue h:bp.hurtValues){
    			h.start();
    		}
    	}
    }
    
    //��״̬�лָ�
    public void returnFromState(){
    	switch(battleState.type){
    	case 1:
    		agile-=2;
    		speed=(int)agile/2;
    		break;
    	case 2:
    		strength-=4;
    		hurt=strength*10;
    		break;
    	case 3:
    		sprit-=4;
    		skillHurt=sprit*8;
    		skillDefense=physicalPower*2+sprit*3;
    		break;
    	case 4:
    		physicalPower-=4;
    		defense=physicalPower*5;
        	skillDefense=physicalPower*2+sprit*3;
    		break;
    	case 5:
    		agile+=2;
    		speed=(int)agile/2;
    		break;
    	case 6:
    		strength+=4;
    		hurt=strength*10;
    		break;
    	case 7:
    		sprit+=4;
    		skillHurt=sprit*8;
    		skillDefense=physicalPower*2+sprit*3;
    		break;
    	case 8:
    		physicalPower+=4;
    		defense=physicalPower*5;
        	skillDefense=physicalPower*2+sprit*3;
    		break;
    	case 10:
    		hurt=strength*10;
        	skillHurt=sprit*8;
        	break;
        	//������
    	case 11:
    		defense=physicalPower*5;
        	skillDefense=physicalPower*2+sprit*3;	
    		break;
    		//Ǳ�ܱ���
    	case 12:
    		physicalPower/=2;
    		sprit/=2;
    		agile/=2;
    		strength/=2;
    		refreshValue();
    		break;
    	}
    }

    //���붯��
    public void loadAnimation(){
    	//��������
    	beAttackedAnimation=new BeAttackedAnimation("��������", 2, YuJie.showX, YuJie.showY, bp);
    	//ʤ������
    	victoryAnimation=new VictoryAnimation(120, -80, 12, "����", bp, this);
    	//��������
    	deadAnimation=new DeadAnimation(400, -70, 4, "����", bp, this);
    }
    
	@Override
	public void getImage() {
		// TODO Auto-generated method stub
		for(int i=1;i<=8;i++){
			Image image=Reader.readImage("image/����2/"+i+".png");
			Images.add(image);
			}
		
		headImage=Reader.readImage("image/����2/Сͷ.png");
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		if(!isStop&&code<8){
			currentImage=Images.get(code);
		    code++;
	
   }else if(code==8){
	code=0;
}
}

	@Override
	public void drawHero(Graphics g) {
		// TODO Auto-generated method stub
		if(isDraw){
			g.drawImage(currentImage, x, y, bp);
		}
	
	}


	@Override
	public void attack() {
	//���ﲻ�ٻ���
	isDraw=false;
	bp.skillAnimation.set("��������", 21, 120, -80, 8,1, 8, 15, 21,-150, 0, -260);
	bp.skillAnimation.isDraw=true;
	bp.skillAnimation.isStop=false;
	}
	
	//ʹ�ü���
	public void skill(int i){
		//���ﲻ�ٻ���
		isDraw=false;
		
		switch(i){
		case 1:
			bp.backgroundAnimation.set("��������", 74);
			bp.skillAnimation.set("��������1",28,120, -80, 8,6,9,22,28, -150, 0, -260);
			break;
		case 2:
			bp.backgroundAnimation.set("׷������", 41);
			bp.skillAnimation.set("��������2", 32, 120, -80,8,3,10, 26, 32, -150, 0, -260);
			break;
		case 3:
			bp.backgroundAnimation.set("��������", 51);
			bp.skillAnimation.set("��������3", 28, 120, -80, 8,4, 9, 23, 28, -150, 0, -260);
			break;
		case 4:
			bp.backgroundAnimation.set("���ֻش�", 23);
			bp.skillAnimation.set("��������4", 16, 120, -80, 0, 0, 0, 0, 0, 0, 0, 0);
			break;
		case 5:
			bp.backgroundAnimation.set("��Ӱ����", 41);
			bp.skillAnimation.set("��������5", 41, 120, -80, 8,4,9, 36, 41, -150, 0, -260);
			break;
		}
		
		bp.backgroundAnimation.isDraw=true;
		bp.backgroundAnimation.isStop=false;
		bp.skillAnimation.isDraw=true;
		bp.skillAnimation.isStop=false;
	}

	@Override
	public SkillAnimation getCurrentAnimation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDefense() {
		// TODO Auto-generated method stub
		return defense;
	}

	@Override
	public int getSkillDefense() {
		// TODO Auto-generated method stub
		return skillDefense;
	}

	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return hp;
	}

	@Override
	public void setHp(int hp) {
		// TODO Auto-generated method stub
		YuJie.hp=hp;
	}

	@Override
	public boolean wheatherDead() {
		// TODO Auto-generated method stub
		return isDead;
	}

	@Override
	public int getExp() {
		// TODO Auto-generated method stub
		return exp;
	}

	@Override
	public void setExp(int exp) {
		// TODO Auto-generated method stub
		YuJie.exp=exp;
	}

	@Override
	public int getExpToLevelUp() {
		// TODO Auto-generated method stub
		return expToLevelUp;
	}

	@Override
	public boolean wheatherLevelUp() {
		// TODO Auto-generated method stub
		return isLevelUp;
	}

	@Override
	public int getPhysicalPower() {
		// TODO Auto-generated method stub
		return physicalPower;
	}

	@Override
	public int getSprit() {
		// TODO Auto-generated method stub
		return sprit;
	}

	@Override
	public int getAgile() {
		// TODO Auto-generated method stub
		return agile;
	}

	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return strength;
	}

	@Override
	public void setLevelUp(boolean isLevelUp) {
		// TODO Auto-generated method stub
		this.isLevelUp=isLevelUp;
	}

	@Override
	public int getHpMax() {
		// TODO Auto-generated method stub
		return hpMax;
	}

	@Override
	public int getMpMax() {
		// TODO Auto-generated method stub
		return mpMax;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public int getShowX() {
		// TODO Auto-generated method stub
		return showX;
	}

	@Override
	public int getShowY() {
		// TODO Auto-generated method stub
		return showY;
	}

	@Override
	public int getMp() {
		// TODO Auto-generated method stub
		return mp;
	}

	@Override
	public void setMp(int mp) {
		// TODO Auto-generated method stub
		YuJie.mp=mp;
	}

	@Override
	public void setDead(boolean isDead) {
		// TODO Auto-generated method stub
		this.isDead=isDead;
	}

	@Override
	public BattleState getBattleState() {
		// TODO Auto-generated method stub
		return battleState;
	}

	@Override
	public int getRoleCode() {
		// TODO Auto-generated method stub
		return roleCode;
	}

	@Override
	public void setExpToLevelUp(int e) {
		// TODO Auto-generated method stub
		expToLevelUp=e;
	}

	@Override
	public void setIsDraw(boolean isDraw) {
		// TODO Auto-generated method stub
		this.isDraw=isDraw;
	}

	@Override
	public VictoryAnimation getVictoryAnimation() {
		// TODO Auto-generated method stub
		return victoryAnimation;
	}

	@Override
	public DeadAnimation getDeadAnimation() {
		// TODO Auto-generated method stub
		return deadAnimation;
	}

	@Override
	public int getAngryValue() {
		// TODO Auto-generated method stub
		return angryValue;
	}

	@Override
	public void setAngryValue(int a) {
		// TODO Auto-generated method stub
		angryValue=a;
	}

	@Override
	public boolean wheatherAngry() {
		// TODO Auto-generated method stub
		return isAngry;
	}

	@Override
	public void setAngry(boolean isAngry) {
		// TODO Auto-generated method stub
		this.isAngry=isAngry;
	}

	@Override
	public BeAttackedAnimation getBeAttackedAnimation() {
		// TODO Auto-generated method stub
		return beAttackedAnimation;
	}
	
	//�浵����
	public ArrayList<String>  saveRoleInfo(){
		roleInfo.clear();
		//���뵱ǰ�ĵȼ�
		roleInfo.add(level+"");
		//���뵱ǰ��hp
		roleInfo.add(hp+"");
		//���뵱ǰ��mp
		roleInfo.add(mp+"");
		//���뵱ǰŭ��ֵ
		roleInfo.add(angryValue+"");
		//���뵱ǰ�Ƿ�ﵽ��ŭ
		roleInfo.add(isAngry+"");
		//���뵱ǰ�Ƿ�����
		roleInfo.add(isDead+"");
		//���뵱ǰ����
		roleInfo.add(exp+"");
		
		return roleInfo;
	}
	
	//��������
	public void loadRoleInfo(ArrayList<String> roleInfo){
		this.roleInfo=roleInfo;
		intialFromInfo();
	}
	
	//������Ϣ��ʼ����������
	public void intialFromInfo(){
		YuJie.level=Integer.parseInt(roleInfo.get(0));
		if(level>=2){
			skillNumber=3;
		}
		if(level>=5){
			skillNumber=4;
		}
		if(level>=10){
			skillNumber=5;
		}
		physicalPower=14+(level-3)*2;
    	sprit=12+(level-3)*1;
    	agile=17+(level-3)*2;
    	strength=18+(level-3)*3;
    	refreshValue();
    	expToLevelUp=(int)(500*((Math.pow(1.4,level))));
    	YuJie.hp=Integer.parseInt(roleInfo.get(1));
    	YuJie.mp=Integer.parseInt(roleInfo.get(2));
    	YuJie.angryValue=Integer.parseInt(roleInfo.get(3));
    	this.isAngry=Boolean.parseBoolean(roleInfo.get(4));
    	this.isDead=Boolean.parseBoolean(roleInfo.get(5));
    	YuJie.exp=Integer.parseInt(roleInfo.get(6));
	}
	public void setPhysicalPower(int physicalPower) {
		// TODO Auto-generated method stub
		YuJie.physicalPower=physicalPower;
	}

	@Override
	public void setSprit(int sprit) {
		// TODO Auto-generated method stub
		YuJie.sprit=sprit;
	}

	@Override
	public void setAgile(int agile) {
		// TODO Auto-generated method stub
		YuJie.agile=agile;
	}

	@Override
	public void setStrength(int strength) {
		// TODO Auto-generated method stub
		YuJie.strength=strength;
	}
	
	
}
