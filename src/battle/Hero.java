package battle;

import java.awt.Graphics;

//�ҷ�ս����λ�ӿ�
public interface Hero {
//�õ���������
public BeAttackedAnimation getBeAttackedAnimation();
//�Ƿ��ŭ
public boolean wheatherAngry();
//�����Ƿ��ŭ
public void setAngry(boolean isAngry);
//�õ�ŭ��ֵ
public int getAngryValue();
//����ŭ��ֵ
public void setAngryValue(int a);
//�õ�ʤ������
public VictoryAnimation getVictoryAnimation();
//�õ���������
public DeadAnimation getDeadAnimation();
//����Ӣ���Ƿ���
public void setIsDraw(boolean isDraw);
//ͨ��ָ����źͳ���������ͼƬ
public void  getImage();
//���ر��
public int getRoleCode();
    //������
public void doAction();
    //�����Լ�
public void drawHero(Graphics g);
    //����
public void attack();
    //�õ���ǰ�ļ��ܶ���
public SkillAnimation getCurrentAnimation();
    //�õ��������
public int getDefense();
    //�õ�ħ������
public int getSkillDefense();
   //�õ�hp
public int getHp();
  //����hp
public void setHp(int hp);
//�õ�mp
public int getMp();
//����mp
public void setMp(int mp);
//�õ�����
public int getExp();
//���þ���
public void setExp(int exp);
//�õ���������ľ���
public int getExpToLevelUp();
//������������ľ���
public void setExpToLevelUp(int e);
//�Ƿ�����
public boolean wheatherLevelUp();
//�����Ƿ�����
public void setLevelUp(boolean isLevelUp);
//����ʱ���õķ���
public void levelUp();
//�����˺�ֵ
public void calDamage();
//ʹ�ü���
public void skill(int skillCode);
//�õ�����
public int getPhysicalPower();
//�õ�����
public int getSprit();
//�õ�����
public int getAgile();
//�õ�����
public int getStrength();
//�õ����hp
public int getHpMax();
//�õ����mp
public int getMpMax();
//�õ�x;
public int getX();
//�õ�y
public int getY();
public int getShowX();
public int getShowY();
//�õ�ս��״̬
public BattleState getBattleState();
//���״̬
public void checkState();
//���״̬
public void returnFromState();
 //�Ƿ�����
public boolean wheatherDead();
//��������
public void setDead(boolean isDead);


public void setPhysicalPower(int physicalPower) ;

public void setSprit(int sprit);

public void setAgile(int agile);

public void setStrength(int strength);

}
