package com.bluebank.api.entity;

import java.io.Serializable;
import java.util.Date;

public interface IAuditable extends Serializable {

    boolean hasCreateInfo();

    void addUpdateUser(Long userId);

}
