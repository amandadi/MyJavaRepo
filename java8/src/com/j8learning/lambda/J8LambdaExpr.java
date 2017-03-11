package com.j8learning.lambda;

import java.util.ArrayList;
import java.util.List;

interface LogMessage {
	public void log(Object obj);
	default String getAcknowledgement(){return "";}
}

public class J8LambdaExpr {

	public static void main(String[] args) {
		J8LambdaExpr j8 = new J8LambdaExpr();
		
		LogMessage logUsingLambda = (Object logger) -> System.out.println(logger.toString());
		
		
		LogMessage logUsingAnonymousInner = new LogMessage() {
			
			@Override
			public void log(Object obj) {
				System.out.println(obj.toString());
				
			}
		};
		
		List<String> names = new ArrayList<String>();
		names.add("Amar");
		
		names.stream().filter(name -> {return true;});
		
		logUsingLambda.log(new String("Hi I am testing logging functionality with Lambda"));
		logUsingAnonymousInner.log(new String("Hi I am testing logging functionality with Anonymous Inner class"));
	}

}
