package Audio_Engine;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffect extends Audio implements Runnable{
	
	public SoundEffect(String musicLoaction){
		super(musicLoaction);
		super.volume = 0.70;
	}
	
	public void playMusic() {
		clip.setMicrosecondPosition(clipTimePosition);
		setVolume(volume);
		clip.start();
		clip.loop(0);
		running = true;

	}
	public void stopMusic() {
		clip.stop();
		running = false;
		clip.close();
		
	}
	@Override
	public void run() {
		try {
			File musicPath= new File(this.musicLocation); // calling for the music path
			if (musicPath.exists()) {
				audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);		
			} else {
				System.out.print("can't find file");
				
			}
			if(clip.isOpen()) {
				audioInput.close();
			}
			
		} catch(IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
			System.out.print("Unable to open audio file, please make sure you have the correct audio file");
		}
		
	}
}
