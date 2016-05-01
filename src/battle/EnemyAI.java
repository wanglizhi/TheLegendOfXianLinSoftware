package battle;

//怪物智能类
public class EnemyAI {

    //战斗面板引用
	BattlePanel bp;
	
	//构造方法
	public EnemyAI(BattlePanel bp){
		this.bp=bp;
	}
	//选择攻击招数
	public void skillToUse(Enemy e){
		bp.currentPattern=(int)(Math.random()*e.skillNum)+1;
		if(bp.currentPattern==2){
			bp.currentBeAttacked=4;
		}
	}
	
	
	//选择攻击对象
	public void heroToAttack(){
		int i=0;
		while(true){
			//生成随机数
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
