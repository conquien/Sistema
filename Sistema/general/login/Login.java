package login;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import baseDeDatos.BDD;
import menu.MenuPrincipal;
import registro.ErrorManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Login {

	private JFrame frame;
	private JTextField txtUsuario;
	private JPasswordField pwdPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 271, 203);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(45, 33, 46, 14);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(45, 70, 69, 14);
		frame.getContentPane().add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(134, 30, 100, 20);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(22, 117, 94, 23);
		frame.getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");		
		btnCancelar.setBounds(140, 117, 94, 23);
		frame.getContentPane().add(btnCancelar);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(134, 67, 100, 20);
		frame.getContentPane().add(pwdPassword);
		
		
		/* listeners */
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtUsuario.getText().length() == 0)
					{
						JOptionPane.showMessageDialog(null, "Debe Ingresar el Usuario");
						txtUsuario.requestFocus();
						return;
					}
					
					char[] pwd = pwdPassword.getPassword();
					String pw = new String(pwd);
					String p = pw;
					
					if (p.length() == 0)
					{
						JOptionPane.showMessageDialog(null, "Debe Ingresar la Contraseña");
						pwdPassword.requestFocus();
						return;
					}
									
					BDD bdd = new BDD();
										
					if (bdd.getConexion() == null)
					{
						JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
						return;
					}
						
					if(bdd.getConexion().isClosed()){
		    			JOptionPane.showMessageDialog(null, "Se devolvio una conexion cerrada.");
		    			return;
					}
		    		
					PreparedStatement st = bdd.getConexion().prepareStatement("SELECT ID_USUA FROM USUARIOS WHERE CODI_USUA = ? AND PASS_USUA = ?");
					st.setString(0, txtUsuario.getText());
					st.setString(1, p);
		    		ResultSet rs = st.executeQuery();
					
		    		if (rs.next()){
		    			/* conexion abierta */
		    			MenuPrincipal a = new MenuPrincipal();					
						a.setVisible(true);
						frame.dispose();
						bdd.getConexion().close();
		    		}
		    		else
		    		{
		    			JOptionPane.showMessageDialog(null, "Usuario / Contraseña erroneo. Verifique los datos ingresados.");
		    			return;
		    		}
				} catch (Exception e1){
					ErrorManager err = new ErrorManager(0, e1);
					
				}
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.dispose();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
