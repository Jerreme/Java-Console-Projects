package projects.pythagoreantheorem;

import java.util.Scanner;

public class PythagoreanTheorem {
    // formulas for pythagorean theorem
    // a2 = (c2 - b2) then root
    // b2 = (c2 - a2) then root
    // c2 = (a2 + b2) then roor
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("``` Welcome to Pythagorean Theorem Calculator ```");
        System.out.print("Enter opposite number [a]: ");
        String a = scanner.next();
        System.out.print("Enter adjacent number [b]: ");
        String b = scanner.next();
        System.out.print("Enter hypotenuse number [c]: ");
        String c = scanner.next();

        double answer;
        int a2, b2, c2;
        if (a.equals("?")) {
            b2 = Integer.parseInt(b) * Integer.parseInt(b);
            c2 = Integer.parseInt(c) * Integer.parseInt(c);
            a2 = c2 - b2;
            answer = Math.sqrt(a2);
            System.out.println("The opposite side is " + answer);
        } else if (b.equals("?")) {
            a2 = Integer.parseInt(a) * Integer.parseInt(a);
            c2 = Integer.parseInt(c) * Integer.parseInt(c);
            b2 = c2 - a2;
            answer = Math.sqrt(b2);
            System.out.println("The adjacent side is " + answer);
        } else if (c.equals("?")) {
            a2 = Integer.parseInt(a) * Integer.parseInt(a);
            b2 = Integer.parseInt(b) * Integer.parseInt(b);
            c2 = a2 + b2;
            answer = Math.sqrt(c2);
            System.out.println("The hypotenuse side is " + answer);
        } else {
            System.out.println("Invalid input");
        }
    }
}
