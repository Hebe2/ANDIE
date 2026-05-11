Team Name: Immortal Jellyfish

Team Members: Timna Nevo, Manuella Parackal, Kai Shieffelbein, Hebe Leung

## User Guide

## Edit Operations

**ImageResize** Once an image is uploaded, click on the edit menu at the top bar, then resize. A pop up will appear asking to give a scale factor as a percent. Enter any integer between 1 and 300 and click ok. If the integer is outside of the range or not an integer, the computer will explain the issue. Then click ok and you will be prompted to enter an integer again.

**Flip-Horizontal** Once an image is uploaded, click on the Edit menu at the top menu bar. Click on "Flip - Horizontal" to flip your image horizontally.

**Flip-Vertical** Once an image is uploaded, click on the Edit menu at the top menu bar. Click on "Flip - Vertical" to flip your image vertically.

**Crop** Once an image is uploaded, select a region by dragging your mouse across the image. Then click on the Edit menu at the top menu bar and select Crop to crop the image to your selected region. 

## Color Operations

**ImageThresholding:** Once an image is uploaded, click on colour on the top bar, then threshold. Enter any integer between 0 and 255 and click ok. If the input is outside of the range or not an integer, the computer will explain the issue. Then click ok and you will be prompted to enter an integer again.

**GreyScale** Once an image is uploaded, click Colour on the top menu bar and select "GreyScale." Image will then appear in grey sale. 

**Inversion** Once image is uploaded, click colour on the top bar, then Inversion. Image will then be inverted.

**Color Channel Swapping** Once your image is uploaded, click on Colour on the top menu. Click on "Color Channel Swap." A window will popup asking you to pick a channel order. Click on the arrow and a drop down menu will appear. Select your desired color swaps. 

**Brightness & Contrast:** Once your image is uploaded, click **Colour** on the menu bar and select **Brightness & Contrast**. A dialog box will appear showing a small preview of your image along with two sliders — one for brightness and one for contrast, both ranging from -100 to 100. As you adjust the sliders the preview will update so you can see the effect before committing. Once you're happy with the result, click **OK** to apply it to your image.


**Contrast Mask:** Once your image is uploaded, click **Colour** on the menu bar and select **Contrast Mask**. A dialog box will appear with two sliders — **Blur Radius** (1–16) and **Strength** (0–100%). Contrast masking sharpens the detail in your image by blending a blurred version

**Colour Tinter (Extra Feature):** Once your image is uploaded, click **Colour** on the menu bar and select **Colour Tinter**. A dialog box will appear with a colour picker where you can select your desired tint colour using Swatches, HSV, HSL, RGB, or CMYK colour modes. Use the **Strength** slider (0–100) to control how intense the tint is applied. Once you're happy, click **OK** to apply it to your image.

## File Operations

**Export:** Once you're finished editing your image, go to File and click Export. You'll be given the option to save it anywhere on your computer. Once you've chosen a destination, name your file in the format `ImageName.extension` (e.g. `image.jpg`, `image.png`) and click Save.

## Filter Operations

**(Multi Threading)Median Filter:** Once your image is uploaded, click **Filter** on the menu bar and select **Median Filter**. A dialog box will appear where you can adjust the filter radius and choose how many threads to use for computation (it will suggest a thread count based on your computer's available cores). Each time you apply the filter it will be applied on top of the previous result. Keep applying until you're happy with the outcome.

**Sharpen Filter** Once your image is uploaded, click Filter on the menu bar and select Sharpen Filter. Each time you click it, the filter will be applied again. Keep clicking until you're happy with the result.

**Gaussian Filter** Once your image is uploaded, click Filter on the top menu bar and selecct Gassian Filter. A window will appear prompting you to choose a radius value. Use the up and down arrows to adjust the radius. The radius ranges from 1 (lowest blur) to 10 (strongest blur).

**Random Scattering:** Once your image is uploaded, click **Filter** on the menu bar and select **Random Scattering**. A dialog box will appear where you can enter a scatter radius between 1 and 50 — the higher the value, the more scattered and noisy the image will appear. Click **OK** to apply the effect.

**Emboss Filter**

## Rotate Operations

**90degreesClockwise:** Once your image is uploaded, click Rotate on the menu bar and select 90° clockwise. Each time you click it, the image will rotate again.

**90degreesAntiClockwise:**  Once your image is uploaded, click Rotate on the menu bar and select 90° anticlockwise. Each time you click it, the image will rotate again.

**180 rotate:**  Once your image is uploaded, click Rotate on the menu bar and select 180° rotate. Each time you click it, the image will rotate again.

## Macros 

## Draw
Once an image is uploaded and select a region by dragging your mouse across the image. Click on the Draw menu at the top menu bar and select a shape type: rectangle, oval or line. Once the shape type is selected, you can select whether to make the shape filled or unfilled, pick the fill color and outline color (Black, Red, Green, Blue, White, Yellow), and whether to make the outline solid or dashed. Once you are happy with your selections click ok and the shape will be drawn onto the selected regions.  

## Settings

**LanguageChange:** If you'd like to switch the language, head to **Settings** it will drop down with the option of **English** or **German**. Click your preferred language and a pop-up will appear letting you know you need to restart ANDIE for the change to take effect. Once you reopen ANDIE, the language will have changed. You can do the same thing to switch back anytime.

## Pop ups 
**Heads up:** If you try to export an image with transparency, a pop-up will appear saying "Unsupported Image type".

**Unsaved Changes Warning**:ANDIE will warn you if you have unsaved changes in two situations:
- **Exiting the program** – if you attempt to exit with unsaved changes, a message will appear asking if you want to save before closing.

- **Opening a new image** – if you attempt to open a new image with unsaved changes, a dialog will appear asking if you want to save first.

In both cases you can choose:
- **Yes** – saves your changes then continues
- **No** – discards your changes and continues
- **Cancel** – returns you to ANDIE without doing anything

## Testing
**Unit Test** a unit test to ensure the ImageResize operation worked after doubling and halving an image

**Unit Test** a unit test to ensure the ImageResize operation worked after doubling and halving an image

**Visual Testing** 

**General Images**
Checked that entering invalid user inputs for Guassian Filter, Mean Filter, Threshold, or Resize is either not possible, or prompts the user to try again after explaining the issue. Tested that pop-ups show up when the user tries to change an image before something has been uploaded. Checked that rotations and flips did the desired action on the image. Checked that all settings, filters and pop-ups turn german when the setting is switched to German. Tested all filters and color actions to ensure they had the desired effect. Tested that save and exporting an image worked and was able to export to the computer. 
 
**Testing Image** 
The testing image was used to check proper color swapping and that the alpha channel was not thrown away. All filters were also checked again on the testing image to ensure proper functionality. 

## Bugs and refactoring
No known bugs or problems exist and no signficant refactoring was done to ANDIE

## Special Feature
After more than two attempts to edit an image without opening an image first, the user will recieve a surprise
