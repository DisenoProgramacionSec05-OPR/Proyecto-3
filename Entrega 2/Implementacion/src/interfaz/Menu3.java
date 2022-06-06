package interfaz;

import java.awt.GridLayout;

import modelo.Participante;
import procesamiento.DatosUsuarios;


@SuppressWarnings("serial")
public class Menu3 extends Menu
{
	private VentanaAplicacion ventana;	
	private LoginA p1;
	private LoginB p2;
	
	
	public Menu3(VentanaAplicacion padre)
	{
		super(1, "¡Bienvenido!");
		this.ventana = padre;
		ventana.enableBotonContinuar(false);
		setLayout(new GridLayout(2, 1));
		
		p1 = new LoginA(this);
		add(p1);
		
		p2 = new LoginB(this);
		add(p2);
	}
	
	
	public void ingresarLogin(String loginEnUso)
	{
		DatosUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Participante usuarioEnUso = archivoUsuarios.getParticipante(loginEnUso);
		
		if (usuarioEnUso == null)
		{
			p1.userNotFound();				
		}
		
		else
		{
			p1.userFound();
			p1.disableFields();
			p2.disableFields();
			ventana.setLoginEnUso(loginEnUso);
			ventana.setUsuarioEnUso(usuarioEnUso);
			ventana.enableBotonContinuar(true);
		}
	}
	
	
	public void ingresarLogin(String login, String nombre)
	{
		DatosUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Participante usuarioEnUso = archivoUsuarios.getParticipante(login);
		
		if (usuarioEnUso == null)
		{
			p2.userNonExistent();
			p1.disableFields();
			p2.disableFields();
			usuarioEnUso = archivoUsuarios.newParticipante(login, nombre);
			ventana.setUsuarioEnUso(usuarioEnUso);
			ventana.setLoginEnUso(login);
			ventana.enableBotonContinuar(true);				
		}
		
		else
		{
			p2.userExistent();
		}
	}

}
