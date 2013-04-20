package spaceappschallenge.moonville.businessmodels;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all buildings per level as of 
 * https://www.facebook.com/photo.php?fbid=10151316698507504
 * 
 * @author Felix
 *
 */
public class BuildingTree {
	String buildingName;
	List<Building> buildings = new ArrayList<Building>();
	List<BuildingTree> childs = new ArrayList<BuildingTree>();
	
	public BuildingTree() {
		
	}
	
	private BuildingTree(Building b) {
		buildings.add(b);
		buildingName = b.getName();
	}
	
	/**
	 * Insert a building into the tree. Only call this method on the root 
	 * node of a tree.
	 * 
	 * @return True if the building was inserted successfully.
	 */
	public boolean add(Building b) {
		if (b == null)
			return true;
		if (b.getName().equals(buildingName)) {
			buildings.add(b);
			return true;
		}
		else {
			for (BuildingTree bt : childs) {
				if (bt.add(b)) {
					return true;
				}
			}
		}
		// Make sure buildings are inserted at the correct level.
		if (buildingName.equals("Moon Base") && 
				(b.getName().equals("Solarpanel Array") || 
				b.getName().equals("Ice Mine") || 
				b.getName().equals("Regolith Processor"))) {
			childs.add(new BuildingTree(b));
			return true;
		}
		else if (buildingName.equals("Ice Mine") && 
				b.getName().equals("Water Processor")) {
			childs.add(new BuildingTree(b));	
			return true;		
		}
		else if (buildingName.equals("Water Processor") && 
				b.getName().equals("Propellant Factory")) {
			childs.add(new BuildingTree(b));
			return true;			
		}
		else if (buildingName.equals("Propellant Factory") && 
				b.getName().equals("Spaceport")) {
			childs.add(new BuildingTree(b));
			return true;			
		}
		else if (buildingName.equals("Regolith Processor") && 
				(b.getName().equals("Smelting Facility") || 
				b.getName().equals("Nuclear Plant"))) {
			childs.add(new BuildingTree(b));
			return true;			
		}
		else if (buildingName.equals("Smelting Facility") && 
				b.getName().equals("Electronics Factory")) {
			childs.add(new BuildingTree(b));
			return true;			
		}
		else if (buildingName.equals("Electronics Factory") && 
				(b.getName().equals("Robot Factory") || 
				b.getName().equals("Maglev Train Transport"))) {
			childs.add(new BuildingTree(b));	
			return true;		
		}
		else if (buildingName.equals("Robot Factory") && 
				b.getName().equals("Asteroid Defense")) {
			childs.add(new BuildingTree(b));	
			return true;		
		}
		else {
			// Either the building was not initially inserted in the tree 
			// root, or the cases above are incorrect.
			assert(false);
			return false;
		}
	}
	
	public List<Building> getBuildings() {
		return buildings;
	}
	
	public List<BuildingTree> getChilds() {
		return childs;
	}
	
	/**
	 * Gets the total power produced by all buildings, then does a 
	 * breadth-first traversal over the tree, powering buildings 
	 * near the root first, setting Building.hasPower accordingly.
	 * 
	 * This implementation does not consider resources, or required buildings, 
	 * which means a building may be powered while it does actually work.
	 */
	public void checkPower() {
		int power = computeTotalPowerOutput();
		List<BuildingTree> q = new ArrayList<BuildingTree>();
		q.add(this);
		while (!q.isEmpty()) {
			BuildingTree v = q.get(0);
			q.remove(v);
    		for (Building b : v.buildings) {
    			if (b.getInputPower() <= power) {
    				power -= b.getInputPower();
    				b.setHasPower(true);
    			}
    			else
    				b.setHasPower(false);
    		}
			for (BuildingTree c : v.childs)
				q.add(c);
		}
	}
	
	/**
	 * Recursively computes the total power generated by all buildings.
	 */
	private int computeTotalPowerOutput() {
		int power = 0;
		for (Building b : buildings)
			power += b.getOutputPower();
		for (BuildingTree c : childs)
			power += c.computeTotalPowerOutput();
		return power;		
	}
	
	/**
	 * Interface function for checkrequiredBuildings(boolean)
	 */
	public void checkRequiredBuildings() {
		// Moon Base has no required buildings.
		checkRequiredBuildings(false);
	}
	
	/**
	 * Checks if each building has its required buildings. This is true if 
	 * no higher node in the tree is empty.
	 * 
	 * @param isParentEmpty True if the parent node is empty. Means required 
	 * 						buildings are not available.
	 */
	private void checkRequiredBuildings(boolean isParentEmpty) {
		if (!isParentEmpty) {
			for (Building b : buildings)
				b.setHasRequiredBuildings(true);
			for (BuildingTree c : childs)
				c.checkRequiredBuildings(false);
		}
		else {
			for (Building b : buildings)
				b.setHasRequiredBuildings(false);
			for (BuildingTree c : childs)
				c.checkRequiredBuildings(true);
		}
	}
	
	/**
	 * Recursively checks how much resources are available in total, 
	 * considering resources used by buildings and non-working buildings.
	 * 
	 * @param resourceAvailable Previous amount of resources.
	 * @return List of resources that are available from buildings.
	 */
	public List<Resource> checkResources(List<Resource> resourceAvailable) {
		for (Building b : buildings) {
			if (!b.getHasPower() || !b.getHasRequiredBuildings())
				continue;
			List<Resource> oldAmount = resourceAvailable;
			for (Resource resourceNeed : b.getInputResources()) {
				if (subtractBuildingResources(resourceAvailable, resourceNeed)) {
					b.setHasResources(true);
					resourceAvailable = Resource.merge(resourceAvailable, 
							b.getOutputResources());				
				}
				else {
					// Reset resources so we don't remove part of the resources 
					// for a building that can't work.
					resourceAvailable = oldAmount;
					b.setHasResources(false);
				}
			}
		}
		for (BuildingTree c : childs) 
			Resource.merge(resourceAvailable, c.checkResources(resourceAvailable));
		return resourceAvailable;
	}

	/**
	 * If enough resources are available, those are removed from resource pool.
	 * 
	 * @return True if enough resources are available of each type.
	 */
	private boolean subtractBuildingResources(List<Resource> resourceAvailable,
			Resource resourceNeed) {
		for (Resource resourceHave : resourceAvailable) {
			if (resourceNeed.getName().equals(resourceHave.getName())) {
				if (resourceHave.getAmount() >= resourceNeed.getAmount())
					resourceHave.setAmount(resourceHave.getAmount() - 
							resourceNeed.getAmount());
				else
					return false;
			}
		}
		return true;
	}
}