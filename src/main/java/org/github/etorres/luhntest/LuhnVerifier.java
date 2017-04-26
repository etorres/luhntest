package org.github.etorres.luhntest;

import javaslang.Function1;
import javaslang.Tuple2;
import javaslang.collection.Seq;

import java.util.function.Predicate;

public class LuhnVerifier {

    private final static Predicate<Tuple2<Byte, Long>> isOddPosition = t -> t._2%2 == 0;

    private final  static Function1<Seq<Byte>, Seq<Tuple2<Byte, Long>>> digitsWithIndex =
            Seq::zipWithIndex;

    private final  static Function1<Seq<Tuple2<Byte, Long>>, Byte> sumOddDigits = s -> s
            .filter(isOddPosition)
            .map(Tuple2::_1)
            .fold((byte)0, (d1, d2) -> (byte)(d1 + d2));

    public boolean verify(long number) {
        Seq<Byte> reversedDigits = new LongReverser().reverseDigits(number);
        Function1<Seq<Byte>, Seq<Tuple2<Byte, Long>>> memDigitsWithIndex = Function1
                .of(digitsWithIndex).memoized();
        Seq<Tuple2<Byte, Long>> digitsWithIndexTuple = memDigitsWithIndex.apply(reversedDigits);
        Byte oddSum = sumOddDigits.apply(digitsWithIndexTuple);



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
