/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cosc202.andie;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests are performed on a {@link BufferedImage} created before each test.
 *
 * @author timnanevo
 * @author leuhe253
 */
public class AndieTest {

    private BufferedImage testImage;

    public AndieTest() {
    }

    /*
    * <p>
    * Creates a 200x100 RGB image to be used as input for each test case.
    * </p>
    */
    @BeforeEach
    public void setUp() {
        testImage = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);

    }
    
/**
     * <p>
     * Test that ImageResize works correctly, scales image dimensions.
     * </p>
     *
     * <p>
     * ensure that resizing a 200x100 image to 50% produces a 100x50 image,
     * and resizing to 200% produces a 400x200 image.
     * </p>
     */
    @Test
    public void testImageSizeAfterResize() {
        ImageResize half = new ImageResize(50);
        BufferedImage resultH = half.apply(testImage);
        assertEquals(100, resultH.getWidth());
        assertEquals(50, resultH.getHeight());

        ImageResize doub = new ImageResize(200);
        BufferedImage resultD = doub.apply(testImage);
        assertEquals(400, resultD.getWidth());
        assertEquals(200, resultD.getHeight());
    }

    @AfterEach
    public void tearDown() {
    }

}
