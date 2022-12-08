/**
 * @author celen
 *
 */
module Project10 {
	requires javafx.graphics;
	requires javafx.controls;
	requires java.desktop;
	requires javafx.base;
	opens thePackage to javafx.graphics;
}