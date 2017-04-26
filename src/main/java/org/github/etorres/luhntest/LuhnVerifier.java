package org.github.etorres.luhntest;

import javaslang.collection.Seq;

public class LuhnVerifier {

    public boolean verify(long number) {
        Seq<Character> reversedDigits = new LongReverser().reverseDigits(number);



//        Reverse the digits:
//
//>    61789372994
//                > Sum the odd digits:
//>    6 + 7 + 9 + 7 + 9 + 4 = 42 = s1
//                >    The even digits:
//>    1,  8,  3,  2,  9
//                >    Two times each even digit:
//>    2, 16,  6,  4, 18
//                >      Sum the digits of each multiplication:
//>    2,  7,  6,  4,  9
//                >      Sum the last:
//>    2 + 7 + 6 + 4 + 9 = 28 = s2

        // TODO
        return false;
    }

}
