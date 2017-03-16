package ntqjteam.collectionframework.set;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import ntqjteam.collectionframework.map.Event;
import ntqjteam.collectionframework.map.HashMapImpl;

public class TreeSetImpl {
	static Map<String, Event> events = HashMapImpl.getEvents();

	/**
	 * Find and show all students who participated in the events.
	 * 
	 * Find and display all room is used in events.
	 **/

	// Find and show all students who participated in the events.
	static void findStudent() {
		List<String> students = new ArrayList<>();
		events.values().stream().map(t -> t.getStudents()).forEach(students::addAll);
		Set<String> setStudents = new TreeSet<>(students);
		System.out.println(setStudents);
	}
}
