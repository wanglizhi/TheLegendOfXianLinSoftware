package battle;

import media.MusicReader;

//�����
public class Check {

	//ս���������
	BattlePanel bp;
	
	//���췽��
	public Check(BattlePanel bp){
		this.bp=bp;
	}
	
	//�������Ƿ����� ע��Ҫ�����еļ��ܶ������������Ժ�
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
		
		//���ȫ�����ﱻɱ��
		if(bp.em1==null&&bp.em2==null&&bp.em3==null){
			
			//�ҷ�������о���
			for(Hero hero:bp.heroes){
				hero.setExp(hero.getExp()+bp.victoryReminder.expToGet);
			}
			//�ҷ�����״̬���
			for(Hero hero:bp.heroes){
				if(hero.getBattleState().isUsable){
				hero.returnFromState();
				hero.getBattleState().clear();
				}
			}
			//����Ƿ�����
			for(Hero hero:bp.heroes){
				if(hero.getExp()>=hero.getExpToLevelUp()){
					hero.levelUp();
				}
			}
			//����ʤ������
			for(Hero hero:bp.heroes){
				MusicReader.readmusic("ս��ʤ��.MP3");
				hero.getVictoryAnimation().start();
			}
			
			//������ֹͣ
			bp.progressBar.isDraw=false;
			
			bp.victoryReminder.isDraw=true;
			bp.victoryReminder.isStop=false;
		}
	}
	
	//����ҷ�Ӣ���Ƿ�����,�ڹ���Ĺ������ܲ���֮����
	public void checkHeroDead(){
		for(Hero hero:bp.heroes){
			if(hero.getHp()<=0){
				hero.setDead(true);
				//���״̬
				if(hero.getBattleState().isUsable){
					hero.returnFromState();
					hero.getBattleState().clear();
					}
				//������������
				hero.getDeadAnimation().start();	
			}
		}
		
		
		//����ҷ��Ƿ�ȫ��
		boolean isAllDead=true;
		for(Hero hero:bp.heroes){
			if(!hero.wheatherDead()){
				isAllDead=false;
				break;
			}
		}
		if(isAllDead){
		MusicReader.readmusic("ս��ʧ��.wav");
		bp.progressBar.isDraw=false;
		bp.gameOver.isDraw=true;
		bp.gameOver.isStop=false;
		}
	}
}
