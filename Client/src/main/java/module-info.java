module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires io.netty.all;
    requires java.desktop;
    requires com.example.common;

    opens com.example.client to javafx.fxml;
//    exports com.example.client;
    exports ClientPackage;
}