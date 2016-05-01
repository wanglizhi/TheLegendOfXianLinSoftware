package battle;

import media.MusicReader;

//检查类
public class Check {

	//战斗面板引用
	BattlePanel bp;
	
	//构造方法
	public Check(BattlePanel bp){
		this.bp=bp;
	}
	
	//检查怪物是否死亡 注意要在所有的技能动画发动结束以后
	public void checkEnemyDead(){
		
		if(bp.em1!=null&&bp.em1.hp<=0){
		bp.enemies.remove(bp.enemies.indexOf(bp.em1));
		bp.em1=null;
		bp.progressBar.Enemy1X=0;
		}
		
		if(bp.em2!=null&&bp.em2.hp<=0){
		bp.enemies.remove(bp.enemies.indexOf(bp.em2));
		bp.em2=null;
		bp.progressBar.Enemy2X=0;
		}
		
		if(bp.em3!=null&&bp.em3.hp<=0){
		bp.enemies.remove(bp.enemies.indexOf(bp.em3));
		bp.em3=null;
		bp.progressBar.Enemy3X=0;
		}
		
		//如果全部怪物被杀死
		if(bp.em1==null&&bp.em2==null&&bp.em3==null){
			
			//我方获得所有经验
			for(Hero hero:bp.heroes){
				hero.setExp(hero.getExp()+bp.victoryReminder.expToGet);
			}
			//我方所有状态清空
			for(Hero hero:bp.heroes){
				if(hero.getBattleState().isUsable){
				hero.returnFromState();
				hero.getBattleState().clear();
				}
			}
			//检查是否升级
			for(Hero hero:bp.heroes){
				if(hero.getExp()>=hero.getExpToLevelUp()){
					hero.levelUp();
				}
			}
			//播放胜利动画
			for(Hero hero:bp.heroes){
				MusicReader.readmusic("战斗胜利.MP3");
				hero.getVictoryAnimation().start();
			}
			
			//进度条停止
			bp.progressBar.isDraw=false;
			
			bp.victoryReminder.isDraw=true;
			bp.victoryReminder.isStop=false;
		}
	}
	
	//检查我方英雄是否死亡,在怪物的攻击技能播放之后检查
	public void checkHeroDead(){
		for(Hero hero:bp.heroes){
			if(hero.getHp()<=0){
				hero.setDead(true);
				//清空状态
				if(hero.getBattleState().isUsable){
					hero.returnFromState();
					hero.getBattleState().clear();
					}
				//播放死亡动画
				hero.getDeadAnimation().start();	
			}
		}
		
		
		//检查我方是否全灭
		boolean isAllDead=true;
		for(Hero hero:bp.heroes){
			if(!hero.wheatherDead()){
				isAllDead=false;
				break;
			}
		}
		if(isAllDead){
		MusicReader.readmusic("战斗失败.wav");
		bp.progressBar.isDraw=false;
		bp.gameOver.isDraw=true;
		bp.gameOver.isStop=false;
		}
	}
}
