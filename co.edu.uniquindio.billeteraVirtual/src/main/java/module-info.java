module co.edu.uniquindio.billeteravirtual.billeteravirtual {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens co.edu.uniquindio.billeteravirtual.billeteravirtual to javafx.fxml;
    exports co.edu.uniquindio.billeteravirtual.billeteravirtual;



    // Y si tienes subpaquetes, asegúrate de abrirlos también:
    opens co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;
    exports co.edu.uniquindio.billeteravirtual.billeteravirtual.ViewController;

    opens co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;
    exports co.edu.uniquindio.billeteravirtual.billeteravirtual.Controller;
}