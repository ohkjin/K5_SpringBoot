package edu.pnu;

//@FunctionalInterface // 함수 추가 불가
interface TestInterface{
	public void test(String s);
}

interface TestInterfaceString{
	public String test(String s);
}


public class Program {

	public static void maintest(TestInterface ti) {
		ti.test("qwer");
		ti.test("qwer2");
	}
	
	public static void maintestString(TestInterfaceString ti) {
		ti.test("qwerst");
		ti.test("qwerst2");
	}
	public static void main(String[] args) {
		TestInterface timain = new TestInterface() {
			@Override
			public void test(String s) {
				System.out.println("Test:"+s);
			}

		};
		timain.test("abcd");
		
		maintest(s->System.out.println("Testing MainTest:"+s));
		maintestString(s->"Test return String:"+s);
		String test = "1234";
		
		TestInterface m= (t)->{
			// return type 이 void일시 필요없음
			System.out.println("=".repeat(10));
			System.out.println(t);
			};
		
		TestInterfaceString m2= (t)->{
			System.out.println("=".repeat(10));
			System.out.println(t);
			return "okay";
			};
		System.out.println(m2.test(test));
		m.test(test); // return이 okay, sysout은 호출될 시 실행
	}

}
