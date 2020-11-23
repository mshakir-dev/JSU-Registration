package com.mshakir.jsu.stu.edu.JSURegistration;

abstract class Course {
	private final String coursename;
	private final int crn;
	private final String course_num;
	private final String professorname;
	private final String sectionnum;
	private final String credithours;

	public Course(String n, int i, String num, String sec, String cred, String prof) {
		coursename = n;
		crn = i;
		course_num = num;
		professorname = prof;
		sectionnum = sec;
		credithours = cred;
	}

	public String getName() {
		return coursename;
	}

	public int getCRN() {
		return crn;
	}

	public String getNum() {
		return course_num;
	}

	public String getProf() {
		return professorname;
	}

	public String getSec() {
		return sectionnum;
	}

	public abstract boolean conflictsWith(Course c);

	public abstract String getStart();
}