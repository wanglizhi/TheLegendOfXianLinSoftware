package battle;

import java.awt.Graphics;

public class SongDaRen implements Hero {
    //hp和mp的上限
    public static int hpMax;
    public static int mpMax;
    
    //hp和mp
    public static int hp;
    public static int mp;
    
    //体力
   public  static int physicalPower=1;
    //精气
    public static int sprit=10;
    //敏捷
    public static int agile=10;
    //武力
    public static int strength=10;
    
    //速度
    public static int speed;
    //攻击力
    public static int hurt;
    //技能攻击力
    public  static int skillHurt;
    //防御力
    public  static int defense;
    //技能防御
    public  static int skillDefense;
	@Override
	public void getImage() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drawHero(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public SkillAnimation getCurrentAnimation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDefense() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getSkillDefense() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setHp(int hp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getExp() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setExp(int exp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getExpToLevelUp() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean wheatherLevelUp() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setLevelUp(boolean isLevelUp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void levelUp() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getPhysicalPower() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getSprit() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getAgile() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getHpMax() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getMpMax() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean wheatherDead() {
		// TODO Auto-generated method stub
		return false;
	}
	public SongDaRen(){
		
	}
	@Override
	public int getMp() {
		// TODO Auto-generated method stub
		return SongDaRen.mp;
	}
	@Override
	public void setMp(int mp) {
		// TODO Auto-generated method stub
		SongDaRen.mp=mp;
	}
	@Override
	public int getRoleCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void calDamage() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void skill(int skillCode) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getShowX() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getShowY() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public BattleState getBattleState() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void checkState() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void returnFromState() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDead(boolean isDead) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setExpToLevelUp(int e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setIsDraw(boolean isDraw) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public VictoryAnimation getVictoryAnimation() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DeadAnimation getDeadAnimation() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getAngryValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setAngryValue(int a) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean wheatherAngry() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setAngry(boolean isAngry) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public BeAttackedAnimation getBeAttackedAnimation() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setPhysicalPower(int physicalPower) {
		// TODO Auto-generated method stub
		SongDaRen.physicalPower=physicalPower;
	}

	@Override
	public void setSprit(int sprit) {
		// TODO Auto-generated method stub
		SongDaRen.sprit=sprit;
	}

	@Override
	public void setAgile(int agile) {
		// TODO Auto-generated method stub
		SongDaRen.agile=agile;
	}

	@Override
	public void setStrength(int strength) {
		// TODO Auto-generated method stub
		SongDaRen.strength=strength;
	}
}
