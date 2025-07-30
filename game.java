import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Game extends JPanel implements KeyListener, Runnable {

    final int WIDTH = 800, HEIGHT = 500;

    Rectangle leftPaddle = new Rectangle(30, HEIGHT / 2 - 40, 10, 80);
    Rectangle rightPaddle = new Rectangle(WIDTH - 40, HEIGHT / 2 - 40, 10, 80);
    Rectangle ball = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 15, 15);

    int ballSpeedX = 5;
    int ballSpeedY = 2;

    boolean up1 = false, down1 = false;
    boolean up2 = false, down2 = false;

    int score1 = 0, score2 = 0;
    boolean gameOver = false;

    public Game() {
        JFrame frame = new JFrame("Ping Pong - Ana Paula :)");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(this);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(this);

        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);


        g.setColor(Color.WHITE);
        for (int i = 0; i < HEIGHT; i += 20)
            g.fillRect(WIDTH / 2 - 1, i, 2, 10);

        g.fillRect(leftPaddle.x, leftPaddle.y, leftPaddle.width, leftPaddle.height);
        g.fillRect(rightPaddle.x, rightPaddle.y, rightPaddle.width, rightPaddle.height);
        g.fillOval(ball.x, ball.y, ball.width, ball.height);

        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(score1 + "   " + score2, WIDTH / 2 - 40, 40);

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.setColor(Color.YELLOW);
            String winner = score1 == 5 ? "Jogador 1 Venceu!" : "Jogador 2 Venceu!";
            g.drawString(winner, WIDTH / 2 - 160, HEIGHT / 2);
        }
    }

    public void run() {
        while (true) {
            if (!gameOver) {

                if (up1 && leftPaddle.y > 0) leftPaddle.y -= 6;
                if (down1 && leftPaddle.y + leftPaddle.height < HEIGHT) leftPaddle.y += 6;
                if (up2 && rightPaddle.y > 0) rightPaddle.y -= 6;
                if (down2 && rightPaddle.y + rightPaddle.height < HEIGHT) rightPaddle.y += 6;

                ball.x += ballSpeedX;
                ball.y += ballSpeedY;

                if (ball.y <= 0 || ball.y + ball.height >= HEIGHT) ballSpeedY *= -1;

                if (ball.intersects(leftPaddle) || ball.intersects(rightPaddle)) {
                    ballSpeedX *= -1;
                }

                if (ball.x <= 0) {
                    score2++;
                    resetBall();
                } else if (ball.x >= WIDTH) {
                    score1++;
                    resetBall();
                }

                if (score1 == 5 || score2 == 5) {
                    gameOver = true;
                    repaint();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    resetGame();
                }
            }

            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void resetBall() {
        ball.x = WIDTH / 2 - 10;
        ball.y = HEIGHT / 2 - 10;
        Random rand = new Random();
        ballSpeedX = rand.nextBoolean() ? 5 : -5;

        do {
            ballSpeedY = rand.nextInt(5) - 2;
        } while (ballSpeedY == 0);
    }


    private void resetGame() {
        score1 = 0;
        score2 = 0;
        gameOver = false;
        resetBall();
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) up1 = true;
        if (code == KeyEvent.VK_S) down1 = true;
        if (code == KeyEvent.VK_UP) up2 = true;
        if (code == KeyEvent.VK_DOWN) down2 = true;
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) up1 = false;
        if (code == KeyEvent.VK_S) down1 = false;
        if (code == KeyEvent.VK_UP) up2 = false;
        if (code == KeyEvent.VK_DOWN) down2 = false;
    }

    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new Game();
    }
}
