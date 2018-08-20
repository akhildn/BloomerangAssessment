package com.bloomerang.util;

public class ParserProperties {

	public static final String TITLE_PATTERN = "MR\\.|MRS\\.|DR\\.|Mr\\.|Mrs\\.|Dr\\.|mr\\.|mrs\\.|dr\\.";
	public static final String SUFFIX_PATTERN = "JR\\.|SR\\.|Jr\\.|Sr\\.|jr\\.|sr\\.";
	public static final String EXCLUDE_PATTERN = "\\bAnd\\b|\\band\\b|\\bAND\\b|\\&\\b";
	public static final String EXTN_PATTERN = "\\bX\\b|\\bEXT\\b";
	public static final String dataPath = "data/";
	public static final String INBOUND_FILE_NAME = "persons.csv";
	public static final String OUTBOUND_FILE_NAME = "persons_out.csv";
	public static final String INBOUND_MAPPING = "person-inbound_mapping.xml";
	public static final String OUTBOUND_MAPPING = "person-outbound_mapping.xml";
}
