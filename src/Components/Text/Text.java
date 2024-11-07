package Components.Text;

import Components.Component;
import enums.Alignment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Text extends Component {

    private final String fullText;
    private String displayedText;
    private Alignment alignment = Alignment.LEFT;
    private Color color = Color.WHITE;
    private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
    private int currentIndex = 0;
    private int typingSpeed;
    private long lastTick;
    private boolean typingEffect = false;

    public Text(String text) {
        super(0, 0, 700, 0);
        this.fullText = text;
        this.displayedText = "";
        this.typingSpeed = 25;
        this.lastTick = System.currentTimeMillis();
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
        typing();
        this.typingSpeed = typingSpeed;
        return this;
    }

    public Text typing() {
        this.typingEffect = true;
        return this;
    }

    @Override
    public void tick() {
        if (typingEffect) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTick >= typingSpeed) {
                if (currentIndex < fullText.length()) {
                    displayedText += fullText.charAt(currentIndex);
                    currentIndex++;
                    lastTick = currentTime;
                }
            }
        } else {
            displayedText = fullText;
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
        updateBounds();

        int baseY = (int) (bounds.y + lineHeight);

        for (String line : lines) {
            g.drawString(line, (int) calculateHorizontalPosition(fm, line), baseY);
//            g.drawString(line, (int) calculateHorizontalPosition(fm, line), baseY);
            baseY += lineHeight;
        }
        // Draw bounds for debugging
        if(showBounds){
            g.setColor(Color.YELLOW);
            g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    @Override
    public void onClick() {
        // Custom action on click
    }

    private List<String> wrapText(FontMetrics fm, String text) {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (fm.stringWidth(currentLine + word) <= width) {
                currentLine.append(word).append(" ");
            } else {
                lines.add(currentLine.toString().trim());
                currentLine = new StringBuilder(word + " ");
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
            case CENTER, JUSTIFY -> bounds.x + (float) (bounds.width - lineWidth) / 2;
            case LEFT -> bounds.x;
            case RIGHT -> bounds.x + bounds.width - lineWidth;
        };
    }
}
