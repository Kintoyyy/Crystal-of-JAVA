package Game;

import java.awt.*;
import javax.swing.JFrame;

/**
 * The Display class is responsible for creating and managing the game's window,
 * which includes setting up the frame and canvas for rendering. It handles the
 * visual aspects of the game display such as window size, title, and custom cursor.
 *
 * <p>This class provides methods to create and display the game window, and it
 * ensures the proper configuration of the canvas where the game content will
 * be drawn.</p>
 *
 * <h3>Key Responsibilities:</h3>
 * <ul>
 *     <li>Initializes and configures the JFrame (game window) with the title, size, and behavior.</li>
 *     <li>Creates a Canvas where the game content will be rendered.</li>
 *     <li>Sets up a custom cursor for the window (though this is currently commented out).</li>
 * </ul>
 *
 * <h3>Usage:</h3>
 * <p>Once an instance of this class is created, the game window is displayed, and the game can
 * start rendering to the Canvas.</p>
 */
public class Display {

    private JFrame frame;
    private Canvas canvas;

    private final String title;
    private final int width;
    private final int height;

    /**
     * Constructs a Display object with the specified title and dimensions.
     *
     * @param title The title to display on the game window.
     * @param width The width of the game window in pixels.
     * @param height The height of the game window in pixels.
     */
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    /**
     * Creates the JFrame and Canvas, and configures them with the provided
     * window dimensions and settings.
     */
    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);  // Center the window
        frame.setVisible(true);

        // Create and configure the canvas where the game will be rendered
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    /**
     * (Optional) Sets a custom cursor for the JFrame.
     * The cursor image should be provided as a file path.
     * Currently commented out in the code.
     *
     * @param frame The JFrame to which the custom cursor will be applied.
     */
    private void setCursor(JFrame frame) {
        Image cursorImage = Toolkit.getDefaultToolkit().getImage("path/to/your/cursor/image.png");
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "Custom Cursor");
        frame.setCursor(customCursor);
    }

    /**
     * Gets the Canvas object where the game will be rendered.
     *
     * @return The Canvas object.
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Gets the JFrame object representing the game window.
     *
     * @return The JFrame object.
     */
    public JFrame getFrame() {
        return frame;
    }
}
