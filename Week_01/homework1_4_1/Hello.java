
public class Hello {
    public static void main(String[] args) {
        new Hello().test(10);
    }

    private int test(int e) {
        int a = 1;
        int b = 2;
        int c = 3;
        int d = 4;
        if (a < e) {
            e = (a + b - c) * b / d;
            for (int i = 0; i < b; i++) {
                e++;
            }
        }
        return e;
    }
}
