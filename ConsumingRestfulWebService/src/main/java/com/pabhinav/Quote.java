package com.pabhinav;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * @author pabhinav
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    @Getter @Setter
    private String type;

    @Getter @Setter
    private Value value;

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
