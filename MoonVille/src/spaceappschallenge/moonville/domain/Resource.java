package spaceappschallenge.moonville.domain;

import java.io.Serializable;

public class Resource implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9104023380104564668L;
	private String name;
	private int importPrice;
	private int exportPrice;
	private int weight;
	private long quantity;
	
	public Resource(String name){
		this.name = name;
		this.importPrice = this.exportPrice = this.weight = 0;
		this.quantity = 0;
	}
	
	public Resource(String name, int importPrice, int exportPrice, int weight){
		this.name = name;
		this.importPrice = importPrice;
		this.exportPrice = exportPrice;
		this.weight = weight;
		this.quantity = 0;
	}
	
	public Resource(String name, long quantity, int importPrice, int exportPrice, int weight){
		this.name = name;
		this.importPrice = importPrice;
		this.exportPrice = exportPrice;
		this.weight = weight;
		this.quantity = quantity;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getImportPrice()
	{
		return this.importPrice;
	}
	
	public int getExportPrice()
	{
		return this.exportPrice;
	}

	public int getWeight() {
		return weight;
	}

	public long getQuantity() {
		return this.quantity;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setImportPrice(int importPrice) {
		this.importPrice = importPrice;
	}

	public void setExportPrice(int exportPrice) {
		this.exportPrice = exportPrice;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
}
