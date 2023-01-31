import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Datos {
    PreparedStatement ps;
    private JTextField valor_ID;
    private JTextField valor_Nombre;
    private JTextField valor_Celular;
    private JTextField valor_Correo;
    private JTextField valor_Ciudad;
    private JTextField valor_EstCivil;
    private JButton cargarDatosButton;
    private JLabel id_Jlabel;
    private JLabel nombre_Jlabel;
    private JLabel celular_Jlabel;
    private JLabel correo_Jlabel;
    private JLabel ciudad_Jlabel;
    private JLabel estCivil_Jlabel;
    private JLabel ingrese_Jlabel;
    private JPanel panelPrincipal;

    public Datos(){
        cargarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;

                try {
                    con = getConection();
                    ps = con.prepareStatement("Insert Into Informacion(id, nombre, celular, correo, ciudad, EstCivil) Values (?,?,?,?,?,?)");
                    ps.setString(1, valor_ID.getText());
                    ps.setString(2, valor_Nombre.getText());
                    ps.setString(3, valor_Celular.getText());
                    ps.setString(4, valor_Correo.getText());
                    ps.setString(5, valor_Ciudad.getText());
                    ps.setString(6, valor_EstCivil.getText());
                    System.out.println(ps);//Imprimir para


                    int res = ps.executeUpdate();

                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "Persona guardada");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar persona");
                    }

                    con.close();

                    valor_ID.setText("");
                    valor_Nombre.setText("");
                    valor_Ciudad.setText("");
                    valor_Correo.setText("");
                    valor_Celular.setText("");
                    valor_EstCivil.setText("");

                } catch  (HeadlessException | SQLException f) {
                    System.err.println(f);
                }
            }
        });

    }
    public static Connection getConection(){
        Connection con = null;
        String url = "jdbc:mysql://localhost/Persona",
            user = "root",
            password = "UGPCUGR2002";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;
    }


    public static void main(String[] args) {
        JFrame frame =new JFrame("Datos");

        frame.setContentPane(new Datos().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.pack();
        frame.setVisible(true);
    }

}
