package Map.Object;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a group of objects parsed from an XML document.
 *
 * <p>This class reads object groups from an XML {@link NodeList}, parses the
 * individual objects within each group, and stores them in a collection. It
 * is used to process and manage objects defined in map files or similar data
 * structures.</p>
 *
 * <p>The attributes of each object (such as name, triggerType, position, and dimensions)
 * are extracted and scaled by a constant factor during the parsing process.</p>
 */
public class ObjectGroup {

    /**
     * A collection of {@link Object} instances parsed from the XML document.
     */
    private final ArrayList<Object> objectCollection = new ArrayList<>();

    private Point pawnPoint;

    /**
     * Constructs an {@code ObjectGroup} by parsing the provided {@link NodeList}.
     *
     * @param objectGroups a {@link NodeList} containing the object groups as XML elements
     *
     *                     <p>Each object group should have a "name" attribute and contain "object" elements.
     *                     Each "object" element should have attributes such as "name", "triggerType", "x", "y",
     *                     "width", and "height".</p>
     *
     *                     <p>The width, height, x, and y values are scaled by a fixed multiplier (magic number)
     *                     during the parsing process.</p>
     */
    public ObjectGroup(NodeList objectGroups) {
        for (int i = 0; i < objectGroups.getLength(); i++) {
            Element objectGroupElement = (Element) objectGroups.item(i);

            // Retrieve the name of the object group
            String groupName = objectGroupElement.getAttribute("name");

            // Retrieve all "object" elements within the group
            NodeList objectElements = objectGroupElement.getElementsByTagName("object");
            for (int j = 0; j < objectElements.getLength(); j++) {
                Element objectElement = (Element) objectElements.item(j);
                // Add the parsed object to the collection



                objectCollection.add(new Object(objectElement));
            }
        }
    }

    private void setPawnPoint(Point point) {
        this.pawnPoint = point;
    }

    public Point getPawnPoint() {
        return pawnPoint;
    }


    /**
     * Retrieves the collection of parsed objects.
     *
     * @return an {@link ArrayList} containing the {@link Object} instances in this group
     */
    public ArrayList<Object> getObjects() {
        return objectCollection;
    }


}
