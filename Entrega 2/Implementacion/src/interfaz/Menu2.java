package interfaz;

import java.awt.GridLayout;
import java.util.ArrayList;

import modelo.Coordinador;
import modelo.Participante;
import procesamiento.DatosUsuarios;


@SuppressWarnings("serial")
public class Menu2 extends Menu
{
	
	private VentanaAplicacion ventana;
	private EleccionA p1;
	private EleccionB p2;
	
	public Menu2(VentanaAplicacion padre)
	{
		super(2, "Elija el proyecto: ");
		this.ventana = padre;
		ventana.enableBotonContinuar(false);
		setLayout(new GridLayout(2, 1));
		
		p1 = new EleccionA(this);
		addProyectosParticipante();
		add(p1);
		
		p2 = new EleccionB(this);
		add(p2);
		
	}
	
	
	private void addProyectosParticipante()
	{
		String loginEnUso = ventana.getLoginEnUso();
		DatosUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		ArrayList<String> proyectosDelUsuario = archivoUsuarios.getProyectosUsuario(loginEnUso);
		
		if (proyectosDelUsuario == null)
		{
			p1.disableFields();
		}
			
		else
		{
			int numProyectos = proyectosDelUsuario.size();
			
			for (int i=1; i<=numProyectos; i++)
			{
				int index = i-1;
				String opcionProyecto = proyectosDelUsuario.get(index);
				p1.addProyectoDesplegable(opcionProyecto);
			}
		}
	}
	
	
	public void setProyectoEnUso(String nombre)
	{
		p1.disableFields();
		p2.disableFields();
		Coordinador coordinadorProyecto = ventana.getCoordinadorProyecto();
		coordinadorProyecto.seleccionarProyecto(nombre);
		ventana.enableBotonContinuar(true);
	}
	
	
	public void newProjectSettings(String nombre, String descripcion, int numTipos)
	{
		String fechaHoy = ventana.getFecha();
		new NuevoProyecto(nombre, descripcion, numTipos, fechaHoy, this);
	}
	
	
	public void crearProyecto(String nombre, String descripcion, String fechaInicio,
			  				  String fechaFin, ArrayList<String> tiposActividades)
	{
		Participante autor = ventana.getUsuarioEnUso();
		Coordinador coordinadorProyecto = ventana.getCoordinadorProyecto();			
		coordinadorProyecto.crearProyecto(nombre, descripcion, fechaInicio,
										  fechaFin, tiposActividades, autor);
		
		p1.disableFields();
		p2.disableFields();
		p2.successfulSave();
		ventana.enableBotonContinuar(true);
	}
	
}
