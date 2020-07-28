package sean.yu.simplesnake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-01 23:15
 **/

//绘制面板，绘制游戏画面
public class GamePanel extends JPanel implements ActionListener {

    private int length;//蛇身长度
    private int[] snakeX = new int[500];
    private int[] snakeY = new int[500];
    private String dir;//蛇头方向
    private boolean isStarted;//游戏是否开始
    private boolean isFailed; //游戏是否失败


    private Timer timer = new Timer(100, this);//定时器

    //食物的坐标
    private int foodX;
    private int foodY;
    private Random random;

    /**
     * 代表上次移动的方向
     * 因为初始蛇头向右，所以这里设置为向右。
     * 这个字段的意义在于，不能前后2个方向若相反，则后按的方向键不生效
     * 比如正在向右的蛇不能立即向左走
     */
    private String lastDir = "R";

    private int score;

    public GamePanel() {
        init();
        this.setFocusable(true);//设置获得焦点事件
        this.addKeyListener(new SnakeKeyListener());
    }

    public void init() {
        length = 3;
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
        dir = "R";
        isStarted = false;
        isFailed = false;
        random = new Random();
        foodX = 25;
        foodY = 75;
        score = 0;
        timer.start();
    }

    //画组件
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //清屏，可以尝试注掉此行代码看效果
        this.setBackground(Color.WHITE);

        Data.header.paintIcon(this, g, 25, 11); //广告栏
        g.fillRect(25, 75, 850, 600); //游戏框

        //画蛇头
        switch (dir) {
            case "U":
                Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "D":
                Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "L":
                Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "R":
                Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
        }

        //画蛇身
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        //画食物
        Data.food.paintIcon(this, g, foodX, foodY);

        //画积分
        g.setColor(Color.BLUE);
        g.setFont(new Font("yahei", Font.BOLD, 20));
        g.drawString("长度" + length,750, 35);
        g.drawString("分数" + score,750, 50);

        if (!isStarted) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("yahei", Font.BOLD, 40));
            g.drawString("按下空格开始游戏", 300, 300);
        }

        if (isFailed) {
            g.setColor(Color.RED);
            g.setFont(new Font("yahei", Font.BOLD, 40));
            g.drawString("游戏失败，按下空格重新开始游戏", 300, 300);
        }
    }

    //事件监听器搜集触发的各种事件
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStarted && !isFailed) {
            //吃食物
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                length++;
                score += 10;
                //再次生成食物
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + 25 * random.nextInt(24);
                System.out.println(String.format("新的坐标%s%s", foodX, foodY));
            }

            //移动
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }

            switch (dir) {
                case "U": {
                    snakeY[0] -= 25;
                    if (snakeY[0] < 75) {
                        snakeY[0] = 650;
                    }
                    break;
                }
                case "D": {
                    snakeY[0] += 25;
                    if (snakeY[0] > 650) {
                        snakeY[0] = 75;
                    }
                    break;
                }
                case "L": {
                    snakeX[0] -= 25;
                    if (snakeX[0] < 25) {
                        snakeX[0] = 850;
                    }
                    break;
                }
                case "R": {
                    snakeX[0] += 25;
                    if (snakeX[0] > 850) {
                        snakeX[0] = 25;
                    }
                    break;
                }
            }

            //失败条件：撞到自己
            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFailed = true;
                }
            }

            repaint();
        }
    }

    //按键监听器监听按键
    private class SnakeKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE: {
                    if (isFailed) {
                        isFailed = false;
                        init();
                    } else {
                        isStarted = !isStarted;
                        repaint();
                    }
                    break;
                }
                case KeyEvent.VK_UP: {
                    if (!"D".equals(lastDir)) {
                        dir = "U";
                        lastDir = "U";
                    }
                    break;
                }
                case KeyEvent.VK_DOWN: {
                    if (!"U".equals(lastDir)) {
                        dir = "D";
                        lastDir = "D";
                    }
                    break;
                }
                case KeyEvent.VK_LEFT: {
                    if (!"R".equals(lastDir)) {
                        dir = "L";
                        lastDir = "L";
                    }
                    break;
                }
                case KeyEvent.VK_RIGHT: {
                    if (!"L".equals(lastDir)) {
                        dir = "R";
                        lastDir = "R";
                    }
                    break;
                }
            }
        }
    }
}
