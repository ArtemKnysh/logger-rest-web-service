package logger.rest.web.service.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Action implements Serializable {

    private List<Property> properties;
    private LocalDateTime time;
    private String type;

    public Action() {
        properties = new ArrayList<>();
    }

    public Action(String time, String type, List<Property> properties) {
        this.time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE);
        this.type = type;
        this.properties = properties;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Action)) return false;
        Action action = (Action) o;
        return Objects.equals(properties, action.properties) && Objects.equals(time, action.time) && Objects.equals(type, action.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(properties, time, type);
    }

    @Override
    public String toString() {
        return "Action{" +
                "properties=" + properties +
                ", time=" + time +
                ", type='" + type + '\'' +
                '}';
    }
}
