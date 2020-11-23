package com.mshakir.jsu.stu.edu.JSURegistration;

class Traditional extends Course {
	private String starttime;
	private String stoptime;
	private String meetDays;
	private String roomLocation;
	private String type;

	public Traditional(String n, int i, String num, String sec, String cred, String start, String stop, String meet,
			String t, String room, String prof) {
		super(n, i, num, sec, cred, prof);
		starttime = start;
		stoptime = stop;
		meetDays = meet;
		roomLocation = room;
		type = t;
	}

	@Override
	public String getStart() {
		return starttime;
	}

	public String getStop() {
		return stoptime;
	}

	public String getMeet() {
		return meetDays;
	}

	public String getRoom() {
		return roomLocation;
	}

	@Override
	public boolean conflictsWith(Course c) {

		return starttime.equals(c.getStart());
	}

	@Override
	public String toString() {
		return "#" + super.getCRN() + ":" + super.getNum() + "-" + super.getSec() + " " + "(" + super.getName() + "),"
				+ super.getProf() + ", Lecture, " + starttime + "-" + stoptime + ", " + meetDays + ", " + roomLocation;
	}

}