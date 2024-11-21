package Map.Object;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a group of objects parsed from an XML document.
 *
 * <p>Parses and processes object groups from an XML {@link NodeList}, extracting
 * details such as name, trigger type, position, and dimensions. Each parsed object
 * is stored in a collection and can be rendered or queried.</p>
 */
public class ObjectGroup {

    /**
     * A collection of {@link Object} instances parsed from the XML document.
     */
    private final List<Object> objectCollection = new ArrayList<>();

    /**
     * Spawn point of the player, parsed from the object group.
     */
    private Point spawnPoint;

    /**
     * Constructs an {@code ObjectGroup} by parsing the provided {@link NodeList}.
     *
     * @param objectGroups a {@link NodeList} containing the object groups as XML elements.
     *
     *                     <p>Each object group should have a "name" attribute and contain "object" elements.
     *                     Each "object" element should have attributes such as "name", "triggerType", "x", "y",
     *                     "width", and "height".</p>
     */
    public ObjectGroup(NodeList objectGroups) {
        for (int i = 0; i < objectGroups.getLength(); i++) {
            Element objectGroupElement = (Element) objectGroups.item(i);

            // Retrieve all "object" elements within the group
            NodeList objectElements = objectGroupElement.getElementsByTagName("object");
            for (int j = 0; j < objectElements.getLength(); j++) {
                Element objectElement = (Element) objectElements.item(j);

                // Create an object from the XML element
                Object parsedObject = new Object(objectElement);

                // Set spawn point if the object represents a spawn location
                if (parsedObject.getClassType() == CLASS.SPAWN) {
                    setSpawnPoint(parsedObject.getPosition());
                }

                // Add the parsed object to the collection
                objectCollection.add(parsedObject);
            }
        }
    }

    /**
     * Sets the spawn point for the player.
     *
     * @param point The spawn point to set.
     */
    private void setSpawnPoint(Point point) {
        this.spawnPoint = point;
    }

    /**
     * Retrieves the spawn point for the player, if available.
     *
     * @return An {@link Optional} containing the spawn point, or empty if none exists.
     */
    public Point getSpawnPoint() {
        return spawnPoint;
    }

    /**
     * Retrieves the collection of parsed objects.
     *
     * @return A {@link List} containing the {@link Object} instances in this group.
     */
    public List<Object> getObjects() {
        return objectCollection;
    }

    /**
     * Renders all objects in this group.
     *
     * @param g       The {@link Graphics} context used for rendering.
     * @param xOffset The x-offset for rendering.
     * @param yOffset The y-offset for rendering.
     */
    public void render(Graphics g, int xOffset, int yOffset) {
        objectCollection.forEach(object -> object.render(g, xOffset, yOffset));
    }
}
