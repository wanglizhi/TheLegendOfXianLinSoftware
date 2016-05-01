package battle;
import java.awt.*;
import java.util.*;

import tools.*;

public class LuXueQi implements Hero{
	//��ɫ���
	int roleCode=3;
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
    //�˺�ֵ��ʾ����
    public static int showX;
    public static int showY;
    //ս�����
    BattlePanel bp;
    //���
    int code=0;
    //��ǰ��������
    public static int skillNumber=2;
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
    public static int physicalPower=10;
    //����
    public static int sprit=12;
    //����
    public static int agile=14;
    //����
    public static int strength=8;
    
    //�ٶ�
    public static int speed;
    //������
    public static int hurt;
    //���ܹ�����
    public static int skillHurt;
    //������
   public  static int defense;
    //���ܷ���
   public  static int skillDefense;
    
    //��ǰ��ɵ��˺�ֵ
    int currentDamage;
    //��ǰ��ɵ��˺����� 1.�˺� 2.�ظ�
    int currentDamageType;
    //��ǰ�����Ķ��󼯺�
    ArrayList<Enemy> currentEnemies=new ArrayList<>();
    
    
    //�й������Ｖ�������
    
    //�ȼ�
    public static int level=1;
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
        	if(currentDamage<0){
        		currentDamage=0;
        	}
        	currentDamageType=1;
        	currentEnemy.hp-=currentDamage;
        	HurtValue hurtValue=new HurtValue(bp);
        	hurtValue.show(currentDamage, currentDamageType,currentEnemy.x,currentEnemy.y);
        	bp.hurtValues.add(hurtValue);
        	}
    	break;
    case 2:
            for(Hero hero:bp.heroes){
            	if(!hero.wheatherDead()){
            	hero.getBattleState().set(2, 1, 100, hero.getRoleCode(), hero.getShowX(), hero.getShowY());
            	hero.checkState();
            	}
            }
            mp-=80;
    	break;
    case 3:
    	if(bp.zxf!=null&&!bp.zxf.isDead){
    		bp.progressBar.ZhangX=bp.progressBar.BarX+400;
    	}
    	if(bp.yj!=null&&!bp.yj.isDead){
    		bp.progressBar.YuX=bp.progressBar.BarX+400;
    	}
    	if(bp.em1!=null){
    		bp.progressBar.Enemy1X=bp.progressBar.BarX;
    	}
    	if(bp.em2!=null){
    		bp.progressBar.Enemy2X=bp.progressBar.BarX;
    	}
    	if(bp.em3!=null){
    		bp.progressBar.Enemy3X=bp.progressBar.BarX;
    	}
    	for(Enemy enemy:bp.enemies){
    		enemy.battleState.set(1, 5, 70, enemy.roleCode, enemy.x, enemy.y);
    		enemy.checkState();
    	}
    	mp-=(int)(mpMax*0.6);
    	break;
    case 4:
    	attackSkill(80, 30, 150);
    	for(Enemy enemy:bp.enemies){
    		enemy.battleState.set(2, 9, 80, enemy.roleCode, enemy.x, enemy.y);
    		enemy.checkState();
    	}
    	break;
    case 5:
    	attackSkill(200,30,160);
    	for(Enemy enemy:currentEnemies){
    		enemy.battleState.set(2, 10, 40, enemy.roleCode, enemy.x, enemy.y);
    		enemy.checkState();
    	}
    	break;
    case 6:
    	attackSkill(180, 30, 200);
    	for(Enemy enemy:bp.enemies){
    		int type=(int)(Math.random()*6)+5;
    		enemy.battleState.set(2, type, 100, enemy.roleCode, enemy.x, enemy.y);
    		enemy.checkState();
    	}
    	for(Hero hero:bp.heroes){
    		if(!hero.wheatherDead()){
    		int type=(int)(Math.random()*4)+1;
    		hero.getBattleState().set(2, type, 100, hero.getRoleCode(), hero.getShowX(), hero.getShowY());
    		hero.checkState();
    		}
    	}
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
    	physicalPower+=1;
    	sprit+=3;
    	agile+=3;
    	strength+=1;
    	
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
    public LuXueQi(){
    	
    }
    public LuXueQi(int x,int y,BattlePanel bp){
    	this.x=x;
    	this.y=y;
    	LuXueQi.showX=x;
    	LuXueQi.showY=y;
    	this.bp=bp;
    	
    	getImage();
    	isDraw=true;
    	isStop=false;
    	
    	battleState=new BattleState(bp);
    	
    	
    	physicalPower=10+(level-1)*1;
    	sprit=12+(level-1)*3;
    	agile=14+(level-1)*3;
    	strength=8+(level-1)*1;
    	
    	loadAnimation();
    	refreshValue();
    	hp=hpMax;
    	mp=mpMax;
    	expToLevelUp=(int)(500*((Math.pow(1.4,level))));
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
    	}
    }

    
    public void loadAnimation(){
        //��������
        beAttackedAnimation=new BeAttackedAnimation("½ѩ������", 2, LuXueQi.showX, LuXueQi.showY, bp);
    	//ʤ������
    	victoryAnimation=new VictoryAnimation(120, 135, 8, "½ѩ��", bp, this);
    	//��������
    	deadAnimation=new DeadAnimation(720, 360, 4, "½ѩ��", bp, this);
    }
    
	@Override
	public void getImage() {
		// TODO Auto-generated method stub
		for(int i=1;i<=6;i++){
			Image image=Reader.readImage("image/����3/"+i+".png");
			Images.add(image);
			}
		
		headImage=Reader.readImage("image/����3/Сͷ.png");
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		if(!isStop&&code<6){
			currentImage=Images.get(code);
		    code++;
	
   }else if(code==6){
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
		// TODO Auto-generated method stub
		//���ﲻ�ٻ���
		isDraw=false;
		bp.skillAnimation.set("½ѩ������", 24, 120, 135, 8, 1, 7, 13, 24, 90, 210, -20);
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
		LuXueQi.hp=hp;
	}

	@Override
	public int getExp() {
		// TODO Auto-generated method stub
		return exp;
	}

	@Override
	public void setExp(int exp) {
		// TODO Auto-generated method stub
		LuXueQi.exp=exp;
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
	public void setLevelUp(boolean isLevelUp) {
		// TODO Auto-generated method stub
		this.isLevelUp=isLevelUp;
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
	public boolean wheatherDead() {
		// TODO Auto-generated method stub
		return isDead;
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
	public void skill(int skillCode) {
		// TODO Auto-generated method stub
		//���ﲻ�ٻ���
				isDraw=false;
//				animation=animations.get(skillCode);
//				animation.isDraw=true;
//				animation.isStop=false;
				
				switch(skillCode){
				case 1:
					bp.backgroundAnimation.set("�������", 30);
					bp.skillAnimation.set("½ѩ������1", 17, 120, 135,0, 0, 0, 0, 0, 0, 0, 0);
					break;
				case 2:
					bp.backgroundAnimation.set("̤���޺�", 68);
					bp.skillAnimation.set("½ѩ������2", 23, 120, 135,0, 0, 0, 0, 0, 0, 0, 0);
					break;
				case 3:
					bp.backgroundAnimation.set("�ǻ�Ǭ��Ȧ", 38);
					bp.skillAnimation.set("½ѩ������3", 21, 120, 135,8, 1, 0, 0, 0, 0, 0, 0);
					break;
				case 4:
					bp.backgroundAnimation.set("ؽ�����", 30);
					bp.skillAnimation.set("½ѩ������4", 29, 120, 135,8, 2, 9, 18, 29, 90, 210, -20);
					break;
				case 5:
					bp.backgroundAnimation.set("����׷��", 90);
					bp.skillAnimation.set("½ѩ������5", 29, 120, 135,8, 11, 9, 22, 29, 90, 210, -20);
					break;
				}	
				bp.backgroundAnimation.isDraw=true;
				bp.backgroundAnimation.isStop=false;
				bp.skillAnimation.isDraw=true;
				bp.skillAnimation.isStop=false;
	}

	@Override
	public int getMp() {
		// TODO Auto-generated method stub
		return mp;
	}

	@Override
	public void setMp(int mp) {
		// TODO Auto-generated method stub
		LuXueQi.mp=mp;
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
		LuXueQi.level=Integer.parseInt(roleInfo.get(0));
		if(level>=2){
			skillNumber=3;
		}
		if(level>=5){
			skillNumber=4;
		}
		if(level>=10){
			skillNumber=5;
		}
		physicalPower=10+(level-1)*1;
    	sprit=12+(level-1)*3;
    	agile=14+(level-1)*3;
    	strength=8+(level-1)*1;
    	refreshValue();
    	expToLevelUp=(int)(500*((Math.pow(1.4,level))));
    	LuXueQi.hp=Integer.parseInt(roleInfo.get(1));
    	LuXueQi.mp=Integer.parseInt(roleInfo.get(2));
    	LuXueQi.angryValue=Integer.parseInt(roleInfo.get(3));
    	this.isAngry=Boolean.parseBoolean(roleInfo.get(4));
    	this.isDead=Boolean.parseBoolean(roleInfo.get(5));
    	LuXueQi.exp=Integer.parseInt(roleInfo.get(6));
	}

	@Override
	public void setPhysicalPower(int physicalPower) {
		// TODO Auto-generated method stub
		LuXueQi.physicalPower=physicalPower;
	}

	@Override
	public void setSprit(int sprit) {
		// TODO Auto-generated method stub
		LuXueQi.sprit=sprit;
	}

	@Override
	public void setAgile(int agile) {
		// TODO Auto-generated method stub
		LuXueQi.agile=agile;
	}

	@Override
	public void setStrength(int strength) {
		// TODO Auto-generated method stub
		LuXueQi.strength=strength;
	}
	
}
