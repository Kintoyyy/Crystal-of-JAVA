package Components.Text;

import Components.UIComponent;
import Components.enums.Alignment;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Text extends UIComponent {

    private String fullText;
    private String displayedText = "";
    private boolean isTypingComplete = false;
    private Alignment alignment = Alignment.LEFT;
    private Color color = Color.WHITE;
    private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
    private int currentIndex = 0;
    private int typingSpeed = 25;
    private long lastTick = System.currentTimeMillis();
    private boolean typingEffect = false;
    private String text;

    public Text(String text) {
        super();
        this.width = 700;  // Increased width to accommodate more text
        this.fullText = text;
        this.displayedText = text;  // Initialize displayed text immediately
    }

    public Text setFont(Font font) {
        this.font = font;
        return this;
    }

    public Text setAlignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public Text setColor(Color color) {
        this.color = color;
        return this;
    }

    public Text typing(int typingSpeed) {
        this.typingEffect = true;
        this.typingSpeed = typingSpeed;
        return this;
    }

    public Text typing() {
        this.typingEffect = true;
        return this;
    }

    public boolean isTypingComplete() {
        return !typingEffect || currentIndex >= fullText.length();
    }

    public void resetTyping() {
        currentIndex = 0;
        displayedText = "";
        lastTick = System.currentTimeMillis();
    }

    public String getDisplayedText() {
        return displayedText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setTypingSpeed(int speed) {
        this.typingSpeed = speed;
    }

    @Override
    public void tick() {
        if (typingEffect && System.currentTimeMillis() - lastTick >= typingSpeed) {
            if (currentIndex < fullText.length()) {
                displayedText += fullText.charAt(currentIndex++);
                lastTick = System.currentTimeMillis();
                isTypingComplete = (currentIndex >= fullText.length());
            }
        } else if (!typingEffect) {
            displayedText = fullText;  // Show full text immediately if typing effect is disabled
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(this.color);
        g.setFont(font);
        
        FontMetrics fm = g.getFontMetrics();
        List<String> lines = wrapText(fm, displayedText);
        int lineHeight = fm.getHeight();

        this.height = lineHeight * lines.size();

        int baseY = this.y + lineHeight;

        for (String line : lines) {
            g.drawString(line, (int) calculateHorizontalPosition(fm, line), baseY);
            baseY += lineHeight;
        }

        // Show bounds for debugging
        if (showBounds) {
//            g.setColor(Color.YELLOW);
//            g.drawRect(this.x, this.y, this.width, this.height);
        }
    }

    @Override
    public void onClick(MouseEvent e) {
        // Define click action here if needed
    }

    private List<String> wrapText(FontMetrics fm, String text) {
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : text.split(" ")) {
            if (fm.stringWidth(currentLine + word) <= width) {
                currentLine.append(word).append(" ");
            } else {
                lines.add(currentLine.toString().trim());
                currentLine = new StringBuilder(word).append(" ");
            }
        }

        if (!currentLine.isEmpty()) {
            lines.add(currentLine.toString().trim());
        }

        return lines;
    }

    private float calculateHorizontalPosition(FontMetrics fm, String line) {
        int lineWidth = fm.stringWidth(line);
        return switch (alignment) {
            case CENTER, JUSTIFY -> this.x + (this.width - lineWidth) / 2f;
            case LEFT -> this.x;
            case RIGHT -> this.x + this.width - lineWidth;
        };
    }

    public void setText(String text) {
        this.fullText = text;
        if (typingEffect) resetTyping();
    }
}
