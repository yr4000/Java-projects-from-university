package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear(Collection<Spaceship> fleet) {
		Comparator<Spaceship> c = new Comparator<Spaceship>(){ //an abstract class for comparator.
			public int compare(Spaceship arg0, Spaceship arg1){
				if(arg0.getFirePower() < arg1.getFirePower()) return 1;
				else if (arg0.getFirePower() > arg1.getFirePower()) return -1;
				else{
					if(arg0.getCommissionYear() < arg1.getCommissionYear()) return 1;
					else if(arg0.getCommissionYear() > arg1.getCommissionYear()) return -1;
					else return arg0.getName().compareTo(arg1.getName()); //TODO problem here with the getName,
																		// not in the right order. need to check again.
				}
			}
		};
		ArrayList<Spaceship> fleetList = new ArrayList<Spaceship>(fleet);
		Collections.sort(fleetList, c);
		ArrayList<String> stringShip = new ArrayList<String>();
		for(Spaceship ship: fleetList){
			stringShip.add(ship.toString());
		}
		return stringShip;
	}

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
		HashMap<String, Integer>  mapShip = new HashMap<String, Integer>();
		for(Spaceship ship: fleet){
			String key = ship.getClass().getSimpleName();
			if(mapShip.containsKey(key)) mapShip.put(key, mapShip.get(key)+1);
			else mapShip.put(key, 1);
		}
		return mapShip;
	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost (Collection<Spaceship> fleet) {
		int sum = 0;
		for(Spaceship ship: fleet) sum += ship.getAnnualMaintenanceCost();
		return sum;
	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		Set<String> weaponSet = new HashSet<String>();
		for(Spaceship ship: fleet){
			if(ship.getWeapon()==null) continue;
			for(Weapon wep: ship.getWeapon()){
				if(!weaponSet.contains(wep.getName())) weaponSet.add(wep.getName());
			}
		}
		return weaponSet;
	}

	/*
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		int numOfCrewMembers = 0;
		for(Spaceship ship: fleet) numOfCrewMembers += ship.getCrewMembers().size();
		return numOfCrewMembers;
		
	}

	/*
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
		int officers = 0;
		int ages = 0;
		for(Spaceship ship: fleet){
			for(CrewMember member: ship.getCrewMembers()){
				if(!member.getClass().getSimpleName().equals("Officer")) continue;
				officers++;
				ages+=member.getAge();
			}
		}
		return ((float)ages/(float)officers);
	}

	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
		Map<Officer, Spaceship> mapi = new HashMap<Officer, Spaceship>();
		for(Spaceship ship: fleet){
			Officer commander = null;
			OfficerRank highRank = OfficerRank.Ensign;
			List<Officer> officers = createOfficerList(ship.getCrewMembers());
			for(Officer officer: officers){
				if(officer.getRank().compareTo(highRank)>0){
					highRank = officer.getRank();
					commander = officer;
				}
			}
//			Comparator<Officer> c =  new Comparator<Officer>(){ // an anon comparator.
//				public int compare(Officer of1, Officer of2){
//					return of1.getRank().compareTo(of2.getRank());
//				}
//			};
//				Collections.sort(officers,c);
//				Collections.reverse(officers);
//				commander = officers.get(officers.size()-1);
				mapi.put(commander, ship);
			}
		return mapi;
	}

	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted descendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
		Map<OfficerRank, Integer> mapi = new HashMap<OfficerRank, Integer>();
		for(Spaceship ship: fleet){
			List<Officer> officers = createOfficerList(ship.getCrewMembers());
			for(Officer officer: officers){
				OfficerRank key = officer.getRank();
				if(!mapi.containsKey(key)) mapi.put(key, 1);
				else mapi.put(key, mapi.get(key)+1);
			}
		}
		List<Map.Entry<OfficerRank,Integer>> popuList = new ArrayList<Map.Entry<OfficerRank,Integer>>();
		Iterator<Entry<OfficerRank, Integer>> it = mapi.entrySet().iterator();
		while(it.hasNext()){
			popuList.add(it.next());
		}
		Collections.sort(popuList,new Comparator<Map.Entry<OfficerRank,Integer>>(){
			public int compare(Entry<OfficerRank, Integer> arg0, Entry<OfficerRank, Integer> arg1) {
				if(arg0.getValue()>arg1.getValue()) return 1;
				else if(arg0.getValue()<arg1.getValue()) return -1;
				else return 0;
			}
			
		});
		Collections.reverse(popuList);
		return popuList;
	}
	
	protected static List<Officer> createOfficerList(Set<CrewMember> crew){
		List<Officer> officers = new ArrayList<Officer>();
		for(CrewMember member: crew){
			if(member.getClass().getSimpleName().equals("Officer")){
				officers.add((Officer) member);
			}
		}
		return officers;
	}

}