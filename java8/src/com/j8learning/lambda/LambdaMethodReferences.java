package com.j8learning.lambda;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class LambdaMethodReferences {

	public static void main(String[] args) {

		List<Person> roster = Person.createRoster();

		Person[] rosterAsArray = roster.toArray(new Person[roster.size()]);

		Arrays.sort(rosterAsArray, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.birthday.compareTo(o2.birthday);
			}
		});

		Arrays.sort(rosterAsArray, (a, b) -> {
			return a.birthday.compareTo(b.birthday);
		});

		Arrays.sort(rosterAsArray, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return Person.compareByAge(o1, o2);
			}
		});

		Arrays.sort(rosterAsArray, (a, b) -> Person.compareByAge(a, b));

		// Using static method reference in place of Lambda expression
		Arrays.sort(rosterAsArray, Person::compareByAge);

		// Using instance method reference in place of Lambda expression
		ComparisonProvider myComparisonProvider = new ComparisonProvider();
		Arrays.sort(rosterAsArray, myComparisonProvider::compareByName);

		// Reference to an Instance Method of an Arbitrary Object of a
		// Particular Type
		String[] stringArray = { "Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda" };
		Arrays.sort(stringArray, String::compareToIgnoreCase);

		// Reference to a Constructor
		Set<Person> rosterSetLambda = transferElements(roster, () -> {
			return new HashSet<>();
		});
		Set<Person> rosterSet = transferElements(roster, HashSet::new);
		Set<Person> rosterSetPerson = transferElements(roster, HashSet<Person>::new);
	}

	public static <SOURCE extends Collection<T>, DEST extends Collection<T>, T> DEST transferElements(
			SOURCE sourceCollection, Supplier<DEST> collectionFactory) {

		DEST result = collectionFactory.get();
		for (T t : sourceCollection) {
			result.add(t);
		}
		return result;
	}
}

class ComparisonProvider {
	public int compareByName(Person a, Person b) {
		return a.getName().compareTo(b.getName());
	}

	public int compareByAge(Person a, Person b) {
		return a.getBirthday().compareTo(b.getBirthday());
	}
}
