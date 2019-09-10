package inSeongJo.aHenIntoTheWild.view;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Media {
	Clip clip;
	public void sound(String file) {
		File bgm;
		AudioInputStream stream;
		AudioFormat format;
		DataLine.Info info;

		bgm = new File("sounds/" + file + ".wav"); // 사용시에는 개별 폴더로 변경할 것

		

		try {
			stream = AudioSystem.getAudioInputStream(bgm);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
		} catch (Exception e) {
			System.out.println("err : " + e);
		}

	}
	public void soundStop() {
		clip.stop();
	}
	public boolean runningCheck() {
		if(clip == null) {
			return true;
		}else {
			return false;
		}
	}
	
}