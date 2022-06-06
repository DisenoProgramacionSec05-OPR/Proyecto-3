package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;


@SuppressWarnings("serial")
public class LoginB extends JPanel implements ActionListener
{
	private Menu3 padre;
	
	private JTextField cuadroLogin;
	private JTextField cuadroNombre;
	private JButton botonEntrar;
	private JLabel textLabel;
	
	
	public LoginB(Menu3 padre)
	{
		this.padre = padre;
		setLayout(null);
		setBorder(new EtchedBorder());
		
		JLabel titulo = new JLabel("Regístrese :");
		titulo.setBounds(10, 10, 600, 30);
		titulo.setFont(new Font("Arial", Font.BOLD, 25));
		add(titulo);
		
		JLabel mensajeLogin = new JLabel("Ingrese su Usuario:");
		mensajeLogin.setBounds(10, 60, 150, 30);
		mensajeLogin.setFont(new Font("Arial", Font.PLAIN, 13));
		add(mensajeLogin);
		
		cuadroLogin = new JTextField();
		cuadroLogin.setBounds(10, 90, 150, 23);
		add(cuadroLogin);
		
		JLabel mensajeNombre = new JLabel("Ingrese su nombre:");
		mensajeNombre.setBounds(10, 120, 150, 30);
		mensajeNombre.setFont(new Font("Arial", Font.PLAIN, 13));
		add(mensajeNombre);
		
		cuadroNombre = new JTextField();
		cuadroNombre.setBounds(10, 150, 150, 23);
		add(cuadroNombre);
		
		botonEntrar = new JButton("Registrar");
		botonEntrar.setBounds(10, 180, 100, 25);
		botonEntrar.addActionListener(this);
		add(botonEntrar);
		
		textLabel = new JLabel("");
		textLabel.setBounds(10, 210, 600, 23);
		this.add(textLabel);

	}
	
	
	public void userNonExistent()
	{
		String texto = "Por favor, haga click en el boton 'Continuar'";
		textLabel.setForeground(new Color(105, 105, 105));
		textLabel.setText(texto);
	}
	
	
	public void userExistent()
	{
		String texto = "El usuario ya se encuentra registrado"
			    + " en el sistema. Por favor intente con otro";
		textLabel.setForeground(Color.RED);
		textLabel.setText(texto);
	}
	
	
	public void disableFields()
	{
		cuadroLogin.setEditable(false);
		cuadroNombre.setEditable(false);
		botonEntrar.setEnabled(false);
	}
	

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==botonEntrar)
        {
			String login = cuadroLogin.getText();
            String nombre = cuadroNombre.getText();
            
			if (login.equals("") || nombre.equals(""))
			{
				String texto = "Por favor complete todos los campos";
				textLabel.setForeground(Color.RED);
				textLabel.setText(texto);
			}
			else
			{
				padre.ingresarLogin(login, nombre);
			}
        }
    }

	
}
