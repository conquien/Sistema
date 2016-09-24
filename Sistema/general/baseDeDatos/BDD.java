package baseDeDatos;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BDD {
	private Connection conexion = null;
	
	public BDD() {
		String rutaBase = leerRutaConfig();
		abrirConexion(rutaBase);
	}

	private String leerRutaConfig(){		
		try{
		    Properties propiedades = new Properties();
		    propiedades.load(new FileInputStream("config"));	    		   
		    return propiedades.getProperty("RUTABASE2");
		}catch(Exception e1){
			e1.printStackTrace();
			return "";
		}
	}
	
	private void abrirConexion(String rutaBase){
		if (rutaBase != "")
	    {
	    	try{
	    		Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
	    		this.setConexion(DriverManager.getConnection("jdbc:firebirdsql:" + rutaBase, "SYSDBA", "masterkey"));	
	    	}catch(Exception e){    			    			    	
	    		showQuickErrorDialog(null, e);	    		
	    		return;
	    	}
	    }			
	}
	public static void showQuickErrorDialog(JFrame parent, Exception e) {
		// create and configure a text area - fill it with exception text.
		final JTextArea textArea = new JTextArea();		
		textArea.setEditable(false);
		
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		textArea.setText("Ocurrio un error. Comuniquese con el personal de sistemas\n\nDETALLE DEL ERROR:\n---------------------------------\n" + writer.toString());
		
		// stuff it in a scrollpane with a controlled size.
		JScrollPane scrollPane = new JScrollPane(textArea);		
		scrollPane.setPreferredSize(new Dimension(500, 500));
		
		// pass the scrollpane to the joptionpane.				
		JOptionPane.showMessageDialog(parent, scrollPane, "Ocurrio un Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public Connection getConexion() {
		return conexion;
	}
	
	public void setConexion(Object object) {
		if (object.equals(null))
			this.conexion = null;
		else
			this.conexion = (Connection) object;
	}
	

}
