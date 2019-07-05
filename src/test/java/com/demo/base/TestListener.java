package com.demo.base;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;


public class TestListener extends TestListenerAdapter implements ISuiteListener {

    private Logger logger = LoggerFactory.getLogger(TestListener.class);

    private String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    // Test Suite
    @Override
    public void onStart(ISuite suite) {
        System.out.println(" ******** ******** ******** ********  ******** ******** ******** ********");
        System.out.println(" ******** ******** Start Test Suite:  ******** ********"+ suite.getName());
        System.out.println(" ******** ******** ******** ********  ******** ******** ******** ********");
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println(" ******** ******** ******** ********  ******** ******** ******** ********");
        System.out.println(" ******** ******** Finished Test Suite: ******** ********"+ suite.getName());
        System.out.println(" ******** ******* ******** ******** ********  ******** ******** ******** \n \n");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("******** ******** Start Test: "+ context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("******** ******** Finished Test: "+ context.getName());
    }

    // Test Method
    private String getFullName(ITestResult result) {
        String classFullPath = result.getMethod().getRealClass().getName();
        String methodName = result.getMethod().getMethodName();
        return classFullPath.concat(".").concat(methodName);
    }


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(".......... Start: "+ getFullName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(".......... PASSED: "+getFullName(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {

        // to take screenshot when driver used during test
        Object currentClass = result.getInstance();
//        WebDriver driver = ((BaseTest) currentClass).getDriver();
//        if (driver != null) {
//            ScreenshooterHelper.shotErrorPage(driver, getFullName(result));
//        }
        System.out.println(".......... FAILED: "+ getFullName(result));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(".......... SKIPPED: "+ getFullName(result));
    }


}
