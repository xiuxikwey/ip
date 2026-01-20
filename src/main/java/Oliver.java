public class Oliver {

    public static void greet() {
        String drawing = """
         ___               ___
        (___\\  /~\\   /~\\  /___)
          (__\\ < O w O > /__)
              (   w  w  )
          o__/  mm   mm
        *****************************
        """;
        System.out.println(drawing
            + "\u001B[31m"
            + "Oliver, King Of The Night, at your service!\n"
            + "\u001B[0m"
        );
    }

    public static void sayGoodbye() {
        String drawing = """
              A    A
            <  U w U >
              /    \\
          o__/      \\  
        *****************************
        """;
        System.out.println(drawing 
            + "\u001B[31m"
            + "Goodbye!"
            + "\u001B[0m");
    }

    public static void main(String[] args) {
    greet();
    sayGoodbye();
    }
}
