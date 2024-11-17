package Components.Button;

import Components.Component;
import Components.Text.Text;
import Utils.CallBackAction;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import Components.enums.Alignment;
import fonts.SuperPixelFont;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Components.enums.States.PRESSED;
/**
 * The Button class represents a clickable button component with different states (idle, hovered, pressed).
 * It renders a button with customizable text, images for each state, and supports interaction via callback actions.
 * The button is drawn using a sprite sheet, where different button images correspond to different states.
 * The text appearance changes dynamically based on the button's state (e.g., position, color).
 *
 * <p>This class extends the {@link Component} class and provides functionality for state management, rendering,
 * and click handling.</p>
 *
 * <p>Usage Example:</p>
 * <pre>
 * Button button = new Button("Click Me")
 *     .setAction(() -> System.out.println("Button clicked!"))
 *     .hideText()
 *     .setActive(true);
 * </pre>
 *
 * @see Component
 * @see CallBackAction
 * @see Text
 */
public class Button extends Component {

    /**
     * Array to hold button images for different states: idle, hovered, and pressed.
     */
    protected BufferedImage[] buttonSheet = new BufferedImage[3];

    /**
     * Sprite sheet used to load button images.
     */
    protected SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Menu_UI.png"));

    /**
     * Callback action to be executed when the button is clicked.
     */
    private CallBackAction clicker;

    /**
     * Flag to determine if the text should be hidden.
     */
    private boolean hideText = false;

    /**
     * The text displayed on the button.
     */
    private final Text text;

    /**
     * Creates a new button with the specified text.
     *
     * @param textContent The text to display on the button.
     */
    public Button(String textContent) {
        super();

        this.width = 200;
        this.height = 60;

        buttonSheet[0] = sheet.crop(0, 0, 47, 14); // Idle state image
        buttonSheet[1] = sheet.crop(48, 0, 47, 14); // Hovered state image
        buttonSheet[2] = sheet.crop(96, 0, 47, 14); // Pressed state image

        this.text = (Text) new Text(textContent)
                .setFont(new SuperPixelFont(30))
                .setAlignment(Alignment.CENTER)
                .setColor(new Color(184, 111, 80))
                .setParent(this)
                .setDimensions(this.width, this.height);
    }

    /**
     * Sets custom button images for the different states.
     *
     * @param buttonSheet An array of BufferedImage objects representing the button states.
     * @return The current Button instance.
     */
    public Button setButtonSheet(BufferedImage[] buttonSheet) {
        this.buttonSheet = buttonSheet;
        return this;
    }

    /**
     * Sets a callback action to be executed when the button is clicked.
     *
     * @param clicker The callback action.
     * @return The current Button instance.
     */
    public Button setAction(CallBackAction clicker) {
        this.clicker = clicker;
        return this;
    }

    /**
     * Hides the text on the button when set to true.
     *
     * @return The current Button instance.
     */
    public Button hideText() {
        this.hideText = true;
        return this;
    }

    /**
     * Sets the button's active state.
     * If the button is active, the state is set to PRESSED.
     *
     * @param isActive True if the button should be active, false otherwise.
     */
    public void setActive(boolean isActive) {
        this.state = isActive ? PRESSED : this.state;
    }

    /**
     * Updates the button's text and checks if it needs to be ticked.
     */
    @Override
    public void tick() {
        text.tick();
    }

    /**
     * Renders the button to the screen, including the button's image and text (if visible).
     * The appearance of the text and button image changes based on the button's state.
     *
     * @param g The Graphics object used for rendering.
     */
    @Override
    public void render(Graphics g) {
        // Choose image based on the current button state
        BufferedImage buttonImage = switch (state) {
            case HOVERED -> buttonSheet[1];
            case PRESSED -> buttonSheet[2];
            default -> buttonSheet[0];
        };

        if (buttonImage != null) {
            g.drawImage(buttonImage, bounds.x, bounds.y, width, height, null);
        }

        if (!hideText) {
            updateTextAppearance();
            text.render(g);
        }

        if (showBounds) {
            g.setColor(Color.BLUE);
            g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    /**
     * Executes the click action, if defined. If no action is set, a warning is printed.
     */
    @Override
    public void onClick() {
        if (clicker != null) {
            clicker.onAction();
        } else {
            System.out.println("Warning: No action defined for button click.");
        }
    }

    /**
     * Updates the appearance of the text based on the current button state.
     * The text's location and color are modified depending on whether the button is hovered or pressed.
     */
    private void updateTextAppearance() {
        switch (state) {
            case HOVERED -> text.setLocation(0, 4);
            case PRESSED -> {
                text.setLocation(0, 8);
                text.setColor(new Color(116, 63, 57));
            }
            default -> {
                text.setLocation(0, 0);
                text.setColor(new Color(184, 111, 80));
            }
        }
        text.setDimensions(bounds.width, bounds.height);
    }
}
