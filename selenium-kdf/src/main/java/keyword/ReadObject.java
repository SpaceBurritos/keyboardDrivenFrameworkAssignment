package keyword;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObject {

	public static Properties getObjectRepository(String objectRepoName) throws IOException{
		
		Properties p = new Properties();

		InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\objectRepositories\\"+objectRepoName + ".properties"));
		p.load(stream);
		return p;
	}
	
}
