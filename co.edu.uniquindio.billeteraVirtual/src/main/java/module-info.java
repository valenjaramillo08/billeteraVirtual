module co.edu.uniquindio.billeteravirtual.billeteravirtual {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires javafx.base;
    requires itextpdf;

    opens co.edu.uniquindio.billeteravirtual.billeteravirtual to javafx.fxml;
    exports co.edu.uniquindio.billeteravirtual.billeteravirtual;
    opens co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController.usuario to javafx.fxml;



    // Y si tienes subpaquetes, asegúrate de abrirlos también:
    opens co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;
    exports co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

    opens co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;
    exports co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;
}