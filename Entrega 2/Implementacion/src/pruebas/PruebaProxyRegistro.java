package pruebas;

import modelo.Actividad;
import modelo.Participante;
import modelo.ProxysReg;

public class PruebaProxyRegistro
{
	public static void main(String[] args)
	{
		Participante autor1 = new Participante("login1", "nombre1");
		Actividad a1 = new ProxysReg("P1", "tipo1", "A1", "descA1", "30/05/2022",
					       			"08:00", "09:00", autor1, false);
	
		Participante autor2 = new Participante("login2", "nombre2");
		Actividad a2 = new ProxysReg("P1", "tipo2", "A2", "descA2", "31/05/2022",
					       			"08:00", "11:00", autor2, false);
	
		Actividad a1Copy = new ProxysReg("P1", "tipo3", "A1", "descA1", "30/05/2022",
       									"08:00", "09:00", autor1, false);
	
	
		//Probar
		System.out.println(a1Copy.getFecha());
		a1.setFecha("nueva fecha");
		System.out.println(a1.getFecha());
		System.out.println(a1Copy.getFecha());
		
	}
}
