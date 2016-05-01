package media;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class MusicPlayer {
	// BGM属性
	boolean isStop = true;// 控制播放线程
	boolean hasStop = true;// 播放线程状态

	String filepath;// 播放文件目录
	String filename;// 播放文件名称
	AudioInputStream audioInputStream;// 文件流
	AudioFormat audioFormat;// 文件格式
	SourceDataLine sourceDataLine;// 输出设备

	String currentPlayingBGM;
	String currentPlayingMusic;

	public static int CAN_PLAY_BGM = 1;
	public static int CAN_PLAY_MUSIC = 1;
	public static int YES = 1;
	public static int NO = 2;

	// 输入此场景所需的音乐来源
	public MusicPlayer(String path) {
		filepath = path + "/";
	}

	// 打开需要调用的音乐
	public void play(String name) {
			try {
				isStop = true;
				while (!hasStop) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
					}
				}
				currentPlayingBGM = name;

				// 获得即将播放的文件
				File file = new File(filepath + currentPlayingBGM);
				// 取得文件输入流
				audioInputStream = AudioSystem.getAudioInputStream(file);
				audioFormat = audioInputStream.getFormat();
				// 转换mp3文件编码
				if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
					audioFormat = new AudioFormat(
							AudioFormat.Encoding.PCM_SIGNED,
							audioFormat.getSampleRate(), 16,
							audioFormat.getChannels(),
							audioFormat.getChannels() * 2,
							audioFormat.getSampleRate(), false);
					audioInputStream = AudioSystem.getAudioInputStream(
							audioFormat, audioInputStream);
				}

				// 打开输出设备
				DataLine.Info dataLineInfo = new DataLine.Info(
						SourceDataLine.class, audioFormat,
						AudioSystem.NOT_SPECIFIED);
				sourceDataLine = (SourceDataLine) AudioSystem
						.getLine(dataLineInfo);
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();

				// 创建独立线程进行播放
				isStop = false;
				Thread playThread = new PlayThread();
				playThread.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void playmusic(String name) {
		// TODO Auto-generated method stub
		if (CAN_PLAY_MUSIC == YES) {
			try {
				filename = name;
				// 获得即将播放的文件
				File file = new File(filepath + filename);
				// 取得文件输入流
				audioInputStream = AudioSystem.getAudioInputStream(file);
				audioFormat = audioInputStream.getFormat();
				// 转换mp3文件编码
				if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
					audioFormat = new AudioFormat(
							AudioFormat.Encoding.PCM_SIGNED,
							audioFormat.getSampleRate(), 16,
							audioFormat.getChannels(),
							audioFormat.getChannels() * 2,
							audioFormat.getSampleRate(), false);
					audioInputStream = AudioSystem.getAudioInputStream(
							audioFormat, audioInputStream);
				}

				// 打开输出设备
				DataLine.Info dataLineInfo = new DataLine.Info(
						SourceDataLine.class, audioFormat,
						AudioSystem.NOT_SPECIFIED);
				sourceDataLine = (SourceDataLine) AudioSystem
						.getLine(dataLineInfo);
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();

				// 创建独立线程进行播放
				isStop = false;
				Thread playThread = new PlayThread2();
				playThread.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class PlayThread extends Thread {

		byte tempBuffer[] = new byte[320];

		public void run() {
			try {
				int cnt;
				hasStop = false;
				// 读取数据到缓存数据
				while ((cnt = audioInputStream.read(tempBuffer, 0,
						tempBuffer.length)) != -1) {
					if (CAN_PLAY_BGM == NO) 
						isStop=true;
					if (isStop)
						break;
					if (cnt > 0) {
						// 写入缓存数据
						sourceDataLine.write(tempBuffer, 0, cnt);
					}
				}
				// Block等待临时数据被输出为空
				sourceDataLine.drain();
				sourceDataLine.close();
				hasStop = true;
				if ((cnt = audioInputStream.read(tempBuffer, 0,
						tempBuffer.length)) == -1)
					play(currentPlayingBGM);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class PlayThread2 extends Thread {

		byte tempBuffer[] = new byte[320];

		public void run() {
			try {
				int cnt;
				hasStop = false;
				// 读取数据到缓存数据
				while ((cnt = audioInputStream.read(tempBuffer, 0,
						tempBuffer.length)) != -1) {
					if (CAN_PLAY_MUSIC == NO) 
						isStop=true;
					if (isStop)
						break;
					if (cnt > 0) {
						// 写入缓存数据
						sourceDataLine.write(tempBuffer, 0, cnt);
					}
				}
				// Block等待临时数据被输出为空
				sourceDataLine.drain();
				sourceDataLine.close();
				hasStop = true;
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
	}
}
