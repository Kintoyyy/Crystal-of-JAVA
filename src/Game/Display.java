package Game;

import java.awt.*;

import javax.swing.JFrame;

public class Display {

    private JFrame frame;
    private Canvas canvas;

    private final String title;
    private final int width;
    private final int height;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        setCursor(frame);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    private void setCursor(JFrame frame) {
        Image cursorImage = Toolkit.getDefaultToolkit().getImage("path/to/your/cursor/image.png");
        // Create a custom cursor with the image and hotspot
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "Custom Cursor");
        // Set the custom cursor
        frame.setCursor(customCursor);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }
}
