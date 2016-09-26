package registro;

import java.awt.Dimension;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Time;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import baseDeDatos.BDD;

public class ErrorManager {
	int user;
	Exception e;
	
	public ErrorManager(int user, Exception e){
		this.user = user;
		this.e = e;
		
		showQuickErrorDialog(null, e);
	}
	
	public void guardarError(int user, Exception e, Date fecha, Time hora){
		ErrorManager err = new ErrorManager(user, e);
		BDD bdd = new BDD();
	}
	
	private static void showQuickErrorDialog(JFrame parent, Exception e) {
		// create and configure a text area - fill it with exception text.
		final JTextArea textArea = new JTextArea();		
		textArea.setEditable(false);
		
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		
		textArea.setText("Ocurrio un error. Comuniquese con el personal de sistemas\n\nDETALLE DEL ERROR:\n---------------------------------\n" + writer.toString());
		textArea.setCaretPosition(0);
		
		
		// stuff it in a scrollpane with a controlled size.
		JScrollPane scrollPane = new JScrollPane(textArea);		
		scrollPane.setPreferredSize(new Dimension(500, 500));
		
		// pass the scrollpane to the joptionpane.				
		JOptionPane.showMessageDialog(parent, scrollPane, "Ocurrio un Error", JOptionPane.ERROR_MESSAGE);
	}
}
