package pruebas;

import modelo.Proyecto;
import modelo.Avances;
import modelo.WBS;
import procesamiento.DatosProyectos;

public class PruebaReporteAvance
{
	public static void main(String[] args)
	{
		DatosProyectos a = new DatosProyectos();
		Proyecto p = a.getProyecto("ProyectoPrueba1");
		WBS wbs = p.getWBS();
		
		int numPaquetes = wbs.getNumPaquetes();
		
		for (int i=0; i<numPaquetes; i++)
		{
			Avances avance = wbs.calcularAvancePaquete(i);
			System.out.println("Reporte Avance Paquete " + i);
			System.out.println("Tareas terminadas: " + avance.tareasTerminadas);
			System.out.println("Tareas terminadas a tiempo: " + avance.tareasTerminadasATiempo);
			System.out.println("Total de tareas: " + avance.totalTareas);
			System.out.println("Tiempo planeado terminadas: " + avance.tiempoPlaneadoTerminadas);
			System.out.println("Tareas planeado total: " + avance.tiempoPlaneadoTotal);
			System.out.println("\n");
		}
	}
}
