package media;

public class MusicReader {
	static MusicPlayer background =new MusicPlayer("sources/BGM");
	static MusicPlayer music= new MusicPlayer("sources/music");
	
	 public static void readBGM(String s){
		 background.play(s);
	 }
	 
	 public static void readmusic(String s){
		music.playmusic(s);
	 }
	 
	 //πÿ±’±≥æ∞“Ù¿÷
	 public static void closeBGM(){
		 MusicPlayer.CAN_PLAY_BGM=MusicPlayer.NO;
	 }
	 public static void openBGM(){
		 background.play(background.currentPlayingBGM);
		 MusicPlayer.CAN_PLAY_BGM=MusicPlayer.YES;
	 }
	 //πÿ±’“Ù–ß
	 public static void closeMusic(){
		 MusicPlayer.CAN_PLAY_MUSIC=MusicPlayer.NO;
	 }
	 public static void openMusic(){
		 MusicPlayer.CAN_PLAY_MUSIC=MusicPlayer.YES;
	 }
}
