package battle;

import media.MusicReader;

//攻击发动器
public class LaunchAttack {
//战斗面板引用
BattlePanel bp;
//计时器
int code;

//构造方法
public LaunchAttack(BattlePanel bp){
	this.bp=bp;
}

//发动技能攻击
public void skillAttack(int mpUse,int reminderCode,int skillCode,Hero hero){
	if(hero.getMp()>=mpUse){
		bp.hurtValues.clear();
		hero.calDamage();
		bp.currentPattern=0;
		//指示器停止
		bp.instruct.end();
		//显示提示
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
//检验方法
public void check(){
	//检验张小凡的回合
	if(bp.currentRound==1&&bp.currentBeAttacked!=0){
		checkZhang();
	}
	//检验文敏的回合
	if(bp.currentRound==2&&bp.currentBeAttacked!=0){
		checkWen();
	}
	//检验陆雪琪的回合
	if(bp.currentRound==3&&bp.currentBeAttacked!=0){
		checkLu();
	}
	
	//检验小精灵的回合
	if(bp.currentRound==4&&bp.currentBeAttacked!=0){
		checkPet();
	}
	
	//检验怪物1的回合
	if(bp.currentRound==5&&bp.currentBeAttacked!=0){
		checkEnemy1();
	}
	//检验怪物2的回合
	if(bp.currentRound==6&&bp.currentBeAttacked!=0){
		checkEnemy2();
	}
	//检验怪物3的回合
	if(bp.currentRound==7&&bp.currentBeAttacked!=0){
		checkEnemy3();
	}
}

//检验张小凡
public void checkZhang(){
	
	//攻击
	if(bp.currentPattern==1){
		MusicReader.readmusic("张小凡攻击(2).wav");
		//计算伤害值
		bp.hurtValues.clear();
		bp.zxf.calDamage();
		//指示器停止
		bp.instruct.end();
		bp.zxf.attack();
		//为了使这一个判断只做一次
		bp.currentPattern=0;
	}
	
	//横剑摆渡
	if(bp.currentPattern==2){
		MusicReader.readmusic("张小凡攻击(2).wav");
		skillAttack(70, 0, 1, bp.zxf);
	}
	if(bp.currentPattern==3){
		MusicReader.readmusic("张小凡攻击(2).wav");
		skillAttack(120, 1, 2, bp.zxf);
	}
	if(bp.currentPattern==4){
		MusicReader.readmusic("张小凡攻击(2).wav");
		skillAttack(150, 2, 3, bp.zxf);
	}
	if(bp.currentPattern==5){
		MusicReader.readmusic("张小凡攻击(2).wav");
		skillAttack(160, 3, 4, bp.zxf);
	}
	if(bp.currentPattern==6){
		MusicReader.readmusic("张小凡攻击(2).wav");
		skillAttack(200, 4, 5, bp.zxf);
	}
	
	//秘术
	if(bp.currentPattern==7){
		MusicReader.readmusic("张小凡攻击(2).wav");
		//全体获得金钟罩状态加成2回合
		for(Hero hero:bp.heroes){
			if(!hero.wheatherDead()){
				hero.getBattleState().set(2, 11, 100, hero.getRoleCode(), hero.getShowX(), hero.getShowY());
				hero.checkState();
			}
		}
		
		//开启动画
		bp.zxf.isDraw=false;
		bp.skillAnimation.set("张小凡秘术", 18, 560, 190, 0, 0, 0, 0, 0, 0, 0, 0);
		bp.skillAnimation.isDraw=true;
		bp.skillAnimation.isStop=false;
		//指示器停止
		bp.instruct.end();
		//处理愤怒值
		ZhangXiaoFan.angryValue=0;
		bp.zxf.isAngry=false;
		//使判断只做一次
		bp.currentPattern=0;
	}

	//技能发动结束后的恢复
	if(bp.skillAnimation.isOver==true){
		//为了使这个判断只做一次
		bp.skillAnimation.isOver=false;
		//人物重新出现
		bp.zxf.isDraw=true;
		
		if(bp.backgroundAnimation==null||bp.backgroundAnimation.isDraw==false){
    	//显示伤害值
		for(HurtValue hurtValue:bp.hurtValues){
			hurtValue.start();
		}
		bp.check.checkEnemyDead();
	
		//进度条继续
	    bp.progressBar.ZhangX=bp.progressBar.BarX;
	    //恢复
	    resume();
		}
		}
	if(bp.backgroundAnimation.isOver==true){
		    bp.backgroundAnimation.isOver=false;
		    
		    //显示伤害值
			for(HurtValue hurtValue:bp.hurtValues){
				hurtValue.start();
			}
			bp.check.checkEnemyDead();
			//进度条继续
		    bp.progressBar.ZhangX=bp.progressBar.BarX;
		    //恢复
		    resume();
	}
}

//检验文敏
public void checkWen(){
	//攻击
	if(bp.currentPattern==1){
		MusicReader.readmusic("文敏攻击(2).wav");
		bp.hurtValues.clear();
		bp.yj.calDamage();
		bp.currentPattern=0;
		//指示器停止
		bp.instruct.end();
		bp.yj.attack();
	}
	
	//技能1 伏虎冲天
	if(bp.currentPattern==2){
		MusicReader.readmusic("文敏攻击(2).wav");
		MusicReader.readmusic("伏虎冲天.wav");
		skillAttack(80, 5, 1,bp.yj);
	}
	
	//技能2 追星破月
		if(bp.currentPattern==3){
		MusicReader.readmusic("文敏攻击(2).wav");
		skillAttack(120,6,2,bp.yj);
	}
		
		//技能3 苍龙盖天
		if(bp.currentPattern==4){
			MusicReader.readmusic("文敏攻击(2).wav");
			skillAttack(150, 7, 3, bp.yj);
		}
		
		//技能5 妙手回春
		if(bp.currentPattern==5){
			MusicReader.readmusic("文敏攻击(2).wav");
			skillAttack(120,8,4,bp.yj);
		}
		
		//技能6 蝶影神灵
		if(bp.currentPattern==6){
			MusicReader.readmusic("文敏攻击(2).wav");
			skillAttack(200, 9, 5, bp.yj);
		}
		
		//秘术
		if(bp.currentPattern==7){
			MusicReader.readmusic("文敏攻击(2).wav");
			//自身潜能爆发三个回合
			bp.yj.battleState.set(3, 12, 100, 2, YuJie.showX, YuJie.showY);
			bp.yj.checkState();
			//播放动画
			bp.yj.isDraw=false;
			bp.skillAnimation.set("文敏秘术", 10, 650, 100, 0, 0, 0, 0, 0, 0, 0, 0);
			bp.skillAnimation.isDraw=true;
			bp.skillAnimation.isStop=false;
			//指示器停止
			bp.instruct.end();
			//处理愤怒值
			YuJie.angryValue=0;
			bp.yj.isAngry=false;
			//使判断只做一次
			bp.currentPattern=0;;
		}
	
	    //技能发动结束后的恢复
		if(bp.skillAnimation.isOver==true){
			//为了使这个判断只做一次
			bp.skillAnimation.isOver=false;
			//人物重新出现
			bp.yj.isDraw=true;
			
			if(bp.backgroundAnimation==null||bp.backgroundAnimation.isDraw==false){
     	   //显示伤害值
			for(HurtValue hurtValue:bp.hurtValues){
				hurtValue.start();
			}
			bp.check.checkEnemyDead();
			
			//进度条继续
		    bp.progressBar.YuX=bp.progressBar.BarX;
		    //恢复
		    resume();
		     }		
			}
		if(bp.backgroundAnimation.isOver==true){
		    bp.backgroundAnimation.isOver=false;	
     	   //显示伤害值
		    for(HurtValue hurtValue:bp.hurtValues){
				hurtValue.start();
			}
			bp.check.checkEnemyDead();
			
			//进度条继续
		    bp.progressBar.YuX=bp.progressBar.BarX;
		    //恢复
		    resume();
	}	
}
//检验陆雪琪
public void checkLu(){
	//攻击
	if(bp.currentPattern==1){
		MusicReader.readmusic("陆雪琪攻击(2).wav");
		//计算伤害值
		bp.hurtValues.clear();
		bp.lxq.calDamage();
		//指示器停止
		bp.instruct.end();
		bp.lxq.attack();
		//为了使这一个判断只做一次
		bp.currentPattern=0;
	}
	
	if(bp.currentPattern==2){
		MusicReader.readmusic("陆雪琪攻击(2).wav");
		skillAttack(80, 10, 1, bp.lxq);
	}
	
	if(bp.currentPattern==3){
		MusicReader.readmusic("陆雪琪攻击(2).wav");
		skillAttack((int)(LuXueQi.mpMax*0.6),11,2,bp.lxq);
	}
	if(bp.currentPattern==4){
		MusicReader.readmusic("陆雪琪攻击(2).wav");
		skillAttack(150, 12, 3, bp.lxq);
	}
	if(bp.currentPattern==5){
		MusicReader.readmusic("陆雪琪攻击(2).wav");
		skillAttack(160, 13, 4, bp.lxq);
	}
	if(bp.currentPattern==6){
		MusicReader.readmusic("陆雪琪攻击(2).wav");
		skillAttack(200, 14, 5, bp.lxq);
	}
	
	//秘术
	if(bp.currentPattern==7){
		MusicReader.readmusic("陆雪琪攻击(2).wav");
		//召唤小精灵
		bp.pet=new Pet(bp);
		
		//开启动画
		bp.lxq.isDraw=false;
		bp.skillAnimation.set("陆雪琪秘术", 8, 620, 300, 0, 0, 0, 0, 0, 0, 0, 0);
		bp.skillAnimation.isDraw=true;
		bp.skillAnimation.isStop=false;
		//指示器停止
		bp.instruct.end();
		//处理愤怒值
		LuXueQi.angryValue=0;
		bp.lxq.isAngry=false;
		//使判断只执行一次
		bp.currentPattern=0;
	}
	
	 //技能发动结束后的恢复
	if(bp.skillAnimation.isOver==true){
		//为了使这个判断只做一次
		bp.skillAnimation.isOver=false;
		//人物重新出现
		bp.lxq.isDraw=true;
		
		if(bp.backgroundAnimation==null||bp.backgroundAnimation.isDraw==false){
 	   //显示伤害值
		for(HurtValue hurtValue:bp.hurtValues){
			hurtValue.start();
		}
		bp.check.checkEnemyDead();
		
		//进度条继续
	    bp.progressBar.LuX=bp.progressBar.BarX;
	    //恢复
	    resume();
	     }		
		}
	if(bp.backgroundAnimation.isOver==true){
	    bp.backgroundAnimation.isOver=false;	
 	   //显示伤害值
	    for(HurtValue hurtValue:bp.hurtValues){
			hurtValue.start();
		}
		bp.check.checkEnemyDead();
		
		//进度条继续
	    bp.progressBar.LuX=bp.progressBar.BarX;
	    //恢复
	    resume();
}	
}

//检验怪物1
public void checkEnemy1(){
	//攻击
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
	 //技能发动结束后的恢复
	if(bp.skillAnimation.isOver==true){
			//为了使这个判断只做一次
			bp.skillAnimation.isOver=false;
			
			//显示伤害值
			for(HurtValue hurtValue:bp.hurtValues){
				hurtValue.start();
			}
		    bp.check.checkHeroDead();
			//人物重新出现
			bp.em1.isDraw=true;
			//进度条继续
		    bp.progressBar.Enemy1X=bp.progressBar.BarX;
		    //恢复
		    resume();
		}
}

//检验怪物2
public void checkEnemy2(){
	//攻击
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
	    //技能发动结束后的恢复
		if(bp.skillAnimation.isOver==true){
				//为了使这个判断只做一次
				bp.skillAnimation.isOver=false;
				
            	//显示伤害值
				for(HurtValue hurtValue:bp.hurtValues){
					hurtValue.start();
				}
				bp.check.checkHeroDead();
				//人物重新出现
				bp.em2.isDraw=true;
				//进度条继续
			    bp.progressBar.Enemy2X=bp.progressBar.BarX;
			    //恢复
			    resume();
			}
}

//检验怪物3
public void checkEnemy3(){
	//攻击
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
	
	        //技能发动结束后的恢复
			if(bp.skillAnimation.isOver==true){
					//为了使这个判断只做一次
					bp.skillAnimation.isOver=false;
					
	            	//显示伤害值
					for(HurtValue hurtValue:bp.hurtValues){
						hurtValue.start();
					}
					bp.check.checkHeroDead();
					//人物重新出现
					bp.em3.isDraw=true;
					//进度条继续
				    bp.progressBar.Enemy3X=bp.progressBar.BarX;
				    //恢复
				    resume();
				}
}

//检查小精灵
public void checkPet(){
	//攻击
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
	
	 //技能发动结束后的恢复
	if(bp.skillAnimation.isOver==true){
			//为了使这个判断只做一次
			bp.skillAnimation.isOver=false;
			
        	//显示伤害值
			for(HurtValue hurtValue:bp.hurtValues){
				hurtValue.start();
			}
			bp.check.checkEnemyDead();
			//人物重新出现
			bp.pet.isDraw=true;
			//进度条继续
		    bp.progressBar.petX=bp.progressBar.BarX;
		    //恢复
		    resume();
		}
}

//恢复
public void resume(){
	bp.currentRound=0;
	bp.currentBeAttacked=0;
	bp.currentPattern=0;
	bp.progressBar.isStop=false;
}

}
