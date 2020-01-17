package io.javabrains;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;


//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("When running MathUtils")
class MathUtilsTest {
	
	MathUtils mathUtils;
	TestInfo testInfo; 
	TestReporter testReporter;
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		System.out.println("Running "+testInfo.getDisplayName() + " with tags "+testInfo.getTags());
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		
		mathUtils = new MathUtils();
	}
/*	
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This needs to run before all");
	}
	
	
	@AfterEach
	void cleanup() {
		System.out.println("Cleaning up ..");
	}
	*/
	
	@Nested
	@DisplayName("add method")
	@Tag("Math")
	class AddTest{
		@Test
		@DisplayName("when adding two positive numbers")
		void testAddPositive() {
			assertEquals(2, mathUtils.add(1, 1), "should return the right sum");
		}
		
		@Test
		@DisplayName("Testing add method for negative")
		void testAddNegative() {
			int expected = -2;
			int actual = mathUtils.add(-1, -1);
			assertEquals(expected, actual, () -> "should return the right sum "+expected + " but returned "+actual );
		}
	}

	@Test
	@DisplayName("multiply method")
	@Tag("Vitor")
	void testMultiply() {
		//System.out.println("Running "+testInfo.getDisplayName() + " with tags "+testInfo.getTags());
		//testReporter.publishEntry("Running "+testInfo.getDisplayName() + " with tags "+testInfo.getTags());
		assertAll(
				() -> assertEquals(4, mathUtils.multiply(2, 2)),
				() -> assertEquals(0, mathUtils.multiply(2, 0)),
				() -> assertEquals(-2, mathUtils.multiply(2, -1))
		);
	}

	
	/*
	@Test
	@DisplayName("Run Testing add method")
	void testAdd() {
		int expected = 2;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected, actual, "The add method should add two numbers");
	}
	*/
	
	@Test
	@EnabledOnOs(OS.LINUX)
	@Tag("Math")
	void testDivide() {
		boolean isServerUp = false;
		assumeTrue(isServerUp);
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "Divide by zero shoul throw");
	}
	
	
	//@Test
	@RepeatedTest(3)
	@Tag("Circle")
	void testComputeCircleRadius(RepetitionInfo repetitionInfo) {
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "Should return right circle area");
	}
	
	@Test
	@Disabled
	@DisplayName("TDD method, should not run")
	void testDisabled() {
		fail("This test should be disabled");
	}
}
