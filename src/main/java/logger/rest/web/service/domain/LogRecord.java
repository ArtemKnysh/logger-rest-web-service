package logger.rest.web.service.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "log-record")
public class LogRecord implements Serializable {

    @Id
    private String userId;
    private String sessionId;
    private List<Action> actions;

    public LogRecord() {
        actions = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public void setActions(Action action) {
        actions.add(action);
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogRecord)) return false;
        LogRecord logRecord = (LogRecord) o;
        return Objects.equals(userId, logRecord.userId) && Objects.equals(sessionId, logRecord.sessionId) && Objects.equals(actions, logRecord.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, sessionId, actions);
    }

    @Override
    public String toString() {
        return "LogRecord{" +
                "userId='" + userId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", actions=" + actions +
                '}';
    }
}

