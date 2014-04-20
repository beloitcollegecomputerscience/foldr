
package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
// ShapeCollectionTest.class,
// AnimateMovementTest.class,
    ShapeTest.class, 
    ToolTest.class, 
    MessagesUtilsTest.class, 
    MessagesTest.class,
    CustomCameraTest.class
})
public class TestSuite {

}
