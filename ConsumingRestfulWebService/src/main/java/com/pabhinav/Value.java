package com.pabhinav;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * @author pabhinav
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String quote;

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
