module com.example.common {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires io.netty.all;

    opens com.example.common to javafx.fxml;
//    exports com.example.common;
    exports Common;
}