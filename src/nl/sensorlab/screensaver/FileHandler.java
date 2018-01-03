package nl.sensorlab.screensaver;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;

public class FileHandler {
	
	private File basePath;
	private boolean basePathExistedAtPreviousPoll;
	
	private List<ScreenSaverFile> files = new ArrayList<>();
	
	private FilenameFilter imageFilenameFilter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			return 
				name.toLowerCase().endsWith(".png")
				|| name.toLowerCase().endsWith(".jpg")
				|| name.toLowerCase().endsWith(".jpeg");
		}
	};
	
	public FileHandler(String basePath) {
		this.basePath = new File(basePath);
		basePathExistedAtPreviousPoll = this.basePath.exists();
		refreshFileList();
	}
	
	public List<ScreenSaverFile> getFiles() {
		return files;
	}
	
	private void refreshFileList() {
		files.clear();
		
		// Add animation
		files.add(new ScreenSaverFile(ScreenSaverType.SENSORLABANIMATION, null));
		
		// Skip further processing if the source folder doesn't exist
		if (!basePath.exists()) {
			return;
		}
		
		// Add image files
		for (File f: basePath.listFiles(imageFilenameFilter)) {
			files.add(new ScreenSaverFile(ScreenSaverType.IMAGE, f));
		}
		
	}
	
	public void pollForChanges() {
		if (basePathExistedAtPreviousPoll != this.basePath.exists()) {
			refreshFileList();
		}
	}
	
	public class ScreenSaverFile {
		public ScreenSaverType type;
		public File file;
		
		public ScreenSaverFile(ScreenSaverType type, File file) {
			this.type = type;
			this.file = file;
		}
	}
	
	public enum ScreenSaverType {
		SENSORLABANIMATION,
		IMAGE,
		VIDEO
	}

}
