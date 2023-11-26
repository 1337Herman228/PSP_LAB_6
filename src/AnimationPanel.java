import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class AnimationPanel extends JPanel {

    private Car car;
    private Person person;
    private Tree tree;
    private boolean isAnimationRunning;

    public AnimationPanel() {
        setPreferredSize(new Dimension(600, 300));
        setBackground(Color.WHITE);

        // Создание объектов для анимации
        car = new Car();
        person = new Person();
        tree = new Tree();
        isAnimationRunning = false;
    }


    public Car getCar() {
        return car;
    }

    public Person getPerson() {
        return person;
    }

    public Tree getTree() {
        return tree;
    }

    public void stopAnimation() {
        isAnimationRunning = false;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Отрисовка объектов анимации
        car.draw(g);
        person.draw(g);
        tree.draw(g);
    }

    class Tree implements Runnable { ////////////////////////////////////////////
        private int height;
        private Image treeImage;

        public Tree() {
            height = 50;
            try {
                treeImage = ImageIO.read(new File("E:/PSP/lab6/tree.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void run() {
            isAnimationRunning = true;
            while (isAnimationRunning) {
                height += 3;
                if (height > 100) {
                    height = 50;
                }
                repaint();

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }

        public void draw(Graphics g) {
            g.drawImage(treeImage, 500, 150, 50, height-height*2, null);
        }
    }

    class Person implements Runnable { ////////////////////////////////////////////
        private int x;
        private Image personImage;

        public Person() {
            x = 0;
            try {
                personImage = ImageIO.read(new File("E:/PSP/lab6/men.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            isAnimationRunning = true;
            while (isAnimationRunning) {
                x += 2;
                if (x > getWidth()) {
                    x = -50;
                }
                repaint();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }

        public void draw(Graphics g) {
            g.drawImage(personImage, x, 130,90,60,null);
        }
    }

    class Car implements Runnable { ////////////////////////////////////////////
        private int x;
        private Image carImage;

        public Car() {
            x = 0;
            try {
                carImage = ImageIO.read(new File("E:/PSP/lab6/car.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            isAnimationRunning = true;
            while (isAnimationRunning) {
                x += 5;
                if (x > getWidth()) {
                    x = -100;
                }
                repaint();

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }


        public void draw(Graphics g) {
            g.drawImage(carImage, x, 200,100,50,null);
        }
    }

}

