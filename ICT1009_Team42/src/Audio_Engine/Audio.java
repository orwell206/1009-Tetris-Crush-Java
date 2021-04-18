package Audio_Engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Audio {
	protected Clip clip;
	protected long clipTimePosition;
	protected boolean running = false;
	protected AudioInputStream audioInput;
	protected FloatControl gainControl;
	protected double volume = 0.30;
	protected String musicLocation;
	public Audio(String musicLoaction) {
		this.musicLocation = musicLoaction;
		
	}
	
	public void setVolume(double newvolume) {
		volume = newvolume;
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		float v = (float) (Math.log(newvolume) / Math.log(10.0) * 20.0);
		gainControl.setValue(v);
	}
	
	public void playMusic() {
		clip.start();
		running = true;
		
	}
	
	// for other packages to get the volume as the access modifier for volume is protected
	public double getVolume() {
		return volume;
	}
	
	public boolean getStatus() {
		return running;
	}
	
	public void setStatus(boolean state) {
		this.running = state;
	}


}