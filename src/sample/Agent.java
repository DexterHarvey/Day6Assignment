package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class Agent {
    private Integer agentId;
    private String  agentFirstName;
    private String agentMiddleInitial;
    private String agentLastName;
    private String agentBusPhone;
    private String agentEmail;
    private Integer agencyId;

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public void setAgentFirstName(String agentFirstName) {
        this.agentFirstName = agentFirstName;
    }

    public void setAgentMiddleInitial(String agentMiddleInitial) {
        this.agentMiddleInitial = agentMiddleInitial;
    }

    public void setAgentLastName(String agentLastName) {
        this.agentLastName = agentLastName;
    }

    public void setAgentBusPhone(String agentBusPhone) {
        this.agentBusPhone = agentBusPhone;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    public void setAgentPosition(String agentPosition) {
        this.agentPosition = agentPosition;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    private String agentPosition;

    public Integer getAgentId() {
        return agentId;
    }

    public String getAgentFirstName() {
        return agentFirstName;
    }

    public String getAgentMiddleInitial() {
        return agentMiddleInitial;
    }

    public String getAgentLastName() {
        return agentLastName;
    }

    public String getAgentBusPhone() {
        return agentBusPhone;
    }

    public String getAgentEmail() {
        return agentEmail;
    }

    public String getAgentPosition() {
        return agentPosition;
    }

    public Integer getAgencyId() {
        return agencyId;
    }
    public Agent() {

    }
    public Agent(Integer agentId, String agentFirstName, String agentMiddleInitial, String agentLastName, String agentBusPhone, String agentEmail, String agentPosition, Integer agencyId) {
        this.agentId = agentId;
        this.agentFirstName = agentFirstName;
        this.agentMiddleInitial = agentMiddleInitial;
        this.agentLastName = agentLastName;
        this.agentBusPhone = agentBusPhone;
        this.agentEmail = agentEmail;
        this.agencyId = agencyId;
        this.agentPosition = agentPosition;
    }
}
