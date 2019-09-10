package inSeongJo.aHenIntoTheWild.view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import inSeongJo.aHenIntoTheWild.model.vo.User;
import inSeongJo.aHenIntoTheWild.view.MainPage.RoundedBorder;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class VideoTest extends JPanel {
	private VideoTest vt;
	EmbeddedMediaPlayer emp;

	public VideoTest(MainFrame mf, String fileName, User user, JPanel np, Media media) {
		media.soundStop();
		vt = this;
		// Create an instance of Canvas wish will be used to display video
		this.setLayout(new BorderLayout());
		Canvas c = new Canvas();
		this.add(c);
		// background is black
		c.setBackground(Color.BLACK);

		// video take all the surface of JPanel
		mf.setBounds(0, 0, 1024, 768);
		mf.add(this);

		JButton skip = new JButton("Skip");
		skip.setContentAreaFilled(false);
		skip.setForeground(Color.BLACK);
		skip.setBorderPainted(false);
		skip.setFocusPainted(false);
		skip.setOpaque(false);
		add(skip, BorderLayout.SOUTH);

		skip.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				emp.stop();
				mf.remove(vt);
				ChangePanel.changePanel(mf, vt, np);
				media.clip.start();
			}
		});

		// Secondly we read the video file using vlcj and the native library of VLC

		// Load the native library to VLC from the directory where VLC is installed
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "lib");
		Native.load(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		// Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		// initialize the media player
		MediaPlayerFactory mpf = new MediaPlayerFactory();
		// control all the interactions with the user
		emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(mf));
		emp.setVideoSurface(mpf.newVideoSurface(c));
		// full screen
		// emp.toggleFullScreen();
		// hide the cursor
		emp.setEnableMouseInputHandling(false);
		// disable the keyboard
		emp.setEnableKeyInputHandling(false);

		String file = "video/" + fileName + ".mp4";
		// prepare file to read
		// emp.prepareMedia(file);
		// read the file
		// emp.play();
		emp.playMedia(file);
		emp.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
			@Override
			public void finished(MediaPlayer mediaPlayer) {
				mf.remove(vt);
				ChangePanel.changePanel(mf, vt, np);
				media.clip.start();

			}
		});

	}

}
