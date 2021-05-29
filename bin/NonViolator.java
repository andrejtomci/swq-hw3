public class NonViolator {

    public void lessVariables(String param) {

        int a = 1;
        int b = 2;
        int c = 3;
        int d = 4;

        for (;;) {
            for(;;){
                for(;;){
                    for(;;){
                        break;
                    }
                    break;
                }
                break;
            }
            break;
        }

        if (true) {}

        if (true) {}
        if (true) {}
        if (true) {}
        if (true) {}

        return;
    }

    public void lessNesting(int foo, int bar) {

        int a = 1;
        int b = 2;
        int c = 3;
        String d = "d";
        int e = 4;
        int f = 5;
        int g = 6;

        while (true) {
            break;
        }

        /* only 3 nesting depth*/
        while(true) {
            if (true) {
                if (true){
                }
            }
        }

        if (true) {}
        if (true) {}
        if (true) {}
        if (true) {}

    }

    public void lessLines(int a, int b, int c, int d, int e, int f, int g, int h, int j, int k) {
        for(;;){
            for(;;){
                for(;;){
                    for(;;)
                    for(;;){}
                }
            }
        }

    }
    
}
