package com.bloomerang.beans;

import java.util.List;

public class PersonOutLayout {

	List<BloomerangRecord> person;

	public List<BloomerangRecord> getOutRecords() {
		return person;
	}

	public void setOutRecords(List<BloomerangRecord> outRecords) {
		this.person = outRecords;
	}
}
