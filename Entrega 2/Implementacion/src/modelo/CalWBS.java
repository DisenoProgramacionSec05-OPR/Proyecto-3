package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class CalWBS extends WBS
{
	
	public CalWBS(Proyecto elProyecto)
	{
		super(elProyecto);
	}
	
	
	public Avances calcularAvancePaquete(int indexPaquete)
	{
		Avances avance = new Avances();
		PqtTrabajo paquete = listaPaquetes.get(indexPaquete);
		completarAvancePaquete(paquete, avance);
		return avance;
	}
	
	
	public Planeacion calcularCalidadPlaneacion()
	{
		Planeacion calidad = new Planeacion();
		
		for (PqtTrabajo paquete : listaPaquetes)
		{
			ArrayList<Tarea> lasTareas = paquete.getTareas();
			calidad.totalTareas += lasTareas.size();
			
			for (Tarea tarea : lasTareas)
			{
				int tiempoReal = tarea.calcularTiempoReal();
				int tiempoPlaneado = tarea.getTiempoEstimado();
				
				calidad.tiempoReal += tiempoReal;
				calidad.tiempoPlaneado += tiempoPlaneado;
				
				if (tiempoReal <= tiempoPlaneado)
				{
					calidad.tareasCumplenTiempo++;
				}
			}
		}
		
		return calidad;
	}
	
	
	public DesEqp calcularDesempenoEquipo()
	{
		HashMap<String, Participante> miembrosEquipo = elProyecto.getParticipantes();
		DesEqp equipo = new DesEqp(miembrosEquipo);
		PqtTrabajo mainWBS = listaPaquetes.get(0);
		completarTiempoInvertido(equipo);
		completarDesempenoTareas(mainWBS, equipo);
		
		return equipo;
	}
	
	
	public ResumenProy calcularResumenProyecto()
	{
		PqtTrabajo mainWBS = listaPaquetes.get(0);
		ArrayList<String> tipos = elProyecto.getTiposActividades();
		ResumenProy resumen = new ResumenProy(tipos);		
		completarResumenPaquete(mainWBS, resumen);		
		resumen.avance = calcularAvancePaquete(0);
		
		return resumen;
	}
	
	
	private void completarAvancePaquete(PqtTrabajo paquete, Avances avance)
	{
		ArrayList<Tarea> lasTareas = paquete.getTareas();
		avance.totalTareas += lasTareas.size();
		
		for (Tarea tarea : lasTareas)
		{
			avance.tiempoPlaneadoTotal += tarea.getTiempoEstimado();
			
			if (tarea.isFinalizada())
			{
				avance.tareasTerminadas ++;
				avance.tiempoPlaneadoTerminadas += tarea.getTiempoEstimado();
				
				String fechaEstimadaFin = tarea.getFechaEstimadaFin();
				String fechaRealFin = tarea.getUltimaFechaProgreso();
				
				if (fechaCumple(fechaRealFin, fechaEstimadaFin))
				{
					avance.tareasTerminadasATiempo++;
				}
			}
		}
		
		for (Integer indexSubPaquete : paquete.getSubPaquetes())
		{
			PqtTrabajo subPaquete = listaPaquetes.get(indexSubPaquete);
			completarAvancePaquete(subPaquete, avance);
		}
	}
	
	
	private void completarTiempoInvertido(DesEqp equipo)
	{
		
		HashMap<String, ArrayList<Actividad>> lasActividades = elProyecto.getActividades();
		ArrayList<String> titulos = new ArrayList<String>(lasActividades.keySet());
		
		for (String titulo : titulos)
		{
			ArrayList<Actividad> homonimas = lasActividades.get(titulo);
			for (Actividad actividad : homonimas)
			{
				Participante autor = actividad.getAutor();
				int tiempo = equipo.tiempoInvertido.get(autor.getNombre());
				tiempo += actividad.getTiempo();
				
				equipo.tiempoInvertido.replace(autor.getNombre(), tiempo);
			}
		}
	}
	
	
	private void completarDesempenoTareas(PqtTrabajo paquete, DesEqp equipo)
	{
		
		ArrayList<Tarea> lasTareas = paquete.getTareas();
		
		for (Tarea tarea : lasTareas)
		{			
			if (!tarea.isFinalizada())
			{
				int tiempoInvertido = tarea.calcularTiempoReal();
				int tiempoPlaneado = tarea.getTiempoEstimado();
				
				for (Participante responsable : tarea.getResponsables())
				{
					int tareasPendientes = equipo.tareasPendientes.get(responsable.getNombre());
					int tiempoInvertidoPendientes = equipo.tiempoInvertidoPendientes.get(responsable.getNombre());
					int tiempoPlaneadoPendientes = equipo.tiempoPlaneadoPendientes.get(responsable.getNombre());
					
					tareasPendientes++;
					tiempoInvertidoPendientes += tiempoInvertido;
					tiempoPlaneadoPendientes += tiempoPlaneado;
					
					equipo.tareasPendientes.replace(responsable.getNombre(), tareasPendientes);
					equipo.tiempoInvertidoPendientes.replace(responsable.getNombre(), tiempoInvertidoPendientes);
					equipo.tiempoPlaneadoPendientes.replace(responsable.getNombre(), tiempoPlaneadoPendientes);
				}
			}
			else
			{
				for (Participante responsable : tarea.getResponsables())
				{
					int tareasTerminadas = equipo.tareasTerminadas.get(responsable.getNombre());
					equipo.tareasTerminadas.replace(responsable.getNombre(), tareasTerminadas + 1);
				}
			}
		}
		
		//Repetir para los subpaquetes
		for (Integer indexSubPaquete : paquete.getSubPaquetes())
		{
			PqtTrabajo subPaquete = listaPaquetes.get(indexSubPaquete);
			completarDesempenoTareas(subPaquete, equipo);
		}
	}
	
	
	private void completarResumenPaquete(PqtTrabajo paquete, ResumenProy resumen)
	{
		ArrayList<Tarea> lasTareas = paquete.getTareas();
		
		for (Tarea tarea : lasTareas)
		{
			String tipo = tarea.getTipoTarea();
			int tiempoTipo = resumen.tiempoPorTipo.get(tipo);
			tiempoTipo += tarea.calcularTiempoReal();
			resumen.tiempoPorTipo.replace(tipo, tiempoTipo);
			
			if (!tarea.isFinalizada())
			{
				int pendientesTipo = resumen.pendientesPorTipo.get(tipo);
				resumen.pendientesPorTipo.replace(tipo, pendientesTipo + 1);
			}
		}
		
		//Repetir para los subpaquetes
		for (Integer indexSubPaquete : paquete.getSubPaquetes())
		{
			PqtTrabajo subPaquete = listaPaquetes.get(indexSubPaquete);
			completarResumenPaquete(subPaquete, resumen);
		}
	}
	
	
	private boolean fechaCumple(String fechaReal, String fechaEstimada)
	{
	
		Integer anoReal = Integer.parseInt(fechaReal.substring(6));
		Integer anoEstimado = Integer.parseInt(fechaEstimada.substring(6));
		
		if (anoReal < anoEstimado)
		{
			return true;
		}
		
		else if (anoReal > anoEstimado)
		{
			return false;
		}
		
		else
		{
			Integer mesReal = Integer.parseInt(fechaReal.substring(3,5));
			Integer mesEstimado = Integer.parseInt(fechaEstimada.substring(3,5));
			
			if (mesReal < mesEstimado)
			{
				return true;
			}
			
			else if (mesReal > mesEstimado)
			{
				return false;
			}
			
			else
			{
				Integer diaReal = Integer.parseInt(fechaReal.substring(0,2));
				Integer diaEstimado = Integer.parseInt(fechaEstimada.substring(0,2));
				
				if (diaReal <= diaEstimado)
				{
					return true;
				}
				
				else
				{
					return false;
				}
			}
		}		
	}
	
	
}
