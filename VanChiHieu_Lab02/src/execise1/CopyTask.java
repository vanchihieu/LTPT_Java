package execise1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class CopyTask extends SwingWorker<Integer, Integer> {
	private File fileFrom;
	private File fileTo;
	private byte[] buff;

	public CopyTask(File fileFrom, File fileTo) {
		super();
		this.fileFrom = fileFrom;
		this.fileTo = fileTo;
	}

	// công việc cần phải làm
	@Override
	protected Integer doInBackground() throws Exception {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(fileFrom));
			out = new BufferedOutputStream(new FileOutputStream(fileTo));
			
			Path path = Paths.get(fileFrom.getAbsolutePath());
			buff = Files.readAllBytes(path);

			int n = buff.length / 100; // 1 lần chép 1/100 % so với mảng ban đầu

			byte[] temp = new byte[n];

			int progress = 0;
			while (in.available() > 0) {
				int x = in.read(temp, 0, n); // đọc vào mảng temp, vị trí 0, ...
				out.write(temp, 0, x);
				
				progress++;
				setProgress(Math.min(100, progress));
				
				publish(x);
			}

		} finally {

			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}

		return buff.length;
	}

	// quá trình thực hiện
	@Override
	protected void process(List<Integer> chunks) {
		System.out.println(chunks);
	}

	@Override
	protected void done() {
		Integer n;
		try {
			n = get();
			JOptionPane.showMessageDialog(null, "Finished + Bytes in total: " + n.byteValue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
