import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private Clip backgroundMusicClip;
    private Clip effectClip;
    private FloatControl musicVolumeControl;

    public AudioPlayer() {
        try {
            backgroundMusicClip = AudioSystem.getClip();
            effectClip = AudioSystem.getClip();

            AudioInputStream bgStream = AudioSystem.getAudioInputStream(new File("data/audio/background.wav"));
            backgroundMusicClip.open(bgStream);

            musicVolumeControl = (FloatControl) backgroundMusicClip.getControl(FloatControl.Type.MASTER_GAIN);

            AudioInputStream effectStream = AudioSystem.getAudioInputStream(new File("data/audio/effect.wav"));
            effectClip.open(effectStream);
            setMusicVolume(0.7f);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Nem sikerült betölteni a hangokat.");
        }
    }

    public void playBackgroundMusic() {
        if (backgroundMusicClip != null) {
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusicClip.start();
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
        }
    }

    public void playEffect() {
        if (effectClip != null) {
            effectClip.setFramePosition(0);
            effectClip.start();
        }
    }

    public void setMusicVolume(float percentage) {
        if (musicVolumeControl != null) {
            float min = musicVolumeControl.getMinimum();
            float max = musicVolumeControl.getMaximum();
            float gain = min + (max - min) * percentage;
            musicVolumeControl.setValue(gain);
        }
    }
}