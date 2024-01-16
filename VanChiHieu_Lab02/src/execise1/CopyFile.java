package execise1;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CopyFile extends JFrame {

	private JTextField tfFom;
	private JTextField tfTo;
	private JProgressBar progress;
	private JButton btnCopy;

	public CopyFile() {
		setTitle("Copy File");
		setSize(500, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Box b, b1, b2, b3, b4;
		add(b = Box.createVerticalBox());

		b.add(b1 = Box.createHorizontalBox());b.add(Box.createVerticalStrut(10));
		b.add(b2 = Box.createHorizontalBox());b.add(Box.createVerticalStrut(10));
		b.add(b3 = Box.createHorizontalBox());b.add(Box.createVerticalStrut(10));
		b.add(b4 = Box.createHorizontalBox());b.add(Box.createVerticalStrut(10));

		JLabel lblFrom, lblTo;
		b1.add(lblFrom = new JLabel("From: "));
		b1.add(tfFom = new JTextField());
		
		b2.add(lblTo = new JLabel("To: "));
		b2.add(tfTo = new JTextField());
		
		b3.add(btnCopy = new JButton("Copy ..."));
		b4.add(progress = new JProgressBar());
		progress.setStringPainted(true);
		
		b.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		lblTo.setPreferredSize(lblFrom.getPreferredSize());
		
		tfFom.setText("data/data.txt");
		tfTo.setText("data/newFile.txt");
		
		btnCopy.addActionListener((e)->{
			File fileFrom = new File(tfFom.getText());
			File fileTo = new File(tfTo.getText());
			
			CopyTask task = new CopyTask(fileFrom, fileTo);
			task.execute();
			task.addPropertyChangeListener((evt) ->{
				if("progress".equals(evt.getPropertyName())) {
					progress.setValue((int) evt.getNewValue());
				}
			});
		});
	}

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(() -> new CopyFile().setVisible(true));
	}
}
