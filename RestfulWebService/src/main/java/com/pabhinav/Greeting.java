package com.pabhinav;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author pabhinav
 */
public class Greeting {

    @Getter(AccessLevel.PUBLIC)
    private final long id;

    @Getter(AccessLevel.PUBLIC)
    private final String content;

    public Greeting(long id, String content){
        this.id  = id;
        this.content = content;
    }
}
