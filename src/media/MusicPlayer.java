package media;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class MusicPlayer {
	// BGM����
	boolean isStop = true;// ���Ʋ����߳�
	boolean hasStop = true;// �����߳�״̬

	String filepath;// �����ļ�Ŀ¼
	String filename;// �����ļ�����
	AudioInputStream audioInputStream;// �ļ���
	AudioFormat audioFormat;// �ļ���ʽ
	SourceDataLine sourceDataLine;// ����豸

	String currentPlayingBGM;
	String currentPlayingMusic;

	public static int CAN_PLAY_BGM = 1;
	public static int CAN_PLAY_MUSIC = 1;
	public static int YES = 1;
	public static int NO = 2;

	// ����˳��������������Դ
	public MusicPlayer(String path) {
		filepath = path + "/";
	}

	// ����Ҫ���õ�����
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

				// ��ü������ŵ��ļ�
				File file = new File(filepath + currentPlayingBGM);
				// ȡ���ļ�������
				audioInputStream = AudioSystem.getAudioInputStream(file);
				audioFormat = audioInputStream.getFormat();
				// ת��mp3�ļ�����
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

				// ������豸
				DataLine.Info dataLineInfo = new DataLine.Info(
						SourceDataLine.class, audioFormat,
						AudioSystem.NOT_SPECIFIED);
				sourceDataLine = (SourceDataLine) AudioSystem
						.getLine(dataLineInfo);
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();

				// ���������߳̽��в���
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
				// ��ü������ŵ��ļ�
				File file = new File(filepath + filename);
				// ȡ���ļ�������
				audioInputStream = AudioSystem.getAudioInputStream(file);
				audioFormat = audioInputStream.getFormat();
				// ת��mp3�ļ�����
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

				// ������豸
				DataLine.Info dataLineInfo = new DataLine.Info(
						SourceDataLine.class, audioFormat,
						AudioSystem.NOT_SPECIFIED);
				sourceDataLine = (SourceDataLine) AudioSystem
						.getLine(dataLineInfo);
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();

				// ���������߳̽��в���
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
				// ��ȡ���ݵ���������
				while ((cnt = audioInputStream.read(tempBuffer, 0,
						tempBuffer.length)) != -1) {
					if (CAN_PLAY_BGM == NO) 
						isStop=true;
					if (isStop)
						break;
					if (cnt > 0) {
						// д�뻺������
						sourceDataLine.write(tempBuffer, 0, cnt);
					}
				}
				// Block�ȴ���ʱ���ݱ����Ϊ��
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
				// ��ȡ���ݵ���������
				while ((cnt = audioInputStream.read(tempBuffer, 0,
						tempBuffer.length)) != -1) {
					if (CAN_PLAY_MUSIC == NO) 
						isStop=true;
					if (isStop)
						break;
					if (cnt > 0) {
						// д�뻺������
						sourceDataLine.write(tempBuffer, 0, cnt);
					}
				}
				// Block�ȴ���ʱ���ݱ����Ϊ��
				sourceDataLine.drain();
				sourceDataLine.close();
				hasStop = true;
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
	}
}
