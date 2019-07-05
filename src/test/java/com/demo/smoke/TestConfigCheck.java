package com.demo.smoke;

import com.demo.base.BaseTest;
import com.demo.framework.TestConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestConfigCheck extends BaseTest {

    @Test
    public void readTestConfig() {
        System.out.println("environment is "+ TestConfig.getEnvironment());
        Assert.assertNotNull(TestConfig.getEnvironment());
    }
}
