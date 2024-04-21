package demo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;

public class WebviewDemo extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtURL;
	private JButton btnSearch;
	private JEditorPane editorPane;
	private JPanel panel;
	private String defaultURL;

	public WebviewDemo(String defaultURL) {
		this.defaultURL = defaultURL;
		panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		// Thiết lập JFrame
		setTitle("Simple Web Viewer");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		txtURL = new JTextField(25);
		btnSearch = new JButton("Search");

		txtURL.setText(defaultURL);

		// Tạo JEditorPane
		editorPane = new JEditorPane();
		editorPane.setEditable(false); // Ngăn không cho người dùng chỉnh sửa nội dung

		panel.add(txtURL);
		panel.add(btnSearch);
		panel.add(editorPane);

		// Thêm JScrollPane để có thanh cuộn
		JScrollPane scrollPane = new JScrollPane(editorPane);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pageURL = "";
				if (e.getSource() == btnSearch) {
					pageURL = txtURL.getText();
				} else {
					pageURL = defaultURL;
					JOptionPane.showMessageDialog(WebviewDemo.this,
							"Nhập địa chỉ trang web", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}

				txtURL.setText(pageURL);
				navigate(pageURL);
			}
		});

		editorPane.addHyperlinkListener((HyperlinkEvent event) -> {
			if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				navigate(event.getURL().toString());
			} 
		});

		// Load trang web
		navigate("https://google.com/");
	}

	private void navigate(String url) {
		try {
			SwingUtilities.invokeLater(() -> {
				try {
					editorPane.setPage(url);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this, 
							"Không thể load trang web: " + url, "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Tạo và hiển thị ứng dụng
		SwingUtilities.invokeLater(() -> {
			WebviewDemo viewer = new WebviewDemo("https://www.google.com/");
			viewer.setVisible(true);
		});
	}
}
