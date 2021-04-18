package Audio_Engine;

import Audio_Engine.Audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BgMusic extends Audio implements Runnable {
	public BgMusic(String musicLoaction){
		super(musicLoaction);
	}
	
	public void playMusic() {
		setVolume(volume);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		running = true;
	}
	
	public void stopMusic() {
		clipTimePosition = clip.getMicrosecondPosition();
		clip.stop();
		running = false;
	}
	
	public void resumeMusic() {
		clip.setMicrosecondPosition(clipTimePosition);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		running = true;
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

