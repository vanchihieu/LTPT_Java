package demo;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class JEditorPaneDemo1 {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("JEditorPane Example");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400, 300);
		
			JEditorPane editorPane = new JEditorPane();
			// Set content type to HTML
			editorPane.setContentType("text/html");

			// Set HTML content
			String htmlContent = "<html><body><table width='100%' border='1'"
					+ "<tr><th>ID</th><th>Name</th><th>Phone</th></tr>"
					+ "<tr><td>001</td><td>Nguyen Van A</td><td>0123456789</td></tr>"
					+ "<tr><td>002</td><td>Nguyen Van B</td><td>0123456789</td></tr>" 
					+ "</table></body></html>";
			editorPane.setText(htmlContent);
			frame.add(editorPane);
			frame.setVisible(true);
		});
	}
}
