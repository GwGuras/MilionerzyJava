module pl.edu.anstar.milionerzy {
	requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.edu.anstar.milionerzy to javafx.fxml;
    exports pl.edu.anstar.milionerzy;
}
