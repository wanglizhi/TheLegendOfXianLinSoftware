package battle;

import java.awt.Graphics;

//我方战斗单位接口
public interface Hero {
//得到被击动画
public BeAttackedAnimation getBeAttackedAnimation();
//是否愤怒
public boolean wheatherAngry();
//设置是否愤怒
public void setAngry(boolean isAngry);
//得到怒气值
public int getAngryValue();
//设置怒气值
public void setAngryValue(int a);
//得到胜利动画
public VictoryAnimation getVictoryAnimation();
//得到死亡动画
public DeadAnimation getDeadAnimation();
//更改英雄是否绘出
public void setIsDraw(boolean isDraw);
//通过指定编号和长度来读入图片
public void  getImage();
//返回编号
public int getRoleCode();
    //自身动画
public void doAction();
    //画出自己
public void drawHero(Graphics g);
    //攻击
public void attack();
    //得到当前的技能动画
public SkillAnimation getCurrentAnimation();
    //得到物理防御
public int getDefense();
    //得到魔法防御
public int getSkillDefense();
   //得到hp
public int getHp();
  //设置hp
public void setHp(int hp);
//得到mp
public int getMp();
//设置mp
public void setMp(int mp);
//得到经验
public int getExp();
//设置经验
public void setExp(int exp);
//得到升级所需的经验
public int getExpToLevelUp();
//设置升级所需的经验
public void setExpToLevelUp(int e);
//是否升级
public boolean wheatherLevelUp();
//设置是否升级
public void setLevelUp(boolean isLevelUp);
//升级时调用的方法
public void levelUp();
//计算伤害值
public void calDamage();
//使用技能
public void skill(int skillCode);
//得到体力
public int getPhysicalPower();
//得到精气
public int getSprit();
//得到敏捷
public int getAgile();
//得到武力
public int getStrength();
//得到最大hp
public int getHpMax();
//得到最大mp
public int getMpMax();
//得到x;
public int getX();
//得到y
public int getY();
public int getShowX();
public int getShowY();
//得到战斗状态
public BattleState getBattleState();
//检查状态
public void checkState();
//清空状态
public void returnFromState();
 //是否死亡
public boolean wheatherDead();
//设置死亡
public void setDead(boolean isDead);


public void setPhysicalPower(int physicalPower) ;

public void setSprit(int sprit);

public void setAgile(int agile);

public void setStrength(int strength);

}
