Team Name: Immortal Jellyfish
Team Members: Timna Nevo, Manuella Parackal, Kai Shieffelbein, Hebe Leung

## User Guide

## Edit Operations

**ImageResize** Once an image is uploaded, click on the edit menu at the top bar, then resize. A pop up will appear asking to give a scale factor as a percent. Enter any integer between 1 and 300 and click ok. If the integer is outside of the range or not an integer, the computer will explain the issue. Then click ok and you will be prompted to enter an integer again.

**Flip-Horizontal**

**Flip-Vertical**

## Color Operations

**ImageThresholding:** Once an image is uploaded, click on colour on the top bar, then threshold. Enter any integer between 0 and 255 and click ok. If the input is outside of the range or not an integer, the computer will explain the issue. Then click ok and you will be prompted to enter an integer again.

**GreyScale**

**Inversion** Once image is uploaded, click colour on the top bar, then Inversion. Image will then be inverted.

**Color Channel Swapping** 

## File Operations

**Export:** Once you're finished editing your image, go to File and click Export. You'll be given the option to save it anywhere on your computer. Once you've chosen a destination, name your file in the format `ImageName.extension` (e.g. `image.jpg`, `image.png`) and click Save.

## Filter Operations

**MedianFilter** Once your image is uploaded, click Filter on the menu bar and select Median Filter. Each time you click it, the filter will be applied again. Keep clicking until you're happy with the result.

**SharpenFilter** Once your image is uploaded, click Filter on the menu bar and select Sharpen Filter. Each time you click it, the filter will be applied again. Keep clicking until you're happy with the result.

**GaussianFilter**

## Rotate Operations

**90degreesClockwise:** Once your image is uploaded, click Rotate on the menu bar and select 90° clockwise. Each time you click it, the image will rotate again.

**90degreesAntiClockwise:**  Once your image is uploaded, click Rotate on the menu bar and select 90° anticlockwise. Each time you click it, the image will rotate again.

**180 rotate:**  Once your image is uploaded, click Rotate on the menu bar and select 180° rotate. Each time you click it, the image will rotate again.


## Settings

**LanguageChange:** If you'd like to switch the language, head to **Settings** it will drop down with the option of **English** or **German**. Click your preferred language and a pop-up will appear letting you know you need to restart ANDIE for the change to take effect. Once you reopen ANDIE, the language will have changed. You can do the same thing to switch back anytime.

## Pop ups 
**Heads up:** If you try to export an image with transparency, a pop-up will appear saying "Unsupported Image type".

**Unsaved Changes Warning**:ANDIE will warn you if you have unsaved changes in two situations:
- **Exiting the program** – if you attempt to exit with unsaved changes, a dialog will appear asking if you want to save before closing.
- **Opening a new image** – if you attempt to open a new image with unsaved changes, a dialog will appear asking if you want to save first.
In both cases you can choose:
- **Yes** – saves your changes then continues
- **No** – discards your changes and continues
- **Cancel** – returns you to ANDIE without doing anything

 

**Heads up:**

## Testing
**Unit Test** a unit test to ensure the ImageResize operation worked after doubling and halving an image

**Visual** 
**General Images**
Checked that entering invalid user inputs for Guassian Filter, Mean Filter, Threshold, or Resize is either not possible, or prompts the user to try again after explaining the issue. Tested that pop-ups show up when the user tries to change an image before something has been uploaded. Checked that rotations and flips did the desired action on the image. Checked that all settings, filters and pop-ups turn german when the setting is switched to german.
**Testing Image** 
The testing image was used to check proper color swapping and that the alpha channel was not thrown away.

## Bugs and refactoring
no known bugs or problems exist and no signficant refactoring was done to ANDIE

















project beyond the features that were added.