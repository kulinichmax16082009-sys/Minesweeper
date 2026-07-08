public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.generateBoard(Difficulty.HARD);
        System.out.println(board);

        IntroducingWindow introducingWindow = new IntroducingWindow();
    }
}