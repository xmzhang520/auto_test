package com.demo.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;


@Listeners({TestListener.class})
public class BaseTest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

}
