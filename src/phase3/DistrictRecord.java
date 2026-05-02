package recordClasses;

import sLinkedList.SLinkedList;

public class DistrictRecord implements Comparable<DistrictRecord> {
	
	private String districtName;
	private SLinkedList<String> locations;
	
	public DistrictRecord(String districtName) {
		
		this.districtName=districtName;
		this.locations=new SLinkedList<>();
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public SLinkedList<String> getLocations() {
		return locations;
	}

	public void setLocations(SLinkedList<String> locations) {
		this.locations = locations;
	}

	@Override
	public String toString() {
		return  ""+ districtName;
	}

	@Override
	public int compareTo(DistrictRecord o) {
		
		return this.districtName.toUpperCase().compareTo(o.districtName.toUpperCase());
	}
	
	
	public boolean equals(Object o) {
		
		if (o instanceof DistrictRecord) {
			
			return this.districtName.equalsIgnoreCase(((DistrictRecord)o).districtName);
		}
		
		return false;
	}

}
