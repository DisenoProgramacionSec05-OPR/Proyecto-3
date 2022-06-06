package interfaz;

import java.awt.GridLayout;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;

import modelo.Actividad;
import modelo.Coordinador;
import modelo.Participante;
import procesamiento.DatosUsuarios;


@SuppressWarnings("serial")
public class Menu4 extends Menu
{
	private VentanaAplicacion ventana;	
	private ProyectoA p1;
	private ProyectoB p2;	
	
	
	public Menu4(VentanaAplicacion padre)
	{
		super(3, "PROYECTO");
		this.ventana = padre;
		ventana.enableBotonContinuar(false);
		setLayout(new GridLayout(2, 1));
		
		Coordinador coordinadorProyecto = padre.getCoordinadorProyecto();
		String nombre = coordinadorProyecto.getNombreProyecto();
		String descripcion = coordinadorProyecto.getDescripcionProyecto();
		String fechaInicio = coordinadorProyecto.getFechaInicio();
		String fechaFin = coordinadorProyecto.getFechaFin();
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> logins = new ArrayList<String>(participantesProyecto.keySet());
		
		p1 = new ProyectoA(this, nombre, descripcion,
				                fechaInicio, fechaFin, logins);
		add(p1);
		
		p2 = new ProyectoB(this);
		add(p2);
	}

	
	public String getNombreParticipante(String login)
	{
		DatosUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Participante participante = archivoUsuarios.getParticipante(login);
		
		return participante.getNombre();
	}
	
	
	public void newParticipantSettings()
	{
		new AgregarParticipante(this);
	}
	
	
	public boolean loginRegistrado(String login)
	{
		boolean ans = true;
		
		DatosUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Participante usuarioEnUso = archivoUsuarios.getParticipante(login);
		
		if (usuarioEnUso == null)
		{
			ans = false;				
		}
		
		return ans;
	}
	
	
	public void agregarParticipante(String login)
	{
		DatosUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Participante usuarioEnUso = archivoUsuarios.getParticipante(login);
		String nombre = usuarioEnUso.getNombre();
		
		Coordinador coordinadorProyecto = ventana.getCoordinadorProyecto();
		coordinadorProyecto.agregarParticipante(new Participante(login, nombre));
		ventana.refresh();
		
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> nombres = new ArrayList<String>(participantesProyecto.keySet());
		
		p1.setListaParticipantes(nombres);
	}
	
	
	public void agregarParticipante(String login, String nombre)
	{
		Coordinador coordinadorProyecto = ventana.getCoordinadorProyecto();
		coordinadorProyecto.agregarParticipante(new Participante(login, nombre));
		ventana.refresh(); //REVISAR
		
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> nombres = new ArrayList<String>(participantesProyecto.keySet());
		
		p1.setListaParticipantes(nombres);
	}
	
	public void elegirTituloRegistro()
	{
		Coordinador coordinadorProyecto = ventana.getCoordinadorProyecto();
		HashMap<String, ArrayList<Actividad>> actividades = coordinadorProyecto.getActividades();
		ArrayList<String> titulos = new ArrayList<String>(actividades.keySet());
		
		SelecRegistro selectReg = new SelecRegistro(this); 
		
		for (String titulo : titulos)
		{
			selectReg.addTituloDesplegable(titulo);
		}
		
		selectReg.setVisible(true);
	}
	
	
	public void addRegistros(SelecRegistro selectReg, String titulo)
	{
		Coordinador coordinadorProyecto = ventana.getCoordinadorProyecto();
		HashMap<String, ArrayList<Actividad>> actividades = coordinadorProyecto.getActividades();
		ArrayList<Actividad> registros = actividades.get(titulo);
	
		for (Actividad registro : registros)
		{
			String fechaAct = registro.getFecha();
			String horaInicio = registro.getHoraInicio();
			String horaFin = registro.getHoraFin();
			selectReg.addFechaDesplegable(fechaAct, horaInicio, horaFin);
		}
	}
	
	
	public void modificarRegistro(String titulo, int index)
	{
		Coordinador coordinadorProyecto = ventana.getCoordinadorProyecto();
		HashMap<String, ArrayList<Actividad>> actividades = coordinadorProyecto.getActividades();
		Actividad registro = actividades.get(titulo).get(index);
		
		String fecha = registro.getFecha();
		String horaInicio = registro.getHoraInicio();
		String horaFin = registro.getHoraFin();
		
		new CambiarRegistro(this, titulo, index, fecha, horaInicio, horaFin);
	}
	
	
	public void actualizarRegistro(String titulo, int index,
			String fecha, String horaInicio, String horaFin)
	{
		Coordinador coordinadorProyecto = ventana.getCoordinadorProyecto();
		coordinadorProyecto.actualizarActividad(titulo, index, fecha,
												horaInicio, horaFin);
	}
	
	
	
	public void elegirParticipante()
	{
		Coordinador coordinadorProyecto = ventana.getCoordinadorProyecto();
		HashMap<String, Participante> participantesProyecto = coordinadorProyecto.getParticipantes();
		ArrayList<String> logins = new ArrayList<String>(participantesProyecto.keySet());
		
		SelecParticipante selectPart = new SelecParticipante(this);
		
		for (String login : logins)
		{
			selectPart.addParticipanteDesplegable(login);
		}
		
		selectPart.setVisible(true);
	}
	
	
	public void generarReporte(String login)
	{
		DatosUsuarios archivoUsuarios = ventana.getArchivoUsuarios();
		Coordinador coordinadorProyecto = ventana.getCoordinadorProyecto();
		
		Participante participante = archivoUsuarios.getParticipante(login);
		ArrayList<Actividad> actividadesMiembro = coordinadorProyecto.actividadesMiembro(login);
		int tiempoTotal = coordinadorProyecto.tiempoTotal(actividadesMiembro);
		HashMap<String, Double> promedios = coordinadorProyecto.tiempoPorActividad(actividadesMiembro);
		
		
		new Reportes(participante, actividadesMiembro, tiempoTotal, promedios);		
	}

	
	public void generarCalendario() throws ParseException
	{
		Coordinador coordinadorProyecto = ventana.getCoordinadorProyecto();
		HashMap<String, ArrayList<Actividad>> Actividades = coordinadorProyecto.getActividades();
		String fechaInicio = coordinadorProyecto.getFechaInicio();
		Collection<ArrayList<Actividad>> values = Actividades.values();
		HashMap<String, Integer> fechas = new HashMap<String, Integer>();
		for (ArrayList<Actividad> list : values)
		{
			for (int i = 0; i <list.size(); i++)
			{
				Actividad Act = list.get(i);
				String fecha = Act.getFecha();
				if (fechas.containsKey(fecha))
				{
					fechas.put(fecha,  Integer.valueOf(fechas.get(fecha).intValue()+1));
				}
				else
				{
					fechas.put(fecha, Integer.valueOf(1));
				}
			}
		}
		new Calendario(fechas, fechaInicio);
	}


}
