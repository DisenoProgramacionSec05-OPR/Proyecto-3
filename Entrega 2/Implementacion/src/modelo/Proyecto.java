package modelo;

import java.util.ArrayList;
import java.util.HashMap;


public abstract class Proyecto
{
	private String nombreProyecto;
	private String descripcion;
	private String fechaInicio;
	private String fechaFin;
	private ArrayList<String> tiposTareas;
	private WBS wbs;
	
	private HashMap<String, Participante> participantes = new HashMap<String, Participante>();
	protected HashMap<String, ArrayList<Actividad>> actividades = new HashMap<String, ArrayList<Actividad>>();
	
	
	public Proyecto(String nombreProyecto, String descripcion, String fechaInicio,
					String fechaFin, ArrayList<String> tiposTareas, Participante autor)
	{
		this.nombreProyecto = nombreProyecto;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tiposTareas = tiposTareas;
		this.wbs = new CalWBS(this);
		
		agregarParticipante(autor);
	}
	
	
	public WBS getWBS()
	{
		return wbs;
	}
	
	
	public String getNombre()
	{
		return nombreProyecto;
	}


	public String getDescripcion()
	{
		return descripcion;
	}
	
	
	public String getFechaInicio()
	{
		return fechaInicio;
	}
	
	
	public String getFechaFin()
	{
		return fechaFin;
	}


	public ArrayList<String> getTiposActividades()
	{
		ArrayList<String> tiposCopia = new ArrayList<String>(tiposTareas);
		return tiposCopia;
	}


	public HashMap<String, Participante> getParticipantes()
	{
		HashMap<String, Participante> participantesCopia = new HashMap<String, Participante>(participantes);
		return participantesCopia;
	}


	public HashMap<String, ArrayList<Actividad>> getActividades() 
	{
		HashMap<String, ArrayList<Actividad>> actividadesCopia = new HashMap<String, ArrayList<Actividad>>(actividades);
		return actividadesCopia;
	}
	
	
	public void agregarParticipante(Participante nuevoParticipante)
	{
		String login = nuevoParticipante.getLogin();
		participantes.put(login, nuevoParticipante);
	}
	
	
	public abstract void registrarActividad(Actividad proxy1, Actividad proxy2, Tarea tarea) throws Exception;
	
	public abstract void modificarFechaActividad(String titulo, int index, String nuevaFecha);

	public abstract void modificarHoraInicio(String titulo, int index, String nuevaHoraInicio);
	
	public abstract void modificarHoraFin(String titulo, int index, String nuevaHoraFin);
	

}
