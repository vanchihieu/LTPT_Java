package execise1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Test {
	public static void main(String[] args) throws IOException {
		File file = new File("data/data.txt");
		
		Path path = Paths.get(file.getAbsolutePath());
		
		byte[] buff = Files.readAllBytes(path);
		List<String> lines = Files.lines(path).toList();
		
		System.out.println(lines.get(6));
	}
}
