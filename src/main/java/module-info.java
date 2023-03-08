module com.example.double_linkedlists {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.double_linkedlists to javafx.fxml;
    exports com.example.double_linkedlists;
}