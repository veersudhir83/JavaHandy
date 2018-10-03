package io.sudheer.practice.simple;

import java.io.Serializable;

public class OptedValueDTO implements Serializable{

	public OptedValueDTO(int id, String value, int rank, String xapiPrefType) {
		this.rank = rank;
		this.id = id;
		this.value = value;
		this.xapiPrefType = xapiPrefType;
	}

	private int rank;
	private int id;
	private String value;

	/**
	 * Applicable only for Category or Brand opted values
	 * preferenceType can hold "B", "E", "I" as values
	 * B -> when a preference value is part of Both explicit and implicit preferences
	 * E -> when a preference value is only part of Explicit preferences
	 * I -> when a preference value is only part of Implicit preferences
	 */
	private String xapiPrefType;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getXapiPrefType() {
		return xapiPrefType;
	}

	public void setXapiPrefType(String xapiPrefType) {
		this.xapiPrefType = xapiPrefType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		OptedValueDTO sample = (OptedValueDTO) o;
		return this.id == sample.id;
	}

	@Override
	public int hashCode() {
		return this.id;
	}
}
