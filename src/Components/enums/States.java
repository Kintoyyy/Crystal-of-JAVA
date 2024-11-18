package Components.enums;

/**
 * Enum representing the various states of a component.
 * <p>
 * This enum defines five states that a component can be in:
 * <ul>
 *   <li><b>IDLE</b> - The component is in an idle state, with no interaction occurring.</li>
 *   <li><b>PRESSED</b> - The component is being pressed or clicked.</li>
 *   <li><b>HOVERED</b> - The component is being hovered over by the mouse.</li>
 *   <li><b>DRAGGED</b> - The component is being dragged by the user.</li>
 *   <li><b>ACTIVE</b> - The component is actively in use or enabled.</li>
 * </ul>
 * </p>
 */
public enum States {
    IDLE,    // The component is in an idle state, with no interaction occurring
    PRESSED, // The component is being pressed or clicked
    HOVERED, // The component is being hovered over by the mouse
    DRAGGED, // The component is being dragged by the user
    ACTIVE   // The component is actively in use or enabled
}
