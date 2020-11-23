package com.mshakir.jsu.stu.edu.JSURegistration;

class Blended extends Course {

	private String type;
	private String starttime = " ";

	public Blended(String n, int i, String num, String sec, String cred, String t, String prof) {
		super(n, i, num, sec, cred, prof);
		type = t;
	}

	@Override
	public String getStart() {
		return starttime;
	}

	@Override
	public boolean conflictsWith(Course c) {
		return false;
	}

	@Override
	public String toString() {
		return "#" + super.getCRN() + ":" + super.getNum() + "-" + super.getSec() + " " + "(" + super.getName() + "), "
				+ super.getProf() + ", " + type;
	}
}