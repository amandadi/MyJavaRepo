package com.j8learning.lambda;

import java.util.Arrays;
import java.util.function.Function;

public class MethodReferences {

	public static void printFunction(Function<Object, String> func) {
		System.out.println(func);
	}

	public static void main(String[] args) {
		printFunction(String::valueOf);
		
		printFunction((x) -> {
			return String.valueOf(x);
		});

		printFunction(new Function<Object, String>() {

			@Override
			public String apply(Object t) {
				return String.valueOf(t);
			}

		});
		String[] stringArray = { "Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda" };
		Arrays.sort(stringArray, String::compareToIgnoreCase);
	}

}
