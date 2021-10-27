package org.wcscda.worms.gamemechanism;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/*import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

import java.io.File;
import java.io.IOException;

/* NRO 2021-09-30 : Test class to play sound
 *  don't use for the moment as it requires some thread
 *  management, which hasn't been covered yet
 */
public class WormSoundPlayer {

  private static WormSoundPlayer instance;

  public WormSoundPlayer() {}

  /*public void playSound() throws FileNotFoundException, JavaLayerException {
    FileInputStream fis =
        new FileInputStream("src/resources/Nanatsu no Taizai AMV - Human Race.mp3");
    Player playMP3 = new Player(fis);

    playMP3.play();
  }*/

  public void grenadeSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
        AudioSystem.getAudioInputStream(new File("src/resources/sounds/explosion-1.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
     //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
     //clip.close();
  }

  public void grenadeBananeSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new File("src/resources/sounds/explosion-2.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    //clip.close();
  }

  public void shotgunSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new File("src/resources/sounds/shotgun.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    //clip.close();
  }

  public void hadokenSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new File("src/resources/sounds/hadoken.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    //clip.close();
  }

  public void ambientSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new File("src/resources/sounds/ambient.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    clip.loop(10);
    //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    //clip.close();
  }

  public void winSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new File("src/resources/sounds/win.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    //clip.close();
  }

  public void startSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new File("src/resources/sounds/herewego.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    //clip.close();
  }

  public void hadokenImpactSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new File("src/resources/sounds/hadoken-impact.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    //clip.close();
  }

  public void grenadeDropSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new File("src/resources/sounds/grenade-drop.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    //clip.close();
  }

  public void screamSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new File("src/resources/sounds/homer-scream.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    //clip.close();
  }

  public void openItemSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new File("src/resources/sounds/item-open.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    //clip.close();
  }

  public void cursorSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new File("src/resources/sounds/cursor.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    //clip.close();
  }

  public void chooseSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new File("src/resources/sounds/choose.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    //clip.close();
  }

  public void themeSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new File("src/resources/sounds/theme.wav").getAbsoluteFile());
    Clip themeSoundClip = AudioSystem.getClip();
    themeSoundClip.open(audioInputStream);
    themeSoundClip.start();
    //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    //clip.close();
  }

  public void numberSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new File("src/resources/sounds/number.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    //Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    //clip.close();
  }

  public static WormSoundPlayer getWormSoundPlayer() {
    if (instance == null) {
      instance = new WormSoundPlayer();
    }

    return instance;
  }

  /*public void playSound2() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
  	AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("src/resources/Nanatsu no Taizai AMV - Human Race.mp3"));
  	Clip clip = AudioSystem.getClip();
  	clip.open(audioIn);
  	clip.start();

  }*/
}
