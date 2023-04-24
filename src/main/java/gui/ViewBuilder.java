package gui;

/**
 * This is a functional interface.
 * Every JPanel in our application should implement this interface.
 * Its purpose is to enforce the use of the buildAndShowView() method.
 * This method should create all the components that will be visible on that JPanel.
 * It should also make the JPanel visible.
 * This method should then be called in the constructor.
 */
@FunctionalInterface
public interface ViewBuilder {
    void buildAndShowView();
}
