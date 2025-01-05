package concurrente;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrincipalS {

	public static void main(String[] args) {
		Persona p1 = new Persona("Homer", 40);
		Persona p2 = new Persona("Marge", 39);
		Persona p3 = new Persona("Bart", 11);
		Persona p4 = new Persona("Lisa", 9);
		Persona p5 = new Persona("Maggie", 1);
		
		ArrayList<Persona> personas = new ArrayList<Persona>();
		
		personas.add(p1);
		personas.add(p2);
		personas.add(p3);
		personas.add(p4);
		personas.add(p5);
		
//filter
		/*Stream<Persona> adultos =
				personas.stream()
						.filter(p -> p.getEdad() >= 18);
		Iterator iter = adultos.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}*/
		
//map
		/*Stream<Integer> edades =
				personas.stream()
						.map(p -> p.getEdad());
		
		Iterator iter = edades.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next()+ " ");
		}*/
		
//filter & map	
		/*Stream<Integer> edadesadultos =
				personas.stream()
						.filter(p -> p.getEdad() >= 18)
						.map(p -> p.getEdad());
		
		Iterator iter = edadesadultos.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next()+ " ");
		}*/
		
//collect
		/*List<Integer> edadesadultos =
				personas.stream()
						.filter(p -> p.getEdad() >= 18)
						.map(p -> p.getEdad())
						.collect(Collectors.toList());
		
		for(int i=0; i<edadesadultos.size(); i++) {
			System.out.print(edadesadultos.get(i)+ " ");
		}*/
		
//collect joining
		/*String nombresAdultos =
				personas.stream()
						.filter(p -> p.getEdad() >= 18)
						.map(p -> p.getNombre())
						.collect(Collectors.joining(",","Adultos: ","."));
		System.out.println(nombresAdultos);*/
		
//Foreach
		/*//String nombleAdultos =
				personas.stream()
						.filter(p -> p.getEdad() >= 18)
						.map(p -> p.getNombre())
						.forEach(p -> System.out.println(p));*/
	}

}
