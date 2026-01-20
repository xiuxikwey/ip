import java.util.Scanner;

public class Oliver {

    public static void event_handler() {
        try(Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                if (str.equals("bye")) {
                    sayGoodbye();
                } else {
                    speak(str);
                }
            }
        }
        
    }

    public static void speak(String input) {
        System.out.println("\u001B[31m"
            + input
            + "\u001B[0m");
    }

    public static void greet() {
        String drawing = """
         ___               ___
        (___\\  /~\\   /~\\  /___)
          (__\\ < O w O > /__)
              (   w  w  )
          o__/  mm   mm
        *****************************
        """;
        System.out.println(drawing);
        speak("Oliver, King Of The Night, at your service!");
    }

    public static void sayGoodbye() {
        String drawing = """
              A    A
            <  U w U >
              /    \\
          o__/      \\  
        *****************************
        """;
        System.out.println(drawing);
        speak("Goodbye!");
    }

    public static void main(String[] args) {
        greet();
        event_handler();
    }
}
