package week5.flightplanner;

import acm.program.ConsoleProgram;
import java.util.*;

import java.io.*;


public class FlightPlanner extends ConsoleProgram {
	private HashMap<String,ArrayList> place= new HashMap<String,ArrayList>(); //stores starting place as key and destinations from each as value
	public void run() {
		String filename ="C:/Users/moni0/eclipse-meritamerica-workspace/Stanford106AAssignmentStarters/Stanford106AAssignments/src/week5/Flights.txt";
		try {
			BufferedReader rd= new BufferedReader(new FileReader(filename));
			System.out.println("Welcome to Flight Planner! \n");
			System.out.println("Here's the list of all the cities in our database: \n");
			String line;
			ArrayList<String> dest = new ArrayList<String>();
			dest=null;
			while((line = rd.readLine())!=null){				
				String city = line.substring(0,line.indexOf("-")).trim();// separates left side i.e. the starting point in each line
				String d = line.substring(line.indexOf(">")+1).trim();	// separates the right side i.e.the destination point in each line
				
				if(place.containsKey(city)) {		
					dest = place.get(city);                   // gets the corresponding values to the key------ city
					dest.add(d);							 // adds the right substring to the value corresponding to the key which is present already
				} else {
					place.put(city,destination(d));
					}
			}
			rd.close();
		} catch (IOException e) {
			System.out.println("File not found!");
		}
		
		//System.out.println(place);
		
		System.out.println(place.keySet());
		System.out.println("Let's plan a round-trip route!");
		ArrayList<String> route = new ArrayList<String>(); //to track the route of travel
		while(true) {
			String start = readLine("Enter the starting city: ");
			String beginning = start;						
			if (place.containsKey(start)){
				System.out.println("From "+start+" you can fly directly to:");
				String st =String.join("\n",place.get(start)); //to align one below the other .join() function is used
				System.out.println(st);
				
				while(true) {
					String next = readLine("Where do you want to go from "+start+" ?");
					if(next.equals(beginning)) {
						System.out.println("The route you've chosen is:");
						System.out.println(String.join("->", route)+"->"+start+"->"+next); //to align with "->" as separator, .join is used
						break;
					}
					if(place.containsKey(next)) {
						System.out.println("From "+next+" you can fly directly to:");
						String s =String.join("\n",place.get(next));
						System.out.println(s);
						String temp= null;
						temp=start;
						route.add(temp);                  //to keep track of the route travelled
						start=next;
						next=temp;
					} else {
						System.out.println("You can't get to that city by a direct flight.");
					}
						
				}
			} else {
				System.out.println("No match "+ start + " in " + place);
			}
		}
	}
	
	public ArrayList destination(String name) {
		
		ArrayList<String> destin= new ArrayList<String>();
		destin.add(name);
		return destin;
	}
}
