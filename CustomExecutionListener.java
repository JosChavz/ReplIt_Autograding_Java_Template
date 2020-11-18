import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class CustomExecutionListener extends RunListener {

    public void testRunStarted(Description description) throws Exception {
        System.out.println("\033[1;36mNumber of tests to execute: " + description.testCount() + "\u001B[00m");
    }

    public void testRunFinished(Result result) throws Exception {
        System.out.println("Number of tests executed: " + result.getRunCount());
    }

    public void testStarted(Description description) throws Exception {
        System.out.println("Starting: " + description.getMethodName());
    }

    public void testFinished(Description description) throws Exception {
        System.out.println("Finished: " + description.getMethodName());
    }

    public void testFailure(Failure failure) throws Exception {
        System.out.println("\033[1;31mFailed: " + failure.getDescription().getMethodName() + "\u001B[00m");
    }

    public void testAssumptionFailure(Failure failure) {
        System.out.println("\033[1;31mFailed: " + failure.getDescription().getMethodName() + "\u001B[00m");
    }

    public void testIgnored(Description description) throws Exception {
        System.out.println("Ignored: " + description.getMethodName());
    }
}