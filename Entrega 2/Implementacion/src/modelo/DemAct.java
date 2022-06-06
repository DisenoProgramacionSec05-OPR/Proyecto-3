package modelo;

import java.util.HashMap;

public class DemAct
{
	private static DemAct singleton = null;
	private HashMap<String, Registro> actividades = new HashMap<String, Registro>();
	
	
	public static DemAct getInstance()
	{
		if (singleton == null)
		{
			singleton = new DemAct();
		}
		
		return singleton;
	}
	
	
	public void guardarRegistro(String idActividad, Registro registro)
	{
		actividades.put(idActividad, registro);
	}
	
	
	public Registro getRegistro(String idActividad)
	{
		return actividades.get(idActividad);
	}
}
