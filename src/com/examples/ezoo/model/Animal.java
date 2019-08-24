package com.examples.ezoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//@Entity(name="ANIMALS")
@Entity
@Table(name="ANIMALS")
public class Animal implements Comparable<Animal> {
	
	@Id 
	@NotNull 
	private Integer animalID = 0;
	
	@Column @NotEmpty(message = "name can't be empty") private String name = "";
	
	@Column @NotEmpty private String taxKingdom  = "";
	@Column @NotEmpty private String taxPhylum = "";
	@Column @NotEmpty private String taxClass = "";
	@Column @NotEmpty private String taxOrder = "";
	@Column @NotEmpty private String taxFamily = "";
	@Column @NotEmpty private String taxGenus = "";
	@Column @NotEmpty private String taxSpecies = "";
	
	@Column @NotNull private Double height = 0D;
	@Column @NotNull private Double weight = 0D;
	
	@Column @NotEmpty private String type = null;
	@Column @NotEmpty private String healthStatus = null;

	@Column(name="feeding_schedule", nullable = true) private Integer feedingScheduleID = null;		// may need to add other annotation since this is a FK
	
	public Animal() {
		/*this.animalID = 0;
		this.name = "";
		this.taxKingdom = "";
		this.taxPhylum = "";
		this.taxClass = "";
		this.taxOrder = "";
		this.taxFamily = "";
		this.taxGenus = "";
		this.taxSpecies = "";
		this.height = 0D;
		this.weight = 0D;
		this.type = "";
		this.healthStatus = "";*/
		// this.feedingScheduleID = null;
	}

	public Animal(Integer animalID, String name, String taxKingdom, String taxPhylum, String taxClass, String taxOrder,
			String taxFamily, String taxGenus, String taxSpecies, double height, double weight, String type,
			String healthStatus, int feedingScheduleID) {
		super();
		this.animalID = animalID;
		this.name = name;
		this.taxKingdom = taxKingdom;
		this.taxPhylum = taxPhylum;
		this.taxClass = taxClass;
		this.taxOrder = taxOrder;
		this.taxFamily = taxFamily;
		this.taxGenus = taxGenus;
		this.taxSpecies = taxSpecies;
		this.height = height;
		this.weight = weight;
		this.type = type;
		this.healthStatus = healthStatus;
		this.feedingScheduleID = feedingScheduleID;
	}

	public Integer getAnimalID() {
		return animalID;
	}

	public void setAnimalID(Integer animalID) {
		this.animalID = animalID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaxKingdom() {
		return taxKingdom;
	}

	public void setTaxKingdom(String taxKingdom) {
		this.taxKingdom = taxKingdom;
	}

	public String getTaxPhylum() {
		return taxPhylum;
	}

	public void setTaxPhylum(String taxPhylum) {
		this.taxPhylum = taxPhylum;
	}

	public String getTaxClass() {
		return taxClass;
	}

	public void setTaxClass(String taxClass) {
		this.taxClass = taxClass;
	}

	public String getTaxOrder() {
		return taxOrder;
	}

	public void setTaxOrder(String taxOrder) {
		this.taxOrder = taxOrder;
	}

	public String getTaxFamily() {
		return taxFamily;
	}

	public void setTaxFamily(String taxFamily) {
		this.taxFamily = taxFamily;
	}

	public String getTaxGenus() {
		return taxGenus;
	}

	public void setTaxGenus(String taxGenus) {
		this.taxGenus = taxGenus;
	}

	public String getTaxSpecies() {
		return taxSpecies;
	}

	public void setTaxSpecies(String taxSpecies) {
		this.taxSpecies = taxSpecies;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	public Integer getFeedingScheduleID() {
		return feedingScheduleID;
	}

	public void setFeedingScheduleID(Integer feedingScheduleID) {
		this.feedingScheduleID = feedingScheduleID;
	}

	@Override
	public String toString() {
		return "Animal [animalID=" + animalID + ", name=" + name + ", taxKingdom=" + taxKingdom + ", taxPhylum="
				+ taxPhylum + ", taxClass=" + taxClass + ", taxOrder=" + taxOrder + ", taxFamily=" + taxFamily
				+ ", taxGenus=" + taxGenus + ", taxSpecies=" + taxSpecies + ", height=" + height + ", weight=" + weight
				+ ", type=" + type + ", healthStatus=" + healthStatus + ", feedingScheduleID=" + feedingScheduleID + "]";
	}

	@Override
	public int compareTo(Animal o) {
		return (int) (this.animalID - o.getAnimalID());
	}

}
