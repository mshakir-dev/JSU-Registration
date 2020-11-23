package com.mshakir.jsu.stu.edu.JSURegistration;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<Course> sched = new ArrayList<>();
		List<Course> courses = readCoursesFromCSV("project-input.csv");
		boolean brakes = true;
		while (brakes != false) {
			System.out.println("1) Search Course");
			System.out.println("2) Register for Course");
			System.out.println("3) Remove Course from Schedule");
			System.out.println("4) View Trial Schedule");
			System.out.println("5) Quit");
			System.out.print("\nYour Choice? ");
			int choice = scan.nextInt();
			scan.nextLine();
			switch (choice) {
			case 1:
				System.out.print("Enter course number in the format SSNNN (for example, CS201): ");
				String lookup = scan.nextLine();
				System.out.println(lookup);
				for (Course c : courses) {
					if (c.getNum().replace(" ", "").equals(lookup))
						System.out.println(c);
					// 1 else
					// System.out.println("No courses found.\n");
				}
				break;
			case 2:
				System.out.print("Enter CRN number: ");
				int adding = scan.nextInt();
				Course temp = null;
				boolean conflict = false;
				for (Course c : courses) {
					if (c.getCRN() == adding) {
						temp = c;
					}
				}
				for (Course c : sched) {
					if (temp != null && temp.conflictsWith(c) == true)
						conflict = true;

				}
				if (conflict == true)
					System.out.println("ERROR: This course conflicts with your current schedule!\n");
				else if ((conflict == false || sched == null) && temp != null) {
					sched.add(temp);
					System.out.println("Course added successfully!\n");
				} else
					System.out.println("Course does not exist.\n");
				break;
			case 3:
				System.out.print("Enter CRN number: ");
				int removing = scan.nextInt();
				// For loop generated an expecption so iterator it is.
				Iterator<Course> iter = sched.iterator();

				while (iter.hasNext()) {
					Course c = iter.next();

					if (c.getCRN() == removing) {
						iter.remove();
						System.out.println("Course removed successfully!\n");
					}
				}
				break;
			case 4:
				// Check to see if schedule is empty
				boolean empty = true;
				for (Course o : sched) {
					if (o != null) {
						empty = false;
						break;
					}
				}
				if (empty == true)
					System.out.println("Schedule is empty.\n");
				// print 'em if not
				else
					sched.forEach((c) -> {
						System.out.println(c);
					});
				break;
			case 5:
				System.out.println("Thank you for using the Student Registration Program!\n");
				brakes = false;
				break;
			default:
				System.out.println("ERROR: Please enter a number between 1 and 5.\n");
			}

		}

	}

	private static Course createCourse(String[] data) {
		boolean check = useLoop(data, "World Wide Web");
		if (data.length == 7 && check == true) {
			return new Online(data[0], Integer.parseInt(data[1]), data[2], data[3], data[4], data[5], data[6]);
		} else if (data.length == 7 && check == false)
			return new Blended(data[0], Integer.parseInt(data[1]), data[2], data[3], data[4], data[5], data[6]);
		else if (data.length == 11 && check == false)
			return new Traditional(data[0], Integer.parseInt(data[1]), data[2], data[3], data[4], data[5], data[6],
					data[7], data[8], data[9], data[10]);
		else
			return null;
	}

	private static List<Course> readCoursesFromCSV(String fileName) {
		List<Course> courses = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			while (line != null) {
				String[] attributes = line.split("\t");

				Course course = createCourse(attributes);
				courses.add(course);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return courses;
	}

	public static boolean useLoop(String[] arr, String targetValue) {
		for (String s : arr) {
			if (s.equals(targetValue))
				return true;
		}
		return false;
	}
}