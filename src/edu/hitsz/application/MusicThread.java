package edu.hitsz.application;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicThread extends Thread {

    private String filename;
    private AudioFormat audioFormat;
    private byte[] samples;
    private boolean loop;
    private int playTime; // Control variable for specifying playback time in milliseconds
    public volatile boolean stopRequested = true; // Flag to indicate if playback should be stopped
    private float volume = 1;
    private boolean enable = true;

    public MusicThread(String filename) {
        this(filename, true, -1, 1, true); // Defaults to looping indefinitely
    }

    public MusicThread(String filename, boolean loop, int playTime, float volume, boolean enable) {
        this.filename = filename;
        this.loop = loop;
        this.playTime = playTime;
        this.volume = volume;
        this.enable = enable;
        reverseMusic();
    }

    public void reverseMusic() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename));
            audioFormat = stream.getFormat();
            samples = getSamples(stream);
        } catch (UnsupportedAudioFileException | IOException e) {
            System.err.println("Error loading audio file: " + e.getMessage());
        }
    }

    public byte[] getSamples(AudioInputStream stream) {
        int size = (int) (stream.getFrameLength() * audioFormat.getFrameSize());
        byte[] samples = new byte[size];
        try (DataInputStream dataInputStream = new DataInputStream(stream)) {
            dataInputStream.readFully(samples);
        } catch (IOException e) {
            System.err.println("Error reading audio samples: " + e.getMessage());
        }
        return samples;
    }

    public void play(InputStream source, float volume) {
    int size = (int) (audioFormat.getFrameSize() * audioFormat.getSampleRate());
    byte[] audioBuffer = new byte[size];
    SourceDataLine dataLine = null;
    DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
    try {
        dataLine = (SourceDataLine) AudioSystem.getLine(info);
        dataLine.open(audioFormat, size);
        
        // Adjust volume
        FloatControl gainControl = (FloatControl) dataLine.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume);

        dataLine.start();

        long startTime = System.currentTimeMillis(); // Record start time for playback duration

        int numBytesRead;
        while ((!stopRequested && (loop || (System.currentTimeMillis() - startTime) <= playTime))) {
            // Check if input stream has available data
            if (source.available() <= 0) {
                if (loop) {
                    // If looping is enabled, reset the input stream
                    source.reset();
                } else {
                    // If looping is disabled, exit the loop
                    break;
                }
            }

            // Read audio data from the input stream
            numBytesRead = source.read(audioBuffer, 0, audioBuffer.length);
            if (numBytesRead == -1) {
                // If end of stream is reached and looping is enabled, reset the input stream
                if (loop) {
                    source.reset();
                } else {
                    break;
                }
            }

            // Write audio data to the output line
            dataLine.write(audioBuffer, 0, numBytesRead);
        }
    } catch (LineUnavailableException | IOException e) {
        System.err.println("Error playing audio: " + e.getMessage());
    } finally {
        if (dataLine != null) {
            dataLine.drain();
            dataLine.close();
        }
    }
}

    @Override
    public void run() {
        if(!enable)
        {
            return;
        }
        stopRequested = false;
        try (InputStream stream = new ByteArrayInputStream(samples)) {
            play(stream, volume);
        } catch (IOException e) {
            System.err.println("Error playing audio: " + e.getMessage());
        }
    }

    public void stopPlayback() {
        stopRequested = true;
        this.interrupt(); // Interrupt the thread to exit playback loop
    }
}