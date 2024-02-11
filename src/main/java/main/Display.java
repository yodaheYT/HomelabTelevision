package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import uk.co.caprica.vlcj.player.base.Marquee;
import uk.co.caprica.vlcj.player.base.MarqueePosition;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.fullscreen.adaptive.AdaptiveFullScreenStrategy;

public class Display {
    public static JFrame window = new JFrame();

    DirectoryPlaylistMaker playlistMaker;

    public JToggleButton playback;
    public JButton next;
    public JButton previous;
    public JToggleButton mute;

    public int volume = 100;
    public boolean muted = false;

    public boolean playing = true;

    public String currentPath = "";

    public static EmbeddedMediaPlayerComponent empc;

    public Display(DirectoryPlaylistMaker pm) {
        playlistMaker = pm;
    }

    public void togglePlaying() {
        if (playing) {
            playing = false;
            empc.mediaPlayer().controls().pause();
            playback.setSelected(false);
        } else {
            playing = true;
            empc.mediaPlayer().controls().play();
            playback.setSelected(true);
        }
    }
    public void changeVol(Boolean upDown) {
        if (upDown) {
            if (volume+5 <= 150) {
                volume += 5;
            }
        } else {
            if (volume-5 >= 0) {
                volume -= 5;
            }
        }
    }

    public void toggleFullScreen() {
        empc.mediaPlayer().fullScreen().toggle();
    }

    public void loadVideo(String path) {
        currentPath = path;
        empc.mediaPlayer().media().start(path);
        empc.mediaPlayer().media().parsing().parse();
        Marquee marquee = Marquee.marquee()
                .text(currentPath)
                .size(40)
                .colour(Color.WHITE)
                .timeout(3000)
                .position(MarqueePosition.BOTTOM)
                .opacity(0.8f)
                .enable();
        empc.mediaPlayer().marquee().set(marquee);
        playback.setSelected(true);
    }

    public void init() {
        window.setBounds(100, 100, 1280, 768);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                empc.release();
                System.exit(0);
            }
        });

        empc = new EmbeddedMediaPlayerComponent() {
            // Play/Pause
            @Override
            public void mouseClicked(MouseEvent e) {
                togglePlaying();
            }

            // Volume Scroll
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (muted) {
                    muted = false;
                }
                if (e.getWheelRotation() == 1) {
                    changeVol(false);
                } else if (e.getWheelRotation() == -1) {
                    changeVol(true);
                }
                empc.mediaPlayer().audio().setVolume(volume);
                Marquee marquee = Marquee.marquee()
                        .text(Integer.toString(volume))
                        .size(40)
                        .colour(Color.WHITE)
                        .timeout(500)
                        .position(MarqueePosition.TOP_RIGHT)
                        .opacity(0.8f)
                        .enable();
                empc.mediaPlayer().marquee().set(marquee);
            }

            // Keybinds
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Key pressed. " + e.getKeyCode());
                if (e.getKeyCode() == 70) {
                    toggleFullScreen();
                } else if (e.getKeyCode() == 32) {
                    togglePlaying();
                } else if (e.getKeyCode() == 39) {
                    playlistMaker.skip();
                    loadVideo(playlistMaker.getCurrent());
                } else if (e.getKeyCode() == 37) {
                    playlistMaker.previous();
                    loadVideo(playlistMaker.getCurrent());
                } else if (e.getKeyCode() == 38) {
                    changeVol(true);
                } else if (e.getKeyCode() == 40) {
                    changeVol(false);
                }
            }

            // Next Video
            @Override
            public void finished(MediaPlayer mediaPlayer) {
                playlistMaker.skip();
            }
        };

        window.add(empc);

        Border playbackBorder = BorderFactory.createTitledBorder("Video");
        Border othersBorder = BorderFactory.createTitledBorder("Others");

        JPanel playbackControls = new JPanel();
        playbackControls.setBorder(playbackBorder);
        previous = new JButton("Previous");
        playbackControls.add(previous);
        playback = new JToggleButton("Play");
        playbackControls.add(playback);
        next = new JButton("Skip");
        playbackControls.add(next);

        JPanel otherControls = new JPanel();
        otherControls.setBorder(othersBorder);
        mute = new JToggleButton("Mute");
        otherControls.add(mute);

        JPanel pane = new JPanel();
        pane.add(playbackControls);
        pane.add(otherControls);
        pane.setPreferredSize(new Dimension(1280, 48));

        playback.addActionListener(e -> togglePlaying());

        window.add(pane, BorderLayout.SOUTH);

        empc.mediaPlayer().fullScreen().strategy(new AdaptiveFullScreenStrategy(window));
        window.setVisible(true);
    }
}
