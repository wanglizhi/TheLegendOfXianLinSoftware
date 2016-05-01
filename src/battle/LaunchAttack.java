package battle;

import media.MusicReader;

//����������
public class LaunchAttack {
//ս���������
BattlePanel bp;
//��ʱ��
int code;

//���췽��
public LaunchAttack(BattlePanel bp){
	this.bp=bp;
}

//�������ܹ���
public void skillAttack(int mpUse,int reminderCode,int skillCode,Hero hero){
	if(hero.getMp()>=mpUse){
		bp.hurtValues.clear();
		hero.calDamage();
		bp.currentPattern=0;
		//ָʾ��ֹͣ
		bp.instruct.end();
		//��ʾ��ʾ
		bp.reminder.show(reminderCode);
		hero.skill(skillCode);
		}
		else{
			bp.reminder.show(20);
			bp.command.isDraw=true;
			bp.currentPattern=0;
			bp.currentBeAttacked=0;
		}
}
//���鷽��
public void check(){
	//������С���Ļغ�
	if(bp.currentRound==1&&bp.currentBeAttacked!=0){
		checkZhang();
	}
	//���������Ļغ�
	if(bp.currentRound==2&&bp.currentBeAttacked!=0){
		checkWen();
	}
	//����½ѩ���Ļغ�
	if(bp.currentRound==3&&bp.currentBeAttacked!=0){
		checkLu();
	}
	
	//����С����Ļغ�
	if(bp.currentRound==4&&bp.currentBeAttacked!=0){
		checkPet();
	}
	
	//�������1�Ļغ�
	if(bp.currentRound==5&&bp.currentBeAttacked!=0){
		checkEnemy1();
	}
	//�������2�Ļغ�
	if(bp.currentRound==6&&bp.currentBeAttacked!=0){
		checkEnemy2();
	}
	//�������3�Ļغ�
	if(bp.currentRound==7&&bp.currentBeAttacked!=0){
		checkEnemy3();
	}
}

//������С��
public void checkZhang(){
	
	//����
	if(bp.currentPattern==1){
		MusicReader.readmusic("��С������(2).wav");
		//�����˺�ֵ
		bp.hurtValues.clear();
		bp.zxf.calDamage();
		//ָʾ��ֹͣ
		bp.instruct.end();
		bp.zxf.attack();
		//Ϊ��ʹ��һ���ж�ֻ��һ��
		bp.currentPattern=0;
	}
	
	//�ὣ�ڶ�
	if(bp.currentPattern==2){
		MusicReader.readmusic("��С������(2).wav");
		skillAttack(70, 0, 1, bp.zxf);
	}
	if(bp.currentPattern==3){
		MusicReader.readmusic("��С������(2).wav");
		skillAttack(120, 1, 2, bp.zxf);
	}
	if(bp.currentPattern==4){
		MusicReader.readmusic("��С������(2).wav");
		skillAttack(150, 2, 3, bp.zxf);
	}
	if(bp.currentPattern==5){
		MusicReader.readmusic("��С������(2).wav");
		skillAttack(160, 3, 4, bp.zxf);
	}
	if(bp.currentPattern==6){
		MusicReader.readmusic("��С������(2).wav");
		skillAttack(200, 4, 5, bp.zxf);
	}
	
	//����
	if(bp.currentPattern==7){
		MusicReader.readmusic("��С������(2).wav");
		//ȫ���ý�����״̬�ӳ�2�غ�
		for(Hero hero:bp.heroes){
			if(!hero.wheatherDead()){
				hero.getBattleState().set(2, 11, 100, hero.getRoleCode(), hero.getShowX(), hero.getShowY());
				hero.checkState();
			}
		}
		
		//��������
		bp.zxf.isDraw=false;
		bp.skillAnimation.set("��С������", 18, 560, 190, 0, 0, 0, 0, 0, 0, 0, 0);
		bp.skillAnimation.isDraw=true;
		bp.skillAnimation.isStop=false;
		//ָʾ��ֹͣ
		bp.instruct.end();
		//�����ŭֵ
		ZhangXiaoFan.angryValue=0;
		bp.zxf.isAngry=false;
		//ʹ�ж�ֻ��һ��
		bp.currentPattern=0;
	}

	//���ܷ���������Ļָ�
	if(bp.skillAnimation.isOver==true){
		//Ϊ��ʹ����ж�ֻ��һ��
		bp.skillAnimation.isOver=false;
		//�������³���
		bp.zxf.isDraw=true;
		
		if(bp.backgroundAnimation==null||bp.backgroundAnimation.isDraw==false){
    	//��ʾ�˺�ֵ
		for(HurtValue hurtValue:bp.hurtValues){
			hurtValue.start();
		}
		bp.check.checkEnemyDead();
	
		//����������
	    bp.progressBar.ZhangX=bp.progressBar.BarX;
	    //�ָ�
	    resume();
		}
		}
	if(bp.backgroundAnimation.isOver==true){
		    bp.backgroundAnimation.isOver=false;
		    
		    //��ʾ�˺�ֵ
			for(HurtValue hurtValue:bp.hurtValues){
				hurtValue.start();
			}
			bp.check.checkEnemyDead();
			//����������
		    bp.progressBar.ZhangX=bp.progressBar.BarX;
		    //�ָ�
		    resume();
	}
}

//��������
public void checkWen(){
	//����
	if(bp.currentPattern==1){
		MusicReader.readmusic("��������(2).wav");
		bp.hurtValues.clear();
		bp.yj.calDamage();
		bp.currentPattern=0;
		//ָʾ��ֹͣ
		bp.instruct.end();
		bp.yj.attack();
	}
	
	//����1 ��������
	if(bp.currentPattern==2){
		MusicReader.readmusic("��������(2).wav");
		MusicReader.readmusic("��������.wav");
		skillAttack(80, 5, 1,bp.yj);
	}
	
	//����2 ׷������
		if(bp.currentPattern==3){
		MusicReader.readmusic("��������(2).wav");
		skillAttack(120,6,2,bp.yj);
	}
		
		//����3 ��������
		if(bp.currentPattern==4){
			MusicReader.readmusic("��������(2).wav");
			skillAttack(150, 7, 3, bp.yj);
		}
		
		//����5 ���ֻش�
		if(bp.currentPattern==5){
			MusicReader.readmusic("��������(2).wav");
			skillAttack(120,8,4,bp.yj);
		}
		
		//����6 ��Ӱ����
		if(bp.currentPattern==6){
			MusicReader.readmusic("��������(2).wav");
			skillAttack(200, 9, 5, bp.yj);
		}
		
		//����
		if(bp.currentPattern==7){
			MusicReader.readmusic("��������(2).wav");
			//����Ǳ�ܱ��������غ�
			bp.yj.battleState.set(3, 12, 100, 2, YuJie.showX, YuJie.showY);
			bp.yj.checkState();
			//���Ŷ���
			bp.yj.isDraw=false;
			bp.skillAnimation.set("��������", 10, 650, 100, 0, 0, 0, 0, 0, 0, 0, 0);
			bp.skillAnimation.isDraw=true;
			bp.skillAnimation.isStop=false;
			//ָʾ��ֹͣ
			bp.instruct.end();
			//�����ŭֵ
			YuJie.angryValue=0;
			bp.yj.isAngry=false;
			//ʹ�ж�ֻ��һ��
			bp.currentPattern=0;;
		}
	
	    //���ܷ���������Ļָ�
		if(bp.skillAnimation.isOver==true){
			//Ϊ��ʹ����ж�ֻ��һ��
			bp.skillAnimation.isOver=false;
			//�������³���
			bp.yj.isDraw=true;
			
			if(bp.backgroundAnimation==null||bp.backgroundAnimation.isDraw==false){
     	   //��ʾ�˺�ֵ
			for(HurtValue hurtValue:bp.hurtValues){
				hurtValue.start();
			}
			bp.check.checkEnemyDead();
			
			//����������
		    bp.progressBar.YuX=bp.progressBar.BarX;
		    //�ָ�
		    resume();
		     }		
			}
		if(bp.backgroundAnimation.isOver==true){
		    bp.backgroundAnimation.isOver=false;	
     	   //��ʾ�˺�ֵ
		    for(HurtValue hurtValue:bp.hurtValues){
				hurtValue.start();
			}
			bp.check.checkEnemyDead();
			
			//����������
		    bp.progressBar.YuX=bp.progressBar.BarX;
		    //�ָ�
		    resume();
	}	
}
//����½ѩ��
public void checkLu(){
	//����
	if(bp.currentPattern==1){
		MusicReader.readmusic("½ѩ������(2).wav");
		//�����˺�ֵ
		bp.hurtValues.clear();
		bp.lxq.calDamage();
		//ָʾ��ֹͣ
		bp.instruct.end();
		bp.lxq.attack();
		//Ϊ��ʹ��һ���ж�ֻ��һ��
		bp.currentPattern=0;
	}
	
	if(bp.currentPattern==2){
		MusicReader.readmusic("½ѩ������(2).wav");
		skillAttack(80, 10, 1, bp.lxq);
	}
	
	if(bp.currentPattern==3){
		MusicReader.readmusic("½ѩ������(2).wav");
		skillAttack((int)(LuXueQi.mpMax*0.6),11,2,bp.lxq);
	}
	if(bp.currentPattern==4){
		MusicReader.readmusic("½ѩ������(2).wav");
		skillAttack(150, 12, 3, bp.lxq);
	}
	if(bp.currentPattern==5){
		MusicReader.readmusic("½ѩ������(2).wav");
		skillAttack(160, 13, 4, bp.lxq);
	}
	if(bp.currentPattern==6){
		MusicReader.readmusic("½ѩ������(2).wav");
		skillAttack(200, 14, 5, bp.lxq);
	}
	
	//����
	if(bp.currentPattern==7){
		MusicReader.readmusic("½ѩ������(2).wav");
		//�ٻ�С����
		bp.pet=new Pet(bp);
		
		//��������
		bp.lxq.isDraw=false;
		bp.skillAnimation.set("½ѩ������", 8, 620, 300, 0, 0, 0, 0, 0, 0, 0, 0);
		bp.skillAnimation.isDraw=true;
		bp.skillAnimation.isStop=false;
		//ָʾ��ֹͣ
		bp.instruct.end();
		//�����ŭֵ
		LuXueQi.angryValue=0;
		bp.lxq.isAngry=false;
		//ʹ�ж�ִֻ��һ��
		bp.currentPattern=0;
	}
	
	 //���ܷ���������Ļָ�
	if(bp.skillAnimation.isOver==true){
		//Ϊ��ʹ����ж�ֻ��һ��
		bp.skillAnimation.isOver=false;
		//�������³���
		bp.lxq.isDraw=true;
		
		if(bp.backgroundAnimation==null||bp.backgroundAnimation.isDraw==false){
 	   //��ʾ�˺�ֵ
		for(HurtValue hurtValue:bp.hurtValues){
			hurtValue.start();
		}
		bp.check.checkEnemyDead();
		
		//����������
	    bp.progressBar.LuX=bp.progressBar.BarX;
	    //�ָ�
	    resume();
	     }		
		}
	if(bp.backgroundAnimation.isOver==true){
	    bp.backgroundAnimation.isOver=false;	
 	   //��ʾ�˺�ֵ
	    for(HurtValue hurtValue:bp.hurtValues){
			hurtValue.start();
		}
		bp.check.checkEnemyDead();
		
		//����������
	    bp.progressBar.LuX=bp.progressBar.BarX;
	    //�ָ�
	    resume();
}	
}

//�������1
public void checkEnemy1(){
	//����
	if(bp.currentPattern==1){
			if(code<5){
				code++;
			}else{
		bp.hurtValues.clear();
		bp.em1.calDamage();
		bp.currentPattern=0;
		bp.em1.attack();
		code=0;
		}
	}
	
	if(bp.currentPattern==2){
		if(code<5){
			code++;
		}else{
	bp.hurtValues.clear();
	bp.em1.calDamage();
	bp.currentPattern=0;
	bp.em1.attack();
	code=0;
	}
	}
	 //���ܷ���������Ļָ�
	if(bp.skillAnimation.isOver==true){
			//Ϊ��ʹ����ж�ֻ��һ��
			bp.skillAnimation.isOver=false;
			
			//��ʾ�˺�ֵ
			for(HurtValue hurtValue:bp.hurtValues){
				hurtValue.start();
			}
		    bp.check.checkHeroDead();
			//�������³���
			bp.em1.isDraw=true;
			//����������
		    bp.progressBar.Enemy1X=bp.progressBar.BarX;
		    //�ָ�
		    resume();
		}
}

//�������2
public void checkEnemy2(){
	//����
	if(bp.currentPattern==1){
		if(code<5){
			code++;
		}else{
		bp.hurtValues.clear();
		bp.em2.calDamage();
		bp.currentPattern=0;
		bp.em2.attack();
		code=0;
		}
	}
	
	if(bp.currentPattern==2){
		if(code<5){
			code++;
		}else{
		bp.hurtValues.clear();
		bp.em2.calDamage();
		bp.currentPattern=0;
		bp.em2.attack();
		code=0;
		}
	}
	    //���ܷ���������Ļָ�
		if(bp.skillAnimation.isOver==true){
				//Ϊ��ʹ����ж�ֻ��һ��
				bp.skillAnimation.isOver=false;
				
            	//��ʾ�˺�ֵ
				for(HurtValue hurtValue:bp.hurtValues){
					hurtValue.start();
				}
				bp.check.checkHeroDead();
				//�������³���
				bp.em2.isDraw=true;
				//����������
			    bp.progressBar.Enemy2X=bp.progressBar.BarX;
			    //�ָ�
			    resume();
			}
}

//�������3
public void checkEnemy3(){
	//����
	if(bp.currentPattern==1){
		if(code<5){
			code++;
		}else{
		bp.hurtValues.clear();
		bp.em3.calDamage();
		bp.currentPattern=0;
		bp.em3.attack();
		code=0;
		}
	}
	
	if(bp.currentPattern==2){
		if(code<5){
			code++;
		}else{
		bp.hurtValues.clear();
		bp.em3.calDamage();
		bp.currentPattern=0;
		bp.em3.attack();
		code=0;
		}
	}
	
	        //���ܷ���������Ļָ�
			if(bp.skillAnimation.isOver==true){
					//Ϊ��ʹ����ж�ֻ��һ��
					bp.skillAnimation.isOver=false;
					
	            	//��ʾ�˺�ֵ
					for(HurtValue hurtValue:bp.hurtValues){
						hurtValue.start();
					}
					bp.check.checkHeroDead();
					//�������³���
					bp.em3.isDraw=true;
					//����������
				    bp.progressBar.Enemy3X=bp.progressBar.BarX;
				    //�ָ�
				    resume();
				}
}

//���С����
public void checkPet(){
	//����
	if(bp.currentPattern==1){
		if(code<5){
			code++;
		}else{
		bp.hurtValues.clear();
		bp.pet.calDamage();
		bp.currentPattern=0;
		bp.pet.attack();
		code=0;
		}
	}
	
	 //���ܷ���������Ļָ�
	if(bp.skillAnimation.isOver==true){
			//Ϊ��ʹ����ж�ֻ��һ��
			bp.skillAnimation.isOver=false;
			
        	//��ʾ�˺�ֵ
			for(HurtValue hurtValue:bp.hurtValues){
				hurtValue.start();
			}
			bp.check.checkEnemyDead();
			//�������³���
			bp.pet.isDraw=true;
			//����������
		    bp.progressBar.petX=bp.progressBar.BarX;
		    //�ָ�
		    resume();
		}
}

//�ָ�
public void resume(){
	bp.currentRound=0;
	bp.currentBeAttacked=0;
	bp.currentPattern=0;
	bp.progressBar.isStop=false;
}

}
