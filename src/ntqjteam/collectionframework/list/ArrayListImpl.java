package ntqjteam.collectionframework.list;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayListImpl {
	/**
	 * Reads a file and displays the words of that file as a list.
	 *
	 * 1. Display the words in reverse order.
	 * 
	 * 2. Display them with all plural words after removed character "e".
	 **/
	static List<String> words = new ArrayList<>();
	static int count = 0;

	// Reads a file and displays the words of that file as a list.
	static {
		System.out.println("Reads a file and displays the words of that file as a list.");
		try {
			Scanner scanner = new Scanner(new File("timetable.txt"));
			while (scanner.hasNext()) {
				words.add(scanner.next());
				count++;
				if (count == 1000)
					break;
			}
			scanner.close();
			count = 0;
			System.out.println(words);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Display the words in reverse order.
	static void reverseOrder(List<String> words) {
		System.out.println("Display the words in reverse order.");
		List<String> wordsReverse = Stream.generate(() -> {
			return words.get(words.size() - 1 - count++);
		}).limit(words.size()).collect(Collectors.toList());
		count = 0;
		System.out.println(wordsReverse);
	}

	// Display them with all plural words after removed character "e".
	static void getAllWordAfterRemoveCharStartWish(char c) {
		System.out.println("Display them with all plural words (starting in \"" + c + "\") removed.");
		List<String> removedWord = words.stream().map(s -> {
			if (s.toLowerCase().startsWith((c + "").toLowerCase())) {
				return s.substring(1, s.length() - 1);
			}
			return s;
		}).collect(Collectors.toList());
		System.out.println(removedWord);
	}
}
