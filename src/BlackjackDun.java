public class BlackjackDun {
    public static void main(String[] args) throws Exception {
        Fight f = new Fight(10, 4);

        for (int i = 0; i < 100; i++) {
            f.draw();
        }

    }
}
