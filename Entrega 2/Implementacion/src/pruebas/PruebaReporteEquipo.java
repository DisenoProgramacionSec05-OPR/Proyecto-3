package pruebas;

import modelo.Participante;
import modelo.Proyecto;
import modelo.DesEqp;
import modelo.WBS;
import procesamiento.DatosProyectos;

public class PruebaReporteEquipo
{
	public static void main(String[] args)
	{
		DatosProyectos a = new DatosProyectos();
		Proyecto p = a.getProyecto("ProyectoPrueba1");
		WBS wbs = p.getWBS();
		DesEqp equipo = wbs.calcularDesempenoEquipo();
		
		for (Participante participante : p.getParticipantes().values())
		{
			String nombre = participante.getNombre();
			
			int tiempoInvertido = equipo.tiempoInvertido.get(nombre);
			int tareasTerminadas = equipo.tareasTerminadas.get(nombre);
			int tareasPendientes = equipo.tareasPendientes.get(nombre);
			int tiempoInvertidoPendientes = equipo.tiempoInvertidoPendientes.get(nombre);
			int tiempoPlaneadoPendientes = equipo.tiempoPlaneadoPendientes.get(nombre);
			
			System.out.println("Reporte Desempeno de " + nombre);
			System.out.println("Tiempo total: " + tiempoInvertido + " minutos");
			System.out.println("Número de tareas terminadas: " + tareasTerminadas);
			System.out.println("Número de tareas pendientes: " + tareasPendientes);
			System.out.println("Tiempo invertido en tareas pendientes: " + tiempoInvertidoPendientes + " minutos");
			System.out.println("Tiempo planeado en tareas pendientes: " + tiempoPlaneadoPendientes + " minutos");
			System.out.println("\n");
		}
	}
}
