package Map.Object;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Object {
    private final String name;
    private final CLASS CLASS;
    private final Point position;
    private final int width;
    private final int height;
    private final int id;
    private final Type type;
    private final List<Properties> properties = new ArrayList<>();
    private int[] polygonX = new int[0];
    private int[] polygonY = new int[0];

    // Constants
    private static final int SCALING_FACTOR = 4;

    /**
     * Constructor for parsing object element data.
     *
     * @param objectElement The XML element representing the object.
     */
    public Object(Element objectElement) {

        this.name = objectElement.hasAttribute("name") ?
                objectElement.getAttribute("name").toUpperCase().replace(" ", "_") :
                objectElement.getAttribute("id");

        this.id = Integer.parseInt(objectElement.getAttribute("id"));

        this.position = new Point(
                parseScaledAttribute(objectElement, "x"),
                parseScaledAttribute(objectElement, "y")
        );

        this.width = parseScaledAttribute(objectElement, "width");
        this.height = parseScaledAttribute(objectElement, "height");

        this.type = determineType(objectElement);
        this.CLASS = parseTriggerType(objectElement);
        parseProperties(objectElement);
    }

    /**
     * Parse and scale a float attribute from the XML element.
     *
     * @param element   The XML element.
     * @param attribute The attribute name.
     * @return The scaled integer value.
     */
    private int parseScaledAttribute(Element element, String attribute) {
        String value = element.getAttribute(attribute);
        return value.isEmpty() ? 0 : (int) (Float.parseFloat(value) * SCALING_FACTOR);
    }

    /**
     * Determine the object type based on XML tags and dimensions.
     *
     * @param objectElement The XML element.
     * @return The determined object type.
     */
    private Type determineType(Element objectElement) {


        if (width > 0 && height > 0) {
            if (hasChildTag(objectElement, "ellipse")) {
                return Type.ELLIPSE;
            } else {
                return Type.RECTANGLE;
            }
        } else if (hasChildTag(objectElement, "point")) {
            return Type.POINT;
        } else if (hasChildTag(objectElement, "polygon")) {
            parsePolygon(objectElement);
            return Type.POLYGON;
        }
        return Type.NONE;
    }

    /**
     * Check if the XML element has a specific child tag.
     *
     * @param element The XML element.
     * @param tag     The tag name.
     * @return True if the child tag exists, false otherwise.
     */
    private boolean hasChildTag(Element element, String tag) {
        return element.getElementsByTagName(tag).getLength() > 0;
    }

    /**
     * Parse polygon points from the XML element and apply scaling.
     *
     * @param objectElement The XML element containing the polygon data.
     */
    private void parsePolygon(Element objectElement) {
        String points = objectElement.getElementsByTagName("polygon")
                .item(0)
                .getAttributes()
                .getNamedItem("points")
                .getNodeValue();

        String[] pointPairs = points.split(" ");
        polygonX = new int[pointPairs.length];
        polygonY = new int[pointPairs.length];

        for (int i = 0; i < pointPairs.length; i++) {
            String[] coords = pointPairs[i].split(",");
            polygonX[i] = (int) (position.x + Float.parseFloat(coords[0]) * SCALING_FACTOR);
            polygonY[i] = (int) (position.y + Float.parseFloat(coords[1]) * SCALING_FACTOR);
        }
    }

    /**
     * Parse the trigger type from the XML element. Defaults to NONE if not found.
     *
     * @param objectElement The XML element.
     * @return The parsed CLASS.
     */
    private CLASS parseTriggerType(Element objectElement) {
        String type = objectElement.getAttribute("type");
        try {
            return type.isEmpty() ? CLASS.NONE : CLASS.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CLASS.NONE;
        }
    }

    /**
     * Parse properties from the XML element.
     *
     * @param objectElement The XML element containing properties.
     */
    private void parseProperties(Element objectElement) {
        NodeList propertiesList = objectElement.getElementsByTagName("properties");
        for (int i = 0; i < propertiesList.getLength(); i++) {
            Element propertyElement = (Element) propertiesList.item(i);
            NodeList propertyNodes = propertyElement.getElementsByTagName("property");
            for (int j = 0; j < propertyNodes.getLength(); j++) {
                Element property = (Element) propertyNodes.item(j);
                properties.add(new Properties(
                        property.getAttribute("name"),
                        property.getAttribute("value"),
                        property.getAttribute("type")
                ));
            }
        }
    }

    /**
     * Render the object using the provided Graphics object, adjusting for offsets.
     *
     * @param g       The Graphics object to draw with.
     * @param xOffset The horizontal offset.
     * @param yOffset The vertical offset.
     */
    public void render(Graphics g, int xOffset, int yOffset) {
        // Debugging only
        g.setColor(Color.RED);
        g.drawString(CLASS + "-" + name + "-" + type, position.x - xOffset, position.y - yOffset);
        switch (type) {
            case RECTANGLE -> g.drawRect(position.x - xOffset, position.y - yOffset, width, height);
            case ELLIPSE -> g.drawOval(position.x - xOffset, position.y - yOffset, width, height);
            case POLYGON -> {
                int[] adjustedX = adjustCoordinates(polygonX, xOffset);
                int[] adjustedY = adjustCoordinates(polygonY, yOffset);
                g.drawPolygon(adjustedX, adjustedY, polygonX.length);
            }
            case POINT ->
                    g.drawLine(position.x - xOffset, position.y - yOffset, position.x - xOffset, position.y - yOffset);
            default -> g.drawString("Unknown Type", position.x - xOffset, position.y - yOffset);
        }
    }

    /**
     * Adjust an array of coordinates by subtracting an offset.
     *
     * @param coordinates The original coordinates.
     * @param offset      The offset to subtract.
     * @return The adjusted coordinates.
     */
    private int[] adjustCoordinates(int[] coordinates, int offset) {
        int[] adjusted = new int[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            adjusted[i] = coordinates[i] - offset;
        }
        return adjusted;
    }

    // Getters for encapsulated fields
    public Point getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Polygon getBounds() {
        switch (type) {
            case RECTANGLE:
                // Convert the rectangle to a polygon with four corners
                Polygon rectanglePolygon = new Polygon();
                rectanglePolygon.addPoint(position.x, position.y); // top-left
                rectanglePolygon.addPoint(position.x + width, position.y); // top-right
                rectanglePolygon.addPoint(position.x + width, position.y + height); // bottom-right
                rectanglePolygon.addPoint(position.x, position.y + height); // bottom-left
                return rectanglePolygon;

            case ELLIPSE:
                // Approximate the ellipse with a polygon by sampling points along the perimeter
                return getEllipsePolygon();

            case POLYGON:
                // Return the polygon with the given vertices
                return new Polygon(polygonX, polygonY, polygonX.length);

            case POINT:
                // A point is just a single location, so it can be treated as a degenerate polygon (with 1 point)
                Polygon pointPolygon = new Polygon();
                pointPolygon.addPoint(position.x, position.y);
                return pointPolygon;

            default:
                return new Polygon(); // Default case: return an empty polygon
        }
    }


    private Polygon getEllipsePolygon() {
        Polygon ellipsePolygon = new Polygon();
        int numPoints = 20; // Number of points to sample along the ellipse
        double angleIncrement = Math.PI * 2 / numPoints; // Angle increment for each point
        int centerX = position.x + width / 2; // Calculate the center X based on top-left anchor
        int centerY = position.y + height / 2; // Calculate the center Y based on top-left anchor
        for (int i = 0; i < numPoints; i++) {
            double angle = i * angleIncrement;
            int x = (int) (centerX + (width / 2.0) * Math.cos(angle)); // X coordinate of point
            int y = (int) (centerY + (height / 2.0) * Math.sin(angle)); // Y coordinate of point
            ellipsePolygon.addPoint(x, y);
        }
        return ellipsePolygon;
    }

    public String getName() {
        return name;
    }

    public CLASS getClassType() {
        return CLASS;
    }

    public int getId() {
        return id;
    }

    public List<Properties> getProperties() {
        return new ArrayList<>(properties);
    }

    public Properties getProperty(String name) {
        for (Properties property : properties) {
            if (property.name().equals(name)) {
                return property;
            }
        }
        return null;
    }

    public boolean hasProperty(String name) {
        for (Properties property : properties) {
            if (property.name().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEnemy() {
        for (Properties property : properties) {
            if (property.type().equals("bool") && property.name().equals("ENEMY")) {
                return true;
            }
        }
        return false;
    }
}
