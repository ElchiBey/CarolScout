# Carol Scout wants to reach her destination on the playground by using recursion
### The following instructions can be used to navigate the field:
##### 'r' Carol turns right from her own point of view.
##### 'l' Carol turns left from her own point of view.
##### 's' Carol takes a step in the current direction of gaze. For this, the absolute height difference must be less than or equal to one.
##### 'p' Carol places a block of ice on the field in the line of sight. To do this, she must carry at least one block of ice and the field in front of her must not have reached the maximum height (9). If Carol is in the water, she cannot place blocks of ice either.
##### 'n' Carol takes a block of ice from the field in the direction of view. To do this, she must be able to pick up at least one block of ice and the space in front of her must not be water (-1). If Carol is in the water, she cannot take any blocks of ice either.

The height of each field is a value from -1 to 9. -1 is water that is a block of ice deep. The height is the number of ice blocks above the water, and the number of ice blocks on a field is equal to the height plus one. Carol can move within this playing field and carry around 0 to 10 blocks of ice.
