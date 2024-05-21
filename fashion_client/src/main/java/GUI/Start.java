package GUI;

import java.rmi.RemoteException;

import javax.swing.SwingUtilities;

public class Start {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				BackGround backGound = new BackGround();
				DangNhap dangNhap;
				try {
					dangNhap = new DangNhap();
					backGound.setVisible(true);
					backGound.handleOpen(dangNhap);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}
}
