package battle;

//����������
public class EnemyAI {

    //ս���������
	BattlePanel bp;
	
	//���췽��
	public EnemyAI(BattlePanel bp){
		this.bp=bp;
	}
	//ѡ�񹥻�����
	public void skillToUse(Enemy e){
		bp.currentPattern=(int)(Math.random()*e.skillNum)+1;
		if(bp.currentPattern==2){
			bp.currentBeAttacked=4;
		}
	}
	
	
	//ѡ�񹥻�����
	public void heroToAttack(){
		int i=0;
		while(true){
			//���������
			i=(int)(Math.random()*3)+1;
			if(i==1&&bp.zxf!=null&&!bp.zxf.isDead){
				bp.currentBeAttacked=i;
				break;
			}
			if(i==2&&bp.yj!=null&&!bp.yj.isDead){
				bp.currentBeAttacked=i;
				break;
			}
		   if(i==3&&bp.lxq!=null&&!bp.lxq.isDead){
			   bp.currentBeAttacked=i;
			   break;
		   }
		}
	}
}
