package Utils;
/**
 * The CallBackAction interface represents a callback action to be executed at a later time.
 * This is typically used when you want to define a specific action or behavior that will be
 * triggered asynchronously or in response to some event.
 *
 * <p>The interface includes a single method, {@link #onAction()}, which should be implemented
 * to define the action that will be performed when the callback is triggered.</p>
 *
 * <p>Usage Example:</p>
 * <pre>
 * public class MyClass implements CallBackAction {
 *     &#064;Override
 *     public void onAction() {
 *         System.out.println("Action performed!");
 *     }
 * }
 * </pre>
 */
public interface CallBackAction {
    /**
     * This method is triggered when the action is to be performed.
     * The specific behavior of the action should be implemented in the class that
     * implements this interface.
     */
    void onAction();
}
