import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class FileSorter {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		/**
		 * Select a specific folder.
		 * For each file and subfolder:
		 * 	— get filename,
		 * 	— get filesize,
		 * 	— get file location,
		 *  — calculate md5checksum,
		 *  - write data above into a file,
		 */
		
//		DBHandler.createDBTable();
		
		File sourceDir = new File("");
		List<File> files = (List<File>) FileUtils.listFiles(sourceDir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
//		for (File file : files) {
//			FileAttributes fa = new FileAttributes(file);
//			DBHandler.insertField(fa.getFileName(), fa.getFileSize(), fa.getFileLocation(), fa.getChecksum());
//		}
		
		DBHandler.queryDB("SELECT * from FileInventory ORDER BY checksum");
		System.out.println("Execution completed");
	}

}
