package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label lblAgent1;

    @FXML
    private Label lblAgent2;

    @FXML
    private Label lblAgent3;

    @FXML
    private Label lblAgent4;

    @FXML
    private Label lblAgent5;

    @FXML
    private Label lblAgent6;

    @FXML
    private Label lblAgent7;

    @FXML
    private Label lblAgent8;

    @FXML
    private TextField txtAgent1;

    @FXML
    private TextField txtAgent2;

    @FXML
    private TextField txtAgent3;

    @FXML
    private TextField txtAgent4;

    @FXML
    private TextField txtAgent5;

    @FXML
    private TextField txtAgent6;

    @FXML
    private TextField txtAgent7;

    @FXML
    private TextField txtAgent8;

    @FXML
    private ComboBox<String> cmbAgentId;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    void handleBtnEdit(ActionEvent event) {
        btnSave.setDisable(false);
        btnEdit.setDisable(true);
        makeTextWritable();
    }

    @FXML
    void handleBtnSave(ActionEvent event) {
        btnSave.setDisable(true);
        btnEdit.setDisable(false);
        makeTextReadOnly();
        Agent updatedAgent = createAgentFromFields();
        String query = "UPDATE agents SET AgtFirstName = '" + updatedAgent.getAgentFirstName() + "', AgtMiddleInitial = '" + updatedAgent.getAgentMiddleInitial() + "', AgtLastName = '" + updatedAgent.getAgentLastName() + "', AgtBusPhone = '" + updatedAgent.getAgentBusPhone() + "', AgtEmail = '" + updatedAgent.getAgentEmail() + "', AgtPosition = '" + updatedAgent.getAgentPosition() + "', AgencyId = '" + updatedAgent.getAgencyId().toString() + "' WHERE AgentId = " + updatedAgent.getAgentId();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");
            Statement stmt = conn.createStatement();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.executeUpdate();
            conn.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleCmbAgentId(ActionEvent event) {
        Integer agentId = Integer.parseInt(cmbAgentId.getValue());
        populateByAgentId(agentId);
    }
    public void makeTextReadOnly() {
        txtAgent2.setEditable(false);
        txtAgent3.setEditable(false);
        txtAgent4.setEditable(false);
        txtAgent5.setEditable(false);
        txtAgent6.setEditable(false);
        txtAgent7.setEditable(false);
        txtAgent8.setEditable(false);
    }
    public void makeTextWritable() {
        txtAgent2.setEditable(true);
        txtAgent3.setEditable(true);
        txtAgent4.setEditable(true);
        txtAgent5.setEditable(true);
        txtAgent6.setEditable(true);
        txtAgent7.setEditable(true);
        txtAgent8.setEditable(true);
    }
    public void populateByAgentId(Integer agentId) {
        ObservableList<Agent> agentInfo = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM agents WHERE AgentId = " + agentId.toString();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()){
                agentInfo.add(new Agent(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8)));
            }
            txtAgent1.setText(agentInfo.get(0).getAgentId().toString());
            txtAgent2.setText(agentInfo.get(0).getAgentFirstName());
            txtAgent3.setText(agentInfo.get(0).getAgentMiddleInitial());
            txtAgent4.setText(agentInfo.get(0).getAgentLastName());
            txtAgent5.setText(agentInfo.get(0).getAgentBusPhone());
            txtAgent6.setText(agentInfo.get(0).getAgentEmail());
            txtAgent7.setText(agentInfo.get(0).getAgentPosition());
            txtAgent8.setText(agentInfo.get(0).getAgencyId().toString());
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void populateLabels() {
        ObservableList<String> columns = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");
            Statement stmt = conn.createStatement();
            String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'agents' ORDER BY ORDINAL_POSITION";
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                columns.add(resultSet.getString(1));
            }
            lblAgent1.setText(columns.get(0));
            lblAgent2.setText(columns.get(1));
            lblAgent3.setText(columns.get(2));
            lblAgent4.setText(columns.get(3));
            lblAgent5.setText(columns.get(4));
            lblAgent6.setText(columns.get(5));
            lblAgent7.setText(columns.get(6));
            lblAgent8.setText(columns.get(7));
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public Agent createAgentFromFields() {
        Agent agent = new Agent(Integer.parseInt(txtAgent1.getText()), txtAgent2.getText(), txtAgent3.getText(), txtAgent4.getText(), txtAgent5.getText(), txtAgent6.getText(), txtAgent7.getText(), Integer.parseInt(txtAgent8.getText()));
        return agent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateLabels();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM agents");

            while (resultSet.next()){
                cmbAgentId.getItems().add(resultSet.getString(1));
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
