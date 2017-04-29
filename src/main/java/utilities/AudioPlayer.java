package utilities;

/**
 * Audio Player
 *
 * @author Seehait Chockthanyawat
 * @since 2017-04-29
 */
public class AudioPlayer {
    private static AudioPlayer audioPlayer;

    /**
     * Constructs an instance.
     */
    private AudioPlayer() {

    }

    /**
     * Constructs an instance.
     *
     * @return singleton audio player
     */
    public static AudioPlayer getAudioPlayer() {
        if (audioPlayer == null) {
            audioPlayer = new AudioPlayer();
        }

        return audioPlayer;
    }

    /**
     * Plays audio.
     *
     * @param text text
     */
    public void play(String text) {
        /*
            Use TTS to generate audio and play.
            If AudioPlayer is playing previous audio, terminate and play new audio.
         */
    }
}
