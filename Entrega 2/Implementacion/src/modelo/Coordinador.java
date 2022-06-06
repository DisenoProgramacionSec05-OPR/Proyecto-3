package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import procesamiento.DatosProyectos;

public class Coordinador
{
	private Proyecto proyectoEnUso;
	private DatosProyectos archivoProyectos = new DatosProyectos();
	private ReporteParticipante reporte = new ReporteParticipante();
	
	
	public void crearProyecto(String nombreProyecto, String descripcion, String fechaInicio,
							  String fechaFin, ArrayList<String> tiposActividades, Participante autor)
	{	

		
		proyectoEnUso = new CorAct(nombreProyecto, descripcion, fechaInicio,
											  fechaFin, tiposActividades, autor);
		archivoProyectos.guardarInfoProyecto(proyectoEnUso);
	}
	
	
	public void seleccionarProyecto(String nombreProyecto)
	{
		
		proyectoEnUso = archivoProyectos.getProyecto(nombreProyecto);
	}

	
	
	public String getNombreProyecto()
	{
		return proyectoEnUso.getNombre();
	}
	
	
	public String getDescripcionProyecto()
	{
		return proyectoEnUso.getDescripcion();
	}
	
	
	public String getFechaInicio()
	{
		return proyectoEnUso.getFechaInicio();
	}
	
	
	public String getFechaFin()
	{
		return proyectoEnUso.getFechaFin();
	}
	
	
	public ArrayList<String> getTiposActividades()
	{
		return proyectoEnUso.getTiposActividades();
	}
	
	
	public HashMap<String, Participante> getParticipantes()
	{
		return proyectoEnUso.getParticipantes();
	}
	
	
	public HashMap<String, ArrayList<Actividad>> getActividades()
	{
		return proyectoEnUso.getActividades();
	}
	
	
	public void agregarParticipante(Participante nuevoParticipante)
	{
		proyectoEnUso.agregarParticipante(nuevoParticipante);
		archivoProyectos.guardarInfoProyecto(proyectoEnUso);
	}
	
	
	public void registrarActividad(String tipoActividad, String titulo, String descripcion, String fecha,
								   String horaInicio, String horaFin, Participante participante, Tarea tarea)
	{
		try
		{
			String tituloProyecto = proyectoEnUso.getNombre();
			
			Actividad proxy1 = new ProxysReg(tituloProyecto, tipoActividad, titulo, descripcion,
												 fecha, horaInicio, horaFin, participante, false);
			Actividad proxy2 = new ProxysReg(tituloProyecto, tipoActividad, titulo, descripcion,
					 							 fecha, horaInicio, horaFin, participante, false);

			proyectoEnUso.registrarActividad(proxy1, proxy2, tarea);
			archivoProyectos.guardarInfoProyecto(proyectoEnUso);
		}
		
		catch (Exception e)
		{
		}
		
	}

	
	public void actualizarActividad(String titulo, int index, String nuevaFecha,
									String nuevaHoraInicio, String nuevaHoraFin)
	{
		proyectoEnUso.modificarFechaActividad(titulo, index, nuevaFecha);
		proyectoEnUso.modificarHoraInicio(titulo, index, nuevaHoraInicio);
		proyectoEnUso.modificarHoraFin(titulo, index, nuevaHoraFin);
		archivoProyectos.guardarInfoProyecto(proyectoEnUso);
	}
	

	
	public ArrayList<Actividad> actividadesMiembro(String loginParticipante)
	{
		HashMap<String, ArrayList<Actividad>> actividades = proyectoEnUso.getActividades();
		return reporte.actividadesMiembro(loginParticipante, actividades);
	}
	
	
	public int tiempoTotal(ArrayList<Actividad> actividadesPorMiembro)
	{
		return reporte.tiempoTotal(actividadesPorMiembro);
	}
	
	
	public HashMap<String, Double> tiempoPorActividad(ArrayList<Actividad> actividadesDelMiembro)
	{
		ArrayList<String> tiposDeActividades = getTiposActividades();
		return reporte.tiempoPorActividad(actividadesDelMiembro, tiposDeActividades);
	}
	
		
}
