module com.example.server {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires io.netty.all;
    requires com.example.common;
    requires java.sql;

    opens com.example.server to javafx.fxml;
//    exports com.example.server;
}