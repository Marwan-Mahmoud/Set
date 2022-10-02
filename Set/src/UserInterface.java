import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

	private static final int numLetters = 26;
	private int size = 0;
	private ArrayList<Set<String>> sets;
	Set<String> universe;

	public void CreateUserInterface() {
		Scanner scanner = new Scanner(System.in);
		String[] input;
		System.out.println("Enter input in format: {String1,String2,String3,...}.");

		universe = new Set<>();
		System.out.print("Universe = ");
		input = scanner.nextLine().replaceAll("\\{|\\}", "").split(",");
		for (String element : input)
			universe.add(element);

		System.out.print("Enter number of sets = ");
		int numSets = scanner.nextInt();
		scanner.nextLine();
		sets = new ArrayList<>(numLetters);

		System.out.println("You must enter elements that are in the \"Universe\" set.");
		for (int i = 0; i < numSets; i++) {
			System.out.print("Set " + indexToChar(i) + " = ");
			input = scanner.nextLine().replaceAll("\\{|\\}", "").split(",");
			Set<String> set = new Set<>();
			for (String element : input)
				set.add(element);
			if (set.isSubset(universe))
				updateSets(set);
			else
				i--;
		}

		while (true) {
			System.out.print("Enter expression: ");
			String expression = scanner.nextLine().toUpperCase();
			input = expression.split(" ");
			switch (input.length) {
			case 1:
				if (isExit(input[0])) {
					scanner.close();
					System.exit(0);
				} else if (isUniv(input[0]))
					System.out.println("Universe = " + universe);
				else if (validSet(input[0]))
					System.out.println(input[0] + " = " + sets.get(charToIndex(input[0].charAt(0))));
				else
					System.out.println("Error");
				break;
			case 2:
				if (input[0].charAt(0) == '!' && validSet(input[1])) {
					updateSets(sets.get(charToIndex(input[1].charAt(0))).complement(universe));
					int index = (size - 1) % numLetters;
					System.out.println(indexToChar(index) + " = " + expression + " = " + sets.get(index));
				} else if (input[0].charAt(0) == '!' && input[1].compareTo("UNIV") == 0) {
					updateSets(new Set<>());
					int index = (size - 1) % numLetters;
					System.out.println(indexToChar(index) + " = " + expression + " = " + sets.get(index));
				} else
					System.out.println("Error");
				break;
			case 3:
				Set<String> set1, set2;
				if (isOperator(input[1])) {
					char operator = input[1].charAt(0);
					if (validSets(input[0], input[2])) {
						set1 = getSet(input[0]);
						set2 = getSet(input[2]);
						operate(expression, set1, set2, operator);
					} else
						System.out.println("Error");
				} else if (input[1].charAt(0) == 'C') {
					if (validSets(input[0], input[2])) {
						set1 = getSet(input[0]);
						set2 = getSet(input[2]);
						System.out.println(set1.isSubset(set2));
					} else
						System.out.println("Error");
				} else
					System.out.println("Error");
				break;
			default:
				System.out.println("Error");
			}
		}
	}

	private boolean validSets(String set1, String set2) {
		if (!validSet(set1) && !isUniv(set1))
			return false;

		if (!validSet(set2) && !isUniv(set2))
			return false;

		return true;
	}

	private boolean validSet(String input) {
		if (input.length() != 1)
			return false;
		char c = input.charAt(0);
		return Character.isLetter(c) && charToIndex(c) < sets.size();
	}

	private Set<String> getSet(String input) {
		if (isUniv(input))
			return universe;
		return sets.get(charToIndex(input.charAt(0)));
	}

	private void operate(String expression, Set<String> set1, Set<String> set2, char operator) {
		operate(set1, operator, set2);
		int index = (size - 1) % numLetters;
		System.out.println(indexToChar(index) + " = " + expression + " = " + sets.get(index));
	}

	private void operate(Set<String> set1, char operator, Set<String> set2) {
		Set<String> result;
		switch (operator) {
		case 'U':
			result = set1.union(set2);
			updateSets(result);
			break;
		case 'N':
			result = set1.intersection(set2);
			updateSets(result);
			break;
		case '-':
			result = set1.difference(set2);
			updateSets(result);
		}
	}

	private void updateSets(Set<String> set) {
		if (size < numLetters)
			sets.add(set);
		else
			sets.set(size % numLetters, set);
		size++;
	}

	private boolean isOperator(String input) {
		char c = input.charAt(0);
		return c == 'U' || c == 'N' || c == '-';
	}

	private int charToIndex(char c) {
		return c - 'A';
	}

	private char indexToChar(int i) {
		return (char) (i + 'A');
	}

	private boolean isExit(String input) {
		return input.compareTo("EXIT") == 0;
	}

	private boolean isUniv(String input) {
		return input.compareTo("UNIV") == 0;
	}

	public static void main(String[] args) {
		new UserInterface().CreateUserInterface();
	}
}
