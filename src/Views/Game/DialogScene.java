package Views.Game;

import Components.Button.Button;
import Components.ComponentEventListener;
import Components.Text.Text;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import Views.View;
import Views.enums.Views;
import fonts.SimplePixelFont;
import fonts.SuperPixelFont;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DialogScene extends View {
    private int dialogIndex = 0;
    private final ArrayList<Dialog> preGameDialogs = new ArrayList<>();
    private final Text text;
    private BufferedImage currentBackgroundImage;
    private final BufferedImage dialogImage;

    public DialogScene() {
        text = (Text) new Text("Long ago, in the Kingdom of Java, unparalleled prosperity and harmony flourished. The secret to this peace was an ancient artifact—the Crystal of Java—believed to be a divine gift from the gods. This sacred crystal maintained the balance of nature, time, and reality, symbolizing light, hope, and life for the people.")
                .setFont(new SimplePixelFont(16))
                .setColor(new Color(116, 63, 58))
                .setLocation(150, 560)
                .showBounds();

        SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Game_UI.png"));
        dialogImage = sheet.crop(0, 45, 183, 46);

        // Initialize dialogs with text and corresponding background
        preGameDialogs.add(new Dialog("Long ago, in the Kingdom of Java, unparalleled prosperity and harmony flourished. The secret to this peace was an ancient artifact—the Crystal of Java—believed to be a divine gift from the gods. This sacred crystal maintained the balance of nature, time, and reality, symbolizing light, hope, and life for the people.", "/Backgrounds/1.png"));
        preGameDialogs.add(new Dialog("One fateful night, the dark sorcerer Zarok, once a trusted scholar, stole the Crystal of Java. With its power, he plunged the kingdom into chaos. Day turned to night, time became disjointed, and nature's laws unraveled. Twisted creatures emerged, terrorizing citizens and corrupting the land.", "/Backgrounds/3.png"));
        preGameDialogs.add(new Dialog("The Crystal of Java was the kingdom’s lifeblood, its magic beyond comprehension. Without it, the world began to collapse. Yet, amidst the darkness, hope endured. A brave group of heroes, united by fate, set out to retrieve the stolen Crystal, defeat Zarok, and restore balance to the Kingdom of Java.", "/Backgrounds/4.png"));
        preGameDialogs.add(new Dialog("Once a blacksmith’s apprentice, Kent Rato took up his father’s sword to defend his village when the Crystal was stolen. A man of action and courage, Kent sees the Crystal as a sacred object that must be protected. His journey will soon challenge his understanding of the world’s balance. For now, he focuses on reclaiming what was lost.", "/Backgrounds/5.png"));
        preGameDialogs.add(new Dialog("Cedi Castro, a mage from a distant land, came to Java seeking knowledge. He believed in magic's mysteries but discovered the Crystal was tied to something deeper. While Java's people saw only the surface, Cedi viewed it as profound magic. He believes returning it will restore ancient spells, but soon realizes there's more beneath the surface than he imagined.", "/Backgrounds/6.png"));
        preGameDialogs.add(new Dialog("Nathan Archival, a ranger from the highlands, sees the Crystal of Java as a natural force maintaining balance. Its theft threw the ecosystem into chaos. He'll soon learn the Crystal's true power affects more than nature alone. His connection to the land might reveal the key to restoring harmony", "/Backgrounds/7.png"));
        preGameDialogs.add(new Dialog("Zeith, once a healer, joined the quest to heal the land and its people after the Crystal was stolen. He views it as a holy relic, the source of all life. As he travels through corrupted lands, Zeith realizes the world's illness runs deeper than physical wounds.", "/Backgrounds/8.png"));
        preGameDialogs.add(new Dialog("After weeks of travel, the heroes entered the twisted Dark Forest. Once tranquil, it was now gnarled and corrupted. Trees were warped, and leaves flickered like broken code, as if reality itself was faltering.", "/Backgrounds/9.png"));

        // Load initial background
        currentBackgroundImage = ImageUtils.loadImage(preGameDialogs.get(0).backgroundPath);

        components.addComponents(
                new Button("Next")
                        .setEventListener(new ComponentEventListener() {
                            @Override
                            public void onComponentClick(MouseEvent event) {
                                if (dialogIndex < preGameDialogs.size() - 1) {
                                    dialogIndex++;
                                    updateCurrentDialog();
                                } else {
                                    // All dialogs displayed, switch to GAME view
                                    viewManager.setView(Views.GAME);
                                }
                            }

                            @Override
                            public void onMouseEnter(MouseEvent event) {}

                            @Override
                            public void onMouseExit(MouseEvent event) {}
                        })
                        .setLocation(780, 720)
        );
    }

    @Override
    public void tick() {
        text.tick();
        components.tick();
    }

    @Override
    public void render(Graphics g) {
        // Render background
        if (currentBackgroundImage != null) {
            g.drawImage(currentBackgroundImage, 0, 0, handler.getWidth(), handler.getHeight(), null);
        }

        // Render dialog box
        g.drawImage((Image) dialogImage, (int) (text.getX() - 20), (int) (text.getY() - 20), 183 * 4, 46 * 4, null);

        // Render text and components
        text.render(g);
        components.render(g);
    }

    @Override
    public void setData(Object data) {}

    /**
     * Updates the current dialog text and background based on the current index.
     */
    private void updateCurrentDialog() {
        if (dialogIndex >= 0 && dialogIndex < preGameDialogs.size()) {
            Dialog currentDialog = preGameDialogs.get(dialogIndex);
            text.setText(currentDialog.text);
            currentBackgroundImage = ImageUtils.loadImage(currentDialog.backgroundPath);
        }
    }

    /**
     * Adds a new dialog with associated text and background.
     *
     * @param text The dialog text.
     * @param backgroundPath The path to the background image for the dialog.
     */
    public void addDialog(String text, String backgroundPath) {
        preGameDialogs.add(new Dialog(text, backgroundPath));
    }

    // Inner class to represent a dialog with text and background
    private static class Dialog {
        String text;
        String backgroundPath;

        public Dialog(String text, String backgroundPath) {
            this.text = text;
            this.backgroundPath = backgroundPath;
        }
    }
}
