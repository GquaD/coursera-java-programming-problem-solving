package CourseraJavaProgramming.week1;

import CourseraJavaProgramming.edu.duke.*;

public class HelloWorld {
	public void runHello () {
		FileResource res = new FileResource("hello_unicode.txt");
		for (String line : res.lines()) {
			System.out.println(line);
		}
	}
}
