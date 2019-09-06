package inSeongJo.aHenIntoTheWild.view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JPanel;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class VideoTest extends JPanel{
	private VideoTest vt;
	
	public VideoTest(MainFrame mf) {
				vt = this;
				//Create an instance of Canvas wish will be used to display video
				Canvas c = new Canvas();
				//background is black
				c.setBackground(Color.BLACK);
				this.setLayout(new BorderLayout());
				//video take all the surface of JPanel
				this.add(c);
				mf.setBounds(100, 100, 1280, 720);
				mf.add(this);
				
				
				//Secondly we read the video file using vlcj and the native library of VLC
				
				//Load the native library to VLC from the directory where VLC is installed
				NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"lib");
				Native.load(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
				//Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
				//initialize the media player
				MediaPlayerFactory mpf = new MediaPlayerFactory();
				//control all the interactions with the user
				EmbeddedMediaPlayer emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(mf));
				emp.setVideoSurface(mpf.newVideoSurface(c));
				//full screen
				//emp.toggleFullScreen();
				//hide the cursor
				emp.setEnableMouseInputHandling(false);
				//disable the keyboard
				emp.setEnableKeyInputHandling(false);
				
				String file = "video/intro.mp4";
				//prepare file to read
				//emp.prepareMedia(file);
				//read the file
				//emp.play();
				emp.playMedia(file);
				

		
	}

}
