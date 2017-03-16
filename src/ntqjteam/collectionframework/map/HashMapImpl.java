package ntqjteam.collectionframework.map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapImpl {
	private static Map<String, Event> events = new HashMap<>();

	static {
		File file = new File("timetable.txt");
		try {
			FileReader fr = new FileReader(file);
			String st;
			LineNumberReader lineNumberReader = new LineNumberReader(fr);
			while ((st = lineNumberReader.readLine()) != null) {
				Event time = new Event();
				String data[] = st.split("\\t");
				time.setId(data[0]);
				time.setModule(data[1]);
				time.setEtype(data[2]);
				time.setDay(data[3]);
				time.setStart(data[4]);
				time.setDuration(Integer.parseInt(data[5].split(":")[0]));
				time.setWeeks(data[6]);
				time.setRoom(data[7]);
				time.setStaff(data[8]);
				ArrayList<String> students = new ArrayList<String>();
				int i = 9;
				while (i < data.length) {
					students.add(data[i++]);
				}
				time.setStudents(students);
				events.put(time.getId(), time);
			}
			lineNumberReader.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, Event> getEvents() {
		return events;
	}

	/**
	 * Find the percentage of events that start at 9:00 for each of the three
	 * different school at Merchiston. Modules for each of the school use the
	 * following prefixes
	 *
	 * School of Computing: CSN IMD INF SET SOC
	 * 
	 * Engineering and Built Environment: BSV CTR ELE MEC
	 * 
	 * Arts and Creative Industries: CLP DES JAC LMD MUS SCA
	 **/
	static void findPercentageOfEventWithModule(List<Module> modules) {
		Map<String, Long> percentOfEvent = new HashMap<>();
		List<String> strings = events.values().stream().filter(t -> t.getStart().equals("9:00")).map(Event::getModule)
				.map(t -> {
					for (Module m : modules) {
						for (String s : m.getPrefix()) {
							if (t.toLowerCase().contains(s.toLowerCase())) {
								return m.getSchool();
							}
						}
					}
					return t;
				}).collect(toList());
		percentOfEvent = strings.stream().collect(groupingBy(t -> t.toString(), counting()));
		for (Module m : modules) {
			System.out.printf("%s: %.2f%%\r\n", m.getSchool(),
					(float) percentOfEvent.get(m.getSchool()) / strings.size() * 100);
		}
	}

	/**
	 * Print the timetable for group Co.Cc1f-B
	 * 
	 * Show the events in a grid - Monday to Friday, 9:00 to 17:00. Show the
	 * module number and the room for each event. You may shorten the room names
	 * if you can apply a consistent string replace function. (For example
	 * replace "Merch." with the empty string).
	 **/

	static void printTimetableByStudent(String student) {
		Map<String, List<Event>> timetables = new HashMap<>();
		timetables = events.values().stream()
				.filter(t -> Integer.parseInt(t.getStart().split(":")[0]) >= 9
						&& Integer.parseInt(t.getStart().split(":")[0]) <= 17)
				.filter(t -> t.getStudents().contains(student)).collect(groupingBy(Event::getDay, toList()));
		for (String s : timetables.keySet()) {
			System.out.println(s.toUpperCase());
			timetables.get(s).forEach(t -> {
				System.out.println(t.getModule() + ": start at " + t.getStart() + " in " + t.getRoom());
			});
		}
	}

	public static void main(String[] args) {
		List<Module> modules = new ArrayList<>();
		modules.add(new Module("School of Computing", new String[] { "CSN", "IMD", "INF", "SET", "SOC" }));
		modules.add(new Module("Engineering and Built Environment", new String[] { "BSV", "CTR", "ELE", "MEC" }));
		modules.add(
				new Module("Arts and Creative Industries", new String[] { "CLP", "DES", "JAC", "LMD", "MUS", "SCA" }));
		findPercentageOfEventWithModule(modules);
		System.out.println("\r\n=========================================\r\n");
		printTimetableByStudent("Co.Cc1f-B");
	}
}
