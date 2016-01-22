package innlevering1;

import java.util.Scanner;

public class LeiebilApp {
	private static Utleier utleier = new Utleier();
	
	public LeiebilApp () {
		utleier = new Utleier();
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < 10; i++) {
			System.out.println("Kunde 1: ");
			Kunde kunde = new Kunde(utleier, in.nextLine());
			new Thread(kunde).start();
		}
		in.close();
	}
}
