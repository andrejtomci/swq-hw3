
public class Violator {

    public void func(int foo, int bar) {

        int a = 1;
        int b = 2;
        int c = 3;
        String d = "d";
        int e = 4;
        int f = 5;
        int g = 6;

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
        return; 
    }

    public void bar() {
        
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

        while(true) {
            if (true) {
                if (true){
                    if (true) {

                    }
                }
            }
        }
    }
}

