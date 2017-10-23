import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class FileAttributes {
	/**
	 * 1. Assign an ID;
	 * 2. Get:
	 *  - name;
	 *  - size;
	 *  - checksum;
	 *  - location;
	 *  3. Put into a DB;
	 *  4. Run necessarry queries;
	 *  
	 */
	
	private File file;
	
	public FileAttributes (File file) {
		this.file = file;
	}
	
	public String getFileName() {
		return file.getName();
	}
	
	public long getFileSize() {
		return file.length();
	}
	
	public String getChecksum() throws IOException, NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(Files.readAllBytes(Paths.get(getFileLocation())));
	    byte[] digest = md.digest();
	    return DatatypeConverter.printHexBinary(digest);
	}
	
	public String getFileLocation() {
		return file.getAbsolutePath();
	}
}
