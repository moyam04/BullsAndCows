# BullsAndCows

This assignment has been so far the most difficult but the funniest to make.

It was difficult to incorporate (methodA "contains"). I feel I could have written the code without it. However, because it was required for the assignment, I used it in method d.
	Method D could have been written instead as shown below.

int cows = 0;
for(int i = 0; i < guess.length; i++){
            for (int j = 0; j<secret.length; j++)
                if(guess[i] == secret[j] && i!=j)
                    cows++;

The main challenge for me was working with zeroes. For example, if the player enters "0587" as the guess, by getting input as integer, we will lose the leading zero.
	The instructions mention that the input has to be integer; however, dealing with leading zeroes is problematic.
	After talking to the TA, I believe that it was allowed to take the player's input as String and then parse it into integer.

In total, it took me well over 20 hours to work on this assigment; however, I do feel I learned a lot in the process.
