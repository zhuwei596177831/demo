package com.example;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-11-06 15:38:40
 * @description
 */
@Component
public abstract class LookUpUtils {
    @Lookup
    public abstract PrototypeComponent getPrototypeComponent();
}
